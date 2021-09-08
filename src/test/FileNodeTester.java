package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import driver.*;


public class FileNodeTester {

	
	private FileNode file1, file2;
	private String test;
	
	
	
	@Before
	public void setUp() throws Exception {
		file1 = new FileNode("File","/");
	}

	@Test
	public void testFileNode() {
		assertTrue(file1 instanceof FileNode);
		
	}

	@Test
	public void testSetContent() {
		file1.setContent("hello");
		//assertEquals(file1.
	}

	@Test
	public void testAppendContent() {
		file1.appendContent("hi");
		assertEquals("hi",file1.getContent());
		
	}

	@Test
	public void testGetContent() {
		file1.setContent("hello");
		String test = file1.getContent();
		assertEquals("hello", test);
	}

}
