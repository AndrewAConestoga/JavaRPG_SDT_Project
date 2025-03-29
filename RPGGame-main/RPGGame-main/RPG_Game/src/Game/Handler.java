package Game;

import Assets.Player;
import Memento.PlayerCaretaker;
import Memento.PlayerOriginator;

public class Handler {

	private Game mGame;
	private World mWorld;
	private PlayerOriginator mPlayerOriginator = new PlayerOriginator();
	private PlayerCaretaker mPlayerCaretaker = new PlayerCaretaker();
	
	public Handler(Game game) {
		this.mGame = game;
	}
	
	public GameCamera getGameCamera() {
		return this.mGame.getGameCamera();
	}
	
	public KeyManager getKeymanager() {
		return this.mGame.getKeyManager();
	}
	
	public MouseManager getMouseManager() {
		return this.mGame.getMouseManager();
	}
	
	public int getWidth() {
		return this.mGame.getWidth();
	}
	
	public int getHeight() {
		return this.mGame.getHeight();
	}

	public Game getGame() {
		return this.mGame;
	}

	public void setGame(Game game) {
		this.mGame = game;
	}

	public World getWorld() {
		return this.mWorld;
	}

	public void setWorld(World world) {
		this.mWorld = world;
	}
	
	public PlayerOriginator getPlayerOriginator() {
		return this.mPlayerOriginator;
	}
	
	public PlayerCaretaker getPlayerCaretaker() {
		return this.mPlayerCaretaker;
	}
	
	public void savePlayer() {
		this.mPlayerOriginator.set(Game.sPlayer);
		this.mPlayerCaretaker.addMemento(this.mPlayerOriginator.storeInMemento());
	}
	
	public Player loadPlayer() {
		return this.mPlayerCaretaker.getLatestMemento().getSavedPlayer();
	}
	
}
