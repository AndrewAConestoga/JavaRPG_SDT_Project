package States;

import java.awt.Color;

import java.awt.Graphics;
import java.util.ArrayList;

import Assets.Arrow;
import Assets.Asset;
import Assets.AttackBarManager;
import Assets.Bar;
import Assets.InGamePlayer;
import Assets.Monster;
import Assets.MonsterInterface;
import Assets.Transition;
import Decorator.HealthyMonster;
import Decorator.StrongMonster;
import Game.Handler;
import Game.UIImageButton;
import Game.UIManager;
import Game.ClickListener;
import Game.Game;

/**
 * class that holds the information about the battle state where player fights a monster
 * @author fuelvin
 */
public class BattleState extends State{
	Handler handler;
	Bar bar;
	ArrayList<MonsterInterface> monsters = new ArrayList<>();
	MonsterInterface monster;
	InGamePlayer inGamePlayer;
	AttackBarManager attackBarManager;	
	private UIManager uiManager;
	private UIManager arrowManager;
	public static boolean encounterFlag;
	public static boolean showBars;
	public static boolean playerAttack;
	public static boolean changedTurn;
	public static boolean switchGameStates;
	public int MONSTER_COUNT = 6;

	/**
	 * creates a new instance of BattleState
	 * @author fuelvin
	 * @param handler Handler to access game information from
	 */
	public BattleState(Handler handler) {
		super(handler);
		showBars = false;
		playerAttack = true;
		this.handler = handler;
		
		
		int randomMonster = (int) (Math.random() * MONSTER_COUNT);
		
		MonsterInterface m = new Monster("Bad Radish", Asset.monsters[1], 23 * 4, 41 * 4, 480, 110, 10, 20, 1, handler);
		if (randomMonster==0) {
			m = new Monster("Bad Radish", Asset.monsters[1], 23 * 4, 41 * 4, 480, 110, 10, 20, 1, handler);
			monsters.add(m);
			System.out.println("Added regular radish");
		}
		else if (randomMonster==1) {
			m = new Monster("Bad Radish", Asset.monsters[1], 23 * 4, 41 * 4, 480, 110, 10, 20, 1, handler);
			m = new HealthyMonster(m, 20);
			monsters.add(m);
			System.out.println("Added healthy radish");
		}
		else if (randomMonster==2) {
			m = new Monster("Bad Radish", Asset.monsters[1], 23 * 4, 41 * 4, 480, 110, 10, 20, 1, handler);
			m = new StrongMonster(m, 10);
			monsters.add(m);
			System.out.println("Added strong radish");
		}
		else if (randomMonster==3) {
			monsters.add(new Monster("Green Slime", Asset.monsters[0], 38 * 4, 27 * 4, 440, 160, 30, 20, 1, handler));
			System.out.println("Added green slime");
		}
		else if (randomMonster==4) {
			m = new Monster("Green Slime", Asset.monsters[0], 38 * 4, 27 * 4, 440, 160, 30, 20, 1, handler);
			m = new HealthyMonster(m, 40);
			monsters.add(m);
			System.out.println("Added healthy slime");
		}
		else {
			m = new Monster("Green Slime", Asset.monsters[0], 38 * 4, 27 * 4, 440, 160, 30, 20, 1, handler);
			m = new StrongMonster(m, 10);
			monsters.add(m);
			System.out.println("Added strong slime");
		}
		
		monster = m;
		inGamePlayer = new InGamePlayer(handler);
		attackBarManager = new AttackBarManager(handler);
		switchGameStates = false;
		bar = new Bar(handler, monster);
		initializeUI();
	}

	private boolean f;
	
	@Override
	/**
	 * called once per frame, calls the tick function for the battle state UIObjects
	 * @author fuelvin
	 */
	public void tick() {
		if(showBars) {
			bar.tick();
			attackBarManager.tick();
		} 
		uiManager.tick();
		monster.tick();
		inGamePlayer.tick();
		
		if(Monster.deathState != 0) {
			if(!f) {
				f = true;
				handler.getMouseManager().setUIManager(arrowManager);
			}
			arrowManager.tick();
			if(switchGameStates) {
				switchGameStates = false;
				switchToGameState();
				destroy();
			}
		}
		
		
	}

