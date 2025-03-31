package MyIterator;

import java.util.Iterator;

/**
 * Allows the using of iterators in methods
 * @auhor Kyle Wagler
 */
public interface IteratorInterface {
	/**
	 * Allows you to iterate through some sort of collection of items
	 * @author Kyle Wagler
	 * @return an iterator for a collection of objects
	 */
	public Iterator<?> getIterator();
}
