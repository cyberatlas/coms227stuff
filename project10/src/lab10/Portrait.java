package lab10;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * A panel which shows a stick figure.
 */
public class Portrait extends JPanel {

	/** Dimensions of body, all of which are in pixels. */
	protected int headRadius;
	protected int neckHeight;
	protected int bodyHeight;
	protected int armSpan;
	protected int armRaise;
	protected int spreadEagleness;

	/** Dimensions of window in pixels. */
	protected static final int SIZE = 100;

	/** Create a portrait with default body shape. */
	public Portrait(double headPercent) {
		// Call sibling constructor.
		this(headPercent, 0.1, 0.2, 0.3, 0.1, 0.3);
	}

	/** Create a portrait with the specified body shape. */
	public Portrait(double headPercent, double neckPercent,
					double bodyPercent, double armPercent,
					double armRaisePercent, double spreadEaglePercent) {
		setMinimumSize(new Dimension(SIZE, SIZE));
		setPreferredSize(getMinimumSize());
		setMaximumSize(getMinimumSize());

		setHeadRadius(headPercent);
		setNeckHeight(neckPercent);
		setBodyHeight(bodyPercent);
		setArmSpan(armPercent);
		setArmRaise(armRaisePercent);
		setSpreadEagleness(spreadEaglePercent);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);

		int midX = SIZE / 2;

		// Draw head.
		g.drawOval(midX - headRadius, 0, headRadius * 2, headRadius * 2);

		// Draw neck.
		int neckTop = headRadius * 2;
		int neckBottom = neckTop + neckHeight;
		int crotch = neckTop + neckHeight + bodyHeight;
		g.drawLine(midX, neckTop, midX, crotch);

		// Draw arms.
		g.drawLine(midX, neckBottom, midX - armSpan, neckBottom + armRaise);
		g.drawLine(midX, neckBottom, midX + armSpan, neckBottom + armRaise);

		// Draw legs.
		g.drawLine(midX, crotch, midX - spreadEagleness, getHeight());
		g.drawLine(midX, crotch, midX + spreadEagleness, getHeight());
	}

	/**
	 * Set head radius.
	 *
	 * @param percent
	 * Proportion of head radius to window height.
	 */
	public void setHeadRadius(double percent) {
		headRadius = (int) (percent * SIZE);
	}


	/**
	 * Set neck height.
	 *
	 * @param percent
	 * Proportion of neck height to window height.
	 */
	public void setNeckHeight(double percent) {
		neckHeight = (int) (percent * SIZE);
	}

	/**
	 * Set body height.
	 *
	 * @param percent
	 * Proportion of body height to window height.
	 */
	public void setBodyHeight(double percent) {
		bodyHeight = (int) (percent * SIZE);
	}

	/**
	 * Set how far arms reach.
	 *
	 * @param percent
	 * Proportion of arm span to window width.
	 */
	public void setArmSpan(double percent) {
		armSpan = (int) (percent * 0.5 * SIZE);
	}

	/**
	 * Set how high or low the arms are raised.
	 *
	 * @param percent
	 * Rate of arm-raisedness. > 0 for raised, 0 for level, < 0 for lowered.
	 */
	public void setArmRaise(double percent) {
		armRaise = (int) (percent * SIZE);
	}

	/**
	 * Set how far the legs are spread apart.
	 *
	 * @param percent
	 * Proportion of leg spread to window width.
	 */
	public void setSpreadEagleness(double percent) {
		spreadEagleness = (int) (percent * 0.5 * SIZE);
	}
}