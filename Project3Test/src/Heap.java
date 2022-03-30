import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Heap implementation. Uses a Comparator to distinguish between min heap and
 * and max heap. This heap acts as an implementation for a priority queue. The
 * heap is used to build a Huffman Tree.
 * 
 * @param <E> is the type of element stored in this heap.
 * 
 * @author Michael Kirkpatrick && Patrick Muradaz
 * @version 11/20/2019
 */

public class Heap<T> {

  T[] elements; // Pointer to the heap array
  private int size; // Number of things now in heap
  private Comparator<T> comp;

  /**
   * Constructor supporting pre-loading of heap contents.
   *
   * @param elements The array of initial items in the heap.
   * @param size     The number of items (could be less than array size).
   */
  public Heap(T[] elements, int size, Comparator<T> comp) {
    this.elements = elements;
    this.size = size;
    this.comp = comp;
    heapify();
  }

  /**
   * Get the current number of items in the heap.
   *
   * @return The current size of the heap
   */
  public int size() {
    return size;
  }

  /**
   * Insert a value into the heap.
   *
   * @param key The value to insert into the heap.
   */
  public void insert(T key) {
    if (size >= elements.length) {
      throw new ArrayIndexOutOfBoundsException();
    }

    int curr = size++;
    elements[curr] = key; // Start at end of heap

    // Now sift up until curr's parent's key >= curr's key
    while ((curr != 0) && comp.compare(elements[curr], elements[parent(curr)]) > -1) {
      swap(elements, curr, parent(curr));
      curr = parent(curr);
    }
  }

  /**
   * Remove and return the extreme value (minimum or maximum) depending on the
   * type of heap.
   *
   * @return The extreme value that was removed.
   */
  T remove() {

    if (size == 0) {
      throw new NoSuchElementException(); // Removing from empty heap
    }

    swap(elements, 0, --size); // Swap maximum with last value

    // Not on last element
    if (size != 0) {
      siftdown(0); // Put new heap root val in correct place
    }

    return elements[size];
  }

  // Determine if the node at a particular position is a leaf
  private boolean isLeaf(int pos) {
    return (pos >= size / 2) && (pos < size);
  }

  // Return position for left child of pos
  private int leftchild(int pos) {
    if (pos >= size / 2) {
      return -1;
    }
    return 2 * pos + 1;
  }

  // Return position for right child of pos
  private int rightchild(int pos) {
    if (pos >= (size - 1) / 2) {
      return -1;
    }
    return 2 * pos + 2;
  }

  // Return position for parent
  private int parent(int pos) {
    if (pos <= 0) {
      return -1;
    }
    return (pos - 1) / 2;
  }

  // Swap two items
  private void swap(T[] items, int i, int j) {
    T tmp = items[i];
    items[i] = items[j];
    items[j] = tmp;
  }

  // heapify contents of elements
  private void heapify() {
    for (int i = size / 2 - 1; i >= 0; i--) {
      siftdown(i);
    }
  }

  // Put element in its correct place
  private void siftdown(int pos) {

    // Illegal position
    if ((pos < 0) || (pos >= size)) {
      return;
    }

    while (!isLeaf(pos)) {

      int j = leftchild(pos);

      // original: elements[j] < elements[j + 1]
      if ((j < (size - 1)) && comp.compare(elements[j], elements[j + 1]) < 0) {
        j++;
      }
      // j is now index of child with greater value

      // if parent is greater than largest child, stop
      if (comp.compare(elements[pos], elements[j]) > 0) {
        return;
      }

      swap(elements, pos, j);
      pos = j; // Move down
    }
  }
}
