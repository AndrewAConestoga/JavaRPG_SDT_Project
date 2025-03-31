package Command;

import Assets.Player;

/**
 * class used to change the speed and direction of the player
 * @author Andrew Abrantes
 */
public class PlayerMoveCommand implements Command {
	
	public enum Direction {
		Up, Right, Left, Down
	}
	
	private Player p;
	private Direction d;
	
	/**
	 * creates a new instance of PlayerMoveCommand
	 * @author Andrew Abrantes
	 * @param p player to manipulate speed and direction of
	 * @param d the direction this command will set the player to 
	 */
	public PlayerMoveCommand(Player p, Direction d) {
		this.p = p;
		this.d = d;
	}
	

	/**
	 * Executes command, sets players speed and direction based on input
	 * @author Andrew Abrantes
	 */
	@Override
	public void execute() {
		
		if (this.d.equals(Direction.Up)) {
			p.yMove = p.speed * -1;
			p.mDir = 1;
		}
		else if (this.d.equals(Direction.Down)) {
			p.yMove = p.speed;
			p.mDir = 0;
		}
		else if (this.d.equals(Direction.Left)) {
			p.xMove = p.speed * -1;
			p.mDir = 2;
		}
		else if (this.d.equals(Direction.Right)) {
			p.xMove = p.speed;
			p.mDir = 3;
		}
	}


	/**
	 * unexecutes command
	 * @author Andrew Abrantes
	 */
	@Override
	public void unexecute() {
		
		// 
		
	}

}
