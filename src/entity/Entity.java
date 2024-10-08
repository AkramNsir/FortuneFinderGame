package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.UtilityTool;

public class Entity {
	public GamePanel gp;
	public int worldX, worldY;
	public int speed;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2,up1axe, up2axe, down1axe, down2axe,
	left1axe, left2axe, right1axe, right2axe;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	public int actionCounter = 0;
	public String[] dialogues = new String[20];
	public int dialogueIndex = 0;
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setAction() {}
	public void speak() {
		
		if(dialogues[dialogueIndex] == null) {
			gp.ui.currentDialogue = "Just do what I told you!";
		}
		else {
			gp.ui.currentDialogue = dialogues[dialogueIndex];
			dialogueIndex ++;
		}
		
		switch(gp.player.direction) {
		case "up" :
			direction = "down";
			break;
		case "down" :
			direction = "up";
			break;
		case "left" :
			direction = "right";
			break;
		case "right" :
			direction = "left";
			break;
		}
	}
	public void update() {
		
		setAction();
		
		//CHECK TILE COLLISION
		collisionOn = false;
		gp.cChecker.checkTile(this);
		
		//CHECK OBJECT COLLISION
		gp.cChecker.checkObject(this,  false);
		
		//CHECK PLAYER COLLISION
		gp.cChecker.checkPlayer(this);
		
		//IF COLLISION IS FALSE, ENTITY CAN MOVE
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
	
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
		   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
	       worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
		   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY
				) {
						
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
	
	public BufferedImage setUp(String imagePath) {
			
			UtilityTool uTool = new UtilityTool();
			BufferedImage image = null;
			
			try {  
				
				image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
				image = uTool.scaledImage(image,  gp.tileSize,  gp.tileSize);
			
			}catch(IOException e) {
				e.printStackTrace();
			}
			
			return image;
		}
}

