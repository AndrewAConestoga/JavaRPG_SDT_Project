package Assets;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {

	// ----- Constants -----

	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;


	
	// ----- Data -----
	
	// General info
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new Tile(Asset.grass, 0, false, false);
	public static Tile bush = new Tile(Asset.bush, 1, false, false);

	// Edges
	public static Tile edgeLeft		= new Tile(Asset.edgeLeft, 2, true, false);
	public static Tile edgeRight	= new Tile(Asset.edgeRight, 3, true, false);
	public static Tile edgeTop		= new Tile(Asset.edgeTop, 4, true, false);
	public static Tile edgeBottom	= new Tile(Asset.edgeBottom, 5, true, false);
	
	// Outer corners
	public static Tile cornerOutTL	= new Tile(Asset.cornerOutTopLeft, 6, true, true);
	public static Tile cornerOutTR	= new Tile(Asset.cornerOutTopRight, 7, true, true);
	public static Tile cornerOutBL	= new Tile(Asset.cornerOutBottomLeft, 8, true, true);
	public static Tile cornerOutBR	= new Tile(Asset.cornerOutBottomRight, 9, true, true);
	
	// Inner corners
	public static Tile cornerInTL	= new Tile(Asset.cornerInTopLeft, 10, true, true);
	public static Tile cornerInTR	= new Tile(Asset.cornerInTopRight, 11, true, true);
	public static Tile cornerInBL	= new Tile(Asset.cornerInBottomLeft, 12, true, true);
	public static Tile cornerInBR	= new Tile(Asset.cornerInBottomRight, 13, true, true);
	
	// Others
	public static Tile ledgeMiddle = new Tile(Asset.ledge, 14, true, false);
	public static Tile grassLedge = new Tile(Asset.grassLedge, 15, true, false);
	public static Tile plateau = new Tile(Asset.plateau, 16, true, false);
	
	
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
