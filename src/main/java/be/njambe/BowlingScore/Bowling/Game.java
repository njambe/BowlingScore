package be.njambe.BowlingScore.Bowling;

import java.util.ArrayList;

import be.njambe.BowlingScore.Exception.BowlingLogicException;

public class Game implements IGame {

	private ArrayList<IFrame> frames;
	private ArrayList<Integer> bufferPinfalls;

	public Game() {
		frames = new ArrayList<IFrame>(10);
		bufferPinfalls = new ArrayList<Integer>(3);
	}

	public int getScore() throws BowlingLogicException {
		if (bufferPinfalls.size() > 0)
			flushBuffer();

		int score = 0;

		int iFrames = getFrames().size();
		for (int i = 0; i < iFrames; i++) {
			IFrame current = getFrames().get(i);
			IFrame plus1 = (i + 1 >= iFrames) ? new Frame(0) : getFrames().get(i + 1);
			IFrame plus2 = (i + 2 >= iFrames) ? new Frame(0) : getFrames().get(i + 2);

			score += current.getAbsoluteScore();

			if (current.isSpare() || current.isStrike())
				score += plus1.getFirstPinfall();

			if (current.isStrike())
				score += (plus1.isStrike() && !plus1.isLastFrameOfGame()) ? plus2.getFirstPinfall()
						: plus1.getSecondPinfall();
		}

		return score;
	}

	public ArrayList<IFrame> getFrames() {
		return frames;
	}

	public void addFrame(IFrame frame) throws BowlingLogicException {
		if (frames.size() < 10)
			frames.add(frame);
		else
			throw new BowlingLogicException("No more than 10 frames per game are allowed");
	}

	public void addFrame(int... pinfalls) throws IllegalArgumentException, BowlingLogicException {
		addFrame(new Frame((frames.size() == 9), pinfalls));
	}

	public void addPinfall(int pinfall) throws IllegalArgumentException, BowlingLogicException {
		// Add in buffer
		bufferPinfalls.add(pinfall);

		// If buffer full or needed to be flushed
		int bufMaxSize = (frames.size() == 9 && (bufferPinfalls.stream().mapToInt(i -> i.intValue())).sum() >= 10) ? 3
				: 2;
		if (bufferPinfalls.size() == bufMaxSize || (frames.size() < 9 && pinfall == 10)) {
			// Flush
			flushBuffer();
		}
	}

	public void addPinfalls(int... pinfalls) throws IllegalArgumentException, BowlingLogicException {
		for (int pinfall : pinfalls) {
			addPinfall(pinfall);
		}
	}

	private void flushBuffer() throws BowlingLogicException {
		int flush[] = new int[bufferPinfalls.size()];
		for (int i = 0; i < bufferPinfalls.size(); i++)
			flush[i] = bufferPinfalls.get(i);

		addFrame(flush);
		bufferPinfalls.clear();
	}

	@Override
	public String toString() {
		return "Game [frames=" + frames + "]";
	}
}
