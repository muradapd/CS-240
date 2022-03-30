import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Generic immutable binary tree node. Although the code does not enforce it,
 * the formed tree should satisfty the binary search tree property: The left
 * sub-tree contains all values that are strictly smaller than the current
 * element; the right sub-tree contains values that are greater than or equal to
 * the current element.
 *
 * @author Michael S. Kirkpatrick
 * @version V1, 9/2018
 */
public class BinTree<E> {
  E element;
  BinTree<E> left;
  BinTree<E> right;

  /**
   * Create a new BinTree that stores the item in the current node.
   *
   * @param item  The element to store in this node
   * @param left  The root of the left sub-tree
   * @param right The root of the right sub-tree
   */
  public BinTree(E item, BinTree<E> left, BinTree<E> right) {
    this.element = item;
    this.left = left;
    this.right = right;
  }

  /**
   * Create a new BinTree that stores the item in the curent node. Sets the right
   * sub-tree to null.
   *
   * @param item The element to store in this node
   * @param left The root of the left sub-tree
   */
  public BinTree(E item, BinTree<E> left) {
    this(item, left, null);
  }

  /**
   * Create a new BinTree that stores the item in the curent node. Sets the both
   * sub-trees to null.
   *
   * @param item The element to store in this node
   */
  public BinTree(E item) {
    this(item, null, null);
  }

  /**
   * Get the BinTree node's element.
   *
   * @return The element stored in this BinTree.
   */
  public E getElement() {
    return element;
  }

  /**
   * Get the left sub-tree.
   *
   * @return The root of the left sub-tree if it exists.
   */
  public BinTree<E> getLeft() {
    return left;
  }

  /**
   * Get the right sub-tree.
   *
   * @return The root of the right sub-tree if it exists.
   */
  public BinTree<E> getRight() {
    return right;
  }

  /**
   * Search through a binary tree for nodes that match a particular criterion. If
   * the passed function returns 0, the current BinTree's element matches the
   * criterion. If the passed function returns a negative value, the current
   * element is too small, and only the right sub-tree needs searched. If the
   * value returned is positive, the current element is too large, and only the
   * left sub-tree needs searched.
   *
   * @param criterion The function specifying the match criterion.
   * @return A list of matching elements from an in-order traversal.
   */
  public List<E> search(Function<E, Integer> criterion) {
    List<E> list = new ArrayList<E>(); // return list

    optimalSearch(this, list, criterion); // enters the recursive search method used to search
    // the optimal trees for the different search criteria

    return list;
  }

  /**
   * Overload for searching for genres.
   * 
   * @param criterion The function specifying the match criterion.
   * @param genre     placeholder to differentiate from default.
   * @return A list of matching elements from an in-order traversal.
   */
  public List<E> search(Function<E, Integer> criterion, String genre) {
    List<E> list = new ArrayList<E>(); // return list

    genreSearch(this, list, criterion); // enters the recursive search method used to search for
    // genres

    return list;
  }

  /**
   * Helper method used for searching the binary tree for genres.
   * 
   * @param tree the tree to search.
   */
  private void genreSearch(BinTree<E> tree, List<E> list, Function<E, Integer> criterion) {
    if (tree == null) { // if we hit a null node
      return;
    }
    genreSearch(tree.left, list, criterion); // search left subtree

    if (criterion.apply(tree.element) == 0) { // if this element fits, add it
      list.add(tree.element);
    }
    genreSearch(tree.right, list, criterion); // search right subtree
  }

  /**
   * Helper method used for searching the binary tree.
   * 
   * @param tree the tree to search.
   */
  private void optimalSearch(BinTree<E> tree, List<E> list, Function<E, Integer> criterion) {
    if (tree == null) {
      return;
    }
    if (criterion.apply(tree.element) == 0) { // if this element fits, add it and search left/right
      genreSearch(tree.left, list, criterion);
      list.add(tree.element);
      genreSearch(tree.right, list, criterion);
    }
    if (criterion.apply(tree.element) < 0) { // if element is less than what we want search right
      genreSearch(tree.right, list, criterion);
    }
    if (criterion.apply(tree.element) > 0) { // if element is greater than what we want search left
      genreSearch(tree.left, list, criterion);
    }
  }
}
