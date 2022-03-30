/*
 * OpenDSA Project Distributed under the MIT License
 * 
 * Copyright (c) 2011-2016 - Ville Karavirta and Cliff Shaffer
 *
 * Modifications by Michael S. Kirkpatrick, 2019.
 */
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class BinarySearchTree<E extends Comparable<? super E>> implements SearchTree<E>, Iterable<E> {

  protected SearchNode<E> root; // Root of the BST
  protected int nodecount; // Number of nodes in the BST

  // constructor
  BinarySearchTree() {
    root = null;
    nodecount = 0;
  }

  // Reinitialize tree
  public void clear() {
    root = null;
    nodecount = 0;
  }

  // Update the root of the tree (used for rebalancing and repairing)
  // newRoot: The new root node to use
  public void updateRoot(SearchNode<E> newRoot) {
    root = newRoot;
  }

  // Insert a record into the tree.
  // Records can be anything, but they must be Comparable
  // e: The record to insert.
  public void insert(E e) {
    root = inserthelp(root, new BinarySearchNode<E>(e));
    nodecount++;
  }

  // Traverse until we find the place to insert the new node
  public SearchNode<E> inserthelp(SearchNode<E> rt, SearchNode<E> newNode) {
    if (rt == null)
      return newNode;
    if (rt.element().compareTo(newNode.element()) >= 0)
      rt.setLeft(inserthelp(rt.left(), newNode));
    else
      rt.setRight(inserthelp(rt.right(), newNode));
    if (newNode.parent() == null) {
      newNode.setParent(rt);
    }
    return rt;

  }

  // Return the record with key value k, null if none exists
  // key: The key value to find
  public E find(E key) {
    return findhelp(root, key);
  }

  // Traverse until we find the element to return
  private E findhelp(SearchNode<E> rt, E key) {
    if (rt == null)
      return null;
    if (rt.element().compareTo(key) > 0)
      return findhelp(rt.left(), key);
    if (rt.element().compareTo(key) == 0)
      return rt.element();
    else
      return findhelp(rt.right(), key);

  }

  // Return the number of records in the dictionary
  public int size() {
    return nodecount;
  }

  // Create an in-order iterator of the elements
  @Override
  public Iterator<E> iterator() {
    return new BSTIterator(root);
  }

  private class BSTIterator implements Iterator<E> {

    List<E> elements;

    public BSTIterator(SearchNode<E> root) {
      elements = new LinkedList<>();
      addElementsInOrder(root);
    }

    private void addElementsInOrder(SearchNode<E> root) {
      if (root == null) {
        return;
      }
      if (root.left() != null) { addElementsInOrder(root.left()); }
      elements.add(root.element());
      if (root.right() != null) { addElementsInOrder(root.right()); }
    }
     
    @Override
    public boolean hasNext() {
      return elements.size() > 0;
    }

    @Override
    public E next() {
      if (!hasNext()) {
        throw new IllegalStateException();
      }
      E element = elements.remove(0);
      return element;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }

  }

  // Return a string based on in-order traversal
  public String toString() {
    String s = inOrderString(root);
    return s + "  [Height: " + inOrderHeight(root) + "; Size: " +
      size() + "]";
  }

  private String inOrderString(SearchNode<E> node) {
    if (node == null) {
      return "";
    }
    String s = inOrderString(node.left());
    s += node.toString() + "\n";
    s += inOrderString(node.right());
    return s;
  }

  private int inOrderHeight(SearchNode<E> node) {
    if (node == null) {
      return 0;
    }
    return Math.max(inOrderHeight(node.left()), inOrderHeight(node.right())) + 1;
  }

}

