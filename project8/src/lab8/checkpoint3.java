package lab8;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import plotter.Plotter;
import plotter.Polyline;

public class checkpoint3
{
    public static void main(String[] args) throws FileNotFoundException
    {
        String filename = "..\\project8\\hello.txt";
        readFile(filename);
    }

    public static ArrayList<Polyline> readFile(String filename) throws FileNotFoundException
    {
        Plotter plotter = new Plotter();//create a new plotter
        File file = new File(filename);
        Scanner scan2 = new Scanner(file);//read in and scan through a file
        ArrayList<Polyline> p2 = new ArrayList<Polyline>();//makes a new arraylist of polylines
        String line = scan2.nextLine();
        line = scan2.nextLine();
        while(scan2.hasNextLine())
        {
            line = scan2.nextLine();
            p2.add(parseOneLine(line));//adds one polyline object at a time to be plotted to the arraylist
            plotter.plot(parseOneLine(line));
        }
        scan2.close();
        return p2;
    }

    public static Polyline parseOneLine(String oneLine) throws FileNotFoundException
    {
        Scanner temp = new Scanner(oneLine);
        int width;
        if(temp.hasNextInt()) {width = temp.nextInt();}//if the scanner has another number then with is that number
        else width = 1;//otherwise the width is one
        String color = temp.next();
        Polyline p1 = new Polyline(color, width);//makes a new polyline given the width and color
        while(temp.hasNextInt())
        {
        	//goes through and every 2 numbers is a new cordinate that is plotted
            int x = temp.nextInt();
            int y = temp.nextInt();
            p1.addPoint(new Point(x, y));
        }
        temp.close();
        return p1; //returns the new object
    }
}

