package lab7;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ArrayExample3
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
		int count = 0;
		while (scanner.hasNextInt())
		{
			scanner.nextInt();
			count +=1;
		}

		int[] nums = new int[count];
		scanner = new Scanner(text);
		int index = 0;
		while (scanner.hasNextInt())
		{
			int num = scanner.nextInt();
			nums[index] = num;
			index += 1;
		}
		return nums;
	}
	public static int[] getPostitiveNumbers(int[] numbers){
		int count = 0;
		for (int i = 0; i<numbers.length;i++){
			if (numbers[i] >0){
				count++;
			}
		}

		int[] newArray = new int[count];
		int i= 0;
		for(int j =0; j<count; j++) {
			if (j > 0) {
				newArray[i] = numbers[j];
				i++;
			}
		}
		return newArray;
	}
	public static int[] randomExperiment(int max, int iters){
		Random rand = new Random();
		int[] counts = new int[max];
		for (int i=0; i <iters; i++){
			for(int j=0; j<max; j++){
				counts[i] += rand.nextInt(max);
			}
		}

		return counts;
	}

}