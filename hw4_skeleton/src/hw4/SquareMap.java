package hw4;

import graph.Cell;
import graph.GraphMap;

import java.awt.*;

/**
 * Created by ruski on 4/16/2017.
 */
public class SquareMap extends GraphMap {


	public SquareMap(){}

	@Override
	public int getPixelWidth() {
		return getCells()[0].length *getDistance();
	}

	@Override
	public int getPixelHeight() {
		return getCells().length * getDistance();
	}

	@Override
	public Cell[] createNeighbors(int col, int row) {
		//create arr of cells
		//populate with adjacent cells
		//watch out for map edges
		return new Cell[0];
	}

	@Override
	protected Point selectClosestIndex(int x, int y) {
		return null;
	}

	@Override
	public Polygon createPolygon(int col, int row) {
		return null;
	}
}
