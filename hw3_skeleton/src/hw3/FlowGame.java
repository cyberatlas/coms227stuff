package hw3;

import api.Cell;
import api.Flow;
import com.sun.xml.internal.bind.v2.TODO;

import static hw3.Util.createFlowsFromStringArray;

/**
 * Game state for a Flow Free game.
 */
public class FlowGame {

	private Flow[] flows;
	private int width;
	private int height;
	private Cell currentCell;
	private Flow currentFlow;
	/**
	 * Constructs a FlowGame to use the given array of Flows and
	 * the given width and height.  Client is responsible for ensuring that all
	 * cells in the given flows have row and column values within
	 * the given width and height.
	 *
	 * @param givenFlows  an array of Flow objects
	 * @param givenWidth  width to use for the game
	 * @param givenHeight height to use for the game
	 */
	public FlowGame(Flow[] givenFlows, int givenWidth, int givenHeight) {
		flows = givenFlows;
		width = givenWidth;
		height = givenHeight;
		currentCell = null;
		currentFlow = null;
	}

	/**
	 * Constructs a FlowGame from the given descriptor.
	 *
	 * @param descriptor array of strings representing initial endpoint positions
	 */
	public FlowGame(String[] descriptor) {
		// TODO
		flows = Util.createFlowsFromStringArray(descriptor);
		width = descriptor[0].length();
		height = descriptor.length;


		currentCell = null;
		currentFlow = null;
	}

	/**
	 * Returns the width for this game.
	 *
	 * @return width for this game
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the height for this game.
	 *
	 * @return height for this game
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns the current cell for this game, possible null.
	 *
	 * @return current cell for this game
	 */
	public Cell getCurrent() {
		return currentCell;
	}

	/**
	 * Returns all flows for this game.  Client should not modify
	 * the returned array or lists.
	 *
	 * @return array of flows for this game
	 */
	public Flow[] getAllFlows() {
		return flows;
	}

	/**
	 * Returns the number of occupied cells in all flows (including endpoints).
	 *
	 * @return occupied cells in this game
	 */
	public int getCount() {
		// TODO
		return 0;
	}

	/**
	 * Returns true if all flows are complete and all cells are occupied.
	 *
	 * @return true if all flows are complete and all cells are occupied
	 */
	public boolean isComplete() {
		// TODO
		return false;
	}

	/**
	 * Attempts to set the "current" cell to be an existing cell at the given
	 * row and column.  When using a GUI, this method is typically
	 * invoked when the mouse is pressed.
	 * <ul>
	 * <li>Any endpoint can be selected as the current cell.  Selecting an
	 * endpoint clears the flow associated with that endpoint.
	 * <li>A non-endpoint cell can be selected as the current cell only if
	 * it is the last cell in a flow.
	 * </ul>
	 * If neither of the above conditions is met, this method does nothing.
	 *
	 * @param row given row
	 * @param col given column
	 */
	public void startFlow(int row, int col) {
		for(Flow f: flows)
		{
			for(int i=0;i<=1;i++)
			{
				if (f.getEndpoint(i).getRow() == row && f.getEndpoint(i).getCol() == col)
				{
					currentCell = f.getEndpoint(i);
					currentFlow = f;
					currentFlow.clear();
					currentFlow.add(currentCell);//adds the current the cell when you start the flow
				}
			}
		}
	}

	/**
	 * Clears the "current" cell. That is, directly after invoking this method,
	 * <code>getCurrent</code> returns null. When using a GUI, this method is
	 * typically invoked when the mouse is released.
	 */
	public void endFlow() {
		currentCell = null;
	}

	/**
	 * Attempts to add a new cell to the flow containing the current cell.
	 * When using a GUI, this method is typically invoked when the mouse is
	 * dragged.  In order to add a cell, the following conditions must be satisfied:
	 * <ol>
	 * <li>The current cell is non-null
	 * <li>The given position is horizontally or vertically adjacent to the
	 * current cell
	 * <li>The given position either is not occupied OR it is occupied by
	 * an endpoint for the flow that is not already in the flow
	 * </ul>
	 * If the three conditions are met, a new cell with the given row/column
	 * and correct color is added to the current flow, and the current cell
	 * is updated to be the new cell.
	 *
	 * @param row given row for the new cell
	 * @param col given column for the new cell
	 */
	public void addCell(int row, int col) {
		if(currentCell != null && isAdjacent(row, col) && !isOccupied(row, col))
		{
			Cell c = new Cell(row, col, currentFlow.getColor());
			currentFlow.add(c);
			currentCell = (currentFlow.isComplete()) ? null : c;
		}
	}

	/**
	 * Returns true if the given position is occupied by a cell in a flow in
	 * this game (possibly an endpoint).
	 *
	 * @param row given row
	 * @param col given column
	 * @return true if any cell in this game has the given row and column, false otherwise
	 */
	public boolean isOccupied(int row, int col) {
		for(int i =0; i <flows.length;i++)
		{
			for(int j=0; j<flows[i].getCells().size(); j++)
			{
				if ((flows[i].getCells().get(j).getRow() == row && flows[i].getCells().get(j).getCol() == col) || isEndpoint(row, col)) {
					//if (flows[i].getCells().get(j) == 1)||(flows[i].getCells().get(j) == 0) {
						return true;
					//}
				}
			}
		}
		return false;
	}

	private boolean isEndpoint(int row, int col)
	{
		for(Flow f: flows)
		{
			for(int i=0;i<=1;i++)
			{
				//TODO find a different way to implement the second part of the if statement
				if((f.getEndpoint(i).getRow() == row && f.getEndpoint(i).getCol() == col) && !f.getEndpoint(i).colorMatches(currentFlow.getColor())) return true; //cant pass through the endpoint that matches the color
			}
		}
		return false;
	}
	/**
	 * Checks to make sure that the cell we are trying to add is directly above or below the current cell
	 */
	private boolean isAdjacent(int row, int col){
		if  (((Math.abs(row - currentCell.getRow())==1) && col == currentCell.getCol()) || (Math.abs(col- currentCell.getCol()) ==1)&&(row == currentCell.getRow())){
			return true;
		}
		return false;
	}
}
