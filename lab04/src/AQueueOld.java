/*
 * OpenDSA Project Distributed under the MIT License
 * 
 * Copyright (c) 2011-2016 - Ville Karavirta and Cliff Shaffer
 */

class AQueueOld<E> implements Queue<E> {
  E queueArray[]; // Array holding queue elements
  private static final int defaultSize = 10;
  private int maxSize; // Maximum size of queue
  private int front; // Index of front element
  private int rear; // Index of rear element

  // Constructors
  @SuppressWarnings("unchecked") // Generic array allocation
  AQueueOld(int size) {
    maxSize = size + 1; // One extra space is allocated
    rear = 0;
    front = 1;
    queueArray = (E[]) new Object[maxSize]; // Create queueArray
  }

  AQueueOld() {
    this(defaultSize);
  }

  // Reinitialize
  public void clear() {
    rear = 0;
    front = 1;
  }

  // Put "it" in queue
  public boolean enqueue(E it) {
    if (((rear + 2) % maxSize) == front)
      return false; // Full
    rear = (rear + 1) % maxSize; // Circular increment
    queueArray[rear] = it;
    return true;
  }

  // Remove and return front value
  public E dequeue() {
    if (length() == 0)
      return null;
    E it = queueArray[front];
    front = (front + 1) % maxSize; // Circular increment
    return it;
  }

  // Return front value
  public E frontValue() {
    if (length() == 0)
      return null;
    return queueArray[front];
  }

  // Return queue size
  public int length() {
    return ((rear + maxSize) - front + 1) % maxSize;
  }
}

