package driver;

import java.util.ArrayList;

public class Tree extends Command{
    //Contains the documentation for Tree command
    private String manual = "tree\n\n"
        + "Display the entire file system as a tree, starting from the root.";
    
    /*
     * Insures that tree is printed with suitable format
     * with the children of directories being indented
     * @param integer called depth, that keeps track of how deep we are
     * in the file system with the root as the reference point
     * @return the number of spaces to indent the file or directory
     */
	public String spaces(int depth){
		String spaces ="";
		//determines number of spaces to indent
		for(int i =0;i<depth;i++) {
			spaces = spaces + " ";
		}
		return spaces;
	}
	
	/*
	 * Traverses the entire file system using recursion, and prints out
	 * each directory and file starting with the root
	 * directories and files are indented according to how deep
	 * they are in the file system
	 * @param Node, current. Contains pointer to the current node being 
	 * accessed
	 * @param integer called depth, that keeps track of how deep we are in 
	 * file system with root as the reference point
	 */
	public void systemTraversal(Node current, int depth) {
		//prints current Node name indented a certain amount of space
		// according to its depth
		System.out.println(spaces(depth)+ current.getName());
		//checks that the current node is directory, and recursively calls 
		//systemTraversal method on each if its children if it is a directory
		if(current instanceof DirectoryNode) {
			for(int i = 0; i<((DirectoryNode)current).children.size();i++) {
				systemTraversal(((DirectoryNode)current).children.get(i),depth+1);
			}
		}
	}
	
	/*
	 * calls systemTraversal method
	 */
	public void execute() {
		//calls the method starting at root and depth of zero
		systemTraversal(Command.root,0);
	}
	/*
	 * makes sure that the input size is correct
	 * @param ArrayList<String> input, contains user inputs
	 */
	public void execute(ArrayList<String> input) {
		if(input.size()>0) {
			ErrorHandling.errorPrint("Tree", "incorrect input size");
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
