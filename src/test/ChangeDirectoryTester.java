package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import driver.*;
import org.junit.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;

public class ChangeDirectoryTester {
	private ChangeDirectory cd;
	private DirectoryNode test1, test2;
	private String manual = "cd DIR\n\nChange directory to DIR, which may be "
	        + "relative to the current directory or\r\n"
	        + "may be a full path. As with Unix, .. means a parent directory and "
	        + "a . means\r\n"
	        + "the current directory. The directory must be /, the forward slash. "
	        + "The foot of\r\n"
	        + "the file system is a single slash: /.";
	
	
	private static DirectoryNode root;
	private static DirectoryNode cwd;
	private Command commander;
	private Manual manual_class;
	private ArrayList<String> inputList, inputList2, inputList3;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	private String path, path2, path3;
	private MakeDirectory mkdir;
	
	@Before
	public void setUp() throws Exception {
		root = new DirectoryNode("/", "/");
		cwd = root;
		cd = new ChangeDirectory();
		mkdir = new MakeDirectory();
		manual_class = new Manual();
		inputList = new ArrayList<String>();
		inputList2 = new ArrayList<String>();
		inputList3 = new ArrayList<String>();
		path = "a";
		path2 = "/a/b";
		path3 = "$";
		inputList.add(path);
		inputList3.add(path3);
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
		
	}

	@After
	public void restoreStreams() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}
	
	@Test
	public void testExecuteArrayListOfString() {
		
		cd.execute(inputList3);
		assertEquals("Error in cd. Reason: " + path3 + " is not a valid directory.", outContent.toString().strip());
		mkdir.execute(inputList);
		cd.execute(inputList);
		assertEquals("/a", Command.getCWD().getAddress());
		inputList2.add(path2);
		mkdir.execute(inputList2);
		cd.execute(inputList2);
		assertEquals("/a/b", Command.getCWD().getAddress());

	}

	@Test
	public void testExecute() {
		cd.execute();
		assertEquals("Error in cd. Reason: one parameter was expected.", outContent.toString().strip());
		
	}
	@Test
	public void testPrintManual() {
		//Setup output handler to simply print
		OutputHandler oh = OutputHandler.initializeOutputHandler(null, null);
		cd.printManual(oh);
		assertEquals(manual, outContent.toString().strip());	
	}
}
