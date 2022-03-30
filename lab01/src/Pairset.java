import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterable collection of Pair objects
 * 
 * @author Michael S. Kirkpatrick and Nathan Sprague
 * @version V2, 1/2018
 */
public class Pairset<T, U> implements Iterable<Pair<T, U>>
{

	private final int maximumSize = 10;
	private Pair<T, U>[] pairs;
	private int currentSize;

	/**
	 * Create a collection that will store items added as pairs.
	 */
	@SuppressWarnings("unchecked")
	public Pairset()
	{
		pairs = new Pair[maximumSize];
		currentSize = 0;
	}

	/**
	 * Create a new Pair and add it to the collection if there's space.
	 * 
	 * @param first  The first object.
	 * @param second The second object.
	 */
	@SuppressWarnings("unchecked")
	public boolean addPair(T first, U second)
	{
		boolean retval = false;

		if (currentSize < maximumSize)
		{
			Pair<T, U> newPair = new Pair<>(first, second);
			pairs[currentSize] = newPair;
			currentSize++;
			retval = true;
		}

		return retval;
	}

	public void removePair()
	{
		currentSize--;
	}

	/**
	 * Get the current count of Pair objects that have been added.
	 */
	public int size()
	{
		return currentSize;
	}

	/**
	 * Creates an Iterator for use in a for-loop. Implement an Iterator here
	 * based on the API documentation at:
	 * https://docs.oracle.com/javase/7/docs/api/java/util/Iterator.html Throw
	 * the exceptions as specified in the API.
	 */
	@Override
	public Iterator<Pair<T, U>> iterator()
	{

		return new Iterator<Pair<T, U>>()
		{

			// TODO: Declare members needed to keep track of the Iterator state
			// here.
			// HINT: You can access the pairs[] array from the containing class
			// directly.

			private int nextVal = 0;
			private int lastRet = -1;

			/**
			 * Return a Boolean indicating whether more items are left.
			 */
			@Override
			public boolean hasNext()
			{
				boolean retval = false;

				if (nextVal < 10)
				{
					if (pairs[nextVal] != null)
					{
						retval = true;
					}
				}
				return retval;
			}

			/**
			 * Return the next Pair in the iterator. We need to suppress the
			 * unchecked warning because Pairset uses an array of Pair objects
			 * rather than an ArrayList.
			 */
			@Override
			@SuppressWarnings("unchecked")
			public Pair<T, U> next()
			{
				Pair<T, U> nextPair;

				if (hasNext())
				{
					nextPair = pairs[nextVal];
					nextVal++;
					lastRet = 1;
				} else
				{
					throw new NoSuchElementException();
				}

				return nextPair;
			}

			/**
			 * Remove the previous Pair returned by next() from the array.
			 */
			@Override
			public void remove()
			{

				if (lastRet == 1)
				{
					int currentVal = nextVal - 1;
					if (currentVal < 0)
					{
						currentVal = 0;
					}
					for (int i = currentVal; i < pairs.length - 1; i++)
					{

						pairs[i] = pairs[i + 1];
					}
					pairs[9] = null;
					lastRet = 0;
					Pairset.this.currentSize--;
				} else 
				{
					throw new IllegalStateException();
				}
			}
		};
	}

	/**
	 * Creates an Iterator to work backwards through the Pairset. This method
	 * should be very similar to the iterator method above, but the anonymous
	 * inner class should have different method implementations.
	 */
	public Iterator<Pair<T, U>> reverseIterator()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns an Iterable wrapper for the reverseIterator. This method creates
	 * an anonymous inner class that implements the Iterable interface.
	 * https://docs.oracle.com/javase/7/docs/api/java/lang/Iterable.html
	 */
	public Iterable<Pair<T, U>> reverse()
	{
		throw new UnsupportedOperationException();
	}

}
