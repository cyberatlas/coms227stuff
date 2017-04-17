package hw4;

import color.ColorGenerator;

import java.awt.*;
import java.util.Random;

/**
 * Created by ruski on 4/16/2017.
 */
public class RainbowColorGenerator implements ColorGenerator{
	@Override
	public Color createColor() {
		Random rand = new Random();
		int numColor = rand.nextInt(5);
		return null;
	}
}
