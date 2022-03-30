import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HuffTreeTest {

  private Heap<HuffTreeNode> lHeap;
  private Heap<HuffTreeNode> rHeap;
  private Heap<HuffTreeNode> fullHeap;

  @BeforeEach
  public void buildTree() {
    HuffTreeNode[] lNodes = new HuffTreeNode[3];
    lNodes[0] = new HuffTreeNode((byte)'a', 1);
    lNodes[1] = new HuffTreeNode((byte)'b', 2);
    lNodes[2] = new HuffTreeNode((byte)'c', 4);
    lHeap = new Heap<>(lNodes, 3,
        (HuffTreeNode htn1, HuffTreeNode htn2) -> htn2.weight() - htn1.weight());
    /*
     *      7
     *     / \
     *    3   c4
     *   / \
     * a1   b2
     */

    HuffTreeNode[] rNodes = new HuffTreeNode[3];
    rNodes[0] = new HuffTreeNode((byte)'a', 2);
    rNodes[1] = new HuffTreeNode((byte)'b', 3);
    rNodes[2] = new HuffTreeNode((byte)'c', 4);
    rHeap = new Heap<>(rNodes, 3,
        (HuffTreeNode htn1, HuffTreeNode htn2) -> htn2.weight() - htn1.weight());
    /*
     *      9
     *     / \
     *   c4   5
     *       / \
     *     a2   b3
     */

    HuffTreeNode[] fullNodes = new HuffTreeNode[8];
    fullNodes[0] = new HuffTreeNode((byte)'a', 2);
    fullNodes[1] = new HuffTreeNode((byte)'b', 5);
    fullNodes[2] = new HuffTreeNode((byte)'c', 1);
    fullNodes[3] = new HuffTreeNode((byte)'d', 4);
    fullNodes[4] = new HuffTreeNode((byte)'e', 8);
    fullNodes[5] = new HuffTreeNode((byte)'f', 10);
    fullNodes[6] = new HuffTreeNode((byte)'g', 17);
    fullNodes[7] = new HuffTreeNode((byte)'h', 30);
    fullHeap = new Heap<>(fullNodes, 8,
        (HuffTreeNode htn1, HuffTreeNode htn2) -> htn2.weight() - htn1.weight());
    /*
     *      77
     *     /  \
     *  h30    47
     *       /    \
     *    18        29
     *   /  \      /  \
     * e8    f10 12    g17
     *          /  \
     *        b5    7
     *             / \
     *            3   d4
     *           / \
     *         c1   a2
     */
  }

  @Test
  public void testHuffTreeRootSimple() {

    HuffTree tree = new HuffTree(lHeap);
    assertEquals(7, tree.root().weight(), "For 1 2 and 4 root weight should be 7");
    tree = new HuffTree(rHeap);
    assertEquals(9, tree.root().weight(), "For 2 3 and 4 root weight should be 9");

  }

  @Test
  public void testHuffTreeSimpleLeftStructure() {

    HuffTree tree = new HuffTree(lHeap);
    assertEquals(7, tree.root().weight(), "For 1 2 and 4 tree is not correct");
    assertEquals(3, tree.root().left().weight(), "For 1 2 and 4 tree is not correct");
    assertEquals(4, tree.root().right().weight(), "For 1 2 and 4 tree is not correct");
    assertEquals((byte)'c', tree.root().right().element(), "For 1 2 and 4 tree is not correct");
    assertEquals(1, tree.root().left().left().weight(), "For 1 2 and 4 tree is not correct");
    assertEquals((byte)'a', tree.root().left().left().element(), "For 1 2 and 4 tree is not correct");
    assertEquals(2, tree.root().left().right().weight(), "For 1 2 and 4 tree is not correct");
    assertEquals((byte)'b', tree.root().left().right().element(), "For 1 2 and 4 tree is not correct");

  }

  @Test
  public void testHuffTreeSimpleRightStructure() {

    HuffTree tree = new HuffTree(rHeap);
    assertEquals(9, tree.root().weight(), "For 2 3 and 4 tree is not correct");
    assertEquals(4, tree.root().left().weight(), "For 2 3 and 4 tree is not correct");
    assertEquals((byte)'c', tree.root().left().element(), "For 2 3 and 4 tree is not correct");
    assertEquals(5, tree.root().right().weight(), "For 2 3 and 4 tree is not correct");
    assertEquals(2, tree.root().right().left().weight(), "For 2 3 and 4 tree is not correct");
    assertEquals((byte)'a', tree.root().right().left().element(), "For 2 3 and 4 tree is not correct");
    assertEquals(3, tree.root().right().right().weight(), "For 2 3 and 4 tree is not correct");
    assertEquals((byte)'b', tree.root().right().right().element(), "For 2 3 and 4 tree is not correct");

  }

  @Test
  public void testHuffTreeSimpleLookup() {

    HuffTree tree = new HuffTree(lHeap);
    Pair<Byte, String> pair = tree.lookup ("1");
    assertEquals(pair.getFirst().byteValue(), (byte)'c', "Lookup of 1 in 1 2 4 tree is not correct");
    pair = tree.lookup ("00");
    assertEquals(pair.getFirst().byteValue(), (byte)'a', "Lookup of 00 in 1 2 4 tree is not correct");
    pair = tree.lookup ("01");
    assertEquals(pair.getFirst().byteValue(), (byte)'b', "Lookup of 01 in 1 2 4 tree is not correct");
    
    tree = new HuffTree(rHeap);
    pair = tree.lookup ("0");
    assertEquals(pair.getFirst().byteValue(), (byte)'c', "Lookup of 1 in 2 3 4 tree is not correct");
    pair = tree.lookup ("10");
    assertEquals(pair.getFirst().byteValue(), (byte)'a', "Lookup of 00 in 2 3 4 tree is not correct");
    pair = tree.lookup ("11");
    assertEquals(pair.getFirst().byteValue(), (byte)'b', "Lookup of 01 in 2 3 4 tree is not correct");
    
  }

  @Test
  public void testHuffTreeSimpleIterator() {

    HuffTree tree = new HuffTree(lHeap);
    Iterator<HuffTreeNode> iter = tree.iterator();
    HuffTreeNode node = iter.next();
    assertEquals(7, node.weight(), "Root of pre-order for 1 2 4 was not visited first");
    node = iter.next();
    assertEquals(3, node.weight(), "Pre-order traversal for 1 2 4 tree was not correct");
    node = iter.next();
    assertEquals(1, node.weight(), "Pre-order traversal for 1 2 4 tree was not correct");
    assertEquals((byte)'a', node.element(), "Pre-order traversal for 1 2 4 tree was not correct");
    node = iter.next();
    assertEquals(2, node.weight(), "Pre-order traversal for 1 2 4 tree was not correct");
    assertEquals((byte)'b', node.element(), "Pre-order traversal for 1 2 4 tree was not correct");
    node = iter.next();
    assertEquals(4, node.weight(), "Pre-order traversal for 1 2 4 tree was not correct");
    assertEquals((byte)'c', node.element(), "Pre-order traversal for 1 2 4 tree was not correct");

    tree = new HuffTree(rHeap);
    iter = tree.iterator();
    node = iter.next();
    assertEquals(9, node.weight(), "Root of pre-order for 2 3 4 was not visited first");
    node = iter.next();
    assertEquals(4, node.weight(), "Pre-order traversal for 2 3 4 tree was not correct");
    assertEquals((byte)'c', node.element(), "Pre-order traversal for 2 3 4 tree was not correct");
    node = iter.next();
    assertEquals(5, node.weight(), "Pre-order traversal for 2 3 4 tree was not correct");
    node = iter.next();
    assertEquals(2, node.weight(), "Pre-order traversal for 2 3 4 tree was not correct");
    assertEquals((byte)'a', node.element(), "Pre-order traversal for 2 3 4 tree was not correct");
    node = iter.next();
    assertEquals(3, node.weight(), "Pre-order traversal for 2 3 4 tree was not correct");
    assertEquals((byte)'b', node.element(), "Pre-order traversal for 2 3 4 tree was not correct");

  }

  /*
   *      77
   *     /  \
   *  h30    47
   *       /    \
   *    18        29
   *   /  \      /  \
   * e8    f10 12    g17
   *          /  \
   *        b5    7
   *             / \
   *            3   d4
   *           / \
   *         c1   a2
   */
  @Test
  public void testHuffTreeComplex() {

    HuffTree tree = new HuffTree(fullHeap);
    HashMap<String, Byte> map = new HashMap<>();
    map.put("0", (byte)'h');
    map.put("100", (byte)'e');
    map.put("101", (byte)'f');
    map.put("111", (byte)'g');
    map.put("1100", (byte)'b');
    map.put("11011", (byte)'d');
    map.put("110100", (byte)'c');
    map.put("110101", (byte)'a');

    for (String key : map.keySet()) {
      Pair<Byte, String> pair = tree.lookup(key);
      assertEquals((char)pair.getFirst().byteValue(), (char)map.get(key).byteValue(),
          "Lookup of " + key + " in complex tree is not correct");
    }

  }

}
