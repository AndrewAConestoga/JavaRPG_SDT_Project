package Decorator;

import java.awt.Color;
import java.awt.Graphics;

import Assets.MonsterInterface;

/**
 * Decorator that adds health to a monster
 * @author Dylan
 */
public class HealthyMonster extends MonsterDecorator {
    private int healthBoost;

    /**
 * Decorator constructor that adds health to a monster
 * @author Dylan
 * @param monster the monster to add health to
 * @param healthBoost the amount of health to add
 */
    public HealthyMonster(MonsterInterface monster, int healthBoost) {
        super(monster);
        this.healthBoost = healthBoost;
        setBaseHealth(healthBoost);
        setHealth(healthBoost);
    }

    /**
     * sets the base health of the monster
     * @author Dylan
     * @param health the value to set the base health to
     */
    @Override
    public void setBaseHealth(int health) {
        super.setBaseHealth(health);
    }

    /**
     * gets the current health of the monster
     * @author Dylan
     * @return the current health of the monster
     */
    @Override
    public int getHealth() {
        return super.getHealth();
    }

/**
 * Renders the healthy monster on the game screen with an additional red color effect.
 * @author fuelvin
 * @param g graphics context to draw on
 */

    @Override
    public void render(Graphics g) {
        super.render(g);
        g.setColor(Color.RED);
    }

/**
 * Updates the state of the healthy monster for each game tick.
 * This method should be called regularly to ensure the monster's state is kept current.
 * @author fuelvin
 */

    @Override
    public void tick() {
        super.tick();
    }

    /**
     * Gets the attack value of the decorated monster.
     * @author Dylan
     * @return the attack value of the decorated monster
     */
    @Override
    public int getAttack() {
        return monster.getAttack();
    }

    /**
     * Sets the attack value of the decorated monster.
     * @author Dylan
     * @param attack the value to set the attack to
     */
    @Override
    public void setAttack(int attack) {
        monster.setAttack(attack);
    }
    
}
