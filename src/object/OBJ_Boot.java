package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_Boot extends SuperObject {
	GamePanel gp;
	
	public OBJ_Boot(GamePanel gp) {
		 this.gp = gp;
		 
		name="boot";
		try {
			image = ImageIO.read(getClass().getResource("/objects/boots.png"));
			uTool.scaledImage(image, gp.tileSize, gp.tileSize);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}

