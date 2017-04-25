package hw4;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import graph.Cell;
import main.Config;
import state.SnakeSegment;

public class CellUtilTest2
{

	ArrayList<Cell> field = new ArrayList<>();

	@Before
	public void setup()
	{
		for (int i = 0; i < 14; i++)
		{
			field.add(new Cell(null, null));
			field.get(i).setNeighbors(new Cell[] {});
		}
	}

	@Test
	public void checkInitialDistance()
	{
		field.get(0).recalculateMouseDistances();
		assertEquals(Config.MAX_MOUSE_DISTANCE, field.get(0).getMouseDistance());
	}

	@Test
	public void checkStayPositive()
	{
		for (int i = 0; i < 10; i++)
		{
			field.add(new Cell(null, null));
		}
		for (int i = 0; i < field.size() - 1; i++)
		{
			field.get(i).setNeighbors(new Cell[] { field.get(i + 1) });
		}
		field.get(0).recalculateMouseDistances();
		for (int i = 0; i < field.size(); i++)
		{
			assertEquals(Math.max(Config.MAX_MOUSE_DISTANCE - i, 0), field.get(i).getMouseDistance());
		}
	}

	@Test
	public void checkTriangles1()
	{
		field.get(0).setNeighbors(new Cell[] { field.get(1) });
		field.get(1).setNeighbors(new Cell[] { field.get(0), field.get(10), field.get(2) });
		field.get(2).setNeighbors(new Cell[] { field.get(1), field.get(3), field.get(7) });
		field.get(3).setNeighbors(new Cell[] { field.get(2), field.get(4) });
		getClass();
		field.get(4).setNeighbors(new Cell[] { field.get(3) });
		field.get(5).setNeighbors(new Cell[] { field.get(6), field.get(7) });
		field.get(6).setNeighbors(new Cell[] { field.get(5), field.get(13) });
		field.get(7).setNeighbors(new Cell[] { field.get(2), field.get(8), field.get(5) });
		field.get(8).setNeighbors(new Cell[] { field.get(9), field.get(7), field.get(11) });
		field.get(9).setNeighbors(new Cell[] { field.get(10), field.get(8) });
		field.get(10).setNeighbors(new Cell[] { field.get(1), field.get(9) });
		field.get(11).setNeighbors(new Cell[] { field.get(8), field.get(13), field.get(12) });
		field.get(12).setNeighbors(new Cell[] { field.get(11) });
		field.get(13).setNeighbors(new Cell[] { field.get(11), field.get(6) });
		field.get(0).recalculateMouseDistances();

		assertEquals(20, field.get(0).getMouseDistance());
		assertEquals(19, field.get(1).getMouseDistance());
		assertEquals(18, field.get(2).getMouseDistance());
		assertEquals(17, field.get(3).getMouseDistance());
		assertEquals(16, field.get(4).getMouseDistance());
		assertEquals(16, field.get(5).getMouseDistance());
		assertEquals(15, field.get(6).getMouseDistance());
		assertEquals(17, field.get(7).getMouseDistance());
		assertEquals(16, field.get(8).getMouseDistance());
		assertEquals(17, field.get(9).getMouseDistance());
		assertEquals(18, field.get(10).getMouseDistance());
		assertEquals(15, field.get(11).getMouseDistance());
		assertEquals(14, field.get(12).getMouseDistance());
		assertEquals(14, field.get(13).getMouseDistance());
	}

