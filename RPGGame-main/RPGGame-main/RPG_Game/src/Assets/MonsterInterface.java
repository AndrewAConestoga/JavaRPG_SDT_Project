package Assets;

import java.awt.Graphics;

/**
 * interface for monsters to implement 
 * @author Dylan
 */
public interface MonsterInterface {
	
	/**
   	 * called every frame
   	 * @author Dylan
   	 */
    void tick();
    
    /**
	 * draws self on the game screen
	 * @author Dylan
	 * @param g graphics to draw to
	 */
    void render(Graphics g);
    
    /**
   	 * getter for monster health
   	 * @author Dylan
   	 * @return health of monster
   	 */
    int getHealth();
    
    /**
   	 * setter for monster health
   	 * @author Dylan
   	 * @param new health of monster
   	 */
    void setHealth(int health);
    
    /**
   	 * setter for monster base health
   	 * @author Dylan
   	 * @param new base health of monster
   	 */
    void setBaseHealth(int health);
    int getAttack();
    void setAttack(int attack);
}
