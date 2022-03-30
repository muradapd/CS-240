/*
 * OpenDSA Project Distributed under the MIT License
 * 
 * Copyright (c) 2011-2016 - Ville Karavirta and Cliff Shaffer
 */

// Unfinished Binary Search Tree implementation
class BST<E extends Comparable<E>> {
  protected BSTNode<E> root; // Root of the BST
  protected int nodecount; // Number of nodes in the BST

  // constructor
  BST() {
    root = null;
    nodecount = 0;
  }

  // Reinitialize tree
  public void clear() {
    root = null;
    nodecount = 0;
  }

  // Insert a record into the tree.
  // Records can be anything, but they must be Comparable
  // e: The record to insert.
  public void insert(E e) {
    root = inserthelp(root, e);
    nodecount++;
  }

  private BSTNode<E> inserthelp(BSTNode<E> rt, E e) {
    if (rt == null)
      return new BSTNode<E>(e);
    if (rt.element().compareTo(e) >= 0)
      rt.setLeft(inserthelp(rt.left(), e));
    else
      rt.setRight(inserthelp(rt.right(), e));
    return rt;

  }


  // Return the record with key value k, null if none exists
  // key: The key value to find
  public E find(E key) {
    return findhelp(root, key);
  }

  private E findhelp(BSTNode<E> rt, E key) {
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


  // UNFINISHED METHODS BELOW THIS POINT**************************

  /**
   * Recursively calculate the number of nodes in this BST.
   */
  public int recursiveSize() {
    return sizeHelp(this.root);
  }

  /**
   * Helper method for recursiveSize().
   * 
   * @param tree is the tree to find the size of.
   * @return the number of nodes in the tree.
   */
  private int sizeHelp(BSTNode<E> tree) {
    if (tree == null) {
      return 0;
    }
    int left = sizeHelp(tree.left());
    int right = sizeHelp(tree.right());
    return 1 + left + right;
  }

  /**
   * Return the smallest element in the tree.
   */
  public E minElement() {
    return minHelp(this.root);
  }

  /**
   * Helper method for minElement().
   * 
   * @param tree is the tree to find the min in.
   * @param min  is the current min of the tree.
   * @return the final min of the tree.
   */
  private E minHelp(BSTNode<E> tree) {
    if (tree == null) {
      return null;
    }
    if (tree.left() != null && tree.left().element().compareTo(tree.element()) < 0) {
      return minHelp(tree.left());
    }
    if (tree.right() != null && tree.right().element().compareTo(tree.element()) < 0) {
      return minHelp(tree.right());
    }
    return tree.element();
  }

  /**
   * Return the largest element in the tree.
   */
  public E maxElement() {
    return maxHelp(this.root);
  }

  /**
   * Helper method for maxElement().
   * 
   * @param tree is the tree to find the min in.
   * @param min  is the current min of the tree.
   * @return the final min of the tree.
   */
  private E maxHelp(BSTNode<E> tree) {
    if (tree == null) {
      return null;
    }
    if (tree.left() != null && tree.left().element().compareTo(tree.element()) > 0) {
      return maxHelp(tree.left());
    }
    if (tree.right() != null && tree.right().element().compareTo(tree.element()) > 0) {
      return maxHelp(tree.right());
    }
    return tree.element();
  }

  /**
   * Return an ordered linked list containing all of the elements from the tree.
   */
  public LList<E> makeOrderedList() {
    LList<E> list = new LList<E>();
    return makeListHelp(this.root, list);
  }

  /**
   * Helper method for makeOrderedList().
   * 
   * @param tree is the tree to traverse to make the list.
   * @param list is the list to create.
   * @return the created list.
   */
  private LList<E> makeListHelp(BSTNode<E> tree, LList<E> list) {
    if (tree == null) {
      return null;
    }
    makeListHelp(tree.left(), list);
    list.append(tree.element());
    makeListHelp(tree.right(), list);
    return list;
  }

  /**
   * Return true if this BST actually has the BST property and false if it does
   * not.
   * 
   * (Note that this method would typically not be necessary. It a correctly
   * coded BST there will be no possibility of violating the BST property.)
   * 
   */
	@SuppressWarnings("unchecked")
	public boolean isValidBST()
	{
		return validHelper((BSTNode<Integer>) this.root, Integer.MIN_VALUE,
				Integer.MAX_VALUE);
  }

  /**
   * Helper method for isValidBST().
   * 
   * @param tree is the tree to validate.
   * @param min  is the minimum key value the tree can have.
   * @param max  is the maximum key value the tree can have.
   * @return true if this is a valid BST, false otherwise.
   */
	private boolean validHelper(BSTNode<Integer> tree, int min, int max)
	{
    if (tree == null) {
      return true;
    }
		if (tree.element() < min || tree.element() > max)
		{
      return false;
    }
		return validHelper(tree.left(), min, tree.element() - 1)
				&& validHelper(tree.right(), tree.element() + 1, max);
  }
}

