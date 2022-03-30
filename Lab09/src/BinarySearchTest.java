import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for rotating a subtree left or right.
 *
 * @author Michael S. Kirkpatrick
 */
public class BinarySearchTest {

  /**
   * Rotate the root of a tree left.
   *
   *        root              =>            leaf
   *       /    \             =>           /    \
   *   null      leaf         =>       root      null
   *            /    \        =>      /    \
   *        null      null    =>  null      null
   *
   */
  @Test
  public void testRotateRootLeft() {
    BinarySearchNode<String> leaf = new BinarySearchNode<>("Leaf");
    BinarySearchNode<String> root = new BinarySearchNode<>("Root", null, leaf);
    BinarySearchNode<String> newRoot = root.rotateLeft();
    assertEquals(newRoot.element(), "Leaf", "Returned node should have name Leaf");
    assertNull(newRoot.parent(), "Returned node should be the new root (parent null)");
    assertNull(newRoot.right(), "Returned node should have no right child");

    assertEquals(newRoot.left(), root, "Old root should now be left child");
    assertEquals(newRoot.left().element(), "Root", "Old root should now be left child");
    assertEquals(newRoot.left().parent(), newRoot, "Old root should have leaf as parent");
    assertNull(newRoot.left().left(), "Old root should now be a leaf");
    assertNull(newRoot.left().right(), "Old root should now be a leaf");

  }

  /**
   * Rotate the root of a tree right.
   *
   *             root         =>       leaf
   *            /    \        =>      /    \
   *        leaf      null    =>  null      root
   *       /    \             =>           /    \
   *   null      null         =>       null      null
   *
   */
  @Test
  public void testRotateRootRight() {
    BinarySearchNode<String> leaf = new BinarySearchNode<>("Leaf");
    BinarySearchNode<String> root = new BinarySearchNode<>("Root", leaf, null);
    BinarySearchNode<String> newRoot = root.rotateRight();
    assertEquals(newRoot.element(), "Leaf", "Returned node should have name Leaf");
    assertNull(newRoot.parent(), "Returned node should be the new root (parent null)");
    assertNull(newRoot.left(), "Returned node should have no left child");

    assertEquals(newRoot.right(), root, "Old root should now be right child");
    assertEquals(newRoot.right().element(), "Root", "Old root should now be right child");
    assertEquals(newRoot.right().parent(), newRoot, "Old root should have leaf as parent");
    assertNull(newRoot.right().left(), "Old root should now be a leaf");
    assertNull(newRoot.right().right(), "Old root should now be a leaf");
  }

  /**
   * Rotate root with a non-leaf child left.
   *
   *        root                 =>            middle
   *       /    \                =>           /      \
   *   null     middle           =>       root        right
   *           /      \          =>      /    \
   *       left        right     =>  null      left
   *
   */
  @Test
  public void testRotateRootWithMiddleLeft() {
    BinarySearchNode<String> leftLeaf = new BinarySearchNode<>("Left leaf");
    BinarySearchNode<String> rightLeaf = new BinarySearchNode<>("Right leaf");
    BinarySearchNode<String> middle = new BinarySearchNode<>("Middle", leftLeaf, rightLeaf);
    BinarySearchNode<String> root = new BinarySearchNode<>("Root", null, middle);
    BinarySearchNode<String> newRoot = root.rotateLeft();

    assertEquals(newRoot.element(), "Middle", "Returned node should be the old middle node");
    assertNull(newRoot.parent(), "Returned node should be the new root (parent null)");

    assertEquals(newRoot.right(), rightLeaf, "Returned node should not change right child");
    assertEquals(newRoot.right().element(), "Right leaf", "Returned node should not change right child");
    assertEquals(newRoot.right().parent(), newRoot, "Returned node should not change right child");

    assertEquals(newRoot.left(), root, "Old root should now be left child");
    assertEquals(newRoot.left().element(), "Root", "Old root should now be left child");
    assertEquals(newRoot.left().parent(), newRoot, "Old root should have middle as parent");

    assertEquals(newRoot.left().right(), leftLeaf, "Old root should pick up left leaf as left child");
    assertEquals(newRoot.left().right().element(), "Left leaf", "Old root should pick up left leaf as right child");
    assertEquals(newRoot.left().right().parent(), root, "Left leaf should have old root as parent");
    assertNull(newRoot.left().left(), "Old root should not have a left child");
  }

