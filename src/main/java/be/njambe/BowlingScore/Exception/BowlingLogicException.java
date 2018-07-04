package be.njambe.BowlingScore.Exception;

/**
 * Custom Exception, raised when the Bowling rules are not followed
 *
 * @author Nathan Jambe
 *
 */
public class BowlingLogicException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = -3932249808740090190L;

	public BowlingLogicException(String message) {
		super(message);
	}

}
