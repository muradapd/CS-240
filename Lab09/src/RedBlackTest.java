import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Red-Black lab
 * @author Michael S. Kirkpatrick
 *
 */
public class RedBlackTest {

  private void checkStructureAndValues(RedBlackNode<Integer> parent, int parentValue, RedBlackNode<Integer> child, int childValue, boolean left) {
    assertEquals(parent.element().intValue(), parentValue, "Parent value was not correct");
    if (left) {
      assertEquals(parent.left(), child, "Parent " + parent + " should have left child " + child);
    } else {
      assertEquals(parent.right(), child, "Parent " + parent + " should have right child " + child);
    }
    assertEquals(child.parent(), parent, "Child " + child + " should have parent " + parent);
    assertEquals(child.element().intValue(), childValue, "Child value was not correct");
  }

  private void nodeIsLeaf(RedBlackNode<Integer> node) {
    assertNull(node.left(), "Node " + node + " should have a null left child");
    assertNull(node.right(), "Node " + node + " should have a null right child");
  }

  /**
   * Test rebalancing a tree of only one node.
   */
  @Test
  public void testRootOnlyRepair() {
    RedBlackNode<Integer> root = new RedBlackNode<>(5);
    assertEquals(root.color(), RedBlackNode.RBColor.RED, "New nodes should be created as RED");
    RedBlackNode<Integer> returned = root.repair();
    assertEquals(returned, root, "Returned value should be the root");
    assertNull(returned.left(), "Rebalancing root should not change its children");
    assertNull(returned.right(), "Rebalancing root should not change its children");
    assertEquals(returned.color(), RedBlackNode.RBColor.BLACK, "Root should always be BLACK");
    nodeIsLeaf(root);
  }

  /**
   * Test repairing two nodes (should not change).
   */
  @Test
  public void testRepairTwoNodes() {
    RedBlackNode<Integer> leaf = new RedBlackNode<>(3);
    RedBlackNode<Integer> root = new RedBlackNode<>(5, leaf, null);
    root.setColor(RedBlackNode.RBColor.BLACK);
    RedBlackNode<Integer> returned = leaf.repair();
    checkStructureAndValues(returned, 5, leaf, 3, true);
    nodeIsLeaf(leaf);
    assertEquals(leaf.color(), RedBlackNode.RBColor.RED, "Leaf node should still be RED");
    assertEquals(returned.color(), RedBlackNode.RBColor.BLACK, "Root should always be BLACK");

    root = new RedBlackNode<>(5, null, leaf);
    root.setColor(RedBlackNode.RBColor.BLACK);
    returned = leaf.repair();
    checkStructureAndValues(returned, 5, leaf, 3, false);
    nodeIsLeaf(leaf);
    assertEquals(leaf.color(), RedBlackNode.RBColor.RED, "Leaf node should still be RED");
    assertEquals(returned.color(), RedBlackNode.RBColor.BLACK, "Root should always be BLACK");

  }

  /**
   * Test repair red parent and uncle.
   *
   *     RB        OR    RB
   *    /  \       OR   /  \
   *   P    U      OR  U    P
   *  /            OR        \
   * L             OR         L
   */
  @Test
  public void testRepairRedUncle() {
    RedBlackNode<Integer> leaf = new RedBlackNode<>(1);
    RedBlackNode<Integer> parent = new RedBlackNode<>(3, leaf, null);
    RedBlackNode<Integer> uncle = new RedBlackNode<>(7, null, null);
    RedBlackNode<Integer> root = new RedBlackNode<>(5, parent, uncle);
    root.setColor(RedBlackNode.RBColor.BLACK);
    RedBlackNode<Integer> returned = leaf.repair();
    checkStructureAndValues(returned, 5, parent, 3, true);
    nodeIsLeaf(leaf);
    assertEquals(leaf.color(), RedBlackNode.RBColor.RED, "Leaf node should still be RED");
    assertEquals(parent.color(), RedBlackNode.RBColor.BLACK, "Parent should switch to BLACK");
    assertEquals(uncle.color(), RedBlackNode.RBColor.BLACK, "Uncle should switch to BLACK");
    assertEquals(returned.color(), RedBlackNode.RBColor.BLACK, "Root should always be BLACK");

    leaf = new RedBlackNode<>(7);
    parent = new RedBlackNode<>(5, null, leaf);
    uncle = new RedBlackNode<>(1, null, null);
    root = new RedBlackNode<>(3, uncle, parent);
    root.setColor(RedBlackNode.RBColor.BLACK);
    returned = leaf.repair();
    checkStructureAndValues(returned, 3, parent, 5, false);
    checkStructureAndValues(parent, 5, leaf, 7, false);
    nodeIsLeaf(leaf);
    assertEquals(leaf.color(), RedBlackNode.RBColor.RED, "Leaf node should still be RED");
    assertEquals(parent.color(), RedBlackNode.RBColor.BLACK, "Parent should switch to BLACK");
    assertEquals(uncle.color(), RedBlackNode.RBColor.BLACK, "Uncle should switch to BLACK");
    assertEquals(returned.color(), RedBlackNode.RBColor.BLACK, "Root should always be BLACK");

  }

