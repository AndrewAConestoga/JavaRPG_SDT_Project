package Command;

/**
 * The command interface to be used on objects later
 * @author Andrew Abrantes
 */
public interface Command {
	/**
	 * Executes some functionality in concrete class
	 * @author Andrew Abrantes
	 */
	public void execute();
	
	/**
	 * Unexecutes some functionality in concrete class
	 * @author Andrew Abrantes
	 */
	public void unexecute();
}
