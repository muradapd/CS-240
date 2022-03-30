
public class SBTrees {

  public static void main(String[] args) {

    testRotateLeft();
  }

  /**
   * Test the rotateLeft method.
   */
  public static void testRotateLeft() {
    BinarySearchTree<Integer> bst = new BinarySearchTree<>();

    // build tree
    for (int num : new int[] { 4, 5, 2, 8, 9, 1, 3, 7, 6 }) {
      bst.insert(num);
    }

    System.out.println("Unedited tree: ");
    for (Integer node : bst) {
      System.out.println(node);
    }
  }
}
