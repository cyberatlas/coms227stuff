package lab9;

public class RecursiveDeli
{

  public static void main(String[] args)
  {
    String result = makeDigitSandwich(4);
    System.out.println(result);
  }

  public static String makeDigitSandwich(int outerDigit)
  {
    if (outerDigit == 0)
    {
      // Base case
      return "0";
    }
    else
    {
      // Recursive case
      String middle = makeDigitSandwich(outerDigit - 1); 
      String result = outerDigit + middle + outerDigit;
      return result;
    }
  }
}