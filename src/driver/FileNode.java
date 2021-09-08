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

public class FileNode extends Node{
	
	private String content = "";

	/**
	 *
	 * @param nodeName
	 * @param path
	 */
	public FileNode(String nodeName, String path)
	{
		name=nodeName;
		address = path; // always stores full path
		parent = null;
		children = null;

	}
	/*
	 * Method that sets the contents of the file
	 * @param String input, is the what the user wants to
	 * set the file's content to
	 */
	public void setContent (String input) {
		this.content = input;
	}
	/*
	 * Method that appends the contents of the file
	 * @param String input, is the what the user wants to
	 * append to the contents of the file
	 */
	public void appendContent (String input) {
		this.content = this.content + input;
	}
	
	/*
	 * Method that gets the content of the file
	 * @return the String contents of the file
	 */
	public String getContent() {
	  return this.content;
	}
}
