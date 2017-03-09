package lab7;

import java.util.Arrays;

/**
 * Class containing some utilities for manipulating arrays.
 */
public class ReverseArray
{
	public static void main(String[] args)
	{
		int[] test = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		System.out.println("Before: " + Arrays.toString(test));
		reverse(test);
		System.out.println("After:  " + Arrays.toString(test));
	}

	/**
	 * Reverses the contents of the given array.
	 * @param arr
	 */
	public static void reverse(int[] arr)
	{
		int front = 0;
		int rear = arr.length - 1;
		while (front < rear)
		{
			swap(arr, front, rear);

			// move indices towards the center
			front += 1;
			rear -= 1;
		}
	}

	public static void swap(int[] arr, int i, int j)
	{
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}