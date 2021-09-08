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


/*
* Lists the contents of a node (directory or file)
*/
public class List extends Command{
	
  
    private String manual = "ls [-R] [PATH . . . ]\n\nif {R is present, "
        + "lists all subdirectories recursively.\r\n"
        + "If no paths are given, prints the contents (file or directory) "
        + "of the current directory, with a new line following\r\n"
        + "each of the content (file or directory).\r\n"
        + "If a given path is a file, prints the name of the file.\r\n"
        + "If it is a directory, print the children of the directory.";
	/*
	* Lists the contents of the node at the given path(s)
	* @Param path, ArrayList of strings that stores the path(s)
	*/
    private void printContents(ArrayList<String> path,OutputHandler oh,
        Node node, int i) {
      ArrayList<String> children = node.getChild();
      ArrayList<Node> childNodes = node.getChildNodes();
      oh.handleOutput(path.get(i) + ": ");
      for (int j = 0; j < children.size(); j++){
          oh.handleOutput(children.get(j));
          //Recursive Case - Makes a recursive call if there are children
          if(path.get(0).equals("-R") && childNodes.get(j).getChild().size()>0){
              ArrayList<String> childPath = new ArrayList<String>();
              childPath.add("-R");
              if(childNodes.get(j) instanceof DirectoryNode){
                childPath.add(childNodes.get(j).address);
              }
              execute(childPath);
          }
      }
    }
    
    private void ListBlankRecursive() {
      ArrayList<String> relative = new ArrayList<String>();
      relative.add("-R");
      relative.add(cwd.address);
      execute(relative);
    }
    
    private void printChildrenCWD(OutputHandler oh) {
      ArrayList<String> children = Command.cwd.getChild();
      for (int i = 0; i < children.size(); i++){
          oh.handleOutput(children.get(i));
      }
    }
    

    
	public void execute(ArrayList<String> path){
		Node node = new Node();
		OutputHandler oh = OutputHandler.setOH(path);  
		//Loops through each path
		if(path.size() != 0) {
			for (int i = 0; i < path.size(); i++){
				//Gets the node that the path goes to
				if(!path.get(i).equals("-R")) {
			        node = Finder.returnDir(path.get(i));
	    			//Tries to print the contents of node
	    			if (node == null){
	    				//Error for case where file does not exist
	    			    ErrorHandling.errorPrint("ls", "Path '"+path.get(i)+
	    			        "' does not exist");
	    			    break;
	    			}else{
	    				if (node instanceof DirectoryNode){
	    				    //Case: Node is a directory
	    					printContents(path, oh, node, i);
	    				}else{ //Case: Node is file
	    					oh.handleOutput(path.get(i));
	    				}
	    			}
				}
				//Recursive relative blank path case (ls -R)
				else if(path.size()==1 && cwd.getChild().size()>0) {
				  ListBlankRecursive();
				}
			}
		}
		else {
			//Gets and prints the children of the current working directory
		    printChildrenCWD(oh);
		}
	}
	
	/*
	* Lists the contents of the current working directory
	*/
	public void execute(){
	  OutputHandler oh;
	  oh = OutputHandler.initializeOutputHandler(null, null);
	  printChildrenCWD(oh);
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