  /**
   * Rotate root with a non-leaf child right.
   *
   *               root         =>       middle
   *              /    \        =>      /      \
   *        middle      null    =>  left        root
   *       /      \             =>             /    \
   *   left        right        =>        right     null
   *
   */
  @Test
  public void testRotateRootWithMiddleRight() {
    BinarySearchNode<String> leftLeaf = new BinarySearchNode<>("Left leaf");
    BinarySearchNode<String> rightLeaf = new BinarySearchNode<>("Right leaf");
    BinarySearchNode<String> middle = new BinarySearchNode<>("Middle", leftLeaf, rightLeaf);
    BinarySearchNode<String> root = new BinarySearchNode<>("Root", middle, null);
    BinarySearchNode<String> newRoot = root.rotateRight();

    assertEquals(newRoot.element(), "Middle", "Returned node should be the old middle node");
    assertNull(newRoot.parent(), "Returned node should be the new root (parent null)");

    assertEquals(newRoot.left(), leftLeaf, "Returned node should not change left child");
    assertEquals(newRoot.left().element(), "Left leaf", "Returned node should not change left child");
    assertEquals(newRoot.left().parent(), newRoot, "Returned node should not change left child");

    assertEquals(newRoot.right(), root, "Old root should now be right child");
    assertEquals(newRoot.right().element(), "Root", "Old root should now be right child");
    assertEquals(newRoot.right().parent(), newRoot, "Old root should have middle as parent");

    assertEquals(newRoot.right().left(), rightLeaf, "Old root should pick up right leaf as left child");
    assertEquals(newRoot.right().left().element(), "Right leaf", "Old root should pick up right leaf as left child");
    assertEquals(newRoot.right().left().parent(), root, "Right leaf should have old root as parent");
    assertNull(newRoot.right().right(), "Old root should not have a right child");
  }
  
  /**
   * Rotate root with a non-leaf child left.
   *
   *               root           =>                    root
   *              /    \          =>                   /    \
   *        middle      null      =>              right      null
   *       /      \               =>             /     \
   *   left        right          =>       middle       rtLf  
   *              /     \         =>      /      \
   *          lfLf       rtLf     =>  left        lfLf
   *
   */
  @Test
  public void testRotateLeftMiddleLeft() {
    BinarySearchNode<String> leftLeaf = new BinarySearchNode<>("lfLf");
    BinarySearchNode<String> rightLeaf = new BinarySearchNode<>("rtLf");
    BinarySearchNode<String> right = new BinarySearchNode<>("Right", leftLeaf, rightLeaf);
    BinarySearchNode<String> left = new BinarySearchNode<>("Left");
    BinarySearchNode<String> middle = new BinarySearchNode<>("Middle", left, right);
    BinarySearchNode<String> root = new BinarySearchNode<>("Root", middle, null);
    BinarySearchNode<String> returned = middle.rotateLeft();

    assertEquals(returned.element(), "Right", "Returned node should be the old right node");
    assertEquals(returned.parent(), root, "Returned node should have root as its parent");
    assertEquals(returned, root.left(), "Root should have returned node as left child");
    assertNull(root.right(), "Root should still have a null right child");

    assertEquals(returned.right(), rightLeaf, "Returned node should not change right child");
    assertEquals(returned.right().element(), "rtLf", "Returned node should not change right child");
    assertEquals(returned.right().parent(), right, "Returned node should not change right child");

    assertEquals(returned.left(), middle, "Old middle should now be the left child");
    assertEquals(returned.left().element(), "Middle", "Old middle should now be the left child");
    assertEquals(returned.left().parent(), right, "Old middle should have right as parent");

    assertEquals(returned.left().left(), left, "Old middle should have left as a left child");
    assertEquals(returned.left().left().element(), "Left", "Old middle should have left as a left child");
    assertEquals(returned.left().left().parent(), middle, "Left should still have middle as parent");

    assertEquals(returned.left().right(), leftLeaf, "Old middle should now have lfLf as a right child");
    assertEquals(returned.left().right().element(), "lfLf", "Old middle should now have lfLf as a right child");
    assertEquals(returned.left().right().parent(), middle, "Left leaf should still have middle as parent");
  }  

