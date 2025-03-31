package Decorator;

import java.awt.Color;
import java.awt.Graphics;

import Assets.MonsterInterface;

public class StrongMonster extends MonsterDecorator {
    private int strengthBoost;

    public StrongMonster(MonsterInterface monster, int strengthBoost) {
        super(monster);
        this.strengthBoost = strengthBoost;
        setAttack(strengthBoost);
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

    @Override
    public int getAttack() {
        return monster.getAttack();
    }

    @Override
    public void setAttack(int attack) {
        monster.setAttack(monster.getAttack() + attack);
    }
    
}
