import java.util.function.Predicate;

/**
 * Runner class for experimenting and for encoding selection criteria.
 *
 * @author Patrick Muradaz
 * @version V2, 9/2019
 */
public class Runner {

  private static final long MAX = 3088286400L;
  private static final Person ALICE = new Person("ALICE");
  private static final Person BOB = new Person("BOB");
  private static final Person CAROL = new Person("CAROL");
  private static final Person EVE = new Person("EVE");

  /**
   * Main method. Driver of the program.
   * 
   * @param args command line arguments.
   */
  public static void main(String[] args) {

//    System.out.println("Analyzer1 Output:");
//    SequenceAnalyzer analyzer1 = new SequenceAnalyzer(ALICE, BOB);
//    String[] toPrint1 = analyzer1.longestSharedSequence(0, MAX);
//
//    for (String str : toPrint1) {
//      System.out.println(str.toString());
//    }
//
//    System.out.println("\nAnalyzer2 Output:");
//    SequenceAnalyzer analyzer2 = new SequenceAnalyzer(CAROL, EVE);
//    Predicate<Region> condition1 = reg -> reg.getEncoding().contains("AGA");
//    WeightedList<Region> toPrint2 = analyzer2.findCandidates(0, MAX, true, condition1);
//    Iterator<Region> iter1 = toPrint2.iterator();
//
//    int i = 0;
//    while (iter1.hasNext()) {
//      System.out.println(iter1.next().toString());
//      i++;
//    }
//
//    System.out.println("\nAnalyzer3 Output:");
//    SequenceAnalyzer analyzer3 = new SequenceAnalyzer(ALICE, EVE);
//    BiPredicate<Region, Region> condition2 = (reg1, reg2) -> reg1.getLength() > 10;
//    WeightedList<Pair<Region, Region>> toPrint3 = analyzer3.match(0, MAX, condition2);
//    Iterator<Pair<Region, Region>> iter2 = toPrint3.iterator();
//
//    int j = 0;
//    while (iter2.hasNext()) {
//      System.out.println(iter2.next().toString());
//      j++;
//    }

    long start;
    long end;
    Predicate<Region> cond = reg -> reg.getEncoding().contains("AGA");

//    System.out.println("lSS 100,000 Time: ");
//    SequenceAnalyzer analyzer1 = new SequenceAnalyzer(ALICE, BOB);
//    start = System.currentTimeMillis();
//    String[] toPrint1 = analyzer1.longestSharedSequence(0, 100000);
//    end = System.currentTimeMillis();
//    System.out.print("" + (end - start) + " ms\n");
//
//    System.out.println("lSS 10,000,000 Time: ");
//    SequenceAnalyzer analyzer2 = new SequenceAnalyzer(ALICE, BOB);
//    start = System.currentTimeMillis();
//    String[] toPrint2 = analyzer2.longestSharedSequence(0, 10000000);
//    end = System.currentTimeMillis();
//    System.out.print("" + (end - start) + " ms\n");
//
//    System.out.println("lSS 100,000,000 Time: ");
//    SequenceAnalyzer analyzer3 = new SequenceAnalyzer(ALICE, BOB);
//    start = System.currentTimeMillis();
//    String[] toPrint3 = analyzer3.longestSharedSequence(0, 100000000);
//    end = System.currentTimeMillis();
//    System.out.print("" + (end - start) + " ms\n");
//
//    System.out.println("lSS 500,000,000 Time: ");
//    SequenceAnalyzer analyzer4 = new SequenceAnalyzer(ALICE, BOB);
//    start = System.currentTimeMillis();
//    String[] toPrint4 = analyzer4.longestSharedSequence(0, 500000000);
//    end = System.currentTimeMillis();
//    System.out.print("" + (end - start) + " ms\n");

    // break

//    System.out.println("fC 100,000 Time: ");
//    SequenceAnalyzer analyzer5 = new SequenceAnalyzer(ALICE, BOB);
//    start = System.currentTimeMillis();
//    WeightedList<Region> toPrint5 = analyzer5.findCandidates(0, 100000, true, cond);
//    end = System.currentTimeMillis();
//    System.out.print("" + (end - start) + " ms\n");
//

    System.out.println("fC 10,000 Time: ");
    SequenceAnalyzer analyzer7 = new SequenceAnalyzer(ALICE, BOB);
    start = System.currentTimeMillis();
    WeightedList<Region> toPrint7 = analyzer7.findCandidates(0, 10000, true, cond);
    end = System.currentTimeMillis();
    System.out.print("" + (end - start) + " ms\n");

    System.out.println("fC 1,000,000 Time: ");
    SequenceAnalyzer analyzer8 = new SequenceAnalyzer(ALICE, BOB);
    start = System.currentTimeMillis();
    WeightedList<Region> toPrint8 = analyzer8.findCandidates(0, 1000000, true, cond);
    end = System.currentTimeMillis();
    System.out.print("" + (end - start) + " ms\n");

    System.out.println("fC 10,000,000 Time: ");
    SequenceAnalyzer analyzer6 = new SequenceAnalyzer(ALICE, BOB);
    start = System.currentTimeMillis();
    WeightedList<Region> toPrint6 = analyzer6.findCandidates(0, 10000000, true, cond);
    end = System.currentTimeMillis();
    System.out.print("" + (end - start) + " ms\n");
  }
}
