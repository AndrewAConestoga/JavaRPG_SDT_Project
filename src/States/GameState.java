package States;

import java.awt.Graphics;
import Assets.Description;
import Assets.Text;
import Game.Game;
import Game.Handler;
import Game.World;

/**
 * class holding game state information for when the player is roaming the world
 * @author fuelvin
 */
public class GameState extends State{
	
	private World world;
	public static boolean flag;
	private Description playerDescription;
	private Text coinsText;

	/**
	 * creates a new instance of GameState
	 * @author fuelvin
	 * @param handler Handler to access game information from
	 */
	public GameState(Handler handler) {
		super(handler); //calls constructor of "State" class
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
		playerDescription = new Description(2, Game.sPlayer.name, Game.sPlayer.health, Game.sPlayer.baseHealth, Game.sPlayer.level, handler);
	}
	

	/**
	 * called once per frame 
	 * @author fuelvin
	 */
	@Override
	public void tick() {
		world.tick();
	}

	/**
	 * draws the game state to the screen
	 * @author fuelvin
	 * @param g graphics screen to draw state to
	 */
	@Override
	public void render(Graphics g) {
		world.render(g);
		playerDescription.render(g);
		coinsText = new Text(Game.sPlayer.coins + "", 50, 126, 4, 4);
		coinsText.render(g);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
