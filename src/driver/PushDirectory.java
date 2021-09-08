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

public class PushDirectory extends ChangeDirectory {
  
    private String manual = "pushd DIR\n\nSaves the "
        + "current working directory by "
        + "pushing onto directory stack and then\r\n"
        + "changes the new current working directory to DIR. "
        + "The push must be\r\n"
        + "consistent as per the LIFO behavior of a stack. "
        + "The pushd command saves\r\n"
        + "the old current working directory in directory stack "
        + "so that it can be returned\r\n"
        + "to at any time (via the popd command). "
        + "The size of the directory stack is\r\n"
        + "dynamic and dependent on the pushd and the popd commands..";
	/*
	 * adds current working directory path 
	 * to top of directory stack following LIFO.
	 * Changes directories to user input path
	 * @param Array list of strings that includes
	 * user input
	 */
    public void push(ArrayList<String> input)
    {
        if(Finder.returnDir(input.get(0)) instanceof DirectoryNode) {
        	Command.stack.add(Command.cwd.address);//Stack the CWD
        	super.execute(input);                  //CD to the input path
        }else {
        	//Can split errors to not directory and dne
        	ErrorHandling.errorPrint("pushd", "target directory is invalid");
        }
    }
    /*
     * Checks to make sure user input is valid
     * calls the push method
     */
    public void execute(ArrayList<String> input) {
    	if(input.size() == 1) {
          push(input);
        }
        else {
        	driver.ErrorHandling.errorPrint("pushd", " only one parameter is" 
        			+ "expected");
        }
    }
    
    public void execute() {
    	ErrorHandling.errorPrint("pushd", "missing arguments");
    }
    
    /*
	 * Method that prints the manual for Echo
	 * @param OutputHandler oh, determines how to handle the output
	 * of the content (manual)
	 */
	public void printManual(OutputHandler oh) {
      oh.handleOutput(manual);
    }
}
