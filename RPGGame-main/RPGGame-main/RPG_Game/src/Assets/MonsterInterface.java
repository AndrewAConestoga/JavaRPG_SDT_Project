package Assets;

import java.awt.Graphics;

/**
 * interface for monsters to implement 
 * @author fuelvin
 */
public interface MonsterInterface {
	
	/**
   	 * called every frame
   	 * @author fuelvin
   	 */
    void tick();
    
    /**
	 * draws self on the game screen
	 * @author fuelvin
	 * @param g graphics to draw to
	 */
    void render(Graphics g);
    
    /**
   	 * getter for monster health
   	 * @author fuelvin
   	 * @return health of monster
   	 */
    int getHealth();
    
    /**
   	 * setter for monster health
   	 * @author fuelvin
   	 * @param new health of monster
   	 */
    void setHealth(int health);
    
    /**
   	 * setter for monster base health
   	 * @author fuelvin
   	 * @param new base health of monster
   	 */
    void setBaseHealth(int health);
    int getAttack();
    void setAttack(int attack);
}
