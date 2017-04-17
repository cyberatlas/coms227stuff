package hw4;

import graph.Cell;
import state.State;

import java.awt.*;

/**
 * Created by ruski on 4/16/2017.
 */
public class Food implements State {
	public Food(){}
	@Override
	public void handle(Cell cell) {

	}

	@Override
	public Color getColor() {
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

	@Override
	public char toChar() {
		return 0;
	}
}
