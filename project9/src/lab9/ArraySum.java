package lab9;

public class ArraySum
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
    return arraySumRec(arr, 0, arr.length - 1);
  }
  
  /**
   * Returns the sum of array elements from start to end, inclusive.
   */
  private static int arraySumRec(int[] arr, int start, int end)
  {
    if (start == end)
    {
      return arr[start];
    }
    else
    {
      int mid = (start + end) / 2;
      int leftSum = arraySumRec(arr, start, mid);
      int rightSum = arraySumRec(arr, mid + 1, end);
      return leftSum + rightSum;
    }
  }
}