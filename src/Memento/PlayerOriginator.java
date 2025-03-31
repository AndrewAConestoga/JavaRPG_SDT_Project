package Memento;

import Assets.Player;

/**
 * originator class used to create memento's
 * @author Andrew Abrantes
 */
public class PlayerOriginator {
	
	private Player player;

	/**
	 * sets the player object to create memento's for
	 * @author Andrew Abrantes
	 * @param player Player to saves states of
	 */
	public void set(Player player) { 
	    this.player = player; 
	    System.out.println("Saved");
	}

	/**
	 * creates new memento of the current state of the player
	 * @author Andrew Abrantes
	 * @return PlayerMemento holding the state of the player at the current moment
	 */
	public PlayerMemento storeInMemento() { 
	    return new PlayerMemento(player); 
	}
	   
	/**
	 * loads the state of the player from a given memento
	 * @author Andrew Abrantes
	 * @param memento PlayerMemento to retrieve state from
	 * @return Player loaded in the given memento
	 */
	public Player restoreFromMemento(PlayerMemento memento) {
		   
		player = memento.getSavedPlayer(); 
		return player;
	   
	}

}
