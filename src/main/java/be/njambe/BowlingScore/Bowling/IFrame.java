package be.njambe.BowlingScore.Bowling;

import be.njambe.BowlingScore.Exception.BowlingLogicException;

/**
 * Interface for a Bowling frame
 *
 * @author Nathan Jambe
 *
 */
public interface IFrame {

	/**
	 * Check if the Frame is a strike (10 pins felt on the first launch)
	 *
	 * @return true if the frame is a strike
	 */
	public boolean isStrike();

	/**
	 * Check if the Frame is a spare (10 pins felt on the second launch)
	 *
	 * @return true if the frame is a spare
	 */
	public boolean isSpare();

	/**
	 * Check if the Frame is complete (strike, both balls launched, ...)
	 *
	 * @return true if the frame is complete
	 */
	public boolean isComplete();

	/**
	 * Check if the Frame is the tenth of the game (the last one)
	 *
	 * @return true if the frame is the last of the game
	 */
	public boolean isLastFrameOfGame();

	/**
	 * Set the pinfalls of the frame. Between 1 and 3 are allowed depending of
	 * the bowling rules
	 *
	 * @param pinfalls
	 *            the number of pin felt, one value per ball launched
	 * @throws BowlingLogicException
	 *             if the rules of Bowling are not followed
	 */
	public void setPinfalls(int... pinfalls) throws BowlingLogicException;

	/**
	 * Get the sum of pins felt just for this Frame
	 *
	 * @return the absolute score
	 */
	public int getAbsoluteScore();

	/**
	 * Get the number of pins felt on the first ball launched
	 *
	 * @return the number of pins felt
	 */
	public int getFirstPinfall();

	/**
	 * Get the number of pins felt on the second ball launched
	 *
	 * @return the number of pins felt
	 */
	public int getSecondPinfall();

	/**
	 * Validate that the rules of Bowling are followed in this Frame
	 *
	 * @throws BowlingLogicException
	 *             if the rules of Bowling are not followed
	 */
	public void validate() throws BowlingLogicException;

}
