package hw4;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import graph.Cell;
import main.Config;

public class FoodTest2
{
	Food f;
	Cell c1;
	Cell c2;

	@Before
	public void setup()
	{
		f = new Food();
		c1 = new Cell(null, null);
		c2 = new Cell(null, null);
		c1.setNeighbors(new Cell[] { c2 });
		c2.setNeighbors(new Cell[] { c1 });
		c1.setState(f);
	}

	@Test
	public void checkColorChange()
	{
		Color first = f.getColor();
		handleCycler();
		Color second = f.getColor();
		assertFalse(first.equals(second));
		assertTrue(first.getAlpha() < second.getAlpha());
		handleCycler();
		Color third = f.getColor();
		assertFalse(second.equals(third));
		assertTrue(second.getAlpha() < third.getAlpha());
		assertTrue(first.equals(Config.FOOD_COLORS[0]));
		assertTrue(second.equals(Config.FOOD_COLORS[1]));
		assertTrue(third.equals(Config.FOOD_COLORS[2]));

		assertTrue(c1.getState().equals(f));
		assertTrue(c2.getState() == null);
	}

	@Test
	public void checkColorCompleteCycle()
	{
		Color first = f.getColor();
		assertEquals(Config.FOOD_COLORS.length, Config.MAX_FOOD_TIMER);
		for (int i = 0; i < Config.FOOD_COLORS.length; i++)
		{
			handleCycler();
		}
		assertTrue(first.equals(f.getColor()));
	}

	@Test
	public void checkPassable()
	{
		assertTrue(f.isPassable());
	}

	@Test
	public void checkChar()
	{
		assertEquals('F', f.toChar());
	}

	// Note: Unlike the SnakeHead, this method will only call handle once, not a
	// full cycle through the timer. This is because the color changes are based
	// on the single increments of the timer.
	
	private void handleCycler()
	{
		if (c1.getState() != null)
		{
			c1.getState().handle(c1);
		}
		if (c2.getState() != null)
		{
			c2.getState().handle(c2);
		}
	}
}
