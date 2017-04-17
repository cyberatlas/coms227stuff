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
		return 0;
	}

	@Override
	public int getPixelHeight() {
		return 0;
	}

	@Override
	public Cell[] createNeighbors(int col, int row) {
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
