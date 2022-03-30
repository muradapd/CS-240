import java.util.Comparator;

public class SelectionSorter<E> implements Sorter<E> {

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

    // Selection sort algorithm (array of length n):
    //   for i := 0 to n-1
    //     minimum := i
    //     for j := i+1 to n-1
    //       if array[j] < array[minimum]
    //         minimum = j
    //     if minimum != i then
    //       swap(array[i], array[minimum])

    for (int i = 0; i < input.length; i++) {

      int minindex = i;
      for (int j = i + 1; j < input.length; j++) {
        comparisons++;
        if (comp.compare(input[j], input[minindex]) < 0) {
          minindex = j;
        }
      }
      if (minindex != i) {
        temp = input[minindex];
        input[minindex] = input[i];
        input[i] = temp;
        swaps++;
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
