package Game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * class that holds utilitiy base functions to use
 * @author fuelvin
 */
public class Utils {
	
	/**
	 * loads the contents of a file into a string
	 * @author fuelvin
	 * @param path filepath location of file to read
	 * @return string represention of the file
	 */
	public static String loadFileAsString(String path) {
		StringBuilder builder = new StringBuilder();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			System.out.println("BR: " + br);
			String line;
			while((line = br.readLine()) != null){
				builder.append(line + "\n");
			}
			
			br.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	/**
	 * turns a string into a number
	 * @author fuelvin
	 * @param number string to convert to number
	 * @return integer value of string
	 */
	public static int parseInt(String number) {
		try {
			return Integer.parseInt(number);
		}catch(NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}

}













