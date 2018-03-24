import java.util.LinkedList;

public class MeetingManager {

	private Employee loggedInEmployee;
	private LinkedList employees;
	
	public MeetingManager() {
		loggedInEmployee = null;
		employees = null;
	}
	
	/**
	 * This method adds the Meeting passed in by reference to
	 * the diary of the loggedInEmployee, if there is space for the
	 * meeting in the diary.
	 * 
	 * @param meetingToAdd reference of the meeting to be added
	 * @return true: meeting was successfully added; false: meeting was not successfully added
	 */
	public boolean addMeeting(Meeting meetingToAdd) {
		if (meetingCanBeAdded(meetingToAdd)) {
			loggedInEmployee.getDiary().add(meetingToAdd);
		}
		
		return false;
	}
	
	/**
	 * This method checks if there is space in the diary of the
	 * loggedInEmployee for the meeting passed in by reference.
	 * 
	 * @param meetingToCheck
	 * @return true: meeting can be added, false: meeting cannot be added
	 */
	private boolean meetingCanBeAdded(Meeting meetingToCheck) {
		
		for (Meeting meeting : loggedInEmployee.getDiary()) {
			if (meeting.getDate() == meetingToCheck.getDate()) {
				if (meetingToCheck.getStartTime().isAfter(meeting.getStartTime()) && meetingToCheck.getEndTime().isBefore(meeting.getEndTime())) {
					return false;
				}
			}
		}
		
		return true;
	}

	/**
	 * Deletes the reference of the Meeting to be deleted from the diary
	 * of the Employee requesting the operation (the employee
	 * currently logged in).
	 * 
	 * @param meetingToDelete reference of the meeting to be deleted
	 */
	public void deleteMeeting(Meeting meetingToDelete) {
		loggedInEmployee.getDiary().remove(meetingToDelete);
	}
	
	/**
	 * Checks that the new meeting details are viable in the diary where the
	 * meeting is being edited. If so, replaces the meeting details of Meeting
	 * passed in by reference as meetingToEdit with new meeting details passed
	 * in by reference as newDetails.
	 * 
	 * @param meetingToEdit reference of the meeting to edit
	 * @param newDetails reference of new meeting which values will be placed in the current meeting
	 */
	public void editMeeting(Meeting meetingToEdit, Meeting newDetails) {
		
	}
	
	/**
	 * This method goes through the array of Employees, passed in by reference as suggestTo:
	 * It adds a MeetingSuggestion to each of these Employees LinkedList of MeetingSuggestion
	 * with the references passed in the parameters as meetingToSuggest and suggestedBy.
	 * 
	 * @param meetingToSuggest reference of the meeting to be suggested to the employees
	 * @param suggestTo reference array of employees which the meeting will be suggested to
	 * @param suggestedBy reference of employee who suggested the meeting
	 */
	public void suggestMeeting(Meeting meetingToSuggest, Employee[] suggestTo, Employee suggestedBy) {
		
	}
	
	/**
	 * This method goes through each Employee the MeetingSuggestion was made to
	 * and for each Employee it deletes the Meeting either from their LinkedList
	 * of Meetings (their diary), if the meeting was accepted, or deletes the
	 * MeetingSuggestion with this Meeting reference from their LinkedList of
	 * MeetingSuggestionsReceived.
	 * 
	 * This method also sends a Notification to each Employee that the Meeting
	 * being cancelled was suggested to, explaining why the meeting was cancelled,
	 * if an explanation was provided by the canceller.
	 * 
	 * @param meetingToCancel reference of the meeting suggestion which holds the
	 * 		  references of Employees the suggestion was made to and the reference
	 * 		  of the Meeting suggested
	 * 
	 * @param cancelationExplenation explanation for why the meeting was cancelled
	 * 		  if necessary
	 */
	public void cancelMeeting(MeetingSuggestionMade meetingToCancel, String cancelationExplenation) {
		
	}
	
	/**
	 * @return true: meeting has been accepted; false: cannot accept meeting
	 */
	public boolean canAttendSuggestedMeeting() {
		return true;
	}
	
	public void cannotAttendSuggestedMeeting() {
		
	}
	
	public MeetingSuggestionMade[] showMeetingSuggestionsMade() {
		return null;
	}
	
	public void saveMeetingData() {
		
	}
	
	/**
	 * @return true: data loaded successfully; false: data not loaded successfully (i.e. Employees AccountType does not have permission to load data)
	 */
	public boolean loadMeetingData() {
		return false;
	}
	
		/**
	 * @return the loggedInEmployee
	 */
	public Employee getLoggedInEmployee() {
		return loggedInEmployee;
	}

	/**
	 * @param loggedInEmployee the loggedInEmployee to set
	 */
	public void setLoggedInEmployee(Employee loggedInEmployee) {
		this.loggedInEmployee = loggedInEmployee;
	}

	/**
	 * @return the employees
	 */
	public LinkedList getEmployees() {
		return employees;
	}

	/**
	 * @param employees the employees to set
	 */
	public void setEmployees(LinkedList employees) {
		this.employees = employees;
	}
}



























