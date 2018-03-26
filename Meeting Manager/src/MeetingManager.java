import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;

public class MeetingManager {

	private Employee loggedInEmployee;
	private LinkedList<Employee> employees;
	
	public MeetingManager() {
		loggedInEmployee = null;
		employees = new LinkedList<Employee>();
		Employee admin = new Employee("admin", "admin", "admin", "admin", "Computing", AccountType.ADMIN);
		Employee roman = new Employee("Roman", "Brodskiy", "romanbrodskiy",  "admin", "Computing", AccountType.ADMIN);
		Employee matt = new Employee("Matt", "Robb", "mattrobb", "admin", "Computing", AccountType.ADMIN);
		Employee archie = new Employee("Archie", "Chalmers", "archiechalmers", "admin", "Computing", AccountType.ADMIN);
		Employee oskar = new Employee("Oskar", "Jankowski", "oskarjankowski", "admin", "Computing", AccountType.ADMIN);
		employees.add(admin);
		employees.add(roman);
		employees.add(matt);
		employees.add(archie);
		employees.add(oskar);
		
		LocalDateTime startTime = LocalDateTime.parse("2018-03-26T16:00:00");
		
		LocalDateTime endTime = LocalDateTime.parse("2018-03-26T18:00:00");
		
		Meeting meeting = new Meeting("Some Meeting","Meeting Desc", startTime, endTime);
		
		MeetingSuggestion meetingSuggestion = new MeetingSuggestion(meeting, admin);
		
		admin.getMeetingSuggestionsReceived().add(meetingSuggestion);
		roman.getMeetingSuggestionsReceived().add(meetingSuggestion);
		matt.getMeetingSuggestionsReceived().add(meetingSuggestion);
		archie.getMeetingSuggestionsReceived().add(meetingSuggestion);
		oskar.getMeetingSuggestionsReceived().add(meetingSuggestion);
		
		Employee suggestedTo[] = {admin, roman, matt, archie, oskar};
		
		MeetingSuggestionMade meetingSuggestionMade = new MeetingSuggestionMade(meeting, suggestedTo);
		
		admin.getMeetingSuggestionsMade().add(meetingSuggestionMade);
	}
	
