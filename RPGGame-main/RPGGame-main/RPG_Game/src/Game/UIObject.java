package Game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import States.BattleState;

/**
 * general class to be used for all UIObjects to be drawn and interacted with on the screen 
 * @author fuelvin
 */
public abstract class UIObject {

	protected float x, y;
	protected int width, height;
	protected Rectangle bounds;
	protected boolean hovering = false;
	protected boolean moved;

	/**
	 * creates a new instance of UIObject
	 * @author fuelvin
	 * @param x top left x position of where to draw object on screen
	 * @param y top left y position of where to draw object on screen
	 * @param width width of object in pixels on screen
	 * @param height height of object in pixels on screen
	 */
	public UIObject(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle((int) x, (int) y, width, height);
	}

	/**
	 * to be called once per frame
	 * @author fuelvin
	 */
	public abstract void tick();

	/**
	 * to be called to draw object to the screen 
	 * @author fuelvin
	 * @param g graphics screen to draw object to 
	 */
	public abstract void render(Graphics g);

	/**
	 * to be called when this object is clicked
	 * @author fuelvin
	 */
	public abstract void onClick();

	/**
	 * called when mouse moves to check if it is hovering over this object
	 * @author fuelvin
	 * @param e mouse event of mouse movement
	 */
	public void onMouseMove(MouseEvent e) {
		moved = true;
		if (bounds.contains(e.getX(), e.getY())) {
			hovering = true;
		} else {
			moved = false;
			hovering = false;
		}
	}

	/**
	 * calls onClick() when the mouse button is released after clicking this object
	 * @author fuelvin
	 * @param e mouse event of mouse click
	 */
	public void onMouseRelease(MouseEvent e) {
		if ((hovering || moved) && e.getButton() == MouseEvent.BUTTON1) {
			onClick();
		}
	}

	/**
	 * getter for X
	 * @author fuelvin
	 * @return the x position of the UIObject
	 */
	public float getX() {
		return x;
	}

	/**
	 * setter for x
	 * @author fuelvin
	 * @param x new x of this object
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * getter for Y
	 * @author fuelvin
	 * @return the y position of the UIObject
	 */
	public float getY() {
		return y;
	}

	/**
	 * setter for y
	 * @author fuelvin
	 * @param y new y of this object
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * getter for width
	 * @author fuelvin
	 * @return width in pixels of the object
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * setter for width
	 * @author fuelvin
	 * @param width new width of this object
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * getter for height
	 * @author fuelvin
	 * @return height in pixels of the object
	 */
	public int getHieght() {
		return height;
	}

	/**
	 * setter for height
	 * @author fuelvin
	 * @param height new height of this object
	 */
	public void setHieght(int hieght) {
		this.height = hieght;
	}

	/**
	 * getter for hovering
	 * @author fuelvin
	 * @return true if mouse is hovering over this object, false if it is not
	 */
	public boolean isHovering() {
		return hovering;
	}

	/**
	 * setter for hovering
	 * @author fuelvin
	 * @param hovering sets the hovering value to this
	 */
	public void setHovering(boolean hovering) {
		this.hovering = hovering;
	}

}
