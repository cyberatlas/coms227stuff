package clock;

/**
 * A Strategy pattern for sending periodic updates.
 * @author Brian Nakayama
 * @see clock.Clock
 */
public interface Updatable {
	
	/**
	 * Called periodically.
	 */
	public void update();
}
