/**
 * Mutable tuple for counting an item.
 * 
 * @author Michael S. Kirkpatrick
 * @version V1, 8/2018
 */
public class Counter<T> {

  private final T item;
  private int count;

  /**
   * Create a Counter tuple with an initial count of 1.
   * 
   * @param item The item to count.
   */
  public Counter(T item) {
    this.item = item;
    count = 1;
  }

  /**
   * Returns the element being counted.
   *
   * @return The item.
   */
  public T element() {
    return item;
  }

  /**
   * Returns the current count of the item.
   *
   * @return The count.
   */
  public int count() {
    return count;
  }

  /**
   * Add one to the counter.
   *
   * @return The new count.
   */
  public int increment() {
    count += 1;
    return count;
  }

  /**
   * Subtract one from the counter.
   *
   * @return The new count.
   */
  public int decrement() {
    count -= 1;
    return count;
  }

  /**
   * Returns the String representation of the ordered pair.
   *
   * @return The String produced.
   */
  @Override
  public String toString() {
    return "<" + item.toString() + "," + count + ">";
  }

}
