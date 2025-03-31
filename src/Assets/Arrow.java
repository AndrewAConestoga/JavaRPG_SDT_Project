package Assets;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import Game.ClickListener;
import Game.UIImageButton;
import Game.UIObject;
import States.BattleState;

/**
 * UIObject that displays an arrow on the screen
 * @author fuelvin
 */
public class Arrow extends UIObject{
	private int i = 0;
	private float arrowX = 0;
	private ClickListener clicker;
	protected float x, y;
	protected int width, height;
	protected Rectangle bounds;
	protected boolean hovering = false;
	protected boolean moved;
	protected BufferedImage[] images;

	/**
	 * Check if the garage door is open
	 * @return a new instance of Arrow
	 * @author fuelvin
	 * @param x top left x position of where to draw object on screen
	 * @param y top left y position of where to draw object on screen
	 * @param width width of arrow in pixels on screen
	 * @param height height of arrow in pixels on screen
	 * @param images array of images to display on the screen for the arrow
	 * @param clicker ClickListener that will check when this object has been clicked on by the users mouse
	 */
	public Arrow(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker) {
		super(x, y, width, height);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.clicker = clicker;
		this.images = images;
		bounds = new Rectangle((int)x, (int)y, width, height);
		
	}

	/**
	 * called once a frame, animates the arrow to move slightly left or slightly right depending on the current frame
	 * @author fuelvin
	 */
	public void tick() {	
		i++;
		if(i <= 15) {
			arrowX += 0.2f;
		} else if(i <= 30) {
			arrowX -= 0.2f;
		} else {
			i = 0;
		}
	}

	/**
	 * draws self on the game screen
	 * @author fuelvin
	 * @param g graphics to draw to
	 */
	public void render(Graphics g) {
		g.drawImage(Asset.arrow[0], (int)(x + arrowX), (int)y, width, height, null);
	}

	/**
	 * called when this object has been clicked using the ClickListener
	 * @author fuelvin
	 */
	@Override
	public void onClick() {
		clicker.onClick();
	}
	
	/**
	 * called when the mouse position has changed
	 * detects if the mouse is hovering over this object or not
	 * @author fuelvin
	 * @param e MouseEvent automatically passed in this function as a callback called when the mouse changes position
	 */
	public void onMouseMove(MouseEvent e) {
		moved = true;
		if(bounds.contains(e.getX(), e.getY())) {
			hovering = true;
		} else {
			moved = false;
			hovering = false;
		}
	}
	
	/**
	 * called when mouse has been clicked and then released, calls onClick if released over this object
	 * @author fuelvin
	 * @param e MouseEvent automatically passed in this function as a callback called when the mouse is released
	 */
	public void onMouseRelease(MouseEvent e) {
		if((hovering || moved) && e.getButton() == MouseEvent.BUTTON1) {
			onClick();
		}
	}

}
