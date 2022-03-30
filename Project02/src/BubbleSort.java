import java.util.Comparator;
import java.util.List;

/**
 * Bubble Sort implements the Sorter interface. This sorting method swaps
 * adjacent items if the left item is greater than the right item. It's time
 * complexity is O(n^2) in the every-case.
 * 
 * @author Patrick Muradaz
 * @param <E> element type being sorted
 * 
 *            Acknowledgments: I received help on this problem from OpenDSA.
 */
public class BubbleSort<E> implements Sorter<E> {

  @Override
  public void sort(List<E> list, Comparator<E> comp) {
    E temp;

    for (int i = 0; i < list.size() - 1; i++) { // check whole list
      for (int j = 0; j < list.size() - i - 1; j++) { // check sublists
        if (comp.compare(list.get(j), list.get(j + 1)) > 0) { // compare elements and switch
          temp = list.get(j);
          list.set(j, list.get(j + 1));
          list.set(j + 1, temp);
        }
      }
    }
  }
}
