package test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import driver.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ConcatenateTester {

	private ArrayList<String> test_files, echo_input, echo_input1;
	private Concatenate cat;
	//private String test, test1;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	private String manual = "cat FILE1 [FILE2 …]\n\nDisplay the "
		      + "contents of FILE1 "
		      + "and other files (i.e. File2 ….) concatenated in\r\n"
		      + "the shell.";
	private String test1, test2;
	private Echo echo;
	
	@Before
	public void setUp() throws Exception {
		cat = new Concatenate();
		test_files = new ArrayList<String>();
		
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
		test1 = "test1";
		test2 = "test2";
		echo = new Echo();
		
}	
		
	@After
	public void restoreStreams() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}
	
	@Test
	public void testExecuteWtihArrayList() {
		echo_input = new ArrayList<String>();
		echo_input.add("\""+test1+"\"");
		echo_input.add(">");
		echo_input.add("file_name");
		echo.execute(echo_input);
		
		echo_input1 = new ArrayList<String>();
		echo_input1.add("\""+test2+"\"");
		echo_input1.add(">");
		echo_input1.add("file_name2");
		echo.execute(echo_input1);
		
		test_files.add("file_name");
	
		test_files.add("file_name2");
		cat.clearCombined();
		
		cat.execute(test_files);
		assertEquals("test1\n\n\ntest2",outContent.toString().strip());
				
	}
	
	@Test
	public void testExecute() {
		cat.execute();
		assertEquals("Error in cat. Reason: at least one parameter is expected.", outContent.toString().strip());
	}

	@Test
	public void testPrintManual() {
		OutputHandler oh = OutputHandler.initializeOutputHandler(null, null);
		cat.printManual(oh);
		assertEquals(manual, outContent.toString().strip());
	}

	@Test
	public void testConcatenate() {
		
		echo_input = new ArrayList<String>();
		echo_input.add("\""+test1+"\"");
		echo_input.add(">");
		echo_input.add("file_name");
		echo.execute(echo_input);
		
		echo_input1 = new ArrayList<String>();
		echo_input1.add("\""+test2+"\"");
		echo_input1.add(">");
		echo_input1.add("file_name2");
		echo.execute(echo_input1);
		
		test_files.add("file_name");
		cat.concatenate(test_files);
		assertEquals("test1",cat.getCombined());
		
		
		test_files.add("file_name2");
		cat.clearCombined();
		cat.concatenate(test_files);
		assertEquals("test1\n\n\ntest2",cat.getCombined());
		
	}

}
