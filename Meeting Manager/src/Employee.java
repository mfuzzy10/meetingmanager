/**
 * A class to handle all of the Employee's details and functions.
 * 
 * @version 1.0
 */

import java.time.*;
import java.util.LinkedList;

public class Employee {

	private String uniqueUsername;
	private String firstName;
	private String lastName;
	private boolean admin;
	private LinkedList <Meeting> meetingList;
	
	/**
	 * Blank Constructor for the Employee class.
	 */
	public Employee() {

		uniqueUsername = "";
		firstName = "";
		lastName = "";
		admin = false;
		meetingList = new LinkedList<Meeting>();
	}
	
	/**
	 * Filled Constructor for the Employee class.
	 * 
	 * @param uniqueUsername The username of the employee.
	 * @param admin Whether this employee is an administrator or not.
	 */
	public Employee(String uniqueUsername, String firstName, String lastName, boolean admin) {
		
		this.uniqueUsername = uniqueUsername;
		this.firstName = firstName;
		this.lastName = lastName;
		this.admin = admin;
		meetingList = new LinkedList<Meeting>();
	}
	
	public String getUniqueUsername() {
		return uniqueUsername;
	}

	public void setUniqueUsername(String uniqueUsername) {
		this.uniqueUsername = uniqueUsername;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public LinkedList<Meeting> getMeetingList() {
		return meetingList;
	}

	public void setMeetingList(LinkedList<Meeting> meetingList) {
		this.meetingList = meetingList;
	}

	/**
	 * Add an element to the Linked List.
	 * 
	 * @param date The date of the new meeting.
	 * @param startTime The time the new meeting will start at.
	 * @param endTime The time the new meeting will end at.
	 * @param description A description of the meeting.
	 */
	public void add(int day, int month, int year, int startHour, int startMinute, int endHour, int endMinute,String description) {
	
		meetingList.add(new Meeting(LocalDate.of(year, month, day), LocalTime.of(startHour, startMinute), LocalTime.of(endHour, endMinute), description));
	}
	
	/**
	 * Delete an element from the Linked List.
	 */
	public void delete(LocalDate date, LocalTime startTime) {
		
		int i = 0;
		boolean found = false;
		
		while (i < meetingList.size() && !found) {
			if (date == meetingList.get(i).getDate() && startTime == meetingList.get(i).getStartTime()) {
				found = true;
			}
			else {
				i++;
			}
		}
		
		if (found) {
			meetingList.remove(i);
		}
		else {
			System.out.println("Not Found");
		}
	}
	
	/**
	 * Edit an element in the Linked List.
	 */
	public void edit() {
		
		
	}
	
	/**
	 * Print out the Linked List.
	 */
	public void print() {
		
		for (int i = 0; i < meetingList.size(); i++) {
			System.out.println();
			System.out.println(i);
			System.out.println("Date        : " + meetingList.get(i).getDate());
			System.out.println("Start Time  : " + meetingList.get(i).getStartTime());
			System.out.println("End Time    : " + meetingList.get(i).getEndTime());
			System.out.println("Description : " + meetingList.get(i).getDescription());
		}
	}
}