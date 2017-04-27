package hw4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import graph.Cell;
import main.Config;
import state.SnakeSegment;

public class SnakeHeadTest2 {
	private SnakeHead s;
	ArrayList<Cell> field = new ArrayList<>();

	@Before
	public void setup() {
		s = new SnakeHead();
		for (int i = 0; i < 14; i++) {
			field.add(new Cell(null, null));
		}
		field.get(0).setNeighbors(new Cell[] { field.get(1) });
		field.get(1).setNeighbors(new Cell[] { field.get(0), field.get(7), field.get(2), field.get(6) });
		field.get(2).setNeighbors(new Cell[] { field.get(3), field.get(5), field.get(1) });
		field.get(3).setNeighbors(new Cell[] { field.get(2), field.get(4) });
		field.get(4).setNeighbors(new Cell[] { field.get(3), field.get(5) });
		field.get(5).setNeighbors(new Cell[] { field.get(2), field.get(4), field.get(13), field.get(6) });
		field.get(6).setNeighbors(new Cell[] { field.get(5), field.get(1), field.get(11), field.get(8) });
		field.get(7).setNeighbors(new Cell[] { field.get(1), field.get(8) });
		field.get(8).setNeighbors(new Cell[] { field.get(7), field.get(6), field.get(10), field.get(9) });
		field.get(9).setNeighbors(new Cell[] { field.get(8) });
		field.get(10).setNeighbors(new Cell[] { field.get(8), field.get(11) });
		field.get(11).setNeighbors(new Cell[] { field.get(10), field.get(12), field.get(13), field.get(6) });
		field.get(12).setNeighbors(new Cell[] { field.get(11) });
		field.get(13).setNeighbors(new Cell[] { field.get(5), field.get(11) });
		field.get(3).setState(new Wall());
		field.get(6).setState(new Wall());
	}

	@Test
	public void checkInitialDistance() {
		field.get(0).setState(s);
		field.get(13).recalculateMouseDistances();
		assertEquals(4, s.getLength());
	}

	@Test
	public void checkBasicMovement() {
		field.get(0).setState(s);
		field.get(13).recalculateMouseDistances();
		for (Cell c : field) {
			System.out.println("" + c.getMouseDistance() + c.getState());
		}
		handleCycler();
		for (Cell c : field) {
			System.out.println("" + c.getMouseDistance() + c.getState());
		}
		assertTrue(field.get(0).getState() instanceof SnakeSegment);
		assertTrue(field.get(1).getState().equals(s));
	}

	@Test
	public void checkMouseHoming() {
		field.get(1).setState(s);
		field.get(13).recalculateMouseDistances();
		handleCycler();
		assertTrue(field.get(2).getState().equals(s));
		assertTrue(field.get(1).getState() instanceof SnakeSegment);
		assertTrue(field.get(7).getState() == null);

	}

	@Test
	public void checkMouseHomingTwoInARow() {
		field.get(1).setState(s);
		field.get(13).recalculateMouseDistances();

		handleCycler();
		handleCycler();

		assertTrue(field.get(2).getState() instanceof SnakeSegment);
		assertTrue(field.get(1).getState() instanceof SnakeSegment);
		assertTrue(field.get(5).getState().equals(s));
		assertTrue(field.get(7).getState() == null);

	}

	@Test
	public void checkHomingOverride() {
		field.get(2).setState(new Wall());
		field.get(1).setState(s);
		field.get(0).setState(new Wall());
		field.get(13).recalculateMouseDistances();
		handleCycler();
		assertTrue(field.get(7).getState().equals(s));
		assertTrue(field.get(1).getState() instanceof SnakeSegment);
		assertTrue(field.get(2).getState() instanceof Wall);
	}

	@Test
	public void checkHomingOverrideTwoInARow() {
		field.get(2).setState(new Wall());
		field.get(1).setState(s);
		field.get(0).setState(new Wall());
		field.get(13).recalculateMouseDistances();
		handleCycler();
		handleCycler();
		assertTrue(field.get(7).getState() instanceof SnakeSegment);
		assertTrue(field.get(8).getState().equals(s));
		assertTrue(field.get(1).getState() instanceof SnakeSegment);
		assertTrue(field.get(2).getState() instanceof Wall);
	}

	@Test
	public void eatFoodAndHomeMouse() {
		field.get(0).setState(s);
		field.get(7).setState(new Wall());
		field.get(11).setState(new Food());
		field.get(11).recalculateMouseDistances();
		for (int i = 0; i < 5; i++) {
			handleCycler();
		}
		assertTrue(field.get(11).getState().equals(s));
		assertTrue(field.get(13).getState() instanceof SnakeSegment);
		assertTrue(field.get(5).getState() instanceof SnakeSegment);
		assertTrue(field.get(2).getState() instanceof SnakeSegment);
		assertEquals(5, s.getLength());
		// Last segment has already disappeared, even though length increases...
		handleCycler();
		assertTrue(field.get(2).getState() instanceof SnakeSegment);
	}

	// This last test tests your ability to lose the game. Only enable it when
	// you want to check it, because it causes other tests to hang up.

	// @Test
	// public void checkLosing()
	// {
	// // Make sure you get the losing message and a score of 4.
	// field.get(0).setState(s);
	// field.get(1).setState(new Wall());
	// field.get(13).recalculateMouseDistances();
	// handleCycler();
	// for (int i = 1; i < Config.MAX_SNAKE_TIMER; i++)
	// {
	// assertFalse(field.get(i).getState().equals(s));
	// }
	// assertTrue(field.get(0).getState().equals(s));
	// return;
	// }

	private void handleCycler() {
		for (int i = 0; i < Config.MAX_SNAKE_TIMER; i++) {
			for (int j = 0; j < field.size(); j++) {
				if (field.get(j).getState() != null) {
					field.get(j).getState().handle(field.get(j));
				}
			}
		}
	}

}
