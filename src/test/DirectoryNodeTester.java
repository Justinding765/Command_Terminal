package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import driver.DirectoryNode;
import driver.Node;

import org.junit.Rule;

public class DirectoryNodeTester {
	private DirectoryNode tester;
	private Node test_node;
	
	@Before
	public void setUp() {
		tester = new DirectoryNode("test1","/");
		test_node = new Node("test2","/");
		tester.setChild(test_node);
	}
	
	@Test
	public void testDirectoryNode() {
		assertTrue(tester instanceof DirectoryNode);
	}

	@Test
	public void testSetChild() {
		tester.setChild(test_node);
		assertTrue(tester instanceof DirectoryNode);
		assertEquals(test_node, tester.getChildNodes().get(0));
		
	}
}
