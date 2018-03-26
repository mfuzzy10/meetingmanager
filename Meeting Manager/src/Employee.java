import java.util.LinkedList;
/**
 * The Employee class contains all the information for an employee including their credentials (first name, last name) 
 * and account information (username, password), as well as their meeting diary containing all their meetings.
 * It also has the meeting suggestions they proposed to other employees and any meetings suggested to them.
 */
public class Employee {

	private LinkedList<Meeting> diary;
	private LinkedList<MeetingSuggestion> meetingSuggestionsReceived;
	private LinkedList<MeetingSuggestionMade> meetingSuggestionsMade;
	private LinkedList<Notification> notifications;
	
	private String firstName;
	private String lastName;
	
	private String department;
	
	private String uniqueUsername;
	private String password;
	
	private AccountType accountType;
	
	public Employee(String firstName, String lastName, String uniqueUsername, String password, String department, AccountType accountType) {
		this.diary = new LinkedList<Meeting>();
		this.meetingSuggestionsReceived = new LinkedList<MeetingSuggestion>();
		this.meetingSuggestionsMade = new LinkedList<MeetingSuggestionMade>();
		this.notifications = new LinkedList<Notification>();
		
		this.firstName = firstName;
		this.lastName = lastName;
		
		this.department = department;
		
		this.uniqueUsername = uniqueUsername;
		this.password = password;
		
		this.accountType = accountType;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public void addNotification(Notification notification) {
		notifications.add(notification);
	}
	
}
