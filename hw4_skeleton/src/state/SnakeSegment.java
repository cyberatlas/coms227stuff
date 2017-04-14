package state;

import graph.Cell;

import java.awt.Color;

import main.Config;

/**
 * A state that stays in a cell until the snake has traveled a number of cells
 * equal to its length away.
 * 
 * @author Brian Nakayama
 * @see hw4.SnakeHead
 */
public class SnakeSegment implements State {

	/**
	 * The head that this segment "follows".
	 */
	private Snake snakeHead;
	/**
	 * The number for frames this state has persisted.
	 */
	private int timer = 0;

	/**
	 * Creates a segment that looks like it's following a snake head.
	 * 
	 * @param snakeHead
	 *            The head of the snake
	 */
	public SnakeSegment(Snake snakeHead) {
		this.snakeHead = snakeHead;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see state.State#handle(graph.Cell)
	 */
	@Override
	public void handle(Cell cell) {
		timer += 1;
		if (snakeHead.getLength() * Config.MAX_SNAKE_TIMER - timer <= 0) {
			cell.setState(null);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see state.State#getColor()
	 */
	@Override
	public Color getColor() {
		int timeLeft = snakeHead.getLength() * Config.MAX_SNAKE_TIMER - timer;
		if (timeLeft >= Config.MAX_SNAKE_TIMER) {
			return Config.SNAKE_COLORS[Config.MAX_SNAKE_TIMER - 1];
		} else if (timeLeft < 0) {
			timer = snakeHead.getLength() * Config.MAX_SNAKE_TIMER;
		}

		return Config.SNAKE_COLORS[timeLeft];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see state.State#isPassable()
	 */
	@Override
	public boolean isPassable() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see state.State#toChar()
	 */
	@Override
	public char toChar() {
		return 0;
	}

}
