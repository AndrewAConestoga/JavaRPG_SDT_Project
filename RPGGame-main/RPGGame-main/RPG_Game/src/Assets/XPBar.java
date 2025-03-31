package Assets;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import Game.Game;
import Game.Handler;
import States.GameState;

/**
 * xp bar asset to be drawn to the game screen to display the players experience
 * @author fuelvin
 */
public class XPBar {
	
	private int x;
	private int y;
	private Text healthText;
	
	/**
	 * draws self on the game screen
	 * @author fuelvin
	 * @param x top left x position of where to draw XPBar to
	 * @param y top left y position of where to draw XPBar to
	 */
	public XPBar(int x, int y) {
		this.x = x;
		this.y = y;
		healthText = new Text(Game.sPlayer.health + "/" + Game.sPlayer.baseHealth, x + 152, y + 80, 4, 1);
	}
	
	/**
	 * called once every frame
	 * @author fuelvin
	 */
	public void tick() {
	}
	
	/**
	 * draws self on the game screen
	 * @author fuelvin
	 * @param g graphics to draw to
	 */
	public void render(Graphics g) {
		drawXPBar(g);
	}
	
	/**
	 * draws the amount of xp the player has into the xp bar on screen
	 * @author fuelvin
	 * @param g graphics to draw to
	 */
	private void drawXPBar(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int cc = 90;
		Color c = new Color(cc, cc, cc);
		g.setColor(c);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
		g.fillRect(112, 44, 276, 20);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		
		c = new Color(0, 0, 255);
		g.setColor(c);
		for(int i = 0; i < Game.sPlayer.xp * 10; i++) {
			g.fillRect(x + 88 + i , y + 44, 2, 20);
		}

	}

	
	
	
	
	
	
	
	
	
	
	
	
}
