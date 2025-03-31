package ImageStuff;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * class used to load image file
 * @author fuelvin
 */
public class ImageLoader {
	
	/**
	 * loads an image into the game at the specified path 
	 * @author fuelvin
	 * @param path filepath location of the image file 
	 * @return image at the specified filepath
	 */
	public static BufferedImage loadImage(String path) {
		try {
			//load in image
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
}
