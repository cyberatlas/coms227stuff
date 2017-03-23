package lab8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListExample1
{
  public static void main(String[] args)
  {
    String s = "3 5 7 9 12";
    ArrayList<Integer> result = readNumbers(s);
    System.out.println(result);
  }
  
  public static ArrayList<Integer> readNumbers(String text)
  {
    Scanner scanner = new Scanner(text);

    ArrayList<Integer> nums = new ArrayList<Integer>();
    
    scanner = new Scanner(text);
    while (scanner.hasNextInt())
    { 
      nums.add(scanner.nextInt());
    }
    return nums;  
  }
}