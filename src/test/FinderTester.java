package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import driver.*;
import java.util.ArrayList;

public class FinderTester {
	ArrayList<String> testInput = new ArrayList<String>();
	MakeDirectory mkdir = new MakeDirectory();
	
	@Before
	public void setUp() throws Exception {
		testInput.add("/a");
		testInput.add("/a/a");
		testInput.add("/b");
		testInput.add("/b/a");
		mkdir.execute(testInput);
		testInput.clear();
	}

	@Test
	public void testReturnDir() {
		assertEquals(Finder.returnDir("/a/a"), 
				Command.getRoot().getChildNodes().
				get(0).getChildNodes().get(0));
	}

	@Test
	public void testIsDuplicateName1() {
		assertEquals(0, Finder.isDuplicateName("a", "/b"));
		assertEquals(1, Finder.isDuplicateName("a", "/b/a"));
	}
}