	@Test
	public void checkTriangles2()
	{
		field.get(0).setNeighbors(new Cell[] { field.get(1) });
		field.get(1).setNeighbors(new Cell[] { field.get(0), field.get(10), field.get(2) });
		field.get(2).setNeighbors(new Cell[] { field.get(1), field.get(3), field.get(7) });
		field.get(3).setNeighbors(new Cell[] { field.get(2), field.get(4) });
		field.get(4).setNeighbors(new Cell[] { field.get(3) });
		field.get(5).setNeighbors(new Cell[] { field.get(6), field.get(7) });
		field.get(6).setNeighbors(new Cell[] { field.get(5), field.get(13) });
		field.get(7).setNeighbors(new Cell[] { field.get(2), field.get(8), field.get(5) });
		field.get(8).setNeighbors(new Cell[] { field.get(9), field.get(7), field.get(11) });
		field.get(9).setNeighbors(new Cell[] { field.get(10), field.get(8) });
		field.get(10).setNeighbors(new Cell[] { field.get(1), field.get(9) });
		field.get(11).setNeighbors(new Cell[] { field.get(8), field.get(13), field.get(12) });
		field.get(12).setNeighbors(new Cell[] { field.get(11) });
		field.get(13).setNeighbors(new Cell[] { field.get(11), field.get(6) });

		field.get(7).setState(new Wall());
		field.get(11).setState(new Wall());

		field.get(0).recalculateMouseDistances();

		assertEquals(20, field.get(0).getMouseDistance());
		assertEquals(19, field.get(1).getMouseDistance());
		assertEquals(18, field.get(2).getMouseDistance());
		assertEquals(17, field.get(3).getMouseDistance());
		assertEquals(16, field.get(4).getMouseDistance());
		assertEquals(0, field.get(5).getMouseDistance());
		assertEquals(0, field.get(6).getMouseDistance());
		assertEquals(0, field.get(7).getMouseDistance());
		assertEquals(16, field.get(8).getMouseDistance());
		assertEquals(17, field.get(9).getMouseDistance());
		assertEquals(18, field.get(10).getMouseDistance());
		assertEquals(0, field.get(11).getMouseDistance());
		assertEquals(0, field.get(12).getMouseDistance());
		assertEquals(0, field.get(13).getMouseDistance());
	}

	@Test
	public void checkSquares1()
	{
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
		field.get(0).recalculateMouseDistances();

		assertEquals(20, field.get(0).getMouseDistance());
		assertEquals(19, field.get(1).getMouseDistance());
		assertEquals(18, field.get(2).getMouseDistance());
		assertEquals(17, field.get(3).getMouseDistance());
		assertEquals(16, field.get(4).getMouseDistance());
		assertEquals(17, field.get(5).getMouseDistance());
		assertEquals(18, field.get(6).getMouseDistance());
		assertEquals(18, field.get(7).getMouseDistance());
		assertEquals(17, field.get(8).getMouseDistance());
		assertEquals(16, field.get(9).getMouseDistance());
		assertEquals(16, field.get(10).getMouseDistance());
		assertEquals(17, field.get(11).getMouseDistance());
		assertEquals(16, field.get(12).getMouseDistance());
		assertEquals(16, field.get(13).getMouseDistance());
	}

	@Test
	public void checkSquares2()
	{
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
		field.get(0).recalculateMouseDistances();

		assertEquals(20, field.get(0).getMouseDistance());
		assertEquals(19, field.get(1).getMouseDistance());
		assertEquals(18, field.get(2).getMouseDistance());
		assertEquals(0, field.get(3).getMouseDistance());
		assertEquals(16, field.get(4).getMouseDistance());
		assertEquals(17, field.get(5).getMouseDistance());
		assertEquals(0, field.get(6).getMouseDistance());
		assertEquals(18, field.get(7).getMouseDistance());
		assertEquals(17, field.get(8).getMouseDistance());
		assertEquals(16, field.get(9).getMouseDistance());
		assertEquals(16, field.get(10).getMouseDistance());
		assertEquals(15, field.get(11).getMouseDistance());
		assertEquals(14, field.get(12).getMouseDistance());
		assertEquals(16, field.get(13).getMouseDistance());
	}

