package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import driver.*;
public class IsValidNameTester {
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCheckValidName() {
		String test1 = "validName";
		assertEquals(false, IsValidName.checkValidName(test1));
		String test2 = "B$dName";
		assertEquals(true, IsValidName.checkValidName(test2));
		String test3 = "";
		assertEquals(true, IsValidName.checkValidName(test3));
		String test4 = "/";
		assertEquals(true, IsValidName.checkValidName(test4));
	}

}
