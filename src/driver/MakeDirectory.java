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



/*
* Stores method that creates 2 children directories
* that returns errors when given invalid names for directories
* or non-existent paths along with its helper methods
*/
public class MakeDirectory extends Command{
  
    private String manual = "mkdir DIR1 [DIR2] ...\n\n Creates directories at "
        + "the paths specified by DIR1, DIR2 ...\n"
        + "These paths may be relative or absolute. You may not use this "
        + "command to create\n a directory with a name that is already used "
        + "by\n another directory or file at the same path.";
    
    /*
     * Method that creates a directory directed by given inputs
     * @param ArrayList of strings that stores the directions on how to
     * make each directory
     */
	public void execute(ArrayList<String> input) {
		for (int i = 0; i < input.size(); i++) {
			String current = input.get(i);
			int end = current.lastIndexOf("/");
			//Case: "/" exist in the string
			if (end != -1) {
				//Splits the name from the parent path
				String name = current.substring(end+1);
				String path = current.substring(0, end+1);
				//Checks for precondition
				if(mkdirHelper(name, path, current)){
					//Already checked if it is a directory in mkdirHelper
					DirectoryNode parent = 
							(DirectoryNode) Finder.returnDir(path);
					createDir(parent, name);
				}else {
					break;
				}
			//Case: Must be relative
			}else {
				if(mkdirHelper(current, Command.cwd.address,
						current)) {
					createDir(Command.cwd, current);
				}else {
					break;
				}
			}
		}
	}
	
	/*
	 * Method that prints error when no params
	 * are given along function call
	 */
	public void execute() {
		ErrorHandling.errorPrint("mkdir", "missing arguments");
	}
	
	/*
	 * Method that creates a directory given a name
	 * and the target location
	 * @param DirectoryNode targetLoc is the parent node at which
	 * the new directory is to be created
	 * @param String name is the name that the directory should
	 * be named
	 */
	public void createDir(DirectoryNode targetLoc, String name) {
		DirectoryNode child;
		if(targetLoc != Command.root) {
			child = new DirectoryNode(name,
					targetLoc.address+ "/" +name);
		}else {
			child = new DirectoryNode(name,
					targetLoc.address+name);
		}
		child.setParent(targetLoc);
		targetLoc.setChild(child);
	}
	
	/*
	 * Method that checks if given the inputs
	 * the preconditions are met to create the directory as specified
	 * @param String name, is the name of the directory we are trying to create
	 * @param String path, is the path of the parent directory
	 * @param String input, is the full user input that the user gives
	 * @return a boolean value that is true if valid and false if not
	 */
	private boolean mkdirHelper(String name, String path, String input) {
		int check = Finder.isDuplicateName(name, path);
		if (check == 2) {
			ErrorHandling.errorPrint("mkdir", input 
					+ " cannot be created as"
					+ " the target location is invalid");
			return false;
		}else if(IsValidName.checkValidName(name) || check == 0) {
			ErrorHandling.errorPrint("mkdir", input
					+ " cannot be created as"
					+ " the name is invalid");
			return false;
		}else {
			return true;
		}
	}
	
	/*
	 * Method that outputs the documentation of MakeDirectory
	 * @param OutputHander oh determines how the contents are outputted
	 */
	public void printManual(OutputHandler oh) {
      oh.handleOutput(manual);
    }
}
	

