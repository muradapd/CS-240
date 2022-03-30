import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Sorting Lab.
 * @author Michael S. Kirkpatrick
 * @version V1, 9/2018
 */
public class SortProfilingTest {

  // BUBBLE SORT TESTS

  @Test
  public void testBubbleSortInteger() {
    Integer input[] = { 9, 5, 6, 7, 2, 8 };
    Integer results[][] = new Integer[5][6];
    int expected[][] = {
      { 5, 6, 7, 2, 8, 9 }, // values after the first loop
      { 5, 6, 2, 7, 8, 9 },
      { 5, 2, 6, 7, 8, 9 },
      { 2, 5, 6, 7, 8, 9 },
      { 2, 5, 6, 7, 8, 9 }
    };
    int expCounts[] = { 15, 8 };

    Sorter<Integer> sorter = new BubbleSorter<>();
    int counts[] = sorter.sort(input, (i1, i2) -> i1 - i2, results);

    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < input.length; j++) {
        assertEquals(expected[i][j], results[i][j].intValue(), "Bubble sort intermediate mismatch");
      }
    }
    assertEquals(expCounts[0], counts[0], "Expected number of bubble sort comparison mismatch");
    assertEquals(expCounts[1], counts[1], "Expected number of bubble sort swap mismatch");
  }

  @Test
  public void testBubbleSortString() {
    String input[] = { "how", "about", "dey", "da", "bears" };
    String results[][] = new String[4][5];
    String expected[][] = {
      { "how", "dey", "da", "about", "bears" }, // values after the first loop
      { "how", "da", "dey", "about", "bears" },
      { "da", "how", "dey", "about", "bears" },
      { "da", "how", "dey", "about", "bears" }
    };
    int expCounts[] = { 10, 4 };

    Sorter<String> sorter = new BubbleSorter<>();
    int counts[] = sorter.sort(input, (s1, s2) -> s1.length() - s2.length(), results);

    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < input.length; j++) {
        assertEquals(expected[i][j], results[i][j], "Bubble sort intermediate mismatch");
      }
    }
    assertEquals(expCounts[0], counts[0], "Expected number of bubble sort comparison mismatch");
    assertEquals(expCounts[1], counts[1], "Expected number of bubble sort swap mismatch");
  }

  // INSERTION SORT TESTS

  @Test
  public void testInsertionSortInteger() {
    Integer input[] = { 9, 5, 6, 7, 2, 8 };
    Integer results[][] = new Integer[5][6];
    int expected[][] = {
      { 5, 9, 6, 7, 2, 8 }, // values after the first loop
      { 5, 6, 9, 7, 2, 8 },
      { 5, 6, 7, 9, 2, 8 },
      { 2, 5, 6, 7, 9, 8 },
      { 2, 5, 6, 7, 8, 9 }
    };
    int expCounts[] = { 11, 8 };

    Sorter<Integer> sorter = new InsertionSorter<>();
    int counts[] = sorter.sort(input, (i1, i2) -> i1 - i2, results);

    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < input.length; j++) {
        assertEquals(expected[i][j], results[i][j].intValue(), "Insertion sort intermediate mismatch");
      }
    }
    assertEquals(expCounts[0], counts[0], "Expected number of insertion sort comparison mismatch");
    assertEquals(expCounts[1], counts[1], "Expected number of insertion sort swap mismatch");
  }

  @Test
  public void testInsertionSortString() {
    String input[] = { "how", "about", "dey", "da", "bears" };
    String results[][] = new String[4][5];
    String expected[][] = {
      { "how", "about", "dey", "da", "bears" }, // values after the first loop
      { "how", "dey", "about", "da", "bears" },
      { "da", "how", "dey", "about", "bears" },
      { "da", "how", "dey", "about", "bears" }
    };
    int expCounts[] = { 7, 4 };

    Sorter<String> sorter = new InsertionSorter<>();
    int counts[] = sorter.sort(input, (s1, s2) -> s1.length() - s2.length(), results);

    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < input.length; j++) {
        assertEquals(expected[i][j], results[i][j], "Insertion sort intermediate mismatch");
      }
    }
    assertEquals(expCounts[0], counts[0], "Expected number of insertion sort comparison mismatch");
    assertEquals(expCounts[1], counts[1], "Expected number of insertion sort swap mismatch");
  }

  // SELECTION SORT TESTS

  @Test
  public void testSelectionSortInteger() {
    Integer input[] = { 9, 5, 6, 7, 2, 8 };
    Integer results[][] = new Integer[5][6];
    int expected[][] = {
      { 2, 5, 6, 7, 9, 8 }, // values after the first loop
      { 2, 5, 6, 7, 9, 8 },
      { 2, 5, 6, 7, 9, 8 },
      { 2, 5, 6, 7, 9, 8 },
      { 2, 5, 6, 7, 8, 9 }
    };
    int expCounts[] = { 15, 2 };

    Sorter<Integer> sorter = new SelectionSorter<>();
    int counts[] = sorter.sort(input, (i1, i2) -> i1 - i2, results);

    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < input.length; j++) {
        assertEquals(expected[i][j], results[i][j].intValue(), "Selection sort intermediate mismatch");
      }
    }
    assertEquals(expCounts[0], counts[0], "Expected number of selection sort comparison mismatch");
    assertEquals(expCounts[1], counts[1], "Expected number of selection sort swap mismatch");
  }

  @Test
  public void testSelectionSortString() {
    String input[] = { "how", "about", "dey", "da", "bears" };
    String results[][] = new String[4][5];
    String expected[][] = {
      { "da", "about", "dey", "how", "bears" }, // values after the first loop
      { "da", "dey", "about", "how", "bears" },
      { "da", "dey", "how", "about", "bears" },
      { "da", "dey", "how", "about", "bears" }
    };
    int expCounts[] = { 10, 3 };

    Sorter<String> sorter = new SelectionSorter<>();
    int counts[] = sorter.sort(input, (s1, s2) -> s1.length() - s2.length(), results);

    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < input.length; j++) {
        assertEquals(expected[i][j], results[i][j], "Selection sort intermediate mismatch");
      }
    }
    assertEquals(expCounts[0], counts[0], "Expected number of selection sort comparison mismatch");
    assertEquals(expCounts[1], counts[1], "Expected number of selection sort swap mismatch");
  }

  // BEST AND WORST CASE TESTS

  @Test
  public void testBubbleSortBestCase() {
    Integer input[] = SortProfiler.bubbleSortBestCase();
    Integer results[][] = new Integer[4][5];
    int expCounts[] = { 10, 0 };

    Sorter<Integer> sorter = new BubbleSorter<>();
    int counts[] = sorter.sort(input, (i1, i2) -> i1 - i2, results);

    for (int i = 1; i < input.length; i++) {
      assertEquals(input[i].intValue(), input[i-1].intValue() + 1, "Bubble sort results are incorrect");
    }
    assertEquals(expCounts[0], counts[0], "Expected number of bubble sort comparison mismatch");
    assertEquals(expCounts[1], counts[1], "Expected number of bubble sort swap mismatch");

  }

  @Test
  public void testBubbleSortWorstCase() {
    Integer input[] = SortProfiler.bubbleSortWorstCase();
    Integer results[][] = new Integer[4][5];
    int expCounts[] = { 10, 10 };

    Sorter<Integer> sorter = new BubbleSorter<>();
    int counts[] = sorter.sort(input, (i1, i2) -> i1 - i2, results);

    for (int i = 1; i < input.length; i++) {
      assertEquals(input[i].intValue(), input[i-1].intValue() + 1, "Bubble sort results are incorrect");
    }
    assertEquals(expCounts[0], counts[0], "Expected number of bubble sort comparison mismatch");
    assertEquals(expCounts[1], counts[1], "Expected number of bubble sort swap mismatch");

  }

  @Test
  public void testInsertionSortBestCase() {
    Integer input[] = SortProfiler.insertionSortBestCase();
    Integer results[][] = new Integer[4][5];
    int expCounts[] = { 4, 0 };

    Sorter<Integer> sorter = new InsertionSorter<>();
    int counts[] = sorter.sort(input, (i1, i2) -> i1 - i2, results);

    for (int i = 1; i < input.length; i++) {
      assertEquals(input[i].intValue(), input[i-1].intValue() + 1, "Insertion sort results are incorrect");
    }
    assertEquals(expCounts[0], counts[0], "Expected number of insertion sort comparison mismatch");
    assertEquals(expCounts[1], counts[1], "Expected number of insertion sort swap mismatch");

  }

  @Test
  public void testInsertionSortWorstCase() {
    Integer input[] = SortProfiler.insertionSortWorstCase();
    Integer results[][] = new Integer[4][5];
    int expCounts[] = { 10, 10 };

    Sorter<Integer> sorter = new InsertionSorter<>();
    int counts[] = sorter.sort(input, (i1, i2) -> i1 - i2, results);

    for (int i = 1; i < input.length; i++) {
      assertEquals(input[i].intValue(), input[i-1].intValue() + 1, "Insertion sort results are incorrect");
    }
    assertEquals(expCounts[0], counts[0], "Expected number of insertion sort comparison mismatch");
    assertEquals(expCounts[1], counts[1], "Expected number of insertion sort swap mismatch");

  }

  @Test
  public void testSelectionSortBestCase() {
    Integer input[] = SortProfiler.selectionSortBestCase();
    Integer results[][] = new Integer[4][5];
    int expCounts[] = { 10, 0 };

    Sorter<Integer> sorter = new SelectionSorter<>();
    int counts[] = sorter.sort(input, (i1, i2) -> i1 - i2, results);

    for (int i = 1; i < input.length; i++) {
      assertEquals(input[i].intValue(), input[i-1].intValue() + 1, "Selection sort results are incorrect");
    }
    assertEquals(expCounts[0], counts[0], "Expected number of selection sort comparison mismatch");
    assertEquals(expCounts[1], counts[1], "Expected number of selection sort swap mismatch");

  }

  @Test
  public void testSelectionSortWorstCase() {
    Integer input[] = SortProfiler.selectionSortWorstCase();
    Integer results[][] = new Integer[4][5];
    int expCounts[] = { 10, 4 };

    Sorter<Integer> sorter = new SelectionSorter<>();
    int counts[] = sorter.sort(input, (i1, i2) -> i1 - i2, results);

    for (int i = 1; i < input.length; i++) {
      assertEquals(input[i].intValue(), input[i-1].intValue() + 1, "Selection sort results are incorrect");
    }
    assertEquals(expCounts[0], counts[0], "Expected number of selection sort comparison mismatch");
    assertEquals(expCounts[1], counts[1], "Expected number of selection sort swap mismatch");

  }

}
