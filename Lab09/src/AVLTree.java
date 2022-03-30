/**
 * Class that provides the basic insertion algorithm of a self-balancing AVL
 * binary search tree.
 * 
 * @author Michael S. Kirkpatrick
 * @version Fall 2019
 */
public class AVLTree<E extends Comparable<? super E>> extends BinarySearchTree<E> {

  /**
   * Insert a new AVL node, which may require re-balancing the tree.
   *
   * @param elem The element to be inserted into the tree.
   */
  public void insert(E elem) {
    AVLNode<E> newNode = new AVLNode<E>(elem);
    root = inserthelp(root, newNode);
    root = newNode.rebalance();
    nodecount++;
  }

}
