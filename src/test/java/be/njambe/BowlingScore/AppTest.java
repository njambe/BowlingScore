package be.njambe.BowlingScore;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import be.njambe.BowlingScore.Exception.BowlingLogicException;

public class AppTest {

	private App app;

	@Before
	public void setUp() {
		app = new App();
	}

	@After
	public void tearDown() {
		app = null;
	}

	@Test
	public void testCalculateScore() {
		try {
			assertEquals(5, app.calculateScore("5"));
			assertEquals(12, app.calculateScore("5,4,3"));
			assertEquals(21, app.calculateScore("1, 5, 5, 1, 7, 2"));
			assertEquals(300, app.calculateScore("10, 10 ,10 ,10,10, 10, 10 ,10 ,10, 10, 10 ,10"));
			assertEquals(57, app.calculateScore("10, 10 ,9, 0"));
			assertEquals(36, app.calculateScore("10, 3, 6, 5, 3"));
			assertEquals(97, app.calculateScore("0, 0, 10, 6, 3, 4, 0, 0, 3, 7, 3, 5, 5, 3, 3, 9, 0, 10, 3, 6"));
			assertEquals(153, app.calculateScore("4,6,4,4,10,5,5,8,1,3,7,6,4,10,7,3,2,8,6"));
			assertEquals(47, app.calculateScore("10, 9, 1, 3 ,6, 5"));
		} catch (Exception e) {
			fail("Exception thrown: " + e.getMessage());
		}
	}

	@Test
	public void testCalculateScore__InvalidInput() {
		testCalculateScore__InvalidInputHelper("a,1,2", NumberFormatException.class);
		testCalculateScore__InvalidInputHelper("0,11,2", BowlingLogicException.class);
		testCalculateScore__InvalidInputHelper("1-2-3", NumberFormatException.class);
		testCalculateScore__InvalidInputHelper("1 2 3 4 5", BowlingLogicException.class);
		testCalculateScore__InvalidInputHelper("1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10",
				BowlingLogicException.class);

	}

	private void testCalculateScore__InvalidInputHelper(String pinfalls, Class<?> x) {
		try {
			app.calculateScore(pinfalls);
			fail("Exception not raised");
		} catch (Exception e) {
			if (!e.getClass().equals(x))
				fail("Exception type is not correct. Expected: '" + x + "', got: '" + e.getClass() + "'");
		}
	}

}
