package Assets;


import java.awt.AlphaComposite;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Timer;
import java.util.TimerTask;

import Game.Game;
import Game.Handler;
import ImageStuff.Animation;
import States.BattleState;

/**
 * attack bar manager used when the player is being attacked by an enemy
 * controls the inputs, speed, and display for the attack bar in game
 * @author fuelvin
 */
public class AttackBarManager {
	private int y;
	public static float xVel;
	private static final float STARTING_X = 104;
	public boolean flag;
	public static boolean pressed;
	private boolean canPress;
	public static boolean hitPause;
	private int baseDamage;
	private int damage;
	public static int id = 0;
	
	private Handler handler;
	
	public static int milliSecondsPassed = 0;
	private int milliSecondsPassed2 = 0;
	private Timer timer;
	
	private Text damageText;
	private Text turn;
	private Text turn2;
	private Text redDamageText;

	
	private Animation slashAnimation;
	private Animation attackBar;
	
	TimerTask task;
	
	/**
	 * creates a new instance of an AttackBarManager 
	 * @author fuelvin
	 * @param handler Handler used to access game information from
	 */
	public AttackBarManager(Handler handler) {
		timer = new Timer();
		task = new TimerTask() {
			public void run() {
				if(BattleState.showBars && milliSecondsPassed < 2600)
				milliSecondsPassed++;
				milliSecondsPassed2++;
			}
		};
		
		this.handler = handler;
		xVel = STARTING_X;
		baseDamage = 20;
		damage = baseDamage;
		milliSecondsPassed = 0;
		y = Bar.height - 8;
		canPress = true;
		turn = new Text("Your turn", 0, 600, 4, 0);
		turn2 = new Text("Enemy turn", 0, 600, 4, 0);
		slash = false;
		pressed = false;
		slashAnimation = new Animation(35, Asset.slashAnimation);
		attackBar = new Animation(90, Asset.attackBar);
		timer.scheduleAtFixedRate(task, 1, 1);
	}

	/**
	 * called once a frame, used to animate enemy attacks and the attack bar items
	 * @author fuelvin
	 */
	public void tick() {	
		if(slash && slashAnimation.getIndex() < 8) {
			slashAnimation.tick();
		}
		if(f) {
			attackBar.tick();
		}
		if(milliSecondsPassed > 700) {
			move();
		}
		
		if(BattleState.switchGameStates) {
			destroy();
		}
	}
	
	private boolean f;
	private boolean f2;
	public static boolean slash;
	private boolean slashFlag;
	public static boolean critical;
	
	/**
	 * draws self on the game screen
	 * @author fuelvin
	 * @param g graphics to draw to
	 */
	public void render(Graphics g) {
		if(Monster.deathState < 2) {
			g.drawImage(Asset.attackBar[0], (int)(xVel) - 4, y, 4 * 2, 34 * 2, null);
			if(BattleState.playerAttack) {
				if(!f2) {
					f2 = true;
					milliSecondsPassed = 0;
				}
				playerAttack(g);
			} else {
				enemyAttack(g);
			}
		}
		
		
	}
	
	/**
	 * getter for the damage member variable
	 * @author fuelvin
	 * @return amount of damage dealt
	 */
	public int getDamage() {
		return damage;
	}
	
	private float opacity = 1;
	
	/**
	 * called once a frame, used to animate enemy attacks and the attack bar items
	 * @author fuelvin
	 */
	private void playerAttack(Graphics g) {
		if(Monster.deathState < 2) {
			turn.render(g);
		}
			if(pressed) {
				if(!f) {
					initializeOnPress();
				}
				if(milliSecondsPassed > 600) {
					if(!slashFlag) {
						Monster.health -= damage;
						if(critical) {
							damageText = new Text(damage + "", 503 - (String.valueOf(damage).length()) * 14, 80, 8, 2);
						} else {
							damageText = new Text(damage + "", 529 - (String.valueOf(damage).length()) * 14, 100, 4, 2);
						}
						slashFlag = true;
						slash = true;
					}
					if(milliSecondsPassed < 1600) {
						damageText.render(g);
					}
				}
				if(milliSecondsPassed > 2200 && Monster.deathState == 0) {
					initializeWhenPlayerTurnEnd();
				} else {
					g.drawImage(attackBar.getCurrentFrame(), (int)(xVel) - 4, y, 8, 68, null);
				}
			}
			
			if(slash && slashAnimation.getIndex() < 8) {
				g.drawImage(slashAnimation.getCurrentFrame(), 510, 170, 30, 100, null);
			}
			
	}
	
