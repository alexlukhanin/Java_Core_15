/**
 * The homework on "Java Core" Course in LOGOS IT Academy
 * Lesson 09 - Exceptions, Java code convention
 * part 01 - based on lesson 08's homework
 * *
 */

package ua.lviv.lgs.hw15;

/**
 * * Console application with exception
 * 
 * @author alexl
 * 
 * @version 1.0
 *
 */

public class WrongInputConsoleParametersException extends Exception {

	public WrongInputConsoleParametersException() {
		super();
	}

	public WrongInputConsoleParametersException(String arg0) {
		super(arg0);
	}

}
