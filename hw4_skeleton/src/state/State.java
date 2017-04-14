package state;

import graph.Cell;

import java.awt.Color;

/**
 * A State Pattern for the snake game.
 * 
 * @author Brian Nakayama
 * @see graph.Cell
 */
public interface State {
	/**
	 * Updates the cell based off of the state. This method can update the
	 * cell's state, or potentially another cell's state depending on the
	 * implementation.
	 * 
	 * @param cell
	 *            The cell that this state belongs to
	 */
	public void handle(Cell cell);

	/**
	 * Get the current color of the state (can be used for drawing).
	 * 
	 * @return The color of the state
	 */
	public Color getColor();

	/**
	 * Get whether or not the cell is passable. Affects whether or not a state
	 * can move through another state via random movement or moving closer to
	 * the mouse.
	 * 
	 * @return true iff the state is passable
	 * @see graph.Cell#getRandomOpen()
	 * @see graph.Cell#getRandomCloser()
	 */
	public boolean isPassable();

	/**
	 * Get the character representation for this State. Used for loading map
	 * text files.
	 * 
	 * @return
	 *   character representation for this State
	 */
	public char toChar();
}
