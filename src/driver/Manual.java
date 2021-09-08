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
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * Contains the descriptions of each command
 * as well as a method that prints them prompted
 */
public class Manual extends Command{
  
  private HashMap<String, String> directory = new HashMap<String, String>();
  private ArrayList<String> commandList = new ArrayList<String>();  
  private String manual = "man CMD\n\nPrint "
  		+ "documentation for CMD";

  /*
  * Initalizes hashmap that maps command to their class names in driver.
  */
  private void initializeDirectory() {
    directory.put("exit", "driver.Exit");
    directory.put("mkdir", "driver.MakeDirectory");
    directory.put("cd", "driver.ChangeDirectory");
    directory.put("ls", "driver.List");
    directory.put("pwd", "driver.PrintWorkingDirectory");
    directory.put("pushd", "driver.PushDirectory");
    directory.put("popd", "driver.PopDirectory");
    directory.put("history", "driver.History");
    directory.put("cat", "driver.Concatenate");
    directory.put("echo", "driver.Echo");
    directory.put("man", "driver.Manual");
    directory.put("rm", "driver.RemoveDirectory");
    directory.put("mv", "driver.Move");
    directory.put("cp", "driver.Copy");
    directory.put("curl", "driver.URLToCurrentWorkingDirectory");
    directory.put("saveJShell", "driver.SaveJShell");
    directory.put("loadJShell", "driver.LoadJShell");
    directory.put("search", "driver.Search");
    directory.put("tree", "driver.Tree");
  }
  
  /*
  * Initliazes list that stores all valid commands
  */
  private void initializeCommandList() {
    commandList.add("exit");
    commandList.add("mkdir");
    commandList.add("cd");
    commandList.add("ls");
    commandList.add("pwd");
    commandList.add("pushd");
    commandList.add("popd");
    commandList.add("history");
    commandList.add("cat");
    commandList.add("echo");
    commandList.add("man");

    commandList.add("rm");
    commandList.add("mv");
    commandList.add("cp");
    commandList.add("curl");
    commandList.add("saveJShell");
    commandList.add("loadJShell");
    commandList.add("tree");
    commandList.add("search");
  }
    
    /*
     * A helper method that tries to fetch the appropriate manual for the
     * command given in inputList, and then tries to print the command. Has
     * catch statements just in case there is an error.
     * @params OutputHandler oh (already built in the main function, ArrayList
     * of String inputList
     */
    private void tryPrint(OutputHandler oh, ArrayList<String> inputList) {
        try{
          Command command = (Command) Class.
                  forName(directory.get(inputList.get(0))).
                  getDeclaredConstructor().newInstance();
          command.printManual(oh);
        //Catches any errors and prints out error messages
        } catch (InstantiationException e) {
            ErrorHandling.errorPrint("Man1", "invalid command");
        //e.printStackTrace();
        } catch (IllegalAccessException e) {
            ErrorHandling.errorPrint("Man2", "invalid command");
            //e.printStackTrace();
        } catch (IllegalArgumentException e) {
            ErrorHandling.errorPrint("Man3", "invalid command");
            //e.printStackTrace();
        } catch (InvocationTargetException e) {
            ErrorHandling.errorPrint("Man4", "invalid command");
            //e.printStackTrace();
        } catch (NoSuchMethodException e) {
            ErrorHandling.errorPrint("Man5", "invalid command");
            //e.printStackTrace();
        } catch (SecurityException e) {
            ErrorHandling.errorPrint("Man6", "invalid command");
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            ErrorHandling.errorPrint("Man7", "invalid command");
            //e.printStackTrace();
        }
    }
    /*
     * Prints the descriptions of the corresponding commands
     * Gives appropriate error when an invalid command is passed as a parameter.
     * @Param inputList, ArrayList
     */
  	public void execute(ArrayList<String> inputList) {
	    initializeDirectory();
	    initializeCommandList();
	    OutputHandler oh = OutputHandler.altSetOH(inputList);  
	    if(inputList.size()==1) {
	    	if(commandList.contains(inputList.get(0))) {
	    	    tryPrint(oh, inputList);
	    	}else {
	    		ErrorHandling.errorPrint("man", "invalid command");
	        }
	    }else {
	    	ErrorHandling.errorPrint("man", "exactly 1 parameter was expected");
	    }
  	}
  /*
   * Error case for when man is called alone.
   */
  	public void execute() {
  	  ErrorHandling.errorPrint("man", "exactly 1 parameter was expected");
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

