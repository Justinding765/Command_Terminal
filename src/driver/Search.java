package driver;

import java.util.ArrayList;

public class Search extends Command{
    //Contains documentation for Search command
    private String manual = "search path ... -type [f|d] -name expression\n\n"
        + "Search the given path(s) for a file or directory (determined by"
        + "input)\r\n with the exact name given by 'expression'."
        + " The paths may be relative or full paths.";
    
    /*
     * Recursive method that outputs any matching nodes 
     * in the given subtree of nodes
     * @param Node current stores the given head of the subtree
     * @param String type, name stores the information about the 
     * node that we are searching for
     * @param OutputHandler oh, contains how and where we 
     * output the results
     */
	public void search(Node current, String type, String name, OutputHandler oh) {
		if(current.name.equals(name)) {
			if(type.equals("d") && current instanceof DirectoryNode)
				oh.handleOutput(current.getAddress());
			else if(type.equals("f") && current instanceof FileNode)
				oh.handleOutput(current.getAddress());
		}
		if (current != null) {
			for(int i =0; i<current.children.size(); i++) {
				if (current.children.get(i) instanceof DirectoryNode) {
					search(current.children.get(i), type, name, oh);
				}
			}
		}
	}
	
	/*
	 * Execute method that check for specific user inputs
	 * and calls other methods to fulfill what the user expects
	 * @param ArrayList of String input stores the parameters that the
	 * user entered along with the command call
	 */
	public void execute(ArrayList<String> input) {
		//Variable declaration
		int i = input.size(); Echo temp = new Echo(); OutputHandler oh;
		
		//Check if actually redirectable
		if(i>=2) {
			oh= OutputHandler.initializeOutputHandler(
					input.get(input.size()-2),input.get(input.size()-1));
			if(oh.isValidRedirect()) {
				input.remove(i-1); //Removes params to indicate redirect
				input.remove(i-2);
				i = i - 2;
			}
		}else { //No redirect necessary
			oh= OutputHandler.initializeOutputHandler(null, null);
		}  
		//Checks if required arguments are inputted
		if(i > 4) {
			if(isValidSearch(input, temp)) {
				String name = temp.quoteParse(input.get(i-1));
				for(int j =0; j < input.size()-4; j++) {
					Node target = Finder.returnDir(input.get(j));
					if(target != null) { 
						//If all precondition are met calls search to output the results
						oh.handleOutput("Results in " + input.get(j));
						search(target,input.get(i-3),name, oh);
					}else {
						ErrorHandling.errorPrint("search", input.get(j)
								+ "is an invalid path");
					}
				}
			}
		}else {
			ErrorHandling.errorPrint("search", "missing arguments");
		}
	}
	/*
	 * @Overload
	 * Method executed for when user calls search with zero parameters
	 * It will return an error
	 */
	public void execute() {
		ErrorHandling.errorPrint("search","at least one argument was expected");
	}
	
	/*
	 * Method that checks the preconditions for the search command
	 * Printing any errors it finds
	 * @param ArrayList of Strings input, is all of the
	 * inputed parameters by user for search
	 * @param Echo temp, a parameter used to call methods in Echo
	 * @return boolean value that is true if precondition are met
	 * and false if not
	 */
	public boolean isValidSearch(ArrayList<String> input, Echo temp) {
		int i = input.size();
		if(!(input.get(i-4).equals("-type"))) {
			ErrorHandling.errorPrint("search", "missing type declaration");
			return false;
		}
		else if(!(input.get(i-3).equals("f") || input.get(i-3).
				equals("d"))) {
			ErrorHandling.errorPrint("search", "missing type");
			return false;
		}
		else if(!(input.get(i-2).equals("-name"))) {
			ErrorHandling.errorPrint("search", "missing name declaration");
			return false;
		}
		else if((!temp.isValidString(input.get(i-1)))){
			ErrorHandling.errorPrint("search", "missing valid name");
			return false;
		}
		return true;
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
