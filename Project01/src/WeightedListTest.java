import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

public class WeightedListTest {

  @SuppressWarnings("deprecation")
  @Test
  public void testWeightedListOfIntegers() {
    WeightedList<Integer> list = new WeightedList<>();
    list.add(5, (i1, i2) -> i1.intValue() - i2.intValue());
    list.add(3, (i1, i2) -> i1.intValue() - i2.intValue());
    list.add(7, (i1, i2) -> i1.intValue() - i2.intValue());
    assertEquals(3, list.size(), "Added three items to the list");
    assertEquals(new Integer(3), list.retrieve(), "Added 5 then 3 then 7 should return 3 first");
  }

  @Test
  public void testWeightedListOfStrings() {
    WeightedList<String> list = new WeightedList<>();
    list.add("foo", (s1, s2) -> s1.length() - s2.length());
    list.add("blargh", (s1, s2) -> s1.length() - s2.length());
    list.add("hi", (s1, s2) -> s1.length() - s2.length());
    assertEquals(3, list.size(), "Added three items to the list");
    assertEquals("hi", list.retrieve(), "hi should be first because it is shortest");
  }

  @Test
  public void testWeightedListOfStringsMismatchComp() {
    WeightedList<String> list = new WeightedList<>();
    list.add("foo", (s1, s2) -> s1.length() - s2.length());
    list.add("blargh", (s1, s2) -> s1.length() - s2.length());
    list.add("hi", (s1, s2) -> s1.charAt(0) - s2.charAt(0));
    assertEquals(3, list.size(), "Added three items to the list");
    assertEquals("foo", list.retrieve(), "foo should be first because hi is added at the end");
  }

  @Test
  public void testWithTieBreaker() {
    WeightedList<String> list = new WeightedList<>();
    Comparator<String> comp = (s1, s2) -> {
      if (s1.charAt(0) != s2.charAt(0)) {
        return s1.charAt(0) - s2.charAt(0);
      } else {
        return s1.length() - s2.length();
      }
    };
    list.add("foo", comp);
    list.add("bla", comp);
    list.add("be", comp);
    list.add("fy", comp);
    assertEquals(4, list.size(), "Added four items to the list");
    assertEquals("be", list.retrieve(), "Resulting list is be bla fy foo");
  }

  @Test
  public void testWithTieBreakerAccept() {
    WeightedList<String> list = new WeightedList<>();
    Comparator<String> comp = (s1, s2) -> {
      if (s1.charAt(0) != s2.charAt(0)) {
        return s1.charAt(0) - s2.charAt(0);
      } else {
        return s1.length() - s2.length();
      }
    };
    list.add("foo", comp);
    list.add("bla", comp);
    list.add("be", comp);
    list.add("fy", comp);
    assertEquals(4, list.size(), "Added four items to the list");
    assertEquals("be", list.retrieve(), "Resulting list is be bla fy foo");

    ArrayList<String> alist = new ArrayList<>();
    alist.add("be");
    alist.add("bla");
    alist.add("fy");
    alist.add("foo");

    list.accept(s -> {
      alist.remove(s);
    });
    assertEquals(0, alist.size(), "All of the strings should have been matched");
  }

  @Test
  public void testWithTieBreakerAcceptOrder() {
    WeightedList<String> list = new WeightedList<>();
    Comparator<String> comp = (s1, s2) -> {
      if (s1.charAt(0) != s2.charAt(0)) {
        return s1.charAt(0) - s2.charAt(0);
      } else {
        return s1.length() - s2.length();
      }
    };
    list.add("foo", comp);
    list.add("bla", comp);
    list.add("be", comp);
    list.add("fy", comp);
    assertEquals(4, list.size(), "Added four items to the list");
    assertEquals("be", list.retrieve(), "Resulting list is be bla fy foo");

    ArrayList<String> alist = new ArrayList<>();
    alist.add("be");
    alist.add("bla");
    alist.add("fy");
    alist.add("foo");

    list.accept(s -> {
      assertEquals(alist.get(0), s, "Matched strings were not in the expected order");
      alist.remove(s);
    });
    assertEquals(0, alist.size(), "All of the strings should have been matched");
  }

}
