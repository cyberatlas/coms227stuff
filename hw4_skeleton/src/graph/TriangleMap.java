package graph;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

/**
 * A square lattice where each cell has 3 neighbors set up as a tessellation.
 * @author Brian Nakayama
 *
 */
public class TriangleMap extends GraphMap{

	/**
	 * The x component of the distance between cells
	 */
	private double x_diff = (Math.cos(Math.PI / 6) * getDistance());
	/**
	 * The y component of the distance between cells
	 */
	private int y_diff = getDistance() /2;

	@Override
	public void setDistance(int distance) {
		super.setDistance(distance);
		x_diff = (Math.cos(Math.PI / 6) * distance);
		y_diff = distance /2;
	}
	
	@Override
	public int getPixelWidth(){
		return (int)(2*getDistance() - x_diff + x_diff * getCells()[0].length);
	}

	@Override
	public int getPixelHeight(){
		return (getDistance() + (getDistance() + y_diff) * getCells().length);	
	}
	
	@Override
	public Cell[] createNeighbors(int x, int y) {
		
		Cell[][] map = getCells();
		
		int yOffset = y - 1;
		if ((y + x) % 2 == 1) {
			yOffset = y + 1;
		}

		List<Cell> cells = new ArrayList<Cell>();
		if (x - 1 >= 0) {
			cells.add(map[y][x - 1]);
		}

		if (x + 1 < map[y].length) {
			cells.add(map[y][x + 1]);
		}

		if (yOffset >= 0 && yOffset < map.length) {
			cells.add(map[yOffset][x]);
		}
		return cells.toArray(new Cell[0]);
	}

	public Point selectClosestIndex(int x, int y) {
		x -= (getDistance()-x_diff/2);
		y -= getDistance()/2;
		x /= x_diff;
		y /= (getDistance() + y_diff);

		return new Point(x, y);
	}

	@Override
	public Polygon createPolygon(int x, int y) {
		int distance = getDistance();
		
		double xOffset = distance + x * x_diff;
		double yOffset = distance + y * (distance + y_diff);
		double yOffset0 = -distance/2;
		double yOffset1 = distance/2 + y_diff;
		if ((x + y) % 2 == 1) {
			yOffset0 = distance/2 + y_diff;
			yOffset1 = -distance/2;
		}
		int[] xCoor = { (int) (xOffset - x_diff), (int) xOffset,
				(int) (xOffset + x_diff) };
		int[] yCoor = { (int) (yOffset + yOffset0), (int) (yOffset + yOffset1),
				(int) (yOffset + yOffset0) };
		return new Polygon(xCoor, yCoor, 3);
	}

}