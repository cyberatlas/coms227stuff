package hw4;

import graph.Cell;

public class CellUtil {
    /**
     * Sets the mouse distance for the given cell and recursively sets
     * the mouse distance for all neighboring cells that a) do not already
     * have a larger mouse distance and b) are open or passable.  Neighboring
     * cells satisfying these conditions are set to <code>distance - 1</code>.
     * If the given <code>distance</code> is less than or equal to zero, this
     * method does nothing.
     *
     * @param cell     the cell whose distance is to be set
     * @param distance the distance value to be set in the given cell
     * @author Alex
     */
    public static void calculateMouseDistance(Cell cell, int distance) {
        //If the distance is less than or equal to 0, do nothing
        if (distance <= 0) return;
        //if the cell is null or the cell is of a passable state, then set the mouse distance to the distance passed in
        if (cell.getState() == null || cell.getState().isPassable()) {
            cell.setMouseDistance(distance);
        }
        //loop through the array and if the distance is less than the mousedisance, call the method again to calculate the distance for the rest of the cells
        for (Cell c : cell.getNeighbors()) {
            if (c.getMouseDistance() < cell.getMouseDistance()) {
                calculateMouseDistance(c, distance - 1);
            }
        }

    }


}
