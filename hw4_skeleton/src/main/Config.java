package main;

import color.RandomGreenGenerator;
import graph.HexagonMap;
import java.awt.Color;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JOptionPane;

import state.State;

/**
 * Holds the default settings for instances in a snake game.
 * 
 * @author Brian Nakayama
 * 
 */
public class Config {
	
	/**
	 * The default max amount of frames that a food will "flash" in.
	 */
	public static final int MAX_FOOD_TIMER = 7;
	/**
	 * The default colors that a food will cycle through.
	 */
	public static final Color[] FOOD_COLORS;

	/**
	 * The default max amount of frames that a snake will stay in.
	 */
	public static final int MAX_SNAKE_TIMER = 5;
	/**
	 * The default colors that a snake will cycle through.
	 */
	public static final Color[] SNAKE_COLORS;

	/**
	 * The default color generator to use if a configuration cannot load a generator.
	 */
	public static final Class<RandomGreenGenerator> DEFAULT_COLOR_GEN = RandomGreenGenerator.class;
	
	/**
	 * The default graph map to use if a configuration cannot load a map.
	 */
	public static final Class<HexagonMap> DEFAULT_GRAPH_MAP = HexagonMap.class;
	/**
	 * The default width for a map if a configuration cannot load one.
	 */
	public static final int GRAPH_DEFAULT_WIDTH = 25;
	/**
	 * The default height for a map if a configuration cannot load one.
	 */
	public static final int GRAPH_DEFAULT_HEIGHT = 19;
	/**
	 * The default distance in pixels to maintain between cells.
	 */
	public static final int GRAPH_DISTANCE = 20;

	/**
	 * The default number generator for the entire game.
	 */
	public static final Random RANDOM = new Random();
	
	/**
	 * The default maximum distance for path finding to a mouse.
	 */
	public static final int MAX_MOUSE_DISTANCE = 20;
			
	/**
	 * A map for all State classes found in the state package.
	 */
	public static final Map<Character, Class<State>> STATES = new HashMap<Character, Class<State>>();

	static {
		FOOD_COLORS = new Color[MAX_FOOD_TIMER];
		for (int i = 0; i < FOOD_COLORS.length; i++) {
			FOOD_COLORS[i] = new Color(1.0f, 0.0f, 1.0f, (i + 1.0f)
					/ FOOD_COLORS.length);
		}

		SNAKE_COLORS = new Color[MAX_SNAKE_TIMER];
		for (int i = 0; i < SNAKE_COLORS.length; i++) {
			SNAKE_COLORS[i] = new Color(1.0f, 0.0f, 0.0f, (i + 1.0f)
					/ SNAKE_COLORS.length);
		}

		File packag = new File("src/hw4");
		for (File file : packag.listFiles()) {
			String fileName = file.getName();

			try {

				@SuppressWarnings("unchecked")
				Class<State> clazz = (Class<State>) Class.forName("hw4."
						+ fileName.substring(0, fileName.length() - 5));
				State s;
				s = clazz.newInstance();

				STATES.put(s.toChar(), clazz);
			} catch (ClassNotFoundException e) {
				System.out.println("No class was found for the file "
						+ fileName + ".");
			} catch (InstantiationException | IllegalAccessException e) {
				System.out.println("Unable to create instances for " + fileName
						+ ".");
			} catch (ClassCastException e) {
              // it's not a State, so ignore it?
            }


		}

	}
	
	public static void endGame(int score){
		JOptionPane.showMessageDialog(null, "GAME OVER. Score: "
				+ score);
		System.exit(0);
	}

}
