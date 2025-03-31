package Game;

/**
 * main class used to launch the game
 * @author fuelvin
 */
public class Launcher{
	
	/**
	 * main function that starts the game
	 * @author fuelvin
	 * @param e holds information detailing which key was pressed
	 */
	public static void main(String[] args) {
		Game game = new Game("Title", 800, 800);
		game.start();
	}
}
