package be.njambe.BowlingScore.Bowling;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import be.njambe.BowlingScore.Exception.BowlingLogicException;

public class FrameTest {

	@Test
	public void testIsStrike() throws BowlingLogicException {
		assertTrue((new Frame(10)).isStrike());
		assertTrue((new Frame(true, 10)).isStrike());
		assertTrue((new Frame(true, 10, 0)).isStrike());
		assertTrue((new Frame(true, 10, 0, 0)).isStrike());
		assertTrue((new Frame(true, 10, 5, 0)).isStrike());
		assertTrue((new Frame(true, 10, 10, 5)).isStrike());

		assertFalse((new Frame()).isStrike());
		assertFalse((new Frame(1)).isStrike());
		assertFalse((new Frame(9)).isStrike());
		assertFalse((new Frame(1, 9)).isStrike());
		assertFalse((new Frame(9, 1)).isStrike());
		assertFalse((new Frame(5, 5)).isStrike());
		assertFalse((new Frame(0, 10)).isStrike());
		assertFalse((new Frame(true, 5, 5, 10)).isStrike());
		assertFalse((new Frame(true, 5, 5, 0)).isStrike());
		assertFalse((new Frame(true, 5, 5)).isStrike());
		assertFalse((new Frame(0, 0)).isStrike());
		assertFalse((new Frame(5, 4)).isStrike());
		assertFalse((new Frame(9, 0)).isStrike());
	}

	@Test
	public void testIsSpare() throws BowlingLogicException {
		assertTrue((new Frame(1, 9)).isSpare());
		assertTrue((new Frame(9, 1)).isSpare());
		assertTrue((new Frame(5, 5)).isSpare());
		assertTrue((new Frame(0, 10)).isSpare());
		assertTrue((new Frame(true, 5, 5, 10)).isSpare());
		assertTrue((new Frame(true, 5, 5, 0)).isSpare());
		assertTrue((new Frame(true, 5, 5)).isSpare());

		assertFalse((new Frame()).isSpare());
		assertFalse((new Frame(1)).isSpare());
		assertFalse((new Frame(9)).isSpare());
		assertFalse((new Frame(10)).isSpare());
		assertFalse((new Frame(0, 0)).isSpare());
		assertFalse((new Frame(5, 4)).isSpare());
		assertFalse((new Frame(9, 0)).isSpare());
		assertFalse((new Frame(true, 10)).isSpare());
		assertFalse((new Frame(true, 10, 10)).isSpare());
		assertFalse((new Frame(true, 10, 5)).isSpare());
	}

	@Test
	public void testIsComplete() throws BowlingLogicException {
		assertTrue((new Frame(10)).isComplete());
		assertTrue((new Frame(5, 0)).isComplete());
		assertTrue((new Frame(5, 5)).isComplete());
		assertTrue((new Frame(0, 0)).isComplete());
		assertTrue((new Frame(true, 10, 10, 10)).isComplete());
		assertTrue((new Frame(true, 10, 0, 0)).isComplete());
		assertTrue((new Frame(true, 0, 0)).isComplete());
		assertTrue((new Frame(true, 5, 5, 0)).isComplete());

		assertFalse((new Frame()).isComplete());
		assertFalse((new Frame(1)).isComplete());
		assertFalse((new Frame(9)).isComplete());
		assertFalse((new Frame(true, 10)).isComplete());
		assertFalse((new Frame(true, 10, 10)).isComplete());
		assertFalse((new Frame(true, 10, 5)).isComplete());
		assertFalse((new Frame(true, 5, 5)).isComplete());
	}

	@Test
	public void testIsLastFrameOfGame() throws BowlingLogicException {
		assertFalse((new Frame()).isLastFrameOfGame());
		assertFalse((new Frame(0, 0)).isLastFrameOfGame());
		assertFalse((new Frame(false)).isLastFrameOfGame());
		assertFalse((new Frame(false, 0, 0)).isLastFrameOfGame());

		assertTrue((new Frame(true)).isLastFrameOfGame());
		assertTrue((new Frame(true, 0, 0)).isLastFrameOfGame());
	}

