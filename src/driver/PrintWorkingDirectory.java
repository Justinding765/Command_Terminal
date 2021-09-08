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

public class PrintWorkingDirectory extends Command {
  
    private ArrayList<String> commandList = new ArrayList<String>();
	
    private String manual = "pwd\n\nPrint the current working directory"
        + " (including the whole path).";
    
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
    
	public void printPWD(OutputHandler oh){
		if (Command.cwd !=null) {
			oh.handleOutput(cwd.address); 
		}                                   
	}                                      
											
	/*
	 * Calls the printPWD method
	 */
	public void execute() {
	  printPWD(OutputHandler.initializeOutputHandler(null, null));
	}
	
	public void execute(ArrayList<String> input) {
		initializeCommandList();
		OutputHandler oh;
		if (input.size() == 2) {
			  oh = OutputHandler.initializeOutputHandler(
					  input.get(input.size()-2), input.get(input.size()-1));
			  if(oh.isValidRedirect()) {
					printPWD(oh);
			  }else {
				  ErrorHandling.errorPrint("pwd", "invalid arguements");
			  }
		}else {
			ErrorHandling.errorPrint("pwd", "too many arguements");
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
