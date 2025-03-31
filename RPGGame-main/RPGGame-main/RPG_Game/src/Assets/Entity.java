package Assets;

import java.awt.Graphics;
import java.awt.Rectangle;

import Game.Handler;

/**
 * entity base class for controllable entities
 * @author fuelvin
 */
public abstract class Entity {
	
	protected Handler mHandler;
	protected float mX, mY;
	protected int mWidth, mHeight;
	protected Rectangle mBounds;
	
	
	/**
	 * creates a new instance of Entity
	 * @param handler Handler used to access game information from
	 * @param x x position of where to draw entity on screen
	 * @param y y position of where to draw entity on screen
	 * @param width width of entity in pixels on screen
	 * @param height height of entity in pixels on screen
	 * @author fuelvin
	 */
	public Entity(Handler handler, float x, float y, int width, int height) {
		this.mHandler = handler;
		this.mX = x;
		this.mY = y;
		this.mWidth = width;
		this.mHeight = height;
		
		this.mBounds = new Rectangle(0, 0, width, height);
	}
	
	/**
	 * function called once per frame
	 * @author fuelvin
	 */
	public abstract void tick();
	
	/**
	 * will be used to draw self on the game screen
	 * @author fuelvin
	 * @param g graphics to draw to
	 */
	public abstract void render(Graphics g);
	
	/**
	 * used to check for if entity has collided with an object
	 * @author fuelvin
	 * @param xOffset horizontal offset of the entities collision bounds
	 * @param yOffset vertical offset of the entities collision bounds
	 * @return true if entity has collided with an object in the world, false if it has not
	 */
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
	
	/**
	 * gets the collision bounds for this creature with the given offset
	 * @author fuelvin
	 * @param xOffset horizontal offset of the entities collision bounds
	 * @param yOffset vertical offset of the entities collision bounds
	 * @return rectangle with the collision dimensions for this entity
	 */
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int)(this.mX + this.mBounds.x + xOffset), (int)(this.mY + this.mBounds.y + yOffset), this.mBounds.width, this.mBounds.height);
	}
	
	/**
	 * getter for x position
	 * @author fuelvin
	 * @return x position of entity
	 */
	public float getX() {
		return this.mX;
	}
	
	/**
	 * setter for x position
	 * @author fuelvin
	 * @param x new x position of entity
	 */
	public void setX(float x) {
		this.mX = x;
	}
	
	/**
	 * getter for y position
	 * @author fuelvin
	 * @return y position of entity
	 */
	public float getY() {
		return this.mY;
	}
	
	/**
	 * setter for y position
	 * @author fuelvin
	 * @param y new y position of entity
	 */
	public void setY(float y) {
		this.mY = y;
	}
	
	/**
	 * getter for width
	 * @author fuelvin
	 * @return width of entity
	 */
	public int getWidth() {
		return this.mWidth;
	}
	
	/**
	 * setter for width
	 * @author fuelvin
	 * @param width new width of entity
	 */
	public void setWidth(int width) {
		this.mWidth = width;
	}
	
	/**
	 * getter for height
	 * @author fuelvin
	 * @return height of entity
	 */
	public int getHeight() {
		return this.mHeight;
	}
	
	/**
	 * setter for height
	 * @author fuelvin
	 * @param height new height of entity
	 */
	public void setHeight(int height) {
		this.mHeight = height;
	}
	
}
