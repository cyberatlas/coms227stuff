package color;

import java.awt.Color;
import java.util.Random;

import main.Config;

/**
 * Creates 3 light blue colors.
 * @author Brian Nakayama
 *
 */
public class RandomSnowGenerator implements ColorGenerator{
	/**
	 * The base color (average color) to generate.
	 */
	public static final Color COLOR_BASE = new Color(0.5f, 0.5f, 1.0f);
	
	/**
	 * The number generator for creating colors.
	 */
	private Random r = Config.RANDOM;
	
	/*
	 * (non-Javadoc)
	 * @see color.ColorGenerator#getColor()
	 */
	@Override
	public Color createColor() {
		int next = r.nextInt(3);
		switch(next){
		case 0:
			return COLOR_BASE;
		case 1:
			return COLOR_BASE.darker();
		default:
			return COLOR_BASE.brighter();
		}
	}

}
