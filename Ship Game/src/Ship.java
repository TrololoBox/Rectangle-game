import java.awt.*;
import java.util.Random;


//DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE 
public class Ship extends GameObject{
    protected Polygon polygon;
    private int respawnSwitch = 0;
    private Random rnd = new Random();
	public Ship(GameScene game) { 
        super(game);
    }
	
	@Override
    public void draw(Graphics g) {
        super.draw(g);

        int x = position.getX();
        int y = position.getY();

        polygon = new Polygon();
        g.setColor(Color.RED);
        polygon.addPoint(x, y);
        polygon.addPoint(x+40, y);
        polygon.addPoint(x+40, y-40);
        polygon.addPoint(x, y-40);

        //g.drawLine(x-25, y, x+25, y);
        //g.drawLine(x-25, y, x, y-70);
        //g.drawLine(x+25, y, x, y-70);
        g.drawPolygon(polygon);
        

    }
	
	public void update() {
	    super.update();
	    
	    if(game.enemyRespawn) {
			Enemy enemy = new Enemy(game);
			respawnSwitch = rnd.nextInt(3);
			switch (respawnSwitch) {
	    	case 0:
	    		enemy.position = new Position(Definitions.ZOMBIE_RESPAWN_LEFT);
	    		break;
		
	    	case 1:
	    		enemy.position = new Position(Definitions.ZOMBIE_RESPAWN_RIGHT);
	    		break;
		
	    	case 2:
	    		enemy.position = new Position(Definitions.ZOMBIE_RESPAWN_TOP);
	    		break;

	    	default:
	    		enemy.position = new Position(Definitions.ZOMBIE_RESPAWN_TOP);
	    		break;
			}

	        game.enemies.add(enemy);
	        game.enemyRespawn = false;
		}
	    
	    
	    if(game.space && game.reload){
	        Shot shot = new Shot(game,game.lastDirection);
	        //shot.position = new Position(position.x +20, position.y-20);
	        shot.position = new Position(this.position.getX() + 20,this.position.getY() - 20) ;
	        game.shots.add(shot);
	        //game.newGameObjects.add(shot);
	        game.reload = false;
	    }
	    
	    
	}

}
