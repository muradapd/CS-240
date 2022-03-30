import java.util.Iterator;

/**
 * Simple test driver for the DoubleList class.
 * 
 * @author Nathan Sprague and Michael S. Kirkpatrick
 * @version 1
 *
 */
public class DoubleListDriver
{

	public static void main(String[] args)
	{
		DoubleList<String> list = new DoubleList<String>();

		String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0; i < letters.length(); i++)
		{
			list.append(letters.substring(i, i + 1));
		}

		System.out.println("Printing using indexed for loop:");
		for (int i = 0; i < list.size(); i++)
		{
			System.out.print(list.get(i) + " ");
		}

		System.out.println("\n\nPrinting using for-each loop:");
		for (String s : list)
		{
			System.out.print(s + " ");
		}

		System.out.println("\n\nRemoving all letters less than M");
		Iterator<String> it = list.iterator();
		while (it.hasNext())
		{
			if (it.next().compareTo("M") < 0)
				it.remove();
		}

		System.out.println("Resulting size: " + list.size());

		System.out.println("Remaining letters");
		for (int i = 0; i < list.size(); i++)
		{
			System.out.print(list.get(i) + " ");
		}

		System.out.println("\n\nAttempting to remove without next().");
		it = list.iterator();
		try
		{
			it.remove();
			System.out.println("ERROR: This should have thrown an exception.");
		} catch (IllegalStateException e)
		{
			System.out.println("SUCCESS: Exception thrown.");
		}

		System.out.println("\nAttempting to next() after the end.");
		while (it.hasNext())
		{
			it.next();
		}
		try
		{
			it.next();
			System.out.println("ERROR: This should have thrown an exception.");
		} catch (java.util.NoSuchElementException e)
		{
			System.out.println("SUCCESS: Exception thrown.");
		}

		System.out.println("\nAttempting to add elements after removing some.");
		list.append("?");

		System.out.println("Resulting size: " + list.size());

		System.out.println("Remaining letters");
		for (int i = 0; i < list.size(); i++)
		{
			System.out.print(list.get(i) + " ");
		}

		System.out.println("\n\nRemoving all remaining letters.");
		it = list.iterator();
		while (it.hasNext())
		{
			it.next();
			it.remove();
		}
		System.out.println("Resulting size: " + list.size());

		for (int i = 0; i < letters.length(); i++)
		{
			list.append(letters.substring(i, i + 1));
		}

		System.out.println("\n\nStarting over:");
		for (String s : list)
		{
			System.out.print(s + " ");
		}
		System.out.println("");

		list.add(0, "A");
		list.add(list.size(), "A");
		list.add(list.size() / 2, "A");
		int i = 0;
		for (String s : list)
		{
			if (i == 0 || i == 14 || i == 28)
				System.out.print(s + " ");
			i++;
		}

		String removed = list.remove(13);

		System.out.println("\n\nRemoved \"" + removed + "\":");
		for (String s : list)
		{
			System.out.print(s + " ");
		}
		System.out.println("");

		list.remove(0);
		list.remove(list.size() - 1);
		System.out.println("\nRemoved first and last:");
		for (String s : list)
		{
			System.out.print(s + " ");
		}
		System.out.println("");

		list.reverse();
		System.out.println("\nReversed:");
		for (String s : list)
		{
			System.out.print(s + " ");
		}
		System.out.println("");

		list.add(11, removed);

		System.out.println("\nAdded removed item back into its place:");
		for (String s : list)
		{
			System.out.print(s + " ");
		}
		System.out.println("");

	}

}