	/**
	 * called when player is attacking enemy and presses space bar, checks to see if the player gets a critical
	 * hit and calculates damage dealt
	 * @author fuelvin
	 */
	private void initializeOnPress() {
		critical = false;
		attackBar.setIndex(0);
		damage = baseDamage - (int)(Math.abs(Bar.randomX - xVel) / (300 / baseDamage));
		if(Math.abs(Bar.randomX - xVel) <= 8) {
			damage *= 1.5;
			critical = true;
		} else if(damage <= 0) {
			damage = 0;
		}
		
		slashAnimation.setIndex(0);
		f = true;
		
		milliSecondsPassed = 0;
	}
	
	/**
	 * called when players turn has ended, resets bar and attack variables
	 * @author fuelvin
	 */
	private void initializeWhenPlayerTurnEnd() {
		f = false;
		f2 = false;
		milliSecondsPassed = 0;
		xVel = 104;
		flag = false;
		pressed = false;
		canPress = true;
		slashFlag = false;
		slash = false;
		BattleState.playerAttack = false;
		Bar.randomX = (int)(Math.random() * 540 + 150);
	}
	
	
	private int i = 0;
	private int max;
	public static int maxId;
	private boolean renderRedText;
	public static boolean canCheckId;
	
	/**
	 * displays enemy attacking player and changes to player turn after animation
	 * @author fuelvin
	 * @param g graphics screen to display the animation on
	 */
	private void enemyAttack(Graphics g) {
		if(Monster.deathState < 2) {
			turn2.render(g);
		}
		if(pressed) {
			if(!f) {
				initializeOnPress2();
			}
			
			if(i < 30 && renderRedText) {
				renderRedText(g);
			}
			
			if(milliSecondsPassed > 500) {
				resetAfterTouchBar();
			}
			g.drawImage(attackBar.getCurrentFrame(), (int)(xVel) - 4, y, 8, 68, null);
		}
		
		if(Bar.barsLeft == 0) {
			if(!f2) {
				milliSecondsPassed2 = 0;
				f2 = true;
			}
			if(milliSecondsPassed2 > 800) {
				switchToPlayerTurn();
			}
		}
	}
	
	
	
	
	
	/**
	 * called when player presses spacebar when blocking enemy attack, detects
	 * if they missed the block and take damage or successfully block
	 * @author fuelvin
	 */
	private void initializeOnPress2() {
		f = true;
		opacity = 1;
		max = -1;
		attackBar.setIndex(0);
		milliSecondsPassed = 0;
		hitPause = true;
		
		if(!MonsterBar.touchingBar) {
			renderRedText = true;
			Game.sPlayer.health -= 10;
			redDamageText = new Text("-10", (int)AttackBarManager.xVel - 35, 635, 4, 3);
			i = 0;
		} else {
			for(int i = 0; i < Bar.ids.length; i++) {
				if(Bar.ids[i] == true) {
					max = Math.max(max, i);
				}
			}
			maxId = max;
			canCheckId = true;
		}
	}
	
	/**
	 * resets the attack bar after it has been touched/clicked
	 * @author fuelvin
	 */
	private void resetAfterTouchBar() {
		canCheckId = false;
		renderRedText = false;
		f = false;
		milliSecondsPassed = 300;
		pressed = false;
		canPress = true;
		id = 0;
	}
	
	/**
	 * displays red text on the screen for showing damage dealt
	 * @author fuelvin
	 * @param g graphics screen to display text on
	 */
	private void renderRedText(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
		redDamageText.render(g);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		opacity -= 0.0333f;
		i++;
		redDamageText.setY(635 - i / 2);
	}
	
	/**
	 * switches the current turn in the battle state to the players turn 
	 * @author fuelvin
	 */
	private void switchToPlayerTurn() {
		milliSecondsPassed = 0;
		BattleState.playerAttack = true;
		BattleState.showBars = false;
		xVel = STARTING_X;
		f = false;
		flag = false;
		f2 = false;
	}
	
	/**
	 * called when this object is deconstructed and no longer in use
	 * @author fuelvin
	 */
	public void destroy() {
		timer.cancel();
		task.cancel();
	}
	
	/**
	 * checks for the players input during their turn or when blocking
	 * @author fuelvin
	 */
	private void move() {
		if(canPress && (handler.getKeymanager().q || handler.getKeymanager().space)) {
			pressed = true;
		} else if(!canPress){
			pressed = false;
		}
		
		if(!pressed) {
			hitPause = false;
			if(!flag) {
				xVel+=15;
				if(xVel >= 680) {
					flag = true;
				}
			} else {
				xVel-=15;
				if(xVel <= 110) {
					flag = false;
				}
			}
		}
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
