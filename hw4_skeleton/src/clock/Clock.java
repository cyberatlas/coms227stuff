package clock;

import javax.swing.SwingUtilities;

/**
 * The Clock class simply takes an Updatable, and calls update preiodically according to a given FPS value.
 * 
 * &nbsp;&nbsp;&nbsp;&nbsp; This class sets up the nitty gritty details for a
 * refreshing frame in use for a game or other simulation.  * 
 * @author Brian Nakayama
 * @version 1.8 Removed all UI related code, renamed to Clock.
 * @version 1.7 FSEM implemented for init() method.
 * @since 1.6 Bug fix to the way thread was created.
 * @since 1.5 Several small convenience fixes have been made.
 */
public class Clock implements Runnable {
	// Informs the Thread loop whether to continue running or not.

	private volatile boolean bRun = true;
	// Informs the user if the Projector is running.
	private volatile boolean bIsRunning = false;
	// A Container for the JFrame used in FSM.
	// The desired Frames per Second.
	private volatile float fFps;
	// The object that receives clock updates.
	private Updatable u;
	// The JFrame for FSM mode.
	private volatile Thread t;


	/**
	 * Creates the Clock Object
	 * 
	 * @param fFps
	 *            A float representing the desired Frames per second.
	 * @param u
	 *            The interface object that'll receive updates.
	 */
	public Clock(float fFps, Updatable u) {
		this.fFps = fFps;
		this.u = u;
	}

	/**
	 * Simply returns if Clock is running.
	 * 
	 * @return A boolean defining the running status.
	 */
	public boolean isRunning() {
		return bIsRunning;
	}

	/**
	 * Say that you wanted to switch updates between two different interfaces,
	 * one should use this to set a new one.
	 * 
	 * @param u
	 *            The new interface to receive updates
	 */
	public void setInterface(Updatable u) {
		this.u = u;
	}

	/**
	 * Sets the Frames per Second float variable.
	 * 
	 * @param fFps
	 *            The new FPS rate
	 */
	public void setFPS(float fFps) {
		this.fFps = fFps;
	}

	/**
	 * Initiates the clock.
	 */
	public void init() {
		if (!bIsRunning) {
			resume();
		}
	}

	/**
	 * Resumes the Clock thread if stopped.
	 */
	public void resume() {
		if (!bIsRunning) {
			bRun = true;
			t = new Thread(this);
			t.start();
		}
	}

	/*
	 * Overrides the Threads run method to update the Clock, and for
	 * greatest accuracy the iInterface.
	 */
	public void run() {
		do {
			bIsRunning = true;
			long lTime = System.nanoTime();
			u.update();
			lTime = System.nanoTime() - lTime;
			try {
				/*
				 * The sleep method of Thread accepts milliseconds, while lTime
				 * is currently in nanoseconds. by dividing 1 second(1000f) by
				 * fFps, and subtracting the time it takes to update in
				 * milliseconds, we can make the Clock consistent.
				 */
				long lSleep = (long) (1000f / fFps) - (lTime / 1000000l);
				if (lSleep > 0) {
					Thread.sleep(lSleep);
				} else {
					//System.out.println("Frame Rate Failure: " + lSleep);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (bRun);
		bIsRunning = false;
	}

	/**
	 * Stops the Projection thread.
	 */
	public void stop() {
		bRun = false;
	}
}
