package be.njambe.BowlingScore.Bowling;

import java.util.Arrays;

import be.njambe.BowlingScore.Exception.BowlingLogicException;

/**
 * This class represents a Bowling frame. A frame contains one, two or three
 * pinfalls (result of launching a ball down the lane). It can be a strike (10
 * pins felt on first launch), a spare (10 pins felt using 2 launches), or none.
 *
 * @author Nathan Jambe
 *
 */
public class Frame implements IFrame {

	private int pinfalls[] = { 0, 0, 0 };
	private int launches = 0;
	private boolean lastFrameOfGame = false;

	public Frame() {
	}

	public Frame(boolean lastFrameOfGame) {
		this.lastFrameOfGame = lastFrameOfGame;
	}

	public Frame(int... pinfalls) throws BowlingLogicException {
		setPinfalls(pinfalls);
	}

	public Frame(boolean lastFrameOfGame, int... pinfalls) throws BowlingLogicException {
		this.lastFrameOfGame = lastFrameOfGame;
		setPinfalls(pinfalls);
	}

	private boolean isStrike(int launches, int... pinfalls) {
		return launches >= 1 && pinfalls[0] == 10;
	}

	public boolean isStrike() {
		return isStrike(launches, pinfalls);
	}

	private boolean isSpare(int launches, int... pinfalls) {
		return launches >= 2 && (pinfalls[0] + pinfalls[1]) == 10;
	}

	public boolean isSpare() {
		return isSpare(launches, pinfalls);
	}

	public boolean isComplete() {
		if (isLastFrameOfGame()) {
			if (isStrike() || isSpare())
				return launches == 3;
			else
				return launches == 2;
		} else {
			if (isStrike())
				return true;
			else
				return launches == 2;
		}
	}

	public boolean isLastFrameOfGame() {
		return lastFrameOfGame;
	}

	public void setPinfalls(int... pinfalls) throws BowlingLogicException {
		this.validate(pinfalls.length, pinfalls);

		int i;
		for (i = 0; i < pinfalls.length; i++)
			this.pinfalls[i] = pinfalls[i];

		launches = i;
	}

	public int getAbsoluteScore() {
		return Arrays.stream(pinfalls).sum();
	}

	public int getFirstPinfall() {
		return pinfalls[0];
	}

	public int getSecondPinfall() {
		return pinfalls[1];
	}

	public void validate() throws BowlingLogicException {
		validate(launches, pinfalls);
	}

	private void validate(int launches, int... pinfalls) throws BowlingLogicException {
		// At least one pinfall value
		if (launches == 0) {
			throw new BowlingLogicException("At least one pitfall is required");
		}

		// Maximum 3 values on the last frame
		if (isLastFrameOfGame() && launches > 3) {
			throw new BowlingLogicException("Maximum 3 pinfalls within the tenth frame");
		}

		// Maximum 2 values on the 9 first frames
		if (!isLastFrameOfGame() && launches > 2) {
			throw new BowlingLogicException("Maximum 2 pinfalls within a frame");
		}

		// Value of pinfalls (must be 0-10)
		for (int pf : pinfalls)
			if (pf < 0 || pf > 10)
				throw new BowlingLogicException("Pinfalls must be between 0 and 10");

		// If only 1 launch, no other check required
		if (launches == 1)
			return;

		// If strike on the last frame, the next value can be anything
		if (isLastFrameOfGame() && launches == 2 && pinfalls[0] == 10)
			return;

		// If 2 strikes on the last frame, last value can be anything
		if (isLastFrameOfGame() && launches == 3 && pinfalls[0] == 10 && pinfalls[1] == 10)
			return;

		// If strike on the last frame, last 2 values must be below than 10
		if (isLastFrameOfGame() && launches == 3 && pinfalls[0] == 10 && pinfalls[1] + pinfalls[2] <= 10)
			return;

		// Strike ends the frame, when not on the last frame of the game
		if (!isLastFrameOfGame() && isStrike(pinfalls.length, pinfalls) && launches > 1)
			throw new BowlingLogicException("Strike ends the frame");

		// Maximum 10 pins per regular frame
		if (pinfalls.length >= 2 && pinfalls[0] + pinfalls[1] > 10)
			throw new BowlingLogicException("Not possible to fall more than 10 pins within a frame");

		// Third launch on tenth frame only possible when done strike or spare
		if (isLastFrameOfGame() && !isStrike(pinfalls.length, pinfalls) && !isSpare(pinfalls.length, pinfalls)
				&& launches == 3)
			throw new BowlingLogicException("Third launch only when possible");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (lastFrameOfGame ? 1231 : 1237);
		result = prime * result + launches;
		result = prime * result + Arrays.hashCode(pinfalls);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Frame other = (Frame) obj;
		if (lastFrameOfGame != other.lastFrameOfGame)
			return false;
		if (launches != other.launches)
			return false;
		if (!Arrays.equals(pinfalls, other.pinfalls))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Frame [pinfalls=" + Arrays.toString(pinfalls) + ", launches=" + launches + ", lastFrameOfGame="
				+ lastFrameOfGame + "]";
	}

}
