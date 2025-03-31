package Assets;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import Game.Game;
import Game.Handler;
import MyIterator.IteratorInterface;

/**
 * class for containing and manager all entities and the player
 * @author fuelvin
 */
public class EntityManager implements IteratorInterface {
	
	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;
	private Comparator<Entity> renderSorter = new Comparator<Entity>() {

		/**
		 * compares 2 entities to see if their y and height values or the same
		 * @author fuelvin
		 * @param a first entity to be compared
		 * @param b second entity to be compared to entity a
		 * @return -1 if the sum of entity a's y and height is less then entity b's, 1 otherwise
		 */
		@Override
		public int compare(Entity a, Entity b) {
			if(a.getY() + a.getHeight() < b.getY() + b.getHeight()) {
				return -1;
			}
			return 1;
		}
		
	};
	
	/**
	 * creates a new instance of EntityManager
	 * @author fuelvin
	 * @param handler Handler used to access game information from
	 */
	public EntityManager(Handler handler) {
		this.handler = handler;
		this.player = Game.sPlayer;
		entities = new ArrayList<Entity>();
		addEntity(player);
	}
	
	/**
	 * called once per frame, calls the tick function on all entities contained in this manager
	 * @author fuelvin
	 */
	public void tick() {
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
		}
	}
	
	/**
	 * draws self on the game screen
	 * @author fuelvin
	 * @param g graphics to draw to
	 */
	public void sort() {
		entities.sort(renderSorter);
	}
	
	/**
	 * adds a specific entity from the entities list
	 * @author fuelvin
	 * @param e the entity to be added
	 */
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	/**
	 * removes a specific entity from the entities list
	 * @author fuelvin
	 * @param e the entity to be removed
	 */
	public void removeEntity(Entity e) {
		entities.remove(e);
	}
	
	
	

	/**
	 * getter for handler
	 * @author fuelvin
	 * @return the handler linked to this object
	 */
	public Handler getHandler() {
		return handler;
	}

	/**
	 * setter for handler
	 * @author fuelvin
	 * @param handler the handler to be contained in this object
	 */
	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	/**
	 * getter for player
	 * @author fuelvin
	 * @return the player object in the entity manager
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * setter for player
	 * @author fuelvin
	 * @param player the player object to be contained in this object
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Gets an iterator for a collection of entitites
	 * @author Kyle Wagler
	 * @return all the entities in this manager as an iterator
	 */
	@Override
	public Iterator<Entity> getIterator() {
		return entities.iterator();
	}

	/**
	 * setter for entities
	 * @author fuelvin
	 * @param entities the list of entities to be contained in this object
	 */
	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

}
