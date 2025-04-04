
package Assets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Command.PlayerInput;
import Command.PlayerMoveCommand;
import Command.PlayerMoveCommand.Direction;
import Command.PlayerRunCommand;
import Command.PlayerShowHitboxesCommand;
import Game.Game;
import Game.Handler;
import Game.World;
import ImageStuff.Animation;
import States.BattleState;

/**
 * Player class that stores all the information about the player and is used to get draw and get input from the user
 * @author fuelvin
 */
public class Player extends Creature {

	private Animation animDown, animUp, animLeft, animRight;
	public int mDir = 1;
	public int health;
	public int baseHealth;
	public int level;
	public int coins;
	public int xp;
	public String name;

	public boolean flag;
	public boolean flag2;
	public boolean flag3;
	private int a;
	private int b;
	
	private PlayerInput inputUp;
	private PlayerInput inputDown;
	private PlayerInput inputLeft;
	private PlayerInput inputRight;
	private PlayerInput inputShowHitboxes;
	private PlayerInput inputRun;

	/**
	 * creates a new instance of Player
	 * @author fuelvin
	 * @param x top left x position of where to draw player on screen
	 * @param y top left y position of where to draw player on screen
	 * @param handler Handler used to access game information from
	 */
	public Player(float x, float y, Handler handler) {
		super(handler, x, y, Creature.PLAYER_WIDTH, Creature.PLAYER_HEIGHT);

		// Collision setup
		this.mBounds.x = 0;
		this.mBounds.height = 35;
		this.mBounds.y = PLAYER_HEIGHT - this.mBounds.height - 1;
		this.mBounds.width = 43;

		// Player stuff
		health = 100;
		baseHealth = 100;
		level = 1;
		name = "Fuelvin";

		animDown = new Animation(120, Asset.player_down);
		animUp = new Animation(120, Asset.player_up);
		animLeft = new Animation(120, Asset.player_left);
		animRight = new Animation(120, Asset.player_right);
		
		this.inputUp = new PlayerInput(new PlayerMoveCommand(this, Direction.Up));
		this.inputDown = new PlayerInput(new PlayerMoveCommand(this, Direction.Down));
		this.inputLeft = new PlayerInput(new PlayerMoveCommand(this, Direction.Left));
		this.inputRight = new PlayerInput(new PlayerMoveCommand(this, Direction.Right));
		this.inputShowHitboxes = new PlayerInput(new PlayerShowHitboxesCommand(this));
		this.inputRun = new PlayerInput(new PlayerRunCommand(this));
	}
	
	/**
	 * creates a new instance of Player with a given player
	 * @author Andrew Abrantes
	 * @param p player to copy values from
	 */
	public Player(Player p) {
		
		super(p.mHandler, p.getX(), p.getY(), Creature.PLAYER_WIDTH, Creature.PLAYER_HEIGHT);

		// Collision setup
		this.mBounds.x = 0;
		this.mBounds.height = 35;
		this.mBounds.y = PLAYER_HEIGHT - this.mBounds.height - 1;
		this.mBounds.width = 43;
		
		this.health = p.health;
		this.baseHealth = p.baseHealth;
		this.level = p.level;
		name = "Fuelvin";

		animDown = new Animation(120, Asset.player_down);
		animUp = new Animation(120, Asset.player_up);
		animLeft = new Animation(120, Asset.player_left);
		animRight = new Animation(120, Asset.player_right);
		
		this.inputUp = new PlayerInput(new PlayerMoveCommand(this, Direction.Up));
		this.inputDown = new PlayerInput(new PlayerMoveCommand(this, Direction.Down));
		this.inputLeft = new PlayerInput(new PlayerMoveCommand(this, Direction.Left));
		this.inputRight = new PlayerInput(new PlayerMoveCommand(this, Direction.Right));
		this.inputShowHitboxes = new PlayerInput(new PlayerShowHitboxesCommand(this));
		this.inputRun = new PlayerInput(new PlayerRunCommand(this));
		
	}
	
	/**
	 * returns the string represention of player
	 * @author fuelvin
	 * @return string represention of player showing all its values in a csv file
	 */
	public String toString() {
		return this.mDir + "," + xPosition + "," + yPosition + "," + health + "," + baseHealth + "," + level + "," + coins + "," + xp + "," + name + "," + flag + "," + flag2 + "," + flag3 + "," + a + "," + b;
	}

