/**
 * Immutable 2-tuple type.
 * 
 * @author Nathan Sprague and Michael S. Kirkpatrick
 * @version V2, 1/2018
 */
public class Pair<T, U> {

  private final T first;
  private U second;

  /**
   * Create an Pair with the provided objects.
   * 
   * @param first The first object.
   * @param second The second object.
   */
  public Pair(T first, U second) {
    this.first = first;
    this.second = second;
  }

  public T getFirst() {
    return first;
  }

  public U getSecond() {
    return second;
  }
  
  public void setSecond(U value) {
    second = value;
  }

  @Override
  public String toString() {
    return "<" + first.toString() + ", " + second.toString() + ">";
  }

}
