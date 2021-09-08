package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import driver.Command;
import driver.Copy;
import driver.DirectoryNode;
import driver.Echo;
import driver.FileNode;
import driver.Finder;
import driver.MakeDirectory;

public class CopyTester {
	private DirectoryNode node1,node2; //declare DirectoryNode objects
	private FileNode node3,node4; //declare FileNode objects
	private MakeDirectory mkdir; //declare MakeDirectory object
	private Echo mkfile; //declare Echo object
	private Copy copy; //declare Copy object
	private ArrayList<String> input; //declare ArrayList object
	@Before
	public void setUp() throws Exception {
		mkdir = new MakeDirectory();
		mkfile = new Echo();
		input = new ArrayList();
		copy = new Copy();
		node3 = new FileNode("1", "/a/b/1");
		node4 = new FileNode("2", "/a/b/2");
			
	}

	@Test
	public void testCopy() {
		input.add("/a");
		input.add("/a/b");
		mkdir.execute(input);
		input.clear();
		input.add('"'+ "hi" +  '"');
		input.add(">");
		input.add(node3.getAddress());
		mkfile.execute(input);
		input.clear();
		input.add('"'+ "bye" +  '"');
		input.add(">");
		input.add(node4.getAddress());
		mkfile.execute(input);
		input.clear();	
		copy.copy(Finder.returnDir("/a/b"), Command.getRoot());
		assertEquals(Finder.returnDir("/a/b").getName(),Finder.returnDir("/a/b").getName());
		assertEquals(Finder.returnDir("/a/b").getChild(),Finder.returnDir("/a/b").getChild());
		
	}
	@Test
	public void testExecuteArrayListOfString() {
	}

	@Test
	public void testValidForCp() {
		
	}

}
