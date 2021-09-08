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

public class PopDirectory extends ChangeDirectory {
  
    private String manual = "popd\n\nRemove the top entry from the "
        + "directory stack, and navigate into it."
        + "The popd command\r\n"
        + "removes the top most directory from the "
        + "directory stack and makes it the\r\n"
        + "current working directory.";
	/*
	 * Deletes most recent path from directory stack
	 * changes to the most recent path on directory stack
	 */
    public void changeCwdAndPop() 
    {
        int len = Command.stack.size()-1;       //Get last index of the stack
        String temp = Command.stack.get(len);   //Get the path at that index
                                                //and set it equal to temp.
        ArrayList<String> temp2 = new ArrayList<String>();
        temp2.add(temp);
        super.execute(temp2);                   //Change directory to temp
        Command.stack.remove(len);              //Remove temp from the stack
        
    }
    /*
     * makes sure directory stack isn't empty
     * calls the changecwdAndPop method
     */
    public void execute() {
      if(Command.stack.size() >0) {
      changeCwdAndPop();
      }
      else {
        driver.ErrorHandling.errorPrint("popd", " the stack is empty");
      }
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
