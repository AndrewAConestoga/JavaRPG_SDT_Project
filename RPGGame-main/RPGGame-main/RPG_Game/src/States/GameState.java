package States;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import Assets.Assets;
import Assets.Description;
import Assets.Player;
import Assets.Text;
import Game.Game;
import Game.Handler;
import Game.World;

public class GameState extends State{
	
	private World world;
	public static boolean flag;
	private Description playerDescription;
	private Text coinsText;

	public GameState(Handler handler) {
		super(handler); //calls constructor of "State" class
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
		playerDescription = new Description(2, Game.player.name, Game.player.health, Game.player.baseHealth, Game.player.level, handler);
	}
	

	
	@Override
	public void tick() {
		world.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
		playerDescription.render(g);
		coinsText = new Text(Game.player.coins + "", 50, 126, 4, 4);
		coinsText.render(g);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
