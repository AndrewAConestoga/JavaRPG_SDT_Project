package Assets;

import java.awt.AlphaComposite;
import States.GameState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import Game.Game;
import Game.Handler;
import States.BattleState;
import States.State;

public class HealthBar {
	
	private int x;
	private int y;
	private Text healthText;
	private float healthBarWidth = 300;
	private float inGameHPWidth = 346;
	private int type;
	private Handler handler;
	private Transition transition;
	
	public HealthBar(int type, int x, int y, Handler handler) {
		this.handler = handler;
		this.type = type;
		this.x = x;
		this.y = y;
		healthText = new Text(Game.player.health + "/" + Game.player.baseHealth, x + 152, y + 80, 4, 1);
		transition = new Transition();
	}
	
	private boolean f;
	private int i = 0;
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
			if(Game.player.health <= 0) {
				BattleState.encounterFlag = true;
				Game.player.health = 0;
				handler.getMouseManager().setUIManager(null);
				Transition.canStart = false;
				Game.flag2 = false;
				i++;
				BattleState.switchGameStates = true;
				
				if(i > 100) {
					
					Player checkpoint = handler.getPlayerCaretaker().getLatestMemento().getSavedPlayer();
					
					State.setState(handler.getGame().gameState);
					Game.player.health = checkpoint.baseHealth;
					Game.player.coins = checkpoint.coins;
					Game.player.xp = checkpoint.xp;
					handler.getGameCamera().setxOffset(0);
					handler.getGameCamera().setyOffset(0);
					handler.getWorld().getEntityManager().getPlayer().setX(24 * Tile.TILEWIDTH + 10);
					handler.getWorld().getEntityManager().getPlayer().setY(48 * Tile.TILEHEIGHT + 16);
					Game.player.dir = 1;
					Creature.xPosition = 0;
					Creature.yPosition = 0;
				}
				
			}
			healthText.setHealth(Game.player.health, Game.player.baseHealth);
		}
	}
	
	
	public void render(Graphics g) {
		if(type == 0 || type == 1) {
			drawHealthBar(g);
		} else if(type == 2) {
			drawHealthBar2(g);
		}
	}
	
	private int combinedDamage;
	private int baseHealth;
	private void drawHealthBar(Graphics g) {
		if(type == 0) {
			combinedDamage = Monster.baseHealth - Monster.health;
			baseHealth = Monster.baseHealth;
		} else if(type == 1) {
			combinedDamage = Game.player.baseHealth - Game.player.health;
			baseHealth = Game.player.baseHealth;
		}
		
		Color c = new Color(0, 255, 0);
		g.setColor(c);
		
		for(int i = 0; i < healthBarWidth - (healthBarWidth / baseHealth * combinedDamage); i++) {
			g.fillRect(x + 74 + i , y + 72, 1, 40);
		}
		
		g.drawImage(Assets.enemyHealthBar, x + 70, y + 72, 77 * 4, 10 * 4, null);
		
		healthText.render(g);
	}
	
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
		for(int i = 0; i < inGameHPWidth - (inGameHPWidth / 100 * (Game.player.baseHealth - Game.player.health)); i++) {
			g.fillRect(x + 70 + i , y, 1, 44);
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
