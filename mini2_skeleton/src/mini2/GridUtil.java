package mini2;

import api.ITransform;

import java.lang.reflect.Array;

/**
 * Utility class for applying transformations to 2d arrays.
 * A transformation computes a new value for a cell that is
 * based on the square neighborhood of cells that surround it.
 * Each transformation is an implementation of 
 * the ITransform interface.
 */
public class GridUtil
{
  /**
   * Applies the given transformation to all cells of the given
   * array and produces a new array of the same size.  The original
   * array is not modified.
   * @param arr
   *   given array
   * @param transform
   *   transformation to apply
   * @return
   *   new array consisting of transformed values
   */
  public static int[][] applyAll(int[][] arr, ITransform transform)
  {
    // THIS METHOD IS FULLY IMPLEMENTED AND SHOULD NOT BE MODIFIED
    int numRows = arr.length;
    int numCols = arr[0].length;
    int[][] result = new int[numRows][numCols];
    for (int row = 0; row < numRows; row += 1)
    {
      for (int col = 0; col < numCols; col += 1)
      {
        int[][] subArray = getSubArray(arr, row, col, transform.getRadius(), transform.isWrapped());
        int newValue = transform.apply(subArray);
        result[row][col] = newValue;
      }
    }
    return result;
  }
   
  /**
   * Displays the contents of a 2d int array
   * @param arr
   *   a 2d array of ints
   */
  public static void printArray(int[][] arr)
  {
    for (int row = 0; row < arr.length; row += 1)
    {
      for (int col = 0; col < arr[0].length; col += 1)
      {
        System.out.printf("%3d", arr[row][col]);
      }
      System.out.println();
    }
  }
  
  /**
   * Returns the neighborhood surrounding the specified cell.  In general,
   * the returned array is a square sub-array of <code>arr</code>, 
   * with width and height 2 * <code>radius</code> + 1, whose center is 
   * <code>arr[centerRow][centerCol]</code>.  For cells within
   * <code>radius</code> of the edge, the value for out-of-range
   * indices is determined by
   * the <code>wrapped</code> parameter, as follows:
   * <ul>
   * <li>if <code>wrapped</code> is false, cells for out-of-range indices
   * are filled with zeros
   * <li>if <code>wrapped</code> is true, cells for out-of-range indices
   * are determined by "wrapping" the indices:
   * <ul>
   * <li>for a row index, the array height is added to or subtracted from the index
   * to bring it within range
   * <li>for a column index, the array width is added to or subtracted from
   * the index to bring it within range
   * </ul>
   * </ul>
   * Note that the size of neighborhood, neighborhood size 2 * <code>radius</code> + 1, 
   * is not allowed to be greater than either the width or height of the given array.
   * @param arr
   *   the original array
   * @param centerRow
   *   row index of center cell
   * @param centerCol
   *   column index of center cell
   * @param radius
   *   radius of the neighborhood (return array has width and height
   *   equal to 2 * <code>radius</code> + 1
   * @param wrapped
   *   true if out-of-range indices should be wrapped, false if cells
   *   should be filled with zeros
   * @return
   *   a new 2d array consisting of the cells surrounding the given center
   *   cell
   * @throws IllegalArgumentException
   *   if the neighborhood size 2 * <code>radius</code> + 1 is greater
   *   than the given array's width or height
   */
  public static int[][] getSubArray(int[][] arr, int centerRow, int centerCol, int radius, boolean wrapped)
  {
    // TODO

    //Throws an exception if the sub array is larger than the initial array
	  if (2*radius+1> arr.length || 2*radius+1 >arr[0].length){
	      throw new IllegalArgumentException();
	    }
    //initializes the subarray to 2* radius +1 length and width. Plus 1 accounts for the center element and *2 is to create the full array not just half
    int[][] subArray = new int[2*radius+1][2*radius+1];
   //if (radius> arr.length || radius >arr[0].length){
    

    for(int i = 0; i < 2*radius+1; ++i)
    {
      for(int j = 0; j < 2*radius+1; ++j)
      {
       //changes the value of the row to centerRow -radius and then we add the value of j. This allows us to check the value fo 77
        int row = centerRow-radius+i;
        int col = centerCol-radius+j;
        if(wrapped)
        {
          if (row > arr.length-1){
            row =row -arr.length;
        }
          if (row < 0){
            row = arr.length+row;
          }
          if (col > arr[0].length-1){
              col = col-arr[0].length;
        }
          if (col < 0){
            col = arr[0].length+col;
          }
          
            subArray[i][j] = arr[row][col];
          
        }
        else
        {
          if (row<0 || col<0 || col>arr[0].length-1 || row>arr.length-1){
            subArray[i][j] = 0;
          }else{
         
            subArray[i][j] = arr[row][col];
           }
      }
    }
    }
//    for (int y = centerCol-radius; y <= centerCol+radius;y++){
//      for (int x = centerRow-radius; x<= centerRow+radius; x++){
//        for (int i =0; i < 2*radius+1; i++){
//          for (int j=0; j<2*radius+1;j++){
//            subArray[i][j] = arr[x][y];/
//          }
//        }
//
//      }
//    }
   // int count = 0;
    /*
    for (int y = centerCol-radius, i=0; y <= centerCol+radius; y++, i++){
      for (int x = centerRow-radius, j=0; x <= centerRow+radius; x++, j++){
        //If the value we are looking at is greater than the width of the array, grab the value from the start of the array
        //ex: arr[5][5] and we are looking at [5][6] then it looks at [5][1]
        //doesnt work on negatives
        if (wrapped && x> arr.length){
          int remain = Math.abs(arr.length-1 - x);
          subArray[i][j] = arr[remain-1][j];
        }
        else if (wrapped && y<0){
          int remain = arr.length + y;
          subArray[i][j] = arr[remain][j];
        }
        subArray[i][j] = arr[y][x];
//doesnt work on negatives
        if (wrapped && y> arr.length){
          int remain = Math.abs(arr.length-1 - y);
          subArray[i][j] = arr[i][remain-1];
        }
        else if (wrapped && y<0){
          int remain = arr.length + y;
          subArray[i][j] = arr[i][remain];
        }
      }


    }*/

    return subArray;
  }
}