  /**
   * Rotate root with a non-leaf child right.
   *
   *                    root           =>            root
   *                   /    \          =>           /    \
   *             middle      null      =>       left      null
   *            /      \               =>      /     \
   *        left        right          =>  lfLf       middle
   *       /     \                     =>            /      \
   *   lfLf       rtLf                 =>        rtLf        right
   *
   */
  @Test
  public void testRotateLeftMiddleRight() {
    BinarySearchNode<String> leftLeaf = new BinarySearchNode<>("lfLf");
    BinarySearchNode<String> rightLeaf = new BinarySearchNode<>("rtLf");
    BinarySearchNode<String> left = new BinarySearchNode<>("Left", leftLeaf, rightLeaf);
    BinarySearchNode<String> right = new BinarySearchNode<>("Right");
    BinarySearchNode<String> middle = new BinarySearchNode<>("Middle", left, right);
    BinarySearchNode<String> root = new BinarySearchNode<>("Root", middle, null);
    BinarySearchNode<String> returned = middle.rotateRight();

    assertEquals(returned.element(), "Left", "Returned node should be the old left node");
    assertEquals(returned.parent(), root, "Returned node should have root as its parent");
    assertEquals(returned, root.left(), "Root should have returned node as left child");
    assertNull(root.right(), "Root should still have a null right child");

    assertEquals(returned.left(), leftLeaf, "Returned node should not change left child");
    assertEquals(returned.left().element(), "lfLf", "Returned node should not change left child");
    assertEquals(returned.left().parent(), left, "Returned node should not change left child");

    assertEquals(returned.right(), middle, "Old middle should now be the right child");
    assertEquals(returned.right().element(), "Middle", "Old middle should now be the right child");
    assertEquals(returned.right().parent(), left, "Old middle should have left as parent");

    assertEquals(returned.right().right(), right, "Old middle should have right as a right child");
    assertEquals(returned.right().right().element(), "Right", "Old middle should have right as a right child");
    assertEquals(returned.right().right().parent(), middle, "Right should still have middle as parent");

    assertEquals(returned.right().left(), rightLeaf, "Old middle should now have rtLf as a left child");
    assertEquals(returned.right().left().element(), "rtLf", "Old middle should now have rtLf as a left child");
    assertEquals(returned.right().left().parent(), middle, "Right leaf should still have middle as parent");
  }

