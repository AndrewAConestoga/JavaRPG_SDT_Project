package Assets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import Game.Game;
import Game.Handler;
import Game.World;
import ImageStuff.Animation;
import States.BattleState;

public class Player extends Creature {

	private Animation animDown, animUp, animLeft, animRight;
	private int mDir = 1;
	public float xPosition;
	public float yPosition;
	public int health;
	public int baseHealth;
	public int level;
	public int coins;
	public int xp;
	public String name;

	private boolean flag;
	private boolean flag2;
	private boolean flag3;
	private int a;
	private int b;

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

		if (this.mHandler.getMouseManager().isRightPressed() && !flag) {
			flag = true;
			if (this.speed == 16.0f) {
				this.speed = 4.0f;
			} else {
				this.speed = 16.0f;
			}
		} else if (!this.mHandler.getMouseManager().isRightPressed() && flag) {
			flag = false;
		}

		if (this.mHandler.getKeymanager().q && !flag2) {
			flag2 = true;
			Game.sShowHitboxes = !Game.sShowHitboxes;
		} else if (!this.mHandler.getKeymanager().q && flag2) {
			flag2 = false;
		}

		if (this.mHandler.getKeymanager().up || this.mHandler.getKeymanager().Up) {
			yMove = -speed;
			this.mDir = 1;
			return;
		}
		if (this.mHandler.getKeymanager().down || this.mHandler.getKeymanager().Down) {
			yMove = speed;
			this.mDir = 0;
			return;
		}
		if (this.mHandler.getKeymanager().left || this.mHandler.getKeymanager().Left) {
			xMove = -speed;
			this.mDir = 2;
			return;
		}
		if (this.mHandler.getKeymanager().right || this.mHandler.getKeymanager().Right) {
			xMove = speed;
			this.mDir = 3;
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
