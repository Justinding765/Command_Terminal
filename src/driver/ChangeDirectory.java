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

public class ChangeDirectory extends Command {
	//Stores documentation for this Change Directory Command
    private String manual = "cd DIR\n\nChange directory to DIR, which may be "
        + "relative to the current directory or\r\n"
        + "may be a full path. As with Unix, .. means a parent directory and "
        + "a . means\r\n"
        + "the current directory. The directory must be /, the forward slash. "
        + "The foot of\r\n"
        + "the file system is a single slash: /.";
  
    /*
     * Takes an ArrayList of type String as a parameter. This ArrayList is
     * expected to contain a single string which is either ".", "..", or the 
     * full path of a directory.
     * 
     * If the String inside the ArrayList is ".", this method will not change
     * the current working directory.
     * 
     * If it is "..", this method will change the current working directory to
     * the parent of the current working directory. If the current working
     * directory is the root, this method will not change the current working
     * directory.
     * 
     * If it is a full path, the current working directory will be changed to
     * that path.
     * 
     * If the given path is invalid, this method will print an appropriate error
     * message.
     * 
     * If there are multiple paths in the ArrayList, this method will print an 
     * appropriate error message.
     * 
     * @param ArrayList of Strings, that is the user's inputs (i.e params)
     */
	public void execute(ArrayList<String> path)
	{
		//Insures path entered exists
		Node temp =  Finder.returnDir(path.get(0));
		//Checks if the node is an directory node and runs cases respectively
		if(path.size()==1) {
	        
			if(temp instanceof DirectoryNode) {
				//checks for case where user wants to change to root
				if(path.get(0).equals("/")) {
					Command.cwd = Command.root;
				}
				else {
				Command.cwd = (DirectoryNode)Finder.returnDir(path.get(0)); 
				}
			}else {
				ErrorHandling.errorPrint("cd", path.get(0)+
				    " is not a valid directory");
			}
		}else {
	        ErrorHandling.errorPrint("cd", "only one parameter was expected");
	    }
	}
	/*
	 * Checks for case were the user enters no paramaters
	 */
	public void execute() {
		ErrorHandling.errorPrint("cd", "one parameter was expected");
	}
	
	/*
    * Method used to handle outputs for a given command
    * @param OutputHandler oh, is used to determine what to
    * do with a given output
    */
    public void printManual(OutputHandler oh) {
      oh.handleOutput(manual);
    }
}
