package Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * class used to listen for mouse inputs and track mouse position from the user
 * @author fuelvin
 */
public class MouseManager implements MouseListener, MouseMotionListener{
	
	private boolean leftPressed, rightPressed;
	private int mouseX, mouseY;
	private UIManager uiManager;
	
	/**
	 * setter for UIManager
	 * @author fuelvin
	 * @param ui manager to listen for key events on
	 */
	public void setUIManager(UIManager uiManager) {
		this.uiManager = uiManager;
	}
	
	/**
	 * getter for leftPresses
	 * @author fuelvin
	 * @return true if left mouse button is currently pressed down, false if it is not
	 */
	public boolean isLeftPressed() {
		return leftPressed;
	}

	/**
	 * getter for rightPressed
	 * @author fuelvin
	 * @return true if right mouse button is currently pressed down, false if it is not
	 */
	public boolean isRightPressed() {
		return rightPressed;
	}
	
	/**
	 * getter for mouseX
	 * @author fuelvin
	 * @return x position of mouse of the screen 
	 */
	public int getMouseX() {
		return mouseX;
	}
	
	/**
	 * getter for mouseY
	 * @author fuelvin
	 * @return y position of mouse of the screen 
	 */
	public int getMouseY() {
		return mouseY;
	}
	
	/**
	 * unimplemented method, called when mouse drags into an area
	 * @author fuelvin
	 * @param e mouse event 
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * sets the x and y position of mouse when the user moves the mouse
	 * @author fuelvin
	 * @param e mouse event used to determine position of mouse
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
		if(uiManager != null) {
			uiManager.onMouseMove(e);
		}
	}

	/**
	 * unimplemented method, called when mouse is clicked
	 * @author fuelvin
	 * @param e mouse event 
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * unimplemented method, called when mouse enters area
	 * @author fuelvin
	 * @param e mouse event 
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * unimplemented method, called when mouse exits area
	 * @author fuelvin
	 * @param e mouse event 
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * called when the mouse is pressed and determines which mouse button was pressed
	 * @author fuelvin
	 * @param e mouse event used to determine button pressed
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) { //if left mouse button is pressed
			leftPressed = true;
		} else if(e.getButton() == MouseEvent.BUTTON3) { //right click
			rightPressed = true;
		}
		
		if(uiManager != null) {
			uiManager.onMouseRelease(e);
		}
		
	}

	/**
	 * called when the mouse is released and determines which mouse button was released
	 * @author fuelvin
	 * @param e mouse event used to determine button released
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) { //if left mouse button is pressed
			leftPressed = false;
		} else if(e.getButton() == MouseEvent.BUTTON3) { //right click
			rightPressed = false;
		}
		
	}

}
