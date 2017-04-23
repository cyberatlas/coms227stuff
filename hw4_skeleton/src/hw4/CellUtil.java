package hw4;

import graph.Cell;

public class CellUtil
{
  /**
   * Sets the mouse distance for the given cell and recursively sets
   * the mouse distance for all neighboring cells that a) do not already 
   * have a larger mouse distance and b) are open or passable.  Neighboring
   * cells satisfying these conditions are set to <code>distance - 1</code>.
   * If the given <code>distance</code> is less than or equal to zero, this 
   * method does nothing.
   * @param cell
   *   the cell whose distance is to be set
   * @param distance
   *   the distance value to be set in the given cell
   */
  public static void calculateMouseDistance(Cell cell, int distance)
  {

    if (cell.getState() == null||cell.getState().isPassable() == true ){
    cell.setMouseDistance(distance);
    }

    if (distance > 0) {
    for (Cell c : cell.getNeighbors()) {
      if (c.getMouseDistance() > distance) {
        calculateMouseDistance(c, distance - 1);
      }
    }
  }

  }


}
