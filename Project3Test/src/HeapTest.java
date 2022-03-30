import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

public class HeapTest {

  Integer[] ints;
  String[] strings;
  final int heapSizes = 5;

  @BeforeEach
  public void buildArrays() {
    ints = new Integer[10];
    ints[0] = 1;
    ints[1] = 8;
    ints[2] = 5;
    ints[3] = 2;
    ints[4] = 3;

    strings = new String[10];
    strings[0] = "foo";
    strings[1] = "bar";
    strings[2] = "zoo";
    strings[3] = "food";
    strings[4] = "alpha";
  }

  @Test
  public void testHeapSizeWithGenerics() {

    Heap<Integer> heap = new Heap<>(ints, heapSizes,
        (Integer i1, Integer i2) -> i1.intValue() - i2.intValue());
    assertEquals(heapSizes, heap.size(), "Heap size did not match");
    Heap<String> strHeap = new Heap<>(strings, heapSizes,
        (String s1, String s2) -> s1.compareTo(s2));
    assertEquals(heapSizes, strHeap.size(), "Heap size did not match");

  }

  @Test
  public void testMaxHeapWithGenerics() {

    Integer[] intSorted = Arrays.copyOf(ints, heapSizes);
    Arrays.sort(intSorted);
    String[] strSorted = Arrays.copyOf(strings, heapSizes);
    Arrays.sort(strSorted);

    Heap<Integer> heap = new Heap<>(ints, heapSizes,
        (Integer i1, Integer i2) -> i1.intValue() - i2.intValue());
    for (int i = intSorted.length - 1; i >= 0; i--) {
      assertEquals(intSorted[i], heap.remove(), "Expected maximum value of integer heap");
    }

    Heap<String> strHeap = new Heap<>(strings, heapSizes,
        (String s1, String s2) -> s1.compareTo(s2));
    for (int i = strSorted.length - 1; i >= 0; i--) {
      assertEquals(strSorted[i], strHeap.remove(), "Expected maximum value of string heap");
    }

  }

  @Test
  public void testMinHeapWithGenerics() {

    Integer[] intSorted = Arrays.copyOf(ints, heapSizes);
    Arrays.sort(intSorted);
    String[] strSorted = Arrays.copyOf(strings, heapSizes);
    Arrays.sort(strSorted);

    Heap<Integer> heap = new Heap<>(ints, heapSizes,
        (Integer i1, Integer i2) -> i2.intValue() - i1.intValue());
    for (int i = 0; i < intSorted.length; i++) {
      assertEquals(intSorted[i], heap.remove(), "Expected maximum value of integer heap");
    }

    Heap<String> strHeap = new Heap<>(strings, heapSizes,
        (String s1, String s2) -> s2.compareTo(s1));
    for (int i = 0; i < strSorted.length; i++) {
      assertEquals(strSorted[i], strHeap.remove(), "Expected maximum value of string heap");
    }

  }

  @Test
  public void testMaxHeapInsert() {

    Heap<Integer> heap = new Heap<>(ints, heapSizes,
        (Integer i1, Integer i2) -> i1.intValue() - i2.intValue());
    heap.insert(10);
    assertEquals(10, heap.remove().intValue(), "Expected new largest value inserted");
    heap.insert(0);
    assertEquals(8, heap.remove().intValue(), "Expected original largest value");

    Heap<String> strHeap = new Heap<>(strings, heapSizes,
        (String s1, String s2) -> s1.compareTo(s2));
    strHeap.insert("zygote");
    assertEquals("zygote", strHeap.remove(), "Expected new largest value inserted");
    strHeap.insert("aes");
    assertEquals("zoo", strHeap.remove(), "Expected original largest value");

  }

  @Test
  public void testMinHeapInsert() {

    Heap<Integer> heap = new Heap<>(ints, heapSizes,
        (Integer i1, Integer i2) -> i2.intValue() - i1.intValue());
    heap.insert(10);
    assertEquals(1, heap.remove().intValue(), "Expected new smallest value inserted");
    heap.insert(0);
    assertEquals(0, heap.remove().intValue(), "Expected original smallest value");

    Heap<String> strHeap = new Heap<>(strings, heapSizes,
        (String s1, String s2) -> s2.compareTo(s1));
    strHeap.insert("zygote");
    assertEquals("alpha", strHeap.remove(), "Expected new smallest value inserted");
    strHeap.insert("aes");
    assertEquals("aes", strHeap.remove(), "Expected original smallest value");

  }

}

