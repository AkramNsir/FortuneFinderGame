package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_Axe extends SuperObject {
	GamePanel gp;
	
	public OBJ_Axe(GamePanel gp) {
		 this.gp = gp;
		 
		name="axe";
		try {
			image = ImageIO.read(getClass().getResource("/objects/axe.png"));
			uTool.scaledImage(image, gp.tileSize, gp.tileSize);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}

