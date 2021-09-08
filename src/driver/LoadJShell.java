package driver;

import java.util.ArrayList;
import java.io.*; 

public class LoadJShell extends Command{
    //Contains the documentation for save JShell command
    private String manual = "loadJShell FileName\n\n"
        + "Load the contents of the FileName\r\nand reinitialize everything "
        + "that was saved previously into the file FileName. This allows for"
        + "the user to restore a previous JShell session.";
    
    /*
     * Calls the proper methods that loads in the file system,
     * history stack, directory stack, and set the current
     * working directory
     * @param String input, contains path of the file in the computer
     */
    public void loadShell(String input) {
    	try {
	    	File file = new File(input); 
	    	  BufferedReader br = new BufferedReader(new FileReader(file)); 
	    	  String st; int i = 0;
	    	  st = br.readLine();
	    	  while ((st = br.readLine()) != null)  { 
	    		//has reached end of file system
	    	    if(st.equals("$File System")){
	    	    	i = 1;
    	    	//has reached end of history stack
	    	    }else if(st.equals("$History Stack")) {
	    	    	i = 2;
	    	    //has reached end of directory stack
	    	    }else if(st.equals("$Directory Stack")) {
	    	    	i = 3;
	    	    }else{
	    	    	//Loads required items
	    	    	switch(i) {
		    	    case 0:
		    	    	loadFileSystem(st);
		    	    	break;
		    	    case 1:
		    	    	loadHistoryStack(st);
		    	    	break;
		    	    case 2:
		    	    	loadDirectoryStack(st);
		    	    	break;
		    	    case 3: 
		    	    	loadCwd(st);
		    	    	break;
	    	    	}	
	    	    }
	    	}
    	}catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
    	}
	} 
    /*
     * Loads the file system from the file in the computer
     * and creates directories or files accordingly
     * @param String input, contains location of file in the file system
     */
    public void loadFileSystem(String input) {
    	ArrayList<String> temp = new ArrayList<String>();
    	if(input.contains("contents.:")) {
    		Echo file = new Echo();
			int end = input.indexOf(" ");
			String path = input.substring(0,end);
			//int end2 = path.lastIndexOf('/');
			int end3 = input.substring(end).indexOf(":");
			temp.add('"'+ input.substring(end3+3) +'"');
    		temp.add(">");
    		temp.add(path.substring(0,end));
    		file.execute(temp);
    	}
    	else {
        	temp.add(input);
    		MakeDirectory dir = new MakeDirectory ();
    		dir.execute(temp);
    	}
    }
    /*
     * Loads the HistoryStack from the file in the computer
     * and populates the HistoryStack in the mock file system accordingly
     * @param String input, contains location of file in the file system
     */
    public void loadHistoryStack(String input) {
    	History.prevInputs.add(input);
    }
    /*
     * Loads the directory from the file in the computer
     * and populates the directory Stack accordingly following
     * LIFO
     * @param String input, contains location of file in the file system
     */
    public void loadDirectoryStack(String input) {
    	int end = Command.stack.size();
    	Command.stack.add(end, input);
    }
    /*
     * Loads the path of the current working directory
     * from the file in the computer
     * and sets the current working directory in the mock file
     * system to that path
     * @param String input, contains location of file in the file system
     */
    public void loadCwd(String input) {
    	//System.out.println(input);
    	Command.cwd = (DirectoryNode) Finder.returnDir(input);
    }
    
    /*
     * Checks that the input size is correct and that 
     * the user has not inputed any other commands
     * @param ArrayList<String>, contains the path of the file
     * on the computer system
     */
    public void execute(ArrayList<String> input) {

    	if(input.size() == 1 && History.prevInputs.size() == 1) {
    		loadShell(input.get(0));
    	}
    	else if(input.size()!= 1) {
    	ErrorHandling.errorPrint("Load JShell", "invalid input size");
    	}
    	//disables command if user has made any other inputs 
    	else if(History.prevInputs.size() > 1) {
    		ErrorHandling.errorPrint("Load JShell", "command disabled");
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
