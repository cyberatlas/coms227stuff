package mini2;

import api.ITransform;

/**
 * Transformation implementing a smoothing operation
 * on cells of an array.  The new value is the 
 * average of the values in a neighborhood
 * around a given cell, rounded to the nearest
 * integer. The size of the neighborhood is 
 * 2 * radius + 1, where the radius is a parameter
 * provided to the constructor. 
 * The <code>isWrapped()</code> method always returns false.
 */
public class SmoothingTransform implements ITransform
{
  private int radius;
  
  public SmoothingTransform(int givenRadius)
  {
    radius = givenRadius;
  }
  
  @Override
  public int apply(int[][] elements)
  {
    // TODO
   // num of
    return 0;
  }

  @Override
  public int getRadius()
  {
    return radius;
  }

  @Override
  public boolean isWrapped()
  {
    return false;
  }

}
