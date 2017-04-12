import mini2.GridUtil;

//import reference.OurGridUtil;


public class SimpleTest
{

  public static void main(String[] args)
  {
    // 6x7 array with sequential values 1 through 42 (as in pdf)
    int[][] test = new int[6][7];
    int count = 1;
    for (int row = 0; row < 6; ++row)
    {
      for (int col = 0; col < 7; ++col)
      {
        test[row][col] = count;
        count += 1;
      }
    }
    
    int[][] sub = GridUtil.getSubArray(test, 1, 1, 2, true);
    GridUtil.printArray(sub);   
  }
}
