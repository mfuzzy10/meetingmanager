import java.util.Random;
import java.util.Scanner;
/**
 * The UserInput class contains general sanitized methods for inputing a string and inputting an integer
 */
public class UserInput {
	/**
	 * Asks the user to input a number 
	 *
	 */
	public static int inputInt() {
		
		try {
			Scanner scanner = new Scanner(System.in);		
			return scanner.nextInt();
		}
		catch(java.util.InputMismatchException e) {
			return -1;
		}
	}
	/**
	 * asks the user to input a number 
	 *
	 */	
	public static String inputString() {
		
		try {
			Scanner scanner = new Scanner(System.in);		
			return scanner.nextLine();
		}
		catch(java.util.InputMismatchException e) {
			return null;
		}
	}
}
