package Main;

import java.awt.*;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	
	// SCREEN SETTINGS
	final int originalTileSize = 16; //16x16 tile
	final int scale = 3; 
	
	public final int tileSize = originalTileSize * scale; // 48x48 tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	public final int screenHeigth = tileSize * maxScreenRow; // 576 pixels
	
	// WORLD SETTINGS 
	public final int maxWorldCol = 102;
	public final int maxWorldRow = 100;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeigth = tileSize * maxWorldRow;
	
	//FPS 
	int  FPS = 60;
	
	//SYSTEM
	public TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	Sound music = new Sound();
	Sound se = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	Thread gameThread;
	
	//ENTITY AND OBJECT
	public Player player = new Player(this,keyH);
	public SuperObject obj[] = new SuperObject[25]; 
	public Entity npc[] = new Entity[10];
	
	//GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	
	//CONSTRUCTER 
	 
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeigth));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setUpGame() {
		
		aSetter.setObject();
		aSetter.setNPC();
		//playMusic(0);
		gameState = titleState; 
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		while(gameThread != null) {
			
			double drawInterval = 1000000000/FPS; // 0.016666 seconds -->draw
			double nextDrawTime = System.nanoTime() + drawInterval;
			
			//1 UPDATE: update information such as character positions
			update(); 
			
			//2 DRAW: draw the screen with the updated informations
			repaint();

			try {
				double remainingTime = nextDrawTime - System.nanoTime(); 
				remainingTime = remainingTime/1000000; // convertion to millis
				
				if(remainingTime < 0 ) {
					remainingTime = 0;
				}
				
				Thread.sleep((long)remainingTime);
				
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		
		if (gameState == playState) {
			//PLAYER
			player.update();
			
			//NPC
			for(int i=0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].update();
				}
			}
		}
		else if(gameState == pauseState) {
			//nothing
		}
		
	}
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		//DEBUG 
		long drawStart = 0;
		if(keyH.checkDrawTime == true) {
			drawStart = System.nanoTime();
		}
		
		
		 {

			 // TILE
			tileM.draw(g2);
			
			//OBJECT
			for(int i=0; i< obj.length; i++) {
				if(obj[i] != null) {
					obj[i].draw(g2,  this);
				}
			}
			
			//NPC
			for(int i=0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].draw(g2);
				}
			}
			
			//PLAYER
			player.draw(g2);
			
			//UI
			ui.draw(g2);
		}
		
		
		//DEBUG
		if(keyH.checkDrawTime == true) {
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			g2.setColor(Color.white);
			g2.drawString("Draw Time: " + passed, 10, 400);
			System.out.println("Draw Time: " + passed);
		}		
		g2.dispose();
	}
	
	public void playMusic(int i) {
		
		music.setFile(i);
		music.play();
		music.loop();
		
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	public void playSE(int i) {
		
		se.setFile(i);
		se.play();
	}
	
}
