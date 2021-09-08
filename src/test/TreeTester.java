package test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import driver.Command;
import driver.DirectoryNode;
import driver.Echo;
import driver.FileNode;
import driver.MakeDirectory;
import driver.Node;
import driver.Tree;
import java.io.Console;

public class TreeTester {
	private DirectoryNode node1,node2; //declare DirectoryNode objects
	private FileNode node3,node4; //declare FileNode objects
	private MakeDirectory mkdir; //declare MakeDirectory object
	private Echo mkfile; //declare Echo object
	private Tree tree; // declare tree object
	private ByteArrayOutputStream outContent; //declare ByteArrayOutStream object
	private ArrayList<String> input; // declare ArrayList object
	
	@Before
	public void setUp() throws Exception {
		outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		mkdir = new MakeDirectory();
		mkfile = new Echo();
		tree = new Tree();
		input = new ArrayList();
		node1 = new DirectoryNode("a","/a");
		node2 = new DirectoryNode("b","/a/b");
		node3 = new FileNode("c","/c");
		node4 = new FileNode("d","/a/d");
	}

	@Test
	public void testExecute() {
		//test case where user invalid input size
		input.add("a");
		tree.execute(input);
		assertEquals("Error in Tree. Reason: incorrect input size.",outContent.toString().strip());
	
	}


	@Test
	public void testSpaces() {
		assertEquals("",tree.spaces(0));
		assertEquals(" ",tree.spaces(1));
		assertEquals("          ",tree.spaces(10));
	}

	@Test
	public void testSystemTraversal() {
		input.add(node1.getAddress());
		input.add(node2.getAddress());
		mkdir.execute(input);
		input.clear();		
		input.add('"'+ "hi"+'"');
		input.add(">");
		input.add(node3.getAddress());
		mkfile.execute(input);
		input.clear();
		input.add('"'+ "hi"+'"');
		input.add(">");
		input.add(node4.getAddress());
		mkfile.execute(input);
		tree.systemTraversal(Command.getRoot(), 0);
		assertEquals("/\r\n"
				+ " a\r\n"
				+ "  b\r\n"
				+ "  d\r\n"
				+ " c",outContent.toString().strip());
	}

}
