import java.util.Random;
import java.util.Scanner;
/**
 * @author Roman
 *
 */
public class Function {
	/**
	 * Asks the user to input a number 
	 *
	 */
	public static int inputInt(String message) {
		while(true){
			try {
				Scanner scanner = new Scanner(System.in);		
				System.out.printf(message + ": "); 
				return scanner.nextInt();
			}
			catch(java.util.InputMismatchException e) {
				System.out.println("Not a number\n");
			}
		}

	}
	/**
	 * asks the user to input a number 
	 *
	 */	
	public static String inputString(String message) {
		while(true){
			try {
				Scanner scanner = new Scanner(System.in);		
				System.out.printf(message + ": "); 
				return scanner.nextLine();
			}
			catch(java.util.InputMismatchException e) {
				System.out.println("Not a string\n");
			}
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
