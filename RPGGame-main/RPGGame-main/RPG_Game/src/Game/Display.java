package Game;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {

	private JFrame mFrame;
	private Canvas mCanvas;
	
	private final String mTitle;
	private final int mWidth;
	private final int mHeight;
	
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

	public void addManagers(KeyManager key, MouseManager mouse) {
		this.mFrame.addKeyListener(key);
		this.mFrame.addMouseListener(mouse);
		this.mFrame.addMouseMotionListener(mouse);
		this.mCanvas.addMouseListener(mouse);
		this.mCanvas.addMouseMotionListener(mouse);
	}
	
	public Canvas getCanvas() {
		return this.mCanvas;
	}	
}
