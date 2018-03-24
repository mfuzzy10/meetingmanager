import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class Menu {

	private MeetingManager manager;
	Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Menu menu = new Menu();
		menu.runMenu();
	}

	public Menu() {
		manager = new MeetingManager();
	}
	
	public void displayUserOptions() {
		
		System.out.println("1) Add Meeting");
		System.out.println("2) Delete Meeting");
		System.out.println("3) Suggest Meeting");
		System.out.println("4) Availability Search");
		System.out.println("5) View Meeting Suggestions Made");
		System.out.println("6) Cancel Meeting Suggestion Made");
		System.out.println("7) View Meeting Suggestions Received");
		System.out.println("8) Respond to Meeting Suggestion Received");
		System.out.println("9) View All Employees");
		
	}
	
	public void displayAdminOptions() {
		displayUserOptions();
		System.out.println("10) Add Employee");
		System.out.println("11) Remove Employee");
	}
	
	public void runMenu() {
		
		while (true) {
			
			if (manager.getLoggedInEmployee() == null) {
				System.out.println("");
				System.out.println("Enter your login details:");
				System.out.println("");
				
				System.out.println("username:");
				String username = scanner.nextLine();
				System.out.println("");
				
				System.out.println("password:");
				String password = scanner.nextLine();
				System.out.println("");
				
				if (manager.logIn(username, password)) {
					
					//Overkill but looks nice :p
					
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
					
				} else {
					System.out.println("");
					System.out.println("Wrong login details. Try again.");
					System.out.println("");
				}
				
			} else {
				
				while (manager.getLoggedInEmployee() != null) {
					
					System.out.println("");
					System.out.println("");
					System.out.println("Please choose your option: ");
					System.out.println("");
					
					if (manager.getLoggedInEmployee().getAccountType() == AccountType.USER) {
						
						displayUserOptions();
						
					} else {
						
						displayAdminOptions();
						
					}
					
					String option = scanner.nextLine();
					
					switch (option) {
					
					case "1":
						addMeeting();
						break;
						
					case "2":
						/* Delete Meeting:
						 * 
						 * 1) List users meeting in a numbered list
						 * 2) Ask user to enter number of the meeting they want to delete
						 */
						break;
						
					case "3":
						/* Suggest Meeting:
						 * 
						 * 1) Get meeting details from user
						 * 2) Get list of employees to suggest the meeting to from user
						 * 3) Send the meeting suggestion
						 * 
						 */
						break;
						
					case "4":
						/* Availability Search:
						 * 
						 * 
						 */
						break;
						
					case "5":
						/* View Meeting Suggestions Made:
						 * 
						 * 
						 */
						break;
						
					case "6":
						/* Cancel Meeting Suggestion Made:
						 * 
						 * 
						 */
						break;
						
					case "7":
						/* View Meeting Suggestions Received:
						 * 
						 * 
						 */
						break;
						
					case "8":
						/* Respond to Meeting Suggestion Received:
						 * 
						 * 
						 */
						break;
						
					case "9":
						viewAllEmployees();
						break;
						
					case "10":
						/* Add Employee:
						 * 
						 * 
						 */
						break;
						
					case "11":
						/* Remove Employee:
						 * 
						 * 
						 */
						break;
						
					default:
						System.out.println("Option: '" + option + "' does not exists");
						
					}
					
				}
				
			}
		}
	}
	
	public void addMeeting() {
		System.out.println("Enter meeting details: ");
		System.out.println("");
		
		System.out.println("title:");
		String title = scanner.nextLine();
		System.out.println("");
		
		System.out.println("description:");
		String description = scanner.nextLine();
		System.out.println("");
		
		String year;
		String month;
		String day;
		String hour;
		String minute;
		
		System.out.println("");
		System.out.println("Meeting starts:");
		System.out.println("");
		
		System.out.println("year:");
		year = scanner.nextLine();
		System.out.println("");
		
		System.out.println("month:");
		month = scanner.nextLine();
		System.out.println("");
		
		System.out.println("day:");
		day = scanner.nextLine();
		System.out.println("");
		
		System.out.println("hour:");
		hour = scanner.nextLine();
		System.out.println("");
		
		System.out.println("minute:");
		minute = scanner.nextLine();
		System.out.println("");
		
		LocalDateTime startTime = LocalDateTime.parse(""+ year +"-"+ month +"-"+ day +"T"+ hour +":"+ minute + ":00");
		
		
		System.out.println("");
		System.out.println("Meeting starts:");
		System.out.println("");
		
		System.out.println("year:");
		year = scanner.nextLine();
		System.out.println("");
		
		System.out.println("month:");
		month = scanner.nextLine();
		System.out.println("");
		
		System.out.println("day:");
		day = scanner.nextLine();
		System.out.println("");
		
		System.out.println("hour:");
		hour = scanner.nextLine();
		System.out.println("");
		
		System.out.println("minute:");
		minute = scanner.nextLine();
		System.out.println("");
		
		LocalDateTime endTime = LocalDateTime.parse(""+ year +"-"+ month +"-"+ day +"T"+ hour +":"+ minute + ":00");
		
		Meeting meetingToAdd = new Meeting(title, description, startTime, endTime);
		
		if (manager.addMeeting(meetingToAdd)) {
			System.out.println("Meeting added!");
			System.out.println("");
		} else {
			System.out.println("Meeting could not be added at the specified time!");
			System.out.println("");
		}
	}
	
	public void viewAllEmployees() {
		
		System.out.println("");
		
		Employee[] employees = manager.getArrayOfAllEmployees();
		
		for (int i = 0; i < employees.length; i++) {
			
			System.out.println("# Employee " + (i+1));
			System.out.println("# First Name: " + employees[i].getFirstName() + "   Last Name: " + employees[i].getLastName());
			System.out.println("# Department: " + employees[i].getDepartment() + "   Unique Username: " + employees[i].getUniqueUsername());
			System.out.println("");
		}
		
		System.out.println("");
	}
	
}
