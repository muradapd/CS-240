import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * Unit and performance tests for DuplicateTracker
 * 
 * @author Nathan Sprague
 */

public class DuplicateTrackerTest {

  /**
   * Generate random numbers with no repeats.
   */
  public ArrayList<Integer> randomSample(int howMany) {
    Random gen = new Random(100101);
    HashSet<Integer> seen = new HashSet<>();
    ArrayList<Integer> result = new ArrayList<>();

    while (result.size() < howMany) {
      int next = gen.nextInt();
      if (!seen.contains(next)) {
        seen.add(next);
        result.add(next);
      }
    }
    return result;
  }

  @Test
  public void testCorrectness() {
    DuplicateTracker tracker = new DuplicateTracker();


    ArrayList<Integer> allIds = randomSample(20);
    ArrayList<Integer> duplicates = new ArrayList<>(allIds.subList(0, 10));
    allIds.addAll(duplicates); // add some repeats
    allIds.addAll(duplicates); // add some more repeats
    
    Collections.shuffle(allIds);

    Collections.sort(duplicates);

    for (int cur : allIds) {
      tracker.addID(cur);
    }

    List<Integer> result = tracker.getDuplicates();

    assertEquals(duplicates, result);

  }

  @Test
  public void testPerformance() {

    int numGetDuplicateTrials = 200;
    long addTime = 0;
    long getTime = 0;

    int numUnique = 2000000;
    int numDups = 100000;

    DuplicateTracker tracker = new DuplicateTracker();
    System.out.println("GENERATING RANDOM DATA...");
    ArrayList<Integer> allIds = randomSample(numUnique);
    ArrayList<Integer> duplicates = new ArrayList<>(allIds.subList(0, numDups));
    allIds.addAll(duplicates); // add some repeats

    Collections.shuffle(allIds);

    Collections.sort(duplicates);

    System.out.println("TIMING DUPLICATE TRACKER ADDS...");
    long start = System.nanoTime();
    for (int cur : allIds) {
      tracker.addID(cur);
    }
    addTime = System.nanoTime() - start;

    System.out.println("TIMING DUPLICATE TRACKER GET DUPLICATES...");
    start = System.nanoTime();
    for (int i = 0; i < numGetDuplicateTrials; i++) {
      List<Integer> result = tracker.getDuplicates();
    }

    getTime = System.nanoTime() - start;


    System.out.printf("TOTAL TIME: %.2f seconds.\n", (addTime + getTime) / 1000000000.0);

    System.out.printf(
        "Add:        %8d operations performed in %4.2f seconds, %8.2f microseconds per call.\n",
        allIds.size(), addTime / 1000000000.0, addTime / 1000.0 / allIds.size());

    System.out.printf(
        "Duplicates: %8d operations performed in %4.2f seconds, %8.2f microseconds per call.\n",
        numGetDuplicateTrials, getTime / 1000000000.0,
        getTime / 1000.0 / numGetDuplicateTrials);

  }
}