	@Test
	public void testValidate__ValidParameters() {
		try {
			// Not the last frame of the game
			new Frame(0, 0);
			new Frame(0, 10);
			new Frame(10);
			new Frame(5, 5);
			new Frame(7, 1);
			new Frame(9, 0);
			new Frame(9);

			// Last frame of the game
			new Frame(true, 9);
			new Frame(true, 10);
			new Frame(true, 0, 10, 0);
			new Frame(true, 0, 10, 5);
			new Frame(true, 10, 0, 0);
			new Frame(true, 10, 10, 10);
			new Frame(true, 10, 0, 5);
			new Frame(true, 5, 5, 5);
		} catch (Exception e) {
			fail("Exception raised when creating a Frame: " + e.getMessage());
		}
	}

	@Test
	public void testValidate__InvalidParameters() {
		testValidate__InvalidParametersHelper("Maximum 3 pinfalls within the tenth frame", true, 0, 0, 0, 0);
		testValidate__InvalidParametersHelper("Maximum 2 pinfalls within a frame", false, 0, 0, 0);
		testValidate__InvalidParametersHelper("At least one pitfall is required", true);
		testValidate__InvalidParametersHelper("Pinfalls must be between 0 and 10", false, 20);
		testValidate__InvalidParametersHelper("Pinfalls must be between 0 and 10", false, 5, 11);
		testValidate__InvalidParametersHelper("Pinfalls must be between 0 and 10", false, 5, -1);
		testValidate__InvalidParametersHelper("Pinfalls must be between 0 and 10", true, 2, 3, 12);
		testValidate__InvalidParametersHelper("Pinfalls must be between 0 and 10", true, 2, 3, -5);
		testValidate__InvalidParametersHelper("Not possible to fall more than 10 pins within a frame", false, 9, 9);
		testValidate__InvalidParametersHelper("Not possible to fall more than 10 pins within a frame", false, 9, 2);
		testValidate__InvalidParametersHelper("Not possible to fall more than 10 pins within a frame", false, 1, 10);
		testValidate__InvalidParametersHelper("Not possible to fall more than 10 pins within a frame", true, 10, 6, 6);
		testValidate__InvalidParametersHelper("Strike ends the frame", false, 10, 0);
		testValidate__InvalidParametersHelper("Strike ends the frame", false, 10, 5);
		testValidate__InvalidParametersHelper("Strike ends the frame", false, 10, 10);
		testValidate__InvalidParametersHelper("Third launch only when possible", true, 0, 0, 1);
		testValidate__InvalidParametersHelper("Third launch only when possible", true, 9, 0, 0);
	}

	private void testValidate__InvalidParametersHelper(String message, boolean isLastFrameOfGame, int... pinfalls) {
		try {
			new Frame(isLastFrameOfGame, pinfalls);
			fail("Exception not raised");
		} catch (BowlingLogicException e) {
			if (!e.getMessage().equals(message))
				fail("Exception message is not correct. Expected: '" + message + "', got: '" + e.getMessage() + "'");
		}
	}

	@Test
	public void testGetAbsoluteScore() throws BowlingLogicException {
		// Incomplete frame
		assertEquals(1, new Frame(1).getAbsoluteScore());
		assertEquals(5, new Frame(5).getAbsoluteScore());
		assertEquals(9, new Frame(9).getAbsoluteScore());

		// Complete frame, no spare, no strike
		assertEquals(0, new Frame(0, 0).getAbsoluteScore());
		assertEquals(1, new Frame(1, 0).getAbsoluteScore());
		assertEquals(1, new Frame(0, 1).getAbsoluteScore());
		assertEquals(5, new Frame(5, 0).getAbsoluteScore());
		assertEquals(5, new Frame(0, 5).getAbsoluteScore());
		assertEquals(9, new Frame(9, 0).getAbsoluteScore());
		assertEquals(9, new Frame(0, 9).getAbsoluteScore());
		assertEquals(9, new Frame(6, 3).getAbsoluteScore());
		assertEquals(9, new Frame(5, 4).getAbsoluteScore());

		// Spare
		assertEquals(10, new Frame(5, 5).getAbsoluteScore());
		assertEquals(10, new Frame(1, 9).getAbsoluteScore());
		assertEquals(10, new Frame(9, 1).getAbsoluteScore());
		assertEquals(10, new Frame(0, 10).getAbsoluteScore());

		// Strike
		assertEquals(10, new Frame(10).getAbsoluteScore());
	}
}
