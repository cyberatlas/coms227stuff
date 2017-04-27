package hw4;

import graph.Cell;
import state.State;

import static main.Config.MAX_FOOD_TIMER;

import java.awt.*;

/**
 * Subclass of food that moves around
 *
 * @author Alex Stevenson
 */
public class DungeonessCrab extends Food {
	/**
	 * It reads in the method that food uses for handle and adds movement If the
	 * food counter is 0 then it moves to an open cell
	 *
	 * @param cell
	 *            the cell that this object belongs to
	 */
	@Override
	public void handle(Cell cell) {
		super.handle(cell);
		if (counter == 0) {
			Cell openCell = cell.getRandomOpen();
			if (openCell != null) {
				cell.moveState(openCell);
			}
		}
	}

	/**
	 * Returns the character representation fo a dungeoness crab
	 *
	 * @return D
	 */
	public char toChar() {
		return 'D';
	}

}
