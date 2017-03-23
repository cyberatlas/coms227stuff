package lab8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListExample2
{
  public static void main(String[] args)
  {
    String s = "3 5 7 9 12";
    int[] result = readNumbers(s);
    System.out.println(Arrays.toString(result));
  }
  
  public static int[] readNumbers(String text)
  {
    Scanner scanner = new Scanner(text);

    ArrayList<Integer> nums = new ArrayList<Integer>();
    
    scanner = new Scanner(text);
    while (scanner.hasNextInt())
    { 
      nums.add(scanner.nextInt());
    }
    
    int[] ret = new int[nums.size()];
    for (int i = 0; i < nums.size(); i += 1)
    {
      ret[i] = nums.get(i);
    }
    return ret;
  }
}