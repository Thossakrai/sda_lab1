package Pattern;

import java.util.ArrayList;

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
	public void update(Observable o);		// Pull
	public void update(ArrayList<CourseRecord> data);	// Push
}