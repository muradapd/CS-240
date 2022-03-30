/*
 * OpenDSA Project Distributed under the MIT License
 * 
 * Copyright (c) 2011-2016 - Ville Karavirta and Cliff Shaffer
 */

public interface Queue<E> { // Queue class ADT
  // Reinitialize queue
  public void clear();

  // Put element on rear
  public boolean enqueue(E it);

  // Remove and return element from front
  public E dequeue();

  // Return front element
  public E frontValue();

  // Return queue size
  public int length();
}
