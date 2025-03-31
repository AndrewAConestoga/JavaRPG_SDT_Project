package Assets;

import java.awt.Graphics;

import Game.Handler;

/**
 * description class used to store and display information of creatures and monsters
 * @author fuelvin
 */
public class Description {
	private Text nameText;
	private String name;
	private int level;
	private int x;
	private int y;
	private Level levelDescription;
	private HealthBar healthBar;
	private XPBar xpBar;
	private int baseHealth;
	private int health;
	private int type;
	
	/**
	 * creates an instance of Description at the top left corner of the screen
	 * @author fuelvin
	 * @param type type of description
	 * @param name name of the description
	 * @param health current health of the description of the owner creature
	 * @param baseHealth the max health of the owner creature
	 * @param level level of the owner creature
	 * @param x top left x position of where to draw description on screen
	 * @param y top left y position of where to draw description on screen
	 * @param handler Handler used to access game information from
	 */
	public Description(int type, String name, int health, int baseHealth, int level, int x, int y, Handler handler) {
		nameText = new Text(name, x - 4, y + 22, 4, -1);
		this.type = type;
		this.name = name;
		this.level = level;
		this.x = x;
		this.y = y;
		this.health = health;
		this.baseHealth = baseHealth;
		levelDescription = new Level(type, level, x, y);
		healthBar = new HealthBar(type, x, y, handler);
		xpBar = new XPBar(x, y);
	}
	
	/**
	 * creates an instance of Description at the top left corner of the screen
	 * @author fuelvin
	 * @param type type of description
	 * @param name name of the description
	 * @param health current health of the description of the owner creature
	 * @param baseHealth the max health of the owner creature
	 * @param level level of the owner creature
	 * @param handler Handler object that will be used to manipulate this object
	 */
	public Description(int type, String name, int health, int baseHealth, int level, Handler handler) {
		this(type, name, health, baseHealth, level, 0, 0, handler);
	}

	/**
	 * getter for name
	 * @author fuelvin
	 * @return name as a string
	 */
	public String getName() {
		return name;
	}

	/**
	 * setter for name
	 * @author fuelvin
	 * @param name sets name to value of this parameter
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * called every frame, calls the tick function on the healthBar member variable
	 * @author fuelvin
	 */
	public void tick() {
		healthBar.tick();
	}
	
	/**
	 * draws self on the game screen
	 * @author fuelvin
	 * @param g graphics to draw to
	 */
	public void render(Graphics g) {
		if(type == 0 || type == 1) {
			g.drawImage(Assets.enemyDescription, x, y, 102 * 4, 33 * 4, null);
			levelDescription.render(g);
			nameText.render(g);
			healthBar.render(g);
		} else if(type == 2) {
			xpBar.render(g);
			healthBar.render(g);
			g.drawImage(Assets.playerDescription, 0, 0, 105 * 4, 38 * 4, null);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
