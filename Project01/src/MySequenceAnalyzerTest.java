import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tester class for SequenceAnalyzer.
 * 
 * @author Patrick Muradaz
 *
 */
public class MySequenceAnalyzerTest {

  Person alice = new Person("Alice");
  Person bob = new Person("Bob");
  Person carol = new Person("Carol");
  Person dave = new Person("Dave");
  Person eve = new Person("Eve");

  SequenceAnalyzer analyzer = new SequenceAnalyzer(alice, bob);

  @Test
  public void testExtend() {
    String[] toTest1 = analyzer.longestSharedSequence(0, 100);
    String[] tester1 = new String[] { "GAUG", "GATC" };
    for (int i = 0; i < tester1.length; i++) {
      assertEquals(tester1[i], toTest1[i]);
    }
    String[] toTest2 = analyzer.longestSharedSequence(0, 10000);
    String[] tester2 = new String[] { "ACGGCACUAGTGT" };
    for (int i = 0; i < tester2.length; i++) {
      assertEquals(tester2[i], toTest2[i]);
    }
    String[] toTest3 = analyzer.longestSharedSequence(0, 1000000);
    String[] tester3 = new String[] { "TAUGCTGCGGATTAGT", "GAUGGTGGCGGTTGCT", "GAUGAGGACGGATGTT" };
    for (int i = 0; i < tester3.length; i++) {
      assertEquals(tester3[i], toTest3[i]);
    }

  }
}
