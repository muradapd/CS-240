/**
 * Huffman tree node implementation. Huffman tree nodes can be internal (with a
 * sum) or a leaf (with an element and a weight).
 * 
 * @author Patrick Muradaz
 * @version 12/02/2019
 */
public class HuffTreeNode {

  private HuffTreeNode parent;
  private HuffTreeNode left;
  private HuffTreeNode right;

  private byte element;
  private int weight;
  private boolean isLeaf;

  /**
   * Default leaf constructor.
   * 
   * @param element is the element of this node.
   * @param weight  is the weight of this node.
   */
  public HuffTreeNode(byte element, int weight) {
    this.parent = null;
    this.element = element;
    this.weight = weight;
    this.isLeaf = true;
  }

  /**
   * Leaf constructor. Creates a leaf node with a particular parent, weight, and
   * element.
   *
   * @param parent  is the parent of this node.
   * @param element The byte to store in the node.
   * @param weight  The weight associated with the node.
   */
  public HuffTreeNode(HuffTreeNode parent, byte element, int weight) {
    this.parent = parent;
    this.element = element;
    this.weight = weight;
    this.isLeaf = true;
  }

  /**
   * Internal node constructor. Creates an internal node given the two children to
   * merge.
   *
   * @param parent is the parent of this node.
   * @param left   The left child node.
   * @param right  The right child node.
   */
  public HuffTreeNode(HuffTreeNode parent, HuffTreeNode left, HuffTreeNode right) {

    this.weight = left.weight() + right.weight();
    this.parent = parent;
    this.left = left;
    this.right = right;
    this.isLeaf = false;
  }

  /**
   * Sets the parent of this node.
   * 
   * @param parent is the new parent of this node;
   */
  public void parentIs(HuffTreeNode parent) {
    this.parent = parent;
  }

  /**
   * Getter for the parent of this node.
   * 
   * @return the parent of this node.
   */
  public HuffTreeNode parent() {
    return parent;
  }

  /**
   * Getter for element.
   *
   * @return The node's element stored.
   */
  public byte element() {
    return element;
  }

  /**
   * Getter for weight.
   *
   * @return The node's weight stored.
   */
  public int weight() {
    return weight;
  }

  /**
   * Getter for this node's left node.
   *
   * @return The node's left child.
   */
  public HuffTreeNode left() {
    return left;
  }

  /**
   * Getter for this node's right node.
   *
   * @return The node's right child.
   */
  public HuffTreeNode right() {
    return right;
  }

  /**
   * Used to tell if this node is a left child.
   * 
   * @return true if this node is a left child.
   */
  public boolean isLeft() {
    return this.equals(parent.left());
  }
  
  /**
   * Returns the height of this subtree.
   * 
   * @return the height of the subtree.
   */
  public int height() {
    if (this.isLeaf()) {
      return 1;
    } else {
      int leftHeight = this.left().height();
      int rightHeight = this.right().height();
      
      if (leftHeight > rightHeight) {
        return leftHeight + 1;
      } else {
        return rightHeight + 1;
      }
    }
  }
  
  /**
   * Returns the smallest element on this subtree.
   * 
   * @param min is the smallest element on this subtree.
   * @return min after running through all elements.
   */
  public int smallest(int min) {
    if (this.element < min) {
      min = this.element;
    }
    if (this.isLeaf()) {
      return min;
    } else {
      this.left().smallest(min);
      this.right().smallest(min);
      return min;
    }
  }

  /**
   * Getter for leaf status of this node.
   *
   * @return Whether or not the node is a leaf.
   */
  public boolean isLeaf() {
    return isLeaf;
  }
}
