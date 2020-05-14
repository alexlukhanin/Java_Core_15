/**
 * The homework on "Java Core" Course in LOGOS IT Academy
 *
 * 
 * *
 */

package ua.lviv.lgs.hw15;

/**
 * *
 * Console application with exception
 * @author alexl
 * 
 * @version 1.0
 *
 */

public class InputMenuString {
	/*This class checks menu input from user and assigns value of string, when it's correct*/
	boolean flag = false;
	private String string;


	public InputMenuString(String string) {
		super();
		
		if ((string.hashCode() >= 49 && string.hashCode() <= 55) ) {
			this.string = string;
			flag = true;
			
			
		} else {
			
			try {
				throw new WrongInputConsoleParametersException("Wrong choise, try again[1-7]...");
			} catch (WrongInputConsoleParametersException e) {
				System.out.println(e.getMessage());
				
				flag = false;
			}
			
		}
		
	}

	public String getString() {
		return string;
	}

	
	
	
	
}
