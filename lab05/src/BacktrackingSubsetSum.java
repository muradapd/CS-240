import java.util.ArrayList;
import java.util.List;

public class BacktrackingSubsetSum {

  private List<Integer> set;

  /**
   * Create the full list of integers to consider.
   *
   * @param items The input list of integers.
   */
  public BacktrackingSubsetSum(List<Integer> items) {
    set = items;
  }

  /**
   * Entry point for subset sum calculation. Creates a copy of the list and begins
   * the recursive calculation on it.
   *
   * @param sum The desired sum to find within the set.
   *
   * @return Whether or not a (not necessarily unique) subset was found that adds
   *         up to the given sum.
   */
  public boolean findSum(int sum) {
    List<Integer> subset = new ArrayList<>();
    for (Integer i : set) {
      subset.add(i);
    }
    return subsetSumHelper(subset, sum);
  }

  /**
   * Recursive helper method to compute subset sum. If the intended sum is 0, the
   * empty subset suffices. If the sum is negative or there are no more integers
   * in the subset, then we have not found a solution. Otherwise, we have to deal
   * with the recursive case.
   *
   * In the recursive case, we remove an item (x) from the subset and make two
   * recursive calls: one with the new target (sum - x) and one with the original
   * target (sum). If either succeeds, we have found a solution.
   *
   * (For completeness: If the recursive call with (sum - x) is successful, that
   * means that x was included in the subset to add up to the desired sum; if the
   * recursive call with just (sum) is successful, then a solution was found
   * without x in the subset. SUBTLE POINT: Both of these can be true. Solutions
   * to this problem are not unique. We only care if there is at least one
   * solution.)
   *
   * HINT: The second recursive call cannot use the same List as the first. (Why?)
   *
   * @param subset A subset of integers to check.
   * @param sum    The desired sum to find within the subset.
   *
   * @return Whether or not the desired sum was found.
   */
  public boolean subsetSumHelper(List<Integer> subset, int sum) {
    if (sum == 0) {
      return true;
    }
    if (sum < 0 || subset.size() == 0) {
      return false;
    }
    List<Integer> sublist = new ArrayList<Integer>(subset);
    int num = subset.remove(0);
    sublist.remove(0);

    boolean include = subsetSumHelper(subset, sum - num);

    boolean exclude = subsetSumHelper(sublist, sum);

    return include || exclude;
  }
}
