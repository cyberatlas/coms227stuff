package hw4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import graph.Cell;

public class WallTest
{
	private Wall w;
	private Cell currentCell;
	private Cell otherCell;

	@Before
	public void setup()
	{
		w = new Wall();
		currentCell = new Cell(null, null);
		otherCell = new Cell(null, null);
		currentCell.setNeighbors(new Cell[] { otherCell });
		currentCell.setState(w);
	}

	@Test
	public void noEffectOnCells()
	{
		w.handle(currentCell);
		assertEquals(null, otherCell.getState());
		assertEquals(Wall.class, currentCell.getState().getClass());
		w.handle(currentCell);
		assertEquals(null, otherCell.getState());
		assertEquals(Wall.class, currentCell.getState().getClass());
	}
	
	@Test
	public void whiteTest()
	{
		assertTrue(w.getColor().equals(new Color(255, 255, 255)));
	}
	
	@Test
	public void passability()
	{
		assertFalse(w.isPassable());
	}
	
	@Test
	public void correctChar()
	{
		assertEquals(w.toChar(), '#');
	}

}
