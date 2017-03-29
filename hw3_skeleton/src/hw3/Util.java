package hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


import api.Cell;
import api.Flow;

/**
 * Utility class with methods for creating Flows from string descriptors
 * and reading string descriptors from a file.  A string descriptor is
 * an array of strings, all with the same length, in which an alphabetic
 * character at a given row and column represents a cell at that
 * row and column.  The color of the cell is determined from the character
 * by the Cell constructor.  A descriptor is invalid if not all strings
 * are the same length or if there is an alphabetic character that
 * does not appear exactly twice.
 */
public class Util {
	/**
	 * Creates an array of Flow objects based on the string descriptor.
	 * If the descriptor is invalid, this method returns null.
	 *
	 * @param descriptor array of strings
	 * @return array of Flow objects determined by the descriptor
	 */
	public static Flow[] createFlowsFromStringArray(String[] descriptor) {

		ArrayList<Cell> strCell = new ArrayList<Cell>(0);
		//int row = 0;//holds the row that is currently being looked at, used when creating the flow
		for ( int rows = 0; rows<descriptor.length; rows++){

			for (int cols = 0; cols <descriptor[rows].length(); cols++) {
				if (descriptor[rows].charAt(cols) != '-') {

					strCell.add(new Cell(rows, cols, descriptor[rows].charAt(cols)));

				}

			}
		}
		Flow[] flows = new Flow[strCell.size() /2];
		for (int i=0; i<flows.length; i++){
			for (int j=1;j<strCell.size();j++){
				if (strCell.get(0).colorMatches(strCell.get(j).getColor()))
				{
					flows[i]=new Flow(strCell.get(0),strCell.get(j));
					strCell.remove(j);
					strCell.remove(0);
					break;
				}
			}
		}
		return flows;
	}

	/**
	 * Reads the given file and constructs a list of FlowGame objects, one for
	 * each descriptor in the file.  Descriptors in the file are separated by
	 * some amount of whitespace, but the file need not end with whitespace and
	 * may have extra whitespace at the beginning.  Invalid descriptors in the file
	 * are ignored, so the method may return an empty list.
	 *
	 * @param filename name of the file to read
	 * @return list of FlowGame objects created from the valid descriptors in the file
	 * @throws FileNotFoundException
	 */

	public static ArrayList<FlowGame> readFile(String filename) throws FileNotFoundException {
		File file =  new File(filename);//reads in the filename and save it in the file object file
		Scanner r = new Scanner(file);
		ArrayList<FlowGame> games = new ArrayList<>();//create an ArrayList to hold the games
//        while(r.hasNext())
//        {
		String s;
		ArrayList<String> board = new ArrayList<>(0);
		while(!(s = r.next()).trim().isEmpty() && r.hasNext())
			board.add(s);
		String[] b = new String[board.size()];
		for(int i=0;i<board.size();i++)
			b[i] = board.get(i);
		for(int i=0;i<b.length;i++)
			System.out.println(b[i]);
		games.add(new FlowGame(b));
//        }
		return games;
	}

}
