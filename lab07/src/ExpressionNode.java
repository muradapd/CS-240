
/**
 * Abstract base class for nodes in a binary tree representing an arithmetic
 * expression.
 * 
 * @author Nathan Sprague
 * @version 10/2016
 */
public abstract class ExpressionNode {

  private ExpressionNode left;
  private ExpressionNode right;

  public ExpressionNode(ExpressionNode left, ExpressionNode right) {
    this.left = left;
    this.right = right;
  }

  public ExpressionNode() {
    this.left = null;
    this.right = null;
  }

  public void setLeft(ExpressionNode left) {
    this.left = left;
  }

  public void setRight(ExpressionNode right) {
    this.right = right;
  }

  public ExpressionNode left() {
    return left;
  }

  public ExpressionNode right() {
    return right;
  }

  // Abstract methods below.

  /**
   * Return the value of the expression tree rooted at this node.
   */
  abstract public double evaluate();

  /**
   * Return a string containing the postfix representation of the expression
   * tree rooted at this node.
   * 
   * For example, the following tree:
   * 
   * <pre>
   *            *
   *           / \
   *          +  2.0
   *         / \
   *       4.0  1.0
   * </pre>
   * 
   * would evaluate to "4.0 1.0 + 2.0 *"
   */
  public abstract String postfixString();

  /**
   * Return a string containing the prefix representation of the expression tree
   * rooted at this node.
   * 
   * For example, the following tree:
   * 
   * <pre>
   *            *
   *           / \
   *          +  2.0
   *         / \
   *       4.0  1.0
   * </pre>
   * 
   * 
   * would evaluate to "* + 4.0 1.0 2.0"
   */
  public abstract String prefixString();

  /**
   * Return a string containing the infix representation of the expression tree
   * rooted at this node.
   * 
   * For example, the following tree:
   * 
   * <pre>
   *            *
   *           / \
   *          +  2.0
   *         / \
   *       4.0  1.0
   * </pre>
   * 
   * would evaluate to ((4.0 + 1.0) * 2.0)"
   */
  public abstract String infixString();


}
