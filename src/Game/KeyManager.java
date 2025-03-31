package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * class used to listen for key inputs from the user
 * @author fuelvin
 */
public class KeyManager implements KeyListener{
	
	private boolean[] mKeys;
	public boolean up, down, left, right;
	public boolean Up, Down, Left, Right;
	public boolean q, space;
	private int mCurrent = -1;
	private int mBackup = -1;
	public static boolean isMoving = false;
	
	/**
	 * creates a new instance of KeyManager
	 * @author fuelvin
	 */
	public KeyManager() {
		this.mKeys = new boolean[256];
	}
	
	/**
	 * called once per frame, sets the values of which keys are currently pressed
	 * @author fuelvin
	 */
	public void tick() {
		up = this.mKeys[KeyEvent.VK_W];
		down = this.mKeys[KeyEvent.VK_S];
		left = this.mKeys[KeyEvent.VK_A];
		right = this.mKeys[KeyEvent.VK_D];
		Up = this.mKeys[KeyEvent.VK_UP];
		Down = this.mKeys[KeyEvent.VK_DOWN];
		Left = this.mKeys[KeyEvent.VK_LEFT];
		Right = this.mKeys[KeyEvent.VK_RIGHT];
		q = this.mKeys[KeyEvent.VK_Q];
		space = this.mKeys[KeyEvent.VK_SPACE];
	}

	/**
	 * this function is called whenever the user presses down on a key
	 * @author fuelvin
	 * @param e holds information detailing which key was pressed
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// Prevent the function from going beyond indexes
		if (e.getKeyCode() >= this.mKeys.length) {
			return;
		}

		// Ensure different keycodes and not invalid
		if(this.mCurrent != e.getKeyCode() && this.mCurrent != -1) {
			// Swap keys
			this.mBackup = this.mCurrent;
			this.mKeys[this.mBackup] = false;
		}
		this.mCurrent = e.getKeyCode();
		this.mKeys[this.mCurrent] = true;
	}

	/**
	 * this function is called whenever the user releases a pressed key
	 * @author fuelvin
	 * @param e holds information detailing which key was released
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() >= this.mKeys.length) {
			return;
		}
		if(this.mBackup != -1) { //if you are pressing more than one key
			
			if(e.getKeyCode() != this.mBackup) {  //release current, this.mBackup becomes current
				this.mKeys[this.mCurrent] = false;
				this.mKeys[this.mBackup] = true;
				this.mCurrent = this.mBackup;
			}
			this.mBackup = -1;
		} else {
			int keyCode = e.getKeyCode();
			if(keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
				this.mKeys[keyCode] = true;
			} else if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
				this.mKeys[keyCode] = true;
			}
			this.mKeys[keyCode] = false;
			this.mCurrent = -1;
		}
	}

	/**
	 * this function is called whenever the user types a key
	 * @author fuelvin
	 * @param e holds information detailing which key was typed
	 */
	@Override
	public void keyTyped(KeyEvent e) {}
}
