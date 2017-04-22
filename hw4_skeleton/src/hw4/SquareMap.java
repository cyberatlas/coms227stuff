package hw4;

import graph.Cell;
import graph.GraphMap;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by ruski on 4/16/2017.
 */
public class SquareMap extends GraphMap {


	public SquareMap(){}

	@Override
	public int getPixelWidth() {
		//multiplies the number of values by pixel length
				return getCells()[0].length *getDistance();
	}

	@Override
	public int getPixelHeight() {
		return getCells().length * getDistance();
	}
	//create arr of cells
	//populate with adjacent cells
	//watch out for map edges

	@Override
	public Cell[] createNeighbors(int col, int row) {
		//Creates an ArrayList of Cells that will hold the cell objects
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		//Checks that it has cells above it, if it does add the Cell above it to neighbors
		if (row>0){
			neighbors.add(getCells()[row-1][col]);
		}
		//If the row is less than the number of rows (minus 1 to account for the 0 index), then get the value from the Cell from the next row
		if (row< getCells()[0].length-1){
			neighbors.add(getCells()[row+1][col]);
		}
		//If there are cells to the left of it, add the cell to the left to neighbors
		if (col>0){
			neighbors.add(getCells()[row][col-1]);
		}
		//if there are cells to the right of it add the cell to the right
		if (col<getCells().length-1){
			neighbors.add(getCells()[row][col+1]);
		}
		
		//return the neighbors arrayList to a Cell array with the same number of items as in the arraylist
		return neighbors.toArray(new Cell[neighbors.size()]);
	}

	@Override
	protected Point selectClosestIndex(int x, int y) {
		//TODO
		//dont think that works??
		int xIndex = x/getDistance();
		int yIndex = y/getDistance();
		return new Point(xIndex, yIndex);
	}

	@Override
	public Polygon createPolygon(int col, int row) {
		//TODO
		//value +/- distance
		//create new arrays to hold the points
		int[] xCoor  = {(int) getDistance()+ col, (int) getDistance() -col };
		int[] yCoor = {(int) getDistance()+row, (int) getDistance()-row};
		return new Polygon(xCoor, yCoor, 2);
	}
}
