package test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import driver.*;

public class SearchTester {
	private Search search;
	private MakeDirectory mkdir;
	private ArrayList<String> testInput; 
	ByteArrayOutputStream outContent;
	private String manual = "search path ... -type [f|d] -name expression\n\n"
	        + "Search the given path(s) for a file or directory (determined by"
	        + "input)\r\n with the exact name given by 'expression'."
	        + " The paths may be relative or full paths.";
	@Before
	public void setUp() throws Exception {
		outContent =  new ByteArrayOutputStream();
		testInput = new ArrayList<String>();
		search = new Search();
		mkdir = new MakeDirectory();         
		testInput.add("/a");						 
		testInput.add("/a/b");					     
		mkdir.execute(testInput);
		System.setOut(new PrintStream(outContent));
	}

	@Test
	public void testExecuteArrayListOfStringNoRedirect() {
		testInput = new ArrayList<String>();
		testInput.add("/");
		testInput.add("-type");
		testInput.add("d");
		testInput.add("-name");
		String input = '"' + "b" + '"';
		testInput.add(input);
		search.execute(testInput);
		assertEquals("Results in " + "/" + System.lineSeparator()
		+ "/a/b" + System.lineSeparator(), outContent.toString());
	}	
	
	public void testExecuteArrayListOfStringRedirect() {
		testInput = new ArrayList<String>();
		testInput.add("/");
		testInput.add("-type");
		testInput.add("d");
		testInput.add("-name");
		String input = '"' + "b" + '"';
		testInput.add(input);
		testInput.add(">");
		testInput.add("file");
		search.execute(testInput);
		try {
			assertEquals("Results in " + "/" + System.lineSeparator()
			+ "/a/b" + System.lineSeparator(), 
			((FileNode)Finder.returnDir("file")).getContent());
		}catch (Exception e) {
			fail("File DNE");
		}
	}	
	
	@Test
	public void testPrintManual() {
		OutputHandler oh = OutputHandler.initializeOutputHandler(null, null);
		search.printManual(oh);
		assertEquals(manual + System.lineSeparator(), outContent.toString());
	}
	
	@Test
	public void testSearch() {
		OutputHandler oh = OutputHandler.initializeOutputHandler(null, null);
		search.search(Command.getRoot(), "d", "a", oh);
		assertEquals("/a" + System.lineSeparator(), outContent.toString());
	}
	
	@Test
	public void testIsValidSearch() {
		testInput = new ArrayList<String>();
		testInput.add("/");
		testInput.add("-type");
		testInput.add("d");
		testInput.add("-name");
		String input = '"' + "b" + '"';
		testInput.add(input);
		Echo temp = new Echo();
		assertEquals(true,search.isValidSearch(testInput, temp));
	}
	
}
