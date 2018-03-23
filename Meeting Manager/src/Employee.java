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
	
	
	
}
