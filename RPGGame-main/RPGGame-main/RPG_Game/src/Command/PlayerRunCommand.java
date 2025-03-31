package Command;

import Assets.Player;

public class PlayerRunCommand implements Command {
	
	private Player p;
	
	public PlayerRunCommand(Player p) {
		this.p = p;
	}

	@Override
	public void execute() {
		
		if (!p.flag) {
			p.flag = true;
			if (p.speed == 16.0f) {
				p.speed = 4.0f;
			} else {
				p.speed = 16.0f;
			}
		}
		
	}

	@Override
	public void unexecute() {
		
		p.flag = false;
		
	}
	
	

}
