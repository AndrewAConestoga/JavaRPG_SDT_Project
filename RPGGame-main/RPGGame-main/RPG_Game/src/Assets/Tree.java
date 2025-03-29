package Assets;

import java.awt.Color;

import java.awt.Graphics;

import Game.Game;
import Game.Handler;

public class Tree extends Entity{
	private int type;
	public Tree(Handler handler, float x, float y, int type) {
		super(handler, x, y, Tile.TILEWIDTH * 3, Tile.TILEHEIGHT * 3);
		this.type = type;
		this.mBounds.width = this.mWidth / 2 + 30;
		this.mBounds.height = this.mHeight / 6 + 1;
		this.mBounds.x = Tile.TILEWIDTH * 2 - this.mBounds.width;
		this.mBounds.y = Tile.TILEHEIGHT * 2;

	}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.trees[type], (int) (this.mX - this.mHandler.getGameCamera().getxOffset()), (int) (this.mY - this.mHandler.getGameCamera().getyOffset()), this.mWidth, this.mHeight, null);
		
		if(Game.sShowHitboxes) {
			g.setColor(Color.red);
			g.drawRect((int) (this.mX + this.mBounds.x - this.mHandler.getGameCamera().getxOffset()), (int) (this.mY + this.mBounds.y - this.mHandler.getGameCamera().getyOffset()), this.mBounds.width, this.mBounds.height);
		}
	}

}
