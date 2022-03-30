import java.util.Comparator;

public class InsertionSorter<E> implements Sorter<E> {

  /**
   * Sorts an array of generic inputs using the insertion sort algorithm.
   *
   * @param input The array being sorted.
   * @param comp A lambda for comparing the items in the array.
   * @param results A reference to a matrix for storing intermediate results.
   *
   * @return An array containing the number of comparisons and the number of
   * swaps that occur.
   */
  @Override
  public int[] sort(E input[], Comparator<E> comp, E results[][]) {

    int comparisons = 0;
    int swaps = 0;
    E temp;

    // Insertion sort algorithm (array of length n):
    //   for i := 1 to n-1
    //     j := i
    //     while j > 0 and array[j-1] > array[j]
    //       swap(array[j-1], array[j])

    for (int i = 1; i < input.length; i++) {
      int j;
      for (j = i; (j > 0) && comp.compare(input[j], input[j - 1]) < 0; j--) {
        temp = input[j];
        input[j] = input[j - 1];
        input[j - 1] = temp;
        swaps++;
        comparisons++;
      }
      if (j > 0) {
        comparisons++;
      }
      /**
       * Pretty sure insertion sort string is wrong.
       */

      // Lab functionality only: after each iteration of the outer loop,
      // copy the current status into a row of the results matrix to
      // show the progress.
      if (i < input.length) {
        for (int k = 0; k < input.length; k++) {
          results[i - 1][k] = input[k];
        }
      }
    }

    int counts[] = { comparisons, swaps };
    return counts;

  }

}
