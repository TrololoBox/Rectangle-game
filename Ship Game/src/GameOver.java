import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

//DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE DONE 
public class GameOver extends JPanel{	
	private int score;
	public GameOver() {
		
	}
	
	
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}



	public void paint (Graphics g) {
		super.paint(g);
		this.setBackground(Color.gray);
		JLabel label = new JLabel("GAME OVER");
		JLabel labelScore = new JLabel("Your score is: " + score);

		label.setBounds((Definitions.WINDOW_WIDTH / 2) - 100, Definitions.WINDOW_HEIGHT / 2 - 120, 500,200);
		label.setFont(new Font("Serif",Font.PLAIN,40));
		labelScore.setBounds((Definitions.WINDOW_WIDTH / 2) - 100 , (Definitions.WINDOW_HEIGHT / 2), 200,200);
		labelScore.setFont(new Font("Serif",Font.PLAIN,25));

		
		this.add(label);
		this.add(labelScore);


		
	}

}