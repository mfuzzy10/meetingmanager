import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
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
		
		loadMeetingData();
		
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
		saveMeetingData();
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
		
		Notification notification = new Notification(getLoggedInEmployee(), "Meeting Cancellation - " + meetingToCancel.getMeetingDetails().getTitle(), "Reason: " + cancelationExplanation);
		
		MeetingSuggestion meetingSuggestionToCancel = null;
		
		for(MeetingSuggestion meetingSugesstion : meetingToCancel.getSuggestedTo()[0].getMeetingSuggestionsReceived()) {
			if (meetingSugesstion.getMeetingDetails() == meetingToCancel.getMeetingDetails()) {
				meetingSuggestionToCancel = meetingSugesstion;
				break;
			}
		}
		
		for (Employee suggestedTo : meetingToCancel.getSuggestedTo()) {
			suggestedTo.addNotification(notification);
			suggestedTo.getMeetingSuggestionsReceived().remove(meetingSuggestionToCancel);
			suggestedTo.getDiary().remove(meetingToCancel.getMeetingDetails());
		}
		
		loggedInEmployee.getMeetingSuggestionsMade().remove(meetingToCancel);
		
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
	public LinkedList<Employee> getEmployees() {
		return employees;
	}

	/**
	 * @param employees the employees to set
	 */
	private void setEmployees(LinkedList<Employee> employees) {
		this.employees = employees;
	}
	
	//Borrowed temporarily from stackoverflow: https://stackoverflow.com/questions/13195797/delete-all-files-in-directory-but-not-directory-one-liner-solution/13195870
	private void purgeDirectory(File dir) {
	    for (File file: dir.listFiles()) {
	        if (file.isDirectory()) purgeDirectory(file);
	        file.delete();
	    }
	}
	
	public void saveMeetingData() {
		if (!employees.isEmpty()) {

			FileOutputStream outputStream = null;
			PrintWriter printWriter = null;
			
			try
			{
				purgeDirectory(new File("saves/"));
				Files.createDirectories(Paths.get("saves/"));
				
				int employeeIndex = 0;
				
				for (Employee employee : employees) {
					
					Files.createDirectories(Paths.get("saves/employee" + employeeIndex + "/employee-details"));
					Files.createDirectories(Paths.get("saves/employee" + employeeIndex + "/meetings"));
					Files.createDirectories(Paths.get("saves/employee" + employeeIndex + "/meeting-suggestions-received"));
					Files.createDirectories(Paths.get("saves/employee" + employeeIndex + "/meeting-suggestions-made"));
					Files.createDirectories(Paths.get("saves/employee" + employeeIndex + "/notifications"));
					
					outputStream = new FileOutputStream("saves/employee" + employeeIndex + "/employee-details/firstname.txt");
					printWriter = new PrintWriter(outputStream);
					printWriter.println(employee.getFirstName());
					
					printWriter.close();
					
					outputStream = new FileOutputStream("saves/employee" + employeeIndex + "/employee-details/lastname.txt");
					printWriter = new PrintWriter(outputStream);
					printWriter.println(employee.getLastName());
					printWriter.close();
					
					outputStream = new FileOutputStream("saves/employee" + employeeIndex + "/employee-details/username.txt");
					printWriter = new PrintWriter(outputStream);
					printWriter.println(employee.getUniqueUsername());
					printWriter.close();
					
					outputStream = new FileOutputStream("saves/employee" + employeeIndex + "/employee-details/password.txt");
					printWriter = new PrintWriter(outputStream);
					printWriter.println(employee.getPassword());
					printWriter.close();
					
					outputStream = new FileOutputStream("saves/employee" + employeeIndex + "/employee-details/department.txt");
					printWriter = new PrintWriter(outputStream);
					printWriter.println(employee.getDepartment());
					printWriter.close();
					
					outputStream = new FileOutputStream("saves/employee" + employeeIndex + "/employee-details/accounttype.txt");
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
						printWriter.println(meetingSuggestionReceived.getSuggestedBy().getUniqueUsername());
						printWriter.close();
						
						outputStream = new FileOutputStream("saves/employee" + employeeIndex + "/meeting-suggestions-received/meeting-suggestion-received" + index + "/title.txt");
						printWriter = new PrintWriter(outputStream);
						printWriter.println(meetingSuggestionReceived.getMeetingDetails().getTitle());
						printWriter.close();
						
						outputStream = new FileOutputStream("saves/employee" + employeeIndex + "/meeting-suggestions-received/meeting-suggestion-received" + index + "/description.txt");
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
						
						outputStream = new FileOutputStream("saves/employee" + employeeIndex + "/meeting-suggestions-made/meeting-suggestion-made" + index + "/description.txt");
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
							printWriter.println(suggestedTo.getUniqueUsername());
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
						printWriter.println(notification.getFrom().getUniqueUsername());
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
	
	public void loadMeetingData() {
		
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		File saveFile = null;
		
		{
			try {
				
				saveFile = new File("saves/");
				File[] employeeFiles = null;
				employeeFiles = saveFile.listFiles();
				
				if (employeeFiles.length > 0) {
					for (File employeeFile : employeeFiles) {
						
						Employee newEmployee = null;
						
						File employeeDetails = new File(employeeFile + "/employee-details");
						
						if (employeeDetails.exists()) {
							
							Path employeeDetailsPath = Paths.get(employeeDetails.getPath());
							
							if (Files.exists(employeeDetailsPath, LinkOption.NOFOLLOW_LINKS)) {
								
								if (Files.exists(Paths.get(employeeDetailsPath + "/firstname.txt"), LinkOption.NOFOLLOW_LINKS) &&
									Files.exists(Paths.get(employeeDetailsPath + "/lastname.txt"), LinkOption.NOFOLLOW_LINKS) &&
									Files.exists(Paths.get(employeeDetailsPath + "/username.txt"), LinkOption.NOFOLLOW_LINKS) &&
									Files.exists(Paths.get(employeeDetailsPath + "/password.txt"), LinkOption.NOFOLLOW_LINKS) &&
									Files.exists(Paths.get(employeeDetailsPath + "/accounttype.txt"), LinkOption.NOFOLLOW_LINKS)) {
									
									String firstName = null;
									String lastName = null;
									String username = null;
									String password = null;
									String department = null;
									AccountType accountType = null;
									
									fileReader = new FileReader(employeeDetails + "/firstname.txt");
									bufferedReader = new BufferedReader(fileReader);
									firstName = bufferedReader.readLine();
									bufferedReader.close();
									
									fileReader = new FileReader(employeeDetails + "/lastname.txt");
									bufferedReader = new BufferedReader(fileReader);
									lastName = bufferedReader.readLine();
									bufferedReader.close();
										
									fileReader = new FileReader(employeeDetails + "/username.txt");
									bufferedReader = new BufferedReader(fileReader);
									username = bufferedReader.readLine();
									bufferedReader.close();
									
									fileReader = new FileReader(employeeDetails + "/password.txt");
									bufferedReader = new BufferedReader(fileReader);
									password = bufferedReader.readLine();
									bufferedReader.close();
									
									fileReader = new FileReader(employeeDetails + "/accounttype.txt");
									bufferedReader = new BufferedReader(fileReader);
									String accType = bufferedReader.readLine();
									bufferedReader.close();
									
									if (accType == "USER") {
										accountType = AccountType.USER;
									} else {
										accountType = AccountType.ADMIN;
									}
									
									if (Files.exists(Paths.get(employeeDetailsPath + "/department.txt"), LinkOption.NOFOLLOW_LINKS)) {
										fileReader = new FileReader(employeeDetails + "/department.txt");
										bufferedReader = new BufferedReader(fileReader);
										department = bufferedReader.readLine();
										bufferedReader.close();
									}
									
									newEmployee = new Employee(firstName, lastName, username, password, department, accountType);
									employees.add(newEmployee);
									
								}		
							}
						}
					}
				}
				
				if (employeeFiles.length > 0) {
					for (File employeeFile : employeeFiles) {
						
						Employee currentEmployee = null;
						
						File employeeDetails = new File(employeeFile + "/employee-details");
						
						if (employeeDetails.exists()) {
							
							Path employeeDetailsPath = Paths.get(employeeDetails.getPath());
							
							if (Files.exists(employeeDetailsPath, LinkOption.NOFOLLOW_LINKS)) {
								
								if (Files.exists(Paths.get(employeeDetailsPath + "/firstname.txt"), LinkOption.NOFOLLOW_LINKS) &&
									Files.exists(Paths.get(employeeDetailsPath + "/lastname.txt"), LinkOption.NOFOLLOW_LINKS) &&
									Files.exists(Paths.get(employeeDetailsPath + "/username.txt"), LinkOption.NOFOLLOW_LINKS) &&
									Files.exists(Paths.get(employeeDetailsPath + "/password.txt"), LinkOption.NOFOLLOW_LINKS) &&
									Files.exists(Paths.get(employeeDetailsPath + "/accounttype.txt"), LinkOption.NOFOLLOW_LINKS)) {
									
									String username = null;
										
									fileReader = new FileReader(employeeDetails + "/username.txt");
									bufferedReader = new BufferedReader(fileReader);
									username = bufferedReader.readLine();
									bufferedReader.close();
									
									currentEmployee = getEmployeeByUsername(username);
									
									File meetings = new File(employeeFile + "/meetings");
									File meetingSuggestionsReceived = new File(employeeFile + "/meeting-suggestions-received");
									File meetingSuggestionsMade = new File(employeeFile + "/meeting-suggestions-made");
									File notifications = new File(employeeFile + "/notifications");
									
									if (meetings.exists()) {
										File meetingFiles[] = null;
										meetingFiles = meetings.listFiles();
										
										if (meetingFiles.length > 0) {
											for (File meetingFile : meetingFiles) {
												
												Path nextMeetingFilePath = Paths.get(meetingFile.getPath());
												
												if (Files.exists(Paths.get(nextMeetingFilePath + "/title.txt"), LinkOption.NOFOLLOW_LINKS) &&
													Files.exists(Paths.get(nextMeetingFilePath + "/description.txt"), LinkOption.NOFOLLOW_LINKS) &&
													Files.exists(Paths.get(nextMeetingFilePath + "/starttime.txt"), LinkOption.NOFOLLOW_LINKS) &&
													Files.exists(Paths.get(nextMeetingFilePath + "/endtime.txt"), LinkOption.NOFOLLOW_LINKS)) {

													String title = null;
													String description = null;
													LocalDateTime startTime = null;
													LocalDateTime endTime = null;		
													
													fileReader = new FileReader(nextMeetingFilePath + "/title.txt");
													bufferedReader = new BufferedReader(fileReader);
													title = bufferedReader.readLine();
													bufferedReader.close();
													
													fileReader = new FileReader(nextMeetingFilePath + "/description.txt");
													bufferedReader = new BufferedReader(fileReader);
													description = bufferedReader.readLine();
													bufferedReader.close();
													
													fileReader = new FileReader(nextMeetingFilePath + "/starttime.txt");
													bufferedReader = new BufferedReader(fileReader);
													startTime = LocalDateTime.parse(bufferedReader.readLine());
													bufferedReader.close();
													
													fileReader = new FileReader(nextMeetingFilePath + "/endtime.txt");
													bufferedReader = new BufferedReader(fileReader);
													endTime = LocalDateTime.parse(bufferedReader.readLine());
													bufferedReader.close();
													
													currentEmployee.getDiary().add(new Meeting(title, description, startTime, endTime));
													
												}
											}
										}
									}
									
									if (meetingSuggestionsReceived.exists()) {
										File meetingSuggestionsReceivedFiles[] = null;
										meetingSuggestionsReceivedFiles = meetingSuggestionsReceived.listFiles();
										
										if (meetingSuggestionsReceivedFiles.length > 0) {
											for(File receivedMeetingSuggestionFile : meetingSuggestionsReceivedFiles) {
												
												Path nextReceivedMeetingSuggestionPath = Paths.get(receivedMeetingSuggestionFile.getPath());
												
												if (Files.exists(Paths.get(nextReceivedMeetingSuggestionPath + "/suggested-by.txt"), LinkOption.NOFOLLOW_LINKS) &&
													Files.exists(Paths.get(nextReceivedMeetingSuggestionPath + "/title.txt"), LinkOption.NOFOLLOW_LINKS) &&
													Files.exists(Paths.get(nextReceivedMeetingSuggestionPath + "/description.txt"), LinkOption.NOFOLLOW_LINKS) &&
													Files.exists(Paths.get(nextReceivedMeetingSuggestionPath + "/starttime.txt"), LinkOption.NOFOLLOW_LINKS) &&
													Files.exists(Paths.get(nextReceivedMeetingSuggestionPath + "/endtime.txt"), LinkOption.NOFOLLOW_LINKS)) {
													
													String suggestedBy = null;
													String title = null;
													String description = null;
													LocalDateTime startTime = null;
													LocalDateTime endTime = null;		
													
													fileReader = new FileReader(nextReceivedMeetingSuggestionPath + "/suggested-by.txt");
													bufferedReader = new BufferedReader(fileReader);
													suggestedBy = bufferedReader.readLine();
													bufferedReader.close();
													
													fileReader = new FileReader(nextReceivedMeetingSuggestionPath + "/title.txt");
													bufferedReader = new BufferedReader(fileReader);
													title = bufferedReader.readLine();
													bufferedReader.close();
													
													fileReader = new FileReader(nextReceivedMeetingSuggestionPath + "/description.txt");
													bufferedReader = new BufferedReader(fileReader);
													description = bufferedReader.readLine();
													bufferedReader.close();
													
													fileReader = new FileReader(nextReceivedMeetingSuggestionPath + "/starttime.txt");
													bufferedReader = new BufferedReader(fileReader);
													startTime = LocalDateTime.parse(bufferedReader.readLine());
													bufferedReader.close();
													
													fileReader = new FileReader(nextReceivedMeetingSuggestionPath + "/endtime.txt");
													bufferedReader = new BufferedReader(fileReader);
													endTime = LocalDateTime.parse(bufferedReader.readLine());
													bufferedReader.close();
													
													Meeting meetingDetails = new Meeting(title, description, startTime, endTime);
													
													currentEmployee.getMeetingSuggestionsReceived().add(new MeetingSuggestion(meetingDetails, getEmployeeByUsername(suggestedBy)));
													
												}
												
											}
										}
									}
									
									
									if (meetingSuggestionsMade.exists()) {
										File meetingSuggestionsMadeFiles[] = null;
										meetingSuggestionsMadeFiles = meetingSuggestionsMade.listFiles();
										
										if (meetingSuggestionsMadeFiles.length > 0) {
											for(File madeMeetingSuggestionFile : meetingSuggestionsMadeFiles) {
												
												Path nextMadeMeetingSuggestionPath = Paths.get(madeMeetingSuggestionFile.getPath());
												
												if (Files.exists(Paths.get(nextMadeMeetingSuggestionPath + "/suggested-to"), LinkOption.NOFOLLOW_LINKS) &&
													Files.exists(Paths.get(nextMadeMeetingSuggestionPath + "/title.txt"), LinkOption.NOFOLLOW_LINKS) &&
													Files.exists(Paths.get(nextMadeMeetingSuggestionPath + "/description.txt"), LinkOption.NOFOLLOW_LINKS) &&
													Files.exists(Paths.get(nextMadeMeetingSuggestionPath + "/starttime.txt"), LinkOption.NOFOLLOW_LINKS) &&
													Files.exists(Paths.get(nextMadeMeetingSuggestionPath + "/endtime.txt"), LinkOption.NOFOLLOW_LINKS)) {
													
													File suggestedTo = new File(nextMadeMeetingSuggestionPath + "/suggested-to");
													File[] suggestedToFileArray = suggestedTo.listFiles();
													
													LinkedList<Employee> suggestedToList = new LinkedList<Employee>();
													String title = null;
													String description = null;
													LocalDateTime startTime = null;
													LocalDateTime endTime = null;		
													
													fileReader = new FileReader(nextMadeMeetingSuggestionPath + "/title.txt");
													bufferedReader = new BufferedReader(fileReader);
													title = bufferedReader.readLine();
													bufferedReader.close();
													
													fileReader = new FileReader(nextMadeMeetingSuggestionPath + "/description.txt");
													bufferedReader = new BufferedReader(fileReader);
													description = bufferedReader.readLine();
													bufferedReader.close();
													
													fileReader = new FileReader(nextMadeMeetingSuggestionPath + "/starttime.txt");
													bufferedReader = new BufferedReader(fileReader);
													startTime = LocalDateTime.parse(bufferedReader.readLine());
													bufferedReader.close();
													
													fileReader = new FileReader(nextMadeMeetingSuggestionPath + "/endtime.txt");
													bufferedReader = new BufferedReader(fileReader);
													endTime = LocalDateTime.parse(bufferedReader.readLine());
													bufferedReader.close();
													
													for (File nextSuggestedTo : suggestedToFileArray) {
														
														fileReader = new FileReader(nextSuggestedTo);
														bufferedReader = new BufferedReader(fileReader);
														suggestedToList.add(getEmployeeByUsername(bufferedReader.readLine()));
														bufferedReader.close();
														
													}
													
													Meeting meetingDetails = new Meeting(title, description, startTime, endTime);
													
													currentEmployee.getMeetingSuggestionsMade().add(new MeetingSuggestionMade(meetingDetails, suggestedToList.toArray(new Employee[suggestedToList.size()])));
													
												}
												
											}
										}
									}
									
									
									if (notifications.exists()) {
										File notificationFiles[] = null;
										notificationFiles = notifications.listFiles();
										
										if (notificationFiles.length > 0) {
											for(File notificationFile : notificationFiles) {
												
												Path nextNotificationPath = Paths.get(notificationFile.getPath());
												
												if (Files.exists(Paths.get(nextNotificationPath + "/title.txt"), LinkOption.NOFOLLOW_LINKS) &&
													Files.exists(Paths.get(nextNotificationPath + "/content.txt"), LinkOption.NOFOLLOW_LINKS) &&
													Files.exists(Paths.get(nextNotificationPath + "/from.txt"), LinkOption.NOFOLLOW_LINKS)) {
													
													String title = null;
													String content = null;
													String from = null;
													
													fileReader = new FileReader(notificationFile + "/title.txt");
													bufferedReader = new BufferedReader(fileReader);
													title = bufferedReader.readLine();
													bufferedReader.close();
													
													fileReader = new FileReader(notificationFile + "/content.txt");
													bufferedReader = new BufferedReader(fileReader);
													content = bufferedReader.readLine();
													bufferedReader.close();
													
													fileReader = new FileReader(notificationFile + "/from.txt");
													bufferedReader = new BufferedReader(fileReader);
													from = bufferedReader.readLine();
													bufferedReader.close();
													
													currentEmployee.getNotifications().add(new Notification(getEmployeeByUsername(from), title, content));
													
												}
												
											}
										}
									}
									
								}		
							}
						}
					}
				}
				
				
				fileReader.close();
				
			} catch (IOException e) {
				
				System.out.println("Error reading from file: " + e);
				
			}	
		}
	}
	
	public Employee getEmployeeByUsername(String username) {
		
		for (Employee nextEmployee : employees) {
			if (nextEmployee.getUniqueUsername().equals(username)) {
				return nextEmployee;
			}
		}
		
		return null;
	}
}



























