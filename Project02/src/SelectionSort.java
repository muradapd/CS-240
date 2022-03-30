import java.util.Comparator;
import java.util.List;

/**
 * Selection Sort implements the Sorter interface. This sorting method finds the
 * largest item in the array and moves it to the last position. It then moves
 * the last position pointer to one before. It's time complexity is O(n^2) in
 * the average and worst-case.
 * 
 * @author Patrick Muradaz
 * @param <E> element type being sorted
 * 
 *            Acknowledgments: I received help on this problem from OpenDSA.
 */
public class SelectionSort<E> implements Sorter<E> {

  @Override
  public void sort(List<E> list, Comparator<E> comp) {
    E temp;

    for (int i = 0; i < list.size() - 1; i++) { // look through whole list
      int minIndex = i; // starting index of smallest item
      for (int j = i + 1; j < list.size(); j++) { // look through sublist
        if (comp.compare(list.get(j), list.get(minIndex)) < 0) {
          minIndex = j; // update index of smallest item
        }
      }
      temp = list.get(minIndex); // swap - edited for stability
      while (minIndex > i) {
        list.set(minIndex, list.get(minIndex - 1));
        minIndex--;
      }
      list.set(i, temp);
    }
  }
}
