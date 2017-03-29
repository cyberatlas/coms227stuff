package lab8;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import plotter.Plotter;
import plotter.Polyline;

public class CheckPoint3
{
    public static void main(String[] args) throws FileNotFoundException
    {
        String filename = "U:\\cs227\\workspace\\project8\\src\\lab8\\hello.txt";
        readFile(filename);
    }

    public static ArrayList<Polyline> readFile(String filename) throws FileNotFoundException
    {
        Plotter plotter = new Plotter();
        File file = new File(filename);
        Scanner scan2 = new Scanner(file);
        ArrayList<Polyline> p2 = new ArrayList<Polyline>();
        String line = scan2.nextLine();
        line = scan2.nextLine();
        while(scan2.hasNextLine())
        {
            line = scan2.nextLine();
            p2.add(parseOneLine(line));
            plotter.plot(parseOneLine(line));
        }
        scan2.close();
        return p2;
    }

    public static Polyline parseOneLine(String oneLine) throws FileNotFoundException
    {
        Scanner temp = new Scanner(oneLine);
        int width;
        if(temp.hasNextInt()) {width = temp.nextInt();}
        else width = 1;
        String color = temp.next();
        Polyline p1 = new Polyline(color, width);
        while(temp.hasNextInt())
        {
            int x = temp.nextInt();
            int y = temp.nextInt();
            p1.addPoint(new Point(x, y));
        }
        temp.close();
        return p1;
    }
}

