package test;

import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import driver.*;

public class HistoryTester {
	ArrayList<String> testInput;
	History history;
	ByteArrayOutputStream outContent;
	private String manual = "history [number]\n\nThis command will "
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
	
	@Before
	public void setUp() throws Exception {
		History.clear();
		history = new History();
		outContent = new ByteArrayOutputStream();
		History.insert("Command #1");
		History.insert("Command #2");
		History.insert("Command #3");
		System.setOut(new PrintStream(outContent));
	}

	@Test
	public void testExecuteArrayListOfString() {
		testInput = new ArrayList<String>();
		testInput.add("2");
		history.execute(testInput);
		assertEquals("2. Command #2" + System.lineSeparator()
				+ "3. Command #3" + System.lineSeparator()
				, outContent.toString());
	}

	@Test
	public void testExecute() {
		history.execute();
		assertEquals("1. Command #1" + System.lineSeparator()
		        + "2. Command #2" + System.lineSeparator()
				+ "3. Command #3" + System.lineSeparator()
				, outContent.toString());
	}

	@Test
	public void testPrintManual() {
		OutputHandler oh = OutputHandler.initializeOutputHandler(null,null);
		history.printManual(oh);
		assertEquals(manual + System.lineSeparator(), outContent.toString());
	}
}
