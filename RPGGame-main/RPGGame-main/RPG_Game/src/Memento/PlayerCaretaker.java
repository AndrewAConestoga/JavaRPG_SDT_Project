package Memento;

import java.util.ArrayList;

public class PlayerCaretaker {
	
		ArrayList<PlayerMemento> savedPlayers = new ArrayList<PlayerMemento>();
		
		public void addMemento(PlayerMemento m) { 
			savedPlayers.add(m); 
		}
	   
		public PlayerMemento getMemento(int index) { 
			return savedPlayers.get(index);
		}
		
		public PlayerMemento getLatestMemento() {
			return savedPlayers.get(savedPlayers.size() - 1);
		}

}
