package mini2;

import api.ITransform;

/**
 * Transformation implementing Conway's Game of Life.
 * All cells have value 0 or 1. The new value is based on the center 
 * cell along with the sum S of its eight neighbors, according to 
 * the rules:
 * <ul>
 *   <li>if S is less than 2 the result is 0
 *   <li>if S is greater than 3 the result is 0
 *   <li>if the center cell is 1 and S is 2 or 3, the result is 1
 *   <li>if the center cell is 0 and S is exactly 3, the result is 1
 * </ul>
 * See
 * http://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
 *
 * <p>
 * The radius is 1 and the <code>isWrapped()</code> method 
 * always returns true.
 */
public class ConwayTransform implements ITransform
{

  @Override
  public int apply(int[][] elements)
  {
    // TODO
    //adds all the values of the subarray except for the center point
    //s is the variable for the sum, returnVal is the variable for the value to be returned

	  if (2 * getRadius()+1 != elements.length || 2*getRadius()+1 != elements[0].length){
	      throw new IllegalArgumentException();
	    }
	  
    int s = 0;
    int returnVal = 0;
    for (int i =0; i < elements.length; i++){
      for (int j =0; j< elements[0].length; j++){
        //checks to make sure it is not at the center point so that it is not used in the sum
        if (!(i==1 && j==1)){
          //adds the current value of the array to the sum
          s += elements[i][j];
        }
      }
    }
    //checks the rules listed above
    if(s<2 || s>3){
      returnVal = 0;
    }else if(elements[elements.length/2][elements[0].length/2] == 1 && (s==2 || s==3)) {
      returnVal = 1;
    }
    else if(elements[elements.length/2][elements[0].length/2] == 0 && s==3){
      returnVal =1;
    }
    else
    {
    	returnVal = 0;
    }
    return returnVal;
  }

  @Override
  public int getRadius()
  {
    return 1;
  }

  @Override
  public boolean isWrapped()
  {
    return true;
  }
  
}
