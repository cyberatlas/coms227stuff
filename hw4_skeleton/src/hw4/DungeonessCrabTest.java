package hw4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import graph.Cell;
import main.Config;

public class DungeonessCrabTest {
	DungeonessCrab d;
	Cell c1;
	Cell c2;

	@Before
	public void setup() {
		d = new DungeonessCrab();
		c1 = new Cell(null, null);
		c2 = new Cell(null, null);
		c1.setNeighbors(new Cell[] { c2 });
		c2.setNeighbors(new Cell[] { c1 });
		c1.setState(d);
	}

	@Test
	public void checkColorChange() {
		Color first = d.getColor();
		handleCycler();
		Color second = d.getColor();
		assertFalse(first.equals(second));
		assertTrue(first.getAlpha() < second.getAlpha());
		handleCycler();
		Color third = d.getColor();
		assertFalse(second.equals(third));
		assertTrue(second.getAlpha() < third.getAlpha());
		assertTrue(first.equals(Config.FOOD_COLORS[0]));
		assertTrue(second.equals(Config.FOOD_COLORS[1]));
		assertTrue(third.equals(Config.FOOD_COLORS[2]));

		// Shouldn't move until the timer actually resets.
		assertTrue(c1.getState().equals(d));
		assertTrue(c2.getState() == null);
	}

	@Test
	public void checkColorCompleteCycleAndMovement() {
		Color first = d.getColor();
		assertEquals(Config.FOOD_COLORS.length, Config.MAX_FOOD_TIMER);
		for (int i = 0; i < Config.FOOD_COLORS.length; i++) {
			handleCycler();
		}
		assertTrue(first.equals(d.getColor()));

		assertTrue(c2.getState().equals(d));
		assertTrue(c1.getState() == null);
	}

	@Test
	public void checkBlockedMovement() {
		c2.setState(new Wall());
		for (int i = 0; i < Config.FOOD_COLORS.length; i++) {
			handleCycler();
		}
		assertTrue(c2.getState() instanceof Wall);
		assertTrue(c1.getState().equals(d));
	}

	@Test
	public void checkPassable() {
		assertTrue(d.isPassable());
	}

	@Test
	public void checkChar() {
		assertEquals('D', d.toChar());
	}

	// Note: Unlike the SnakeHead, this method will only call handle once, not a
	// full cycle through the timer. This is because the color changes are based
	// on the single increments of the timer.

	// Also Note: I added else to the second if to protect against handling the
	// DungeonessCrab after it already moves in an update. The actual update
	// method uses the parity booleans to solve this problem more effectively.
	private void handleCycler() {
		if (c1.getState() != null) {
			c1.getState().handle(c1);
		} else if (c2.getState() != null) {
			c2.getState().handle(c2);
		}
	}
}
