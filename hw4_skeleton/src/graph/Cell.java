package graph;

import java.awt.Color;
import java.awt.Polygon;
import java.util.Random;

import main.Config;

import clock.Updatable;
import hw4.CellUtil;
import state.State;

/**
 * Holds relationships with other cells to create an arbitrary graph.
 * 
 * @author Brian Nakayama
 * 
 */
public class Cell implements Updatable {

	/**
	 * The maximum distance that we will allow path finding for.
	 */
	private int maxMouseDistance = Config.MAX_MOUSE_DISTANCE;

	/**
	 * The background color to render.
	 */
	private Color color;

	/**
	 * The polygon for rendering.
	 */
	private Polygon polygon;

	/**
	 * The current state to notify every update.
	 * @see #update()
	 */
	private State state;

	/**
	 * The adjacent cells in the graph.
	 */
	private Cell[] neighbors;

	/**
	 * The cell's inverted distance from the mouse. The closer the cell is, the
	 * higher this number should be.
	 */
	private int mouseDistance = 0;

	/**
	 * Keeps track of if we have processed an odd number of frames.
	 */
	private boolean parity = false;

	/**
	 * Keeps track of the next parity.
	 */
	private boolean nextParity = false;

	/**
	 * The random number generator used for getting neighbors.
	 */
	private static Random random = Config.RANDOM;

	/**
	 * Create a cell with a background color and a polygon (in the correct x-y
	 * coordinates).
	 * 
	 * @param color
	 *            A background color for rendering
	 * @param polygon
	 *            A polygon for rendering
	 */
	public Cell(Color color, Polygon polygon) {
		this.color = color;
		this.polygon = polygon;
	}

	/**
	 * Set the adjacent cells.
	 * 
	 * @param neighbors
	 *            Adjacent cells
	 */
	public void setNeighbors(Cell[] neighbors) {
		this.neighbors = neighbors;
	}

	/**
	 * Returns the adjacent cells.
	 * @return
	 *   array of adjacent cells to this one
	 */
	public Cell[] getNeighbors()
	{
	  return neighbors;
	}

	/**
	 * Sets the mouse distance for this cell.  
	 * @param distance
	 *   mouse distance for this cell
	 */
	public void setMouseDistance(int distance)
	{
	  mouseDistance = distance;
	}

	/**
	 * Returns the mouse distance for this cell.
	 * @return
	 *   mouse distance for this cell
	 */
	public int getMouseDistance(){
		return mouseDistance;
	}

	/**
	 * Updates the mouse distance for cells near this one, assuming that
	 * the mouse is on this cell and using MAX_MOUSE_DISTANCE for this cell. 
	 * For example, if one could get to a neighboring cell from this one by 
	 * moving ten cells, then that cell's mouse distance will be set to
	 * to MAX_MOUSE_DISTANCE minus ten.  Cells more than MAX_MOUSE_DISTANCE
	 * from this one will not be affected (will retain the default mouse distance
	 * of 0).  Cells with an impassable state always have a mouse distance of 0.
	 */
	public void recalculateMouseDistances() {
	  CellUtil.calculateMouseDistance(this, maxMouseDistance);
	}

	/**
	 * Gets a random cell closer to the mouse. The cell can be open or it can
	 * have a passable state.
	 * 
	 * @return An open or passable cell closer to the mouse, or null if none can
	 *         be found.
	 */
	public Cell getRandomCloser() {
		double highestProb = 0.5;
		int highestDistance = 1;
		Cell highCell = null;
		for (Cell neighbor : neighbors) {
			if (neighbor.state == null || neighbor.state.isPassable()) {
				if (neighbor.mouseDistance > highestDistance) {
					double chance = random.nextDouble();
					highestDistance = neighbor.mouseDistance;
					highCell = neighbor;
					highestProb = chance;
				} else if (neighbor.mouseDistance == highestDistance) {
					double chance = random.nextDouble();
					if (chance > highestProb) {
						highCell = neighbor;
						highestProb = chance;
					}
				}
			}
		}
		return highCell;
	}

	/**
	 * Get an open adjacent state to the current cell, selecting from each cell
	 * with uniform probability. If the cell calling this method has an
	 * impassable state, it will extend its search to passable states.
	 * 
	 * @return An adjacent open cell or null if one cannot be found.
	 */
	public Cell getRandomOpen() {
		double highest = 0;
		Cell highCell = null;
		for (Cell neighbor : neighbors) {
			if (neighbor.getState() == null || !state.isPassable()
					&& neighbor.getState().isPassable()) {
				double chance = random.nextDouble();
				if (chance > highest) {
					highCell = neighbor;
					highest = chance;
				}
			}
		}
		return highCell;
	}

	/**
	 * Resets the distance from the mouse for path finding.
	 */
	public void clearDistance() {
		mouseDistance = 0;
	}

	/**
	 * Get the background color of this cell.
	 * 
	 * @return background color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Get the polygon representing the area this cell occupies.
	 * 
	 * @return the space occupied
	 */
	public Polygon getPolygon() {
		return polygon;
	}

	/**
	 * Sets the current state of this cell.  NOTE: when an existing state
	 * is being moved from one cell to an adjacent cell, use 
	 * the method <code>moveState</code>.
	 * 
	 * @param state
	 *            the state of this cell
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * Moves the state onto the given cell, prevents the cell from updating the
	 * state again in the same frame, and sets the current State to null.
	 * 
	 * @param nextCell
	 *            the cell to move the state to.
	 */
	public void moveState(Cell nextCell) {
		nextCell.state = this.state;
		this.state = null;
		// Prevent the next cell from updating in the same cycle.
		nextCell.nextParity = parity;
	}

	/**
	 * Gets the current state.
	 * 
	 * @return the current state
	 */
	public State getState() {
		return state;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see clock.Updatable#update()
	 */
	@Override
	public void update() {
		if (parity == nextParity) {
			parity = !parity;
			nextParity = !nextParity;
			if (state != null) {
				state.handle(this);
			}
		} else {
			// This state was updated recently.
			parity = nextParity;
		}
	}
	
	
}
