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

/*
* Runs main shell as well as initialize hashmap helps run shell
*/
public class JShell {

  public static void main(String[] args) {
	
	//Calls necessary classes
	UserInputHandling ui = new UserInputHandling();
	Menu call = new Menu();
	Command.root.setParent(Command.root);
	
	//Creates hashmap for classes
    HashMap<String, String> commandHashMap = new HashMap<String, String>();
	initializeHashMap(commandHashMap);
	
	//Creates scanner object
	Scanner sc = new Scanner(System.in);
	
	//Gets user input continuously
	while (true){
		System.out.print(Command.cwd.address + ">");
		String userInput = sc.nextLine();
		//Passes user inputs to helper classes and history to run commands
		History.insert(userInput);
		ArrayList<String> inputList = ui.interpret(userInput);
		call.nav(inputList, commandHashMap);
	}
  }
  
  
  /*
  *Hash map initialization that maps all valid commands to class
  *@Param hashmap, hashmap that maps strings(command) to strings(class)
  */
  private static void initializeHashMap(HashMap<String, String> hashmap){
	hashmap.put("mkdir", "driver.MakeDirectory");
	hashmap.put("ls", "driver.List");
	hashmap.put("cd", "driver.ChangeDirectory");
	hashmap.put("pwd", "driver.PrintWorkingDirectory");
	hashmap.put("history", "driver.History");
	hashmap.put("pushd", "driver.PushDirectory");
	hashmap.put("popd", "driver.PopDirectory");
	hashmap.put("cat", "driver.Concatenate");
	hashmap.put("exit", "driver.Exit");
	hashmap.put("man", "driver.Manual");
	hashmap.put("echo", "driver.Echo");
	hashmap.put("rm", "driver.RemoveDirectory");
	hashmap.put("cp", "driver.Copy");
	hashmap.put("mv", "driver.Move");
	hashmap.put("curl", "driver.URLToCurrentWorkingDirectory");
	hashmap.put("tree", "driver.Tree");
	hashmap.put("search", "driver.Search");
	hashmap.put("saveJShell","driver.SaveJShell" );
	hashmap.put("loadJShell","driver.LoadJShell" );
  }
}
