import java.util.Scanner;

public class Menu {

	private MeetingManager manager;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Menu menu = new Menu();
		menu.runMenu();
	}

	public Menu() {
		manager = new MeetingManager();
	}
	
	public void displayUserOptions() {
		
	}
	
	public void displayAdminOptions() {
		
	}
	
	public void runMenu() {
		
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			
			if (manager.getLoggedInEmployee() == null) {
				System.out.println("Enter your login details:");
				System.out.println("");
				
				System.out.println("username:");
				String username = scanner.nextLine();
				System.out.println("");
				
				System.out.println("password:");
				String password = scanner.nextLine();
				System.out.println("");
				manager.logIn(username, password);
			} else {
				
				while (manager.getLoggedInEmployee() != null) {
					
					System.out.println("");
					System.out.println("You have logged in Successfully!");
					System.out.println("");
					System.out.println("###################################################");
					System.out.println("#                Account Details                  #");
					System.out.println("###################################################");
					System.out.println("# First Name:                                     #");
					System.out.print("# " + manager.getLoggedInEmployee().getFirstName());
					for (int x = 0; x < (48 - manager.getLoggedInEmployee().getFirstName().length()); x++) {
						System.out.print(" ");
					}
					System.out.println("#");
					System.out.println("#                                                 #");
					System.out.println("# Last Name Name:                                 #");
					System.out.print("# " + manager.getLoggedInEmployee().getLastName());
					for (int x = 0; x < (48 - manager.getLoggedInEmployee().getLastName().length()); x++) {
						System.out.print(" ");
					}
					System.out.println("#");
					System.out.println("#                                                 #");
					System.out.println("# Unique Username:                                #");
					System.out.print("# " + manager.getLoggedInEmployee().getUniqueUsername());
					for (int x = 0; x < (48 - manager.getLoggedInEmployee().getUniqueUsername().length()); x++) {
						System.out.print(" ");
					}
					System.out.println("#");
					System.out.println("#                                                 #");
					System.out.println("# Account Type:                                   #");
					System.out.print("# " + manager.getLoggedInEmployee().getAccountType());
					for (int x = 0; x < (48 - manager.getLoggedInEmployee().getAccountType().toString().length()); x++) {
						System.out.print(" ");
					}
					System.out.println("#");
					System.out.println("###################################################");
					
					if (manager.getLoggedInEmployee().getAccountType() == AccountType.USER) {
						
						displayUserOptions();
						
					} else {
						
						displayAdminOptions();
						
					}
					
					System.out.println("Please choose your option: ");
					
					String option = scanner.nextLine();
					
					switch (option) {
					
					case "1":
						
						
					case "2":
						
						
					case "3":
						
						
					default:
						
						
					}
					
				}
				
			}
		}
	}
	
	
}
