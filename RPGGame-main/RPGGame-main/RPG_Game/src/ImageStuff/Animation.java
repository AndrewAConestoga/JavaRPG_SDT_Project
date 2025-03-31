package ImageStuff;

import java.awt.image.BufferedImage;

/**
 * class for holding animtaiotn information for UIObjects
 * @author fuelvin
 */
public class Animation {
	
	private int speed, index;
	private long lastTime, timer;
	private BufferedImage[] frames;
	
	/**
	 * creates a new instance of Animation
	 * @author fuelvin
	 * @param speed the amount of frames in between each frame change
	 */
	public Animation(int speed, BufferedImage[] frames) {
		this.speed = speed;
		this.frames = frames;
		index = 0;
		lastTime = System.currentTimeMillis();
	}
	
	/**
	 * called once per frame, ticks down timer and changes the current frame when timer surpases speed
	 * @author fuelvin
	 */
	public void tick() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed) {
			index++;
			timer = 0;
			if(index >= frames.length) {
				index = 0;
			}
		}
	}
	
	/**
	 * gets the current frame number of the animtation
	 * @author fuelvin
	 * @return the frame number of the animtation 
	 */
	public int getIndex() {
		return index;
	}
	
	/**
	 * sets the animtation to a set frame
	 * @author fuelvin
	 * @param index number to set frame to
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	
	/**
	 * gets the current frame as an image to be displayed
	 * @author fuelvin
	 * @return image of the current frame of the animation 
	 */
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}
	
	
	
	
	
	
	
	
	

}
