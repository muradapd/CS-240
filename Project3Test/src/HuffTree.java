import java.util.Iterator;

/**
 * Huffman coding tree. This is the tree object that is used to encode a file
 * into a zipped file and decode a zipped file back into it's original format.
 * 
 * @author Patrick Muradaz
 * @version 12/02/2019
 */

public class HuffTree implements Iterable<HuffTreeNode> {

  HuffTreeNode root;
  Heap<HuffTreeNode> maxHeap;  
  int size;

  /**
   * Default Constructor. Takes in a heap that holds only the root of this tree in
   * the first position of the heap.
   *
   * @param counts A heap that contains the root of this tree in the first heap
   *               position.
   */
  public HuffTree(Heap<HuffTreeNode> counts) {
    maxHeap = counts;
    
    // if the heap is empty this tree is empty
    if (maxHeap.size() == 0) {
      root = null;
      size = 0;
    } else {
      
      buildTree(); // use the heap to build the Huffman coding tree
      root = maxHeap.remove(); // set root of the tree (first node in heap)
  
      int leaves = counts.elements.length;
      int internals = leaves - 1;
      size = leaves + internals;
    }
  }

  /**
   * Getter for the tree's root.
   *
   * @return The root of the tree.
   */
  public HuffTreeNode root() {
    return root;
  }

  /**
   * Traverse the tree edges to lookup a string of bits. If all bits are used in
   * the lookup, return the byte stored in the last node. If all bits are not used
   * (e.g., the bit string denotes more than one byte), return the byte of the
   * last node along with the remaining bits.
   *
   * @param bits The input bit string to look up.
   *
   * @return A Pair of the byte value and any remaining bit string.
   */
  public Pair<Byte, String> lookup(String bits) {
    return lookupHelper(root, bits);
  }

  /**
   * Finds the bit sequence representation of the given element by traversing the
   * tree to find the given element on the tree and then recreating the
   * bitSequence with bitSegFinderHelper.
   * 
   * @param element is the element to find the bit sequence representation for.
   * @return the bit sequence representation of the given element.
   */
  public String bitSeqFinder(byte element) {
    Iterator<HuffTreeNode> iter = iterator();
    HuffTreeNode curr = null;

    // find the element we want on the tree then break
    while (iter.hasNext()) {
      curr = iter.next();

      if (curr.element() == element && curr.isLeaf()) {
        break;
      }
    }
    return bitSeqFinderHelper(curr);
  }

  /**
   * Iterator for visiting all nodes in a pre-order traversal of the tree. HINT:
   * Create a helper class to implement the Iterator interface. Within that class,
   * keep a stack of the parent nodes. When a parent node is visited, push it onto
   * the stack and visit the left child. Once a leaf is visited, if it was its
   * parent's left child, jump to the right. Otherwise, start popping from the
   * Stack until there is a right child to switch to.
   *
   * @return An Iterator to return one node at a time.
   */
  @Override
  public Iterator<HuffTreeNode> iterator() {
    return new HuffTreeIterator<HuffTreeNode>(this);
  }

  /**
   * Recursive helper method for lookup.
   * 
   * @param curr is the current node.
   * @param bits is the string of bits to find.
   * @return the pair located at the location of the given bit sequence.
   */
  private Pair<Byte, String> lookupHelper(HuffTreeNode curr, String bits) {
    if (bits.length() == 0) {
      return new Pair<Byte, String>(curr.element(), bits);
    } else if (curr.isLeaf()) {
      return new Pair<Byte, String>(curr.element(), bits);
    } else {
      if (bits.charAt(0) == '0') {
        curr = curr.left();
        return lookupHelper(curr, bits.substring(1));
      } else {
        curr = curr.right();
        return lookupHelper(curr, bits.substring(1));
      }
    }
  }

  /**
   * Recursive helper method for bitSeqFinder. Travels up the tree from the
   * current node and builds the bitSequence as a string.
   * 
   * @param curr    is the current node.
   * @param element is the element to find.
   * @return the bitSequence of the given element.
   */
  private String bitSeqFinderHelper(HuffTreeNode curr) {
    if (curr.parent() == null && curr.isLeaf()) {
      return "0";
    } else if (curr.parent() == null) {
      return "";
    } else {
      if (curr.isLeft()) {
        return bitSeqFinderHelper(curr.parent()) + "0";
      } else {
        return bitSeqFinderHelper(curr.parent()) + "1";
      }
    }
  }
  
  /**
   * Recursively iterates through the maxHeap and builds the Huffman coding tree
   * based on the entries in the heap.
   */
  private void buildTree() {
    if (maxHeap.size() == 1) {
      return;
    }
    HuffTreeNode left;
    HuffTreeNode right;
    HuffTreeNode node1 = maxHeap.remove();
    HuffTreeNode node2 = maxHeap.remove();
    
    int compWeight = node1.weight() - node2.weight();
    int compElem = node1.element() - node2.element();
    int compHeight = node1.height() - node2.height();
    int compSmall = node1.smallest(Integer.MAX_VALUE) - node2.smallest(Integer.MAX_VALUE);
    
    // node with smaller weight goes on left
    if (compWeight < 0) {
      left = node1;
      right = node2;
    } else if (compWeight > 0) {
      left = node2;
      right = node1;
    } else {
      
      // node with smaller element goes on left 
      if (node1.isLeaf() && node2.isLeaf() && compElem < 0) {
        left = node1;
        right = node2;
      } else if (node1.isLeaf() && node2.isLeaf() && compElem > 0) {
        left = node2;
        right = node1;
      } else {
        
        // node with smaller height goes on left
        if (compHeight < 0) {
          left = node1;
          right = node2;
        } else if (compHeight > 0) {
          left = node2;
          right = node1;
        } else {
          
          // node with smallest element goes on left
          if (compSmall < 0) {
            left = node1;
            right = node2;
          } else {
            left = node2;
            right = node1;
          }
        }
      }
    }
    
    HuffTreeNode newRoot = new HuffTreeNode(null, left, right);
    left.parentIs(newRoot);
    right.parentIs(newRoot);
    maxHeap.insert(newRoot);
    buildTree();
  }
}
