package Assets;

import java.awt.Color;

import java.awt.Graphics;

import Game.Game;
import Game.Handler;

/**
 * tree object to be displayed on the world screen
 * @author fuelvin
 */
public class Tree extends Entity{
	private int type;
	
	/**
	 * creates a new instance of a tree
	 * @author fuelvin
	 * @param handler Handler used to access game information from
	 * @param x x position of where to draw tree on screen
	 * @param y y position of where to draw tree on screen
	 * @param type sets the type of the tree
	 */
	public Tree(Handler handler, float x, float y, int type) {
		super(handler, x, y, Tile.TILEWIDTH * 3, Tile.TILEHEIGHT * 3);
		this.type = type;
		this.mBounds.width = this.mWidth / 2 + 30;
		this.mBounds.height = this.mHeight / 6 + 1;
		this.mBounds.x = Tile.TILEWIDTH * 2 - this.mBounds.width;
		this.mBounds.y = Tile.TILEHEIGHT * 2;

	}

	/**
	 * called once every frame
	 * @author fuelvin
	 */
	@Override
	public void tick() {}

	/**
	 * draws self on the game screen
	 * @author fuelvin
	 * @param g graphics to draw to
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(Asset.trees[type], (int) (this.mX - this.mHandler.getGameCamera().getxOffset()), (int) (this.mY - this.mHandler.getGameCamera().getyOffset()), this.mWidth, this.mHeight, null);
		
		if(Game.sShowHitboxes) {
			g.setColor(Color.red);
			g.drawRect((int) (this.mX + this.mBounds.x - this.mHandler.getGameCamera().getxOffset()), (int) (this.mY + this.mBounds.y - this.mHandler.getGameCamera().getyOffset()), this.mBounds.width, this.mBounds.height);
		}
	}

}
