import java.util.List;

public class DynamicSubsetSum {

  private int sum;
  private List<Integer> set;
  private boolean[][] memo;

  public DynamicSubsetSum(List<Integer> items) {
    set = items;
  }

  /**
   * Create and fill a dynamic programming table for the given sum. This table
   * progressively builds up the collection of possible sums that can be produced
   * by introducing each element of the set. Each row of the table corresponds to
   * one of the values in the set, and each column corresponds to the sums that
   * can be produced from 0 up to the target sum. Within each row, there are two
   * possibilities to consider:
   *
   * Excluding the current value. In this case, we can produce the same possible
   * sums as the row below.
   *
   * Including the current value. In this case, we can add the current value to
   * each of the sums already produced.
   *
   * To illustrate, consider the set { 8, 6, 7, 5, 3, 9 } and the target sum of
   * 14. We start off with a dummy row (the numbers indicate the column indeces)
   * in which the only possible sum is 0 (because we have not yet added anything):
   *
   * 00 01 02 03 04 05 06 07 08 09 10 11 12 13 14 T F F F F F F F F F F F F F F
   * 
   * Starting at the end of the list, consider adding 9. That means we now have 0
   * and 9 as possible sums:
   *
   * 00 01 02 03 04 05 06 07 08 09 10 11 12 13 14 T F F F F F F F F T F F F F F
   *
   * Next, add 3, which makes 0, 3, 9, and 12 as possible sums:
   *
   * 00 01 02 03 04 05 06 07 08 09 10 11 12 13 14 T F F T F F F F F T F F T F F
   *
   * Eventually for the set above, we would end up with the following final row:
   *
   * 00 01 02 03 04 05 06 07 08 09 10 11 12 13 14 T F F T F F T F T T T T T T T
   *
   * For the final answer, look at the last column in this row. That tells you
   * whether or not there was SOME combination of values that produced that sum.
   *
   * @param sum The desired sum to find in the set.
   *
   * @return Whether or not there is a subset that adds up to the desired sum.
   */
  public boolean fillTable(int sum) {
    this.sum = sum;
    memo = new boolean[set.size() + 1][sum + 1];

    // Starting row: Assume no items have been added. The only possible
    // sum that can be produced is 0.
    memo[set.size()][0] = true;
    for (int t = 1; t < sum + 1; t++) {
      memo[set.size()][t] = false;
    }

    // Now begin the recursion, starting with the last item of the list.
    fillHelper(memo, set.size() - 1);

    return memo[0][sum];
  }

  /**
   * Fill in a row of the dynamic programming table. Copy the previous row, then
   * overwrites values to indicate new sums that are possible.
   *
   * @param memo The memoization table of boolean values that indicate what sums
   *             are possible.
   *
   * @param row  The row within the table under consideration.
   */
  public void fillHelper(boolean[][] memo, int row) {
    // STEP 1: If row is negative, we have reached the base case and the
    // table is completely filled.

    // STEP 2: Get an item from the set, using row as the index. Make sure
    // we do not go beyond the end of the row. This can happen if the item
    // is larger than the current sum that is needed (e.g., the set contains
    // 25 and we are looking for a sum of 10).

    // STEP 3: For each index in the current row, determine what new partial
    // sums can be included (set to true). For instance, assume that we
    // selected 3 from the set and the previous row had true values at
    // indexes 0, 5, 10, and 15. Then the current row should end up with
    // true values at 0, 3, 5, 8, 10, 13, 15, and 18. HINT: Based on the way
    // fillTable is set up, the "previous" row should be row+1.

    // STEP 4: After setting all values, make a recursive call to move to the
    // next row.
    if (row < 0) {
      return;
    }
    int num = set.get(row);

    for (int i = 0; i < memo[row].length; i++) {
      if (memo[row + 1][i]) {
        memo[row][i] = memo[row + 1][i];
      }
      if (memo[row + 1][i] && (i + num) < memo[row].length) {
        memo[row][i + num] = true;
      }
    }
    fillHelper(memo, row - 1);
  }

  /**
   * Print the current structure of the memoization table. Useful for debugging.
   */
  public void printMemo() {
    System.out.print("   ");
    for (int j = 0; j < memo[0].length; j++) {
      System.out.printf("%02d ", j);
    }
    System.out.println("");
    for (int i = 0; i < set.size() + 1; i++) {
      if (i < set.size()) {
        System.out.print(set.get(i) + ": ");
      } else {
        System.out.print(" : ");
      }
      for (int j = 0; j < memo[i].length; j++) {
        if (memo[i][j]) {
          System.out.print(" T ");
        } else {
          System.out.print(" F ");
        }
      }
      System.out.println("");
    }
  }

}
