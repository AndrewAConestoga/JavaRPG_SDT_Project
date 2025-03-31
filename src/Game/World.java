package Game;

import java.awt.Graphics;
import java.util.Iterator;

import Assets.Entity;
import Assets.EntityManager;
import Assets.Tree;
import Assets.Tile;

/**
 * class to load and display a game world for the player to play in
 * @author fuelvin
 */
public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	
	private EntityManager entityManager;

	/**
	 * creates a new instance of World
	 * @author fuelvin
	 * @param handler Handler to access game information from
	 * @param path filepath of world file to load
	 */
	public World(Handler handler, String path) {
		this.handler = handler;
		entityManager = new EntityManager(handler);
		
		loadWorld(path);
		
		entityManager.getPlayer().setX(spawnX * Tile.TILEWIDTH + 10);
		entityManager.getPlayer().setY(spawnY * Tile.TILEHEIGHT + 16);
		
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				if(tiles[j][i] >= 96 && tiles[j][i] < 100) {
					entityManager.addEntity(new Tree(handler, (int) (j * Tile.TILEWIDTH), (int) (i * Tile.TILEWIDTH) - (int)(Tile.TILEWIDTH * 1.5), 99 - tiles[j][i]));
				}
			}
		} 
	}
	
	/**
	 * getter for EntityManager
	 * @author fuelvin
	 * @return this objects EntityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * called once per frame, calls the tick() function of the entityManager
	 * @author fuelvin
	 */
	public void tick() {
		entityManager.tick();
	}
	
	/**
	 * draws self on the game screen
	 * @author fuelvin
	 * @param g graphics to draw to
	 */
	public void render(Graphics g) {
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0,  handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height,  (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		
		for(int i = yStart; i < yEnd; i++) {
			for(int j = xStart; j < xEnd; j++) {
				if(getTile(j, i).front()) {
					getTile(-1, -1).render(g, (int) (j * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (i * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
				}
				getTile(j, i).render(g, (int) (j * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (i * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}

		// Display entities
		Iterator<Entity> entities = entityManager.getIterator();
		while (entities.hasNext()) {
			entities.next().render(g);
		}
		entityManager.sort();
	}
	
	/**
	 * getter for tile at specific location
	 * @author fuelvin
	 * @param x column of tile to get
	 * @param y row of tile to get
	 * @return tile at position (x,y)
	 */
	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y >= height) {
			return Tile.grassTile;
		}
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null) {
			return Tile.grassTile;
		}
		return t;
	}
	
	/**
	 * loads a new world 
	 * @author fuelvin
	 * @param path filepath of world file to load
	 */
	private void loadWorld(String path) {
		System.out.println(path);
		String file = Utils.loadFileAsString(path);
		System.out.println(file);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}
	
	/**
	 * getter for width
	 * @author fuelvin
	 * @return width of the world
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * getter for height
	 * @author fuelvin
	 * @return height of the world
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * getter for spawnX
	 * @author fuelvin
	 * @return spawnX position for the world
	 */
	public int getSpawnX() {
		return spawnX;
	}
	
	/**
	 * getter for spawnY
	 * @author fuelvin
	 * @return spawnY position for the world
	 */
	public int getSpawnY() {
		return spawnY;
	}
	
	/**
	 * setter for spawnX
	 * @author fuelvin
	 * @param new spawnX position for the world
	 */
	public void setSpawnX(int spawnX) {
		this.spawnX = spawnX;
	}
	
	/**
	 * setter for spawnY
	 * @author fuelvin
	 * @param new spawnY position for the world
	 */
	public void setSpawnY(int spawnY) {
		this.spawnY = spawnY;
	}
}

