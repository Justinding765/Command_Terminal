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

import java.util.*;

public class History extends Command {
	
	protected static ArrayList<String> prevInputs = new ArrayList<String>();
	
	private String manual = "history [number]\n\nThis command will "
	      + "print out recent commands, one command per line. i.e.\r\n"
	      + "1. cd ..\r\n"
	      + "2. mkdir textFolder\r\n"
	      + "3. echo “Hello World”\r\n"
	      + "4. fsjhdfks\r\n"
	      + "5. history\r\n"
	      + "The above output from history has two columns. The:first\r\n"
	      + " column is numbered such that the line with the highest number "
	      + "is the most recent command.\r\n"
	      + "The most recent command is history. "
	      + "The second column contains the actual\r\n"
	      + "command.";
	
	/*
	 * Method that outputs the history stack with respect to
	 * the integer parameter which dictates the number of the latest commands
	 * on the history stack to output
	 * @param input, an array list of strings as our history of commands
	 */
	public void execute(ArrayList<String> input){
	  OutputHandler oh;
	  if (input.size() >= 2) { //Checks if redirectable
		  oh = OutputHandler.initializeOutputHandler(
				  input.get(input.size()-2), input.get(input.size()-1));
		  if(oh.isValidRedirect()) { //If so remove extra params
				input.remove(input.size()-1);
				input.remove(input.size()-1);
		  }
	  }else { //If not sets up OutputHandler to just print
		  oh = OutputHandler.initializeOutputHandler(
				  null, null);
	  }
	  
	  if(input.size() == 1) { 
		//Checks if parameter value is valid
		int i = Integer.parseInt(input.get(0));
		if(i >= 0){
			printHistory(oh, i);
		}else{
			//Case: param has negative value
			ErrorHandling.errorPrint("history", "invalid parameter value"); 
		}
	  }else if(input.size() == 0) {
		  printHistory(oh, prevInputs.size());
	  }else {
		//Case: 2 or more params
	    ErrorHandling.errorPrint("history", "too many parameters"); 
	  }
	} 
	
	/*
	 * Method that outputs the entire history stack
	 */
	public void execute(){
		OutputHandler oh = OutputHandler.initializeOutputHandler(null, null);
		printHistory(oh, prevInputs.size());
	}
	/*
	 * Method that outputs the num last entries of the history stack
	 * @param OutputHandler oh, determines how to handle the outputs
	 * @param int num, is the number of entries that the user wants outputted
	 */
	public void printHistory(OutputHandler oh, int num) {
		try{ 
			if (num > prevInputs.size()){ //num too large
				num = prevInputs.size(); //Made assumption
			}
			//Prints items
			for (int i = 1; i <= num ; i++){
				oh.handleOutput((prevInputs.size()-num+i) + 
						". " + prevInputs.get(num-i));
			}
		}catch (NumberFormatException e){ //Case: string param
			ErrorHandling.errorPrint("history", "invalid parameter type"); 
		}
	}
	/*
	 * Helper method that is used to insert previous commands
	 * into the directory stack
	 * @param String input, the user input to be added to the history stack
	 */
	public static void insert(String input){
        prevInputs.add(0, input);
	}
	
	/*
	 * Method that prints the manual for Echo
	 * @param OutputHandler oh, determines how to handle the output
	 * of the content (manual)
	 */
	public void printManual(OutputHandler oh) {
      oh.handleOutput(manual);
    }
	
	/*
	 * Method that clears the history stack
	 * Used for JUnit testing
	 */
	public static void clear() {
		prevInputs.clear();
	}
}
