package Decorator;

import java.awt.Graphics;

import Assets.MonsterInterface;

public abstract class MonsterDecorator implements MonsterInterface {
    protected MonsterInterface monster;

    public MonsterDecorator(MonsterInterface monster) {
        this.monster = monster;
    }

    @Override
    public void tick() {
        monster.tick();
    }

    @Override
    public void render(Graphics g) {
        monster.render(g);
    }

    @Override
    public int getHealth() {
        return monster.getHealth();
    }

    @Override
    public void setHealth(int health) {
        monster.setHealth(health);
    }

    @Override
    public void setBaseHealth(int health) {
        monster.setBaseHealth(health);
    }
}
