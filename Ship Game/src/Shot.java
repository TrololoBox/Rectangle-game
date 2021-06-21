import java.awt.*;

//DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE 

public class Shot extends GameObject{ 
	private int lastDirection;
	
	public Shot(GameScene game,int lastDirection) {
        super(game);
        this.lastDirection = lastDirection;
    }
	
	@Override
    public void draw(Graphics g) {
        super.draw(g);
        //g.fillRect(position.x, position.y, 5, 5);
        g.fillRect(position.getX(), position.getY(), 5, 5);

    }

    @Override
    public void update() {
        super.update();
        switch (this.lastDirection) {
		case 1:
			position.setX(position.getX() - 10);
			break;
		
		case 2:
			position.setX(position.getX() + 10);
			break;
			
		case 3:
			position.setY(position.getY() - 10);
			break;
	
		case 4:
			position.setY(position.getY() + 10);
			break;

		default:
			break;
		}
        
    }

}
