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

public class UserInputHandling {
	
	/*
	*Convert user input into an ArrayList of strings
	*@param String userInput
	*@return ArrayList of Strings
	*/
	public ArrayList<String> interpret(String userInput){
		//Separates the user input into parts
		String[] inputArray = userInput.split(" ");
		ArrayList<String> inputList = new ArrayList<String>();
		//Goes through all inputs split by spaces
		int i=0;
		while (inputArray.length - i > 0){	
			//Case: Current input is not a string
			if (inputArray[i].indexOf('"') == -1){ 
				if (!inputArray[i].equals("")){ 
					inputList.add(inputArray[i]); //Remove Excess Spaces
				}
				i++;
			}else{ //Case: Current input is a part of a string
				String temp = "";
				if (inputArray[i].indexOf('"') == 0){
					inputArray[i] = inputArray[i].substring(1);
					while (i < inputArray.length){
						//Case: Middle portion of string
						if (inputArray[i].indexOf('"') == -1){
							temp = temp + inputArray[i] + " ";
							i++;
						//Case: End portion of string
						}else if (inputArray[i].indexOf('"') == 
								inputArray[i].length()-1){
							temp = '"' + temp + inputArray[i];
							inputList.add(temp); i++; break;
						//Case: Invalid String
						}else{
							ErrorHandling.errorPrint("User Input Handling",
									"invalid input string");
							return null;
						}
					}
					//Missing end parentheses
					if (temp.indexOf('"', 1) != temp.length()-1){
						ErrorHandling.errorPrint(
								"User Input Handling" ,"invalid input string");
						return null;
					}
				}
			}
		}
		return inputList;
	} 
}
