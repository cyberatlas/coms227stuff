package hw4;

import graph.Cell;
import state.State;

import java.awt.*;

import static main.Config.FOOD_COLORS;
import static main.Config.MAX_FOOD_TIMER;

/**
 * @author Alex Food superclass. If snake eats it, the snake gets bigger
 */
public class Food implements State {
	/**
	 * Counter that food and food objects use
	 */
	private int timer = 0;

	protected int getTimer(){return timer;} 
	public Food() {
	}

	/**
	 * If the counter equals MAX_FOOD_TIMER, reset the counter
	 *
	 * @param cell
	 *            the cell that the object is at
	 */
	@Override
	public void handle(Cell cell) {
		if (++timer == MAX_FOOD_TIMER)
			timer = 0;
	}

	/**
	 * Generates the color of the food based on the current counter value
	 *
	 * @return the value of the color at Food colors at index counter
	 */
	@Override
	public Color getColor() {
		return FOOD_COLORS[timer];
	}

	/**
	 * Checks to see if the snake can go through the cell
	 *
	 * @return true because "snakes can mow through food"
	 */
	@Override
	public boolean isPassable() {
		return true;
	}

	/**
	 * Returns the character representation of food
	 *
	 * @return 'F'
	 */
	@Override
	public char toChar() {
		return 'F';
	}
}
