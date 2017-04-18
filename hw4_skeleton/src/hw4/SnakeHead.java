package hw4;

import graph.Cell;
import state.Snake;
import state.State;

import java.awt.*;

/**
 * Created by ruski on 4/16/2017.
 */
public class SnakeHead implements State, Snake {
	@Override
	public int getLength() {
		return 0;
	}

	@Override
	public void handle(Cell cell) {

	}


	/**
	 * Returns the color of the snakehead
	 * @return the color object with the random color I made up
	 */
	@Override
	public Color getColor() {
		Color snakeHeadColor = new Color(.42f,.42f,.42f);
		return snakeHeadColor;
	}

	/**
	 * Checks to if the snakehead can pass through itself
	 * @return false
	 */
	@Override
	public boolean isPassable() {
		return false;
	}

	/**
	 * Return the character representation of a snakehead
	 * @return 'S'
	 */
	@Override
	public char toChar() {
		return 'S';
	}
}
