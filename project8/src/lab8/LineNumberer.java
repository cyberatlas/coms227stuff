package lab8;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileNotFoundException;

public class LineNumberer
{
  public static void main(String[] args) throws FileNotFoundException
  {
	 //File  file = new File("C:\\Users\\ruski\\Documents\\Coms227\\coms227shit\\project7\\src\\lab7\\Deck.java");
	 //Scanner scanner = new Scanner(file);
    //Scanner scanner = new Scanner(System.in);
   // int lineCount = 1;
    
    File f1 = new File("C:\\Users\\ruski\\Documents\\Coms227\\coms227shit\\project8\\story.txt");
    Scanner s1 = new Scanner(f1);
    int count = 0;
    Scanner line = null;
    
    while(s1.hasNextLine()){
    	line = new Scanner( s1.nextLine());
    	while (line.hasNext()){
    		line.next();
    		count +=1;
    	}
    	System.out.println(count);
    	count = 0;
    	//s1.nextLine();
    }
    
    /*
    while (scanner.hasNextLine())
    {
      String line = scanner.nextLine();
      System.out.print(lineCount + " ");
      System.out.println(line);
      lineCount += 1;
    }
    */
    s1.close();
    System.out.println("Done");
  }
}