package lab7;

import java.util.Arrays;

public class ArrayExample4
{
	public static void main(String[] args)
	{
		int[] test = {1, 2, 3, 4};
		System.out.println("Before: " + Arrays.toString(test));
		doubleAll(test);
		System.out.println("After:  " + Arrays.toString(test));
	}

	public static void doubleAll(int[] arr)
	{
		for (int i = 0; i < arr.length; i += 1)
		{
			arr[i] = arr[i] * 2;
		}
	}
}