package Decorator;

import java.awt.Color;
import java.awt.Graphics;

import Assets.MonsterInterface;

/**
 * Decorator that adds strength to a monster
 * @author Dylan
 */
public class StrongMonster extends MonsterDecorator {
    private int strengthBoost;

    /**
     * Constructor for the StrongMonster class
     * @param monster the monster that will be decorated
     * @param strengthBoost the amount of strength to add to the monster
     */
    public StrongMonster(MonsterInterface monster, int strengthBoost) {
        super(monster);
        this.strengthBoost = strengthBoost;
        setAttack(strengthBoost);
    }

    /**
     * draws self on the game screen, with red color
     * @author Dylan
     * @param g graphics to draw to
     */
    @Override
    public void render(Graphics g) {
        super.render(g);
        g.setColor(Color.RED);
    }

    /**
     * Called once per frame, calls the tick function on the monster
     * @author Dylan
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
     * Sets the attack value of the decorated monster. The value is increased
     * by the strength boost of the StrongMonster.
     * @author Dylan
     * @param attack the value to set the attack to
     */ 
    @Override
    public void setAttack(int attack) {
        monster.setAttack(monster.getAttack() + attack);
    }
    
}
