package Command;

import Assets.Player;

public class PlayerMoveCommand implements Command {
	
	public enum Direction {
		Up, Right, Left, Down
	}
	
	private Player p;
	private Direction d;
	
	public PlayerMoveCommand(Player p, Direction d) {
		this.p = p;
		this.d = d;
	}
	

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


	@Override
	public void unexecute() {
		
		// 
		
	}

}
