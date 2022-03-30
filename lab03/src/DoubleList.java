import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Doubly-linked list class (UNFINISHED).
 * 
 * @author Patrick Muradaz
 * 
 *         Acknowledgments: Some classmates and I talked through some of the
 *         problems together.
 */
public class DoubleList<E> implements Iterable<E> {

  private Link<E> head; // Pointer to list header
  private Link<E> tail; // Pointer to last node
  private int listSize; // Size of list

  /**
   * Create an empty LList.
   */
  DoubleList() {
    clear();
  }

  /**
   * Remove all elements.
   */
  public void clear() {
    tail = new Link<E>(null, null); // Create trailer
    head = new Link<E>(null, tail); // Create header
    tail.setPrev(head);
    listSize = 0;
  }

  /**
   * Append item to the end of the list.
   */
  public void append(E item) {

    Link<E> newLink = new Link<>(item, tail.prev(), tail);
    tail.prev().setNext(newLink);
    tail.setPrev(newLink);
    listSize++;
  }

  /**
   * Add element at arbitrary index.
   */
  public void add(int index, E item) {
    if (index == listSize) {
      append(item);
    } else {
      Link<E> next = getLink(index);
      Link<E> prev = getLink(index).prev();
      Link<E> add = new Link<E>(item, prev, next);

      prev.setNext(add);
      next.setPrev(add);

      listSize++;
    }
  }

  /**
   * Remove an item at an arbitrary index.
   */
  public E remove(int index) {
    Link<E> current = getLink(index);

    current.prev().setNext(current.next());
    current.next().setPrev(current.prev());
    listSize--;

    return current.element();
  }

  /**
   * Reverse the order of all elements in the list.
   */
  public void reverse() {
    Link<E> hold = null;
    Link<E> current = head.next();

    while (current.element() != null) {
      hold = current.prev();
      current.setPrev(current.next());
      current.setNext(hold);
      current = current.prev();
    }

    hold = head.next();
    head.setNext(tail.prev());
    tail.setPrev(hold);
    tail.setNext(null);
    head.setPrev(null);

    head.next().setPrev(head);
    tail.prev().setNext(tail);
  }

  /**
   * Return the element at the provided index. This method will iterate from the
   * head or the tail depending on which will require fewer steps.
   */
  public E get(int pos) {
    return getLink(pos).element();
  }

  /**
   * Helper method to return the node at a particular index.
   */
  private Link<E> getLink(int pos) {
    if (pos < 0 || pos >= listSize) {
      throw new IndexOutOfBoundsException();
    }

    Link<E> current;

    if (pos < listSize / 2) { // Start from the head.

      current = head.next();
      for (int i = 0; i < pos; i++) {
        current = current.next();
      }

    } else { // Start from the tail.

      current = tail.prev();
      for (int i = 0; i < (listSize - 1) - pos; i++) {
        current = current.prev();
      }
    }

    return current;

  }

  /**
   * Return the number of elements stored in the list.
   */
  public int size() {
    return listSize;
  }

  /**
   * Iterates forward through the list. Remove operation is supported.
   */
  @Override
  public Iterator<E> iterator() {
    return new DoubleIterator();
  }

  private class DoubleIterator implements Iterator<E> {

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

    @Override
    public void remove() {
      if (nextCalled) {
        current.prev().setNext(current.next());
        current.next().setPrev(current.prev());
        listSize--;
        nextCalled = false;
      } else {
        throw new IllegalStateException();
      }
    }

  }

}
