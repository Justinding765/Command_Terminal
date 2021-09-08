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

public class IsValidName {
	
	//Contains all invalid characters for file or directory
	public static String invalidChar = "/. !@#$%^&*( ){ }~|<>?";
	
	/*
	 * Function to check validity of file/directory (naming convention)
	 * @param name, string that will be checked for validity
	 * @return boolean, true if invalid, false otherwise
	 */
	public static boolean checkValidName(String name) {
		for(int i =0; i<invalidChar.length();i++) {
			if (name.contains(Character.toString(invalidChar.charAt(i))) 
					|| name.contains(Command.root.address)
					|| name.length()==0){
				return true;
			}
			
		}
		return false;
	}
}
