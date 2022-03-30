import java.util.List;

/**
 * Builds an immutable binary tree from a sorted list. Building the tree should
 * be accomplished recursively so that an in-order traversal returns the same
 * order of elements. One way to to this is to set the root element to the
 * middle element of that portion of the list. The left sub-tree is then created
 * from all elements preceding the root in the list, while the right sub-tree is
 * the later elements. For example, consider the list 0 1 2 3 4 5 6 7 8. The
 * resulting tree should look as follows:
 *
 * <P>4 / \ 1 6 / \ / \ 0 2 5 7 \ \ 3 8
 */
public class BinTreeBuilder<E> {

  /**
   * Builds the tree from the given list.
   *
   * @param list The list of elements to form the tree with.
   * @return The root of the tree.
   */
  public BinTree<E> build(List<E> list) {

    if (list.size() == 0) { // if the list is empty return null
      return null;
    }

    if (list.size() == 1) { // if the list has one element return that element in the tree
      return new BinTree<E>(list.get(0));
    }

    int split = (list.size() - 1) / 2; // split the list in half
    List<E> subLeft = list.subList(0, split); // left sublist
    List<E> subRight = list.subList(split + 1, list.size()); // right sublist

    BinTree<E> left = build(subLeft); // build left tree
    BinTree<E> right = build(subRight); // build right tree

    return new BinTree<E>(list.get(split), left, right); // final return is the head node
  }

}
