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
				return getCells()[0].length *getDistance() + getDistance();
	}

	@Override
	public int getPixelHeight() {
		return getCells().length * getDistance() + getDistance();
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
		if (row< getCells().length-1){
			neighbors.add(getCells()[row+1][col]);
		}
		//If there are cells to the left of it, add the cell to the left to neighbors
		if (col>0){
			neighbors.add(getCells()[row][col-1]);
		}
		//if there are cells to the right of it add the cell to the right
		if (col<getCells()[0].length-1){
			neighbors.add(getCells()[row][col+1]);
		}
		
		//return the neighbors arrayList to a Cell array with the same number of items as in the arraylist
		return neighbors.toArray(new Cell[neighbors.size()]);
	}

	@Override
	protected Point selectClosestIndex(int x, int y) {
		//TODO
		//subtract the white space and divide by the pixel distance
		
		int xIndex = (x-getDistance()/2)/getDistance();
		if(xIndex > getCells().length-1){xIndex=getCells().length-1;}
		int yIndex = (y-getDistance()/2)/getDistance();
		if(yIndex > getCells()[0].length-1){yIndex=getCells()[0].length-1;}

		return new Point(xIndex, yIndex);
	}

	@Override
	public Polygon createPolygon(int col, int row) {
		//TODO
		//value +/- distance
		//create new arrays to hold the points
		int[] xCoor = new int[4];
		int[]  yCoor = new int[4];
		for(int i=0; i<4; i++){
			int coor = col* getDistance() + getDistance()/2;
			if(i==0 || i == 3){
				xCoor[i] = coor;
				}
			else if (i==1){
				xCoor[i] = coor + getDistance();
			}
			else if (i==2){
				xCoor[i] = coor + getDistance();
			}
			}
		for(int i=0; i<4; i++){
			int coor = row* getDistance() + getDistance()/2;
			if(i==0 || i == 1){yCoor[i] = coor;}
			if (i==2){yCoor[i] = coor + getDistance();}
			if (i ==3){yCoor[i] = coor+getDistance();} 
			}
		
		/*int[] xCoor  = {
				(int) col * getDistance()+getDistance()/2,
				(int) (col+1)* getDistance() +getDistance()/2,
				(int) (col+1)*getDistance() +getDistance()/2,
				(int) (col+1)};
		int[] yCoor = {(int) getDistance()+row, (int) getDistance()-row};
		*/
		return new Polygon(xCoor, yCoor, 4);
	}
}
