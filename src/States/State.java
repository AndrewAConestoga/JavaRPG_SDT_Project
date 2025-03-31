package States;

import java.awt.Graphics;

import Game.Handler;

/**
 * class interface for game states
 * @author fuelvin
 */
public abstract class State {
	
	private static State currentState = null;
	
	/**
	 * sets state of the game
	 * @author fuelvin
	 * @param state State to set game to
	 */
	public static void setState(State state) {
		currentState = state;
	}
	
	/**
	 * gets the current state of the game
	 * @author fuelvin
	 * @return current game state
	 */
	public static State getState() {
		return currentState;
	}
	
	protected Handler handler;
	
	/**
	 * creates a new instance of State
	 * @author fuelvin
	 * @param handler Handler to access game information from
	 */
	public State(Handler handler) {
		this.handler = handler;
	}
	
	/**
	 * to be called once per frame
	 * @author fuelvin
	 */
	public abstract void tick();
	
	/**
	 * to be used to draw state graphics on the screen
	 * @author fuelvin
	 * @param g graphics screen to draw state to 
	 */
	public abstract void render(Graphics g);
	
}