	/**
	 * draws the battle state to the screen
	 * @author fuelvin
	 * @param g graphics screen to draw state to
	 */
	@Override
	public void render(Graphics g) {
		drawBackground(g);
		monster.render(g);
		inGamePlayer.render(g);
		
		if(showBars) {
			bar.render(g);
			attackBarManager.render(g);
			if(Monster.deathState < 2) {
				drawSquares(g);
			}
		} else {
			uiManager.render(g);
		}

		if(Monster.deathState <= 4 && Monster.deathState >= 2) {
			arrowManager.render(g);
		}
		
		if(Monster.deathState == 5) {
			switchToGameState();
			Monster.deathState = 0;
		}
		
	}
	
	/**
	 * swtiches the games context to the game state after battle is complete
	 * @author fuelvin
	 */
	private void switchToGameState() {
		if (Game.sPlayer.health > 0) {
			handler.savePlayer();
		}
		encounterFlag = true;
		handler.getMouseManager().setUIManager(null);
		Transition.canStart = false;
		Game.sFlag2 = false;
		State.setState(handler.getGame().mGameState);
		BattleState.switchGameStates = true;
		destroy();
	}

	/**
	 * initializes all the UIObjects that are displayed during the battle state
	 * @author fuelvin
	 */
	private void initializeUI() {
		uiManager = new UIManager(handler);
		arrowManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(50, 680, 49 * 4, 17 * 4, Asset.attackButton, new ClickListener() {
			@Override
			public void onClick() {
				showBars = true;
			}
			}));
		
		uiManager.addObject(new UIImageButton(300, 680, 49 * 4, 17 * 4, Asset.statsButton, new ClickListener() {
			@Override
			public void onClick() {
				if(!showBars) {
					
				}
			}
			}));
		
		uiManager.addObject(new UIImageButton(550, 680, 49 * 4, 17 * 4, Asset.escapeButton, new ClickListener() {
			@Override
			public void onClick() {
				if(!showBars) {
					switchToGameState();
					destroy();
				}
			}
			}));
		
		arrowManager.addObject(new Arrow(715, 728, 12 * 2, 12 * 2, Asset.arrow, new ClickListener() {
			@Override
			public void onClick() {
				if(Monster.deathState <= 4 && Monster.deathState >= 2) {
					Monster.deathState++;
				}
			}
			
			}));
		
		
	}
	
	/**
	 * draws background on the screen
	 * @author fuelvin
	 * @param g graphics screen to draw background on
	 */
	private void drawBackground(Graphics g) {
		Color c = new Color(0, 203, 3);
		g.setColor(c);
		g.fillRect(0, 0, 800, 800);
		g.drawImage(Asset.barGrass, -75, 524, 110 * 8, 85 * 4, null);
		
		//c = new Color(147, 147, 147);
		//g.setColor(c);
		//g.fillRect(25, 550, 750, 230);
		g.drawImage(Asset.greySquare, 28, 558, 185 * 4, 55 * 4, null);
		g.drawImage(Asset.grassPlatform, 356, 180, 86 * 4, 37 * 4, null);
	}
	
	/**
	 * draws squares to the screen
	 * @author fuelvin
	 * @param g graphics screen to draw squares on
	 */
	private void drawSquares(Graphics g) {
		Color d = new Color(184, 184, 184);
		g.setColor(d);
		g.fillRect(32, Bar.height, 68, 50);
		d = new Color(73, 73, 73);
		g.setColor(d);
		g.fillRect(28, Bar.height, 4, 50);
		d = new Color(5, 169, 6);
		g.setColor(d);
		g.fillRect(0, Bar.height, 28, 50);
		
		d = new Color(184, 184, 184);
		g.setColor(d);
		g.fillRect(700, Bar.height, 64, 50);
		d = new Color(73, 73, 73);
		g.setColor(d);
		g.fillRect(764, Bar.height, 4, 50);
		d = new Color(5, 169, 6);
		g.setColor(d);
		g.fillRect(768, Bar.height, 28, 50);
		
		d = new Color(0, 124, 1);
		g.setColor(d);
		g.fillRect(796, Bar.height, 4, 50);

	}
	
	/**
	 * called when game switches away from battle state, destroys the attackBarManager
	 * @author fuelvin
	 */
	public void destroy() {
		attackBarManager.destroy();
	}
	
	

}









