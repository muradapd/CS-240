import java.util.Comparator;
import java.util.List;

/**
 * The Sorter interface that all objects created by the SorterFactory must
 * implement.
 */
public interface Sorter<E> {

  /**
   * Sort a list based on a provided comparison function. If the function returns
   * a negative value, then the first item is less than the second and should
   * appear earlier in the list. A positive value indicates a larger item.
   * Returning 0 indicates they match.
   *
   * @param list The list of items to sort.
   * @param comp The function for comparing two instances of E.
   */
  public void sort(List<E> list, Comparator<E> comp);

}
