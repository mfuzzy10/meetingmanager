import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class Menu {

	private final int CURRENT_YEAR;
	private MeetingManager manager;
	Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Menu menu = new Menu();
		menu.runMenu();
	}

	public Menu() {
		CURRENT_YEAR = 2018;
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
						viewAllMeetingSuggestionsMade();
						break;
						
					case "6":
						/* Cancel Meeting Suggestion Made:
						 * 
						 * 
						 */
						break;
						
					case "7":
						viewAllMeetingSuggestionsReceived();
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
		String title = UserInput.inputString();
		System.out.println("");
		
		System.out.println("description:");
		String description = UserInput.inputString();
		System.out.println("");
		
		String year = null;
		String month = null;
		String day = null;
		String hour = null;
		String minute = null;
		
		System.out.println("");
		System.out.println("Meeting starts:");
		System.out.println("");

		
		LocalDateTime startTime;
		startTime = getMeetingDateAndTimeInputFromUser();
		
		
		boolean validInput = false;
		LocalDateTime endTime;
		
		do  {
			System.out.println("");
			System.out.println("Meeting ends:");
			System.out.println("");
			endTime = getMeetingDateAndTimeInputFromUser();
			if (endTime.isAfter(startTime)) {
				validInput = true;
			} else {
				System.out.println("End time of the meeting must not precede the start time of the meeting");
			}
		} while (validInput == false);
		
		
		
		Meeting meetingToAdd = new Meeting(title, description, startTime, endTime);
		
		if (manager.addMeeting(meetingToAdd)) {
			System.out.println("Meeting added!");
			System.out.println("");
		} else {
			System.out.println("Meeting could not be added at the specified time!");
			System.out.println("");
		}
	}
	
	public LocalDateTime getMeetingDateAndTimeInputFromUser() {

		String year = null;
		String month = null;
		String day = null;
		String hour = null;
		String minute = null;
		
		boolean validInput = false;
		
		do {
			System.out.println("year (YYYY):");
			int intYear = UserInput.inputInt();
			System.out.println("");
			
			if (intYear != -1) {
				if (intYear >= CURRENT_YEAR && intYear < 9999) {
					year = Integer.toString(intYear);
					validInput = true;
				} else {
					System.out.println("Year must be " + CURRENT_YEAR + " or later");
				}
			} else {
				System.out.println("You must enter an integer");
			}
		} while (validInput == false);
		
		validInput = false;
		
		do {
			System.out.println("month (MM):");
			int intMonth = UserInput.inputInt();
			System.out.println("");
			
			if (intMonth != -1) {
				if (intMonth > 0 && intMonth < 13) {
					if (intMonth < 10) {
						month = "0" + Integer.toString(intMonth);
					} else {
						month = Integer.toString(intMonth);
					}
					validInput = true;
				} else {
					System.out.println("Month must be between 1 (January) and 12 (December)");
				}
			} else  {
				System.out.println("You must enter an integer");
			}
		} while (validInput == false);
		
		validInput = false;
		
		do {
			System.out.println("day (DD):");
			int intDay = UserInput.inputInt();
			System.out.println("");
			
			if (intDay != -1) {
				if (intDay > 0 && intDay < 30) {
					if (intDay < 10) {
						day = "0" + Integer.toString(intDay);
					} else {
						day = Integer.toString(intDay);
					}
					validInput = true;
				} else {
					System.out.println("Day must be between 1 and the number of days in the month you entered");
				}
			} else  {
				System.out.println("You must enter an integer");
			}
		} while (validInput == false);
		
		validInput = false;
		
		do {
			System.out.println("hour (HH):");
			int intHour = UserInput.inputInt();
			System.out.println("");
			
			if (intHour != -1) {
				if (intHour > -1 && intHour < 25) {
					if (intHour < 10) {
						hour = "0" + Integer.toString(intHour);
					} else {
						hour = Integer.toString(intHour);
					}
					validInput = true;
				} else {
					System.out.println("Hour must be between 0 and 24");
				}
			} else  {
				System.out.println("You must enter an integer");
			}
		} while (validInput == false);
		
		validInput = false;
		
		do {
			System.out.println("minute (MM):");
			int intMinute = UserInput.inputInt();
			System.out.println("");
			
			if (intMinute != -1) {
				if (intMinute > -1 && intMinute < 60) {
					if (intMinute < 10) {
						minute = "0" + Integer.toString(intMinute);
					} else {
						minute = Integer.toString(intMinute);
					}
					validInput = true;
				} else {
					System.out.println("Minute must be between 0 and 59");
				}
			} else  {
				System.out.println("You must enter an integer");
			}
		} while (validInput == false);
		
		LocalDateTime localDateTime = LocalDateTime.parse(""+ year +"-"+ month +"-"+ day +"T"+ hour +":"+ minute + ":00");
		
		return localDateTime;
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
	
	public void viewAllMeetingSuggestionsReceived() {
		
		System.out.println("");
		
		MeetingSuggestion[] meetingSuggestion = manager.getArrayOfMeetingSuggestionsReceived();
		
		for (int i = 0; i < meetingSuggestion.length; i++) {
			
			Meeting meetingDetails = meetingSuggestion[i].getMeetingDetails();
			LocalDateTime startTime = meetingDetails.getStartTime();
			LocalDateTime endTime = meetingDetails.getEndTime();
			
			System.out.println("# Meeting Suggestion " + (i+1) + " - " + meetingDetails.getTitle());
			System.out.println("# Meeting Start: " + startTime.getYear() + "/" + startTime.getMonthValue() + "/" + startTime.getDayOfMonth() + " @ " + startTime.getHour() + ":" + startTime.getMinute());
			System.out.println("# Meeting End: " + endTime.getYear() + "/" + endTime.getMonthValue() + "/" + endTime.getDayOfMonth() + " @ " + endTime.getHour() + ":" + endTime.getMinute());
			System.out.println("");
		}
		
		System.out.println("");
	}

	public void viewAllMeetingSuggestionsMade() {
		
		System.out.println("");
		
		MeetingSuggestion[] meetingSuggestion = manager.getArrayOfMeetingSuggestionsMade();
		
		for (int i = 0; i < meetingSuggestion.length; i++) {
			
			Meeting meetingDetails = meetingSuggestion[i].getMeetingDetails();
			LocalDateTime startTime = meetingDetails.getStartTime();
			LocalDateTime endTime = meetingDetails.getEndTime();
			
			System.out.println("# Meeting Suggestion " + (i+1) + " - " + meetingDetails.getTitle());
			System.out.println("# Meeting Start: " + startTime.getYear() + "/" + startTime.getMonthValue() + "/" + startTime.getDayOfMonth() + " @ " + startTime.getHour() + ":" + startTime.getMinute());
			System.out.println("# Meeting End: " + endTime.getYear() + "/" + endTime.getMonthValue() + "/" + endTime.getDayOfMonth() + " @ " + endTime.getHour() + ":" + endTime.getMinute());
			System.out.println("");
		}
		
		System.out.println("");
	}
	
}
