package Command;

import Assets.Player;
import Game.Game;

/**
 * command class for displaying hitboxes in the game
 * @author Andrew Abrantes
 */
public class PlayerShowHitboxesCommand implements Command {
	
private Player p;
	
	/**
	 * creates a new instance of PlayerShowHitboxesCommand
	 * @author Andrew Abrantes
	 * @param p player to show hitboxes of
	 */
	public PlayerShowHitboxesCommand(Player p) {
		this.p = p;
	}

	/**
	 * Executes command, shows the hitboxes of the player and the game
	 * @author Andrew Abrantes
	 */
	@Override
	public void execute() {
		
		if (!p.flag2) {
			p.flag2 = true;
			Game.sShowHitboxes = !Game.sShowHitboxes;
		}
		
	}
	
	/**
	 * unexcutes command, hides all hitboxes
	 * @author Andrew Abrantes
	 */
	@Override
	public void unexecute() {
		
		p.flag2 = false;
		
	}

}
