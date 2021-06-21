import java.awt.Color;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;


//DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE 
public class GameScene extends JPanel implements KeyListener{
	
	public Set<GameObject> gameObjects = new HashSet<>();
    public Set<GameObject> deleteobjects = new HashSet<>();
    public Set<GameObject> newGameObjects = new HashSet<>();
    public Set<Shot> shots = new HashSet<Shot>();
    public Set<Enemy> enemies = new HashSet<Enemy>();
	
    private Thread thread;
    private boolean isLeft = false;
    private boolean isRight = false;
    private boolean isUp = false;
    private boolean isDown = false;
    private GameOver gameOver = new GameOver();
    
    protected boolean space = false;
    protected boolean reload = false;
    
    private int reload_timer = 0;
    protected int lastDirection = 1;
    
    private int enemyRespawn_timer = 0;
    protected boolean enemyRespawn = false;
    
    private Ship ship;
    
    private boolean triggerEndGame = false;
    private boolean gameThread = true;
    
    private int score = 0;
    private JLabel scoreLabel = new JLabel("Score: " + score);

	
	public GameScene() {
		ship = new Ship(this);
	    ship.position = new Position(Definitions.SHIP_START);
	    
	    
	    gameObjects.add(ship);
        this.setFocusable(true);
        this.requestFocusInWindow();
        
        this.add(scoreLabel);
	    this.addKeyListener(this);
	    this.MoveThread();


	}
	
	public void MoveThread() {
		new Thread(() ->{
			while(gameThread) {
		        this.setFocusable(true);
		        this.requestFocusInWindow();
				animate();
			try {
				Thread.sleep(20);
			} catch(InterruptedException e) {
				e.printStackTrace();
				}
			}
		}).start();
	}
	
	public void animate() {
		
		scoreLabel.setText("Score: " + score);
		this.requestFocusInWindow();
		
		if (reload_timer == 100) {
			reload_timer = 0;
			reload = true;
		}
		
		if (enemyRespawn_timer == 10) {
			enemyRespawn = true;
			enemyRespawn_timer = 0;
		}
		
		enemyRespawn_timer++;
		reload_timer++;

        if (isLeft) {
        	ship.position.setX(ship.position.getX()-5);
        	lastDirection = 1;
        	}
        if (isRight) {
        	ship.position.setX(ship.position.getX()+5);
        	lastDirection = 2;
        	}
        if (isUp) {
        	ship.position.setY(ship.position.getY()-5);
        	lastDirection = 3;
        	}
        if (isDown) {
        	ship.position.setY(ship.position.getY()+5);
        	lastDirection = 4;
        	}
        
        
        gameObjects.addAll(shots);
        gameObjects.addAll(enemies);
        gameObjects.removeAll(deleteobjects);
        shots.removeAll(deleteobjects);
        enemies.removeAll(deleteobjects);
        deleteobjects.clear();
       
  
        for(GameObject gameObject: gameObjects){
	        gameObject.update();
	    }
        
        for (Enemy enemyy : enemies) {
        	for (Shot shot : shots) {
            	if(isCatched(enemyy, shot)) {
            		deleteobjects.add(shot);
            		deleteobjects.add(enemyy);
            		score++;
            	}
        	}
        	if (enemyy.position.getX() > Definitions.WINDOW_WIDTH || enemyy.position.getY() > Definitions.WINDOW_HEIGHT || enemyy.position.getX() < 0 || enemyy.position.getY() < 0) {
        		deleteobjects.add(enemyy);
			}
        	Point point1 = new Point();
        	point1.x=enemyy.position.getX();
        	point1.y=enemyy.position.getY();
        	
        	Point point2 = new Point();
        	point2.x=enemyy.position.getX()+Definitions.HITBOX_SIZE;
        	point2.y=enemyy.position.getY();
        	
        	Point point3 = new Point();
        	point3.x=enemyy.position.getX()+Definitions.HITBOX_SIZE;
        	point3.y=enemyy.position.getY()+Definitions.HITBOX_SIZE;
        	
        	Point point4 = new Point();
        	point4.x=enemyy.position.getX();
        	point4.y=enemyy.position.getY()+Definitions.HITBOX_SIZE;

        	if (ship.polygon != null){
        			if (ship.polygon.contains(point1) || ship.polygon.contains(point2) || ship.polygon.contains(point3) || ship.polygon.contains(point4)) {
        		        deleteobjects.add(ship);
        		        triggerEndGame = true;
        		        gameThread = false;
        		        gameObjects.clear();
        			}
        	} 
	    this.repaint();
        }
	}
	
	public boolean isCatched(Enemy enemy,Shot shot) {
		int enemy_x1 = enemy.position.getX();
		int enemy_x2 = enemy.position.getX()+Definitions.ZOMBIE_BODY_X_SIZE;

		int enemy_y1 = enemy.position.getY();
		int enemy_y2 = enemy.position.getY()+Definitions.ZOMBIE_BODY_Y_SIZE;
		
		int shot_x = shot.position.getX();
		int shot_y = shot.position.getY();
		
		if((enemy_x1 < shot_x && enemy_x2 > shot_x) && (enemy_y1 < shot_y && enemy_y2 > shot_y)) {
			return true;
		}
		return false;
	}
	
	
	public void paint (Graphics g) {
		super.paint(g);
		this.setBackground(Color.white);
		super.paint(g);
        for(GameObject gameObject: this.gameObjects){
            gameObject.draw(g);
        }
    	gameOver.setBounds(0,0, Definitions.WINDOW_WIDTH, Definitions.WINDOW_HEIGHT);
    	if (triggerEndGame) {
    		this.removeAll();
			this.add(gameOver);
			gameOver.setScore(this.score);
			repaint();
		}
	}
	
	
	public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_LEFT) {
        	isLeft = true;
        	}
        if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
        	isRight = true;
        	}
        if (e.getKeyCode()==KeyEvent.VK_UP) {
        	isUp = true;
        	}
        if (e.getKeyCode()==KeyEvent.VK_DOWN) {
        	isDown = true;
        	}	
        if (e.getKeyCode()==KeyEvent.VK_SPACE) space = true;
            
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_LEFT) isLeft = false;
        if (e.getKeyCode()==KeyEvent.VK_RIGHT) isRight = false;
        if (e.getKeyCode()==KeyEvent.VK_UP) isUp = false;
        if (e.getKeyCode()==KeyEvent.VK_DOWN) {
        	isDown = false;
        }
        if (e.getKeyCode()==KeyEvent.VK_SPACE) {   
        	space = false;
        }  
    }
    @Override
    public void keyTyped(KeyEvent e) {
    	
    }
    
 
}


