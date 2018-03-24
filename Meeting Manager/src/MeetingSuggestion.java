
public class MeetingSuggestion {

	private Meeting meetingDetails;
	private Employee suggestedBy;
	
	public MeetingSuggestion(Meeting meetingDetails, Employee suggestedBy) {
		this.meetingDetails = meetingDetails;
		this.suggestedBy = suggestedBy;
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
	 * @return the suggestedBy
	 */
	public Employee getSuggestedBy() {
		return suggestedBy;
	}
	/**
	 * @param suggestedBy the suggestedBy to set
	 */
	public void setSuggestedBy(Employee suggestedBy) {
		this.suggestedBy = suggestedBy;
	}
	
	
}
