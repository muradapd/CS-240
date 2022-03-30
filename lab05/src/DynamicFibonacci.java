import java.math.BigInteger;

public class DynamicFibonacci {

  private BigInteger[] bigInts;// YOU WILL NEED AN ARRAY HERE FOR LARGE
                               // VALUES

  /**
   * Entry point to computing Fib(n). In the dynamic programming version, this
   * method will initialize the memoization table used by the helper method below.
   *
   * @param n The index of the Fibonacci number to compute.
   *
   * @return A BigInteger with the nth Fibonacci number. This data type allows
   *         absurdly large calculations like Fib(1000).
   */
  public BigInteger compute(int n) {
    bigInts = new BigInteger[n];
    return computeHelper(n);
  }

  /**
   * Recursive Fibonacci calculation helper. Establish the base cases Fib(0) = 0
   * and Fib(1) = 1, then make necessary recursive calls. For larger values (such
   * as Fib(50) or larger), a dynamic programming memoization table (an array of
   * BigInteger values that stores the smaller results) is necessary.
   *
   * @param n The index of the Fibonacci number to compute. Use Fib(0) = 0 and
   *          Fib(1) = 1 as the base cases.
   *
   * @return A BigInteger with the nth Fibonacci number. This data type allows
   *         absurdly large calculations like Fib(1000).
   */
  public BigInteger computeHelper(int n) {
    if (n == 0) {
      return BigInteger.valueOf(0);
    }
    if (n == 1) {
      return BigInteger.valueOf(1);
    } else {
      if (bigInts[n - 1] == null) {
        bigInts[n - 1] = computeHelper(n - 1);
      }
      if (bigInts[n - 2] == null) {
        bigInts[n - 2] = computeHelper(n - 2);
      }
      return bigInts[n - 1].add(bigInts[n - 2]);
    }
  }

}
