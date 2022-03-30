

/**
 * Operator nodes are the internal nodes of a binary expression tree.
 * 
 * @author Patrick Muradaz
 * @version 10/17/2019
 */
public class OperatorNode extends ExpressionNode {

  private Operator op;

  public OperatorNode(Operator op) {
    super();
    this.op = op;
  }

  public OperatorNode(Operator op, ExpressionNode left, ExpressionNode right) {
    super(left, right);
    this.op = op;
  }

  /**
   * Evaluate the expression rooted at this node and return the result.
   */
  @Override
  public double evaluate() {
    double left = this.left().evaluate();
    double right = this.right().evaluate();
    if (this.op.opString().equals("*")) {
      return left * right;
    } else if (this.op.opString().equals("/")) {
      return left / right;
    } else if (this.op.opString().equals("+")) {
      return left + right;
    } else {
      return left - right;
    }
  }

  @Override
  public String postfixString() {
    String left = this.left().postfixString();
    String right = this.right().postfixString();
    return left + " " + right + " " + this.op.opString();
  }

  @Override
  public String prefixString() {
    String left = this.left().prefixString();
    String right = this.right().prefixString();
    return this.op.opString() + " " + left + " " + right;
  }

  @Override
  public String infixString() {
    String left = this.left().infixString();
    String right = this.right().infixString();
    return "(" + left + " " + this.op.opString() + " " + right + ")";
  }


}
