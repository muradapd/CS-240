/*
 * OpenDSA Project Distributed under the MIT License
 * 
 * Copyright (c) 2011-2016 - Ville Karavirta and Cliff Shaffer
 */

class AQueue<E> implements Queue<E> {

  // Keep this non-private for testing purposes!
  E queueArray[]; // Array holding queue elements

  private static final int defaultSize = 10;
  private int front; // Index of front element
  private int rear;
  private int size; // Number of elements stored.

  // Constructors
  @SuppressWarnings("unchecked")
  AQueue(int capacity) {
    front = 0;
    rear = 0;
    size = 0;
    queueArray = (E[]) new Object[capacity];
  }

  AQueue() {
    this(defaultSize);
  }

  // Reinitialize
  @Override
  public void clear() {
    front = 0;
    rear = 0;
    size = 0;
  }

  // Put "it" in queue
  @Override
  public boolean enqueue(E it) {
    Boolean retVal = true;

    if (size == queueArray.length) {
      rear = queueArray.length;
      queueArray = growArray(queueArray);
    }

    queueArray[rear] = it;
    if (rear == queueArray.length - 1) {
      rear = 0;
    } else {
      rear++;
    }
    size++;

    return retVal;
  }

  // Remove and return front value
  @Override
  public E dequeue() {
    E retVal = null;

    if (size != 0) {
      retVal = queueArray[front];
      if (front == queueArray.length - 1) {
        front = 0;
      } else {
        front++;
      }
      size--;
    }
    return retVal;
  }

  // Return front value
  @Override
  public E frontValue() {
    return queueArray[front];
  }

  // Return queue size
  @Override
  public int length() {
    return size;
  }

  // Helper method to grow the array when needed
  private E[] growArray(E[] array) {
    @SuppressWarnings("unchecked")
    E[] retArray = (E[]) new Object[array.length * 2];

    for (int i = 0; i < array.length; i++) {
      retArray[i] = array[i];
    }

    return retArray;
  }
}
