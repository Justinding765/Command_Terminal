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
import java.util.HashMap;
import java.util.ArrayList;

public class Menu {

	public void nav(ArrayList<String> inputList, 
			HashMap<String, String> commandHashMap){
				
		//Check if user has inputed anything
		if(inputList != null){
			if(inputList.size() != 0) {
				//Separates parameters from command
				String inputCommand = inputList.get(0);
				ArrayList<String> param = inputList;
				param.remove(0);
				
				//Initializes command class and runs command
				String className = commandHashMap.get(inputCommand);
				if (className != null){
					try{
						Command command = (Command) Class.forName(className).
								getDeclaredConstructor().newInstance();
						
						//Checks for parameters and calls methods accordingly
						if (param.size() >= 1){
							command.execute(param);
						}else{
							command.execute();

						}
					//Catches any errors and prints out error messages
					}catch (InstantiationException e) {
						ErrorHandling.errorPrint("Menu", "invalid command");
						//e.printStackTrace();
					} catch (IllegalAccessException e) {
						ErrorHandling.errorPrint("Menu", "invalid command");
						//e.printStackTrace();
					} catch (IllegalArgumentException e) {
						ErrorHandling.errorPrint("Menu", "invalid command");
						//e.printStackTrace();
					} catch (InvocationTargetException e) {
						ErrorHandling.errorPrint("Menu", "invalid command");
						//e.printStackTrace();
					} catch (NoSuchMethodException e) {
						ErrorHandling.errorPrint("Menu", "invalid command");
						//e.printStackTrace();
					} catch (SecurityException e) {
						ErrorHandling.errorPrint("Menu", "invalid command");
						//e.printStackTrace();
					} catch (ClassNotFoundException e) {
						ErrorHandling.errorPrint("Menu", "invalid command");
						//e.printStackTrace();
					}
				}else{
					ErrorHandling.errorPrint("Menu", "invalid command");
				}
			}
		}
	}
}
