package test;

import static org.junit.Assert.*;
import driver.*;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import org.junit.*;


public class ManualTester {
    private ArrayList<String> inputList;
    private Manual man;
    private String manManual = "man CMD\n\nPrint documentation for CMD";
    private String cdManual = "cd DIR\n\nChange directory to DIR, which may be "
        + "relative to the current directory or\r\n"
        + "may be a full path. As with Unix, .. means a parent directory and "
        + "a . means\r\n"
        + "the current directory. The directory must be /, the forward slash. "
        + "The foot of\r\n"
        + "the file system is a single slash: /.";
    private String catManual = "cat FILE1 [FILE2 …]\n\nDisplay the "
        + "contents of FILE1 "
        + "and other files (i.e. File2 ….) concatenated in\r\n"
        + "the shell.";
    private String cpManual = "cp OLDPATH NEWPATH\n\n"
        + "Like mv, but don't remove OLDPATH. If OLDPATH\r\n"
        + "is a directory, recursively copy the contents.";
    private String echoManual = "echo STRING [> OUTFILE]\n\nIf OUTFILE "
        + "is not provided,"
        + " print STRING on the shell. Otherwise, put\r\n"
        + "STRING into file OUTFILE. STRING is a "
        + "string of characters surrounded by double\r\n"
        + "quotation marks. This creates a new file "
        + "if OUTFILE does not exists and erases the\r\n"
        + "old contents if OUTFILE already exists. "
        + "In either case, the only thing in OUTFILE\r\n"
        + "should be STRING.\n\n"
        + "echo STRING >> OUTFILE\n\nLike the previous command, but appends "
        + "instead of overwrites.";
    private String exitManual = "exit\n\nQuit the program";
    private String historyManual = "history [number]\n\nThis command will "
        + "print out recent commands, one command per line. i.e.\r\n"
        + "1. cd ..\r\n"
        + "2. mkdir textFolder\r\n"
        + "3. echo “Hello World”\r\n"
        + "4. fsjhdfks\r\n"
        + "5. history\r\n"
        + "The above output from history has two columns. The:first\r\n"
        + " column is numbered such that the line with the highest number "
        + "is the most recent command.\r\n"
        + "The most recent command is history. "
        + "The second column contains the actual\r\n"
        + "command.";
    private String lsManual = "ls [-R] [PATH . . . ]\n\nif {R is present, "
        + "lists all subdirectories recursively.\r\n"
        + "If no paths are given, prints the contents (file or directory) "
        + "of the current directory, with a new line following\r\n"
        + "each of the content (file or directory).\r\n"
        + "If a given path is a file, prints the name of the file.\r\n"
        + "If it is a directory, print the children of the directory.";
    private String loadJShellManual = "loadJShell FileName\n\n"
        + "Load the contents of the FileName\r\nand reinitialize everything "
        + "that was saved previously into the file FileName. This allows for"
        + "the user to restore a previous JShell session.";
    private String mkdirManual = "mkdir DIR1 [DIR2] ...\n\n Creates directories"
        + " at "
        + "the paths specified by DIR1, DIR2 ...\n"
        + "These paths may be relative or absolute. You may not use this "
        + "command to create\n a directory with a name that is already used "
        + "by\n another directory or file at the same path.";
    private String mvManual = "mv OLDPATH NEWPATH\n\n"
        + "Move item OLDPATH to NEWPATH. Both OLD-\r\n"
        + "PATH and NEWPATH may be relative "
        + "to the current directory or may be full paths. If NEWPATH is a\r\n"
        + "directory, move the item into the directory.";
    private String popdManual = "popd\n\nRemove the top entry from the "
        + "directory stack, and navigate into it."
        + "The popd command\r\n"
        + "removes the top most directory from the "
        + "directory stack and makes it the\r\n"
        + "current working directory.";
    private String pwdManual = "pwd\n\nPrint the current working directory"
        + " (including the whole path).";
    private String pushdManual = "pushd DIR\n\nSaves the "
        + "current working directory by "
        + "pushing onto directory stack and then\r\n"
        + "changes the new current working directory to DIR. "
        + "The push must be\r\n"
        + "consistent as per the LIFO behavior of a stack. "
        + "The pushd command saves\r\n"
        + "the old current working directory in directory stack "
        + "so that it can be returned\r\n"
        + "to at any time (via the popd command). "
        + "The size of the directory stack is\r\n"
        + "dynamic and dependent on the pushd and the popd commands..";
    private String rmManual = "rm DIR\n\nremoves the DIR from the file system."
        + "\nThis also removes all the children of DIR.";
    private String saveJShellManual = "saveJShell FileName\n\n"
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
    private String searchManual = "search path ... -type [f|d] "
        + "-name expression\n\n"
        + "Search the given path(s) for a file or directory (determined by"
        + "input)\r\n with the exact name given by 'expression'."
        + " The paths may be relative or full paths.";
    private String treeManual = "tree\n\n"
        + "Display the entire file system as a tree, starting from the root.";
    private String curlManual = "curl URL\n\n"
        + "Retrieve the file at that URL\r\n"
        + "and add it to the current working directory.";
    