	/**
	 * called once per frame
	 * @author fuelvin
	 */
	@Override
	public void tick() {
		if (!Game.sFlag2) {
			animDown.tick();
			animUp.tick();
			animRight.tick();
			animLeft.tick();
			getInput();
			move();
			checkEncounter();
		}
		this.mHandler.getGameCamera().centerOnEntity(this);
	}

	/**
	 * checks for user input and executes according command
	 * @author Andrew Abrantes
	 */
	private void getInput() {
		xMove = 0;
		yMove = 0;

		if (this.mHandler.getMouseManager().isRightPressed()) {
			this.inputRun.execute();
		} 
		else if (!this.mHandler.getMouseManager().isRightPressed() && flag) {
			this.inputRun.unexecute();
		}

		if (this.mHandler.getKeymanager().q) {
			this.inputShowHitboxes.execute();
		} 
		else if (!this.mHandler.getKeymanager().q && flag2) {
			this.inputShowHitboxes.unexecute();
		}

		if (this.mHandler.getKeymanager().up || this.mHandler.getKeymanager().Up) {
			this.inputUp.execute();
			return;
		}
		if (this.mHandler.getKeymanager().down || this.mHandler.getKeymanager().Down) {
			this.inputDown.execute();
			return;
		}
		if (this.mHandler.getKeymanager().left || this.mHandler.getKeymanager().Left) {
			this.inputLeft.execute();
			return;
		}
		if (this.mHandler.getKeymanager().right || this.mHandler.getKeymanager().Right) {
			this.inputRight.execute();
			return;
		}

	}

	/**
	 * draws self on the game screen
	 * @author fuelvin
	 * @param g graphics to draw to
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (this.mX - this.mHandler.getGameCamera().getxOffset()),
				(int) (this.mY - this.mHandler.getGameCamera().getyOffset()), this.mWidth, this.mHeight, null);
		if (Game.sShowHitboxes) {
			g.setColor(Color.red);
			g.drawRect((int) (this.mX + this.mBounds.x - this.mHandler.getGameCamera().getxOffset()),
					(int) (this.mY + this.mBounds.y - this.mHandler.getGameCamera().getyOffset()), this.mBounds.width, this.mBounds.height);
		}
	}

	/**
	 * getter for current animation frame
	 * @author fuelvin
	 * @return the current frame of animation to be used by render to display on the screen
	 */
	private BufferedImage getCurrentAnimationFrame() {
		// Check positional based items
		if (xMove < 0) {
			return animLeft.getCurrentFrame();
		} else if (xMove > 0) {
			return animRight.getCurrentFrame();
		} else if (yMove < 0) {
			return animUp.getCurrentFrame();
		} else if (yMove > 0) {
			return animDown.getCurrentFrame();
		}

		// Check directional items if positional fails
		if (this.mDir == 0) {
			animDown.setIndex(0);
			return animDown.getCurrentFrame();
		} else if (this.mDir == 1) {
			animUp.setIndex(0);
			return animUp.getCurrentFrame();
		} else if (this.mDir == 2) {
			animLeft.setIndex(0);
			return animLeft.getCurrentFrame();
		} else {
			animRight.setIndex(0);
			return animRight.getCurrentFrame();
		}
	}

	/**
	 * used to check if player should encounter an enemy when walking in tall grass
	 * @author fuelvin
	 */
	private void checkEncounter() {
		World w = this.mHandler.getWorld();
		if ((w.getTile(w.getSpawnX() + ((int) this.xPosition) / 64,
				w.getSpawnY() + ((int) this.yPosition) / 64) == Tile.bush) &&
				!BattleState.encounterFlag &&
				Math.random() >= 0.99)
			{
			a = w.getSpawnX() + ((int) this.xPosition) / 64;
			b = w.getSpawnY() + ((int) this.yPosition) / 64;
			if (!flag3) {
				flag3 = true;
				Game.sFlag = true;
			}
		} else if (BattleState.encounterFlag) {
			if (a != w.getSpawnX() + ((int) this.xPosition) / 64
					|| b != w.getSpawnY() + ((int) this.yPosition) / 64) {
				BattleState.encounterFlag = false;
				flag3 = false;
			}
		}
	}

	/**
	 * setter for dir
	 * @author fuelvin
	 * @param dir sets the direction for the player to be facing in
	 */
	public void setDir(int dir) {
		this.mDir = dir;
	}
	
	/**
	 * levels up the player increasing their hp and reseting their xp to 0
	 * @author fuelvin
	 * @param dir sets the direction for the player to be facing in
	 */
	public void levelUp() {
		this.level++;
		this.xp = 0;
		this.baseHealth = this.baseHealth + (this.level*10);
		this.health=this.baseHealth;
	}
}
