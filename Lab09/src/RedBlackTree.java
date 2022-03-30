/**
 * Class that provides the basic insertion algorithm of a self-balancing
 * red-black binary search tree.
 * 
 * @author Michael S. Kirkpatrick
 * @version Fall 2019
 */
public class RedBlackTree<E extends Comparable<? super E>> extends BinarySearchTree<E> {

  /**
   * Insert a new red-black node, which may require repairing the tree.
   *
   * @param elem The element to be inserted into the tree.
   */
  public void insert(E e) {
    RedBlackNode<E> newNode = new RedBlackNode<E>(e);
    root = inserthelp(root, newNode);
    RedBlackNode<E> grandparent = newNode.repair();
    if (grandparent.parent() == null) {
      root = grandparent;
    }
    nodecount++;
  }
}
