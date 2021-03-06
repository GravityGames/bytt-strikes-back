package com.gravitygamesinteractive.byttstrikesback;

import java.awt.Dimension;
import java.awt.event.*;

public class KeyListen implements KeyListener{
	public static boolean downpressed=false;

	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		if(!Main.menuon && !Level.levelOver){
		switch(key){
		case KeyEvent.VK_RIGHT:
		Frame.isMoving=true;
		Frame.dir=Character.moveSpeed;
		Main.Kylex=Level.kyle.x-8-Frame.sx;
		Main.Kyley=Level.kyle.y-Frame.sy;
		//Main.Kylex+=3;
		break;
		
		case KeyEvent.VK_LEFT:
			Frame.isMoving=true;
			Frame.dir=-Character.moveSpeed;
			break;
		case KeyEvent.VK_UP:
			Character.movingup=true;
			Character.movingdown=false;
			break;
		case KeyEvent.VK_DOWN:
			Character.movingup=false;
			Character.movingdown=true;
			break;
		case KeyEvent.VK_Z:
			if(Level.currentEnemy==1){
				Level.currentEnemy=2;
			}else{
				Level.currentEnemy-=1;
			}
			break;
        case KeyEvent.VK_X:
        	if(Level.currentEnemy==2){
				Level.currentEnemy=1;
			}else{
				Level.currentEnemy+=1;
			}
			break;
        case KeyEvent.VK_SPACE:
			Character.spawningEnemy=true;
			Character.spaceDown=true;
			//Character.spawnSoundPlaying=true;
			break;
		case KeyEvent.VK_ENTER:
			if(Level.Timer>0){
			Level.Timer=0;
			}else{
				Frame.sx=0;
			}
			break;
		}
		}else if(Main.menuon){
			switch(key){
			case KeyEvent.VK_RIGHT:
				if(Menu.currentMenu==2){
					if(Menu.option==3){
						Menu.option=1;
					}else{
					Menu.option+=1;
				}
					Menu.blip=true;
				    }else if(Menu.currentMenu==6){
						if(Menu.option==1){
							if(Menu.screenSize<Frame.screenSize.height/Frame.gameSize.height){
								Menu.screenSize++;
								Frame.windowSize = new Dimension(Frame.gameSize.width*Menu.screenSize, Frame.gameSize.height*Menu.screenSize);
								Frame.frame.setSize(Frame.windowSize);
								Frame.frame.setLocationRelativeTo(null);
							}else{
								Menu.screenSize=1;
								Frame.windowSize = new Dimension(Frame.gameSize.width*Menu.screenSize, Frame.gameSize.height*Menu.screenSize);
								Frame.frame.setSize(Frame.windowSize);
								Frame.frame.setLocationRelativeTo(null);
							}
						}else{
							
						}
						Menu.blip=true;
					    }
			break;
			
			case KeyEvent.VK_LEFT:
				if(Menu.currentMenu==2){
					if(Menu.option==1){
						Menu.option=3;
					}else{
					Menu.option-=1;
				}
					Menu.blip=true;
				}else if(Menu.currentMenu==6){
					if(Menu.option==1){
						if(Menu.screenSize>1){
							Menu.screenSize--;
							Frame.windowSize = new Dimension(Frame.gameSize.width*Menu.screenSize, Frame.gameSize.height*Menu.screenSize);
							Frame.frame.setSize(Frame.windowSize);
							Frame.frame.setLocationRelativeTo(null);
						}else{
							Menu.screenSize=Frame.screenSize.height/Frame.gameSize.height;
							Frame.windowSize = new Dimension(Frame.gameSize.width*Menu.screenSize, Frame.gameSize.height*Menu.screenSize);
							Frame.frame.setSize(Frame.windowSize);
							Frame.frame.setLocationRelativeTo(null);
						}
					}else{
						
					}
					Menu.blip=true;
				    }
				break;
			case KeyEvent.VK_UP:
				if(Menu.currentMenu==1){
					if(Menu.option==1){
						Menu.option=5;
					}else{
						Menu.option--;
					}
				//Menu.option-=1;
				Menu.blip=true;
				}else if(Menu.currentMenu==3){
					if(Menu.option==1){
						Menu.option=4;
					}else{
						Menu.option--;
					}
				//Menu.option-=1;
				Menu.blip=true;
				}
				break;
			case KeyEvent.VK_DOWN:
				if(Menu.currentMenu==1){
					if(Menu.option==5){
						Menu.option=1;
					}else{
						Menu.option++;
					}
				//Menu.option+=1;
				Menu.blip=true;
			    }else if(Menu.currentMenu==3){
					if(Menu.option==4){
						Menu.option=1;
					}else{
						Menu.option++;
					}
				//Menu.option+=1;
				Menu.blip=true;
			    }
				break;
			case KeyEvent.VK_Z:
				Menu.confirmed=true;
				if(Menu.currentMenu==1){
					if(Menu.option==1){
						Menu.currentMenu=2;
						Menu.option=1;
					}else if(Menu.option==2){
						Menu.currentMenu=3;
						Menu.option=1;
					}else if(Menu.option==3){
						Menu.currentMenu=4;
						Menu.option=1;
					}
				}else if(Menu.currentMenu==2){
					if(Menu.option==1){
						Main.levelname=new String("assets/VerdantValley1");
						}
						if(Menu.option==2){
							Main.levelname=new String("assets/VerdantValley2");
							}
						if(Menu.option==3){
							Main.levelname=new String("assets/VerdantValley4");
							}
				Main.menuon=false;
				Level.levelOver=false;
				Level.levelWon=false;
				}else if(Menu.currentMenu==3){
					if(Menu.option==1){
						//Menu.currentMenu=2;
					}else if(Menu.option==2){
						Menu.currentMenu=6;
					}else if(Menu.option==3){
						//Menu.currentMenu=4;
					}
					Menu.option=1;
				}else if(Menu.currentMenu==4){
					Menu.currentMenu=1;
					Menu.option = 1;
				}
				break;
	        case KeyEvent.VK_X:
	        	Menu.confirmed=true;
	        	if(Menu.currentMenu==1){
					
				}else if(Menu.currentMenu==2){
					Menu.currentMenu=1;
					Menu.option=1;
				}else if(Menu.currentMenu==3){
					Menu.currentMenu=1;
					Menu.option=1;
				}else if(Menu.currentMenu==6){
					Menu.currentMenu=3;
					Menu.option=1;
				}
				break;
	        case KeyEvent.VK_SPACE:
	        	Menu.confirmed=true;
	        	if(Menu.currentMenu==1){
					Menu.currentMenu=2;
					Menu.option=1;
				}else if(Menu.currentMenu==2){
					if(Menu.option==1){
						Main.levelname=new String("assets/VerdantValley1");
						}
						if(Menu.option==2){
							Main.levelname=new String("assets/VerdantValley2");
							}
						if(Menu.option==3){
							Main.levelname=new String("assets/VerdantValley4");
							}
				Main.menuon=false;
				Level.levelOver=false;
				Level.levelWon=false;
				}
				break;
			case KeyEvent.VK_ENTER:
				Menu.confirmed=true;
				if(Menu.currentMenu==1){
					Menu.currentMenu=2;
					Menu.option=1;
				}else if(Menu.currentMenu==2){
					if(Menu.option==1){
						Main.levelname=new String("assets/VerdantValley1");
						}
						if(Menu.option==2){
							Main.levelname=new String("assets/VerdantValley2");
							}
						if(Menu.option==3){
							Main.levelname=new String("assets/VerdantValley4");
							}
				Main.menuon=false;
				Level.levelOver=false;
				Level.levelWon=false;
				}
				break;
			}
		}else if(Level.levelOver){
			switch(key){
			case KeyEvent.VK_Z:
				Main.statscreenover=true;
				Level.levelOver=false;
				Level.levelWon=false;
				break;
	        case KeyEvent.VK_X:
	        	Main.statscreenover=true;
	        	Level.levelOver=false;
				Level.levelWon=false;
				break;
	        case KeyEvent.VK_SPACE:
	        	Main.statscreenover=true;
	        	Level.levelOver=false;
				Level.levelWon=false;
				break;
			case KeyEvent.VK_ENTER:
				Main.statscreenover=true;
				Level.levelOver=false;
				Level.levelWon=false;
				break;
			}
		}
	}

	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		switch(key){
		case KeyEvent.VK_RIGHT:
		if(Frame.dir==Character.moveSpeed){
			Frame.isMoving=true;
			Frame.dir=0;
		}
		break;
		
		case KeyEvent.VK_LEFT:
			if(Frame.dir==-Character.moveSpeed){
				Frame.isMoving=true;
				Frame.dir=0;
			}
			break;
		case KeyEvent.VK_UP:
			Character.movingup=false;
			Character.movingdown=false;
			break;
		case KeyEvent.VK_DOWN:
			Character.movingup=false;
			Character.movingdown=false;
			break;
		case KeyEvent.VK_Z:
			//Frame.isJumping=false;
			//Character.isJumping=false;
			Character.allowFall=true;
			downpressed=false;
			if(Character.allowFall){
			Character.jumpCount=Character.jumpHeight;
			}
		break;
		case KeyEvent.VK_SPACE:
			Character.spaceDown=false;
		break;
		}
	}
	public void keyTyped(KeyEvent e){
		int key = e.getKeyCode();
		
		switch(key){
		case KeyEvent.VK_RIGHT:
		
		break;
		
		case KeyEvent.VK_LEFT:
			
			break;
		}
	}
}
