import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * A simplified weighted linked list class. When items are added, they are
 * compared with existing items and those with the highest comparison score are
 * at the front.
 *
 * @author Patrick Muradaz
 * @version V2, 9/2019
 */
class WeightedList<E> implements Iterable<E> {

  private Link<E> head; // Pointer to first node
  private Link<E> tail; // Pointer to last node
  private int listSize;

  /**
   * Constructor for a new weighted list.
   */
  WeightedList() {
    clear();
  }

  /**
   * Removes all of the elements from this list.
   */
  public void clear() {
    tail = new Link<E>(null, null);
    head = new Link<E>(null, tail);
    tail.setPrev(head);
    listSize = 0;
  }

  /**
   * Ensures that this list contains the specified element.
   *
   * @param it   The item to add to the list.
   * @param comp The method for comparing two items.
   *
   * @return Returns true if the item was successfully added.
   */
  public boolean add(E it, Comparator<E> comp) {
    Boolean isAdded = false;
    Link<E> newLink;
    Link<E> nextLink;
    Iterator<E> iter = this.iterator();

    if (listSize == 0) {
      newLink = new Link<>(it, head, head.next());
      head.next().setPrev(newLink);
      head.setNext(newLink);
      listSize++;
      isAdded = true;
    } else {
      while (iter.hasNext() && !isAdded) {
        nextLink = ((WeightedList<E>.ListIterator) iter).nextLink();
        if (comp.compare(it, nextLink.element()) < 0) {
          newLink = new Link<>(it, nextLink.prev(), nextLink);
          nextLink.prev().setNext(newLink);
          nextLink.setPrev(newLink);
          listSize++;
          isAdded = true;
        }
      }

      if (!isAdded) {
        newLink = new Link<>(it, tail.prev(), tail);
        tail.prev().setNext(newLink);
        tail.setPrev(newLink);
        listSize++;
        isAdded = true;
      }
    }
    ((WeightedList<E>.ListIterator) iter).reset();

    return isAdded;
  }

  /**
   * Apply a Consumer lambda to each element in the list.
   *
   * @param cons A consumer lambda that will be applied to each element.
   */
  public void accept(Consumer<E> cons) {
    Iterator<E> iter = this.iterator();

    while (iter.hasNext()) {
      cons.accept(iter.next());
    }
  }

  /**
   * Get the first element of the list.
   *
   * @return The element with the highest weighting.
   */
  public E retrieve() {
    return head.next().element();
  }

  /**
   * Declares whether the list is empty.
   *
   * @return Returns true if this list contains no elements.
   */
  public boolean isEmpty() {
    return listSize == 0;
  }

  /**
   * Gets the number of elements in the list.
   *
   * @return Returns the number of elements in the list.
   */
  public int size() {
    return listSize;
  }

  /**
   * Iterates forward through the list.
   */
  @Override
  public Iterator<E> iterator() {
    return new ListIterator();
  }

  /**
   * Implementation of Iterator.
   * 
   * @author Patrick Muradaz
   *
   */
  private class ListIterator implements Iterator<E> {

    private Link<E> current = head;
    boolean nextCalled = false;

    @Override
    public boolean hasNext() {
      return !current.next().equals(tail);
    }

    @Override
    public E next() {
      E retval = null;

      if (hasNext()) {
        retval = current.next().element();
        current = current.next();
        nextCalled = true;
      } else {
        throw new NoSuchElementException();
      }

      return retval;
    }

    /**
     * Returns the next link.
     * 
     * @return the next link in the linked list.
     */
    public Link<E> nextLink() {
      Link<E> retval = null;

      if (hasNext()) {
        retval = current.next();
        current = current.next();
        nextCalled = true;
      } else {
        throw new NoSuchElementException();
      }

      return retval;
    }

    /**
     * Resets the iterator.
     */
    public void reset() {
      current = head;
    }
  }
}
