package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_DoorIron extends SuperObject{
	GamePanel gp;
	
	public OBJ_DoorIron(GamePanel gp) {
		this.gp = gp;
		
		name = "door_iron";
		try {
			image = ImageIO.read(getClass().getResource("/objects/door_iron.png"));
			uTool.scaledImage(image, gp.tileSize, gp.tileSize);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		collision = true;
	}

}

