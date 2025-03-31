package Assets;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Game.Game;
import Game.Handler;
import States.BattleState;

/**
 * class for monster that player will fight during the game, holds all the monsters values 
 * @author fuelvin
 */
public class Monster implements MonsterInterface{
	
	protected BufferedImage img;
	private int width;
	private int height;
	public static int baseHealth;
	public static int health;
	public static int deathState;
	private long lastTime, timer;
	private int money;
	private int xp;
	private int attack;
	private Handler handler;
	private String name;
	
	private Text defeatText;
	private Text text;
	private Text rewardsText;
	private Text rewardsText2;
	private Description monsterDescription;
	private ArrayList<CoinAndXP> coinAndXP;
	
	private int x;
	private int y;
	
	/**
	 * creates a new instance of a monster object
	 * @author fuelvin
	 * @param name name of the monster
	 * @param img image to be used to display the monster
	 * @param width width of monster in pixels on screen
	 * @param height height of monster in pixels on screen
	 * @param x top left x position of where to draw monster on screen
	 * @param y top left y position of where to draw monster on screen
	 * @param health the amount of health the monster has
	 * @param attack the amount of damage the monster does
	 * @param level the level of the monster
	 * @param handler Handler used to access game information from
	 */
	public Monster(String name, BufferedImage img, int width, int height, int x, int y, int health, int attack, int level, Handler handler) {
		this.img = img;
		this.width = width;
		this.height = height;
		this.name = name;
		this.health = health;
		this.baseHealth = health;
		this.attack = attack;
		this.money = (level * 4) + (int)(Math.random() * (level * 3) + 1);
		this.xp = (level * 4) + (int)(Math.random() * (level * 3) + 1);
		deathState = 0;
		this.x = x;
		this.y = y;
		lastTime = System.currentTimeMillis();
		coinAndXP = new ArrayList<>();
		monsterDescription = new Description(0, name, health, baseHealth, level, 8, 10, handler);
		text = new Text("You encounter a " + name + "!", 68, 604, 4, 0);
		defeatText = new Text("You defeated the " + name + "!", 56, 654, 4, 0);
		rewardsText = new Text("You gain " + money + " coins!", 0, 654, 4, 0);
		rewardsText2 = new Text("You gain " + xp + " xp!", 0, 654, 4, 5);
		f = false;
		f2 = false;
	}
	
	/**
	 * called once per frame, calls tick on the monster description and CoinAndXp when defeated and adds time elapsed to the timer
	 * for moving the monster when it is attacked
	 * @author fuelvin
	 */
	public void tick() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		monsterDescription.tick();
		if(deathState == 1 && time > 10) {
			for(int i = 0; i < coinAndXP.size(); i++) {
				coinAndXP.get(i).tick();
			}
		}
		
		moveEffect();
	}
	
	
	private int amount = 0;
	private float opacity = 1;
	private int time = 0;
	private int time2 = 0;
	private int maxAmount = 15;
	private boolean f;
	private boolean f2;
	
	/**
	 * draws self on the game screen
	 * @author fuelvin
	 * @param g graphics to draw to
	 */
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		Composite ogComposite = g2d.getComposite();
		
		if(!BattleState.showBars) {
			text.render(g);
		}
		if(deathState == 1 || deathState == 2) {
			time++;
			time2++;
			if(amount < maxAmount) {
				if(time2 > (50 / maxAmount)) {
					time2 = 0;
					coinAndXP.add(new CoinAndXP());
					amount++;
				}
			}
			for(int i = 0; i < coinAndXP.size(); i++) {
				coinAndXP.get(i).render(g);
			}
			
			if(time > 100) {
				reward(g);
			}
		} else if(deathState == 3) {
			if(!f) {
				Game.sPlayer.coins += money;
				f = true;
			}
			rewardsText.render(g);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0));
			//BattleState.switchGameStates = true;
		} else if(deathState == 4) {
			if(!f2) {
				Game.sPlayer.xp += xp;
				f2 = true;
			}
			rewardsText2.render(g);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0));
		}
		
		g.drawImage(img, x, y, width, height, null);
		g2d.setComposite(ogComposite);
		monsterDescription.render(g);
		
	}
	
	/**
	 * displays the xp and coin rewards in text on the game screen when this monster is defeated
	 * @author fuelvin
	 * @param g graphics screen to display to
	 */
	private void reward(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		deathState = 2;
		defeatText.render(g);
		//rewardsText.render(g);
		if(opacity <= 0.04) {
			opacity = 0;
		} else {
			opacity -= 0.04;
		}
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
	}
	
	int i = 0;
	
	/**
	 * adjusts the monster position depending on the state of the attack bar manager, in other words
	 * moves the monster when the player attacks
	 * @author fuelvin
	 * @param g graphics screen to display to
	 */
	private void moveEffect() {
		if(AttackBarManager.slash) {
			if(!AttackBarManager.critical) {
				if(i < 4) {
					if(timer > 60 && timer < 120) {
						y = 156;
						x = 444;
					} else if(timer > 120 && timer < 180) {
						y = 158;
						x = 435;
					} else if(timer > 180) {
						i++;
						x = 440;
						y = 160;
						timer = 0;
					}
				}
			} else {
				if(i < 5) {
					if(timer > 50 && timer < 100) {
						y = 150;
						x = 448;
					} else if(timer > 100 && timer < 150) {
						y = 152;
						x = 432;
					} else if(timer > 150) {
						i++;
						x = 440;
						y = 160;
						timer = 0;
					}
				}
			}
		} else {
			i = 0;
		}
	}
	
	/**
	 * getter for health
	 * @author Dylan
	 * @return the integer value of this monsters health
	 */
	@Override
	public int getHealth() {
		return health;
	}

	/**
	 * setter for health
	 * @author Dylan
	 * @param health value to set this monster current health to
	 */
	@Override
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * setter for baseHealth
	 * @author Dylan
	 * @param health value to set this monster base health to
	 */
	@Override
	public void setBaseHealth(int health) {
		this.baseHealth = health;
	}
	
	/**
	 * getter for attack
	 * @author Dylan
	 * @return the monsters attack stat
	 */
	public int getAttack() {
		return attack;
	}
	
	/**
	 * setter for attack
	 * @author Dylan
	 * @param attack new value of the monsters attack stat
	 */
	public void setAttack(int attack) {
		this.attack = attack;
	}

}
