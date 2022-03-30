import java.util.Comparator;

public class BubbleSorter<E> implements Sorter<E> {

  /**
   * Sorts an array of generic inputs using the bubble sort algorithm.
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

    for (int i = 0; i < input.length - 1; i++) {
      for (int j = 1; j < input.length - i; j++) {
        comparisons++;
        if (comp.compare(input[j - 1], input[j]) > 0) {
          temp = input[j - 1];
          input[j - 1] = input[j];
          input[j] = temp;
          swaps++;
        }
      }

      // Lab functionality only: after each iteration of the outer loop,
      // copy the current status into a row of the results matrix to
      // show the progress.
      if (i < input.length - 1) {
        for (int k = 0; k < input.length; k++) {
          results[i][k] = input[k];
        }
      }
    }

    int counts[] = { comparisons, swaps };
    return counts;

  }

}
