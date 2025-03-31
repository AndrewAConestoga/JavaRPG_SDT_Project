package Game;

import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import States.BattleState;

/**
 * class for displaying clickable buttons on the game screen 
 * @author fuelvin
 */
public class UIImageButton extends UIObject{
	
	private BufferedImage[] images;
	private ClickListener clicker;

	/**
	 * creates a new instance of a UIImageButton
	 * @author fuelvin
	 * @param x top left x position of where to draw button on screen
	 * @param y top left y position of where to draw button on screen
	 * @param width width of button in pixels on screen
	 * @param height height of button in pixels on screen
	 * @param images Array of 2 images, first image is default look, 2nd image is look of button when being hovered
	 * @param clicker ClickListener to check for player input 
	 */
	public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker) {
		super(x, y, width, height);
		this.images = images;
		this.clicker = clicker;
	}

	/**
	 * called once per frame
	 * @author fuelvin
	 */
	@Override
	public void tick() {}

	
	/**
	 * draws the button on screen in its default state or hovered state if mouse is hovering over the button
	 * @author fuelvin
	 */
	@Override
	public void render(Graphics g) {
		if(hovering) {
			g.drawImage(images[1], (int) x, (int) y, width, height, null);
		} else {
			g.drawImage(images[0], (int) x, (int) y, width, height, null);
		}
	}

	/**
	 * called when button is clicked, calls the click listeners onClick()
	 * @author fuelvin
	 */
	@Override
	public void onClick() {
		clicker.onClick();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
