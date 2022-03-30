import java.util.Iterator;
import java.util.Stack;

/**
 * Iterator for the HuffTree class. Implements Iterator interface.
 * 
 * @param <T> is the type that this iterator will handle.
 * 
 * @author Patrick Muradaz
 * @version 11/15/2019
 */
public class HuffTreeIterator<T> implements Iterator<T> {

  private HuffTree tree;
  private HuffTreeNode curr;
  private Stack<HuffTreeNode> parents = new Stack<>();
  int index = 0;

  /**
   * Default Constructor. Sets up iterator's elements.
   * 
   * @param tree is the tree this iterator belongs to.
   */
  public HuffTreeIterator(HuffTree tree) {
    this.tree = tree;
    curr = tree.root;
  }

  @Override
  public boolean hasNext() {
    return index < tree.size;
  }

  @Override
  @SuppressWarnings("unchecked")
  public T next() {
    if (index == 0) {
      index++;
      return (T) curr;
    }
    if (!curr.isLeaf()) {
      parents.push(curr);
      curr = curr.left();
      index++;
      return (T) curr;
    } else {
      index++;
      return recurNext();
    }
  }

  /**
   * Recursive helper for the next() method.
   * 
   * @return the next current node.
   */
  @SuppressWarnings("unchecked")
  private T recurNext() {
    if (curr.isLeft()) {
      curr = curr.parent().right();
    } else {
      curr = parents.pop();
      recurNext();
    }
    return (T) curr;
  }
}
