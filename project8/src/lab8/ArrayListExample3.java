package lab8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListExample3
{
    public static void main(String[] args)
    {
        int[] test = {3, -5, -6, 9, 12};
        int[] result = getPositiveNumbers(test);
        System.out.println(Arrays.toString(result));
    }

    public static int[] getPositiveNumbers(int[] arr)
    {
        ArrayList<Integer> positiveNums = new ArrayList<Integer>();
        for (int num : arr)
        {
            if (num > 0)
            {
                positiveNums.add(num);
            }
        }

        int[] ret = new int[positiveNums.size()];
        for (int i = 0; i < positiveNums.size(); i += 1)
        {
            ret[i] = positiveNums.get(i);
        }
        return ret;
    }
}