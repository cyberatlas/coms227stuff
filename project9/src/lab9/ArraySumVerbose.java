package lab9;

public class ArraySumVerbose
{
  /**
   * Try it out.
   */
  public static void main(String[] args)
  {
    int[] test = {3, 4, 5, 1, 2, 3, 2}; // sum should be 20
    int result = arraySum(test);
    System.out.println(result);
  }

  /**
   * Returns the sum of all array elements.
   */
  public static int arraySum(int[] arr)
  {
    int result = arraySumVerbose(arr, 0, arr.length - 1, 0);
    System.out.print(subArrayToString(arr, 0, arr.length - 1));
    System.out.println(" -> " + result);
    return result;
  }
  

  /**
   * Finds the sum of array elements from indices start to end, inclusive.
   */
  private static int arraySumVerbose(int[] arr, int start, int end, int depth)
  {
    if (start == end)
    {
      return arr[start];
    }
    else
    {
      // print the whole subarray
      System.out.print(makeSpaces(depth));
      System.out.println(subArrayToString(arr, start, end));

      // show how it's split
      System.out.print(makeSpaces(depth));
      int mid = (start + end) / 2;
      String leftString = subArrayToString(arr, start, mid);
      String rightString = subArrayToString(arr, mid + 1, end);
      System.out.println(leftString + " " + rightString);
      
      // recursively find sum for left subarray
      int leftSum = arraySumVerbose(arr, start, mid, depth);
      
      // print left subarray again followed by result
      System.out.print(makeSpaces(depth));
      System.out.println(leftString + " -> " + leftSum);
      
      // recursively find sum for right subarray, line up output
      int rightDepth = depth + leftString.length() + 1;
      int rightSum = arraySumVerbose(arr, mid + 1, end, rightDepth);
      
      // print right subarray again followed by result
      System.out.print(makeSpaces(rightDepth));
      System.out.println(rightString + " -> " + rightSum);      
      return leftSum + rightSum;
    }
  }

  
  /**
   * Returns a string representation of the subarray from start to
   * end, inclusive.
   */
  private static String subArrayToString(int[] arr, int start, int end)
  {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    if (start <= end)
    {
      sb.append(arr[start]);
    }
    for (int i = start + 1; i <= end; ++i)
    {
      sb.append(", " + arr[i]);
    }
    
    sb.append("]");
    return sb.toString();
  }
  
  /**
   * Returns a string of n spaces.
   */
  private static String makeSpaces(int n)
  {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; ++i)
    {
      sb.append(" ");
    }
    return sb.toString();
  }
}