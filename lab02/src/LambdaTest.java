import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

/**
 * Minimal Unit Tests for the Lambda class.
 * 
 * @author CS240 Instructors
 * @version 1.0
 *
 */
public class LambdaTest
{

	// --------------------------------------------
	// TESTS FOR BASIC LAMBDA USAGE
	// --------------------------------------------

	@Test
	public void testNumbersNoFunction()
	{

		int[] values = { 5, 0, -1, 3, -2 };
		boolean[] expected = { true, false, false, true, false };
		boolean[] results = Lambda.testNumbers(values);

		for (int i = 0; i < values.length; i++)
		{
			assertEquals(expected[i], results[i], "Results do not match");
		}
	}

	@Test
	public void testDoubler()
	{

		for (int i = 0; i < 10; i++)
		{
			assertEquals(i * 2, Lambda.doubler().modify(i),
					"Number should double");
		}

	}

	@Test
	public void testNToTheN()
	{

		int[] results = { 1, 1, 4, 27, 256, 3125, 46656, 823543, 16777216,
				387420489 };
		for (int i = 0; i < 10; i++)
		{
			assertEquals(results[i], Lambda.nToTheN().modify(i),
					"Number should increase");
		}

	}

	@Test
	public void testStringSplitter()
	{

		String str = "onomatopeia";
		String[] results = { "\"\",\"onomatopeia\"", "\"o\",\"nomatopeia\"",
				"\"on\",\"omatopeia\"", "\"ono\",\"matopeia\"",
				"\"onom\",\"atopeia\"", "\"onoma\",\"topeia\"",
				"\"onomat\",\"opeia\"", "\"onomato\",\"peia\"",
				"\"onomatop\",\"eia\"", "\"onomatope\",\"ia\"",
				"\"onomatopei\",\"a\"", };
		for (int i = 0; i < str.length(); i++)
		{
			String[] strs = Lambda.stringSplitter(str).split(i);
			String result = "\"" + strs[0] + "\",\"" + strs[1] + "\"";
			assertEquals(results[i], result, "Strings to not match");
		}
	}

	// --------------------------------------------
	// TESTS FOR STANDARD FUNCTION INTERFACES
	// --------------------------------------------

	/**
	 * Test for applying a UnaryOperator
	 */
	@Test
	public void testUseOfUnaryOperator()
	{

		Lambda<Integer> lamb = new Lambda<>();
		assertEquals(3, lamb.useUnary(in -> in + 1, 2).intValue());

	}

	/**
	 * Test for making UnaryOperator generic
	 */
	@Test
	public void testUnaryGeneric()
	{

		Lambda<String> lamb = new Lambda<>();
		assertEquals("elp",
				lamb.useUnary(in -> in.substring(1, in.length()), "help"));

		Lambda<Boolean> lambool = new Lambda<>();
		assertTrue(lambool.useUnary(in -> !in, false), "This should flip flop");
		assertFalse(lambool.useUnary(in -> !in, true), "This should flip flop");
	}

	/**
	 * Test for creating an Integer UnaryOperator
	 */
	@Test
	public void testCreateUnaryOperator()
	{

		Lambda<Integer> lamb = new Lambda<>();
		assertEquals(11, lamb.sumOfDigits().apply(137).intValue());
		assertEquals(35, lamb.sumOfDigits().apply(78992).intValue());

	}

	/**
	 * Test for using a BooleanPredicate
	 */
	@Test
	public void testFizzBang()
	{

		Lambda<Integer> lamb = new Lambda<>();
		assertEquals("FIZZ", lamb.fizzBang(n -> n < 10, 5));
		assertEquals("BANG", lamb.fizzBang(n -> n < 10, 15));

	}

	/**
	 * Test for creating a Predicate
	 */
	@Test
	public void testCreatePrimeTest()
	{
		Lambda<Integer> lamb = new Lambda<>();
		assertTrue(lamb.primeTest().test(0), "0 is prime");
		assertTrue(lamb.primeTest().test(1), "1 is prime");
		assertTrue(lamb.primeTest().test(7), "7 is prime");
		assertTrue(lamb.primeTest().test(31), "31 is prime");
		assertFalse(lamb.primeTest().test(30), "30 is not prime");
		assertFalse(lamb.primeTest().test(143), "143 is not prime");
	}

	/**
	 * Test for creating a BiPredicate
	 */
	@Test
	public void testCreateBiPredicate()
	{

		Lambda<String> lamb = new Lambda<>();
		assertTrue(lamb.sumOver100().test(95, "10"));
		assertFalse(lamb.sumOver100().test(80, "13"));
		assertFalse(lamb.sumOver100().test(0, "100"));
		assertThrows(NumberFormatException.class,
				() -> lamb.sumOver100().test(1, "foo"),
				"Cannot call parseInt on \"foo\"");
	}

	/**
	 * Test for creating a BiPredicate with same type
	 */
	@Test
	public void testCommonSubstring()
	{
		Lambda<String> lamb = new Lambda<>();

		assertTrue(lamb.commonSubstring("foo").test("food", "barfood"));
		assertFalse(lamb.commonSubstring("foo").test("food", "doof"));
	}

	/**
	 * Test for creating a Suppler
	 */
	@Test
	public void testPseudoRandom()
	{

		int expected[] = { 92, 0, 75, 98, 63, 10, 93, 13, 56, 14 };

		Lambda<Integer> lamb = new Lambda<>();
		Supplier<Integer> supp = lamb.pseudoRandom(13, 100, 0);
		for (int i = 0; i < 10; i++)
		{
			assertEquals(expected[i], supp.get().intValue(),
					"Expected values to be same");
		}

		Random rand = new Random(25);
		supp = lamb.pseudoRandom(25, 1000, 10);
		for (int i = 0; i < 10; i++)
		{
			assertEquals(10 + rand.nextInt(1000), supp.get().intValue(),
					"Expected values to be same");
		}
	}

}
