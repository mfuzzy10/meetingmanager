import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 * @author Roman
 *
 */
public class Tree {
	private TreeNode root;
	
	/**
	 * Tree constructor
	 */
	public Tree() {
		root = null;
	}
	
	/**
	 * Gets the binary tree's root node
	 */
	public TreeNode getRoot() {
		return root;
	}
	
	/**
	 * Sets the binary tree's root node to the given node
	 */
	public void setRoot(TreeNode root) {
		if(root == null) {
			System.out.println("No null");
			return;
		}
		this.root = root;
	}
	
	
	public void addToTree(TreeNode node) {
		addNode(node, root);
	}
	
	/**
	 * Adds the given node to the tree 
	 */
	private void addNode(TreeNode node, TreeNode parentNode) {
		if(getRoot() == null) {
			setRoot(node);
			return;
		}
		if(node.getUsername().compareTo(parentNode.getUsername()) < 0) {
			if(parentNode.getLeft() == null) {
				parentNode.setLeft(node);
			}
			else {
				addNode(node, parentNode.getLeft());
			}
		}
		else{
			if(parentNode.getRight() == null) {
				parentNode.setRight(node);
			}
			else {
				addNode(node, parentNode.getRight());
			}
		}
	}
	
	/**
	 * Calls the delete recursive method
	 */
//	public void deleteFromTree(String username) {
//		root = delete(root, username);
//	}
	
	/**
	 * Tries to delete a node in the tree with the given id		TODO REPLACE WITH A BETTER VERSION
	 */
//	public TreeNode delete(TreeNode parentNode, int id) {
//		if(parentNode == null) {
//			System.out.println("Node not in the tree");
//			return parentNode;
//		}
//		if(id < parentNode.getUsername()) {
//			parentNode.setLeft(delete(parentNode.getLeft(), id));
//		}
//		else if(id > parentNode.getUsername()) {
//			parentNode.setRight(delete(parentNode.getRight(), id));
//		}
//		else {
//			switch(parentNode.getChildren()) {
//			case(0):{
//				System.out.println("Node deleted");
//				return null;
//			}
//			case(1):{
//				if(parentNode.getLeft() == null) {
//					System.out.println("Node deleted");
//					return parentNode.getRight();
//				}
//				else {
//					System.out.println("Node deleted");
//					return parentNode.getLeft();
//				}
//			}
//			case(2):{
//				if(parentNode == getRoot()) {
//					System.out.println("Node deleted (but isn't because it isn't implemented)");
//					return parentNode;
//				}
//				else {
//					System.out.println("Node deleted (but isn't because it isn't implemented)");
//					return parentNode;
//				}
//			}
//			}
//		}
//		return parentNode;
//	}
		
	public Boolean isInTree(String username) {
		return find (username, root);
	}
	/**
	 * Checks if a node with the given id is in the tree
	 */	
	private Boolean find(String username, TreeNode parentNode) {
		if(parentNode == null) {
			return false;
		}
		else if(username.equals(parentNode.getUsername())) {
			return true;
		}
		else if(username.compareTo(parentNode.getUsername()) > 0) {
			return find(username, parentNode.getRight());
		}
		else if(username.compareTo(parentNode.getUsername()) < 0) {
			return find(username, parentNode.getLeft());
		}
		return false;		
	}
	
	public TreeNode findInTree(String username) {
		return findNode(username, root);
	}
	
	/**
	 * Tries to finds a node with the given id in the tree 
	 */	
	private TreeNode findNode(String username, TreeNode parentNode) {
		if(parentNode == null) {
			return null;
		}
		else if(username == parentNode.getUsername()) {
			return parentNode;
		}
		else if(username.compareTo(parentNode.getUsername()) > 0) {
			return findNode(username, parentNode.getRight());
		}
		else if(username.compareTo(parentNode.getUsername()) < 0) {
			return findNode(username, parentNode.getLeft());
		}
		return parentNode;
	}
	
