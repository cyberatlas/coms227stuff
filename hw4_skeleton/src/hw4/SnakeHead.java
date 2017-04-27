package hw4;

import graph.Cell;
import state.Snake;
import state.SnakeSegment;
import state.State;

import java.awt.*;

import static main.Config.MAX_SNAKE_TIMER;
import static main.Config.endGame;

/**
 * The Logic of the snake
 * 
 * @author Alex
 *
 */
public class SnakeHead implements State, Snake {

	private int length = 4;
	private int timer = 0;

	/**
	 * Returns the length of the snake
	 * 
	 * @return The length instance variable
	 */
	@Override
	public int getLength() {
		return length;
	}

	/**
	 * Checks to see when the snake can move and modifies the state of the snake
	 */
	@Override
	public void handle(Cell cell) {

		timer++;
		if (timer >= MAX_SNAKE_TIMER)
			timer = 0;
		if (timer == 0) {
			Cell c = cell.getRandomCloser();
			if (c == null) {
				c = cell.getRandomOpen();
				// if the random cell that it chose is null (empty) end the game
				if (c == null)
					endGame(length);
			}
			// If the snake moved to a cell that is food, then the length
			// increases by 1
			if (c.getState() instanceof Food) {
				length++;
			}
			cell.moveState(c);
			cell.setState(new SnakeSegment(this));
		}
	}

	/**
	 * Returns the color of the snakehead
	 *
	 * @return the color object with the random color I made up
	 */
	@Override
	public Color getColor() {
		Color snakeHeadColor = new Color(.42f, .42f, .42f);
		return snakeHeadColor;
	}

	/**
	 * Checks to if the snakehead can pass through itself
	 *
	 * @return false
	 */
	@Override
	public boolean isPassable() {
		return false;
	}

	/**
	 * Return the character representation of a snakehead
	 *
	 * @return 'S'
	 */
	@Override
	public char toChar() {
		return 'S';
	}
}
