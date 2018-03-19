/**
 * A class to handle all of the Employee's details and functions.
 * 
 * @version 1.0
 */

import java.time.LocalTime;
import java.util.Date;
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
		
		uniqueUsername = this.uniqueUsername;
		firstName = this.firstName;
		lastName = this.lastName;
		admin = this.admin;
		meetingList = new LinkedList<Meeting>();
	}
	
	/**
	 * Add an element to the Linked List.
	 * 
	 * @param date The date of the new meeting.
	 * @param startTime The time the new meeting will start at.
	 * @param endTime The time the new meeting will end at.
	 * @param description A description of the meeting.
	 */
	public void add(Date date, LocalTime startTime, LocalTime endTime, String description) {
		
		Meeting newMeeting = new Meeting(date, startTime, endTime, description);
		meetingList.add(newMeeting);
	}
	
	/**
	 * Delete an element from the Linked List.
	 */
	public void delete(Date date, LocalTime startTime) {
		
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
			System.out.println(meetingList.get(i).getDate() + " " + meetingList.get(i).getStartTime());
		}
	}
}