    private final ByteArrayOutputStream outContent= new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent= new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    
	@Before
	public void setUp() throws Exception {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	    
	    inputList = new ArrayList<String>();
	    man = new Manual();
	}
	
	@After
	public void restoreStreams() {
	  System.setOut(originalOut);
	  System.setErr(originalErr);
	}

	@Test
	public void testEmptyArrayList() {
	    
	    man.execute(inputList);
	    assertEquals("Error in man. Reason: exactly 1 parameter was expected.",
	        outContent.toString().strip());
	}
	

	@Test
	public void testPrintManual() {
	    OutputHandler oh = OutputHandler.initializeOutputHandler(null, null);
	    man.printManual(oh);
	    assertEquals(manManual, outContent.toString().strip());
	}
	
	@Test
	public void testCD() {
	  inputList.clear();
      inputList.add("cd");
      man.execute(inputList);
      assertEquals(cdManual, outContent.toString().strip());
	}
	
	@Test
    public void testCat() {
	  inputList.clear();
	  inputList.add("cat");
      man.execute(inputList);
      assertEquals(catManual, outContent.toString().strip());
    }
	
	@Test
    public void testCP() {
      inputList.clear();
      inputList.add("cp");
      man.execute(inputList);
      assertEquals(cpManual, outContent.toString().strip());
    }
	
	@Test
    public void testEcho() {
      inputList.clear();
      inputList.add("echo");
      man.execute(inputList);
      assertEquals(echoManual, outContent.toString().strip());
    }
	
	@Test
    public void testExit() {
      inputList.clear();
      inputList.add("exit");
      man.execute(inputList);
      assertEquals(exitManual, outContent.toString().strip());
    }
	
	@Test
    public void testHistory() {
      inputList.clear();
      inputList.add("history");
      man.execute(inputList);
      assertEquals(historyManual, outContent.toString().strip());
    }
	
	@Test
    public void testLS() {
      inputList.clear();
      inputList.add("ls");
      man.execute(inputList);
      assertEquals(lsManual, outContent.toString().strip());
    }
	
	@Test
    public void testLoadJShell() {
      inputList.clear();
      inputList.add("loadJShell");
      man.execute(inputList);
      assertEquals(loadJShellManual, outContent.toString().strip());
    }
	
	@Test
    public void testMkdir() {
      inputList.clear();
      inputList.add("mkdir");
      man.execute(inputList);
      assertEquals(mkdirManual, outContent.toString().strip());
    }
	
	@Test
    public void testMan() {
      inputList.clear();
      inputList.add("man");
      man.execute(inputList);
      assertEquals(manManual, outContent.toString().strip());
    }
	
	@Test
    public void testMV() {
      inputList.clear();
      inputList.add("mv");
      man.execute(inputList);
      assertEquals(mvManual, outContent.toString().strip());
    }
	
	@Test
    public void testPopd() {
      inputList.clear();
      inputList.add("popd");
      man.execute(inputList);
      assertEquals(popdManual, outContent.toString().strip());
    }
	
	@Test
    public void testPwd() {
      inputList.clear();
      inputList.add("pwd");
      man.execute(inputList);
      assertEquals(pwdManual, outContent.toString().strip());
    }
	
	@Test
    public void testPushd() {
      inputList.clear();
      inputList.add("pushd");
      man.execute(inputList);
      assertEquals(pushdManual, outContent.toString().strip());
    }
	
	@Test
    public void testRM() {
      inputList.clear();
      inputList.add("rm");
      man.execute(inputList);
      assertEquals(rmManual, outContent.toString().strip());
    }
	
	@Test
    public void testSaveJShell() {
      inputList.clear();
      inputList.add("saveJShell");
      man.execute(inputList);
      assertEquals(saveJShellManual, outContent.toString().strip());
    }
	
	@Test
    public void testSearch() {
      inputList.clear();
      inputList.add("search");
      man.execute(inputList);
      assertEquals(searchManual, outContent.toString().strip());
    }
	
	@Test
    public void testTree() {
      inputList.clear();
      inputList.add("tree");
      man.execute(inputList);
      assertEquals(treeManual, outContent.toString().strip());
    }
	
	@Test
    public void testCurl() {
      inputList.clear();
      inputList.add("curl");
      man.execute(inputList);
      assertEquals(curlManual, outContent.toString().strip());
    }
	
	@Test
    public void testMultipleInvalidInputs() {
      inputList.clear();
      inputList.add("these");
      inputList.add("are");
      inputList.add("not");
      inputList.add("valid");
      inputList.add("commands");
      man.execute(inputList);
      assertEquals("Error in man. Reason: exactly 1 parameter was expected.",
          outContent.toString().strip());
    }
	
	@Test
    public void testSingleInvalidInput() {
      inputList.clear();
      inputList.add("bread");
      man.execute(inputList);
      assertEquals("Error in man. Reason: invalid command.",
          outContent.toString().strip());
    }
	
	@Test
    public void testExecute() {
      man.execute();
      assertEquals("Error in man. Reason: exactly 1 parameter was expected.",
          outContent.toString().strip());
    }
}
