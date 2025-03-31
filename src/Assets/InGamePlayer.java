package Assets;

import java.awt.Color;
import java.awt.Graphics;

import Game.Game;
import Game.Handler;

/**
 * class for the player during the game which holds the players values like health and draws the player on the screen 
 * @author fuelvin
 */
public class InGamePlayer {
	
	Description description;
	Handler handler;
	
	/**
	 * creates a new instance of InGamePlayer
	 * @author fuelvin
	 * @param handler Handler used to access game information from
	 */
	public InGamePlayer(Handler handler) {
		this.handler = handler;
		description = new Description(1, Game.sPlayer.name, Game.sPlayer.health, Game.sPlayer.baseHealth, Game.sPlayer.level, 392, 396, handler);
	}
	
	/**
	 * Called once per frame, calls the tick function on this objects description
	 * @author fuelvin
	 */
	public void tick() {
		description.tick();
	}
	
	/**
	 * draws self on the game screen
	 * @author fuelvin
	 * @param g graphics to draw to
	 */
	public void render(Graphics g) {
		description.render(g);
		Color c = new Color(184, 184, 184);
		g.setColor(c);
		//g.fillRect(396, 516, 400, 12);
	}

}
