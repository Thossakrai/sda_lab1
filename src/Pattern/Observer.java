package Pattern;

/**
 * An interface for all Observers
 */
public interface Observer {
	/**
	 * Informs this observer that an observed subject has changed
	 * 
	 * @param o
	 *            the observed subject that has changed
	 */
	void update(Observable o);public
	void update(Observable o, Object arg);
}