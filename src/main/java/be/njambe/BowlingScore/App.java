package be.njambe.BowlingScore;

import be.njambe.BowlingScore.Bowling.Game;
import be.njambe.BowlingScore.Exception.BowlingLogicException;

public class App {
	public static void main(String[] args) {
		App app = new App();

		if (args.length == 0) {
			System.out.println("Please enter the pinfalls, separated by comma ','");
			System.exit(0);
		}

		// Concat the parameters to avoid the space issue
		String parameters = "";
		for (String p : args)
			parameters += p;

		try {
			System.out.println("Your score is: " + app.calculateScore(parameters));

		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			System.exit(1);

		} catch (BowlingLogicException e) {
			System.out.println(e.getMessage());
			System.exit(1);

		}
	}

	/**
	 * Calculate the score of a bowling game
	 *
	 * @param pinfallsString
	 *            The pinfalls, separated by commas
	 * @return The score of the game
	 * @throws NumberFormatException
	 * @throws BowlingLogicException
	 */
	public int calculateScore(String pinfallsString) throws NumberFormatException, BowlingLogicException {

		// Check if there is other characters than digits, spaces and commas
		if (!pinfallsString.matches("^[0-9,\\s]+$"))
			throw new NumberFormatException("Invalid character detected or format not respected");

		// Remove all spaces
		pinfallsString = pinfallsString.replaceAll("\\s", "");

		String pinfalls[] = pinfallsString.split(",");

		// Load the pinfalls
		Game game = new Game();
		for (String pinfall : pinfalls)
			game.addPinfall(Integer.parseInt(pinfall));

		// Get the score
		return game.getScore();
	}
}
