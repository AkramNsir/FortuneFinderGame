package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_IronKey extends SuperObject{
	
	GamePanel gp;
	
	public OBJ_IronKey(GamePanel gp) {
		this.gp = gp;
		
		name = "iron_key";
		try {
			image = ImageIO.read(getClass().getResource("/objects/ironkey.png"));
			uTool.scaledImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	

}

