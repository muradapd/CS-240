import java.util.Comparator;
import java.util.List;

/**
 * Insertion Sort implements the Sorter interface. This sorting method inserts
 * each item into its correct position starting at the front of the array and
 * moving right. It's time complexity is O(n^2) in the average and worst-case.
 * 
 * @author Patrick Muradaz
 * @param <E> element type being sorted
 * 
 *            Acknowledgments: I received help on this problem from OpenDSA.
 */
public class InsertionSort<E> implements Sorter<E> {

  @Override
  public void sort(List<E> list, Comparator<E> comp) {
    E temp;

    for (int i = 1; i < list.size(); i++) { // check whole list
      for (int j = i; (j > 0) && comp.compare(list.get(j), list.get(j - 1)) < 0; j--) { // switch
        temp = list.get(j);
        list.set(j, list.get(j - 1));
        list.set(j - 1, temp);
      }
    }
  }
}
