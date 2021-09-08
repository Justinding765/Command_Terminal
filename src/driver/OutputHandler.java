package driver;

import java.util.ArrayList;

public class OutputHandler {
	public String mode;
	public FileNode targetLoc = null;
	
	/*
	 * Constructor for an instance of OutputHandler
	 * @param String mode, is the string representation of the mode
	 * of the OutputHandler
	 * @param FileNode targetLoc is the location of where the user
	 * wishes to redirect the output
	 */
	private OutputHandler(String mode, FileNode targetLoc) {
		this.targetLoc = targetLoc;
		this.mode = mode;
	}
	
	/*
	 * Method actually used for initializing the OutputHandler class
	 * Calls the initializer (a pseudo builder method)
	 * @param String mode, is the string representation of the mode
	 * @param String targetPath, is the address of where the user wishes the
	 * file to be created at
	 * @return a OutputHandler class created by the constructor
	 */
	public static OutputHandler initializeOutputHandler(String mode, String 
	    targetPath){
		Node target = Finder.returnDir(targetPath);
		Echo echo = new Echo();
		String name, parentPath;
		
		//Case file not found
		if (mode != null && (mode.equals(">") || mode.equals(">>"))) {
			if (target == null) {
				//Splits the path into parent path and name
				int end = targetPath.lastIndexOf("/");
				if (end != -1) {
					name = targetPath.substring(end+1);
					parentPath = targetPath.substring(0,end+1);
				}else {
					name = targetPath;
					parentPath = Command.root.address;
				}
				return new OutputHandler(mode, echo.createFile(name, parentPath));
			//Case file exists
			}else if(target instanceof FileNode) {
				return new OutputHandler(mode, (FileNode)target);
			//Case directory exists so don't redirect
			}else {
				return new OutputHandler(null, null);
			}
		}else {
			return new OutputHandler(null, null);
		}
	}
	
	/*
	 * Method that prints, appends, or overwrites given of the mode
	 * @param String output, is the actual content that needs to be handled
	 */
	public void handleOutput(String output) {
		if(this.mode != null) {
			if(this.mode.equals(">")) {
				targetLoc.setContent(output);
				this.mode = ">>";
			}else if(this.mode.equals(">>")) {
				targetLoc.appendContent("\n" + output);
			}else {
				System.out.println(output);
			}
		}else {
			System.out.println(output);
		}
	}
	
	/*
	 * Method used to check if we are to redirect output
	 * @return a boolean value that is true if we are and false if we aren't
	 */
	public boolean isValidRedirect() {
		//Checks if necessary fields have values
		if(this.targetLoc != null && 
				(this.mode.equals(">") || this.mode.equals(">>"))) {
			return true;
		}
		return false;
	}
	/*
	 * Method used as a helper function to initialize the OutputHandler object
	 * in the methods that require it, instead of coding it seperately each time
	 * @params ArrayList of String path
	 * @return an OutputHandler object that is initialized
	 */
	 public static OutputHandler setOH(ArrayList<String> path) {
	      OutputHandler oh;
	      if(path.size()>=2) {
	          oh= OutputHandler.initializeOutputHandler(
	                  path.get(path.size()-2),path.get(path.size()-1));
	          if(oh.isValidRedirect()) {
	              path.remove(path.size()-1);
	              path.remove(path.size()-1);
	          }
	      }else {
	          oh= OutputHandler.initializeOutputHandler(null, null);
	      }
	      return oh;
	    }
	 /*
	  * An alternative version for the helper method above, this one is for
	  * commands that expect to have more than 2 parameters when a redirect is
	  * intended.
	  * @params ArrayList of String path
	  * @return an OutputHandler object that is initialized
	  */
	 public static OutputHandler altSetOH(ArrayList<String> path) {
       OutputHandler oh;
       if(path.size()>2) {
           oh= OutputHandler.initializeOutputHandler(
                   path.get(path.size()-2),path.get(path.size()-1));
           if(oh.isValidRedirect()) {
               path.remove(path.size()-1);
               path.remove(path.size()-1);
           }
       }else {
           oh= OutputHandler.initializeOutputHandler(null, null);
       }
       return oh;
     }
}
