package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Main{

	public int x = 0;
	JFrame window ;
	
	public Main() {
		window = new JFrame();

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Fortune Finder Game");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.setUpGame();
		gamePanel.startGameThread();	
	}
	public static void main(String[] args) {
		
		new Main();
	}
}