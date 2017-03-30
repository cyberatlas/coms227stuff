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
    result  = ballin(7n);
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
   * Reads in the number of levels, gives you the number of balls. The sum ends up becoming a pyramid
   * @param n (number of levels)
   * @return the number of ballz
   */
  public static int ballin(int n){
	  	  
	  return ballz(n);}
  
  private static int ballz(int n){
	  if (n== 1){
		  //base case
		  return 1;
	  }
	  else{
		  return (n*n)+(ballz(n-1));
	  }
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
      //return leftSum + rightSum;
      return (Math.max(leftSum, rightSum));
    }
  }
}