	/**
	 * @param username
	 * @return true: logged in successfully; false: not logged in successfully (i.e. details don't match)
	 */
	public boolean logIn(String username, String password) {
		for (Employee employee : employees) {
			if (username.equals(employee.getUniqueUsername())) {
				if (password.equals(employee.getPassword())) {
					loggedInEmployee = employee;
					return true;
				} else {
					return false;
				}
			}
		}
			
		return false;
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
			return true;
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
			if (meetingToCheck.getStartTime().isAfter(meeting.getStartTime()) && meetingToCheck.getEndTime().isBefore(meeting.getEndTime())) {
				return false;
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
		
		//Create MeetingSuggestion with the Meeting passed in by reference to send to the array of Employee references passed in
		MeetingSuggestion meetingSuggestion = new MeetingSuggestion(meetingToSuggest, suggestedBy);
		
		//Send the MeetingSuggestion created to each Employee in the array of Employees passed in
		for (Employee employee: suggestTo) {
			employee.getMeetingSuggestionsReceived().add(meetingSuggestion);
		}
		
		//Create MeetingSuggestionMade type instance with Meeting passed in by reference and array of Employee references it was sent to
		MeetingSuggestionMade meetingSuggestionMade = new MeetingSuggestionMade(meetingToSuggest, suggestTo);
		
		//Add the MeetingSuggestionMade type created to the LinkedList of meeting suggestions made 
		loggedInEmployee.getMeetingSuggestionsMade().add(meetingSuggestionMade);
		
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
	
	/**
	 * @return array of references of all Employees
	 */
	public Employee[] getArrayOfAllEmployees() {
		
		Employee[] employeesArray = employees.toArray(new Employee[employees.size()]);
		
		return employeesArray;
	}
	
	/**
	 * @return array of references of all MeetingSuggestionsReceived by loggedInEmployee 
	 */
	public MeetingSuggestion[] getArrayOfMeetingSuggestionsReceived() {
		
		MeetingSuggestion[] meetingSuggestionsReceivedArray = loggedInEmployee.getMeetingSuggestionsReceived().toArray(new MeetingSuggestion[loggedInEmployee.getMeetingSuggestionsReceived().size()]);
		
		return meetingSuggestionsReceivedArray;
		
	}
	
	/**
	 * @return array of references of all MeetingSuggestions Made by loggedInEmployee 
	 */
	public MeetingSuggestionMade[] getArrayOfMeetingSuggestionsMade() {
		
		MeetingSuggestionMade[] meetingSuggestionsMadeArray = loggedInEmployee.getMeetingSuggestionsMade().toArray(new MeetingSuggestionMade[loggedInEmployee.getMeetingSuggestionsMade().size()]);
		
		return meetingSuggestionsMadeArray;
		
	}
	
	public TimePeriod[] availabilitySearch(Employee[] employees, LocalDateTime startTime, LocalDateTime endTime) {
		
		LinkedList<TimePeriod> notFreeTimes = new LinkedList<TimePeriod>();
		TimePeriod tempTimePeriod;
		
		for (Employee employee : employees) {
			Collections.sort(employee.getDiary());
			
			for (Meeting meeting : employee.getDiary()) {
				if (notFreeTimes.isEmpty()) {
					tempTimePeriod = new TimePeriod(meeting.getStartTime(), meeting.getEndTime());
					notFreeTimes.add(tempTimePeriod);
				} else {
					
					TimePeriod newNotFreeTimePeriod = new TimePeriod(meeting.getStartTime(), meeting.getEndTime());
					
					for (TimePeriod notFreeTimePeriod : notFreeTimes) {
						
						if (newNotFreeTimePeriod.getStartTime().isAfter(notFreeTimePeriod.getStartTime()) && newNotFreeTimePeriod.getStartTime().isBefore(notFreeTimePeriod.getEndTime())) {
							newNotFreeTimePeriod.setStartTime(notFreeTimePeriod.getStartTime());
							notFreeTimes.remove(notFreeTimePeriod);
							
						} else if (newNotFreeTimePeriod.getEndTime().isAfter(notFreeTimePeriod.getStartTime()) && newNotFreeTimePeriod.getEndTime().isBefore(notFreeTimePeriod.getEndTime())) {
							newNotFreeTimePeriod.setEndTime(notFreeTimePeriod.getEndTime());
							notFreeTimes.remove(notFreeTimePeriod);
							
						} else if (newNotFreeTimePeriod.getStartTime().isAfter(notFreeTimePeriod.getStartTime()) && newNotFreeTimePeriod.getEndTime().isBefore(notFreeTimePeriod.getEndTime())) {
							newNotFreeTimePeriod = new TimePeriod(notFreeTimePeriod.getStartTime(), notFreeTimePeriod.getEndTime());
							break;
							
						} else /* if (meeting.getStartTime().isBefore(notFreeTimePeriod.getStartTime()) && meeting.getEndTime().isAfter(notFreeTimePeriod.getEndTime())) */ {
							notFreeTimes.remove(notFreeTimePeriod);
							
						}
					}
					
					notFreeTimes.add(newNotFreeTimePeriod);
					Collections.sort(notFreeTimes);
					
				}
			}
		}
		
		if (notFreeTimes.isEmpty()) {
			return new TimePeriod[]{new TimePeriod(startTime, endTime)};
		}
		
		
		LinkedList<TimePeriod> freeTimes = new LinkedList<TimePeriod>();
		
		LocalDateTime tempStartTime = startTime;
		LocalDateTime tempEndTime = endTime;
		
		for (TimePeriod notFreeTimePeriod : notFreeTimes) {
			if (tempStartTime.equals(notFreeTimePeriod.getStartTime())) {
				tempStartTime = notFreeTimePeriod.getEndTime();
			} else {
				tempEndTime = notFreeTimePeriod.getStartTime();
				freeTimes.add(new TimePeriod(tempStartTime, tempEndTime));
				tempStartTime = notFreeTimePeriod.getEndTime();
			}
		}
		
		if (tempEndTime != endTime) {
			tempEndTime = endTime;
			freeTimes.add(new TimePeriod(tempStartTime, tempEndTime));
		}
		
		return freeTimes.toArray(new TimePeriod[freeTimes.size()]);
			
	}
	
}



