	/**
	 * Displays the tree in-order
	 */
	public void displayInOrder(TreeNode parentNode) {
		if (!(parentNode.getLeft() == null)){
			displayInOrder(parentNode.getLeft());
		}
		parentNode.getEmployee().displayEmployee();
		if (!(parentNode.getRight() == null)){
			displayInOrder(parentNode.getRight());
		}		
	}
	
	/**
	 * Displays the tree pre-order
	 */
	public void displayPreOrder(TreeNode parentNode) {
		parentNode.getEmployee().displayEmployee();
		if (!(parentNode.getLeft() == null)){
			displayPreOrder(parentNode.getLeft());
		}
		if (!(parentNode.getRight() == null)){
			displayPreOrder(parentNode.getRight());
		}			
	}
	
	/**
	 * Displays the tree post-order
	 */
	public void displayPostOrder(TreeNode parentNode) {
		if (!(parentNode.getLeft() == null)){
			displayPostOrder(parentNode.getLeft());
		}
		if (!(parentNode.getRight() == null)){
			displayPostOrder(parentNode.getRight());
		}		
		parentNode.getEmployee().displayEmployee();
	}
	
	/**
	 * Adds multiple node on the tree
	 */
//	public void addMultipleValues() {
//		String[] splitMarks;
//		String[] splitValues;
//		while(true) {
//			String values = Function.inputString("IDs (separated by a space)");
//			splitValues = values.split(" ");
//			String marks = Function.inputString(splitValues.length + " Marks (separated by a space)");
//			splitMarks = marks.split(" ");
//			if(splitMarks.length == splitValues.length) {
//				break;
//			}
//			else {
//				System.out.println("The number of names must equal the number of values");
//			}
//		}	
//		for(int i = 0; i < splitValues.length; i++) {
//			TreeNode node = new TreeNode(Integer.parseInt(splitMarks[i]), Integer.parseInt(splitValues[i]), null, null);
//			addToTree(node, getRoot());
//			node.printInfo();
//		}
//	}
	
	/**
	 * Saves a node to a file
	 */
//	public void saveNode(TreeNode parentNode, PrintWriter printWriter) {
//		printWriter.println(parentNode.getMark() + "," + Integer.toString(parentNode.getID()));
//		if (!(parentNode.getLeft() == null)){
//			saveNode(parentNode.getLeft(), printWriter);
//		}
//		if (!(parentNode.getRight() == null)){
//			saveNode(parentNode.getRight(), printWriter);
//		}			
//	}
//	
	/**
	 * Empties the tree by setting the root to null 
	 * (java's garbage collector should do the rest)
	 */
	public void emptyTree() {
		this.root = null;
	}
	
//	/**
//	 * Loads the saved tree
//	 */
//	public Tree loadTree() {
//		emptyTree();
//		Tree loadedTree = new Tree();
//		try {		
//			FileReader fileReader;
//			BufferedReader bufferedReader;
//			fileReader = new FileReader("save.txt");
//			bufferedReader = new BufferedReader(fileReader);
//			String nextLine = bufferedReader.readLine();
//			while(!(nextLine == null)) {
//				String[] splitLine = nextLine.split(",");
//				String username= splitLine[0];
//				TreeNode node = new TreeNode(username, null, null);
//				loadedTree.addToTree(node, loadedTree.getRoot());
//				nextLine = bufferedReader.readLine();
//			}
//			bufferedReader.close();
//			System.out.println("Tree loaded");
//		}
//		catch(IOException e) {
//			System.out.println("No such file");
//		}
//		return loadedTree;
//	}
//	
//	/**
//	 * Saves the tree to a file by calling saveNode()
//	 */
//	public void saveTree() {
//		try {
//			FileOutputStream outputStream;
//			PrintWriter printWriter = null;				
//			outputStream = new FileOutputStream("save.txt");
//			printWriter = new PrintWriter(outputStream);
//			try {
//				saveNode(getRoot(), printWriter);
//				System.out.println("Tree saved");
//			}
//			catch(NullPointerException e) {
//				System.out.println("Tree is empty");
//			}
//			printWriter.close();			
//		}
//		catch(IOException e) {
//			System.out.println("No such file");
//		}
//	}
}
