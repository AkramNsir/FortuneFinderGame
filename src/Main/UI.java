package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

import object.OBJ_Axe;
import object.OBJ_Boot;
import object.OBJ_Door;
import object.OBJ_DoorIron;
import object.OBJ_IronKey;
import object.OBJ_Key;
import object.OBJ_Potion;
import object.OBJ_Wood;

public class UI {
	
	GamePanel gp;
	public Graphics2D g2;
	public Font arial_40;
	Font arial_80B;
	Font arial_80P;
	BufferedImage keyImage, woodImage, ironKeyImage;
	File file = new File("score.txt");
	
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	
	public double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	public String currentDialogue = "";
	public int commandNum = 0;
	public int titleScreenState = 0; //0: for the first page  1: for the second page ect ...
	public int save = 0;
	
	public UI(GamePanel gp) {
		
		this.gp = gp;
		
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
		arial_80P = new Font("Arial", Font.PLAIN, 80);
		OBJ_Key key = new OBJ_Key(gp);
		keyImage = key.image;
		OBJ_Wood wood = new OBJ_Wood(gp);
		woodImage = wood.image;
		OBJ_IronKey ironKey = new OBJ_IronKey(gp);
		ironKeyImage = ironKey.image;
	}
	
	public void showMessage(String text) {
		
		message = text;
		messageOn = true;
	}

	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		
		g2.setFont(arial_80P);
		g2.setColor(Color.white);
		
		//TITLESTATE
		if(gp.gameState == gp.titleState) {
			
			drawTitleScreen();
		}
		//PLAYSTATE
		if(gp.gameState == gp.playState) {
			
			drawPlayScreen();
		}
		
