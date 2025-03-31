package Command;

import Assets.Player;
import Game.Game;

public class PlayerShowHitboxesCommand implements Command {
	
private Player p;
	
	public PlayerShowHitboxesCommand(Player p) {
		this.p = p;
	}

	@Override
	public void execute() {
		
		if (!p.flag2) {
			p.flag2 = true;
			Game.sShowHitboxes = !Game.sShowHitboxes;
		}
		
	}
	
	@Override
	public void unexecute() {
		
		p.flag2 = false;
		
	}

}