  /**
   * Test repair red parent and black uncle. Note that this tree is not possible on its
   * own but could be a sub-tree.
   *
   *     RB        OR     RB
   *    /  \       OR    /  \
   *   P   UB      OR  UB    P
   *  /            OR       /
   * L             OR      L
   */
  @Test
  public void testRepairBlackUncle() {
    RedBlackNode<Integer> leaf = new RedBlackNode<>(1);
    RedBlackNode<Integer> parent = new RedBlackNode<>(3, leaf, null);
    RedBlackNode<Integer> uncle = new RedBlackNode<>(7, null, null);
    RedBlackNode<Integer> root = new RedBlackNode<>(5, parent, uncle);
    uncle.setColor(RedBlackNode.RBColor.BLACK);
    root.setColor(RedBlackNode.RBColor.BLACK);

    // Outside child requires only single rotation right for root; parent will become
    // the new root
    RedBlackNode<Integer> returned = leaf.repair();
    checkStructureAndValues(returned, 3, leaf, 1, true);
    checkStructureAndValues(returned, 3, root, 5, false);
    nodeIsLeaf(leaf);
    nodeIsLeaf(uncle);
    assertEquals(leaf.color(), RedBlackNode.RBColor.RED, "Leaf node should still be RED");
    assertEquals(parent.color(), RedBlackNode.RBColor.BLACK, "Parent should switch to BLACK");
    assertEquals(uncle.color(), RedBlackNode.RBColor.BLACK, "Uncle should still be BLACK");
    assertEquals(root.color(), RedBlackNode.RBColor.RED, "Root should switch to RED after rotation");

    leaf = new RedBlackNode<>(5);
    parent = new RedBlackNode<>(7, leaf, null);
    uncle = new RedBlackNode<>(1, null, null);
    root = new RedBlackNode<>(3, uncle, parent);
    root.setColor(RedBlackNode.RBColor.BLACK);
    uncle.setColor(RedBlackNode.RBColor.BLACK);

    // Inside child requires rotation right, making parent a right child; then root is
    // rotated left, bringing the leaf up as the root
    returned = leaf.repair();
    checkStructureAndValues(returned, 5, root, 3, true);
    checkStructureAndValues(returned, 5, parent, 7, false);
    nodeIsLeaf(uncle);
    nodeIsLeaf(parent);
    assertEquals(leaf.color(), RedBlackNode.RBColor.BLACK, "Leaf node should now be BLACK as root");
    assertEquals(parent.color(), RedBlackNode.RBColor.RED, "Parent should still be RED");
    assertEquals(uncle.color(), RedBlackNode.RBColor.BLACK, "Uncle should still be BLACK");
    assertEquals(root.color(), RedBlackNode.RBColor.RED, "Root should switch to RED after rotation");

  }

  /**
   * Test full operation of Red-Black tree.
   */
  @Test
  public void testRedBlackTreeInsertions() {
    RedBlackTree<Integer> tree = new RedBlackTree<>();
    tree.insert(9);
    tree.insert(8);
    tree.insert(1);
    // Tree should now have 5 as root
    tree.insert(3);
    tree.insert(4);
    //             8B
    //            /  \
    //           3    9B
    //          / \
    //        1B   4B
    tree.insert(6);
    //              8B
    //            /    \
    //           3      9B
    //          / \
    //        1B   4B
    //               \
    //                6
    tree.insert(5);
    //              8B               8B               8B
    //            /    \            /  \             /  \
    //           3      9B         3    9B          3    9B
    //          / \               / \              / \
    //        1B   4B           1B   4B          1B   5B
    //               \                 \             /  \
    //                6                 5           4    6
    //               /                   \
    //              5                     6
    tree.insert(7);
    //             8B                8B             8B                5B
    //            /  \              /  \           /  \            /      \
    //           3    9B           3    9B        5    9B         3        8
    //          / \               / \            / \             / \      / \
    //        1B   5B           1B   5          3   6B         1B   4B  6B   9B
    //            /  \              / \        / \    \                   \
    //           4    6           4B   6B    1B   4B   7                   7
    //                 \                 \
    //                  7                 7
    int prev = 0;
    for (Integer i : tree) {
      assertTrue(i > prev, "Final tree should be in increasing order");
    }
  }
}
