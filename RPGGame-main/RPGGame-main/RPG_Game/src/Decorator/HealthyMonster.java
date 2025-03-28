package Decorator;

import java.awt.Color;
import java.awt.Graphics;

import Assets.MonsterInterface;

public class HealthyMonster extends MonsterDecorator {
    private int healthBoost;

    public HealthyMonster(MonsterInterface monster, int healthBoost) {
        super(monster);
        this.healthBoost = healthBoost;
        setBaseHealth(healthBoost);
        setHealth(healthBoost);
    }

    @Override
    public void setBaseHealth(int health) {
        super.setBaseHealth(health);
    }

    @Override
    public int getHealth() {
        return super.getHealth();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        g.setColor(Color.RED);
    }

    @Override
    public void tick() {
        super.tick();
    }
    
}
