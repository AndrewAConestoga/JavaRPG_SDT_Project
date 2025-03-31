package Assets;

import java.awt.Graphics;

/**
 * the class displays the level of players and monsters in text in combat
 * @author fuelvin
 */
public class Level {
	
	private int level;
	private int x;
	private int y;
	private Text levelText;
	private int type;
	
	/**
	 * creates a new Instance of Level
	 * @author fuelvin
	 * @param type the type of the level
	 * @param level the level number to be displayed in text
	 * @param x the top left x position of where the level will be drawn on the screen
	 * @param y the top left y position of where the level will be drawn on the screen
	 */
	public Level(int type, int level, int x, int y) {
		this.type = type;
		this.level = level;
		this.x = x;
		this.y = y;
		levelText = new Text(level + "", x + 368, y + 22, 4, 1);
	}
	
	/**
	 * called once per frame
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
		g.drawImage(Assets.monsterLevel, x + 304, y + 14, 92, 40, null);
		levelText.render(g);
	}

}
