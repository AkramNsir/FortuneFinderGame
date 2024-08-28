package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_Potion extends SuperObject{
	
	GamePanel gp;
	
	public OBJ_Potion(GamePanel gp) {
		this.gp = gp;
		
		name = "potion";
		try {
			image = ImageIO.read(getClass().getResource("/objects/potion_red.png"));
			uTool.scaledImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	

}

