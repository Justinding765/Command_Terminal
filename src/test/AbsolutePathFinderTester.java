package test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import driver.AbsolutePathFinder;
import driver.Command;
import driver.Node;

public class AbsolutePathFinderTester {
	private AbsolutePathFinder tester; //declare AbsolutePathFinder object
	private Node temp, temp2; //declare Node objects
	
	@Before
	public void setUp() throws Exception {
		tester = new AbsolutePathFinder();//Initialize
		temp = new Node("node1", "/node1"); //Initialize
		temp2 = new Node("node2", "/node1/node2");//initialize

	}

	@Test
	public void testGetAbsPath() {
		
		assertEquals("/node1",tester.getAbsPath(temp));
		assertEquals("/node1/node2",tester.getAbsPath(temp2));
		assertEquals("/",tester.getAbsPath(Command.getRoot()));

	}

}
