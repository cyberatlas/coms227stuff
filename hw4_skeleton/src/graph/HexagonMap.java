package graph;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

/**
 * A hexagonal lattice where each cell has 6 neighbors set up in a honeycone pattern.
 * @author Brian Nakayama
 *
 */
public class HexagonMap extends GraphMap {

	/**
	 * The x component of the distance between cells.
	 */
	private double x_diff = (Math.cos(Math.PI / 6) * getDistance());

	/*
	 * (non-Javadoc)
	 * @see graph.GraphMap#setDistance(int)
	 */
	@Override
	public void setDistance(int distance) {
		super.setDistance(distance);
		x_diff = (Math.cos(Math.PI / 6) * distance);
	}
	
	/*
	 * (non-Javadoc)
	 * @see graph.GraphMap#getPixelWidth()
	 */
	@Override
	public int getPixelWidth() {
		return (int)((this.getCells()[0].length - 1) * x_diff) + 2*getDistance();
	}

	/*
	 * (non-Javadoc)
	 * @see graph.GraphMap#getPixelHeight()
	 */
	@Override
	public int getPixelHeight() {
		return (this.getCells().length+1) * getDistance() + getDistance()/2;
	}

	/*
	 * (non-Javadoc)
	 * @see graph.GraphMap#createNeighbors(int, int)
	 */
	@Override
	public Cell[] createNeighbors(int x, int y) {
		
		Cell[][] map = getCells();
		
		int[][] relative = {{-1, -1},{0, -1},{-1, 1},{0, 1},{-1,0},{1,0}};
		if (x % 2 == 1) {
			for(int i = 0; i < 4; i++){
				relative[i][0] += 1;
			}
		}

		List<Cell> cells = new ArrayList<Cell>();
		for(int i = 0; i < relative.length; i++){
			int yCoor = y + relative[i][0];
			int xCoor = x + relative[i][1];
			if (yCoor >= 0 && yCoor < map.length){
				if (xCoor >= 0 && xCoor < map[yCoor].length){
					cells.add(map[yCoor][xCoor]);
				}
			}
		}
		
		return cells.toArray(new Cell[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see graph.GraphMap#selectClosestPoint(int, int)
	 */
	@Override
	protected Point selectClosestIndex(int x, int y) {
		x -= (getDistance() - 2*x_diff/3);
		x /= x_diff;
		if (x%2 == 0){
			y -= getDistance()/2;
		} else {
			y -= getDistance();
		}
		y /= getDistance();
		
		return new Point(x, y);
	}

	/*
	 * (non-Javadoc)
	 * @see graph.GraphMap#createPolygon(int, int)
	 */
	@Override
	public Polygon createPolygon(int x, int y) {
		int yOffset0 = getDistance() / 2;
		if (x % 2 == 1) {
			yOffset0 += getDistance() / 2;
		}
		yOffset0 += y * getDistance();
		int yOffset1 = yOffset0 + getDistance() / 2;
		int yOffset2 = yOffset0 + getDistance();

		double xOffset0 = getDistance() - 2 * x_diff / 3;
		xOffset0 += x * x_diff;

		double xOffset1 = xOffset0 + x_diff / 3;
		double xOffset2 = xOffset0 + x_diff;
		double xOffset3 = xOffset2 + x_diff / 3;

		int[] yCoor = { yOffset1, yOffset0, yOffset0, yOffset1, yOffset2,
				yOffset2 };
		int[] xCoor = { (int) xOffset0, (int) xOffset1, (int) xOffset2,
				(int) xOffset3, (int) xOffset2, (int) xOffset1 };

		return new Polygon(xCoor, yCoor, 6);
	}

}
