package hw4;

import graph.Cell;
import state.State;

import java.awt.*;

/**
 * Created by Ruski on 4/14/2017.
 */
public class DungeonessCrab extends Food{

	public void handle(Cell cell){
		if (counter == 0){
			cell.moveState(cell.getRandomOpen());}
	}
	public char toChar(){
		return 'D';
	}

}
