package Command;

import Assets.Player;

/**
 * command class for allowing player to run
 * @author Andrew Abrantes
 */
public class PlayerRunCommand implements Command {
	
	private Player p;
	
	/**
	 * Creates a new instance of PlayerRunCommand
	 * @author Andrew Abrantes
	 * @param p player to manipulate speed of
	 */
	public PlayerRunCommand(Player p) {
		this.p = p;
	}

	/**
	 * Executes command, increases the players move speed
	 * @author Andrew Abrantes
	 */
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

	/**
	 * unexecutes command, sets players move speed back to default
	 * @author Andrew Abrantes
	 */
	@Override
	public void unexecute() {
		
		p.flag = false;
		
	}
	
	

}
