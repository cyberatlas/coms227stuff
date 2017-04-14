package state;

/**
 * The super type for a snake object that holds the player's score.
 * @author Brian Nakayama
 *
 */
public interface Snake {
	/**
	 * The current length in cells of the snake.
	 * @return The length in cells
	 */
	public int getLength();
}
