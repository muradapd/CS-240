public interface SearchTree<E extends Comparable<? super E>> {
  
  // Reinitialize tree
  public void clear();

  public void updateRoot(SearchNode<E> newRoot);

  // Insert a record into the tree.
  // Records can be anything, but they must be Comparable
  // e: The record to insert.
  public void insert(E e);

  // Return the record with key value k, null if none exists
  // key: The key value to find
  public E find(E key);
  
  // Return the number of records in the dictionary
  public int size();

  // Return a string based on in-order traversal
  public String toString();
}
