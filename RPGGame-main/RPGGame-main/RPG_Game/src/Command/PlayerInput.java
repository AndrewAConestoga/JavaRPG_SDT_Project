package Command;

/**
 * invoker class used to execute set commands
 * @author Andrew Abrantes
 */
public class PlayerInput {
	
	private Command command;
	
	/**
	 * Invoker used to execute command
	 * @author Andrew Abrantes
	 * @param command command to be executed by this invoker
	 */
	public PlayerInput(Command command) {
		this.command = command;	
	}
	
	/**
	 * getter for command
	 * @author Andrew Abrantes
	 * @return the command of this invoker
	 */
	public Command getCommand() {
		return this.command;
	}
	
	/**
	 * setter for command
	 * @author Andrew Abrantes
	 * @param the command to be used by this invoker
	 */
	public void setCommand(Command command) {
		this.command = command;
	}
	
	/**
	 * Executes the command of this invoker
	 * @author Andrew Abrantes
	 */
	public void execute() {
		this.command.execute();
	}
	
	/**
	 * Unexecutes the command of this invoker
	 * @author Andrew Abrantes
	 */
	public void unexecute() {
		this.command.unexecute();
	}

}
