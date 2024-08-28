package Main;

import java.util.Random;

import entity.NPC_OldMan;
import object.OBJ_Axe;
import object.OBJ_Boot;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_DoorIron;
import object.OBJ_IronKey;
import object.OBJ_Key;
import object.OBJ_Potion;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
		 Random random = new Random();
		 int i = random.nextInt(100)+1; //pick a number from 1 to 100
		
		 
		 if(i>0 && i<25) {
			 	
				//ISLAND 1
				gp.obj[1] = new OBJ_Key(gp);
				gp.obj[1].worldX = 37 * gp.tileSize;
				gp.obj[1].worldY = 78 * gp.tileSize;
				
				gp.obj[2] = new OBJ_Axe(gp);
				gp.obj[2].worldX = 49 * gp.tileSize;
				gp.obj[2].worldY = 51 * gp.tileSize;
				
				//ISLAND 2
			 	gp.obj[20] = new OBJ_Key(gp);
				gp.obj[20].worldX = 46 * gp.tileSize;
				gp.obj[20].worldY = 11 * gp.tileSize;
				
				gp.obj[0] = new OBJ_Potion(gp);
				gp.obj[0].worldX = 10 * gp.tileSize;
				gp.obj[0].worldY = 14 * gp.tileSize;
				
				//ISLAND 3
				gp.obj[3] = new OBJ_Key(gp);
				gp.obj[3].worldX = 85 * gp.tileSize;
				gp.obj[3].worldY = 10 * gp.tileSize;
				
				gp.obj[4] = new OBJ_Key(gp);
				gp.obj[4].worldX = 91 * gp.tileSize;
				gp.obj[4].worldY = 28 * gp.tileSize;
				
				gp.obj[16] = new OBJ_Potion(gp);
				gp.obj[16].worldX = 83 * gp.tileSize;
				gp.obj[16].worldY = 16 * gp.tileSize;
		 }
		 else if(i>=25 &&i<50) {
			 
				//ISLAND 1
				gp.obj[1] = new OBJ_Axe(gp);
				gp.obj[1].worldX = 37 * gp.tileSize;
				gp.obj[1].worldY = 78 * gp.tileSize;
				
				gp.obj[2] = new OBJ_Key(gp);
				gp.obj[2].worldX = 49 * gp.tileSize;
				gp.obj[2].worldY = 51 * gp.tileSize;
				
				gp.obj[16] = new OBJ_Potion(gp);
				gp.obj[16].worldX = 12 * gp.tileSize;
				gp.obj[16].worldY = 52 * gp.tileSize;
				
				//ISLAND 2
			 	gp.obj[0] = new OBJ_Key(gp);
				gp.obj[0].worldX = 10 * gp.tileSize;
				gp.obj[0].worldY = 18 * gp.tileSize;
				
				//ISLAND 3
				gp.obj[3] = new OBJ_Key(gp);
				gp.obj[3].worldX = 74 * gp.tileSize;
				gp.obj[3].worldY = 11 * gp.tileSize;
				
				gp.obj[4] = new OBJ_Key(gp);
				gp.obj[4].worldX = 92 * gp.tileSize;
				gp.obj[4].worldY = 37 * gp.tileSize;
				
				gp.obj[5] = new OBJ_Potion(gp);
				gp.obj[5].worldX = 83 * gp.tileSize;
				gp.obj[5].worldY = 16 * gp.tileSize;
		 }
		 else if(i>=50 && i<75) {
			 
			//ISLAND 1
				gp.obj[1] = new OBJ_Axe(gp);
				gp.obj[1].worldX = 21 * gp.tileSize;
				gp.obj[1].worldY = 75 * gp.tileSize;
				
				gp.obj[2] = new OBJ_Key(gp);
				gp.obj[2].worldX = 37 * gp.tileSize;
				gp.obj[2].worldY = 78 * gp.tileSize;
				
				
				//ISLAND 2
			 	gp.obj[0] = new OBJ_Key(gp);
				gp.obj[0].worldX = 46 * gp.tileSize;
				gp.obj[0].worldY = 11 * gp.tileSize;
				
				gp.obj[16] = new OBJ_Potion(gp);
				gp.obj[16].worldX = 10 * gp.tileSize;
				gp.obj[16].worldY = 22 * gp.tileSize;
				
				//ISLAND 3
				gp.obj[3] = new OBJ_Key(gp);
				gp.obj[3].worldX = 90 * gp.tileSize;
				gp.obj[3].worldY = 22 * gp.tileSize;
				
				gp.obj[4] = new OBJ_Key(gp);
				gp.obj[4].worldX = 56 * gp.tileSize;
				gp.obj[4].worldY = 19 * gp.tileSize;
				
				gp.obj[5] = new OBJ_Potion(gp);
				gp.obj[5].worldX = 83 * gp.tileSize;
				gp.obj[5].worldY = 16 * gp.tileSize;
				
		 }
		 else if(i>=75 && i<=100) {
			 
			 	//ISLAND 1
				gp.obj[1] = new OBJ_Axe(gp);
				gp.obj[1].worldX = 18 * gp.tileSize;
				gp.obj[1].worldY = 44 * gp.tileSize;
				
				gp.obj[2] = new OBJ_Key(gp);
				gp.obj[2].worldX = 37 * gp.tileSize;
				gp.obj[2].worldY = 78 * gp.tileSize;
				
				
				//ISLAND 2
				gp.obj[18] = new OBJ_Door(gp);
				gp.obj[18].worldX = 68 * gp.tileSize;
				gp.obj[18].worldY = 58 * gp.tileSize;
				
			 	gp.obj[0] = new OBJ_Key(gp);
				gp.obj[0].worldX = 46 * gp.tileSize;
				gp.obj[0].worldY = 11 * gp.tileSize;
				
				//ISLAND 3
				gp.obj[3] = new OBJ_Key(gp);
				gp.obj[3].worldX = 85 * gp.tileSize;
				gp.obj[3].worldY = 10 * gp.tileSize;
				
				gp.obj[4] = new OBJ_Key(gp);
				gp.obj[4].worldX = 92 * gp.tileSize;
				gp.obj[4].worldY = 37 * gp.tileSize;
				
				gp.obj[19] = new OBJ_Key(gp);
				gp.obj[19].worldX = 56 * gp.tileSize;
				gp.obj[19].worldY = 19 * gp.tileSize;
				
				gp.obj[5] = new OBJ_Potion(gp);
				gp.obj[5].worldX = 83 * gp.tileSize;
				gp.obj[5].worldY = 16 * gp.tileSize;
				
				gp.obj[16] = new OBJ_Potion(gp);
				gp.obj[16].worldX = 71 * gp.tileSize;
				gp.obj[16].worldY = 45 * gp.tileSize;
		 }
		 
		 System.out.println(i);
		 
		 
		 //ISLAND 1
		gp.obj[11] = new OBJ_Door(gp);
		gp.obj[11].worldX = 13 * gp.tileSize;
		gp.obj[11].worldY = 68 * gp.tileSize;
			
		gp.obj[12] = new OBJ_Boot(gp);
		gp.obj[12].worldX = 13 * gp.tileSize;
		gp.obj[12].worldY = 74 * gp.tileSize;
		
		//ISLAND 3
		gp.obj[7] = new OBJ_Door(gp);
		gp.obj[7].worldX = 69 * gp.tileSize;
		gp.obj[7].worldY = 41 * gp.tileSize;
		
		gp.obj[8] = new OBJ_Door(gp);
		gp.obj[8].worldX = 69 * gp.tileSize;
		gp.obj[8].worldY = 60 * gp.tileSize;
		
		gp.obj[9] = new OBJ_DoorIron(gp);
		gp.obj[9].worldX = 69 * gp.tileSize;
		gp.obj[9].worldY = 71 * gp.tileSize;
		
		gp.obj[13] = new OBJ_Door(gp);
		gp.obj[13].worldX = 83 * gp.tileSize;
		gp.obj[13].worldY = 23 * gp.tileSize;
		
		gp.obj[10] = new OBJ_Chest(gp);
		gp.obj[10].worldX = 69 * gp.tileSize;
		gp.obj[10].worldY = 77 * gp.tileSize;
		
		//ISLAND 4
		
		gp.obj[17] = new OBJ_Door(gp);
		gp.obj[17].worldX = 88 * gp.tileSize;
		gp.obj[17].worldY = 52 * gp.tileSize;
		
		
		gp.obj[15] = new OBJ_Key(gp);
		gp.obj[15].worldX = 89 * gp.tileSize;
		gp.obj[15].worldY = 63 * gp.tileSize;
		
		gp.obj[6] = new OBJ_IronKey(gp);
		gp.obj[6].worldX = 87 * gp.tileSize;
		gp.obj[6].worldY = 63 * gp.tileSize;
	
		
	}
	
	public void setNPC() {
		
		gp.npc[0] = new NPC_OldMan(gp);
		gp.npc[0].worldX = gp.tileSize * 24;
		gp.npc[0].worldY = gp.tileSize * 59;
	}

}
