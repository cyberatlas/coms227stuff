package api;


/**
 * Interface representing a transformation on a 2d array
 * based on a square neighborhood of each cell.
 */
public interface ITransform
{
  /**
   * Returns the new value obtained by applying this transformation
   * to the given neighborhood.  The original cell is the center 
   * of the given array and is always
   *  <code>elements[getRadius()][getRadius()]</code>.
   * 
   * @param elements
   *   the neighborhood of cells from which to compute a new value
   * @return
   *   new value for the center cell
   * @throws IllegalArgumentException
   *   if either the width or height of the given array is 
   *   not equal to 2 * <code>getRadius()</code> + 1
   */
  int apply(int[][] elements);
  
  /**
   * Returns the radius of the neighborhood required to compute
   * this transformation.
   * @return
   *   radius of the neighborhood required to compute
   * this transformation
   */
  int getRadius();
  
  /**
   * Returns whether or not this transformation expects
   * out of range neighborhood values to be wrapped.
   * @return
   *   true if values should be wrapped, false otherwise
   */
  boolean isWrapped();
}
