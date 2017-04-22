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
		//TODO
		if (cell.getState()  instanceof SnakeHead){
			//+1 to snake length and make the Cell a snakehead
			length +=1;
			this.cell.setState(SnakeHead);
		}


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
