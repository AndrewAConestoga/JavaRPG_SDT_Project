package Game;

import Assets.Entity;
import Assets.Tile;

/**
 * class for setting the game camera to display the players position
 * @author fuelvin
 */
public class GameCamera {
	
	private Handler handler;
	private float xOffset, yOffset;
	
	/**
	 * creates a new instance of GameCamera
	 * @author fuelvin
	 * @param handler Handler used to access game information from
	 * @param xOffset the x offset in pixels of the camera to display
	 * @param yOffset the y offset in pixels of the camera to display
	 */
	public GameCamera(Handler handler, float xOffset, float yOffset) {
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	/**
	 * checks to see if the camera's offset is displaying out of bounds of the current world
	 * @author fuelvin
	 */
	public void checkBlankSpace() {
		if(xOffset < 0) {
			xOffset = 0;
		} else if(xOffset > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth()) {
			xOffset = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();
		}
		
		if(yOffset < 0) {
			yOffset = 0;
		} else if(yOffset > handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight()) {
			yOffset = handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
		}
	} 
	
	/**
	 * centers the camera on a entity
	 * @author fuelvin
	 * @param e entity to center game camera on
	 */
	public void centerOnEntity(Entity e) {
		xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;
		yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
		checkBlankSpace();
	}
	
	/**
	 * shifts the game camera in the x and y position
	 * @author fuelvin
	 * @param xAmount amount of pixels to shift game camera in the x direction
	 * @param yAmount amount of pixels to shift game camera in the y direction
	 */
	public void move(float xAmount, float yAmount) {
		xOffset += xAmount;
		yOffset += yAmount;
	}

	/**
	 * getter for xOffset
	 * @author fuelvin
	 * @return the xOffset of the game camera
	 */
	public float getxOffset() {
		return xOffset;
	}

	/**
	 * setter for xOffset
	 * @author fuelvin
	 * @param xOffset sets the camera x offset to this value
	 */
	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	/**
	 * getter for yOffset
	 * @author fuelvin
	 * @return the yOffset of the game camera
	 */
	public float getyOffset() {
		return yOffset;
	}

	/**
	 * setter for yOffset
	 * @author fuelvin
	 * @param yOffset sets the camera y offset to this value
	 */
	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}
	

}
