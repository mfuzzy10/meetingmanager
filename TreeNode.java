/**
 * @author Roman
 *
 */
public class TreeNode {
	private Employee employee;
	private TreeNode leftNode;
	private TreeNode rightNode;
	
	/**
	 * TreeNode object contructor
	 */	
	public TreeNode(Employee employee, TreeNode rightNode, TreeNode leftNode) {
		this.employee = employee;
		this.rightNode = rightNode;
		this.leftNode = leftNode;
	}
	
	/**
	 * Returns the number of children the node has
	 */
	public int getChildren() {
		if(getLeft() != null && getRight() != null) {
			return 2;
		}
		else if(getLeft() != null && getRight() == null || (getLeft() == null && getRight() != null)) {
			return 1;
		}
		return 0;
	}
	
	/**
	 * Sets the right child to a given node
	 */
	public void setRight(TreeNode rightNode) {
		this.rightNode = rightNode;
	}
	
	/**
	 * Sets the left child to a given node
	 */	
	public void setLeft(TreeNode leftNode) {
		this.leftNode = leftNode;
	}
	
	/**
	 * Gets the right child node
	 */
	public TreeNode getRight() {
		return rightNode;
	}
	
	/**
	 * Gets the left child node
	 */
	public TreeNode getLeft() {
		return leftNode;
	}

	public String getUsername() {
		return employee.getUniqueUsername();
	}
	
	public Employee getEmployee() {
		return employee;
	}
}
