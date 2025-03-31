package Assets;

import java.awt.Graphics;

public interface MonsterInterface {
    void tick();
    void render(Graphics g);
    int getHealth();
    void setHealth(int health);
    void setBaseHealth(int health);
    int getAttack();
    void setAttack(int attack);
}
