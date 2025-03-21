package Memento;

import Assets.Player;

public class PlayerOriginator {
	
	private Player player;

	// Sets the value for the article
	
	public void set(Player player) { 
	    this.player = player; 
	    System.out.println("Saved");
	}

	// Creates a new Memento with a new note
	
	public PlayerMemento storeInMemento() { 
	    return new PlayerMemento(player); 
	}
	   
	// Gets the article currently stored in memento
	
	public Player restoreFromMemento(PlayerMemento memento) {
		   
		player = memento.getSavedPlayer(); 
		return player;
	   
	}

}
