/**
 * The MeetingSuggestion class stores a meeting suggested and all the employees to whom said meeting was suggested
 */
public class MeetingSuggestionMade {
	private Meeting meetingDetails;
	private Employee[] suggestedTo;
	
	
	
	public MeetingSuggestionMade(Meeting meetingDetails, Employee[] suggestedTo) {
		this.meetingDetails = meetingDetails;
		this.suggestedTo = suggestedTo;
	}
	
	/**
	 * @return the meetingDetails
	 */
	public Meeting getMeetingDetails() {
		return meetingDetails;
	}
	/**
	 * @param meetingDetails the meetingDetails to set
	 */
	public void setMeetingDetails(Meeting meetingDetails) {
		this.meetingDetails = meetingDetails;
	}
	/**
	 * @return the suggestedTo
	 */
	public Employee[] getSuggestedTo() {
		return suggestedTo;
	}
	/**
	 * @param suggestedTo the suggestedTo to set
	 */
	public void setSuggestedTo(Employee[] suggestedTo) {
		this.suggestedTo = suggestedTo;
	}
	
	
}
