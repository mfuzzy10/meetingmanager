/**
 * A class to handle all of the Employee's details and functions.
 * 
 * @version 1.0
 */

public class Employee {

	private String uniqueUsername;
	private String firstName;
	private String lastName;
	private boolean admin;
	private Meeting headNode;
	
	/**
	 * Blank Constructor for the Employee class.
	 */
	public Employee() {

		uniqueUsername = "";
		firstName = "";
		lastName = "";
		admin = false;
		headNode = null;
	}
	
	/**
	 * Filled Constructor for the Employee class.
	 * 
	 * @param uniqueUsername The username of the employee.
	 * @param admin Whether this employee is an admin or not;
	 */
	public Employee(String uniqueUsername, String firstName, String lastName, boolean admin) {
		
		uniqueUsername = this.uniqueUsername;
		firstName = this.firstName;
		lastName = this.lastName;
		admin = this.admin;
		headNode = null;
	}
}
