package examples;

import api.ITransform;

/**
 * Transformation that does "nothing", i.e., it returns 
 * the value of the cell of the given 1 x 1 array.  This 
 * is sometimes useful for testing. The radius is zero and
 * values are not wrapped.
 */
public class DoNothingTransform implements ITransform
{

  @Override
  public int apply(int[][] elements)
  {
    return elements[getRadius()][getRadius()];
  }

  @Override
  public int getRadius()
  {
    return 0;
  }

  @Override
  public boolean isWrapped()
  {
    return false;
  }
  
}
