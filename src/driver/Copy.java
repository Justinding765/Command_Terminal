package driver;

import java.util.ArrayList;

public class Copy extends Command {
    //Contains the documentation for copy command
    private String manual = "cp OLDPATH NEWPATH\n\n"
        + "Like mv, but don't remove OLDPATH. If OLDPATH\r\n"
        + "is a directory, recursively copy the contents.";
	/*
	 * Recursively copies the contents of the desired directory
	 * and calls MakeDirectory class to recursively create the 
	 * desired directory and its contents at the new location
	 * @param Node target,  the location of the directory the user wants to copy
	 * @param targetLoc, the location where user wants to copy directory to. 
	 */
	public void copy(Node target, DirectoryNode targetLoc) {

		String path = targetLoc.getAddress(); 
		if(target instanceof DirectoryNode) {
			//creates instances of MakeDirectory class and creates new directory
			MakeDirectory dir = new MakeDirectory();
			dir.createDir((DirectoryNode)targetLoc, target.getName());
			//Recursively creates the children directories of the parent 
			//directory
			DirectoryNode temp = (DirectoryNode)target;
			if(temp.children.size() > 0) {
				for(int i =0; i< temp.children.size();i++){
					if(!(targetLoc.getAddress().equals("/"))) {
						copy(temp.children.get(i), 
								(DirectoryNode)Finder.returnDir((path + "/" + 
										target.getName())));	
					}
					else {
						copy(temp.children.get(i), 
								(DirectoryNode)Finder.returnDir((path  + 
										target.getName())));
					}
				}
			}
		}
		//Deals with case where the node to be copied is a file
		//Calls Echo class and creates new file at the desired location
		else if(target instanceof FileNode){
			Echo file = new Echo();
			file.outFileNotExist(path, target.getName(), 
					'"' + ((FileNode) target).getContent() + '"');
		}
	}
	/*
	 * Ensures that both the path of the desired directory, and 
	 * the desired location where the directory is to be copied 
	 * are valid
	 * Ensures that the desired path where the directory is to be copied
	 * is indeed a directory
	 * Verifies that there is no duplicate name in the path where the
	 * directory is to be copied
	 * @param Node target,  the location of the directory the user wants to copy
	 * @param Node targetLoc, the location where user wants to copy directory to. 
	 * @return integer depending on whether the path is valid for copying
	 */
	public int validForCp(Node target, Node targetLoc){
		
		if(target != null && targetLoc !=null  && 
				targetLoc instanceof DirectoryNode  && 
				Finder.isDuplicateName(target.name, targetLoc.address)==1
				&& target.equals(root) == false) {
			
			return 0;
		}
		else if(target == null || targetLoc == null || 
				!(targetLoc instanceof DirectoryNode)) {
			//invalid path entered
			return 1;
		}
		else if(Finder.isDuplicateName(target.name, targetLoc.address)==0) {
			//Duplicate name
			return 2;
		}
		return 1;

	}
	/*
	 * Ensures that user input size is correct
	 * Ensures that the target directory is valid for copying
	 * at the desired path
	 * Covers case where the user wishes to copy a file or directory
	 * to a desired path
	 * Prints suitable error message 
	 */
	public void execute(ArrayList<String> input) {
		//input size must be 2(old path and new path)
		if(input.size()==2) {
			//pointer to node of the old path
			Node target = Finder.returnDir(input.get(0));
			//pointer to node of the new path
			Node targetLoc = Finder.returnDir(input.get(1));

			if(validForCp(target,targetLoc) == 0){
				//Case where the desired node to copy is a Directory
				if(target instanceof DirectoryNode) {
					copy(target, (DirectoryNode)targetLoc);
				}
				//Case were the desired node to copy is a file
				else if(target instanceof FileNode){
					copy(target, (DirectoryNode)targetLoc );
				}
			}else {
				if(validForCp(target,targetLoc) == 1) {
				ErrorHandling.errorPrint("cp", "invalid input path "
						+ "does not exist" );
				}
				else if( validForCp(target,targetLoc) == 2) {
					ErrorHandling.errorPrint("cp", "duplicate name" );	
				}
			}
		}
		else 
		{
			ErrorHandling.errorPrint("cp", "invalid input size");
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
	

