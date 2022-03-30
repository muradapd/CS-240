import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PairTest
{

	// --------------------------------------------
	// TESTS FOR GET METHODS
	// --------------------------------------------

	/**
	 * Test for creating a Pair of Integers
	 */
	/*
	 * @Test public void testPairIntegerInteger() { Pair<Integer> pair = new
	 * Pair<>(1, 2);
	 * 
	 * // Check the object values are 1 and 2 assertEquals(1, pair.getFirst());
	 * assertEquals(2, pair.getSecond()); }
	 */

	/**
	 * Test for creating a Pair of Doubles
	 */
	/*
	 * @Test public void testPairDoubleDouble() { Pair<Double> pair = new
	 * Pair<>(1.1, 2.2);
	 * 
	 * // Check the object values are 1.1 and 2.2 assertEquals(1.1,
	 * pair.getFirst()); assertEquals(2.2, pair.getSecond()); }
	 */

	/**
	 * Test for creating a Pair of String/Integer
	 */
	/*
	 * @Test public void testPairStringInteger() { Pair<String> pair = new
	 * Pair<>("Hello", "World");
	 * 
	 * // Check the object values are "Hello" and "World" assertEquals("Hello",
	 * pair.getFirst()); assertEquals("World", pair.getSecond());
	 * 
	 * }
	 */

	/**
	 * Test for Pair of Integer toString()
	 */

	@Test
	public void testPairIntegersToString()
	{
		Pair<Integer, Integer> pair = new Pair<>(1, 2);
		assertEquals("<1, 2>", pair.toString());
	}

	/**
	 * Test for a composite parameterized Pair
	 */

	@Test
	public void testPairHelperIntegerToString()
	{
		// Create a Pair with a parameterized type and check toString()
		Single<Integer> sng = new Single<>(5);
		Pair<Single<Integer>, Integer> pair = new Pair<>(sng, 10);
		assertEquals("<5, 10>", pair.toString());
	}

}
