package api;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A Flow represents a sequence of cells from a 2D grid.
 * The Flow object also permanently stores two designated cells, called
 * the endpoints, even when the sequence is empty.  
 * The sequence is initially empty, and cells are added to the
 * sequence using the <code>add()</code> method. 
 */
public class Flow
{
  /**
   * The sequence of cells in this Flow.
   */
  private ArrayList<Cell> cells;
  
  /**
   * The two endpoints associated with this Flow.
   */
  private Cell[] endpoints;
  
  /**
   * Constructs a Flow with the given endpoints and an empty list of cells.
   * The client is responsible for ensuring that the endpoints have the same color
   * and do not have the same position.
   * @param endpoint0
   *   a Cell
   * @param endpoint1
   *   a Cell
   */
  public Flow(Cell endpoint0, Cell endpoint1)
  {
    endpoints = new Cell[2];
    endpoints[0] = endpoint0;
    endpoints[1] = endpoint1;
    cells = new ArrayList<Cell>();
  }
  
  /**
   * Returns the list of cells in this flow.  Clients should
   * not directly modify this list.
   * @return
   *   list of cells in this flow
   */
  public ArrayList<Cell> getCells()
  {
    return cells;
  }
  
  /**
   * Clears the list of cells in this flow.
   */
  public void clear()
  {
    cells.clear();
  }
  
  /**
   * Adds the given cell to the list of cells for this flow.  The client
   * is responsible for ensuring that the cell is of the correct color
   * and is adjacent to the last cell in the list.
   * are 
   * @param c
   */
  public void add(Cell c)
  {
    cells.add(c);
  }
  
  /**
   * Returns one of the endpoints (0 or 1) associated with this flow.
   * @param i
   *   index (0 or 1) of the endpoint to return
   * @return
   *   an endpoint associated with this flow
   */
  public Cell getEndpoint(int i)
  {
    return endpoints[i];
  }
  
  /**
   * Returns the color of the cells associated with this flow.  
   * @return
   *   color of the cells associated with this flow
   */
  public Color getColor()
  {
    return endpoints[0].getColor();
  }
  
  /**
   * Determines whether this flow's list of cells appears to be complete,
   * that is, whether it starts with one of the endpoints and ends with the other.
   * @return
   *   true if the list is complete, false otherwise
   */
  public boolean isComplete()
  {
    if (cells.size() >= 2)
    {
      Cell first = cells.get(0);
      Cell last = cells.get(cells.size() - 1);
      if (first.equals(endpoints[0]) && last.equals(endpoints[1]) ||
          first.equals(endpoints[1]) && last.equals(endpoints[0]))
      {
        return true;
      }         
    }
    return false;
  }
  
  /**
   * Returns a string representation of this flow.
   */
  @Override
  public String toString()
  {
    // TODO
    return "{" + endpoints[0] + ", " + endpoints[1] + "} " + 
        cells.toString();    
  }
}
