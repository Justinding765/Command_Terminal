// **********************************************************
// Assignment2:
// Student1: Efkan Serhat Goktepe
// UTORID user_name: goktepee
// UT Student #: 1005939166
// Author: Efkan Serhat Goktepe
//
// Student2:Justin Ding
// UTORID user_name:Dingjust
// UT Student #:1006443145
// Author: Justin ding
//
// Student3: Artem Petrishchev
// UTORID user_name: petrish1
// UT Student #: 1002575260
// Author: Artem Petrishchev
//
// Student4: 
// UTORID user_name: linpeng1
// UT Student #: 10061092398
// Author:Peng Jie Lin (Bob)
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package driver;
import java.util.ArrayList;


public class DirectoryNode extends Node{
	
	/*
	 * Constructor
	 * @param nodeName, a String for the name of the directory node
	 * @param path, a String for the absolute path of the directory node
	 */
	public DirectoryNode(String nodeName, String path)
	{
		name=nodeName;
		address = path; // always stores abs path
		parent = null;
		children = new ArrayList<Node>();

	}
	
	/*
	 * Adds a child node to the ArrayList of children nodes of the respective
	 * directoryNode
	 * @param n, the Node which is a child of the respective directoryNode
	 */
	public void setChild(Node n)
	{
		this.children.add(n);
	}
	
	
}
