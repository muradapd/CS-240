import java.util.Stack;

/**
 * Class for parsing strings that encode mathematical expressions in prefix
 * format.
 * 
 * @author Patrick Muradaz
 * @version 10/17/19
 *
 */
public class PrefixParser {

  private Stack<ExpressionNode> stack;

  public PrefixParser() {
  
    stack = new Stack<>(); 

  }

  /**
   * This method takes a string encoding a prefix expression and returns the
   * corresponding expression tree.
   * 
   * For example, the string "* + 4.0 1.0 2.0", will result in the tree:
   * 
   * <pre>
   *            *
   *           / \
   *          +  2.0
   *         / \
   *       4.0  1.0
   * </pre>
   * 
   * @param <E>
   */
  public <E> ExpressionNode parseExpression(String expression) {
    String[] tokens = expression.split(" ");
    ExpressionNode head = null;
    
    for (int i = 0; i < tokens.length; i++) {
      try {
        
        double operand = Double.parseDouble(tokens[i]);
        System.out.println("The token " + operand + " is an operand!");
        if (stack.peek().getClass().isInstance(new OperandNode(operand))) {
          stack.push(new OperandNode(operand));
          ExpressionNode right = stack.pop();
          ExpressionNode left = stack.pop();
          ExpressionNode root = stack.pop();
          root.setLeft(left);
          root.setRight(right);
          stack.push(root);
        } else {
          stack.push(new OperandNode(operand));
        }
        if (i == tokens.length - 1 && stack.size() > 1) {
          ExpressionNode right = stack.pop();
          ExpressionNode left = stack.pop();
          head = stack.pop();
          head.setLeft(left);
          head.setRight(right);
        } else if (i == tokens.length - 1) {
          head = stack.pop();
        }

      } catch (NumberFormatException e) {

        Operator op = Operator.parseOperator(tokens[i]);
        System.out.println("The token " + op.opString() + " is an operator!");
        stack.push(new OperatorNode(op));
      }
    }
    return head;
  }

}
