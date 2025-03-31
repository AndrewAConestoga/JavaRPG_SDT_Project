package Game;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * class for the game window and displaying all the game assets
 * @author fuelvin
 */
public class Display {

	private JFrame mFrame;
	private Canvas mCanvas;
	
	private final String mTitle;
	private final int mWidth;
	private final int mHeight;
	
	/**
	 * creates a new instance of Display
	 * @author fuelvin
	 * @param title title of the window to be set to
	 * @param width width of the window in pixels
	 * @param height heigh of the window in pixels
	 */
	public Display(String title, int width, int height) {
		this.mTitle = title;
		this.mWidth = width;
		this.mHeight = height;
		
		// JFrame initialization
		this.mFrame = new JFrame(this.mTitle);
		this.mFrame.setSize(this.mWidth, this.mHeight);
		this.mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mFrame.setResizable(false);
		this.mFrame.setLocationRelativeTo(null);
		this.mFrame.setVisible(true);
		
		// Create canvas
		this.mCanvas = new Canvas();
		this.mCanvas.setPreferredSize(new Dimension(this.mWidth, this.mHeight));
		this.mCanvas.setMaximumSize(new Dimension(this.mWidth, this.mHeight));
		this.mCanvas.setMinimumSize(new Dimension(this.mWidth, this.mHeight));
		this.mCanvas.setFocusable(false);
		
		// Final frame packing
		this.mFrame.add(this.mCanvas);
		this.mFrame.pack();
	}

	/**
	 * sets all the mouse and key managers on the screen for getting player input
	 * @author fuelvin
	 * @param key KeyManager used to listen for key inputs
	 * @param mouse MouseManager used to listen for mouse button inputs
	 */
	public void addManagers(KeyManager key, MouseManager mouse) {
		this.mFrame.addKeyListener(key);
		this.mFrame.addMouseListener(mouse);
		this.mFrame.addMouseMotionListener(mouse);
		this.mCanvas.addMouseListener(mouse);
		this.mCanvas.addMouseMotionListener(mouse);
	}
	
	/**
	 * getter for canvas
	 * @author fuelvin
	 * @return the canvas the screen is drawing assets on
	 */
	public Canvas getCanvas() {
		return this.mCanvas;
	}	
}
