/*
 * OpenDSA Project Distributed under the MIT License
 * 
 * Copyright (c) 2011-2016 - Ville Karavirta and Cliff Shaffer
 *
 * Modifications by Michael S. Kirkpatrick, 2019.
 */

/**
 * Binary tree node implementation: supports comparable objects.
 */ 
public class BinarySearchNode<E extends Comparable<? super E>> implements SearchNode<E> {
  private E element;              // Element for this node
  private SearchNode<E> left;     // Pointer to left child
  private SearchNode<E> right;    // Pointer to right child
  private SearchNode<E> parent;   // Pointer to the parent node

  // Constructors
  BinarySearchNode() {left = right = null; }
  BinarySearchNode(E val) { left = right = null; element = val; }
  BinarySearchNode(E val, SearchNode<E> l, SearchNode<E> r) {
    left = l; right = r; element = val;
    if (l != null) { l.setParent(this); }
    if (r != null) { r.setParent(this); }
  }

  // Get and set the element value
  @Override
  public E element() { return element; }
  @Override
  public void setElement(E v) { element = v; }

  // Get and set the left child
  @Override
  public SearchNode<E> left() { return left; }
  @Override
  public void setLeft(SearchNode<E> node) { left = node; }

  // Get and set the middle child
  @Override
  public SearchNode<E> middle() { throw new UnsupportedOperationException(); }
  @Override
  public void setMiddle(SearchNode<E> node) { throw new UnsupportedOperationException(); }

  // Get and set the right child
  @Override
  public SearchNode<E> right() { return right; }
  @Override
  public void setRight(SearchNode<E> node) { right = node; }

  // Get and set the parent node
  @Override
  public SearchNode<E> parent() { return parent; }
  @Override
  public void setParent(SearchNode<E> node) { parent = node; }

  // return TRUE if a leaf node, FALSE otherwise
  @Override
  public boolean isLeaf() { return (left == null) && (right == null); }

  // Print information about the nodes value, its parent, and its children.
  @Override
  public String toString() {
    String s = this.element() + " [P: ";
    if (this.parent() == null) { s += "-"; } else { s += this.parent().element(); }
    s += "; L: ";
    if (this.left() == null) { s += "-"; } else { s += this.left().element(); }
    s += "; R: ";
    if (this.right() == null) { s += "-"; } else { s += this.right().element(); }
    return s + "]";
  }
  
  /**
   * Perform a single rotation to the right of a tree rooted at the current node.
   * Consider the following illustrations (called on the node A):
   *
   *        A       =>     B
   *       / \      =>    / \
   *      B   T3    =>  T1   A
   *     / \        =>      / \
   *   T1   T2      =>    T2   T3
   *
   * Note that A's original parent (if it exists) will need to become B's new
   * parent. We don't know if A is a left or right child of that node, so be
   * sure to handle both cases. If A was the original root of the tree, its
   * parent would be null.
   *
   * @return The new root of this subtree (node B).
   */
  public BinarySearchNode<E> rotateRight() {
    // Implement this method. First, promote the child B as the new
    // parent. Then, attach the subtree T2 to the new child A. Return the
    // new root B. Do not forget to change A's parent (if it exists) to be
    // aware of B as the new root.
    BinarySearchNode<E> thisParent = (BinarySearchNode<E>) this.parent;
    BinarySearchNode<E> nodeB = (BinarySearchNode<E>) this.left;
    BinarySearchNode<E> sT2 = (BinarySearchNode<E>) nodeB.right;

    nodeB.setRight(this);
    this.setParent(nodeB);
    this.setLeft(sT2);

    if (sT2 != null) {
      sT2.setParent(this);
    }

    if (thisParent != null) {
      nodeB.setParent(thisParent);
      if (thisParent.right() != null) {
        thisParent.setRight(nodeB);
      }
      if (thisParent.left() != null) {
        thisParent.setLeft(nodeB);
      }
    } else {
      nodeB.setParent(null);
    }

    return nodeB;

    // TODO done?
  }

  /**
   * Perform a single rotation to the left of a tree rooted at the current node.
   * Consider the following illustrations (called on the node A):
   *
   *      A         =>       B
   *     / \        =>      / \
   *   T1   B       =>     A   T3
   *       / \      =>    / \
   *     T2   T3    =>  T1   T2
   *
   * Note that A's original parent (if it exists) will need to become B's new
   * parent. We don't know if A is a left or right child of that node, so be
   * sure to handle both cases. If A was the original root of the tree, its
   * parent would be null.
   *
   * @return The new root of this subtree (node B).
   */
  public BinarySearchNode<E> rotateLeft() {
    // Implement this method. First, promote the child B as the new
    // parent. Then, attach the subtree T2 to the new child A. Return the
    // new root B. Do not forget to change A's parent (if it exists) to be
    // aware of B as the new root.
    BinarySearchNode<E> thisParent = (BinarySearchNode<E>) this.parent;
    BinarySearchNode<E> nodeB = (BinarySearchNode<E>) this.right;
    BinarySearchNode<E> sT2 = (BinarySearchNode<E>) nodeB.left;

    nodeB.setLeft(this);
    this.setParent(nodeB);
    this.setRight(sT2);

    if (sT2 != null) {
      sT2.setParent(this);
    }

    if (thisParent != null) {
      nodeB.setParent(thisParent);
      if (thisParent.right() != null) {
        thisParent.setRight(nodeB);
      }
      if (thisParent.left() != null) {
        thisParent.setLeft(nodeB);
      }
    } else {
      nodeB.setParent(null);
    }

    return nodeB;
    // TODO done?
  }
}
