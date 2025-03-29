package Assets;

import java.awt.Graphics;
import java.awt.Rectangle;

import Game.Handler;

public abstract class Entity {
	
	protected Handler mHandler;
	protected float mX, mY;
	protected int mWidth, mHeight;
	protected Rectangle mBounds;
	
	
	public Entity(Handler handler, float x, float y, int width, int height) {
		this.mHandler = handler;
		this.mX = x;
		this.mY = y;
		this.mWidth = width;
		this.mHeight = height;
		
		this.mBounds = new Rectangle(0, 0, width, height);
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		for(Entity e : this.mHandler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this)) {
				continue;
			}
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
				Creature.collided = true;
				return true;
			}
		}
		return false;
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int)(this.mX + this.mBounds.x + xOffset), (int)(this.mY + this.mBounds.y + yOffset), this.mBounds.width, this.mBounds.height);
	}
	
	public float getX() {
		return this.mX;
	}
	
	public void setX(float x) {
		this.mX = x;
	}
	
	public float getY() {
		return this.mY;
	}
	
	public void setY(float y) {
		this.mY = y;
	}
	
	public int getWidth() {
		return this.mWidth;
	}
	
	public void setWidth(int width) {
		this.mWidth = width;
	}
	
	public int getHeight() {
		return this.mHeight;
	}
	
	public void setHeight(int height) {
		this.mHeight = height;
	}
	
}
