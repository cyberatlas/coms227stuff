package main;




import java.util.HashSet;
import java.util.Set;

import javax.swing.SwingUtilities;

import clock.Clock;

import graph.GraphMap;
import graph.GraphMapFactory;

/**
 * Loads and starts a custom snake game. One can use the "-d" option for debug mode.
 * 
 * 
 * For example: 
 * <pre>
 * java Main -d Arena.txt
 * </pre>
 * <p>
 * The contents of Arena.txt:
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
 * @author Brian Nakayama
 *
 */
public class Main {

	public static void main(String[] args) {
		GraphMap map;
		Set<String> argSet = new HashSet<String>();
		for (String s: args){
			argSet.add(s);
		}
		boolean debug = argSet.remove("-d");
		
		if (argSet.size() > 0){
			map = new GraphMapFactory(argSet.iterator().next()).createGraphMap();
		} else {
			map = new GraphMapFactory("Default.txt").createGraphMap();
		}
			
		Runnable r = new Runnable() {
		  public void run() {
		    View gui = new View(map.getPixelWidth(),
		        map.getPixelHeight(), map, debug);
		    try {
		      Thread.sleep(50);
		    } catch (InterruptedException e) {
		      e.printStackTrace();
		    }
		    Clock clock = new Clock(20.0f, gui);
		    clock.init();
		  }
		};
		SwingUtilities.invokeLater(r);
	}
}
