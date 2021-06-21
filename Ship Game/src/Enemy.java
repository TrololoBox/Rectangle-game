import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

//DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE 
public class Enemy extends GameObject{
    private BufferedImage texture;
    private Random rnd = new Random();   
    
	public Enemy(GameScene game) { 
        super(game);
        rnd = new Random();
        try {
            texture = ImageIO.read(new File("zombi.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
	
	public void draw(Graphics g) {
        super.draw(g);
     
        int x = position.getX();
        int y = position.getY();      
        g.drawImage(texture, position.getX() + 15, position.getY() + 10, null);

        
    }
	
	public void update() {
	    super.update();
	    int random = rnd.nextInt(4);

	    switch (random) {
		case 0:
			position.setX(position.getX()+5);
			break;
		case 1:
			position.setX(position.getX()-6);
			break;
		case 2:
			position.setY(position.getY()+6);
			break;
		case 3:
			position.setY(position.getY()-7);
			break;
		}
	}
	
	
	
	 

}
