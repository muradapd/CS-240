/**
 * Factory pattern implementation for creating an object that implements one of
 * the sorting algorithms. Users should create an instance of this class, then
 * invoke buildSorter on it with the desired algorithm name.
 */
public class SortFactory<E> {

  /**
   * Create an object that implements a sorting algorithm.
   *
   * @param algorithm The name of the algorithm to implement.
   * @return The object that sorts items.
   */
  public Sorter<E> buildSorter(String algorithm) {
    switch (algorithm) {
      case "Bubble":
      case "bubble":
        return new BubbleSort<E>();
      case "Insertion":
      case "insertion":
        return new InsertionSort<E>();
      case "Merge":
      case "merge":
        return new MergeSort<E>();
      case "Quick":
      case "quick":
        return new QuickSort<E>();
      case "Selection":
      case "selection":
        return new SelectionSort<E>();
      default:
        return null;
    }
  }
}
