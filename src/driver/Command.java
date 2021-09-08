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

public abstract class Command {	
	
	//Parent class of all commands used to call all commands efficiently
    protected static DirectoryNode root = new DirectoryNode("/", "/");
	protected static DirectoryNode cwd = root;
	protected static ArrayList<String> stack = new ArrayList<String>();
	/*
	 * Has no direct function. Used as a general method in order to call
	 * methods from each command.
	 * 
	 * @param param ArrayList of Strings
	 */
    public void execute(ArrayList<String> param){
    }
    
    /*
     * Has no direct function. Used as a general method in order to call
     * methods from each command.
     */
	public void execute(){
	}
	
	/*
	 * Getting method
	 * @Return DirectoryNode that is pointer to current working directory
	 */
	public static DirectoryNode getCWD() {
		return cwd;
	}
	/*
	 * Getting method
	 * @Return DirectoryNode that is pointer to root
	 */
	public static DirectoryNode getRoot() {
		return root;
	}
	/*
	 * Template function that is used to handle outputs
	 * @param OutputHandler oh, is a parameter communicates to
	 * the class about how to handle an output
	 */
	
	public void printManual(OutputHandler oh) {
	}
}
