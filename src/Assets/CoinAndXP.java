package Assets;


import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Class that creates the coin and xp shooting out animation after defeating a monster
 * @author fuelvin
 */
public class CoinAndXP {
	
	private int x;
	private int y;
	private int type;
	private int type2;
	private float xVel;
	private float yVel;
	private float opacity = 1;
	
	private float randSpeed;

	/**
	 * creates a new instance of CoinAndXp
	 * @author fuelvin
	 */
	public CoinAndXP() {
		this.x = 500;
		this.y = 200;
		type = (int)Math.round(Math.random());
		type2 = (int)Math.round(Math.random());
		if(type2 == 0){
			xVel = (int)(Math.random() * 3 + 1);
		} else {
			xVel = (int)(Math.random() * 3 + 1) * -1;
		}
		randSpeed = (int)(Math.random() * 99 + 1) / 100;
		yVel = 10;
	}
	
	/**
	 * called once per frame, plays random drop animation after defeating an enemy
	 * @author fuelvin
	 */
	public void tick() {
		x += xVel;
		y -= yVel;
		if(type2 == 0) {
			xVel += randSpeed;
		} else {
			xVel -= randSpeed * -1;
		}
		yVel -= 0.4f;
	}
	
	/**
	 * draws self on the game screen
	 * @author fuelvin
	 * @param g graphics to draw to
	 */
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if(opacity >= 0.017) {
			opacity -= 0.017;
		} else {
			opacity = 0;
		}
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
		
		if(type == 0) {
			g.drawImage(Asset.coin, x, y, 8 * 4, 8 * 4, null);
		} else {
			g.drawImage(Asset.xp, x, y, 10 * 4, 8 * 4, null);
		}
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
