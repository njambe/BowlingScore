package be.njambe.BowlingScore.Bowling;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameTest {

	private Game game;

	@Before
	public void setUp() {
		game = new Game();
	}

	@After
	public void tearDown() {
		game = null;
	}

	@Test
	public void testGetScore_1() {
		try {
			game.addFrame(1, 5);
			assertEquals(6, game.getScore());
			game.addFrame(5, 1);
			assertEquals(12, game.getScore());
			game.addFrame(7, 2);
			assertEquals(21, game.getScore());

		} catch (Exception e) {
			fail("Exception thrown: " + e.getMessage());
		}
	}

	@Test
	public void testGetScore_2() {
		try {
			game.addFrame(10);
			assertEquals(10, game.getScore());
			game.addFrame(10);
			assertEquals(30, game.getScore());
			game.addFrame(10);
			assertEquals(60, game.getScore());
			game.addFrame(10);
			assertEquals(90, game.getScore());
			game.addFrame(10);
			assertEquals(120, game.getScore());
			game.addFrame(10);
			assertEquals(150, game.getScore());
			game.addFrame(10);
			assertEquals(180, game.getScore());
			game.addFrame(10);
			assertEquals(210, game.getScore());
			game.addFrame(10);
			assertEquals(240, game.getScore());
			game.addFrame(10, 10, 10);
			assertEquals(300, game.getScore());

		} catch (Exception e) {
			fail("Exception thrown: " + e.getMessage());
		}
	}

	@Test
	public void testGetScore_3() {
		try {
			game.addFrame(10);
			assertEquals(10, game.getScore());
			game.addFrame(10);
			assertEquals(30, game.getScore());
			game.addFrame(9, 0);
			assertEquals(57, game.getScore());

		} catch (Exception e) {
			fail("Exception thrown: " + e.getMessage());
		}
	}

	@Test
	public void testGetScore_4() {
		try {
			game.addFrame(10);
			assertEquals(10, game.getScore());
			game.addFrame(3, 6);
			assertEquals(28, game.getScore());
			game.addFrame(5, 3);
			assertEquals(36, game.getScore());

		} catch (Exception e) {
			fail("Exception thrown: " + e.getMessage());
		}
	}

	@Test
	public void testGetScore_5() {
		try {
			game.addFrame(4, 6);
			game.addFrame(4, 4);
			game.addFrame(10);
			game.addFrame(5, 5);
			game.addFrame(8, 1);
			game.addFrame(3, 7);
			game.addFrame(6, 4);
			game.addFrame(10);
			game.addFrame(7, 3);
			game.addFrame(2, 8, 6);
			assertEquals(153, game.getScore());

		} catch (Exception e) {
			fail("Exception thrown: " + e.getMessage());
		}
	}

	@Test
	public void testAddFrame() {
		try {
			game.addFrame(0, 0);
			assertEquals(new Frame(0, 0), game.getFrames().get(0));

			game.addFrame(10);
			assertEquals(new Frame(10), game.getFrames().get(1));

			game.addFrame(6, 3);
			assertEquals(new Frame(6, 3), game.getFrames().get(2));

			game.addFrame(4, 0);
			assertEquals(new Frame(4, 0), game.getFrames().get(3));

			game.addFrame(0, 3);
			assertEquals(new Frame(0, 3), game.getFrames().get(4));

			game.addFrame(7, 3);
			assertEquals(new Frame(7, 3), game.getFrames().get(5));

			game.addFrame(5, 5);
			assertEquals(new Frame(5, 5), game.getFrames().get(6));

			game.addFrame(3, 3);
			assertEquals(new Frame(3, 3), game.getFrames().get(7));

			game.addFrame(9, 0);
			assertEquals(new Frame(9, 0), game.getFrames().get(8));

			game.addFrame(10, 3, 6);
			assertEquals(new Frame(true, 10, 3, 6), game.getFrames().get(9));

			assertEquals(97, game.getScore());

		} catch (Exception e) {
			fail("Exception thrown: " + e.getMessage());
		}

		try {
			game.addFrame(1, 3);
			fail("Exception not thrown");
		} catch (IllegalArgumentException e) {
			fail("Wrong exception thrown: " + e.getMessage());
		} catch (Exception e) {
			// TODO Change exception type
		}

	}

	@Test
	public void testAddPinfall() {
		try {
			assertEquals(0, game.getFrames().size());

			game.addPinfall(0);
			assertEquals(0, game.getFrames().size());
			game.addPinfall(0);
			assertEquals(1, game.getFrames().size());
			assertEquals(new Frame(0, 0), game.getFrames().get(0));

		} catch (Exception e) {
			fail("Exception thrown: " + e.getMessage());
		}
	}

	@Test
	public void testAddPinfalls_1() {
		try {
			assertEquals(0, game.getFrames().size());

			game.addPinfalls(0, 0, 10, 6, 3, 4, 0, 0, 3, 7, 3, 5, 5, 3, 3, 9, 0, 10, 3, 6);

			assertEquals(10, game.getFrames().size());
			assertEquals(97, game.getScore());

		} catch (Exception e) {
			fail("Exception thrown: " + e.getMessage());
		}
	}

	@Test
	public void testAddPinfalls_2() {
		try {
			game.addPinfalls(1, 5, 5, 1, 7, 2);
			assertEquals(3, game.getFrames().size());
			assertEquals(21, game.getScore());

		} catch (Exception e) {
			fail("Exception thrown: " + e.getMessage());
		}
	}

	@Test
	public void testAddPinfalls_3() {
		try {
			game.addPinfalls(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
			assertEquals(10, game.getFrames().size());
			assertEquals(300, game.getScore());

		} catch (Exception e) {
			fail("Exception thrown: " + e.getMessage());
		}
	}

}
