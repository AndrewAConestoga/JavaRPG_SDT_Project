package Command;

public class PlayerInput {
	
	private Command command;
	
	public PlayerInput(Command command) {
		this.command = command;	
	}
	
	public Command getCommand() {
		return this.command;
	}
	
	public void setCommand(Command command) {
		this.command = command;
	}
	
	public void execute() {
		this.command.execute();
	}
	
	public void unexecute() {
		this.command.unexecute();
	}

}
