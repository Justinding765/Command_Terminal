package test;

import static org.junit.Assert.*;

import org.junit.*;
import java.util.*;


import driver.Node;

public class NodeTester {

	private Node tester, tester2;	

	@Before
	public void setUp() throws Exception {
		tester = new Node("test1","/");
		tester2 = new Node();
		
	}

	@Test
	public void testNode() {
		assertTrue(tester instanceof Node);
	
	}

	@Test
	public void testNodeStringString() {
		assertTrue(tester.getName() == "test1");
		assertTrue(tester instanceof Node);
	}

	@Test
	public void testGetparent() {
		tester.setParent(tester2);
		assertEquals(tester2, tester.getparent());
		
	}

	@Test
	public void testSetParent() {
		tester.setParent(tester2);
		
	}

	@Test
	public void testGetName() {
		assertEquals("test1", tester.getName());
	}

	@Test
	public void testGetAddress() {
		assertEquals("", tester.getAddress());
	}

	@Test
	public void testGetChild() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetChildNodes() {
		fail("Not yet implemented");
	}

}
