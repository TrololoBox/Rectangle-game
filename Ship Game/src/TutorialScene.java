import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TutorialScene extends JPanel{
	
	
	//DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE 
	private GameScene gameScene = new GameScene();
	
	
	public TutorialScene() {
		
	}
	
	public void paint (Graphics g) {
		super.paint(g);
		this.setBackground(Color.black);
		this.setLayout(null);
		JLabel labelTutorialFirstLine = new JLabel("<html>" + "<B>" + "Try to reach the maximum score you can by shooting Zombies on the screen." + "</B>" + "</html>");
		JLabel labelTutorialSecondLine = new JLabel("<html>" + "<B>" + "Arrow keys to move, space to shoot." + "</B>" + "</html>");
		JLabel labelTutoriaThirdLine = new JLabel("<html>" + "<B>" + "You shoot to the last Direction you moved" + "</B>" + "</html>");
		JLabel labelTutorialLastLine = new JLabel("<html>" + "<B>" + "Reload delay 2 sec, respawn of Zombies 0.4 sec. Yes, its not fair game!" + "</B>" + "</html>");

		labelTutorialFirstLine.setBounds(10, 10, 1000, 100);
		labelTutorialSecondLine.setBounds(50, 50, 1000, 100);
		labelTutoriaThirdLine.setBounds(100, 100, 1000, 100);
		labelTutorialLastLine.setBounds(150, 150, 1000, 100);

		labelTutorialFirstLine.setFont(new Font("Serif", Font.PLAIN, 25));
		labelTutorialSecondLine.setFont(new Font("Serif", Font.PLAIN, 25));
		labelTutoriaThirdLine.setFont(new Font("Serif", Font.PLAIN, 25));
		labelTutorialLastLine.setFont(new Font("Serif", Font.PLAIN, 25));
		
		JButton button = new JButton("Start GAME");
		button.setBounds(Definitions.WINDOW_WIDTH / 2 - 150 / 2, Definitions.WINDOW_HEIGHT / 2, 150, 50);
		gameScene.setBounds(0,0,Definitions.WINDOW_WIDTH, Definitions.WINDOW_HEIGHT);
		this.add(button);
		
		this.add(labelTutorialFirstLine);
		this.add(labelTutorialSecondLine);
		this.add(labelTutoriaThirdLine);
		this.add(labelTutorialLastLine);

		button.addActionListener((e -> {
			this.removeAll();
			this.add(gameScene);
			repaint();
			

		}));
		
	}

}