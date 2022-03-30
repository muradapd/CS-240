import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;
import java.util.Iterator;

import org.junit.Test;

/**
 * Test class for the WeightedList class.
 * 
 * @author Patrick Muradaz
 * 
 *         Based off of DoubleList test class.
 */
public class MyWeightedListTest {

  private String nums = "0123456789";
  private Comparator<String> comp = (String s1, String s2) -> Integer.valueOf(s1)
      - Integer.valueOf(s2);

  public <E> WeightedList<E> makeWeightedList() {
    return new WeightedList<E>();
  }

  /**
   * Fills a list with letters for testing.
   * 
   * @return a list full of letters
   */
  public WeightedList<String> makeNumberList() {
    WeightedList<String> list = makeWeightedList();
    for (int i = 0; i < nums.length(); i++) {
      list.add(nums.substring(i, i + 1), comp);
    }
    return list;
  }

  @Test
  public void testAdd() {
    WeightedList<String> list = makeNumberList();
    assertTrue(list.add("10", comp));
    assertEquals(11, list.size());
    assertTrue(list.add("-1", comp));
    assertEquals("-1", list.retrieve());
  }

  @Test
  public void testRetrieve() {
    WeightedList<String> list = makeNumberList();
    assertEquals("0", list.retrieve());
    list.add("10", comp);
    assertEquals("0", list.retrieve());
  }

  @Test
  public void testIsEmpty() {
    WeightedList<String> list1 = makeWeightedList();
    assertTrue(list1.isEmpty());
    WeightedList<String> list2 = makeNumberList();
    assertFalse(list2.isEmpty());
  }

  @Test
  public void testSize() {
    WeightedList<String> list1 = makeWeightedList();
    assertEquals(0, list1.size());
    WeightedList<String> list2 = makeNumberList();
    assertEquals(10, list2.size());
  }

  @Test(expected = java.util.NoSuchElementException.class)
  public void testNextAfterEnd() {
    WeightedList<String> list = makeNumberList();
    Iterator<String> it = list.iterator();
    while (it.hasNext()) {
      it.next();
    }
    it.next();
  }
}
