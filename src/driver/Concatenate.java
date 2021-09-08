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

public class Concatenate extends Command {
  private String combined = ""; // stores the contents of the files
  private String invalid = ""; //for telling user which path
  							   // entered was invalid
  
  /*
   * Return the combined string belonging to the Concatenate object.
   * This getter is only used for testing.
   * @return String, which is a string of combined Strings
   */
  public String getCombined() {
    return combined;
  }
  
  /*
   * Sets combined to be an empty string. Only used for testing.
   */
  public void clearCombined() {
    combined = "";
  }
  
  private String manual = "cat FILE1 [FILE2 …]\n\nDisplay the "
      + "contents of FILE1 "
      + "and other files (i.e. File2 ….) concatenated in\r\n"
      + "the shell.";
  
  /*
   * Takes in an ArrayList of Strings which are expected to be paths to files.
   * Paths may be relative or full paths. Concatenates the content of each file
   * into one String, with three blank spaces between the content of each file.
   * 
   * Checks each String in the ArrayList to see if they lead to a valid file.
   * If an element does not point to a valid file, it will stop concatenating
   * and will prepare a "not a valid file" message that will be used in 
   * method execute().
   * 
   * @param files, an ArrayList of Strings
   */
  public void concatenate(ArrayList<String> files) {
	  for(int i=0; i<files.size(); i++) {
		  Node temp = Finder.returnDir(files.get(i));//check if path exists
		  if(temp instanceof FileNode) {             //check if the input is a file
			  FileNode temp2 = (FileNode) temp;        //cast Node to FileNode
			  if(i!=files.size()-1) {
				  combined = combined + temp2.getContent() + "\n\n\n";  //Add 3 blank lines
			  }
			  else if(i ==files.size()-1) {            //Check if i is the last index
				  combined = combined + temp2.getContent();
			  }
		  }
		  else{
			  invalid= files.get(i) + " is not a valid file."; //Input is not a file
			  break;
		  }
	  }
  }
  
  
  /*
   * Takes in an ArrayList of Strings.
   * 1) Check if the ArrayList is empty. If empty, print an error message. If
   *    not, proceed with the following steps.
   * 2) Concatenates the Strings inside of the ArrayList into a single String.
   * 3) Prints the concatenated String.
   * 4) Checks if there were any invalid paths/files inside the given ArrayList.
   * 4.1) If there were any invalid elements, print a message explaining which
   *      element was invalid.
   * @param files, ArrayList of Strings
   */
  public void execute(ArrayList<String> files) {
      OutputHandler oh = OutputHandler.setOH(files);  
	  if(files.size()>0) {
		  concatenate(files);
		  oh.handleOutput(combined);
		  if(invalid.isEmpty() == false) {       //Check for invalid parameters
			  oh.handleOutput(invalid);
		  }
	  }
	  else {                                     //Check for invalid input
		  driver.ErrorHandling.errorPrint("cat", "At least one parameter is "
		  		+ "expected");
	  }
  }
  /*
   * @Overload
   * Execute function if user enters no parameters
   * Which returns an error
   */
  public void execute() {
	  ErrorHandling.errorPrint("cat", "at least one parameter is expected");
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
