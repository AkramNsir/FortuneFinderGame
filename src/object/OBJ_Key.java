package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_Key extends SuperObject{
	
	GamePanel gp;
	
	public OBJ_Key(GamePanel gp) {
		this.gp = gp;
		
		name = "key";
		try {
			image = ImageIO.read(getClass().getResource("/objects/key.png"));
			uTool.scaledImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	

}