		//PAUSESTATE
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		
		//DIALOGUESTATE
		if(gp.gameState == gp.dialogueState) {
			drawDialogueScreen();
		}
	}
	
	public void drawPlayScreen() {

		if(gp.keyH.touchs == 0 ) {
		
			g2.setFont(g2.getFont().deriveFont(Font.ITALIC,30F));
			g2.drawString("Ask the old man to know what to do!", gp.tileSize * 3, gp.tileSize*4);
			g2.drawString("Press U if you want to skip ", gp.tileSize * 4, gp.tileSize*5);
		}
				
		if(gameFinished == true) {

			g2.setFont(arial_40);
			g2.setColor(Color.white); 
			String text;
			int textLength;
			int x;
			int y;
			
			text = "You found the treasure!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();	
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeigth/2 - (gp.tileSize * 3);
			g2.drawString(text,  x,  y);
			
			text = "Your time is : " + dFormat.format(playTime);
			textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();			
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeigth/2 + (gp.tileSize * 2);
			g2.drawString(text,  x,  y);
			
			g2.setFont(arial_80B);
			g2.setColor(Color.green);
			
			text = "Congratulations!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();			
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeigth/2 ;
			g2.drawString(text,  x,  y);
			
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
			g2.setColor(Color.white);
			text = "Press ENTER to Replay!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();			
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeigth/2 + (gp.tileSize * 4);
			g2.drawString(text,  x,  y);
			int i = 0;
				try {
					if(i == 0) {
						 FileWriter writer = new FileWriter(file,true);
						 BufferedWriter bw = new BufferedWriter(writer);

				            bw.newLine(); // Ajout d'une nouvelle ligne
				            bw.write("Attempt :		"+dFormat.format(playTime));
				            bw.close();
				            writer.close();
				            i++;
					}     
		            gp.gameThread =null;
		            System.out.println("Les données ont été écrites dans le fichier.");
		        } catch (IOException e) {
		            System.out.println("Une erreur s'est produite : " + e.getMessage());
		        }			
		}
		else {
			
			//HAS KEYS
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2 - 15, gp.tileSize, gp.tileSize, null);
			g2.drawString("X "+gp.player.hasKey, 74, 50);
			
			//HAS IRONKEY
			g2.drawImage(ironKeyImage, gp.tileSize/2, gp.tileSize + 15, gp.tileSize, gp.tileSize, null);
			g2.drawString("X "+gp.player.hasIronKey, 74, 105);
			
			//HAS WOOD
			g2.drawImage(woodImage, gp.tileSize/2, gp.tileSize + 70 , gp.tileSize, gp.tileSize, null);
			g2.drawString("X "+gp.player.hasWood, 74, 160);
			
			//TIMER
				if(gp.keyH.u == 0) {
					
					if(gp.npc[0].dialogueIndex == 6) {
						playTime +=(double)1/60;
						g2.drawString("Time: "+ dFormat.format(playTime), gp.tileSize * 11, 65);
					}
				}
				else {
					
					playTime +=(double)1/60;
					g2.drawString("Time: "+ dFormat.format(playTime), gp.tileSize * 11, 65);
				}
					
			//MESSAGE
			if(messageOn == true) {
				
				g2.setFont(g2.getFont().deriveFont(30F));
				g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
				
				messageCounter ++;
				
				if(messageCounter > 120) {
					messageCounter = 0;
					messageOn = false;
				}
			}
			}	
	}
	
	public void drawTitleScreen() {
		
		if(titleScreenState == 0) {
			
			g2.setColor(new Color(242,145,145));
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeigth);
			 
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));
			String text = "Fortune Finder Game";
			int x = getXforCenteredText(text);
			int y = gp.tileSize * 3;
			
			//Shadow
			g2.setColor(Color.black);
			g2.drawString(text, x+4, y+4);
			
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			//sprite character image
			x = gp.screenWidth/2 - (gp.tileSize*2)/2 - 10;
			y += gp.tileSize;
			g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
			
			//MENU
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
			
			text = "New Game";
			x = getXforCenteredText(text);
			y += gp.tileSize * 4;
			g2.setColor(Color.black);
			g2.drawString(text, x+4, y+4);
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				text = ">";
				x -= gp.tileSize;
				g2.setColor(Color.black);
				g2.drawString(text, x+4, y+4);
				g2.setColor(Color.white);
				g2.drawString(text, x, y);
			}
			
			text = "Load Game";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.setColor(Color.black);
			g2.drawString(text, x+4, y+4);
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				text = ">";
				x -= gp.tileSize;
				g2.setColor(Color.black);
				g2.drawString(text, x+4, y+4);
				g2.setColor(Color.white);
				g2.drawString(text, x, y);
			}
			
			text = "Help";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.setColor(Color.black);
			g2.drawString(text, x+4, y+4);
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				text = ">";
				x -= gp.tileSize;
				g2.setColor(Color.black);
				g2.drawString(text, x+4, y+4);
				g2.setColor(Color.white);
				g2.drawString(text, x, y);
			}
			
			text = "Quit";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.setColor(Color.black);
			g2.drawString(text, x+4, y+4);
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			if(commandNum == 3) {
				text = ">";
				x -= gp.tileSize;
				g2.setColor(Color.black);
				g2.drawString(text, x+4, y+4);
				g2.setColor(Color.white);
				g2.drawString(text, x, y);
			}
		}
		else if (titleScreenState == 1) {
			
			g2.setColor(new Color(242,145,145));
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeigth);
			 
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));
			String text = "Your Results";
			int x = getXforCenteredText(text);
			int y = gp.tileSize * 2;
			
			//Shadow
			g2.setColor(Color.black);
			g2.drawString(text, x+4, y+4);
			
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			
			//PRINT RESULTS FROM THE FILE
		        try {
		        	int ne = 1;
		            FileReader reader = new FileReader(file);
		            BufferedReader bufferedReader = new BufferedReader(reader);
		            
		            y += gp.tileSize;
		            g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30F));
		            String line = bufferedReader.readLine();
		            while (line != null) {
		            	line = ne +"° " + line+" Sec";
		            	x = getXforCenteredText(text)-gp.tileSize;
		    			y += gp.tileSize;
		    			g2.setColor(Color.white);
		    			g2.drawString(line, x, y);
		    			
		    			ne++;
		                line = bufferedReader.readLine();
		            }

		            bufferedReader.close();
		        } catch (IOException e) {
		            System.out.println("Une erreur s'est produite : " + e.getMessage());
		        }
		        
			//REST OF THE MENU
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
			text = "Back";
			x = getXforCenteredText(text);
			y = gp.tileSize * 11;
			g2.setColor(Color.black);
			g2.drawString(text, x+4, y+4);
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
				text = ">";
				x -= gp.tileSize;
				g2.setColor(Color.black);
				g2.drawString(text, x+4, y+4);
				g2.setColor(Color.white);
				g2.drawString(text, x, y);
		}
		else if (titleScreenState ==2) {
			
			g2.setColor(new Color(242,145,145));
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeigth);
			 
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));
			String text = "Pause Mode";
			int x = getXforCenteredText(text);
			int y = gp.tileSize * 2;
			
			//Shadow
			g2.setColor(Color.black);
			g2.drawString(text, x+4, y+4);
			
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			//sprite character image
			x = gp.screenWidth/2 - (gp.tileSize*2)/2 - 10;
			y += gp.tileSize;
			g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
			
			//MENU
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
			
			text = "Resume Game";
			x = getXforCenteredText(text);
			y += gp.tileSize * 4;
			g2.setColor(Color.black);
			g2.drawString(text, x+4, y+4);
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				text = ">";
				x -= gp.tileSize;
				g2.setColor(Color.black);
				g2.drawString(text, x+4, y+4);
				g2.setColor(Color.white);
				g2.drawString(text, x, y);
			}
			
			text = "Load Game";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.setColor(Color.black);
			g2.drawString(text, x+4, y+4);
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				text = ">";
				x -= gp.tileSize;
				g2.setColor(Color.black);
				g2.drawString(text, x+4, y+4);
				g2.setColor(Color.white);
				g2.drawString(text, x, y);
			}
			
			text = "Help";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.setColor(Color.black);
			g2.drawString(text, x+4, y+4);
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				text = ">";
				x -= gp.tileSize;
				g2.setColor(Color.black);
				g2.drawString(text, x+4, y+4);
				g2.setColor(Color.white);
				g2.drawString(text, x, y);
			}
			
			text = "Quit";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.setColor(Color.black);
			g2.drawString(text, x+4, y+4);
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			if(commandNum == 3) {
				text = ">";
				x -= gp.tileSize;
				g2.setColor(Color.black);
				g2.drawString(text, x+4, y+4);
				g2.setColor(Color.white);
				g2.drawString(text, x, y);
			}
		}
		
		else if (titleScreenState == 3) {
			
			g2.setColor(new Color(242,145,145));
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeigth);
			 
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));
			String text = "Help";
			int x = getXforCenteredText(text);
			int y = gp.tileSize * 2;
			
			//Shadow
			g2.setColor(Color.black);
			g2.drawString(text, x+4, y+4);
			
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			
			//PRINT INSTRUCTIONS
		        
			 g2.setFont(g2.getFont().deriveFont(Font.PLAIN,25F));
	         text = "Press 'Z' or '↑' to go UP"; 
	            	x = gp.tileSize;
	    			y += gp.tileSize;
	    			g2.setColor(Color.white);
	    			g2.drawString(text, x, y);
	    			
	    			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,25F));
	   	       text = "Press 'W' or '↓' to go DOWN"; 
	   	            	x = gp.tileSize;
	   	    			y += gp.tileSize;
	   	    			g2.setColor(Color.white);
	   	    			g2.drawString(text, x, y);
	   	    			
	   	    			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,25F));
	   		   text = "Press 'Q' or '←' to go LEFT"; 
	   		            	x = gp.tileSize;
	   		    			y += gp.tileSize;
	   		    			g2.setColor(Color.white);
	   		    			g2.drawString(text, x, y);
	   		    			
	   		    			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,25F));
	   		   text = "Press 'D' or '→' to go RIGHT"; 
	   			            	x = gp.tileSize;
	   			    			y += gp.tileSize;
	   			    			g2.setColor(Color.white);
	   			    			g2.drawString(text, x, y);
	   		   text = "Press 'SPACE' to cut"; 
	   			            	x = gp.tileSize;
	   			    			y += gp.tileSize;
	   			    			g2.setColor(Color.white);
	   			    			g2.drawString(text, x, y);
	   			    			
	   		   text = "Press 'B' to build"; 
	   			            	x = gp.tileSize;
	   			    			y += gp.tileSize;
	   			    			g2.setColor(Color.white);
	   			    			g2.drawString(text, x, y);
	   			    			
	   		   text = "Press 'ENTER' to Talk"; 
	   			            	x = gp.tileSize;
	   			    			y += gp.tileSize;
	   			    			g2.setColor(Color.white);
	   			    			g2.drawString(text, x, y);
	   			    			
	   		  text = "Press 'P' to PAUSE"; 
	   			            	x = gp.tileSize;
	   			    			y += gp.tileSize;
	   			    			g2.setColor(Color.white);
	   			    			g2.drawString(text, x, y);
	   			    			
	   		//PRINT OBJECTS EFFECTS 
	   			    // GOLDEN KEY AND WOOD DOOR
	   		x = gp.screenWidth/2 + gp.tileSize;;
	   		y = gp.tileSize * 3;
	   		g2.drawImage(new OBJ_Key(gp).image, x, y, gp.tileSize, gp.tileSize, null);
	   		
	   		x += gp.tileSize * 1.25;
	   		y += 40;
	   		g2.drawString(" To open ", x, y);
	   		
	   		x += gp.tileSize * 2.5;
	   		y -= 40;
	   		g2.drawImage(new OBJ_Door(gp).image, x, y, gp.tileSize, gp.tileSize, null);
	   		
	   				// GOLDEN KEY AND WOOD DOOR
	   		x = gp.screenWidth/2 + gp.tileSize;;
	   		y = gp.tileSize * 5 - gp.tileSize/2;
	   		g2.drawImage(new OBJ_IronKey(gp).image, x, y, gp.tileSize, gp.tileSize, null);
	   		
	   		x += gp.tileSize * 1.25;
	   		y += 40;
	   		g2.drawString(" To open ", x, y);
	   		
	   		x += gp.tileSize * 2.5;
	   		y -= 40;
	   		g2.drawImage(new OBJ_DoorIron(gp).image, x, y, gp.tileSize, gp.tileSize, null);
	   		
	   				// Axe
	   		x = gp.screenWidth/2 + gp.tileSize;;
	   		y = gp.tileSize * 6;
	   		g2.drawImage(new OBJ_Axe(gp).image, x, y, gp.tileSize, gp.tileSize, null);
	   		
	   		x += gp.tileSize * 1.25;
	   		y += 40;
	   		g2.drawString(" To Cut Trees ", x, y);
	   		
	   				// BOOTS
	   		x = gp.screenWidth/2 + gp.tileSize;;
	   		y = gp.tileSize * 7 + 15;
	   		g2.drawImage(new OBJ_Boot(gp).image, x, y, gp.tileSize, gp.tileSize, null);
	   		
	   		x += gp.tileSize * 1.25;
	   		y += 40;
	   		g2.drawString(" To Move Faster ", x, y);
	   		
	   				//MAGIC POTION
	   		x = gp.screenWidth/2 + gp.tileSize;;
	   		y = gp.tileSize * 8 + 35;
	   		g2.drawImage(new OBJ_Potion(gp).image, x, y, gp.tileSize, gp.tileSize, null);
	   		
	   		x += gp.tileSize * 1.25;
	   		y += 40;
	   		g2.drawString(" To Win 15 Seconds ", x, y);
		        
			//REST OF THE MENU
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
			text = "Back";
			x = getXforCenteredText(text);
			y = gp.tileSize * 11;
			g2.setColor(Color.black);
			g2.drawString(text, x+4, y+4);
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
				text = ">";
				x -= gp.tileSize;
				g2.setColor(Color.black);
				g2.drawString(text, x+4, y+4);
				g2.setColor(Color.white);
				g2.drawString(text, x, y);
		}
}	
		
	
	
	public void drawDialogueScreen() {
		
		int x = gp.tileSize * 2;
		int y = gp.tileSize/2;
		int width = gp.screenWidth - (gp.tileSize * 4);
		int height = gp.tileSize * 4;
		
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
		x += gp.tileSize;
		y += gp.tileSize;
		
		for(String line : currentDialogue.split("\n")){
		
			g2.drawString(line, x, y);
			y += 40;
		}
	}

	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color c = new Color(0,0,0, 205); // the last parametere is for transparancy
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		Color c1 = new Color(255, 255, 255);
		g2.setColor(c1);
		g2.setStroke(new BasicStroke(5)); // a border with a 5 pixels weight
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}
	
	public void drawPauseScreen() {
		
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.screenHeigth/2;
		
		g2.drawString(text, x, y);	
	}
	
	public int getXforCenteredText(String text) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
}
