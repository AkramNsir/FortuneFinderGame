package entity;

import Main.KeyHandler;
import Main.UtilityTool;
import Main.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Entity {
	
	KeyHandler keyH;
	
	public final int  screenX;
	public final int  screenY;
	public int hasKey = 0;
	public int hasIronKey = 0;
	public int hasWood = 0;
	public boolean hasAxe = false;
		
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 -(gp.tileSize/2);
		screenY = gp.screenHeigth/2 -(gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 10;
		solidArea.y = 20;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 28;
		solidArea.height = 22;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize * 26;
		worldY = gp.tileSize * 59;
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImage() {
		
		up1 = setUp("/player/ak_up1");
		up2 = setUp("/player/ak_up2");
		down1 = setUp("/player/ak_down1");
		down2 = setUp("/player/ak_down2");
		left1 = setUp("/player/ak_left1");
		left2 = setUp("/player/ak_left2");
		right1 = setUp("/player/ak_right1");
		right2 = setUp("/player/ak_right2");
		
		//WITH AXE
		up1axe = setUp("/player/ak_axe_up1");
		up2axe = setUp("/player/ak_axe_up2");
		down1axe = setUp("/player/ak_axe_down1");
		down2axe= setUp("/player/ak_axe_down2");
		left1axe = setUp("/player/ak_axe_left1");
		left2axe = setUp("/player/ak_axe_left2");
		right1axe = setUp("/player/ak_axe_right1");
		right2axe = setUp("/player/ak_axe_right2");
	}
	
	public void update() {
		
		if(keyH.upPressed == true || keyH.downPressed == true || 
				keyH.leftPressed == true || keyH.rightPressed == true ) {
		
		if(keyH.upPressed == true) {
			direction ="up";		
		}
		else if(keyH.downPressed == true) {
			direction = "down";
		}
		else if(keyH.leftPressed == true) {
			direction = "left";
		}
		else if(keyH.rightPressed == true) {
			direction = "right";
		}
		
		//CHECK TILE COLLISION
		collisionOn = false;
		int tileCords[] = gp.cChecker.checkTile(this);
		cutTree(tileCords);
		buildBridge(tileCords);

		//CHECK OBJECT COLLISION
		int objIndex = gp.cChecker.checkObject(this, true);
		pickUpObject(objIndex);
		
		//CHECK NPC & MONSTERS
		int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
		interactNPC(npcIndex);
		
		//IF COLLISION IS FALSE, PLAYER CAN MOVE
		if(collisionOn == false) {
			switch(direction) {
			case "up" :	worldY -= speed; break;
			case "down" : worldY += speed; break;
			case "left" : worldX -= speed; break;
			case "right" : worldX += speed; break;
			}
		}
		spriteCounter++;
		if(spriteCounter > 8) {
			if(spriteNum == 1) {
				spriteNum =2;
			}
			else if(spriteNum ==2) {
				spriteNum =1;
			}
			spriteCounter = 0;
		}
		}
	}
	
	public void pickUpObject(int i) {
		
		if(gp.keyH.u == 0) {
			
			if(i != 999 && gp.npc[0].dialogueIndex == 6) {
				
				String objectName = gp.obj[i].name;
				
				switch(objectName) {
				case "key" :
					gp.playSE(1);
					hasKey++;
					gp.obj[i] = null;
					gp.ui.showMessage("you got a key!");
					break;
				case "iron_key" :
					gp.playSE(1);
					hasIronKey++;
					gp.obj[i] = null;
					gp.ui.showMessage("you got the Iron key!");
					break;
				case "door" :
					if (hasKey > 0) {
						gp.playSE(3);
						gp.obj[i] = null;
						hasKey --;		
						gp.ui.showMessage("You opened the door!");
					}
					else {
						gp.ui.showMessage("You need a key!");
					}
					break;
				case "door_iron" :
					if (hasIronKey > 0) {
						gp.playSE(3);
						gp.obj[i] = null;
						hasIronKey --;		
						gp.ui.showMessage("You opened the iron door!");
					}
					else {
						gp.ui.showMessage("You need the Iron  key!");
					}
					break;
				case "boot" :
					gp.playSE(2);
					speed +=2;
					gp.obj[i] = null;
					gp.ui.showMessage("Speed up!");
					break;
				case "chest" :
					gp.ui.gameFinished = true;
					gp.stopMusic();
					gp.playSE(4);
					break;
				case "axe" :
					gp.playSE(5);
					hasAxe = true;
					gp.obj[i] = null;
					gp.ui.showMessage("you got an axe!");
					up1 = up1axe;
					up2 = up2axe;
					down1 = down1axe;
					down2 = down2axe;
					left1 = left1axe;
					left2 = left2axe;
					right1 = right1axe;
					right2 = right2axe;
					break;
				case "potion" :
					gp.playSE(7);
					gp.obj[i] = null;
					gp.ui.playTime -= 15;
					gp.ui.showMessage("you got the Magic Potion!");	
					break;	
				}
			}
		}
		else if (gp.keyH.u != 0) {
			
				if(i != 999) {
				
				String objectName = gp.obj[i].name;
				
				switch(objectName) {
				case "key" :
					gp.playSE(1);
					hasKey++;
					gp.obj[i] = null;
					gp.ui.showMessage("you got a key!");
					break;
				case "iron_key" :
					gp.playSE(1);
					hasIronKey++;
					gp.obj[i] = null;
					gp.ui.showMessage("you got the Iron key!");
					break;
				case "door" :
					if (hasKey > 0) {
						gp.playSE(3);
						gp.obj[i] = null;
						hasKey --;		
						gp.ui.showMessage("You opened the door!");
					}
					else {
						gp.ui.showMessage("You need a key!");
					}
					break;
				case "door_iron" :
					if (hasIronKey > 0) {
						gp.playSE(3);
						gp.obj[i] = null;
						hasIronKey --;		
						gp.ui.showMessage("You opened the iron door!");
					}
					else {
						gp.ui.showMessage("You need the Iron  key!");
					}
					break;
				case "boot" :
					gp.playSE(2);
					speed +=2;
					gp.obj[i] = null;
					gp.ui.showMessage("Speed up!");
					break;
				case "chest" :
					gp.ui.gameFinished = true;
					gp.stopMusic();
					gp.playSE(4);
					break;
				case "axe" :
					gp.playSE(5);
					hasAxe = true;
					gp.obj[i] = null;
					gp.ui.showMessage("you got an axe!");
					up1 = up1axe;
					up2 = up2axe;
					down1 = down1axe;
					down2 = down2axe;
					left1 = left1axe;
					left2 = left2axe;
					right1 = right1axe;
					right2 = right2axe;
					break;
				case "potion" :
					gp.playSE(7);
					gp.obj[i] = null;
					gp.ui.playTime -= 15;
					gp.ui.showMessage("you got the Magic Potion!");					
					break;
				}
			}
		}
		
	}
	
	public void cutTree(int [] tab) {
		
		int index = tab[0];
		int colTile = tab[1];
		int rowTile = tab[2];
		
		if(hasAxe) {
			
			if(gp.keyH.spacePressed == true && index == 41 && gp.npc[0].dialogueIndex == 6 && gp.keyH.u == 0) {
		
					gp.tileM.mapTileNum[colTile][rowTile] = 42;
					hasWood ++;
				}else if (gp.keyH.spacePressed == true && index == 41 && gp.keyH.u != 0) {
					gp.tileM.mapTileNum[colTile][rowTile] = 42;
					hasWood ++;
				}
			gp.keyH.spacePressed = false;
		}
	}
	
	public void buildBridge(int [] tab) {
		
		int index = tab[0];
		int colTile = tab[1];
		int rowTile = tab[2];
		
		if(hasWood > 0 && gp.keyH.u == 0) {
			
			if(( gp.keyH.bPressed == true && (index == 12 || index == 13) && gp.npc[0].dialogueIndex == 6)) {	
					gp.tileM.mapTileNum[colTile][rowTile] = 43;
					hasWood --;
				}
			else if( gp.keyH.bPressed == true && index == 17 && gp.npc[0].dialogueIndex == 6) {
				
				gp.tileM.mapTileNum[colTile][rowTile] = 44;
				hasWood --;
			}
			else if( gp.keyH.bPressed == true && index == 18 && gp.npc[0].dialogueIndex == 6) {
				
				gp.tileM.mapTileNum[colTile][rowTile] = 45;
				hasWood --;
			}
			else if( gp.keyH.bPressed == true && index == 15 && gp.npc[0].dialogueIndex == 6) {
				
				gp.tileM.mapTileNum[colTile][rowTile] = 46;
				hasWood --;
			}
			else if( gp.keyH.bPressed == true && index == 20 && gp.npc[0].dialogueIndex == 6) {
				
				gp.tileM.mapTileNum[colTile][rowTile] = 47;
				hasWood --;
			}
			else if( gp.keyH.bPressed == true && index == 14 && gp.npc[0].dialogueIndex == 6) {
				
				gp.tileM.mapTileNum[colTile][rowTile] = 48;
				hasWood --;
			}
			else if( gp.keyH.bPressed == true && index == 16 && gp.npc[0].dialogueIndex == 6) {
				
				gp.tileM.mapTileNum[colTile][rowTile] = 49;
				hasWood --;
			}
			else if( gp.keyH.bPressed == true && index == 19 && gp.npc[0].dialogueIndex == 6) {
				
				gp.tileM.mapTileNum[colTile][rowTile] = 50;
				hasWood --;
			}
			else if( gp.keyH.bPressed == true && index == 21 && gp.npc[0].dialogueIndex == 6) {
				
				gp.tileM.mapTileNum[colTile][rowTile] = 51;
				hasWood --;
			}
			gp.keyH.bPressed = false;			
		}
		else if(hasWood > 0 && gp.keyH.u != 0) {
			
			if(( gp.keyH.bPressed == true && (index == 12 || index == 13))) {	
					gp.tileM.mapTileNum[colTile][rowTile] = 43;
					hasWood --;
				}
			else if( gp.keyH.bPressed == true && index == 17) {
				
				gp.tileM.mapTileNum[colTile][rowTile] = 44;
				hasWood --;
			}
			else if( gp.keyH.bPressed == true && index == 18) {
				
				gp.tileM.mapTileNum[colTile][rowTile] = 45;
				hasWood --;
			}
			else if( gp.keyH.bPressed == true && index == 15) {
				
				gp.tileM.mapTileNum[colTile][rowTile] = 46;
				hasWood --;
			}
			else if( gp.keyH.bPressed == true && index == 20) {
				
				gp.tileM.mapTileNum[colTile][rowTile] = 47;
				hasWood --;
			}
			else if( gp.keyH.bPressed == true && index == 14) {
				
				gp.tileM.mapTileNum[colTile][rowTile] = 48;
				hasWood --;
			}
			else if( gp.keyH.bPressed == true && index == 16) {
				
				gp.tileM.mapTileNum[colTile][rowTile] = 49;
				hasWood --;
			}
			else if( gp.keyH.bPressed == true && index == 19) {
				
				gp.tileM.mapTileNum[colTile][rowTile] = 50;
				hasWood --;
			}
			else if( gp.keyH.bPressed == true && index == 21) {
				
				gp.tileM.mapTileNum[colTile][rowTile] = 51;
				hasWood --;
			}
			gp.keyH.bPressed = false;			
		}
		gp.keyH.bPressed = false;
	}
	
	public void interactNPC(int i) {
		if(i != 999) {
			if(gp.keyH.enterPressed == true) {
			
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
		}
		gp.keyH.enterPressed = false;
	}
	public void draw(Graphics2D g2) {
		
	//	g2.setColor(Color.white);
	//	g2.fillRect(x, y, gp.tileSize, gp.tileSize );
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up" :
			if(spriteNum == 1) {
			image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down" :
			if(spriteNum == 1) {
				image = down1;
				}
				if(spriteNum == 2) {
					image = down2;
				}
				break;
		case "left" : 
			if(spriteNum == 1) {
				image = left1;
				}
				if(spriteNum == 2) {
					image = left2;
				}
				break;
		case "right" :
			if(spriteNum == 1) {
				image = right1;
				}
				if(spriteNum == 2) {
					image = right2;
				}
				break;
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
	
}

