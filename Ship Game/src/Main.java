import javax.swing.JFrame;
import javax.swing.WindowConstants;


//DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE 
public class Main extends JFrame{

	public static void main(String[] args) {
		new Main();

	}
	
	public static TutorialScene tutorialGameScene = new TutorialScene();
	
	public static GameScene gameScene = new GameScene();
	
	protected static int sceneChange = 0;
	
	public Main() {
	    this.setVisible(true);
		this.setSize(Definitions.WINDOW_WIDTH, Definitions.WINDOW_HEIGHT);
	    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    this.setResizable(false);
	    this.setLayout(null);
	    this.setTitle("Ship  Game");
	    tutorialGameScene.setBounds(0, 0, Definitions.WINDOW_WIDTH, Definitions.WINDOW_HEIGHT);	
	    this.add(tutorialGameScene);   
	}	
}
