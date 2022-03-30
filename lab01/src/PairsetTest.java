import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Minimal Unit Tests for the Pair class.
 * 
 * @author CS240 Instructors
 * @version 1.1
 *
 */
public class PairsetTest
{

	// --------------------------------------------
	// TESTS FOR ADDING AND SIZE METHODS
	// --------------------------------------------

	/**
	 * Test for creating an empty Pairset object
	 */
	@Test
	public void testEmptyPairsetCreation()
	{
		Pairset<String, Integer> pairs = new Pairset<>();
		assertEquals(pairs.size(), 0);
	}

	/**
	 * Test the size after adding one Pair
	 */
	@Test
	public void testAddingOnePair()
	{
		Pairset<String, Integer> pairs = new Pairset<>();
		assertEquals(pairs.size(), 0);
		assertTrue(pairs.addPair("hello", 5));
		assertEquals(pairs.size(), 1);
	}

	/**
	 * Test for adding multiple unique Pair instances
	 */
	@Test
	public void testMultipleUnique()
	{
		Pairset<Integer, Integer> pairs = new Pairset<>();
		for (int i = 0; i < 10; i++)
		{
			assertEquals(pairs.size(), i);
			assertTrue(pairs.addPair(i, i * 2));
		}
		assertEquals(pairs.size(), 10);
	}

	/**
	 * Test for adding too many items (more than 10)
	 */
	@Test
	public void testAddTooMany()
	{
		Pairset<Integer, Integer> pairs = new Pairset<>();
		for (int i = 0; i < 10; i++)
		{
			assertEquals(pairs.size(), i);
			assertTrue(pairs.addPair(i, i * 2));
		}
		assertEquals(pairs.size(), 10);

		for (int i = 0; i < 10; i++)
		{
			assertTrue(!pairs.addPair(10 + i, i * 2));
			assertEquals(pairs.size(), 10);
		}
	}

	// --------------------------------------------
	// TESTS FOR ADDING AND ITERATING (NO REMOVE)
	// --------------------------------------------

	/**
	 * Test for iterating with multiple unique Pair instances
	 */
	@Test
	public void testSingleUniqueIterator()
	{
		Pairset<Integer, Integer> pairs = new Pairset<>();
		pairs.addPair(1, 2);

		for (Pair<Integer, Integer> pair : pairs)
		{
			assertEquals(pair.getFirst().intValue(), 1);
			assertEquals(pair.getSecond().intValue(), 2);
		}
	}

	/**
	 * Test for iterating with multiple unique Pair instances
	 */
	@Test
	public void testMultipleUniqueIterator()
	{
		Pairset<Integer, Integer> pairs = new Pairset<>();
		for (int i = 0; i < 10; i++)
		{
			assertEquals(pairs.size(), i);
			assertTrue(pairs.addPair(i, i * 2));
		}

		for (Pair<Integer, Integer> pair : pairs)
		{
			assertEquals(pair.getFirst().intValue() * 2,
					pair.getSecond().intValue());
		}
	}

	/**
	 * Test for the reverse iterator implementation
	 */
	/**
	@Test
	public void testMultipleUniqueReverseIterator()
	{
		Pairset<Integer, Integer> pairs = new Pairset<>();
		for (int i = 0; i < 10; i++)
		{
			assertEquals(pairs.size(), i);
			assertTrue(pairs.addPair(i, i * 2));
		}

		ArrayList<Pair<Integer, Integer>> list = new ArrayList<>();
		for (Pair<Integer, Integer> pair : pairs)
		{
			list.add(pair);
		}

		Iterator<Pair<Integer, Integer>> iter = pairs.reverseIterator();
		while (iter.hasNext())
		{
			Pair<Integer, Integer> revPair = iter.next();
			assertEquals(revPair.getFirst().intValue() * 2,
					revPair.getSecond().intValue());
			Pair<Integer, Integer> forPair = list.remove(list.size() - 1);
			assertEquals(revPair.getFirst(), forPair.getFirst());
			assertEquals(revPair.getSecond(), forPair.getSecond());
		}
	}
	*/

