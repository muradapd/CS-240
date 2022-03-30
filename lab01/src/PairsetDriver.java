import java.util.Iterator;

/**
 * Adding an Iterator to a generic collection
 *
 * @author Michael S. Kirkpatrick
 * @version V1, 8/2017
 *
 */

public class PairsetDriver
{
	public static void main(String[] args)
	{

		// Try to create 15 Pair instances (only 10 allowed)
		Pairset<String, Integer> pairs = new Pairset<String, Integer>();
		for (int i = 0; i < 15; i++)
		{
			pairs.addPair(Character.toString((char) ('A' + i)), i);
		}

		// Iterate through all and print them
		System.out.println("All pairs:");
		for (Pair<String, Integer> pair : pairs)
		{
			System.out.print(pair.toString() + " ");
		}
		System.out.println("\n");

		// Create an Iterator and remove the one with the second value of 3
		Iterator<Pair<String, Integer>> iterator = pairs.iterator();
		while (iterator.hasNext())
		{
			Pair<String, Integer> pair = iterator.next();
			if (pair.getSecond() == 3)
			{
				iterator.remove();
			}
		}

		// Iterate through all and print them
		System.out.println("After removing Pair with getSecond() of 3:");
		for (Pair<String, Integer> pair : pairs)
		{
			System.out.print(pair.toString() + " ");
		}
		System.out.println("\n");

		iterator = pairs.iterator();
		while (iterator.hasNext())
		{
			Pair<String, Integer> pair = iterator.next();
			if ((pair.getSecond() % 2) == 0)
			{
				iterator.remove();
			}
		}

		// Iterate through all and print them
		System.out.println("After removing Pair with even second values:");
		for (Pair<String, Integer> pair : pairs)
		{
			System.out.print(pair.toString() + " ");
		}
		System.out.println("\n");

		// Now add "B" back in and print
		pairs.addPair("C", 2);

		System.out.println("After added \"C\" back in with even value:");
		for (Pair<String, Integer> pair : pairs)
		{
			System.out.print(pair.toString() + " ");
		}
		System.out.println("\n");

		System.out.println("Here is the Pairset in reverse order:");
		Iterator<Pair<String, Integer>> iter = pairs.reverseIterator();
		while (iter.hasNext())
		{
			Pair<String, Integer> pair = iter.next();
			System.out.print(pair.toString() + " ");
		}
		System.out.println("\n");

	}

}
