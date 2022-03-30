import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Partial implementation of a Bag containing a list of Counters.
 * 
 * @author Michael S. Kirkpatrick
 * @version V1, 8/2018
 */
public class Bag<T> implements Iterable<T>
{

	private ArrayList<Counter<T>> counters;

	// --------------------------------------------
	// DO NOT MODIFY THIS CLASS
	// add(T) - adds an item
	// counters() - returns Iterator<Counter<T>>
	// iterator() - returns Iterator<T>
	// size() - returns the total of all Counters
	// --------------------------------------------

	/**
	 * Create a collection that will store items added as pairs.
	 */
	public Bag()
	{
		counters = new ArrayList<Counter<T>>();
	}

	/**
	 * Add another instance of an item to the Bag. If there is already a Counter
	 * for that item, increment its value. Otherwise, add a new Counter object.
	 * 
	 * @param item The item to add.
	 *
	 * @return true if successful (should always work).
	 */
	public boolean add(T item)
	{
		for (Counter<T> c : counters)
		{
			if (c.element().equals(item))
			{
				c.increment();
				return true;
			}
		}
		Counter<T> c = new Counter<T>(item);
		counters.add(c);
		return true;
	}

	/**
	 * Checks to see if the Bag contains at least one of an item.
	 *
	 * @param item The item to find in the Bag.
	 * @return true if there is a Counter with that object.
	 */
	/*
	 * public boolean contains(T item) { for (Counter<T> c : counters) { if
	 * (c.element().equals(item)) { return true; } } return false; }
	 */

	/**
	 * Searchs through the Bag for an item and indicates how many instances have
	 * been added. This assumes that the add operation guarantees items are
	 * inserted only once.
	 *
	 * @param item The item to count.
	 *
	 * @return The number in the Counter for that item.
	 */
	/*
	 * public int count(T item) { for (Counter<T> count : counters) { if
	 * (count.element().equals(item)) { return count.count(); } } return 0; }
	 */

	public Iterable<Counter<T>> counters()
	{
		return new Iterable<Counter<T>>()
		{
			@Override
			public Iterator<Counter<T>> iterator()
			{
				return counters.iterator();
			}
		};
	}

	/**
	 * Iterator for all of the items added to the Bag. Note that if the same
	 * item was added multiple times, it will be returned multiple times.
	 *
	 * @return The Iterator of all elements added to the Bag.
	 */
	@Override
	public Iterator<T> iterator()
	{
		return new Iterator<T>()
		{
			Iterator<Counter<T>> iter = counters.iterator();
			Counter<T> current = (iter.hasNext() ? iter.next() : null);
			int index = 0;

			@Override
			public boolean hasNext()
			{
				return current != null;
			}

			@Override
			public T next()
			{
				if (!hasNext())
				{
					throw new NoSuchElementException();
				}

				// Note: No item can be removed, which simplifies things.
				T item = current.element();
				index++;

				// If this is the last of this item, get the next Counter<T>.
				if (index >= current.count())
				{
					index = 0;
					if (iter.hasNext())
					{
						current = iter.next();
					} else
					{
						current = null;
					}
				}
				return item;
			}
		};
	}

	/**
	 * Get the total of all the Counter objects. You should use the Iterator
	 * returned below and loop through, counting up the total of all the Counter
	 * objects.
	 *
	 * @return The total number of (not unique) items in the Bag.
	 */
	public int size()
	{
		int sum = 0;
		for (Counter<T> c : counters)
		{
			sum += c.count();
		}
		return sum;
	}

}
