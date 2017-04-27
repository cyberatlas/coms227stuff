package hw4;

import graph.Cell;
import graph.GraphMap;

import java.awt.*;
import java.util.ArrayList;

/**
 * Creates a playing map using squares. Subclass of GraphMap
 *
 * @author Alex
 */
public class SquareMap extends GraphMap {

	public SquareMap() {
	}

	/**
	 * Calculates the width of map
	 *
	 * @return the width in pixels of the map
	 */
	@Override
	public int getPixelWidth() {
		// multiplies the number of columns by pixel length plus 1 pixel to
		// account for the border
		return getCells()[0].length * getDistance() + getDistance();
	}

	/**
	 * Calculates the height of map
	 *
	 * @return the height in pixels of the map
	 */
	@Override
	// multiplies the nubmer of rows by the pixel length +1 pixel to account for
	// the border
	public int getPixelHeight() {
		return getCells().length * getDistance() + getDistance();
	}

	/**
	 * Creates an array with the cells around the given index
	 * 
	 * @param col
	 *            The column index of a Cell
	 * @param row
	 *            The row index of a Cell
	 * @return array of the cells above, below, to the left of, and to the right
	 *         of the cell in the given position
	 */
	@Override
	public Cell[] createNeighbors(int col, int row) {
		// Creates an ArrayList of Cells that will hold the cell objects
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		// Checks that it has cells above it, if it does add the Cell above it
		// to neighbors
		if (row > 0) {
			neighbors.add(getCells()[row - 1][col]);
		}
		// If the row is less than the number of rows (minus 1 to account for
		// the 0 index), then get the value from the Cell from the next row
		if (row < getCells().length - 1) {
			neighbors.add(getCells()[row + 1][col]);
		}
		// If there are cells to the left of it, add the cell to the left to
		// neighbors
		if (col > 0) {
			neighbors.add(getCells()[row][col - 1]);
		}
		// if there are cells to the right of it add the cell to the right
		if (col < getCells()[0].length - 1) {
			neighbors.add(getCells()[row][col + 1]);
		}

		// return the neighbors arrayList to a Cell array with the same number
		// of items as in the arraylist
		return neighbors.toArray(new Cell[neighbors.size()]);
	}

	/**
	 * Find the closest Point to the given coordinantes
	 * 
	 * @param x
	 *            The x coordinate in pixels
	 * @param y
	 *            The y coordinate in pixels
	 * @return the closest coordinate to the specified in
	 */
	@Override
	protected Point selectClosestIndex(int x, int y) {
		// subtract the white space and divide by the pixel distance
		int xIndex = (x - getDistance() / 2) / getDistance();
		if (xIndex > getCells().length - 1) {
			xIndex = getCells().length - 1;
		}
		int yIndex = (y - getDistance() / 2) / getDistance();
		if (yIndex > getCells()[0].length - 1) {
			yIndex = getCells()[0].length - 1;
		}

		return new Point(xIndex, yIndex);
	}

	/**
	 * Creates square given the top left vertex of the square
	 *
	 * @param col
	 *            The column index of a Cell
	 * @param row
	 *            The row index of a Cell
	 * @return a new Polygon with the arguments of the x and y vertecies and the
	 *         number of verticies
	 */
	@Override
	public Polygon createPolygon(int col, int row) {
		// create new arrays to hold the points
		int[] xCoor = new int[4];
		int[] yCoor = new int[4];
		// loop through the arrays and assign values that would create a square
		for (int i = 0; i < 4; i++) {
			int coor = col * getDistance() + getDistance() / 2;
			if (i == 0 || i == 3) {
				xCoor[i] = coor;
			} else if (i == 1) {
				xCoor[i] = coor + getDistance();
			} else if (i == 2) {
				xCoor[i] = coor + getDistance();
			}
		}
		for (int i = 0; i < 4; i++) {
			int coor = row * getDistance() + getDistance() / 2;
			if (i == 0 || i == 1) {
				yCoor[i] = coor;
			}
			if (i == 2) {
				yCoor[i] = coor + getDistance();
			}
			if (i == 3) {
				yCoor[i] = coor + getDistance();
			}
		}
		// we return a new Polygon based on the arrays of coordinates generated,
		// there are 4 endpoints because a square only has 4 vertecies
		return new Polygon(xCoor, yCoor, 4);
	}
}
