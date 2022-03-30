public class SortProfiler {

  /**
   * Generates an array containing the integers 6, 3, 4, 5, 2 in an
   * order to produce the best-case performance for Bubble Sort.
   *
   * @return An array to cause best-case Bubble Sort.
   */
  public static Integer[] bubbleSortBestCase() {
    Integer input[] = { 2, 3, 4, 5, 6 };
    return input;
  }

  /**
   * Generates an array containing the integers 6, 3, 4, 5, 2 in an
   * order to produce the worst-case performance for Bubble Sort.
   *
   * @return An array to cause worst-case Bubble Sort.
   */
  public static Integer[] bubbleSortWorstCase() {
    Integer input[] = { 6, 5, 4, 3, 2 };
    return input;
  }

  /**
   * Generates an array containing the integers 6, 3, 4, 5, 2 in an
   * order to produce the best-case performance for Insertion Sort.
   *
   * @return An array to cause best-case Insertion Sort.
   */
  public static Integer[] insertionSortBestCase() {
    Integer input[] = { 2, 3, 4, 5, 6 };
    return input;
  }

  /**
   * Generates an array containing the integers 6, 3, 4, 5, 2 in an
   * order to produce the worst-case performance for Insertion Sort.
   *
   * @return An array to cause worst-case Insertion Sort.
   */
  public static Integer[] insertionSortWorstCase() {
    Integer input[] = { 6, 5, 4, 3, 2 };
    return input;
  }

  /**
   * Generates an array containing the integers 6, 3, 4, 5, 2 in an
   * order to produce the best-case performance for Selection Sort.
   *
   * @return An array to cause best-case Selection Sort.
   */
  public static Integer[] selectionSortBestCase() {
    Integer input[] = { 2, 3, 4, 5, 6 };
    return input;
  }

  /**
   * Generates an array containing the integers 6, 3, 4, 5, 2 in an
   * order to produce the worst-case performance for Selection Sort.
   *
   * @return An array to cause worst-case Selection Sort.
   */
  public static Integer[] selectionSortWorstCase() {
    Integer input[] = { 6, 2, 3, 4, 5 };
    return input;
  }

}
