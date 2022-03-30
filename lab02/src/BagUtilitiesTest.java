import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class BagUtilitiesTest
{

	private Bag<String> buildStringBag()
	{
		Bag<String> bag = new Bag<>();
		for (int i = 0; i < 3; i++)
		{
			bag.add("apple");
		}
		for (int i = 0; i < 5; i++)
		{
			bag.add("b");
		}
		bag.add("cherry");
		for (int i = 0; i < 7; i++)
		{
			bag.add("apple");
		}
		return bag;
	}

	@Test
	public void testBagContains()
	{
		Bag<String> bag = buildStringBag();
		BagUtilities<String> utils = new BagUtilities<>();
		assertTrue(utils.contains(bag).test("b"));
		assertFalse(utils.contains(bag).test("d"));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testBagCount()
	{
		Bag<String> bag = buildStringBag();
		BagUtilities<String> utils = new BagUtilities<>();
		assertEquals(new Integer(10), utils.count(bag).apply("apple"),
				"There should be 10 copies of \"apple\"");
		assertEquals(new Integer(5), utils.count(bag).apply("b"),
				"There should be 5 copies of \"b\"");
		assertEquals(new Integer(1), utils.count(bag).apply("cherry"),
				"There should be 1 copies of \"cherry\"");
	}

	@Test
	public void testSumIfNotB()
	{
		Bag<String> bag = buildStringBag();
		BagUtilities<String> utils = new BagUtilities<>();
		assertEquals(11,
				utils.sumIf(bag,
						it -> it.equals("apple") || it.equals("cherry")),
				"There should be 11 \"apple\" and \"cherry\"");
	}

	@Test
	public void testSearchForString()
	{
		Bag<String> bag = buildStringBag();
		BagUtilities<String> utils = new BagUtilities<>();
		List<Counter<String>> list = utils.search(bag,
				(s, n) -> s.length() > 1);

		for (Counter<String> c : list)
		{
			assertTrue(c.element().equals("apple")
					|| c.element().equals("cherry"));
			if (c.element().equals("apple"))
			{
				assertEquals(10, c.count(), "Should be 10 apples");
			} else
			{
				assertEquals(1, c.count(), "Should be 1 cherry");
			}
		}
	}

	@Test
	public void testSearchForNumber()
	{
		Bag<String> bag = buildStringBag();
		BagUtilities<String> utils = new BagUtilities<>();
		List<Counter<String>> list = utils.search(bag,
				(s, n) -> n > 1 && n < 10);

		assertEquals(1, list.size(), "Should only find 1 Counter");
		assertEquals("b", list.get(0).element(), "Should only find \"b\"");
		assertEquals(5, list.get(0).count(), "Should have 5 copies");
	}

	@Test
	public void testComplexSearch()
	{
		Bag<String> bag = buildStringBag();
		BagUtilities<String> utils = new BagUtilities<>();
		List<Counter<String>> list = utils.search(bag,
				(s, n) -> n > 1 && !s.contains("b"));

		assertEquals(1, list.size(), "Should only find 1 Counter");
		assertEquals("apple", list.get(0).element(),
				"Should only find \"apple\"");
		assertEquals(10, list.get(0).count(), "Should have 10 copies");
	}

}