	@Test
	public void checkHexagons1()
	{
		// Taken from the test I wrote last week using the hexagon map

		Cell c1 = new Cell(null, null);
		Cell c2 = new Cell(null, null);
		Cell c3 = new Cell(null, null);
		Cell c4 = new Cell(null, null);
		Cell c5 = new Cell(null, null);
		Cell c6 = new Cell(null, null);
		Cell c7 = new Cell(null, null);
		Cell c8 = new Cell(null, null);
		Cell c9 = new Cell(null, null);
		Cell c10 = new Cell(null, null);
		Cell c11 = new Cell(null, null);
		Cell c12 = new Cell(null, null);
		Cell c13 = new Cell(null, null);
		Cell c14 = new Cell(null, null);

		c1.setNeighbors(new Cell[] { c6, c2 });
		c2.setNeighbors(new Cell[] { c1, c6, c5, c3 });
		c3.setNeighbors(new Cell[] { c2, c5, c4 });
		c4.setNeighbors(new Cell[] { c3, c5, c8 });
		c5.setNeighbors(new Cell[] { c2, c6, c7, c8, c4, c3 });
		c6.setNeighbors(new Cell[] { c1, c2, c5, c7, c14 });
		c7.setNeighbors(new Cell[] { c14, c13, c10, c8, c5, c6 });
		c8.setNeighbors(new Cell[] { c4, c5, c7, c10, c9 });
		c9.setNeighbors(new Cell[] { c8, c10, c11 });
		c10.setNeighbors(new Cell[] { c7, c8, c9, c11, c12, c13 });
		c11.setNeighbors(new Cell[] { c9, c10, c12 });
		c12.setNeighbors(new Cell[] { c13, c10, c11 });
		c13.setNeighbors(new Cell[] { c14, c7, c10, c12 });
		c14.setNeighbors(new Cell[] { c6, c7, c13 });
		c1.recalculateMouseDistances();

		assertEquals(20, c1.getMouseDistance());
		assertEquals(19, c2.getMouseDistance());
		assertEquals(18, c3.getMouseDistance());
		assertEquals(17, c4.getMouseDistance());
		assertEquals(18, c5.getMouseDistance());
		assertEquals(19, c6.getMouseDistance());
		assertEquals(18, c7.getMouseDistance());
		assertEquals(17, c8.getMouseDistance());
		assertEquals(16, c9.getMouseDistance());
		assertEquals(17, c10.getMouseDistance());
		assertEquals(16, c11.getMouseDistance());
		assertEquals(16, c12.getMouseDistance());
		assertEquals(17, c13.getMouseDistance());
		assertEquals(18, c14.getMouseDistance());
	}

	@Test
	public void checkHexagons2()
	{
		Cell c1 = new Cell(null, null);
		Cell c2 = new Cell(null, null);
		Cell c3 = new Cell(null, null);
		Cell c4 = new Cell(null, null);
		Cell c5 = new Cell(null, null);
		Cell c6 = new Cell(null, null);
		Cell c7 = new Cell(null, null);
		Cell c8 = new Cell(null, null);
		Cell c9 = new Cell(null, null);
		Cell c10 = new Cell(null, null);
		Cell c11 = new Cell(null, null);
		Cell c12 = new Cell(null, null);
		Cell c13 = new Cell(null, null);
		Cell c14 = new Cell(null, null);

		c1.setNeighbors(new Cell[] { c6, c2 });
		c2.setNeighbors(new Cell[] { c1, c6, c5, c3 });
		c3.setNeighbors(new Cell[] { c2, c5, c4 });
		c4.setNeighbors(new Cell[] { c3, c5, c8 });
		c5.setNeighbors(new Cell[] { c2, c6, c7, c8, c4, c3 });
		c6.setNeighbors(new Cell[] { c1, c2, c5, c7, c14 });
		c7.setNeighbors(new Cell[] { c14, c13, c10, c8, c5, c6 });
		c8.setNeighbors(new Cell[] { c4, c5, c7, c10, c9 });
		c9.setNeighbors(new Cell[] { c8, c10, c11 });
		c10.setNeighbors(new Cell[] { c7, c8, c9, c11, c12, c13 });
		c11.setNeighbors(new Cell[] { c9, c10, c12 });
		c12.setNeighbors(new Cell[] { c13, c10, c11 });
		c13.setNeighbors(new Cell[] { c14, c7, c10, c12 });
		c14.setNeighbors(new Cell[] { c6, c7, c13 });

		SnakeHead s1 = new SnakeHead();
		SnakeSegment s2 = new SnakeSegment(s1);
		Wall w1 = new Wall();
		Food f1 = new Food();
		DungeonessCrab d1 = new DungeonessCrab();
		c1.setState(d1);
		c4.setState(s1);
		c3.setState(s2);
		c7.setState(w1);
		c9.setState(w1);
		c8.setState(f1);

		c1.recalculateMouseDistances();

		assertEquals(20, c1.getMouseDistance());
		assertEquals(19, c2.getMouseDistance());
		assertEquals(0, c3.getMouseDistance());
		assertEquals(0, c4.getMouseDistance());
		assertEquals(18, c5.getMouseDistance());
		assertEquals(19, c6.getMouseDistance());
		assertEquals(0, c7.getMouseDistance());
		assertEquals(17, c8.getMouseDistance());
		assertEquals(0, c9.getMouseDistance());
		assertEquals(16, c10.getMouseDistance());
		assertEquals(15, c11.getMouseDistance());
		assertEquals(16, c12.getMouseDistance());
		assertEquals(17, c13.getMouseDistance());
		assertEquals(18, c14.getMouseDistance());

	}
}
