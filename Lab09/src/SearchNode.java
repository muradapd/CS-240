/*
 * OpenDSA Project Distributed under the MIT License
 * 
 * Copyright (c) 2011-2016 - Ville Karavirta and Cliff Shaffer
 */

public interface SearchNode<E extends Comparable<? super E>> {
  // Get and set the element value
  public E element();
  public void setElement(E value);

  // Get and set the left child
  public SearchNode<E> left();
  public void setLeft(SearchNode<E> node);

  // Get and set a middle child (optional)
  public SearchNode<E> middle();
  public void setMiddle(SearchNode<E> node);

  // Get and set the right child
  public SearchNode<E> right();
  public void setRight(SearchNode<E> node);

  // Get and set the parent node
  public SearchNode<E> parent();
  public void setParent(SearchNode<E> node);

  // return TRUE if a leaf node, FALSE otherwise
  public boolean isLeaf();

  // Convert the node to a string representation
  public String toString();
}

