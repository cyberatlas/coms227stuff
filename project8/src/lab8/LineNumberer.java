package lab8;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileNotFoundException;

public class LineNumberer
{
  public static void main(String[] args) throws FileNotFoundException
  {
	 File  file = new File("C:\\Users\\ruski\\Documents\\Coms227\\coms227shit\\project7\\src\\lab7\\Deck.java");
	 Scanner scanner = new Scanner(file);
    //Scanner scanner = new Scanner(System.in);
    int lineCount = 1;

    while (scanner.hasNextLine())
    {
      String line = scanner.nextLine();
      System.out.print(lineCount + " ");
      System.out.println(line);
      lineCount += 1;
    }
    scanner.close();
    System.out.println("Done");
  }
}