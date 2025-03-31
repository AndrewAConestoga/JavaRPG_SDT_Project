package Assets;

import Game.Handler;

public abstract class Creature extends Entity {
	
	public static final int DEFAULT_HEALTH = 10;
	public static final float DEFAULT_SPEED = 4.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64;
	public static final int DEFAULT_CREATURE_HEIGHT = 64;
	public static final int PLAYER_WIDTH = 48;
	public static final int PLAYER_HEIGHT = 80;

	public int health;
	public float speed;
	public static float xMove, yMove;
	public static float xPosition, yPosition;
	public static boolean collided = false;
	
	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
		
	}
	
	public void move() {
		if(xMove != 0 && !checkEntityCollisions(xMove, 0f)) {
			moveX();
		}
		if(yMove != 0 && !checkEntityCollisions(0f, yMove)) {
			moveY();
		}
	}
	
	public void moveX() {
		if(xMove > 0) {
			collided = false;
			int tx = (int) (this.mX + xMove + this.mBounds.x + this.mBounds.width) / Tile.TILEWIDTH;
			if(!collisionWithTile(tx, (int) (this.mY + this.mBounds.y) / Tile.TILEHEIGHT) && !collisionWithTile(tx, (int) (this.mY + this.mBounds.y + this.mBounds.height) / Tile.TILEHEIGHT)){
				this.mX += xMove;
				xPosition += xMove;
				//System.out.println("x added to xMove:" + x + " xPosition=" + xPosition);
			} else {
				this.mX = tx * Tile.TILEWIDTH + this.mBounds.x - this.mBounds.width - 1;
			}
		} else if(xMove < 0) {	
			collided = false;
			int tx = (int) (this.mX + xMove + this.mBounds.x) / Tile.TILEWIDTH;
			if(!collisionWithTile(tx, (int) (this.mY + this.mBounds.y) / Tile.TILEHEIGHT) && !collisionWithTile(tx, (int) (this.mY + this.mBounds.y + this.mBounds.height) / Tile.TILEHEIGHT)){
				this.mX += xMove;
				xPosition += xMove;
				//System.out.println("x added to xMove:" + x + " xPosition=" + xPosition);
			} else {
				this.mX = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - this.mBounds.x;
			}
		}
	}
	
	public void moveY() {
		if(yMove < 0) {
			collided = false;
			int ty = (int) (this.mY + yMove + this.mBounds.y) / Tile.TILEHEIGHT;
			if(!collisionWithTile((int) (this.mX + this.mBounds.x) / Tile.TILEWIDTH, ty) && !collisionWithTile((int) (this.mX + this.mBounds.x + this.mBounds.width) / Tile.TILEWIDTH, ty)) {
				this.mY += yMove;
				yPosition += yMove;
				//System.out.println("y added to yMove:" + y + " yPosition=" + yPosition);
			} else {
				collided = true;
				this.mY = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - this.mBounds.y;
			}
		} else if(yMove > 0) {
			collided = false;
			int ty = (int) (this.mY + yMove + this.mBounds.y + this.mBounds.height) / Tile.TILEHEIGHT;
			if(!collisionWithTile((int) (this.mX + this.mBounds.x) / Tile.TILEWIDTH, ty) && !collisionWithTile((int) (this.mX + this.mBounds.x + this.mBounds.width) / Tile.TILEWIDTH, ty)) {
				this.mY += yMove;
				yPosition += yMove;
				//System.out.println("y added to yMove:" + y + " yPosition=" + yPosition);
			} else {
				this.mY = ty * Tile.TILEHEIGHT - this.mBounds.y - this.mBounds.height - 1;
				collided = true;
			}
			
		}
	}
	
	protected boolean collisionWithTile(int x, int y) {
		if(this.mHandler.getWorld().getTile(x, y).isSolid()) {
			collided = true;
		}
		return this.mHandler.getWorld().getTile(x, y).isSolid();
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
}
