package Main;

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	public int touchs = 0;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, spacePressed, bPressed;
	public int u = 0;
	public int verif;
	
	//DEBUG
	boolean checkDrawTime =false;  
	
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		//TITLESTATE
		if(gp.gameState == gp.titleState) {
			
			if(gp.ui.titleScreenState == 0) {
				
				if(code == KeyEvent.VK_Z || code == KeyEvent.VK_UP) {
					gp.ui.commandNum --;
					if(gp.ui.commandNum < 0) {
						gp.ui.commandNum = 3;
					}
				}
			    if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
					gp.ui.commandNum ++;
					if(gp.ui.commandNum > 3) {
						gp.ui.commandNum = 0;
					}
				}
				if(code == KeyEvent.VK_ENTER) {
					if(gp.ui.commandNum == 0) {
						
						gp.gameState = gp.playState;
						gp.playMusic(0);
					}
					if(gp.ui.commandNum == 1) {
						verif = 0;
						gp.ui.titleScreenState = 1;
					}
					if(gp.ui.commandNum == 2) {
						verif = 0;
						gp.ui.titleScreenState = 3;
					}
					if(gp.ui.commandNum == 3) {
						System.exit(0);
					}
				}
			}
			else if(gp.ui.titleScreenState == 1) {
				
				if(code == KeyEvent.VK_ENTER) {
					
					if(verif == 0) {
						
						gp.gameState = gp.titleState;
						gp.ui.titleScreenState = 0;
					}
					else if(verif == 2) {
						
						gp.gameState = gp.titleState;
						gp.ui.titleScreenState = 2;
					}
						
					
				}
			}
			else if(gp.ui.titleScreenState == 2) {
				
				if(code == KeyEvent.VK_Z || code == KeyEvent.VK_UP) {
					gp.ui.commandNum --;
					if(gp.ui.commandNum < 0) {
						gp.ui.commandNum = 3;
					}
				}
			    if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
					gp.ui.commandNum ++;
					if(gp.ui.commandNum > 3) {
						gp.ui.commandNum = 0;
					}
				}
				if(code == KeyEvent.VK_ENTER) {
					if(gp.ui.commandNum == 0) {
						
						gp.gameState = gp.playState;
						gp.playMusic(0);
					}
					if(gp.ui.commandNum == 1) {
						
						verif = 2;
						gp.ui.titleScreenState = 1;
					}
					if(gp.ui.commandNum == 2) {
						
						verif = 2;
						gp.ui.titleScreenState = 3;
					}
					if(gp.ui.commandNum == 3) {
						System.exit(0);
					}
				}
			}
			else if(gp.ui.titleScreenState == 3) {
				
				if(code == KeyEvent.VK_ENTER) {
					
					if(verif == 0) {
						
						gp.gameState = gp.titleState;
						gp.ui.titleScreenState = 0;
					}
					else if(verif == 2) {
						
						gp.gameState = gp.titleState;
						gp.ui.titleScreenState = 2;
					}
				}
			}
		}
		//PLAYSTATE
		if(gp.gameState == gp.playState) {
			
			if(gp.ui.gameFinished == false ) {
				
				if(code == KeyEvent.VK_Z || code == KeyEvent.VK_UP) {
					upPressed = true;
					touchs++;
				}
				else if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
					downPressed = true;
					touchs++;
				}
				else if(code == KeyEvent.VK_Q || code == KeyEvent.VK_LEFT) {
					leftPressed = true;
					touchs++;
				}
				else if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
					rightPressed = true;
					touchs++;
				}
				else if(code == KeyEvent.VK_U) {
					u++;
				}
				else if(code == KeyEvent.VK_P) {
					gp.stopMusic();
					gp.gameState = gp.titleState;
					gp.ui.titleScreenState = 2;
				}
				else if (code == KeyEvent.VK_ENTER) {
					enterPressed = true;
				}
				else if (code == KeyEvent.VK_SPACE) {
					spacePressed = true;
				}
				else if (code == KeyEvent.VK_B) {
					bPressed = true;
				}
			}
			else if(gp.ui.gameFinished == true) {
				
				 if (code == KeyEvent.VK_ENTER) {
					
					new Main();
				}	
		}
		
}		
		//PAUSESTATE
				
		//DEBUG
		if(code == KeyEvent.VK_T) {
			if(checkDrawTime ==false) {
				checkDrawTime = true;
			}
			else if(checkDrawTime == true) {
				checkDrawTime = false;
			}
		}
		
		//DIALOGUESTATE
		if(gp.gameState == gp.dialogueState) {
			if( code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.playState;
			}
		}
}
	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_Z || code == KeyEvent.VK_UP) {
			upPressed = false;
		}
		else if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			downPressed = false;
		}
		else if(code == KeyEvent.VK_Q || code == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}
		else if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}
		
		
		
	}
	

}
