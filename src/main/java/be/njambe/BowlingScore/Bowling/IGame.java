package be.njambe.BowlingScore.Bowling;

import be.njambe.BowlingScore.Exception.BowlingLogicException;

/**
 * Interface for a Bowling game
 *
 * @author Nathan Jambe
 *
 */
public interface IGame {

	/**
	 * Get the total score of the Bowling game
	 *
	 * @return the total score
	 * @throws BowlingLogicException
	 *             if the rules of Bowling are not followed
	 */
	public int getScore() throws BowlingLogicException;

	/**
	 * Add a new Frame in the game
	 *
	 * @param frame
	 * @throws BowlingLogicException
	 *             if the rules of Bowling are not followed
	 */
	public void addFrame(IFrame frame) throws BowlingLogicException;

	/**
	 * Add a new Frame in the game
	 *
	 * @param pinfalls
	 * @throws BowlingLogicException
	 *             if the rules of Bowling are not followed
	 */
	public void addFrame(int... pinfalls) throws BowlingLogicException;

	/**
	 * Add a new pinfall in the game, that will automatically added to the
	 * current/next Frame
	 *
	 * @param pinfall
	 * @throws BowlingLogicException
	 *             if the rules of Bowling are not followed
	 */
	public void addPinfall(int pinfall) throws BowlingLogicException;

	/**
	 * Add new pinfalls in the game, that will automatically added to the
	 * current/next Frame
	 *
	 * @param pinfall
	 * @throws BowlingLogicException
	 *             if the rules of Bowling are not followed
	 */
	public void addPinfalls(int... pinfalls) throws BowlingLogicException;

}
