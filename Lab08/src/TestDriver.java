/**
 * Simple test driver for new BST methods.
 * @author Nathan Sprague and Michael S. Kirkpatrick
 * @version 1.1
 *
 */
public class TestDriver {
  
  /**
   * Call desired tests here.
   */
  public static void main(String[] args) {
    /* Uncomment a test and recompile to run it */

		// testMinElement(); // PASSED
		// testMaxElement(); // PASSED
		//testRecursiveSize(); // PASSED
		// testMakeOrderedList(); //PASSED
		// testIsBST(); //PASSED
		// testIsAVL(); //PASSED
		// testInsertAVL(); //PASSED
		// testInsertAVLBad(); //PASSED
  }
  
  /**
   *  Test the minElement method.
   */
  public static void testMinElement() {
    BST<Integer> bst = new BST<>();
    System.out.println("\nStarting minimum element test with empty tree");
    for (int num : new int[]{4, 5, 2, 8, 9, 1, 3}) {
      System.out.println("  Inserting: " + num);
      bst.insert(num);
      System.out.println("  Minimum element: " + bst.minElement());
    }
  }
  
  /**
   * Test the maxElement method.
   */
  public static void testMaxElement() {
    BST<Integer> bst = new BST<>();
    System.out.println("\nStarting maximum element test with empty tree");
    for (int num : new int[]{4, 5, 2, 8, 9, 1, 3}) {
      System.out.println("  Inserting: " + num);
      bst.insert(num);
      System.out.println("  Maximum element: " + bst.maxElement());
    }
  }
  
  /**
   *  Test the recursiveSize method.
   */
  public static void testRecursiveSize() {
    BST<Integer> bst = new BST<>();
    System.out.println("\nStarting recursive size test with empty tree");
    for (int num : new int[]{4, 5, 2, 8, 9, 1, 3}) {
      System.out.println("  Size should be " + bst.size() + "; it is: " + bst.recursiveSize());
      bst.insert(num); 
    }
  }
   
  /**
   * Test the makeOrderedList method.
   */
  public static void testMakeOrderedList() {
    
    BST<Integer> bst = new BST<>();
    System.out.println("\nStarting test to make ordered list with empty tree");
    for (int num : new int[]{4, 5, 2, 8, 9, 1, 3}) {
      System.out.println("  Inserting: " + num);
      bst.insert(num);
    }
    
    System.out.println("ORDERED:");
    LList<Integer> list = bst.makeOrderedList();
    
    for (list.moveToPos(0); !list.isAtEnd(); list.next() ) {
      System.out.print("  " + list.getValue());
    }
    System.out.println("");
  }
  
  /**
   * Test the isBST method.
   */
  public static void testIsBST() {

    System.out.println("\nStarting test for BST status with four trees");
   
    BST<Integer> bstOK = new BST<>();
    bstOK.root = new BSTNode<>(10);
    bstOK.root.setLeft(new BSTNode<>(7));
    bstOK.root.setRight(new BSTNode<>(15));
    bstOK.root.left().setLeft(new BSTNode<>(2));
    bstOK.root.left().setRight(new BSTNode<>(8));
    bstOK.root.right().setLeft(new BSTNode<>(14));
    
    BST<Integer> bstEasyBroken = new BST<>();
    bstEasyBroken.root = new BSTNode<>(10);
    bstEasyBroken.root.setLeft(new BSTNode<>(7));
    bstEasyBroken.root.setRight(new BSTNode<>(3));
   
    BST<Integer> bstSneakyBroken = new BST<>();
    bstSneakyBroken.root = new BSTNode<>(10);
    bstSneakyBroken.root.setLeft(new BSTNode<>(7));
    bstSneakyBroken.root.setRight(new BSTNode<>(15));
    bstSneakyBroken.root.left().setLeft(new BSTNode<>(2));
    bstSneakyBroken.root.left().setRight(new BSTNode<>(11));
    bstSneakyBroken.root.right().setLeft(new BSTNode<>(14));

    BST<Integer> bstSneakierBroken = new BST<>();
    bstSneakierBroken.root = new BSTNode<>(10);
    bstSneakierBroken.root.setLeft(new BSTNode<>(7));
    bstSneakierBroken.root.setRight(new BSTNode<>(12));
    bstSneakierBroken.root.left().setLeft(new BSTNode<>(3));
    bstSneakierBroken.root.left().setRight(new BSTNode<>(7));
    bstSneakierBroken.root.right().setLeft(new BSTNode<>(11));
    bstSneakierBroken.root.right().setLeft(new BSTNode<>(15));
    
    System.out.println("  BST? Should be true: "+ bstOK.isValidBST());
    System.out.println("  BST? Should be false: "+ bstEasyBroken.isValidBST());
    System.out.println("  BST? Should be false: "+ bstSneakyBroken.isValidBST());
    System.out.println("  BST? Should be false: "+ bstSneakierBroken.isValidBST());
  }

