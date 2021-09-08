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

public class Finder{
	
	//Helper function that tries to traverse the path
	//@param String path, the address of the target node
	//Return node if it reaches the end of path and null o/w.
	public static Node returnDir(String path){
		Node temp = Command.cwd;
		String[] path_list = {};
		if(path != null) {
			if(path.length() > Command.root.address.length()) {
				if(path.substring(0, Command.root.address.length())
						.equals(Command.root.address)) {
					temp = Command.root;
					path = path.substring(Command.root.address.length());
				}
			}
			if(path.length() == 1) {
				path_list = path.split("");
			}else {
				path_list = path.split("/");
			}
		}
		int i = 0;
		//Denoting the progress of the path 
		for (; i < path_list.length; i++){ 		
			//Updates the node as we move through the path
			if(path_list[i].equals(Command.root.address)) {
				temp = Command.root;
			}else if(path_list[i].equals(".")) {
				temp = Command.cwd;
			}else if (path_list[i].equals("..")){
				temp = temp.getparent();
			}else if (temp.getChild().size() == 0) {
				i = path_list.length+1;
			}else {
				for(int j = 0; j<temp.getChild().size() ;j++) {
					if(path_list[i].equals(temp.getChild().get(j))){
						temp = temp.children.get(j);
						break;
					}else if(j == temp.getChild().size()-1) {
						i = path_list.length+1;
					}
				}
			}
		}
		//Checks if the end of path has been reached
		if (i == path_list.length){
			return temp;
		}else{
			return null;
		}
	}
	
	/*
	 * Method that checks if there already exists a children
	 * for a directory that has a specified name
	 * @param String name, the name that we are checking
	 * to see if there is a duplicate
	 * @param String path, the address of the parent directory
	 * we are concerned with
	 * @return int that represents states
	 * 0: Found duplicate
	 * 1: No duplicate
	 * 2: Invalid path or path leads to non-directory node
	 */
	public static int isDuplicateName(String name, String path) {
		
		if(Finder.returnDir(path) instanceof DirectoryNode) { 
			DirectoryNode temp= (DirectoryNode) Finder.returnDir(path);

			if(temp.getChild().indexOf(name)!= -1) {
				return 0 ; // duplicate name
			}
			
			else return 1; // no duplicate
			
		}
		return 2; // path doesn't exist, or it isn't directory
			
		}
}
