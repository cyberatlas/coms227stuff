package hw4;

import graph.Cell;
import state.State;

import java.awt.*;

import static main.Config.MAX_FOOD_TIMER;

/**
 * Created by ruski on 4/16/2017.
 */
public class Food implements State {
	private int counter =0;

	public Food(){}

	@Override
	public void handle(Cell cell) {
		counter= (MAX_FOOD_TIMER== counter)? 0: counter +1;
	}

	@Override
	public Color getColor() {
		//TODO needs to return food color which is implemented in config

		return null;
	}

	/**
	 * Checks to see if the snake can go through the cell
	 * @return true because "snakes can mow through food"
	 */
	@Override
	public boolean isPassable() {
		return true;
	}

	/**
	 * Returns the character representation of food
	 * @return 'F'
	 */
	@Override
	public char toChar() {
		return 'F';
	}
}
