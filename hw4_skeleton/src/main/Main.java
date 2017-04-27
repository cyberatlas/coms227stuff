package main;


import java.util.HashSet;
import java.util.Set;

import javax.swing.SwingUtilities;

import clock.Clock;

import graph.Cell;
import graph.GraphMap;
import graph.GraphMapFactory;
import hw4.DungeonessCrab;
import state.State;

/**
 * Loads and starts a custom snake game. One can use the "-d" option for debug mode.
 * <p>
 * <p>
 * For example:
 * <pre>
 * java Main -d Arena.txt
 * </pre>
 * <p>
 * The contents of Arena.txt:
 * <p>
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
 * @author Brian Nakayama
 */


public class Main {

    public static void main(String[] args) {
        GraphMap map;
        Set<String> argSet = new HashSet<String>();
        for (String s : args) {
            argSet.add(s);
        }
        boolean debug = argSet.remove("-d");

        if (argSet.size() > 0) {
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


/**
 * Steve's test for the dungeoness crab. Should return true null null true
 */
/*
public class Main {
	public static void main(String[] args) {
		// Create a cell whose state is DungeonessCrab and
		// give it an open neighbor.  Note the color and
		// polygon won't be used, so we can leave them null
		Cell c = new Cell(null, null);
		State myCrab = new DungeonessCrab();
		c.setState(myCrab);
		Cell c2 = new Cell(null, null);
		Cell[] neighbors = {c2};
		c.setNeighbors(neighbors);

		// check initial values
		System.out.println(c.getState() == myCrab); // should be true
		System.out.println(c2.getState());           // should be null

		// do updates (calls handle())
		for (int i = 0; i < Config.MAX_FOOD_TIMER; ++i) {
			c.update();
		}

		// crab state should have moved to neighbor
		System.out.println(c.getState()); // should be null
		System.out.println(c2.getState() == myCrab); // should be true
	}
}
*/