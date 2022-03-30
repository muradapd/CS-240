import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the AVL lab
 * @author Michael S. Kirkpatrick
 *
 */
public class AVLTest {

  private void checkStructureAndValues(AVLNode<Integer> parent, int parentValue, AVLNode<Integer> child, int childValue, boolean left) {
    assertEquals(parent.element().intValue(), parentValue, "Parent value was not correct");
    if (left) {
      assertEquals(parent.left(), child, "Parent " + parent + " should have left child " + child);
    } else {
      assertEquals(parent.right(), child, "Parent " + parent + " should have right child " + child);
    }
    assertEquals(child.parent(), parent, "Child " + child + " should have parent " + parent);
    assertEquals(child.element().intValue(), childValue, "Child value was not correct");
  }

  private void nodeIsLeaf(AVLNode<Integer> node) {
    assertNull(node.left(), "Node " + node + " should have a null left child");
    assertNull(node.right(), "Node " + node + " should have a null right child");
  }

  /**
   * Test rebalancing a tree of only one node.
   */
  @Test
  public void testRootOnlyRebalance() {
    AVLNode<Integer> root = new AVLNode<>(5);
    AVLNode<Integer> returned = root.rebalance();
    assertEquals(returned, root, "Returned value should be the root");
    assertNull(returned.left(), "Rebalancing root should not change its children");
    assertNull(returned.right(), "Rebalancing root should not change its children");
    nodeIsLeaf(root);
  }

  /**
   * Test rebalancing two nodes (should not change).
   */
  @Test
  public void testRebalanceTwoNodes() {
    AVLNode<Integer> leaf = new AVLNode<>(3);
    AVLNode<Integer> root = new AVLNode<>(5, leaf, null);
    AVLNode<Integer> returned = root.rebalance();
    checkStructureAndValues(returned, 5, leaf, 3, true);
    nodeIsLeaf(leaf);

    root = new AVLNode<>(5, null, leaf);
    returned = root.rebalance();
    checkStructureAndValues(returned, 5, leaf, 3, false);
    nodeIsLeaf(leaf);

  }

  /**
   * Test outside leaves (single rotation of root).
   *
   *     R  OR  R
   *    /   OR   \
   *   M    OR    M
   *  /     OR     \
   * L      OR      L
   */
  @Test
  public void testOutsideRotations() {
    AVLNode<Integer> leaf = new AVLNode<>(3);
    AVLNode<Integer> middle = new AVLNode<>(4, leaf, null);
    AVLNode<Integer> root = new AVLNode<>(5, middle, null);
    AVLNode<Integer> newRoot = leaf.rebalance();
    checkStructureAndValues(newRoot, 4, leaf, 3, true);
    nodeIsLeaf(leaf);
    checkStructureAndValues(newRoot, 4, root, 5, false);
    nodeIsLeaf(root);

    leaf = new AVLNode<>(3);
    middle = new AVLNode<>(2, null, leaf);
    root = new AVLNode<>(1, null, middle);
    newRoot = leaf.rebalance();
    checkStructureAndValues(newRoot, 2, root, 1, true);
    nodeIsLeaf(leaf);
    checkStructureAndValues(newRoot, 2, leaf, 3, false);
    nodeIsLeaf(root);
  }

  /**
   * Test inside leaves (double rotation of root).
   *
   *     R    OR  R
   *    /     OR   \
   *   M      OR    M
   *    \     OR   /
   *     L    OR  L
   */
  @Test
  public void testInsideDoubleRotations() {
    AVLNode<Integer> leaf = new AVLNode<>(4);
    AVLNode<Integer> middle = new AVLNode<>(3, null, leaf);
    AVLNode<Integer> root = new AVLNode<>(5, middle, null);
    AVLNode<Integer> newRoot = leaf.rebalance();
    checkStructureAndValues(newRoot, 4, middle, 3, true);
    nodeIsLeaf(middle);
    checkStructureAndValues(newRoot, 4, root, 5, false);
    nodeIsLeaf(root);

    leaf = new AVLNode<>(4);
    middle = new AVLNode<>(5, leaf, null);
    root = new AVLNode<>(3, null, middle);
    newRoot = leaf.rebalance();
    checkStructureAndValues(newRoot, 4, middle, 5, false);
    nodeIsLeaf(middle);
    checkStructureAndValues(newRoot, 4, root, 3, true);
    nodeIsLeaf(root);
  }

  /**
   * Test full operation of AVL tree.
   */
  @Test
  public void testAVLTreeInsertions() {
    AVLTree<Integer> tree = new AVLTree<>();
    tree.insert(9);
    tree.insert(8);
    tree.insert(1);
    // Tree should now have 5 as root
    tree.insert(3);
    tree.insert(4);
    //            8
    //           / \
    //          3   9
    //         / \
    //        1   4
    tree.insert(6);
    //             8              8              4
    //           /   \           / \            / \
    //          3     9         4   9          3   8
    //         / \             / \            /   / \
    //        1   4           3   6          1   6   9
    //             \         /
    //              6       1
    tree.insert(5);
    //            4
    //           / \
    //          3   8
    //         /   / \
    //        1   6   9 
    //           /
    //          5
    tree.insert(7);
    int prev = 0;
    for (Integer i : tree) {
      assertTrue(i > prev, "Final tree should be in increasing order");
    }
  }

}
