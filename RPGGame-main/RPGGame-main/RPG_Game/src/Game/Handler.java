package Game;

import Assets.Player;
import Memento.PlayerCaretaker;
import Memento.PlayerOriginator;

public class Handler {

	private Game game;
	private World world;
	private PlayerOriginator pOriginator = new PlayerOriginator();
	private PlayerCaretaker pCaretaker = new PlayerCaretaker();
	
	public Handler(Game game) {
		this.game = game;
	}
	
	public GameCamera getGameCamera() {
		return game.getGameCamera();
	}
	
	public KeyManager getKeymanager() {
		return game.getKeyManager();
	}
	
	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}
	
	public int getWidth() {
		return game.getWidth();
	}
	
	public int getHeight() {
		return game.getHeight();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
	
	public PlayerOriginator getPlayerOriginator() {
		return pOriginator;
	}
	
	public PlayerCaretaker getPlayerCaretaker() {
		return pCaretaker;
	}
	
	public void savePlayer() {
		pOriginator.set(Game.player);
		pCaretaker.addMemento(pOriginator.storeInMemento());
	}
	
	public Player loadPlayer() {
		return pCaretaker.getLatestMemento().getSavedPlayer();
	}
	
}
