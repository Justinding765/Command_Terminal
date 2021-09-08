package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import driver.*;

public class ListTester {
	private MakeDirectory mkdir;
	private ArrayList<String> testInput; 
	private List ls;
	private String manual = "ls [-R] [PATH . . . ]\n\nif {R is present, "
	        + "lists all subdirectories recursively.\r\n"
	        + "If no paths are given, prints the contents (file or directory) "
	        + "of the current directory, with a new line following\r\n"
	        + "each of the content (file or directory).\r\n"
	        + "If a given path is a file, prints the name of the file.\r\n"
	        + "If it is a directory, print the children of the directory.";
	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@Before
	public void setUp() throws Exception {
		ls = new List();
		mkdir = new MakeDirectory();
		testInput = new ArrayList<String>();
		testInput.add("/a");						 
		testInput.add("/a/b");
		testInput.add("/b");
		testInput.add("/b/b");
		mkdir.execute(testInput);
		System.setOut(new PrintStream(outContent));
		testInput = new ArrayList<String>();
	}

	@Test
	public void testExecuteArrayListOfString() {
		testInput.add("/a");
		ls.execute(testInput);
		assertEquals("/a: " + System.lineSeparator() 
			+ "b" + System.lineSeparator(), outContent.toString());
	}

	@Test
	public void testExecute() {
		ls.execute();
		assertEquals("a" + System.lineSeparator() 
		+ "b" + System.lineSeparator(), outContent.toString());
	}

	@Test
	public void testPrintManual() {
		OutputHandler oh = OutputHandler.initializeOutputHandler(null, null);
		ls.printManual(oh);
		assertEquals(manual + System.lineSeparator(), outContent.toString());
	}

}
