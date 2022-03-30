import java.util.ArrayList;

public class Runner {

  public static void main(String[] args) {
//    queenTester();
//    fibTester();
    bSSTester();
//    dSSTester();
  }

  private static ArrayList<Integer> makeList() {
    ArrayList<Integer> list = new ArrayList<Integer>();

    for (int i = 0; i < 10; i++) {
      list.add(i);
    }

    return list;
  }

//  private static void queenTester() {
//    System.out.println("Queen Test:");
//    BacktrackingQueens queenTest = new BacktrackingQueens(8);
//    queenTest.placeQueens(0);
//    System.out.println(queenTest.toString());
//    System.out.println("Passed!");
//  }
//
//  private static void fibTester() {
//    System.out.println("\nFibonacci Test:");
//    DynamicFibonacci fibTest = new DynamicFibonacci();
//    System.out.println(fibTest.compute(1000));
//    System.out.println("Passed!");
//  }

  private static void bSSTester() {
    System.out.println("\nBacktrackingSubsetSum Test:");
    BacktrackingSubsetSum bSSTest = new BacktrackingSubsetSum(makeList());
    for (int i = 0; i < 47; i++) {
      System.out.println(i + ".\t" + bSSTest.findSum(i));
    }
//    System.out.println("Passed!");
  }

//  private static void dSSTester() {
//    System.out.println("\nDynamicSubsetSum Test:");
//    DynamicSubsetSum dSSTest = new DynamicSubsetSum(makeList());
//    dSSTest.fillTable(50);
//    dSSTest.printMemo();
//    System.out.println("Passed!");
//  }
}
