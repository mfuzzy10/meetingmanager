import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

public class Employee {

	private LinkedList<Meeting> diary;
	private LinkedList<MeetingSuggestion> meetingSuggestionsReceived;
	private LinkedList<MeetingSuggestionMade> meetingSuggestionsMade;
	private LinkedList<Notification> notifications;
	
	private String firstName;
	private String lastName;
	private String uniqueUsername;
	
	private AccountType accountType;

	/**
	 * Blank Constructor for the Employee class.
	 */
	public Employee() {
		uniqueUsername = "";
		firstName = "";
		lastName = "";
		accountType = accountType.USER;
		diary = new LinkedList<Meeting>();
	}
	
	/**
	 * Filled Constructor for the Employee class.
	 * 
	 * @param uniqueUsername The username of the employee.
	 * @param admin Whether this employee is an administrator or not.
	 */
	public Employee(String uniqueUsername, String firstName, String lastName, AccountType accountType) {
		this.uniqueUsername = uniqueUsername;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountType = accountType;
		this.diary = new LinkedList<Meeting>();
	}
	
	/**
	 * @return the diary
	 */
	public LinkedList<Meeting> getDiary() {
		return diary;
	}

	/**
	 * @param diary the diary to set
	 */
	public void setDiary(LinkedList<Meeting> diary) {
		this.diary = diary;
	}

	/**
	 * @return the meetingSuggestionsReceived
	 */
	public LinkedList<MeetingSuggestion> getMeetingSuggestionsReceived() {
		return meetingSuggestionsReceived;
	}

	/**
	 * @param meetingSuggestionsReceived the meetingSuggestionsReceived to set
	 */
	public void setMeetingSuggestionsReceived(LinkedList<MeetingSuggestion> meetingSuggestionsReceived) {
		this.meetingSuggestionsReceived = meetingSuggestionsReceived;
	}

	/**
	 * @return the meetingSuggestionsMade
	 */
	public LinkedList<MeetingSuggestionMade> getMeetingSuggestionsMade() {
		return meetingSuggestionsMade;
	}

	/**
	 * @param meetingSuggestionsMade the meetingSuggestionsMade to set
	 */
	public void setMeetingSuggestionsMade(LinkedList<MeetingSuggestionMade> meetingSuggestionsMade) {
		this.meetingSuggestionsMade = meetingSuggestionsMade;
	}

	/**
	 * @return the notifications
	 */
	public LinkedList<Notification> getNotifications() {
		return notifications;
	}

	/**
	 * @param notifications the notifications to set
	 */
	public void setNotifications(LinkedList<Notification> notifications) {
		this.notifications = notifications;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the uniqueUsername
	 */
	public String getUniqueUsername() {
		return uniqueUsername;
	}

	/**
	 * @param uniqueUsername the uniqueUsername to set
	 */
	public void setUniqueUsername(String uniqueUsername) {
		this.uniqueUsername = uniqueUsername;
	}

	/**
	 * @return the accountType
	 */
	public AccountType getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	
	/**
	 * Add an element to the Linked List.
	 * 
	 * @param date The date of the new meeting.
	 * @param startTime The time the new meeting will start at.
	 * @param endTime The time the new meeting will end at.
	 * @param description A description of the meeting.
	 */
	public boolean isValidMeeting(int day, int month, int year, int startHour, int startMinute, int endHour, int endMinute, String description) {
		if(day > 31 || day < 1) {
			return false;
		}
		if(month > 12 || month < 1) {
			return false;
		}
		if(year < 1) {
			return false;
		}
		if(startHour > 24 || startHour < 1) {
			return false;
		}
		if(endHour > 24 || endHour < 1) {
			return false;
		}
		if(startMinute > 59 || startMinute < 1) {
			return false;
		}	
		if(endMinute > 59 || endMinute < 1) {
			return false;
		}
		return true;
	}
	
	public void add(int day, int month, int year, int startHour, int startMinute, int endHour, int endMinute, String description, String title) {
		if(isValidMeeting(day, month, year, startHour, startMinute, endHour, endMinute, description)) {
			diary.add(new Meeting(LocalDate.of(year, month, day), LocalTime.of(startHour, startMinute), LocalTime.of(endHour, endMinute), description, title));
		}
		else {
			System.out.println("Invalid meeting parameters");
		}
		
	}
	
	/**
	 * Delete an element from the Linked List.
	 */
	public void delete(LocalDate date, LocalTime startTime) {
		
		int i = 0;
		boolean found = false;
		
		while (i < diary.size() && !found) {
			if (date == diary.get(i).getDate() && startTime == diary.get(i).getStartTime()) {
				found = true;
			}
			else {
				i++;
			}
		}
		
		if (found) {
			diary.remove(i);
		}
		else {
			System.out.println("Not Found");
		}
	}
	
	/**
	 * TODO Edit an element in the Linked List.
	 */
	public void edit() {
		
		
	}
	
	/**
	 * Print out the Linked List.
	 */
	public void displayMeetings() {
		for (int i = 0; i < diary.size(); i++) {
			System.out.println("Meeting #" + (i+1) + " - " + diary.get(i).getTitle());
			System.out.println("Date: " +diary.get(i).getRealDate());
			System.out.println("Time: " + diary.get(i).getStartTime() + " - " + diary.get(i).getEndTime());
			System.out.println("Description: " +diary.get(i).getDescription());
			System.out.println();
		}
	}
	
	/**
	 * Prints the employee information (admin only?)
	 */
	public void displayEmployee() {
		System.out.print("Username: " + getUniqueUsername());
		if(getAccountType() == AccountType.ADMIN) {
			System.out.println("     ADMIN");
		}
		else {
			System.out.println();
		}
		System.out.println("Name: " + getFirstName() + " " + getLastName());
		System.out.println();
	}
	
}