  /**
   * Test the isAVL method.
   */
  public static void testIsAVL() {
    System.out.println("\nStarting test for AVL status");
    AVL<Integer> avlOK = new AVL<>();
    avlOK.root = new BSTNode<>(10);
    avlOK.root.setLeft(new BSTNode<>(7));
    avlOK.root.setRight(new BSTNode<>(17));
    avlOK.root.left().setLeft(new BSTNode<>(5));
    avlOK.root.right().setLeft(new BSTNode<>(14));
    avlOK.root.right().setRight(new BSTNode<>(18));
    avlOK.root.right().left().setLeft(new BSTNode<>(11));

    AVL<Integer>avlEasyBroken = new AVL<>();
    avlEasyBroken.root = new BSTNode<>(10);
    avlEasyBroken.root.setLeft(new BSTNode<>(7));
    avlEasyBroken.root.left().setLeft(new BSTNode<>(3));

    AVL<Integer>avlTrickyOK = new AVL<>();
    avlTrickyOK.root = new BSTNode<>(10);
    avlTrickyOK.root.setLeft(new BSTNode<>(7));
    avlTrickyOK.root.left().setLeft(new BSTNode<>(3));
    avlTrickyOK.root.setRight(new BSTNode<>(17));
    avlTrickyOK.root.right().setLeft(new BSTNode<>(13));

    System.out.println("  BST? Should be true: " + avlOK.isValidBST());
    System.out.println("  AVL? Should be true: " + avlOK.isValidAVL());

    System.out.println("  BST? Should be true: " + avlEasyBroken.isValidBST());
    System.out.println("  AVL? Should be false: " + avlEasyBroken.isValidAVL());

    System.out.println("  BST? Should be true: " + avlTrickyOK.isValidBST());
    System.out.println("  AVL? Should be true: " + avlTrickyOK.isValidAVL());
  }

  /**
   * Test general BST insert that doesn't create AVL
   */
  public static void testInsertAVLBad() {
    AVL<Integer> avl = new AVL<>();
    System.out.println("\nStarting AVL bad insert test with empty tree");
    for (int num : new int[] { 4, 5, 2, 8, 9, 1, 3 }) { // this creates a balanced tree
      System.out.print("  Inserting: " + num);
      avl.insert(num);
      if (avl.isValidAVL())
        System.out.println(" (balanced)");
      else
        System.out.println(" (NOT balanced)");
    }
    System.out.println("  BST? Should be true: " + avl.isValidBST());
    System.out.println("  AVL? Should be false: " + avl.isValidAVL()); // is there an issue?
  }

  /**
   * Test general BST insert that does create AVL
   */
  public static void testInsertAVL() {
    AVL<Integer> avl = new AVL<>();
    System.out.println("\nStarting AVL insert test with empty tree");
    for (int num : new int[]{4, 6, 5, 2, 9, 1, 8}) {
      System.out.print("  Inserting: " + num);
      avl.insert(num);
      if (avl.isValidAVL())
        System.out.println(" (balanced)");
      else
        System.out.println(" (NOT balanced)");
    }
    System.out.println("  BST? Should be true: " + avl.isValidBST());
    System.out.println("  AVL? Should be true: " + avl.isValidAVL());
  }

}
