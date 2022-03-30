/**
 * OpenDSA implementation of a singly linked list node.
 *
 * @author OpenDSA - Edited by Patrick Muradaz
 * @version V1, 8/2018
 */
class Link<E> {
  private E element; // Value for this node
  private Link<E> next; // Point to next node in list
  private Link<E> prev; // Pointer to previous node

  /**
   * Create a new node that points to an existing node.
   *
   * @param it  The value to store in this node.
   * @param inp The node to point to prev.
   * @param inn The node to point to next.
   */
  Link(E it, Link<E> inp, Link<E> inn) {
    element = it;
    prev = inp;
    next = inn;
  }

  /**
   * Create a new node with no element.
   *
   * @param inp The node to point to prev.
   * @param inn The node to point to next.
   */
  Link(Link<E> inp, Link<E> inn) {
    element = null;
    prev = inp;
    next = inn;
  }

  /**
   * Getter for the node element.
   *
   * @return The node's current value.
   */
  E element() {
    return element;
  }

  /**
   * Setter for the node element.
   *
   * @param it The node's new value.
   * @return The node's updated value.
   */
  E setElement(E it) {
    return element = it;
  }

  /**
   * Getter for the node's next pointer.
   *
   * @return The node's current next pointer.
   */
  Link<E> next() {
    return next;
  }

  /**
   * Setter for the node's next pointer.
   *
   * @param it The node's new next pointer.
   * @return The node's updated next pointer.
   */
  Link<E> setNext(Link<E> inn) {
    return next = inn;
  }

  /**
   * Getter for the node's previous pointer.
   * 
   * @return The node's current previous pointer.
   */
  Link<E> prev() {
    return prev;
  }

  /**
   * Setter for the node's new next pointer.
   * 
   * @param inp The nodes new prev pointer.
   * @return The nodes updated prev pointer.
   */
  Link<E> setPrev(Link<E> inp) {
    return prev = inp;
  }
}
