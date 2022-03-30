import java.util.Comparator;
import java.util.List;

/**
 * Quick Sort implements the Sorter interface. This sorting method splits the
 * list into sublists. These sublists are then sorted by partitioning. It's time
 * complexity is O(nlogn) in the average-case, O(n^2) in the worst-case.
 * 
 * @author Patrick Muradaz
 * @param <E> element type being sorted
 * 
 *            Acknowledgments: I received help on this problem from OpenDSA.
 */
public class QuickSort<E> implements Sorter<E> {

  @Override
  public void sort(List<E> list, Comparator<E> comp) {
    quickSort(list, 0, list.size() - 1, comp); // enter into the recursive sorting algorithm
  }

  /**
   * Helper method that recursively implements Quick Sort.
   * 
   * @param list  is the list to sort.
   * @param left  is the starting index of the sublist to sort.
   * @param right is the ending index of the sublist to sort.
   * @param comp  is the comparator to compare list items.
   */
  private void quickSort(List<E> list, int left, int right, Comparator<E> comp) {
    int pivotindex = (left + right) / 2; // set pivot to the middle
    swap(list, pivotindex, right); // swap pivot with last element

    int part = partition(list, left, right - 1, list.get(right), comp); // partition
    swap(list, part, right); // swap pivot back into place

    if ((part - left) > 1) {
      quickSort(list, left, part - 1, comp); // recursively sort left sublist
    }
    if ((right - part) > 1) {
      quickSort(list, part + 1, right, comp); // recursively sort right sublist
    }
  }

  /**
   * Helper method that partitions and sorts the items in the provided list.
   * 
   * @param list    is the list to partition.
   * @param left    is the starting index of the sublist.
   * @param right   is the ending index of the sublist.
   * @param element is the Element to compare a list item to.
   * @param comp    is the comparator to compare with.
   * @return the first position in the right partition.
   */
  private int partition(List<E> list, int left, int right, E element, Comparator<E> comp) {

    while (left <= right) { // don't cross the beams
      while (comp.compare(list.get(left), element) < 0) { // stop if element is larger than pivot
        left++; // increment index of element to look at
      }
      while ((right >= left) && (comp.compare(list.get(right), element) >= 0)) {
        // stop if element is smaller than pivot or if beams are crossed
        right--; // decrement index of element to look at
      }
      if (right > left) {
        swap(list, left, right); // swap items if they need to be swapped
      }
    }
    return left; // the index where the pivot should be
  }

  /**
   * Helper method that swaps two list items.
   * 
   * @param list  is the list to swap items in.
   * @param left  is the left item to swap.
   * @param right is the right item to swap.
   */
  private void swap(List<E> list, int left, int right) {
    E temp = list.get(left);
    list.set(left, list.get(right));
    list.set(right, temp);
  }
}
