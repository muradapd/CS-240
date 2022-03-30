/**
 * Class for implementing the re-balancing algorithm of a self-balancing
 * Red-black binary search tree.
 * 
 * @author Michael S. Kirkpatrick and ???
 * @version Fall 2019
 */
public class RedBlackNode<E extends Comparable<? super E>> extends BinarySearchNode<E> {
  
  public enum RBColor { RED, BLACK };

  private RBColor color;

  RedBlackNode() { super(); color = RBColor.RED; }
  RedBlackNode(E val) { super(val); color = RBColor.RED; }
  RedBlackNode(E val, RedBlackNode<E> l, RedBlackNode<E> r) {
    super(val, l, r);
    color = RBColor.RED;
  }

  /**
   * Getter for the node's color (red or black).
   */
  public RBColor color() {
    return color;
  }

  /**
   * Setter for the node's color.
   *
   * @param color The new color to set the node to.
   */
  public void setColor(RBColor color) {
    this.color = color;
  }

  /**
   * Repairs a red-black tree after a node insertion.
   *
   * @return The last node repaired.
   */
  public RedBlackNode<E> repair() {
    // Red-black trees have to follow four rules:
    //
    //   1 - The root is black. If this node is the root, set its color and
    //       return this node.
    //   2 - The parent's color is black. Do nothing and return the parent.
    //   3 - The parent's color is red and the uncle is red. No rotations are
    //       needed. Set the parent and uncle to black, set the grandparent to
    //       red, then make a recursive call on the grandparent.
    //   4 - The parent's color is red and the uncle is black (see below).

    // Case 4: Parent is red and uncle is black, so a rotation is needed. First
    // step is to make sure the new node is not on the inside. If it is, do a
    // preliminary rotation on the parent. If the new node is N, rotate the
    // parent based on these two cases:
    //
    //     G    =>      G       OR       G    =>    G
    //    / \   =>     / \      OR      / \   =>   / \
    //   P   U  =>    N   U     OR     U   P  =>  U   N
    //    \     =>   /          OR        /   =>       \
    //     N    =>  P           OR       N    =>        P

    // After making sure the new node is on the outside, rotate the grandparent
    // accordingly. The * indicates the current node and its original parent
    // (which is which depends on whether the rotation above happened).
    //
    //       G    =>    *        OR       G      =>      *
    //      / \   =>   / \       OR      / \     =>     / \
    //     *   U  =>  *   G      OR     U   *    =>    G   *
    //    /       =>       \     OR          \   =>   /
    //   *        =>        U    OR           *  =>  U

    // Finally, adjust colors. Set the original grandparent to red and the
    // new root of this subtree to black. The new root will be either the new
    // node or the new node's parent (depending on whether the first rotation
    // happened). Return the new root of this subtree (no recursion needed).

    RedBlackNode<E> parent = (RedBlackNode<E>) this.parent();

    if (parent == null) {
      this.setColor(RBColor.BLACK);
      return this;
    }

    RedBlackNode<E> uncle = this.uncle(this);
    RedBlackNode<E> grandparent = (RedBlackNode<E>) parent.parent();
    RedBlackNode<E> newRoot = parent;

    if (parent.color().equals(RBColor.BLACK)) {
      return parent;
    }

    if (uncle != null && uncle.color().equals(RBColor.RED)) {
      parent.setColor(RBColor.BLACK);
      uncle.setColor(RBColor.BLACK);
      grandparent.setColor(RBColor.RED);
      grandparent.repair();
    } else {
      if (this.isInsideChild()) {
        if (parent.right() != null && parent.right().equals(this)) {
          parent.rotateLeft();
        } else {
          parent.rotateRight();
        }
        newRoot = this;
      }

      if (grandparent.left().equals(parent)) {
        grandparent.rotateRight();
      } else {
        grandparent.rotateLeft();
      }

      grandparent.setColor(RBColor.RED);
      newRoot.setColor(RBColor.BLACK);
    }
    return newRoot;
    // TODO done?
  }

  /**
   * Helper function to get a node's uncle (which depends on whether the parent
   * is a left or right child of the grandparent).
   *
   * @param parent The current node's parent.
   *
   * @return The uncle node, which is the sibling of the parent.
   */
  private RedBlackNode<E> uncle(RedBlackNode<E> parent) {

    RedBlackNode<E> grandparent = (RedBlackNode<E>) parent.parent();
    if (grandparent == null) {
      return null;
    }

    RedBlackNode<E> left = (RedBlackNode<E>) grandparent.left();
    RedBlackNode<E> right = (RedBlackNode<E>) grandparent.right();
    RedBlackNode<E> uncle = null;

    if (right != null) {
      if (right.equals(this) && left != null) {
        uncle = (RedBlackNode<E>) grandparent.right();
      }
    }
    if (left != null) {
      if (left.equals(this) && right != null) {
        uncle = (RedBlackNode<E>) grandparent.left();
      }
    }

    return uncle;
    // TODO done?
  }

  /**
   * Helper function to get the uncle's color. Note that the uncle might be a
   * null node, so we cannot call the color() method on it.
   *
   * @param uncle The uncle node.
   *
   * @return The color of the uncle node, which is black for null nodes.
   */
  private RBColor uncleColor(RedBlackNode<E> uncle) {

    RBColor UColor = RBColor.BLACK;
    if (uncle != null) {
      UColor = uncle.color();
    }
    return UColor;
    // TODO done?
  }

  /**
   * Helper function to determine if the current node is an inside node. The
   * node is an inside node if it is the left child and its parent is a right
   * child, or if the current node is a right child and its parent is a left
   * child. Otherwise, this is false.
   *
   * @return True if the node is an inside child.
   */
  private boolean isInsideChild() {

    boolean retval = false;
    RedBlackNode<E> parent = (RedBlackNode<E>) this.parent();
    RedBlackNode<E> grandparent = (RedBlackNode<E>) parent.parent();
    RedBlackNode<E> pLeft = (RedBlackNode<E>) parent.left();
    RedBlackNode<E> pRight = (RedBlackNode<E>) parent.right();
    RedBlackNode<E> gpLeft = (RedBlackNode<E>) grandparent.left();
    RedBlackNode<E> gpRight = (RedBlackNode<E>) grandparent.right();

    if (pLeft != null && gpRight != null) {
      if (pLeft.equals(this) && gpRight.equals(parent)) {
        retval = true;
      }
    }
    if (pRight != null && gpLeft != null) {
      if (pRight.equals(this) && gpLeft.equals(parent)) {
        retval = true;
      }
    }

    return retval;
    // TODO done?
  }

  /**
   * Create a string format for debugging. The element is shown first, then
   * additional data about the node's color in brackets.
   *
   * @return A string representation of the node.
   */
  @Override
  public String toString() {
    return super.toString() + " [COLOR: " + (color == RBColor.RED ? "R" : "B") + "]";
  }

}
