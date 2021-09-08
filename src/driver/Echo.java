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

public class Echo extends Command {
  
    private String manual = "echo STRING [> OUTFILE]\n\nIf OUTFILE "
        + "is not provided,"
        + " print STRING on the shell. Otherwise, put\r\n"
        + "STRING into file OUTFILE. STRING is a "
        + "string of characters surrounded by double\r\n"
        + "quotation marks. This creates a new file "
        + "if OUTFILE does not exists and erases the\r\n"
        + "old contents if OUTFILE already exists. "
        + "In either case, the only thing in OUTFILE\r\n"
        + "should be STRING.\n\n"
        + "echo STRING >> OUTFILE\n\nLike the previous command, but appends "
        + "instead of overwrites.";
	/*
	 * Creates a new file if the name does not already exist
	 * @param String parentPath, stores parent path of the file 
	 * we want to create
	 * @param String name, stores desired name of the file
	 */
	public void outFileNotExist(String parentPath, String name
			, String content) //Creates new file in desired directory
							  // When Outfile doesn't exist in directory
	{
		String abspath = AbsolutePathFinder.getAbsPath(Finder.
				returnDir(parentPath));
		FileNode file1 = new FileNode(name,abspath  +name);
		file1.setContent(quoteParse(content));
		file1.setParent(Finder.returnDir(abspath));
		DirectoryNode tempNode = (DirectoryNode)Finder.returnDir(abspath);
		tempNode.setChild(file1); //set children for the parent node
	}
	
	/*
	 * Gets rid of the quotes around String inputed
	 * @param String input, stores user inputed string with quotes
	 * @Return String that contains the inputed string without quotes
	 */
	public String quoteParse(String input) { // gets ride of quotes in user
											//input
		String content = ""; 
		for(int i =1; i<input.length()-1;i++) {
			content = content + input.charAt(i);
		}
		return content;
	}
	
	/*
	 *Makes sure user enters valid string
	 *Specifically with two quotes at start and end of string
	 *@String input, stores user inputed string with quotes
	 *@return boolean true if valid false if not
	 */
	public boolean isValidString(String input) {
		if(input.charAt(0) == '"'&& input.charAt(input.length()-1)
				== '"') {
			return true;
		}
		return false;
	}

	/*
	 * Runs case where user only inputs one parameter
	 * Runs case where user wants to create new file
	 * Runs case where user wants to overwrite existing file
	 * Runs case where user wants to append existing file
	 * Covers case where user inputs invalid number of parameters
	 * Covers case where file name if invalid
	 * covers case where String input is invalid
	 * @param ArrayList of strings that consists of all user input parameters
	 */
	public void execute(ArrayList<String> input) {
		if(!isValidString(input.get(0))) {
			ErrorHandling.errorPrint("echo", "a string was expected as input "
			    + "(e.g. echo \"hello\")");
		
		//Case: user only input echo String
		}else if(input.size() == 1) {
			System.out.println(quoteParse(input.get(0)));
		
		//Case: redirect
		}else if(input.size() == 3) {
			String target = input.get(2);
			String mode = input.get(1);
			String content = quoteParse(input.get(0));
			int end = input.get(2).lastIndexOf("/");
			if (end != -1) {
				String name = target.substring(end+1);
				String path = target.substring(0, end+1);
				runEchoMode(name, path, target, mode, content);
			}else {
				runEchoMode(target, Command.cwd.getAddress()
						, target, mode, content);
			}
		//Case: wrong amount of arguments
		}else {
			ErrorHandling.errorPrint("echo", "this command takes either 1"
			    + " parameter or 3 parameters");
		}
	}
	/*
	 * Helper method of Echo that will create a file if
	 * it doesn't already exist and append or overwrite
	 * as directed by the mode
	 * @param String name, the name of the file
	 * @param String path, the path of the parent where the file should reside
	 * @param String target, the whole parameter passed to the command
	 * which will be used to show errors
	 * @param String mode, the mode that should be used for echo
	 * @param String content, the content of the file
	 */
	private void runEchoMode(String name, String path,
			String target, String mode, String content) {
		//Gets the node at the target address
		Node node = Finder.returnDir(target);
		if(!(node instanceof DirectoryNode)){
			//Creates a file if it doesn't already exist
			if(node == null) {
				node = createFile(name, path);
			}
			//Add contents based upon the mode
			FileNode file = (FileNode) node;
			if(mode.equals(">>")){
				file.appendContent(content);
			}else if(mode.equals(">")) {
				file.setContent(content);
			}else {
				ErrorHandling.errorPrint("echo", mode 
						+ " is an invalid argument");
			}
		}else if(node instanceof DirectoryNode) {
			//Prints error if node is a directory node
			ErrorHandling.errorPrint("echo", target + " is a"
					+ " directory not file");
		}else {
			ErrorHandling.errorPrint("echo", "unexpected error");
		}
	}
	
	/*
	 * Creates a FileNode with the given parameters
	 * @param String name, is the name of the file that is to be created
	 * @param String path, is the path of the location of 
	 * where the file is to be created
	 * @return The file that is created
	 */
	public FileNode createFile(String name, String path) {
		DirectoryNode parent = (DirectoryNode) Finder.returnDir(path);
		FileNode file;
		if(parent == Command.root) {
			file = new FileNode(name, parent.address+name);
		}else {
			file = new FileNode(name, parent.address+"/"+name);
		}
		parent.setChild(file);
		file.setParent(parent);
		return file;
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
