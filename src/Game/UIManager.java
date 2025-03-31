package Game;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * class for managing the UI of the game screen  
 * @author fuelvin
 */
public class UIManager {
	
	private Handler handler;
	private ArrayList<UIObject> objects;
	
	/**
	 * creates a new instance of a UIManager
	 * @author fuelvin
	 * @param handler Handler to access game information from
	 */
	public UIManager(Handler handler) {
		this.setHandler(handler);
		objects = new ArrayList<UIObject>();
	}
	
	/**
	 * called once per frame, calls the tick function of all the UIObjects in this container
	 * @author fuelvin
	 */
	public void tick() {
		for(UIObject o : objects) {
			o.tick();
		}
	}
	
	/**
	 * renders all the UIObjects in this container to the game screen
	 * @author fuelvin
	 * @param g graphics screen to draw the images to 
	 */
	public void render(Graphics g) {
		for(UIObject o : objects) {
			o.render(g);
		}
	}
	
	/**
	 * called when the player moves the mouse to a new position, calls onMouseMove of all UIObjects
	 * @author fuelvin
	 * @param e MouseEvent of player moving the mouse
	 */
	public void onMouseMove(MouseEvent e) {
		for(UIObject o : objects) {
			o.onMouseMove(e);
		}
	}
	
	/**
	 * called when the player releases the mouse, calls onMouseRelease of all UIObjects
	 * @author fuelvin
	 * @param e MouseEvent of player releasing the mouse
	 */
	public void onMouseRelease(MouseEvent e) {
		for(UIObject o : objects) {
			o.onMouseRelease(e);
		}
	}
	
	/**
	 * adds object to this manager
	 * @author fuelvin
	 * @param o UIObject to add to the list
	 */
	public void addObject(UIObject o) {
		objects.add(o);
	}
	
	/**
	 * removes objects from the list
	 * @author fuelvin
	 * @param o UIObject to remove from the list
	 */
	public void removeObject(UIObject o) {
		objects.remove(o);
	}

	/**
	 * getter for handler
	 * @author fuelvin
	 * @return the handler of this object
	 */
	public Handler getHandler() {
		return handler;
	}

	/**
	 * setter for handler
	 * @author fuelvin
	 * @param handler new Handler to set handler to
	 */
	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
