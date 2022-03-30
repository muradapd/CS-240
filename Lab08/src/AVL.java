/**
 * Simple class to check if a BST satisfies the AVL property.
 * @author Michael S. Kirkpatrick
 * @version 1.0
 */

// Unfinished AVL tree implementation
class AVL<E extends Comparable<E>> extends BST<E> {
  /**
   * Return true if this BST has the AVL property
   */
  public boolean isValidAVL() {
    // You will need two recursive helpers: one to get the height of a
    // subtree, and the other to determine if the subtrees are balanced
    return isBalanced(this.root);
  }

  /**
   * Helper method to determine the height of a given tree or subtree.
   * 
   * @param tree is the tree/subtree to find the height of.
   * @return the height of the tree/subtree.
   */
  private int getHeight(BSTNode<E> tree) {
    if (tree == null) {
      return 0;
    }
    int left = 1 + getHeight(tree.left());
    int right = 1 + getHeight(tree.right());

    if (left > right) {
      return left;
    } else {
      return right;
    }
  }

  /**
   * Helper method to determine if the given tree or subtree is balanced.
   * 
   * @param tree is the tree/subtree to check for balance.
   * @return true if the tree/subtree is balanced, false otherwise.
   */
  private boolean isBalanced(BSTNode<E> tree) {
    if (tree == null) {
    	return true;
    }
	if (Math.abs(getHeight(tree.left()) - getHeight(tree.right())) > 1) {
      return false;
    } else {
			return true && isBalanced(tree.left()) && isBalanced(tree.right());
    }
  }
}
