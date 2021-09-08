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

public class Node {

		protected String name; 
		// This would be the name of file/directory STIRNG
		protected String address;  		
		//Contains the relative or absolute path of the node
		protected Node parent;				
		// pointer to parent node
		protected ArrayList<Node> children;				
		// pointer to all of the Nodes children
		
		
		public Node() //default constructor
		{
			
		}
		/*
		 * Constructor of Node
		 * Initializes the instance variables of Node
		 * @param String nodeName 
		 * @param String path, relative or absolute path inputed by user
		 */
		public Node(String nodeName, String path) //constructor for Node object
		{
			name=nodeName;
			address = path; // will store absolute path
			parent = null; 
			children = null;
		}
		/*
		 *@return pointer to parent of the node object
		 */
		public Node getparent()
		{
			return parent; //returns pointer to node's parent
		}
		/*
		 * sets a pointer to the current node object's parent
		 */
		public void setParent(Node n)
		{
			this.parent=n; //Sets a pointer to parent of node 
		}
		/*
		 * @return String that contains name of the node
		 */
		public String getName()
		{
			return name; // returns name of node
		}
		/*
		 * @return String that contains absolute path of the node
		 */
		public String getAddress()
		{
			return address; //returns absolute path of the node
		}
		/*
		 * @returns Array list of strings containing the names of the
		 * current node's children 
		 */
		public ArrayList<String> getChild(){
			//returns ArrayList of names of 
			//all the Nodes children									
	        ArrayList<String> children_name = new ArrayList<String>();
	        if(this.children !=null )
	        	{
	        	for (int i = 0; i < children.size(); i++)
	        		{
	        		children_name.add(children.get(i).name);
	        		}
	        	}
	        return children_name;
	    }
		public ArrayList<Node> getChildNodes(){
		  return this.children;
		}
}
