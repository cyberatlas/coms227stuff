package hw4;

import graph.Cell;
import state.State;
import static main.Config.MAX_FOOD_TIMER;

import java.awt.*;

/**
 * Created by Ruski on 4/14/2017.
 */
public class DungeonessCrab extends Food{

	public void handle(Cell cell){
		super.handle(cell);
		if (counter == 0){
			Cell openCell = cell.getRandomOpen();
			if (openCell != null) {
				cell.moveState(openCell);
			}
		}
	}
	public char toChar(){
		return 'D';
	}

}
