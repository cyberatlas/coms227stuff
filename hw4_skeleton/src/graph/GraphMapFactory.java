package graph;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.Config;

import color.ColorGenerator;

/**
 * Creates a graphMap given a text file.
 * 
 * @author Brian Nakayama
 * @see graph.GraphMap
 */
public class GraphMapFactory {

	/**
	 * The location of a GraphMap text file
	 */
	private String graphLocation;

	/**
	 * Store the location for creating graphMaps. Below is an example file:
	 * 
	 * <pre>
	 * color.RainbowColorGenerator
	 * graph.SquareMap 15 true
	 * -----------###-----------
	 * --####-------------####--
	 * --#-------------------#--
	 * --#--####---#---####--#--
	 * --#---------#---------#--
	 * --#---------#---------#--
	 * ------------#------------
	 * ---------#######---------
	 * -------------------------
	 * ------------S------------
	 * -------------------------
	 * ---------#######---------
	 * ------------#------------
	 * --#---------#---------#--
	 * --#---------#---------#--
	 * --#--####---#---####--#--
	 * --#-------------------#--
	 * --####-------------####--
	 * -----------###-----------
	 * </pre>
	 * 
	 * @param graphLocation
	 *            The location of the file
	 * @see graph.GraphMap
	 */
	public GraphMapFactory(String graphLocation) {
		this.graphLocation = graphLocation;
	}

	/**
	 * Using the graph location, create a new graphMap.
	 * 
	 * @return The graphMap specified in the file, or a default graphMap if an
	 *         exception occurs.
	 */
	public GraphMap createGraphMap() {

		GraphMap map;
		ColorGenerator gen;
		try {
			Scanner s = new Scanner(new File(graphLocation));
			gen = (ColorGenerator) Class.forName(s.nextLine()).newInstance();

			try {
				map = (GraphMap) Class.forName(s.next()).newInstance();
				if (s.hasNextInt()) {
					map.setDistance(s.nextInt());
				}
				if (s.hasNextBoolean()) {
					map.setCreateFood(s.nextBoolean());
				}
				s.nextLine();
				List<String> descriptor = new ArrayList<String>();
				while (s.hasNext()) {
					descriptor.add(s.nextLine());
				}
				map.setColorGenerator(gen);
				map.initialize(descriptor.toArray(new String[0]));
			} catch (Exception e) {
				System.out.println("Unable to load GraphMap from config."
						+ " Creating the default map with custom colors.");
				map = Config.DEFAULT_GRAPH_MAP.newInstance();
				map.setColorGenerator(gen);
				map.initialize();
			}
			s.close();
		} catch (Exception e) {
			try {
				System.out.println("Unable to load config."
						+ " Creating the default map.");
				map = Config.DEFAULT_GRAPH_MAP.newInstance();
				map.initialize();
			} catch (Exception e0) {
				throw new RuntimeException("Unable to create a graphMap!");
			}
		}

		return map;

	}
}