	// --------------------------------------------
	// TESTS FOR REMOVING WITH ITERATOR
	// --------------------------------------------

	/**
	 * Test for adding only one Pair and removing it
	 */
	@Test
	public void testAddOneRemoveIt()
	{
		Pairset<Integer, Integer> pairs = new Pairset<>();
		assertTrue(pairs.addPair(1, 2));
		assertEquals(pairs.size(), 1);
		Iterator<Pair<Integer, Integer>> iter = pairs.iterator();
		assertTrue(iter.hasNext());
		iter.next();
		assertTrue(!iter.hasNext());
		iter.remove();
		assertEquals(pairs.size(), 0);
	}

	/**
	 * Test for removing from the beginning
	 */
	@Test
	public void testIteratorRemoveFirst()
	{

		Pairset<Integer, Integer> pairs = new Pairset<>();
		for (int i = 0; i < 10; i++)
		{
			pairs.addPair(i, i);
		}
		assertEquals(pairs.size(), 10);

		Iterator<Pair<Integer, Integer>> iter = pairs.iterator();
		assertTrue(iter.hasNext());
		iter.next();
		iter.remove();
		assertEquals(pairs.size(), 9);

		for (Pair<Integer, Integer> pair : pairs)
		{
			assertTrue(pair.getFirst() > 0);
		}

	}

	/**
	 * Test for removing from the end
	 */
	@Test
	public void testIteratorRemoveLast()
	{

		Pairset<Integer, Integer> pairs = new Pairset<>();
		for (int i = 0; i < 10; i++)
		{
			pairs.addPair(i, i);
		}
		assertEquals(pairs.size(), 10);

		Iterator<Pair<Integer, Integer>> iter = pairs.iterator();
		while (iter.hasNext())
		{
			iter.next();
		}
		iter.remove();
		assertEquals(pairs.size(), 9);

		for (Pair<Integer, Integer> pair : pairs)
		{
			assertTrue(pair.getFirst() < 9);
		}

	}

	/**
	 * Test for removing from the middle
	 */
	@Test
	public void testIteratorRemoveMiddle()
	{

		Pairset<Integer, Integer> pairs = new Pairset<>();
		for (int i = 0; i < 10; i++)
		{
			pairs.addPair(i, i);
		}
		assertEquals(pairs.size(), 10);

		Iterator<Pair<Integer, Integer>> iter = pairs.iterator();
		for (int i = 0; i < 5; i++)
		{
			iter.next();
		}
		iter.remove();
		assertEquals(pairs.size(), 9);

		for (Pair<Integer, Integer> pair : pairs)
		{
			assertTrue(pair.getFirst() != 4);
		}

	}

	/**
	 * Test for calling next() on an empty Pairset
	 */
	@Test
	public void testNextOnEmptyPairset()
	{

		Pairset<Integer, Integer> pairs = new Pairset<>();
		Iterator<Pair<Integer, Integer>> iter = pairs.iterator();
		assertTrue(!iter.hasNext());
		assertThrows(NoSuchElementException.class, () -> {
			iter.next();
		}, "Cannot call next on an empty Pairset");
	}

	/**
	 * Test for calling remove() twice in a row
	 */
	@Test
	public void testIteratorRemoveTwice()
	{

		Pairset<Integer, Integer> pairs = new Pairset<>();
		for (int i = 0; i < 10; i++)
		{
			pairs.addPair(i, i);
		}
		assertEquals(pairs.size(), 10);

		Iterator<Pair<Integer, Integer>> iter = pairs.iterator();
		for (int i = 0; i < 5; i++)
		{
			iter.next();
		}

		iter.remove();
		assertEquals(pairs.size(), 9);
		assertThrows(IllegalStateException.class, () -> {
			iter.remove();
		}, "Cannot call remove without a call to next");
	}

}
