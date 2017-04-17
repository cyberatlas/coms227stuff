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

	@Override
	public Color getColor() {
		return null;
	}

	@Override
	public boolean isPassable() {
		return false;
	}

	@Override
	public char toChar() {
		return 0;
	}
}
