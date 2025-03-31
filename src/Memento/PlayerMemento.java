package Memento;

import Assets.Player;

/**
 * memento class used to store state of a player object
 * @author Andrew Abrantes
 */
public class PlayerMemento {
	
	private Player player;

	/**
	 * creates a new instance of PlayerMemento
	 * @author Andrew Abrantes
	 * @param player Player to save state of in the memento
	 */
	public PlayerMemento(Player player) { 
		this.player = player; 
	}
	
	/**
	 * gets the player from the memento
	 * @author Andrew Abrantes
	 * @return Player state stored in memento
	 */
	public Player getSavedPlayer() { 
		return player; 
	}

}
