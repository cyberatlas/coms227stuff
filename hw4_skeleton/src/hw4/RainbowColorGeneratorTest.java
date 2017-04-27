package hw4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class RainbowColorGeneratorTest {
	RainbowColorGenerator r;
	Color red;
	Color orange;
	Color yellow;
	Color green;
	Color blue;
	Color purple;
	int[] colorArray = { 0, 0, 0, 0, 0, 0, 0 };

	@Before
	public void setup() {
		r = new RainbowColorGenerator();
		red = new Color(0.25f, 0.0f, 0.0f);
		orange = new Color(0.25f, 0.125f, 0.0f);
		yellow = new Color(0.25f, 0.25f, 0.0f);
		green = new Color(0.0f, 0.25f, 0.0f);
		blue = new Color(0.0f, 0.0f, 0.25f);
		purple = new Color(0.25f, 0.0f, 0.25f);
	}

	@Test
	public void testRandom() {
		for (int i = 0; i < 1000; i++) {
			Color test = r.createColor();

			if (test.equals(red)) {
				++colorArray[0];
			} else if (test.equals(orange)) {
				++colorArray[1];

			} else if (test.equals(yellow)) {
				++colorArray[2];
			} else if (test.equals(green)) {
				++colorArray[3];
			} else if (test.equals(blue)) {
				++colorArray[4];
			} else if (test.equals(purple)) {
				++colorArray[5];
			} else {
				++colorArray[6];
			}
		}

		assertEquals(0, colorArray[6]);
		int sum = 0;
		for (int i = 0; i < colorArray.length; i++) {
			sum += colorArray[i];
		}
		assertEquals(1000, sum);

		// Manually check to see that these values are approximately equal; the
		// seventh element should be zero:

		for (int i = 0; i < colorArray.length; i++) {
			System.out.println(colorArray[i]);
		}
	}
}