  /**
   * Rotate root with a non-leaf child left.
   *
   *        root                       =>         root
   *       /    \                      =>        /    \
   *   null      middle                =>    null      right
   *            /      \               =>             /     \
   *        left        right          =>       middle       rtLf  
   *                   /     \         =>      /      \
   *               lfLf       rtLf     =>  left        lfLf
   *
   */
  @Test
  public void testRotateRightMiddleLeft() {
    BinarySearchNode<String> leftLeaf = new BinarySearchNode<>("lfLf");
    BinarySearchNode<String> rightLeaf = new BinarySearchNode<>("rtLf");
    BinarySearchNode<String> right = new BinarySearchNode<>("Right", leftLeaf, rightLeaf);
    BinarySearchNode<String> left = new BinarySearchNode<>("Left");
    BinarySearchNode<String> middle = new BinarySearchNode<>("Middle", left, right);
    BinarySearchNode<String> root = new BinarySearchNode<>("Root", null, middle);
    BinarySearchNode<String> returned = middle.rotateLeft();

    assertEquals(returned.element(), "Right", "Returned node should be the old right node");
    assertEquals(returned.parent(), root, "Returned node should have root as its parent");
    assertEquals(returned, root.right(), "Root should have returned node as right child");
    assertNull(root.left(), "Root should still have a null left child");

    assertEquals(returned.right(), rightLeaf, "Returned node should not change right child");
    assertEquals(returned.right().element(), "rtLf", "Returned node should not change right child");
    assertEquals(returned.right().parent(), right, "Returned node should not change right child");

    assertEquals(returned.left(), middle, "Old middle should now be the left child");
    assertEquals(returned.left().element(), "Middle", "Old middle should now be the left child");
    assertEquals(returned.left().parent(), right, "Old middle should have right as parent");

    assertEquals(returned.left().left(), left, "Old middle should have left as a left child");
    assertEquals(returned.left().left().element(), "Left", "Old middle should have left as a left child");
    assertEquals(returned.left().left().parent(), middle, "Left should still have middle as parent");

    assertEquals(returned.left().right(), leftLeaf, "Old middle should now have lfLf as a right child");
    assertEquals(returned.left().right().element(), "lfLf", "Old middle should now have lfLf as a right child");
    assertEquals(returned.left().right().parent(), middle, "Left leaf should still have middle as parent");
  }  

  /**
   * Rotate root with a non-leaf child right.
   *
   *        root                  =>       root
   *       /    \                 =>      /    \
   *   null      middle           =>  null      left
   *            /      \          =>           /    \
   *        left        right     =>       lfLf      middle
   *       /     \                =>                /      \
   *   lfLf       rtLf            =>            rtLf        right
   *
   */
  @Test
  public void testRotateRightMiddleRight() {
    BinarySearchNode<String> leftLeaf = new BinarySearchNode<>("lfLf");
    BinarySearchNode<String> rightLeaf = new BinarySearchNode<>("rtLf");
    BinarySearchNode<String> left = new BinarySearchNode<>("Left", leftLeaf, rightLeaf);
    BinarySearchNode<String> right = new BinarySearchNode<>("Right");
    BinarySearchNode<String> middle = new BinarySearchNode<>("Middle", left, right);
    BinarySearchNode<String> root = new BinarySearchNode<>("Root", null, middle);
    BinarySearchNode<String> returned = middle.rotateRight();

    assertEquals(returned.element(), "Left", "Returned node should be the old left node");
    assertEquals(returned.parent(), root, "Returned node should have root as its parent");
    assertEquals(returned, root.right(), "Root should have returned node as right child");
    assertNull(root.left(), "Root should still have a null left child");

    assertEquals(returned.left(), leftLeaf, "Returned node should not change left child");
    assertEquals(returned.left().element(), "lfLf", "Returned node should not change left child");
    assertEquals(returned.left().parent(), left, "Returned node should not change left child");

    assertEquals(returned.right(), middle, "Old middle should now be the right child");
    assertEquals(returned.right().element(), "Middle", "Old middle should now be the right child");
    assertEquals(returned.right().parent(), left, "Old middle should have left as parent");

    assertEquals(returned.right().right(), right, "Old middle should have right as a right child");
    assertEquals(returned.right().right().element(), "Right", "Old middle should have right as a right child");
    assertEquals(returned.right().right().parent(), middle, "Right should still have middle as parent");

    assertEquals(returned.right().left(), rightLeaf, "Old middle should now have rtLf as a left child");
    assertEquals(returned.right().left().element(), "rtLf", "Old middle should now have rtLf as a left child");
    assertEquals(returned.right().left().parent(), middle, "Right leaf should still have middle as parent");
  }

}
