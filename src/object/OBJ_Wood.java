package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_Wood extends SuperObject{
	
	GamePanel gp;
	
	public OBJ_Wood(GamePanel gp) {
		this.gp = gp;
		
		name = "wood";
		try {
			image = ImageIO.read(getClass().getResource("/objects/wood.png"));
			uTool.scaledImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	

}

