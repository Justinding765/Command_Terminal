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
public class ErrorHandling {
  /*
   * Receives a command and reason for error from a class in the package, and
   * prepares an error message.
   * @param command, a String to indicate which command the error message was
   * prompted from
   * @param error, a String to indicate the reason of the error.
   */
	public static void errorPrint(String command, String error){
		//Gets error message from a command class or the menu and prints
		System.out.println("Error in " + command + ". Reason: " + error + ". ");
	}
}
