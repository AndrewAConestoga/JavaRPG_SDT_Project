package Game; //Adding res to build path: Project, properties, add class folder, select folder.

import java.awt.Graphics;

import java.awt.image.BufferStrategy;
import Assets.Assets;
import Assets.Player;
import Assets.Transition;
import States.BattleState;
import States.GameState;
import States.MenuState;
import States.State;

/**
 * game class that handles all the logic for controlling the game and changing game states
 * @author fuelvin
 */
public class Game {
	
	private Display display;
	
	private final int mWidth;
	private final int mHeight;
	public String mTitle;
	public boolean mIsRunning = false;
	
	public static boolean sShowHitboxes = false;
	public static boolean sFlag = false;
	public static boolean sFlag2 = false;
	public static boolean sBattling = false;
	public static Player sPlayer;
	
	private BufferStrategy mBs;
	private Graphics mGraphics;
	
	//States
	public GameState mGameState;
	public State mMenuState;
	public State mBattleState;
	
	//Input
	private KeyManager mKeyManager;
	private MouseManager mMouseManager;
	
	private GameCamera mGameCamera;
	
	private Handler mHandler;
	private Transition mTransition;
	
	private int mFpsCount;
	private double mTimePerTick; 
	
	/**
	 * creates a new instance of a Game
	 * @author fuelvin
	 * @param title title of the window to be set to that will be created
	 * @param width width of the window in pixels that will be created
	 * @param height heigh of the window in pixels that will be created
	 */
	public Game(String title, int width, int height) {
		// Window info
		this.mWidth = width;
		this.mHeight = height;
		this.mTitle = title;
		this.mKeyManager = new KeyManager();
		this.mMouseManager = new MouseManager();
		
		// Init window
		display = new Display("RPG Game", this.mWidth, this.mHeight);
		display.addManagers(this.mKeyManager, this.mMouseManager);
		Assets.init();
		
		// Setup handlers
		this.mHandler = new Handler(this);
		sPlayer = new Player(0,0, this.mHandler);
		this.mGameCamera = new GameCamera(this.mHandler, 0, 0);
		
		// Create states
		this.mGameState = new GameState(this.mHandler);
		this.mMenuState = new MenuState(this.mHandler);
		// battleState = new BattleState(handler);
		State.setState(this.mGameState); //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// Other datapoints
		this.mIsRunning = true;
		this.mFpsCount = 60;
		this.mTimePerTick = 1000000000 / this.mFpsCount; //1 billion bcus 1 billion nanoseconds in one second
	}
	
	/**
	 * starts the game
	 * @author fuelvin
	 */
	public void start() {
		// Data preparation
		double delta = 0;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		// Main game loop
		while(this.mIsRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / this.mTimePerTick;
			timer += now - lastTime;
			lastTime = now;
			if(delta >= 1) {
				this.tick(); 
				this.render();
				ticks++;
				delta--;
			}
			if(timer >= 1000000000) { //if timer exceeds one second
				System.out.println("FPS: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
	}
	
	/**
	 * called once per frame, updates all the window objects
	 * @author fuelvin
	 */
	private void tick() {
		// Game ticks
		this.mKeyManager.tick();
		if(State.getState() != null) {
			State.getState().tick();
		}
		
		// Check whatever this flag does
		if(sFlag) {
			sFlag = false;
			this.mTransition = new Transition();
			sFlag2 = true;
		}

		// Idk man, transitions somehow
		if(Transition.canStart) {
			Transition.canStart = false;
			sBattling = true;
			this.mBattleState = new BattleState(this.mHandler);
			State.setState(this.mHandler.getGame().mBattleState);
		}
	}
	
	/**
	 * renders the state of all the game objects in the current context to the screen
	 * @author fuelvin
	 */
	private void render() {
		this.mBs = display.getCanvas().getBufferStrategy();
		if(this.mBs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		this.mGraphics = this.mBs.getDrawGraphics();
		
		//Clears certain portion of screen (in this case the whole screen)
		this.mGraphics.clearRect(0, 0, this.mWidth, this.mHeight);
		
		//Draws stuff in the screen-
		if(State.getState() != null) {
			State.getState().render(this.mGraphics);
		}
		
		if(sFlag2) {
			this.mTransition.render(this.mGraphics);
		}
		
		//End drawings-
		this.mBs.show();
		this.mGraphics.dispose();
	}

	
	/**
	 * getter for KeyManager
	 * @author fuelvin
	 * @return the games KeyManager
	 */
	public KeyManager getKeyManager() {
		return this.mKeyManager;
	}
	
	/**
	 * getter for MouseManager
	 * @author fuelvin
	 * @return the games MouseManager
	 */
	public MouseManager getMouseManager() {
		return this.mMouseManager;
	}
	
	/**
	 * getter for GameCamera
	 * @author fuelvin
	 * @return the games GameCamera
	 */
	public GameCamera getGameCamera() {
		return this.mGameCamera;
	}
	
	/**
	 * getter for width
	 * @author fuelvin
	 * @return the width of the screen in integer pixels
	 */
	public int getWidth() {
		return this.mWidth;
	}
	
	/**
	 * getter for height
	 * @author fuelvin
	 * @return the height of the screen in integer pixels
	 */
	public int getHeight() {
		return this.mHeight;
	}	
}
