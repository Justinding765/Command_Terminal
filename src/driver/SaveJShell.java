package driver;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.ArrayList;
import java.io.FileWriter;   // Import the FileWriter class
public class SaveJShell extends Command {
    //Contains the documentation for save Jshell command
    private String manual = "saveJShell FileName\n\n"
        + "Save the current session of the JShell to a File called."
        + "FileName. The next time the\r\n"
        + "JShell is started, you may type in the command "
        + "loadJShell FileName to reinitialize the last saved\r\n"
        + "session and begin from where they left off. For instance, "
        + "if you type in the command saveJShell\r\n"
        + "/Users/User1/Desktop/save.txt, then you will create a "
        + "file save.txt on your computer that will\r\n"
        + "save the session of the JShell. If the above file exists "
        + "on your computer, then this"
        + "command will overwrite the file\r\ncompletely.";
    /*
     * Creates a File in the actual computer file system
     * Checks for cases for if the file already exists, or if input is invalid
     * @param String, input, stores the user input path for the file system
     * @return integer, Returns either 0,2,3 based on the cases(i.e if File already exists
     * or is invalid...)
     */
	public int createFile(String input) {
	    try {
	    	//creates new file object at desired location in computer
	        File myObj = new File(input);
	        if (myObj.createNewFile()) {
	          System.out.println("File created: " + myObj.getName());
	          return 0;
	        } else {
	          System.out.println("File already exists, will overwrite this file");
	          return 1;
	        }
	      } catch (IOException e) {
	        System.out.println("Path does not exist");
	        e.printStackTrace();
	        return 2;
	      }

	}
	/*
	 * Writes the necessary contents(root, Current working directory
	 * file system,etc) into the file in the computer system
	 * Calls the necessary methods, that return the needed contents(file system, 
	 * History stack) as strings that are written into the file 
	 * Checks for cases, where their is IOException
	 * @param String input, contains the desired path in the file system
	 */
	public void writeFile(String input) {
		 try {
		      FileWriter myWriter = new FileWriter(input);
		      myWriter.write(uploadFileSystem(Command.root,""));
		      //indicates end of file system String
		      myWriter.write("$File System\n");
		      myWriter.write(uploadHistoryStack());
		      //indicates end of history stack
		      myWriter.write("$History Stack\n"); 
		      myWriter.write(uploadDirectoryStack());
		      //indicates end of directory stack
		      myWriter.write("$Directory Stack\n");
		      //writes in path of current working direcotry
		      myWriter.write(Command.cwd.address);
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	/*
	 * returns a string that contains the paths of every node in the 
	 * file System, and also contains the contents of the node if it 
	 * is a file
	 * @param Node current, pointer to the current node being accessed
	 * @param String fileSystem, contains the paths of the nodes and contents
	 * in the file system
	 * @return String fileSystem, that contains the paths of the nodes and 
	 * contents in the file system
	 */
	public String uploadFileSystem(Node current, String fileSystem ) {
		String temp = current.address ;
		if(current instanceof FileNode) {
			temp = temp + " contents.:" + ((FileNode)current).getContent();
		}
		fileSystem = fileSystem + temp + "\n";
		 if(current instanceof DirectoryNode) {
			for(int i = 0; i<((DirectoryNode)current).children.size();i++) {
				fileSystem = uploadFileSystem(((DirectoryNode)current)
						.children.get(i), fileSystem);
			}
		}
		
		return fileSystem;
	}
	/*
	 * returns a string that contains the entire
	 * HistoryStack, which stores the user inputs
	 * @return String, contains history stack
	 */
	public String uploadHistoryStack() {
		
		String temp = "";
		//accesses History stack, which is a static variable
		for(int i = 0;i<History.prevInputs.size();i++) {
			temp = temp + History.prevInputs.get(i) + "\n";
		}
		return temp;
	}
	/*
	 * returns a string that contains the entire
	 * Directory Stack, which stores the recent directory paths
	 * that are accessed through pushd and popd commands
	 * @return String, containing the directory stack
	 */
	public String uploadDirectoryStack() {
		//accesses Directory stack, which is a static variable
			String temp = "";
			for(int i = 0;i<Command.stack.size();i++) {
				temp = temp + Command.stack.get(i) + "\n";
			}
			return temp;
		}
	/*
	 * Calls createFile method and writeFile to create and write
	 * the necessary contents into the desired file at the desired
	 * path
	 * Overwrites file, if the file already exists, and gives error
	 * if input is invalid
	 * @param ArrayList<String> contains the path where 
	 * user wants to create the file
	 */
	public void execute(ArrayList<String> input) {
		int i = createFile(input.get(0)); 
		if(i == 0 && input.size() == 1) {
			writeFile(input.get(0));
		}
		else if(i == 1 && input.size() == 1) {
			writeFile(input.get(0));

		}
		else {
	    	ErrorHandling.errorPrint("Save JShell", "invalid input size ");
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

  
