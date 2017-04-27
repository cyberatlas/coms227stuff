package hw4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.awt.Polygon;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import graph.Cell;

public class SquareMapTest2 {
    private TempSquareMap map = new TempSquareMap();
    private String[] grid;
    private Cell[][] allCells;
    private double epsilon;

    @Before
    public void setup() throws FileNotFoundException {
        map = new TempSquareMap();
        grid = makeSnowflake();
        map.initialize(grid);
        map.setDistance(24);
        allCells = map.getCells();
        epsilon = 10e-6;
    }

    @Test
    public void dimensionalityCheck() {
        assertEquals(936, map.getPixelHeight());
        assertEquals(1392, map.getPixelWidth());
    }

    @Test
    public void gimmeSomeNeighbors1() {
        Cell[] buddies = allCells[30][33].getNeighbors();
        ArrayList<Cell> expectedNeighbors = new ArrayList<>();
        expectedNeighbors.add(allCells[31][33]);
        expectedNeighbors.add(allCells[29][33]);
        expectedNeighbors.add(allCells[30][32]);
        expectedNeighbors.add(allCells[30][34]);
        assertTrue(checkNeighbors(expectedNeighbors, buddies));
    }

    @Test
    public void gimmeSomeNeighbors2() {
        Cell[] buddies = allCells[0][56].getNeighbors();
        ArrayList<Cell> expectedNeighbors = new ArrayList<>();
        expectedNeighbors.add(allCells[0][55]);
        expectedNeighbors.add(allCells[1][56]);
        assertTrue(checkNeighbors(expectedNeighbors, buddies));
    }

    @Test
    public void gimmeSomeNeighbors3() {
        Cell[] buddies = allCells[25][56].getNeighbors();
        ArrayList<Cell> expectedNeighbors = new ArrayList<>();
        expectedNeighbors.add(allCells[25][55]);
        expectedNeighbors.add(allCells[24][56]);
        expectedNeighbors.add(allCells[26][56]);
        assertTrue(checkNeighbors(expectedNeighbors, buddies));
    }

    @Test
    public void testGetClosest1() {
        Point p = map.selectClosestIndex(32, 32);
        assertTrue(Math.abs(p.getX() - 0.0) < epsilon);
        assertTrue(Math.abs(p.getY() - 0.0) < epsilon);
    }

    @Test
    public void testGetClosest2() {
        Point p = map.selectClosestIndex(920, 360);
        assertTrue(Math.abs(p.getX() - 37.0) < epsilon);
        assertTrue(Math.abs(p.getY() - 14.0) < epsilon);
    }

    @Test
    public void testGetClosest3() {
        Point p = map.selectClosestIndex(-1, 233);
        assertTrue(
                "Note: This test checks a pixel outside the actual playing field. Protecting against such behavior is probably optional",
                Math.abs(p.getX() - 0.0) < epsilon);
        assertTrue(Math.abs(p.getY() - 9.0) < epsilon);
    }


    // Note about polygons: You need to make sure you're adding points in the right order, otherwise you get diamonds.

    @Test
    public void testCreatePolygon1() {
        Polygon realPoly = new Polygon();
        realPoly.addPoint(468, 348);
        realPoly.addPoint(468, 324);
        realPoly.addPoint(492, 324);
        realPoly.addPoint(492, 348);
        ArrayList<Point> realPoints = new ArrayList<>();
        for (int i = 0; i < realPoly.npoints; i++) {
            realPoints.add(new Point(realPoly.xpoints[i], realPoly.ypoints[i]));
        }
        Polygon testPoly = map.createPolygon(19, 13);
        ArrayList<Point> testPoints = new ArrayList<>();
        for (int i = 0; i < testPoly.npoints; i++) {
            testPoints.add(new Point(testPoly.xpoints[i], testPoly.ypoints[i]));
        }
        for (Point p : testPoints) {
            assertTrue(realPoints.contains(p));
        }
    }

    @Test
    public void testCreatePolygon2() {
        Polygon realPoly = new Polygon();
        realPoly.addPoint(60, 60);
        realPoly.addPoint(60, 84);
        realPoly.addPoint(84, 84);
        realPoly.addPoint(84, 60);
        ArrayList<Point> realPoints = new ArrayList<>();
        for (int i = 0; i < realPoly.npoints; i++) {
            realPoints.add(new Point(realPoly.xpoints[i], realPoly.ypoints[i]));
        }
        Polygon testPoly = map.createPolygon(2, 2);
        ArrayList<Point> testPoints = new ArrayList<>();
        for (int i = 0; i < testPoly.npoints; i++) {
            testPoints.add(new Point(testPoly.xpoints[i], testPoly.ypoints[i]));
        }
        for (Point p : testPoints) {
            assertTrue(realPoints.contains(p));
        }
    }

    private static class TempSquareMap extends SquareMap {
        @Override
        public Cell[][] getCells() {
            return super.getCells();
        }

        @Override
        public Point selectClosestIndex(int x, int y) {
            return super.selectClosestIndex(x, y);
        }
    }

    private static String[] makeSnowflake() throws FileNotFoundException {
        ArrayList<String> Snowflake = new ArrayList<>();
        Scanner s = new Scanner(new File("Snowflake.txt"));
        int lineNumber = 1;
        while (s.hasNextLine()) {
            String newLine = s.nextLine();
            if (lineNumber > 2 && !newLine.trim().isEmpty()) {
                Snowflake.add(newLine);
            }
            lineNumber++;
        }
        s.close();
        return Snowflake.toArray(new String[0]);
    }

    private static boolean checkNeighbors(ArrayList<Cell> expected, Cell[] neighbors) {
        // the neighbors are not required to be in any particular order,
        // just make sure they are all there, and no extras
        if (expected.size() != neighbors.length) {
            return false;
        }
        List<Cell> actual = Arrays.asList(neighbors);
        for (Cell c : actual) {
            if (!expected.contains(c)) {
                return false;
            }
        }
        for (Cell c : expected) {
            if (!actual.contains(c)) {
                return false;
            }
        }
        return true;
    }

}
