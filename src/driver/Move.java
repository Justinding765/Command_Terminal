package driver;

import java.util.ArrayList;

public class Move extends Command {
	
	//instance of copy class
	private Copy test_copy = new Copy();
	//instance of RemoveDirectory class
	private RemoveDirectory remove = new RemoveDirectory();
	//    //Contains the documentation for move command
	private String manual = "mv OLDPATH NEWPATH\n\n"
	      + "Move item OLDPATH to NEWPATH. Both OLD-\r\n"
	      + "PATH and NEWPATH may be relative "
	      + "to the current directory or may be full paths. If NEWPATH is a\r\n"
	      + "directory, move the item into the directory.";
	
	
	//public Move() {
		
	//}
	
	/*
	 * Checks input size is correct
	 * Ensures that the old path and new path are both valid
	 * Ensures that old path and new path are both directories
	 * Uses copy and remove directory class to first create
	 * directory and its contents in the desired location
	 * then deleting directory and its contents at old location
	 * prints errors for cases where there's duplicate name,
	 * invalid input size or invalid input, invalid path, and if the
	 * user tries to move root or current directories
	 * @param ArrayList, contains the inputs from user stored in array list
	 */
	public void execute(ArrayList<String> paths) {
		//Ensures correct input size
	if (paths.size() == 2) {
		Node old_node, new_node;
		// pointer to old path
		old_node = Finder.returnDir(paths.get(0));
		// pointer to new path
		new_node = Finder.returnDir(paths.get(1));
		//ensures that these paths exist
		if (old_node != null && new_node != null) {
			//ensures that there is no duplicate name, and that the paths are
			// paths of directories
			if(Finder.isDuplicateName(old_node.name, paths.get(1)) == 1) {
				if(old_node instanceof DirectoryNode && new_node instanceof DirectoryNode) {
					// proceeds to move the directory by calling copy and remove directory
					//classes
					if (old_node != Command.cwd && old_node != Command.root) {
						test_copy.copy(old_node, (DirectoryNode) new_node);
						remove.removeDir(old_node);
						
					} else {
						ErrorHandling.errorPrint("mv", "Cannot move root and current directories");
					}
				} else {
					ErrorHandling.errorPrint("mv", "Must enter directories");
				}
			} else {
				ErrorHandling.errorPrint("mv", "Duplicate directory");
			}
		} else {
			ErrorHandling.errorPrint("mv", "Directories not found"); 
		}
				
	} else {
		ErrorHandling.errorPrint("mv", "Invalid number of arguments");
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
