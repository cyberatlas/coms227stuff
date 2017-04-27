package hw4;

import graph.Cell;
import state.State;

import java.awt.*;

/**
 * Unmovable white wall
 * 
 * @author Alex
 */
public class Wall implements State {
	/**
	 * Walls don't do anything so the handle does nothing
	 */
	@Override
	public void handle(Cell cell) {
	}

	/**
	 * Returns the color white because walls are white
	 *
	 * @return color object which is white
	 */
	@Override
	public Color getColor() {
		// White is a combination of all colors so we use 100% of each
		Color wallColor = new Color(1f, 1f, 1f);
		return wallColor;
	}

	/**
	 * Boolean that checks if one can pass through a wall or not
	 *
	 * @return false because you cannot pass through a wall
	 */
	@Override
	public boolean isPassable() {
		return false;
	}

	/**
	 * Gets the character representation of the wall
	 *
	 * @return '#' which represents a wall
	 */
	@Override
	public char toChar() {
		return '#';
	}
}
