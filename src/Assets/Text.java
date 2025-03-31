package Assets;

import java.awt.Graphics;

/**
 * class used to draw text onto the game screen
 * @author fuelvin
 */
public class Text {
	private String s;
	private int x;
	private int y;
	private int multiplier;
	private int type;
	private int start;
	private int letterCount;
	private boolean show;
	
	/**
	 * creates a new instance of Text
	 * @author fuelvin
	 * @param s string of text be be drawn to screen
	 * @param x x position where the text will start being drawn
	 * @param y y position where the text will start being drawn
	 * @param multiplier size of the text being drawn 
	 * @param type the type of text being drawn to the screen 
	 */
	public Text(String s, int x, int y, int multiplier, int type) {
		this.s = s;
		this.x = x;
		this.y = y;
		this.multiplier = multiplier;
		this.type = type;
	}
	
	private int count;
	
	/**
	 * draws self on the game screen
	 * @author fuelvin
	 * @param g graphics to draw to
	 */
	public void render(Graphics g) {
		if(type == -1) {
			start = x;
			for(int i = 0; i < s.length(); i++) {
				if(s.charAt(i) == ' ') {
					count -= multiplier;
					start += 5 * multiplier + count;
					g.drawImage(Asset.miniAlphabet[26], start, y, 3 * multiplier, 6 * multiplier, null);
				} else if(s.toLowerCase().charAt(i) == 'm' || s.toLowerCase().charAt(i) == 'w'){
					count += multiplier;
					start += 5 * multiplier;
					g.drawImage(Asset.miniAlphabet[(s.toLowerCase().charAt(i) - 97)], start, y, 5 * multiplier, 6 * multiplier, null);
				} else if(s.charAt(i) == '!') {
					start += 5 * multiplier + count;
					g.drawImage(Asset.miniAlphabet[27], start, y, 3 * multiplier, 6 * multiplier, null);
				} else {
					start += 5 * multiplier + count;
					g.drawImage(Asset.miniAlphabet[(s.toLowerCase().charAt(i) - 97)], start, y, 4 * multiplier, 6 * multiplier, null);
					count = 0;
				}
			}
		} else if(type == 0) {
			String delims = "[ ]+";
			String[] tokens = s.split(delims);
			int shift = 0;
			int xShift = 0;
			letterCount = 0;
			for(int i = 0; i < tokens.length; i++) {
				if(tokens[i].length() + letterCount > 29) {
					f = true;
					letterCount = 0;
					shift += 60;
				}
				xShift = 400 - ((maxLetters - 1) * multiplier * 3);
				alphabetRender(g, tokens[i], i, xShift, y + shift, 0);
			}
			show = true;
			f = true;
			
		} else if(type == 1) {
			for(int i = 0; i < s.length(); i++) {
				if(s.charAt(i) == '/') {
					g.drawImage(Asset.numbers[10], x + i * 5 * multiplier, y, 4 * multiplier, 6 * multiplier, null);
				} else {
					if(s.charAt(i) != '-') {
						g.drawImage(Asset.numbers[s.charAt(i) - 48], x + i * 5 * multiplier, y, 4 * multiplier, 6 * multiplier, null);
					}
				}
			}
		} else if(type == 2) {
			for(int i = 0; i < s.length(); i++) {
				g.drawImage(Asset.damageNumbers[s.charAt(i) - 48], x + i * 7 * multiplier, y, 7 * multiplier, 8 * multiplier, null);
			}
		} else if(type == 3) {
			for(int i = 0; i < s.length(); i++) {
				if(s.charAt(i) == '-') {
					g.drawImage(Asset.redNumbers[0], x + i * 5 * multiplier, y, 4 * multiplier, 6 * multiplier, null);
				} else {
					g.drawImage(Asset.redNumbers[(s.charAt(i) - 48) + 1], x + i * 5 * multiplier, y, 4 * multiplier, 6 * multiplier, null);
				}
			}
		} else if(type == 4) {
			for(int i = 0; i < s.length(); i++) {
				g.drawImage(Asset.moneyNumbers[(s.charAt(i) - 48)], x + i * 5 * multiplier, y, 4 * multiplier, 6 * multiplier, null);
			}
		} else if(type == 5) {
			String delims = "[ ]+";
			String[] tokens = s.split(delims);
			int shift = 0;
			int xShift = 0;
			letterCount = 0;
			for(int i = 0; i < tokens.length; i++) {
				if(tokens[i].length() + letterCount > 28) {
					f = true;
					letterCount = 0;
					shift += 60;
				}
				xShift = 400 - ((maxLetters - 1) * multiplier * 3);
				alphabetRender(g, tokens[i], i, xShift, y + shift, 1);
			}
			show = true;
			f = true;
			
		} 
	}
	
	public void setHealth(int health, int baseHealth) {
		s = health + "/" + baseHealth;
	}
	
	/**
	 * setter for y
	 * @author fuelvin
	 * @param y new y position to draw text to
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	private int maxLetters;
	private boolean f;
	
	/**
	 * draws a string onto the screen
	 * @author fuelvin
	 * @param g graphics to draw to
	 * @param s string to render onto the screen
	 * @param j unused
	 * @param x x position on screen to draw text to 
	 * @param y y position on screen to draw text to 
	 * @param type type of the text being drawn to the screen
	 */
	private void alphabetRender(Graphics g, String s, int j, int x, int y, int type) {
		if(!f) {
			maxLetters++;
		}
		for(int i = 0; i < s.length(); i++) {
			if(!f) {
				maxLetters++;
			}
			if(show) {
				if(s.charAt(i) == ' ') {
					g.drawImage(Asset.alphabet[26], x + letterCount * 6 * multiplier, y, 5 * multiplier, 8 * multiplier, null);
				} else if(s.charAt(i) == '!') {
					g.drawImage(Asset.alphabet[27], x + letterCount * 6 * multiplier, y, 5 * multiplier, 8 * multiplier, null);
				} else {
					if(s.charAt(i) == '/') {
						g.drawImage(Asset.numbers[10], x + i * 5 * multiplier, y, 4 * multiplier, 6 * multiplier, null);
					} else if(s.charAt(i) != '-' && (s.charAt(i) == '1' ||  s.charAt(i) == '2' || s.charAt(i) == '3' || s.charAt(i) == '4' || s.charAt(i) == '5' || s.charAt(i) == '6' || s.charAt(i) == '7' || s.charAt(i) == '8' || s.charAt(i) == '9' || s.charAt(i) == '0')) {
						if(type == 0) {
							g.drawImage(Asset.moneyNumbers[s.charAt(i) - 48], x + letterCount * 6 * multiplier, y, 5 * multiplier, 8 * multiplier, null);
						} else if(type == 1) {
							g.drawImage(Asset.xpNumbers[s.charAt(i) - 48], x + letterCount * 6 * multiplier, y, 5 * multiplier, 8 * multiplier, null);
						}
					} else {
						g.drawImage(Asset.alphabet[(s.toLowerCase().charAt(i) - 97)], x + letterCount * 6 * multiplier, y, 5 * multiplier, 8 * multiplier, null);
					}
				}
			}
			letterCount++;
		}
		g.drawImage(Asset.alphabet[26], x + letterCount * 6 * multiplier, y, 5 * multiplier, 8 * multiplier, null);
		letterCount++;
	}
	
	
	
	
	
	
	
	
	
	
}
