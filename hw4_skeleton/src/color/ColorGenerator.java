package color;

import java.awt.Color;

/**
 * An abstract factory for generating colors.
 * @author Brian Nakayama
 * @see graph.GraphMap#initialize(String[] descriptor)
 */
public interface ColorGenerator {
	/**
	 * Makes a new color. For style, subclasses should create multiple colors.
	 * @return A Color
	 */
	public Color createColor();

}
