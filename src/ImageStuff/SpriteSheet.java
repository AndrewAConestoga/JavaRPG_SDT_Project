package ImageStuff;

import java.awt.image.BufferedImage;

/**
 * class used to crop images to get render objects
 * @author fuelvin
 */
public class SpriteSheet {
	private BufferedImage sheet;
	
	
	/**
	 * creates a new spritesheet with the given image
	 * @author fuelvin
	 * @param sheet image to create spritesheet of
	 */
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}
	
	/**
	 * crops the spritesheet and returns a subset of the image
	 * @author fuelvin
	 * @param x position starting from top left of the image to start the crop
	 * @param y position starting from top left of the image to start the crop
	 * @param width width of the image to crop
	 * @param height height of the image to crop
	 * @return cropped image 
	 */
	public BufferedImage crop(int x, int y, int width, int height) {
		//returns new image of the x and y area specified
		return sheet.getSubimage(x, y, width, height); 
	}
}
