
/**
 * Enumerated type representing possible binary operators.
 * 
 * @author Nathan Sprague
 * @version 10/2016
 *
 */
public enum Operator {
  ADD("+"), SUBTRACT("-"), MULTIPLY("*"), DIVIDE("/");

  private String opString;

  Operator(String operator) {
    this.opString = operator;
  }

  /**
   * Return the Operator enum that corresponds to the string representation of
   * the operator.
   * 
   * @param operator Should be one of "+", "-", "*", "/"
   * @return The corresponding Operator Enum
   * @throws IllegalArgumentException if the string does not represent a valid
   *         operator
   */
  public static Operator parseOperator(String operator) {
    for (Operator op : Operator.values()) {
      if (op.opString.equals(operator)) {
        return op;
      }
    }
    throw new IllegalArgumentException("Unrecognized operator");
  }

  /**
   * Return the string representation of this operator.
   */
  public String opString() {
    return opString;
  }

}
