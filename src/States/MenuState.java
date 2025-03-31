
package States;

import java.awt.Graphics;
import Game.ClickListener;
import Game.Handler;
import Game.UIImageButton;
import Game.UIManager;
import Assets.Asset;

/**
 * class for menu states for when player is in a menu
 * @author fuelvin
 */
public class MenuState extends State{
	
	private UIManager uiManager;

	/**
	 * creates a new instance of MenuState
	 * @author fuelvin
	 * @param handler Handler to access game information from
	 */
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(200, 200, 128, 64, Asset.buttonStart, new ClickListener() {

			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().mGameState);
			}
			}));
	}

	/**
	 * called once per frame, calls the UIManagers tick
	 * @author fuelvin
	 */
	@Override
	public void tick() {
		uiManager.tick();
	}

	/**
	 * draws the menu state to the screen
	 * @author fuelvin
	 * @param g graphics screen to draw state to
	 */
	@Override
	public void render(Graphics g) {
		uiManager.render(g);
	}

	
	
	
	
	
	
}
