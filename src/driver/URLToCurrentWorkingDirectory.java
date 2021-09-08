package driver;
import java.util.ArrayList;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.io.*;


public class URLToCurrentWorkingDirectory extends Command {
    //Contains the documentation for Curl url command
    private String manual = "curl URL\n\n"
        + "Retrieve the file at that URL\r\n"
        + "and add it to the current working directory.";
  /*
   * Parses the URL String, and gets the desired name for the file
   * Checks that the file name is valid, and that it isn't a duplicate name
   * @param String input, contains the URL entered by the user
   * @return, a String that contains desired name, or returns ""
   * if the name isn't valid
   */
	public String getNameFromURL(String url) {
		//Gets the name of file from url entered
		String[] temp = url.split("/");
		String temp2 = temp[temp.length-1];
		String name = "";
		for(int i =0;i<temp2.length();i++) {
			if(temp2.charAt(i) == '.') {
				break;
			}
			name = name + temp2.charAt(i);
		}
		if(Finder.isDuplicateName(name, Command.cwd.address) == 1 
				&& IsValidName.checkValidName(name) == false) {
			return name;
		}
		return "";
	}
	/*
	 * Ensures that the user inputed URL is valid
	 * Checks that the URL exists, isn't malformed, and has correct syntax
	 * @param String url, contains string that stores user inputed URL
	 * @return Boolean, returns True or False depending on if the Url is valid
	 */
	public  boolean isUrlValid(String url) {
	    try {
	       URL obj = new URL(url);
	       obj.toURI();
	       return true;
	    } catch (MalformedURLException e) {
	       return false;
	    } catch (URISyntaxException e) {
	       return false;
	    }
	 }
	/*
	 * Checks to make sure the URL entered is valid
	 * Retrieves the desired file from URL, and creates a file
	 * containing same contents as the file from the URL in 
	 * the current working directories
	 * Prints suitable error messages if user input is invalid
	 * @param ArrayList of String containing all parameters
	 * for curl, namely urls
	 */
    public void execute(ArrayList<String> input)  {
    	if(input.size() == 1) {
	    	if(isUrlValid(input.get(0))) {
		        try {
	        		//Creates instance of URL class
		            URL url = new URL(input.get(0));
		            //Retrieves file from website URL
			        BufferedReader read = new BufferedReader(
			        new InputStreamReader(url.openStream()));
			        String content = "";
			        String i;
			        //Reads contents of file
			        while ((i = read.readLine()) != null) {
			            content = content + i + "\n";
		        	}
			        read.close();
			        Echo file = new Echo();
			        //Calls method in Echo class to create file in 
			        //Current working directory
			        if(getNameFromURL(input.get(0)).equals("")== false) {
				        file.outFileNotExist(Command.cwd.address,
				        		getNameFromURL(input.get(0)), '"'+content+'"');
				    }
		        }catch (IOException e)  { //Catches errors
		        	ErrorHandling.errorPrint("curl", "input/output file operation issues");
		        }
	    	}else {
	    		ErrorHandling.errorPrint("curl", "invalid url");
	    	}
    	}else {
    		ErrorHandling.errorPrint("Curl", "invalid input size");
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
	


