package lab10;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Simplest possible example of drawing pictures in Java.
 */
public class SwingGraphicsExample extends JPanel
{

	/**
	 * Override of the paintComponent method to customize appearance
	 * of this panel.  Take a look at the API for the java.awt.Graphics
	 * class for other methods to try.
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		// Draw a circle of radius 50 whose center is at 200, 150
		// The first two numbers are the upper-left corner of
		// the rectangle that bounds the circle.  The second two
		// numbers are the width and height of the bounding rectangle.
		g.drawOval(150, 100, 100, 100);
	}


	//
	// Remaining code is pretty much the same for any Swing application.
	// The size of the panel is set in the call setPreferredSize()
	//

	/**
	 * Entry point. This method should normally do
	 * nothing except (possibly) parse command-line
	 * arguments and invoke a helper method for creating
	 * and starting up the UI.
	 */
	public static void main(String[] args)
	{
		// Boilerplate code for starting any Swing application
		Runnable r = new Runnable()
		{
			public void run()
			{
				createAndShow();
			}
		};
		SwingUtilities.invokeLater(r);
		System.out.println("Main thread exiting");
	}

	/**
	 * Static helper method creates the frame and
	 * makes it visible.  This method should always be invoked
	 * via a call to SwingUtilities.invokeLater().
	 */
	private static void createAndShow()
	{
		// create the frame, which corresponds to the actual window on the screen
		JFrame frame = new JFrame("Swing Graphics Example");

		// create an instance of our JPanel subclass and
		// set a size for it
		JPanel panel = new SwingGraphicsExample();
		panel.setPreferredSize(new Dimension(400, 300));
		frame.getContentPane().add(panel);

		// make the frame just big enough to hold the panel
		frame.pack();

		// we want to shut down the application if the
		// "close" button is pressed on the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// make the frame visible and start the UI machinery
		frame.setVisible(true);
	}

}