package entity;

import java.util.Random;

import Main.GamePanel;

public class NPC_OldMan extends Entity{ 
	
	public NPC_OldMan(GamePanel gp) {
		
		super(gp);
		
		direction = "down";
		speed = 1;
		getImage();
		setDialogue();
	}
	
	public void getImage() {
		
		up1 = setUp("/npc/oldman_up_1");
		up2 = setUp("/npc/oldman_up_2");
		down1 = setUp("/npc/oldman_down_1");
		down2 = setUp("/npc/oldman_down_2");
		left1 = setUp("/npc/oldman_left_1");
		left2 = setUp("/npc/oldman_left_2");
		right1 = setUp("/npc/oldman_right_1");
		right2 = setUp("/npc/oldman_right_2");
	}
	
	public void setDialogue() {
		
		dialogues[0]="Hello, Son";
		dialogues[1]="So you've come to this island to \n find the treasure?";
		dialogues[2]="I used to be a great wizard ... Now I am \n too old to take an adventure, but I will \n help you out";
		dialogues[3]="You should find all the keys and especially \n the Iron key spread all around the \n islands.";
		dialogues[4]="And then, open the gates to get \n the big treasure!";
		dialogues[5]="Now go ! And good luck for you.";
	}
	
	public void setAction() {
		
			actionCounter++;
			
		
				 if(actionCounter == 120) {
					 
					 Random random = new Random();
						int i = random.nextInt(100)+1;  // pick  a number between 1 and 100

						if(i <= 25) {
							direction = "up";
						}
						if(i > 25 && i <=50) {
							direction = "down";
						}
						if(i >= 50 && i < 75) {
							direction = "left";
						}
						if (i > 75 && i <= 100) {
							direction = "right";
						}
						//System.out.println(i+"   "+actionCounter);
						actionCounter = 0;
				 }	
	}
	
	public void speak() {
		
		super.speak();
			
	}
}
