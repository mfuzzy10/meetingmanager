/**
 * A class to handle all of the Employee's details and functions.
 * 
 * @version 1.0
 */

public class Employee {
	
	private String username;
	private boolean admin;
	private Meeting headNode;
	
	/**
	 * Blank Constructor for the Employee class.
	 */
	public Employee() {
		
		username = "";
		admin = false;
		headNode = null;
	}
	
	/**
	 * Filled Constructor for the Employee class.
	 * 
	 * @param username The username of the employee.
	 * @param admin Whether this employee is an admin or not;
	 */
	public Employee(String username, boolean admin) {
		
		username = this.username;
		admin = this.admin;
		headNode = null;
	}
}
