package main;

import graph.Cell;
import graph.GraphMap;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.VolatileImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import clock.Updatable;

/**
 * A desktop view for playing the snake game.
 * 
 * @author Brian Nakayama
 * 
 */
public class View extends JFrame implements Updatable {

	/**
	 * Toggles on and off the display of the mouseDistance for cells.
	 */
	private boolean debug;

	/**
	 * The following variable exists because JFrame is serializable.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The model to render to the screen.
	 */
	private GraphMap graphMap;

	/**
	 * Draws the state(s) of the graphMap onto the screen.
	 */
	private Graphics graphics;

	/**
	 * An image for double buffering the screen. We render to this image first
	 * before copying it to the screen.
	 */
	private VolatileImage image;

	/**
	 * The x offset in pixels from which we should draw numbers for debug mode.
	 */
	private int xOffset;

	/**
	 * The y offset in pixels from which we should draw numbers for debug mode.
	 */
	private int yOffset;

	/**
	 * Create a window for the view in pixel dimensions. Draws the given
	 * graphMap in the window.
	 * 
	 * @param width
	 *            The width in pixels
	 * @param height
	 *            The height in pixels
	 * @param graphMap
	 *            The model to draw
	 * @param debug
	 *            If true, draws the mouseDistance for each Cell.
	 */
	public View(int width, int height, GraphMap graphMap, boolean debug) {
		this.graphMap = graphMap;
		this.debug = debug;

		JPanel jp = new JPanel();
		jp.setPreferredSize(new Dimension(width, height));
		this.add(jp);
		//this.addMouseMotionListener(new Control(graphMap));
		jp.addMouseMotionListener(new Control(graphMap));
		this.pack();
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		image = this.createVolatileImage(width, height);
		graphics = jp.getGraphics();
		xOffset = graphics.getFontMetrics().stringWidth(
				"" + Config.MAX_MOUSE_DISTANCE) / 2;
		yOffset = graphics.getFontMetrics().getHeight() / 2;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see clock.Updatable#update()
	 */
	@Override
	public void update() {
		Graphics2D g2D = image.createGraphics();
		g2D.setColor(Color.WHITE);
		g2D.drawRect(0, 0, image.getWidth(), image.getHeight());
		graphMap.clearAllDistances();
		graphMap.update();
		// graphMap.render(g2D);
		for (Cell cell : graphMap) {
			g2D.setColor(cell.getColor());
			g2D.fill(cell.getPolygon());
			if (cell.getState() != null) {
				g2D.setColor(cell.getState().getColor());
				g2D.fill(cell.getPolygon());
			}
			if (debug && cell.getMouseDistance() > 0) {
				Point p = getCenter(cell.getPolygon());
				g2D.setColor(Color.BLACK);
				g2D.drawString("" + cell.getMouseDistance(), p.x - xOffset, p.y
						+ yOffset);
			}
		}
		graphics.drawImage(image, 0, 0, null);
		//graphMap.clearAllDistances();
	}

	/**
	 * Return the approximate center of a polygon. Note, for regular polygons
	 * this should be the centroid.
	 * 
	 * @param p A polygon
	 * @return A point containing the average of its x and y coordinates.
	 */
	private Point getCenter(Polygon p) {
		int x = 0;
		for (int i : p.xpoints) {
			x += i;
		}
		x /= p.xpoints.length;
		int y = 0;
		for (int i : p.ypoints) {
			y += i;
		}
		y /= p.ypoints.length;
		return new Point(x, y);
	}

}
