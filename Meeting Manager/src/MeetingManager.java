import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
/**
 * The MeetingManager class contains all the meeting managing methods for employees: add/delete/edit meetings, suggest/cancel meetings and save/load a meeting to a file
 */
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
	
	public void logOut() {
		setLoggedInEmployee(null);
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
	public void cancelMeeting(MeetingSuggestionMade meetingToCancel, String cancelationExplanation) {
		for(int i = 0; i < meetingToCancel.getSuggestedTo().length; i++) {
			Notification notification = new Notification(getLoggedInEmployee(), "Meeting Cancellation", cancelationExplanation);
			meetingToCancel.getSuggestedTo()[i].addNotification(notification);
			MeetingSuggestion MS = new MeetingSuggestion(meetingToCancel.getMeetingDetails() ,getLoggedInEmployee());
			meetingToCancel.getSuggestedTo()[i].getMeetingSuggestionsReceived().remove(MS);
		}
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
	
	public String removeEmployee(Employee employeeToRemove) {
		if (loggedInEmployee.getAccountType() == AccountType.ADMIN) {
			employees.remove(employeeToRemove);
			return "Employee successfully removed";
		}
		
		return "You not have permission to remove employees";
		
	}
	
	/**
	 * @param employeeToAdd
	 * @return true: employee was added successfully; false: employee wasn't added successfully
	 */
	public String addEmployee(Employee employeeToAdd) {
		if (this.loggedInEmployee.getAccountType() == AccountType.ADMIN) {
			if (isUsernameFree(employeeToAdd.getUniqueUsername())) {
				this.employees.add(employeeToAdd);
				return "Employee successfully added";
			} else {
				return "Employee with this username already exists";
			}
		}
		return "You do not have permission to add employees";
	}
	
	/**
	 * @param username username to check against the existing usernames
	 * @return true: username is free; false: username is in use
	 */
	public boolean isUsernameFree(String username) {
		
		for (Employee employee : employees) {
			if (employee.getUniqueUsername().equals(username)) {
				return false;
			}
		}
		return true;
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
	private void setLoggedInEmployee(Employee loggedInEmployee) {
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
	private void setEmployees(LinkedList employees) {
		this.employees = employees;
	}
	
	public void saveMeetingData() {
		if (!employees.isEmpty()) {

	        FileOutputStream outputStream = null;
	        PrintWriter printWriter = null;
	        
	        try
	        {
	        	
	        	Files.createDirectories(Paths.get("saves/"));
	        	
	            int employeeIndex = 0;
	            
	            for (Employee employee : employees) {
	            	
	            	Files.createDirectories(Paths.get("saves/employee" + employeeIndex + "/employeedetails"));
	            	
	            	outputStream = new FileOutputStream("saves/employee" + employeeIndex + "/employeedetails/firstname.txt");
		            printWriter = new PrintWriter(outputStream);
		            printWriter.println(employee.getFirstName());
		            
		            printWriter.close();
		            
		            outputStream = new FileOutputStream("saves/employee" + employeeIndex + "/employeedetails/lastname.txt");
		            printWriter = new PrintWriter(outputStream);
		            printWriter.println(employee.getLastName());
		            
		            printWriter.close();
		            
		            outputStream = new FileOutputStream("saves/employee" + employeeIndex + "/employeedetails/username.txt");
		            printWriter = new PrintWriter(outputStream);
		            printWriter.println(employee.getUniqueUsername());
		            
		            printWriter.close();
		            
		            outputStream = new FileOutputStream("saves/employee" + employeeIndex + "/employeedetails/password.txt");
		            printWriter = new PrintWriter(outputStream);
		            printWriter.println(employee.getPassword());
		            
		            printWriter.close();
		            
		            outputStream = new FileOutputStream("saves/employee" + employeeIndex + "/employeedetails/accounttype.txt");
		            printWriter = new PrintWriter(outputStream);
		            printWriter.println(employee.getAccountType());
		            
		            printWriter.close();
		            
		            int index = 0;
		            
		            for (Meeting meeting : employee.getDiary()) {
		            	
		            	Files.createDirectories(Paths.get("saves/employee" + employeeIndex + "/meetings/meeting" + index));
		            	
		            	outputStream = new FileOutputStream("saves/employee" + employeeIndex + "/meetings/meeting" + index + "/title.txt");
			            printWriter = new PrintWriter(outputStream);
			            printWriter.println(meeting.getTitle());
			            
			            printWriter.close();
			            
			            outputStream = new FileOutputStream("saves/employee" + employeeIndex + "/meetings/meeting" + index + "/description.txt");
			            printWriter = new PrintWriter(outputStream);
			            printWriter.println(meeting.getDescription());
			            
			            printWriter.close();
			            
			            outputStream = new FileOutputStream("saves/employee" + employeeIndex + "/meetings/meeting" + index + "/starttime.txt");
			            printWriter = new PrintWriter(outputStream);
			            printWriter.println(meeting.getStartTime());
			            
			            printWriter.close();
			            
			            outputStream = new FileOutputStream("saves/employee" + employeeIndex + "/meetings/meeting" + index + "/endtime.txt");
			            printWriter = new PrintWriter(outputStream);
			            printWriter.println(meeting.getEndTime());
			            
			            printWriter.close();
			            
			            index++;
		            	
		            }
		            
		            index = 0;
		            
		            for (MeetingSuggestion meetingSuggestionReceived : employee.getMeetingSuggestionsReceived()) {
		            	
		            	Files.createDirectories(Paths.get("saves/employee" + employeeIndex + "/meeting-suggestions-received/meeting-suggestion-received" + index));
		            	
		            	outputStream = new FileOutputStream("saves/employee" + employeeIndex + "/meeting-suggestions-received/meeting-suggestion-received" + index + "/suggested-by.txt");
			            printWriter = new PrintWriter(outputStream);
			            printWriter.println(meetingSuggestionReceived.getSuggestedBy());
			            
			            printWriter.close();
			            
			            outputStream = new FileOutputStream("saves/employee" + employeeIndex + "/meeting-suggestions-received/meeting-suggestion-received" + index + "/title.txt");
			            printWriter = new PrintWriter(outputStream);
			            printWriter.println(meetingSuggestionReceived.getMeetingDetails().getTitle());
			            
			            printWriter.close();
			            
			            outputStream = new FileOutputStream("saves/employee" + employeeIndex + "/meeting-suggestions-received/meeting-suggestion-received" + index + "/desciption.txt");
			            printWriter = new PrintWriter(outputStream);
			            printWriter.println(meetingSuggestionReceived.getMeetingDetails().getDescription());
			            
			            printWriter.close();
			            
			            outputStream = new FileOutputStream("saves/employee" + employeeIndex + "/meeting-suggestions-received/meeting-suggestion-received" + index + "/starttime.txt");
			            printWriter = new PrintWriter(outputStream);
			            printWriter.println(meetingSuggestionReceived.getMeetingDetails().getStartTime());
			            
			            printWriter.close();

			            outputStream = new FileOutputStream("saves/employee" + employeeIndex + "/meeting-suggestions-received/meeting-suggestion-received" + index + "/endtime.txt");
			            printWriter = new PrintWriter(outputStream);
			            printWriter.println(meetingSuggestionReceived.getMeetingDetails().getEndTime());
			            
			            printWriter.close();
			            
		            }
		            
		            for (MeetingSuggestionMade meetingSuggestionMade : employee.getMeetingSuggestionsMade()) {
		            	
		            	Files.createDirectories(Paths.get("saves/employee" + employeeIndex + "/meeting-suggestions-made/meeting-suggestion-made" + index));
			            
			            outputStream = new FileOutputStream("saves/employee" + employeeIndex + "/meeting-suggestions-made/meeting-suggestion-made" + index + "/title.txt");
			            printWriter = new PrintWriter(outputStream);
			            printWriter.println(meetingSuggestionMade.getMeetingDetails().getTitle());
			            
			            printWriter.close();
			            
			            outputStream = new FileOutputStream("saves/employee" + employeeIndex + "/meeting-suggestions-made/meeting-suggestion-made" + index + "/desciption.txt");
			            printWriter = new PrintWriter(outputStream);
			            printWriter.println(meetingSuggestionMade.getMeetingDetails().getDescription());
			            
			            printWriter.close();
			            
			            outputStream = new FileOutputStream("saves/employee" + employeeIndex + "/meeting-suggestions-made/meeting-suggestion-made" + index + "/starttime.txt");
			            printWriter = new PrintWriter(outputStream);
			            printWriter.println(meetingSuggestionMade.getMeetingDetails().getStartTime());
			            
			            printWriter.close();

			            outputStream = new FileOutputStream("saves/employee" + employeeIndex + "/meeting-suggestions-made/meeting-suggestion-made" + index + "/endtime.txt");
			            printWriter = new PrintWriter(outputStream);
			            printWriter.println(meetingSuggestionMade.getMeetingDetails().getEndTime());
			            
			            printWriter.close();
			            
			            int index2 = 0;
			            
			            for (Employee suggestedTo : meetingSuggestionMade.getSuggestedTo()) {
			            	
			            	Files.createDirectories(Paths.get("saves/employee" + employeeIndex + "/meeting-suggestions-made/meeting-suggestion-made" + index + "/suggested-to"));
			            	
			            	outputStream = new FileOutputStream("saves/employee" + employeeIndex + "/meeting-suggestions-made/meeting-suggestion-made" + index + "/suggested-to/employee" + index2 + ".txt");
				            printWriter = new PrintWriter(outputStream);
				            printWriter.println(suggestedTo);
				            
				            printWriter.close();
				            
				            index2++;
			            	
			            }
			            
			            index ++;
			            
		            }
		            
		            index = 0;
		            
		            for (Notification notification : employee.getNotifications()) {
		            	
		            	Files.createDirectories(Paths.get("saves/employee" + employeeIndex + "/notifications/notification" + index));
		            	
		            	outputStream = new FileOutputStream("saves/employee" + employeeIndex + "/notifications/notification" + index + "/title.txt");
			            printWriter = new PrintWriter(outputStream);
			            printWriter.println(notification.getTitle());
			            
			            printWriter.close();
			            
			            outputStream = new FileOutputStream("saves/employee" + employeeIndex + "/notifications/notification" + index + "/content.txt");
			            printWriter = new PrintWriter(outputStream);
			            printWriter.println(notification.getContent());
		            	
			            printWriter.close();
			            
			            outputStream = new FileOutputStream("saves/employee" + employeeIndex + "/notifications/notification" + index + "/from.txt");
			            printWriter = new PrintWriter(outputStream);
			            printWriter.println(notification.getFrom());
			            
			            printWriter.close();
			            
		            }
		            
		            employeeIndex++;
		            
				}
	            

				outputStream.close();
	        }
	        catch (IOException e)
	        {
	            System.out.println("Error in file write: " + e);
	        }
		} else {
			System.out.println("There are no records to save. Save not performed.");
		}
	}
}



























