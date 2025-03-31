package Game;

import Assets.Player;
import Memento.PlayerCaretaker;
import Memento.PlayerOriginator;

/**
 * class used to contain important information about the game 
 * @author fuelvin
 */
public class Handler {

	private Game mGame;
	private World mWorld;
	private PlayerOriginator mPlayerOriginator = new PlayerOriginator();
	private PlayerCaretaker mPlayerCaretaker = new PlayerCaretaker();
	
	/**
	 * creates a new instance of Handler
	 * @author fuelvin
	 * @param game Game object to access information of
	 */
	public Handler(Game game) {
		this.mGame = game;
	}
	
	/**
	 * getter for GameCamera
	 * @author fuelvin
	 * @return the games GameCamera
	 */
	public GameCamera getGameCamera() {
		return this.mGame.getGameCamera();
	}
	
	/**
	 * getter for KeyManager
	 * @author fuelvin
	 * @return the games KeyManager
	 */
	public KeyManager getKeymanager() {
		return this.mGame.getKeyManager();
	}
	
	/**
	 * getter for MouseManager
	 * @author fuelvin
	 * @return the games MouseManager
	 */
	public MouseManager getMouseManager() {
		return this.mGame.getMouseManager();
	}
	
	/**
	 * getter for width
	 * @author fuelvin
	 * @return the games width
	 */
	public int getWidth() {
		return this.mGame.getWidth();
	}
	
	/**
	 * getter for height
	 * @author fuelvin
	 * @return the games height
	 */
	public int getHeight() {
		return this.mGame.getHeight();
	}

	/**
	 * getter for Game
	 * @author fuelvin
	 * @return the Game
	 */
	public Game getGame() {
		return this.mGame;
	}

	/**
	 * setter for Game
	 * @author fuelvin
	 * @param new Game to access information of
	 */
	public void setGame(Game game) {
		this.mGame = game;
	}

	/**
	 * getter for World
	 * @author fuelvin
	 * @return the games World
	 */
	public World getWorld() {
		return this.mWorld;
	}

	/**
	 * setter for World
	 * @author fuelvin
	 * @param new world to set game to
	 */
	public void setWorld(World world) {
		this.mWorld = world;
	}
	
	/**
	 * getter for PlayerOriginator
	 * @author Andrew Abrantes
	 * @return PlayerOriginator used to save player states
	 */
	public PlayerOriginator getPlayerOriginator() {
		return this.mPlayerOriginator;
	}
	
	/**
	 * getter for PlayerCaretaker
	 * @author Andrew Abrantes
	 * @return PlayerCaretaker used to save player states
	 */
	public PlayerCaretaker getPlayerCaretaker() {
		return this.mPlayerCaretaker;
	}
	
	/**
	 * saves the current state of the player in a memento
	 * @author Andrew Abrantes
	 */
	public void savePlayer() {
		this.mPlayerOriginator.set(Game.sPlayer);
		this.mPlayerCaretaker.addMemento(this.mPlayerOriginator.storeInMemento());
	}
	
	/**
	 * loads the last saved state of the player
	 * @author Andrew Abrantes
	 * @return the most recently saved player
	 */
	public Player loadPlayer() {
		return this.mPlayerCaretaker.getLatestMemento().getSavedPlayer();
	}
	
}
