package Memento;

import Assets.Player;

public class PlayerMemento {
	
	private Player player;

	// Save a new note String to the memento Object
	public PlayerMemento(Player player) { 
		this.player = player; 
	}
	
	// Return the value stored in article 
	public Player getSavedPlayer() { 
		return player; 
	}

}
