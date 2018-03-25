import java.util.Random;
import java.util.Scanner;
/**
 * @author Roman
 *
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

	/**
	 * returns an int between the given min and max numbers
	 *
	 */	
	public static int randInt(int min, int max) {
		Random rand = new Random();						
		int number = rand.nextInt((max - min) + 1) + min;
		return number;
	}
	
	/**
	 * waits a certain amount of time
	 *
	 */	
	public static void sleep(int msecs) {
		try {
			Thread.sleep(msecs);
		} 
		catch (InterruptedException e) {
		}
	}
}