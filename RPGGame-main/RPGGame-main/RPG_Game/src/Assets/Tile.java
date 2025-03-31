package Assets;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Game.Game;
import Game.Handler;
import Assets.Assets;

/**
 * tile class used to create tiles in the world for player to move through
 * @author fuelvin
 */
public class Tile {
	
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new Tile(Assets.grass, 0, false, false);
	public static Tile rockTile = new Tile(Assets.rock, 1, false, false);
	
	public static Tile ledgeTopLeft = new Tile(Assets.ledgeTopLeft, 3, true, true);
	public static Tile ledgeTopMiddle = new Tile(Assets.ledgeTopMiddle, 4, true, false);
	public static Tile ledgeTopRight = new Tile(Assets.ledgeTopRight, 5, true, true);
	
	public static Tile ledgeTile = new Tile(Assets.ledgeMiddleLeft, 6, true, false);
	public static Tile ledgeMiddleMiddle = new Tile(Assets.ledgeMiddleMiddle, 7, true, false);
	public static Tile middleRightLedge = new Tile(Assets.ledgeMiddleRight, 8, true, false);
	
	public static Tile leftCornerLedge = new Tile(Assets.leftCornerLedge, 9, true, true);
	public static Tile bottomMiddleLedge = new Tile(Assets.bottomMiddleLedge, 10, true, false);
	public static Tile bottomRightLedge = new Tile(Assets.bottomRightLedge, 11, true, true);
	
	public static Tile ledgeCornerTopRight = new Tile(Assets.ledgeCornerTopRight, 14, true, false);
	public static Tile ledgeCornerTopLeft = new Tile(Assets.ledgeCornerTopLeft, 15, true, false);
	
	public static Tile ledgeMiddle = new Tile(Assets.ledge, 12, true, false);
	public static Tile grassLedge = new Tile(Assets.grassLedge, 13, true, false);
	
	public static Tile bush = new Tile(Assets.bush, 2, false, false);
	
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
	
	protected BufferedImage texture;
	protected final int id;
	protected boolean isSolid;
	protected boolean isFront;

	/**
	 * creates a new instance of a tile obejct
	 * @author fuelvin
	 * @param texture texture/image of tile to be displayed
	 * @param id id number of the tile
	 * @param isSolid whether or not the tile has collision
	 * @param isFront whether or not the tile is in front and should be displayed over other tiles
	 */
	public Tile(BufferedImage texture, int id, boolean isSolid, boolean isFront) {
		this.texture = texture;
		this.id = id;
		tiles[id] = this;
		this.isSolid = isSolid;
		this.isFront = isFront;
	}
	
	/**
	 * called once per frame
	 * @author fuelvin
	 */
	public void tick() {
		
	}
	
	/**
	 * draws self on the game screen
	 * @author fuelvin
	 * @param g graphics to draw to
	 * @param x top left x pixel on screen to draw image to
	 * @param y top left y pixel on screen to draw image to
	 */
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	/**
	 * getter for isSolid
	 * @author fuelvin
	 * @return true if the tile is solid or has collision, false if it does not
	 */
	public boolean isSolid() {
		return isSolid;
	}
	
	/**
	 * getter for front
	 * @author fuelvin
	 * @return true front is true, false if it is not
	 */
	public boolean front() {
		return isFront;
	}
	
	/**
	 * getter for id
	 * @author fuelvin
	 * @return the id number of the tile
	 */
	public int getId() {
		return id;
	}

	
}
