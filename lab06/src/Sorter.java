import java.util.Comparator;

public interface Sorter<E> {

  /**
   * Sorts an array of generic inputs using a quadratic-time sort algorithm.
   * Uses the Comparator lambda to compare two items; the lambda returns
   * positive values if the first argument is greater than the second,
   * negative if the first argument is less than the second, and 0 if they
   * are equal.
   *
   * After each iteration of the outermost loop, the current status of the
   * array being sorted is copied into a separate row of the results matrix.
   * The return value is a pair of ints that stores the number of item
   * comparisons and the number of swaps that occur.
   *
   * @param input The array being sorted.
   * @param comp A lambda for comparing the items in the array.
   * @param results A reference to a matrix for storing intermediate results.
   *
   * @return An array containing the number of comparisons and the number of
   * swaps that occur.
   */
  public int[] sort(E input[], Comparator<E> comp, E results[][]);

}
