import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

/**
 * Simple test driver for the DoubleList class.
 * 
 * @author Nathan Sprague and Michael S. Kirkpatrick
 * @version 1
 *
 */
public class DoubleListTest
{

	private String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public <T> DoubleList<T> makeDoubleList()
	{
		return new DoubleList<T>();
	}

	public DoubleList<String> makeLetterList()
	{
		DoubleList<String> list = makeDoubleList();
		for (int i = 0; i < letters.length(); i++)
		{
			list.append(letters.substring(i, i + 1));
		}
		return list;
	}

	// --------------------------------------------
	// TESTS FOR SIZE
	// --------------------------------------------
	@Test
	public void testEmptySizeZero()
	{
		DoubleList<String> list = makeDoubleList();
		assertEquals(list.getClass().getName(), 0, list.size());
	}

	@Test
	public void testAddAllLetters()
	{
		DoubleList<String> list = makeLetterList();
		assertEquals(list.getClass().getName(), 26, list.size());
	}

	@Test
	public void testAllLettersLoop()
	{
		DoubleList<String> list = makeLetterList();
		for (int i = 0; i < list.size(); i++)
		{
			assertEquals(list.getClass().getName(), letters.substring(i, i + 1),
					list.get(i));
		}
	}

	@Test
	public void testAddAllLettersForEach()
	{
		DoubleList<String> list = makeLetterList();
		int i = 0;
		for (String s : list)
		{
			assertEquals(list.getClass().getName(), letters.substring(i, i + 1),
					s);
			i++;
		}
		assertEquals(list.getClass().getName(), i, letters.length());
	}

	@Test
	public void testIteratorRemoveLessAll()
	{
		DoubleList<String> list = makeLetterList();
		Iterator<String> it = list.iterator();
		while (it.hasNext())
		{
			it.next();
			it.remove();
		}
		assertEquals(list.getClass().getName(), 0, list.size());
	}

	@Test
	public void testIteratorRemoveLessThanM()
	{
		DoubleList<String> list = makeLetterList();
		Iterator<String> it = list.iterator();
		while (it.hasNext())
		{
			if (it.next().compareTo("M") < 0)
				it.remove();
		}
		assertEquals(list.getClass().getName(), 14, list.size());
	}

	@Test(expected = IllegalStateException.class)
	public void testRemoveWithoutNext()
	{
		DoubleList<String> list = makeLetterList();
		Iterator<String> it = list.iterator();
		it.remove();
	}

	@Test(expected = java.util.NoSuchElementException.class)
	public void testNextAfterEnd()
	{
		DoubleList<String> list = makeLetterList();
		Iterator<String> it = list.iterator();
		while (it.hasNext())
		{
			it.next();
		}
		it.next();
	}

	@Test
	public void testAppendAfterRemove()
	{
		DoubleList<String> list = makeLetterList();
		Iterator<String> it = list.iterator();
		while (it.hasNext())
		{
			if (it.next().compareTo("M") < 0)
				it.remove();
		}
		assertEquals(list.getClass().getName(), 14, list.size());
		list.append("?");
		assertEquals(list.getClass().getName(), 15, list.size());
		int i = 12;
		for (String s : list)
		{
			if (i < letters.length())
			{
				assertEquals(list.getClass().getName(),
						letters.substring(i, i + 1), s);
				i++;
			} else
			{
				assertEquals(list.getClass().getName(), "?", s);
			}
		}
		assertEquals(list.getClass().getName(), i, letters.length());
	}

	@Test
	public void testAddArbitraryLocation()
	{
		DoubleList<String> list = makeLetterList();
		list.add(0, "A");
		list.add(list.size(), "A");
		list.add(list.size() / 2, "A");
		int i = 0;
		for (String s : list)
		{
			if (i == 0 || i == 14 || i == 28)
				assertEquals(list.getClass().getName(), "A", s);
			else if (i < 14)
				assertEquals(list.getClass().getName(),
						letters.substring(i - 1, i), s);
			else
				assertEquals(list.getClass().getName(),
						letters.substring(i - 2, i - 1), s);
			i++;
		}

	}

	@Test
	public void testRemoveArbitraryLocation()
	{
		DoubleList<String> list = makeLetterList();
		list.remove(0);
		list.remove(list.size() / 2);
		list.remove(list.size() - 1);
		int i = 0;
		for (String s : list)
		{
			if (i == 0 || i == 13)
				i++;
			assertEquals(list.getClass().getName(), letters.substring(i, i + 1),
					s);
			i++;
		}
		assertEquals(list.getClass().getName(), i + 1, letters.length());
	}

	@Test
	public void testReverse()
	{
		DoubleList<String> list = makeLetterList();
		list.reverse();
		int i = letters.length() - 1;
		for (String s : list)
		{
			assertEquals(list.getClass().getName(), letters.substring(i, i + 1),
					s);
			i--;
		}
	}

}
