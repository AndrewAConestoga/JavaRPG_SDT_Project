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

public class Player extends Creature {

	private Animation animDown, animUp, animLeft, animRight;
	public int mDir = 1;
	public float xPosition;
	public float yPosition;
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

	public Player(float x, float y, Handler handler) {
		super(handler, x, y, Creature.PLAYER_WIDTH, Creature.PLAYER_HEIGHT);

		this.mBounds.x = 0;
		this.mBounds.height = 35;
		this.mBounds.y = PLAYER_HEIGHT - this.mBounds.height - 1;
		this.mBounds.width = 43;

		health = 100;
		baseHealth = 100;
		level = 1;
		name = "Fuelvin";

		animDown = new Animation(120, Assets.player_down);
		animUp = new Animation(120, Assets.player_up);
		animLeft = new Animation(120, Assets.player_left);
		animRight = new Animation(120, Assets.player_right);
		
		this.inputUp = new PlayerInput(new PlayerMoveCommand(this, Direction.Up));
		this.inputDown = new PlayerInput(new PlayerMoveCommand(this, Direction.Down));
		this.inputLeft = new PlayerInput(new PlayerMoveCommand(this, Direction.Left));
		this.inputRight = new PlayerInput(new PlayerMoveCommand(this, Direction.Right));
		this.inputShowHitboxes = new PlayerInput(new PlayerShowHitboxesCommand(this));
		this.inputRun = new PlayerInput(new PlayerRunCommand(this));

	}
	
	public String toString() {
		return this.mDir + "," + xPosition + "," + yPosition + "," + health + "," + baseHealth + "," + level + "," + coins + "," + xp + "," + name + "," + flag + "," + flag2 + "," + flag3 + "," + a + "," + b;
	}

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

	private void checkEncounter() {
		World w = this.mHandler.getWorld();
		if ((w.getTile(w.getSpawnX() + ((int) Creature.xPosition) / 64,
				w.getSpawnY() + ((int) Creature.yPosition) / 64) == Tile.bush) &&
				!BattleState.encounterFlag &&
				Math.random() >= 0.99)
			{
			a = w.getSpawnX() + ((int) Creature.xPosition) / 64;
			b = w.getSpawnY() + ((int) Creature.yPosition) / 64;
			if (!flag3) {
				flag3 = true;
				Game.sFlag = true;
			}
		} else if (BattleState.encounterFlag) {
			if (a != w.getSpawnX() + ((int) Creature.xPosition) / 64
					|| b != w.getSpawnY() + ((int) Creature.yPosition) / 64) {
				BattleState.encounterFlag = false;
				flag3 = false;
			}
		}
	}

	public void setDir(int dir) {
		this.mDir = dir;
	}
}
