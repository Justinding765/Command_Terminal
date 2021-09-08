package driver;

import java.util.ArrayList;

public class RemoveDirectory extends Command {
    // Cases: User wants to remove root, shouldn't allow
	//        User chooses to remove current working directory
	//        Path is invalid 
    
    private String manual = "rm DIR\n\nremoves the DIR from the file system.\n"
        + "This also removes all the children of DIR.";
	
    public void removeDir(Node target) {
		Node parent = target.parent;
		
			parent.children.remove(target);
			
			//Didn't check if it was a file or a directory
			//Since file has zero children by design
			if(target.children != null) {
				for(int i = 0; i<target.children.size(); i++) {
					removeDir(target.children.get(i));
				}
			}
		}
	
	public void execute(ArrayList<String> input) {
		if(input.size() == 1) {
			Node target = Finder.returnDir(input.get(0));
			
			//Errors may happen
			//Checking if the target is the root node
			if(target instanceof DirectoryNode) {
				if(target != Command.cwd &&
						target != Command.root){
					removeDir(target);
				}else {
					ErrorHandling.errorPrint("rm", "cannot delete current/"
					    + "root directory");

				}
			}else if(target == null){
				ErrorHandling.errorPrint("rm", input.get(0)+
				    "is not a valid file or directory");
			}else {
				ErrorHandling.errorPrint("rm", "this command may only"
				    + "be used to remove directories, not files");
			}
		}
		else {
			ErrorHandling.errorPrint("rm", "only one parameter was expected");
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
