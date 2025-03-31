package Assets;

import java.awt.AlphaComposite;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import Game.Game;
import Game.Handler;
import States.BattleState;
import States.State;

/**
 * health bar class used to draw the player and enemies health bars on the screen 
 * @author fuelvin
 */
public class HealthBar {
	
	private int x;
	private int y;
	private Text healthText;
	private float healthBarWidth = 300;
	private float inGameHPWidth = 346;
	private int type;
	private Handler handler;
	
	/**
	 * creates a new instance of HealthBar
	 * @author fuelvin
	 * @param type the type of the health bar
	 * @param x the top left x position of where the health bar will be drawn on the screen
	 * @param y the top left y position of where the health bar will be drawn on the screen
	 * @param handler Handler used to access game information from
	 */
	public HealthBar(int type, int x, int y, Handler handler) {
		this.handler = handler;
		this.type = type;
		this.x = x;
		this.y = y;
		healthText = new Text(Game.sPlayer.health + "/" + Game.sPlayer.baseHealth, x + 152, y + 80, 4, 1);
	}
	
	private boolean f;
	private int i = 0;
	
	/**
	 * called once per frame, sets the text of the health bar for monster and player
	 * to their current integer health values
	 * @author fuelvin
	 */
	public void tick() {
		if(type == 0) {
			if(Monster.health <= 0) {
				Monster.health = 0;
				if(!f) {
					f = true;
					Monster.deathState = 1;
				}
			}
			healthText.setHealth(Monster.health, Monster.baseHealth);
		} else if(type == 1){
			if(Game.sPlayer.health <= 0) {
				BattleState.encounterFlag = true;
				Game.sPlayer.health = 0;
				handler.getMouseManager().setUIManager(null);
				Transition.canStart = false;
				Game.sFlag2 = false;
				i++;
				BattleState.switchGameStates = true;
				
				if(i > 100) {
					
					Player checkpoint = handler.getPlayerCaretaker().getLatestMemento().getSavedPlayer();
					
					State.setState(handler.getGame().mGameState);
					Game.sPlayer.health = checkpoint.baseHealth;
					Game.sPlayer.coins = checkpoint.coins;
					Game.sPlayer.xp = checkpoint.xp;
					handler.getGameCamera().setxOffset(0);
					handler.getGameCamera().setyOffset(0);
					handler.getWorld().getEntityManager().getPlayer().setX(24 * Tile.TILEWIDTH + 10);
					handler.getWorld().getEntityManager().getPlayer().setY(48 * Tile.TILEHEIGHT + 16);
					Game.sPlayer.setDir(1);
					Game.sPlayer.xPosition = 0;
					Game.sPlayer.yPosition = 0;
				}
				
			}
			healthText.setHealth(Game.sPlayer.health, Game.sPlayer.baseHealth);
		}
	}
	
	/**
	 * draws self on the game screen
	 * @author fuelvin
	 * @param g graphics to draw to
	 */
	public void render(Graphics g) {
		if(type == 0 || type == 1) {
			drawHealthBar(g);
		} else if(type == 2) {
			drawHealthBar2(g);
		}
	}
	
	private int combinedDamage;
	private int baseHealth;
	
	/**
	 * draws the current mmonsters health bar onto the screen
	 * @author fuelvin
	 * @param g graphics screen of where to draw health bar on
	 */
	private void drawHealthBar(Graphics g) {
		if(type == 0) {
			combinedDamage = Monster.baseHealth - Monster.health;
			baseHealth = Monster.baseHealth;
		} else if(type == 1) {
			combinedDamage = Game.sPlayer.baseHealth - Game.sPlayer.health;
			baseHealth = Game.sPlayer.baseHealth;
		}
		
		Color c = new Color(0, 255, 0);
		g.setColor(c);
		
		for(int i = 0; i < healthBarWidth - (healthBarWidth / baseHealth * combinedDamage); i++) {
			g.fillRect(x + 74 + i , y + 72, 1, 40);
		}
		
		g.drawImage(Assets.enemyHealthBar, x + 70, y + 72, 77 * 4, 10 * 4, null);
		
		healthText.render(g);
	}
	
	/**
	 * draws the current players health bar onto the screen
	 * @author fuelvin
	 * @param g graphics screen of where to draw health bar on
	 */
	private void drawHealthBar2(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int cc = 110;
		Color c = new Color(cc, cc, cc);
		g.setColor(c);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
		g.fillRect(74, 4, 345, 40);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		
		c = new Color(0, 245, 0);
		g.setColor(c);
		for(int i = 0; i < inGameHPWidth - (inGameHPWidth / 100 * (Game.sPlayer.baseHealth - Game.sPlayer.health)); i++) {
			g.fillRect(x + 70 + i , y, 1, 44);
		}
	}
}
