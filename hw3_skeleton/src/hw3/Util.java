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
 * @author Alex Stevenson
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
		//holds the row that is currently being looked at, used when creating the flow
		for (int rows = 0; rows < descriptor.length; rows++) {

			for (int cols = 0; cols < descriptor[rows].length(); cols++) {
				//If the character in the string is not a dash, set the color to the character in the string
				if (descriptor[rows].charAt(cols) != '-') {
					//adds a new cell with the color that is passed in the string
					strCell.add(new Cell(rows, cols, descriptor[rows].charAt(cols)));

				}

			}
		}
		//make a flow array that is half the size of the ArrayList that hold the endpoints because there are 2 endpoints in every flow
		Flow[] flows = new Flow[strCell.size() / 2];
		//loops through every index of the flow array and creates a new flow from the end points if the color of both endpoints matches
		for (int i = 0; i < flows.length; i++) {
			for (int j = 1; j < strCell.size(); j++) {
				if (strCell.get(0).colorMatches(strCell.get(j).getColor())) {
					flows[i] = new Flow(strCell.get(0), strCell.get(j));
					//gets rid of the endpoints stored in those indeces
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
		//reads in the filename and save it in the file object file
		File file = new File(filename);
		Scanner scanArrayList = new Scanner(file);
		//create an ArrayList to hold the games
		ArrayList<FlowGame> flowgames = new ArrayList<>();

		//creates an empty string
		String tempString = "";
		ArrayList<String> tempArrayList = new ArrayList<>(0);
		//add the temporary string to the to the temporary array list if it isn't empty and has another character in it after the whitespace is trimmed
		while (!(tempString = scanArrayList.next()).trim().isEmpty() && scanArrayList.hasNext())
			tempArrayList.add(tempString);
		//Creates a string array. These will be used to create the flowgames
		String[] tempArray = new String[tempArrayList.size()];
		//iterates through the String array and gives it the value in the temp array list
		for (int i = 0; i < tempArrayList.size(); i++)
			tempArray[i] = tempArrayList.get(i);
		//takes the value stored in tempArray, turns it into a flow and adds it to the arrayList of flow games
		flowgames.add(new FlowGame(tempArray));

		return flowgames;
	}

}
