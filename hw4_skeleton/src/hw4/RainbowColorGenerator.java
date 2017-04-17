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
		//creates a new a random number generator that returns int 0-5
		Random rand = new Random();
		int numColor = rand.nextInt(5);
		//defining the Color object that we will be using
		Color randColor;
		/**
		 * red =0 orange =1, yellow =2, Green =3, blue =4, purple =5
		 */
		switch (numColor){
			case 0:
				randColor = new Color(0.25f, 0.0f, 0.0f);
				return randColor;
			case 1:
				randColor =  new Color(0.25f, 0.125f,0.0f);
				return randColor;
			case 2:
				randColor = new Color(0.25f,0.25f,0.0f);
				return randColor;
			case 3:
				randColor = new Color(0.0f,0.25f,0.0f);
				return randColor;
			case 4:
				randColor = new Color(0.0f,0.0f,0.25f);
				return randColor;
			case 5:
				randColor = new Color(0.25f,0.0f,0.25f);
				return randColor;
			//this is here so that the program will compile. The program should not ever get to this point
				default:
				return null;
		}
		// why am I getting an error here?
		//return randColor;
	}
}
