package Memento;

import java.util.ArrayList;

/**
 * caretaker class to hold memento's for the player
 * @author Andrew Abrantes
 */
public class PlayerCaretaker {
	
		ArrayList<PlayerMemento> savedPlayers = new ArrayList<PlayerMemento>();
		
		/**
		 * adds a new memento state to the saved memento's
		 * @author Andrew Abrantes
		 * @param m PlayerMemento to add
		 */
		public void addMemento(PlayerMemento m) { 
			savedPlayers.add(m); 
		}
	   
		/**
		 * retrieves a specified memento
		 * @author Andrew Abrantes
		 * @param index index of memento in the list to retrieve
		 * @return PlayerMemento at position index
		 */
		public PlayerMemento getMemento(int index) { 
			return savedPlayers.get(index);
		}
		
		/**
		 * retrieves the most recently saved memento
		 * @author Andrew Abrantes
		 * @return most recently saved PlayerMemento
		 */
		public PlayerMemento getLatestMemento() {
			return savedPlayers.get(savedPlayers.size() - 1);
		}

}
