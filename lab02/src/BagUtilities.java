import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Utility class to use lambdas to query and manipulate the Bag.
 *
 * @author Michael S. Kirkpatrick and ???
 * @version V1, 8/2018
 */
public class BagUtilities<T>
{

	/**
	 * Creates a Predicate to search through the Bag for an item. The item is
	 * passed as an argument to the lambda.
	 *
	 * @param bag The bag to search through.
	 *
	 * @return The Function to apply to the requested item.
	 */
	public Predicate<T> contains(Bag<T> bag)
	{
		Predicate<T> search = item -> {
			Boolean retval = false;

			for (T thing : bag)
			{
				if (item.equals(thing))
				{
					retval = true;
				}
			}

			return retval;
		};

		return search;
	}

	/**
	 * Creates a Function to search for an item. The Function takes an item of
	 * type T and returns an Integer.
	 *
	 * @param bag The bag to search through.
	 *
	 * @return The Function to apply to the requested item.
	 */
	public Function<T, Integer> count(Bag<T> bag)
	{
		Function<T, Integer> counter = item -> {
			Integer count = 0;

			for (T thing : bag)
			{
				if (thing.equals(item))
				{
					count++;
				}
			}

			return count;
		};

		return counter;
	}

	/**
	 * Calculates the sum of counters based on a Predicate. If the items match
	 * according to the Predicate, their counts should be included in the sum.
	 *
	 * @param bag  The bag to search through.
	 * @param pred The Predicate to apply to search for items.
	 *
	 * @return The sum of the matched items.
	 */
	public int sumIf(Bag<T> bag, Predicate<T> pred)
	{
		int sum = 0;

		for (T thing : bag)
		{
			if (pred.test(thing))
			{
				sum++;
			}
		}

		return sum;
	}

	/**
	 * Returns a List of Counter<T> items that match a binary predicate. The
	 * BiPredicate may specify constraints on the element or the count.
	 *
	 * @param bag  The bag to search through.
	 * @param pred The Predicate to apply to search for items.
	 *
	 * @return The list of matched Counter<T> nodes.
	 */
	public List<Counter<T>> search(Bag<T> bag, BiPredicate<T, Integer> pred)
	{
		List<Counter<T>> list = new ArrayList<>();

		return list;
	}
}
