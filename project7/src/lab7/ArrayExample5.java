package lab7;

import java.util.Arrays;

public class ArrayExample5
{
	public static void main(String[] args)
	{
		int[] test = {10, 20, 30, 40, 50};
		System.out.println("Before: " + Arrays.toString(test));
		swap(test, 1, 4);
		System.out.println("After:  " + Arrays.toString(test));

	}

	public static void swap(int[] arr, int i, int j)
	{
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}