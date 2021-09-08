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
public class AbsolutePathFinder {
	/*
	 * Method that gets the absolute path given a relative path
	 * @param Node relative, is the node that we want the abs path of
	 * @return a string that is the full path address of that node
	 */
	public static String getAbsPath(Node relative) {
		//Converts relative path to absolute path
		Node temp = relative;
		String temp1 = relative.address;
		if(temp !=null && temp.address.contains("/") == false) {
			temp1 = Command.cwd.address + "/"+ temp1;

		}
		return temp1;
	}
}
