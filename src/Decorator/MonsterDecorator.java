package Decorator;

import java.awt.Graphics;

import Assets.Monster;
import Assets.MonsterInterface;

/**
 * Abstract class that implements the MonsterInterface and serves as the base
 * class for all decorators. It contains a reference to a MonsterInterface object
 * which is the component to be decorated.
 * 
 * @author Dylan
 */
public abstract class MonsterDecorator implements MonsterInterface {
    /**
     * The monster to be decorated
     */
    protected MonsterInterface monster;

    /**
     * Constructor for the MonsterDecorator class. It takes a MonsterInterface
     * object as a parameter and assigns it to the monster field.
     * 
     * @author Dylan
     * @param monster the monster to be decorated
     */
    public MonsterDecorator(MonsterInterface monster) {
        this.monster = monster;
    }

    /**
     * Calls the tick method on the decorated monster
     * @author Dylan
     */
    @Override
    public void tick() {
        monster.tick();
    }

    /**
     * Calls the render method on the decorated monster
     * 
     * @param g the Graphics object to draw to
     * @author Dylan
     */
    @Override
    public void render(Graphics g) {
        monster.render(g);
    }

    /**
     * Calls the getHealth method on the decorated monster
     * 
     * @author Dylan
     * @return the health of the decorated monster
     */
    @Override
    public int getHealth() {
        return monster.getHealth();
    }

    /**
     * Calls the setHealth method on the decorated monster
     * 
     * @author Dylan
     * @param health the new health value for the decorated monster
     */
    @Override
    public void setHealth(int health) {
        monster.setHealth(health);
    }

    /**
     * Calls the setBaseHealth method on the decorated monster
     * 
     * @author Dylan
     * @param health the new base health value for the decorated monster
     */
    @Override
    public void setBaseHealth(int health) {
        monster.setBaseHealth(health);
    }
}
