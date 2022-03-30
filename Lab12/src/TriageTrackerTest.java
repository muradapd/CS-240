import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Correctness and performance tests for TriageTracker
 * 
 * @author Nathan Sprague
 *
 */
public class TriageTrackerTest {

  private TriageTracker triage;

  @BeforeEach
  public void setUp() throws Exception {
    triage = new TriageTracker();
  }

  @Test
  public void testCorrectnessNoDuplicatesNoRemoval() {

    triage.addPatient("A", 0);
    triage.addPatient("B", 1000000000);
    triage.addPatient("C", 5);
    triage.addPatient("D", 2);

    assertEquals("B", triage.nextPatient());
    assertEquals("C", triage.nextPatient());
    assertEquals("D", triage.nextPatient());
    assertEquals("A", triage.nextPatient());
  }

  @Test
  public void testCorrectnessWithRemovalNoDuplicates() {

    triage.addPatient("A", 0);
    triage.addPatient("B", 10);
    triage.addPatient("C", 5);
    triage.addPatient("D", 1);
    triage.addPatient("E", 2);
    triage.addPatient("F", 7);

    triage.removePatient("A");
    triage.removePatient("C");
    triage.removePatient("F");

    assertEquals("B", triage.nextPatient());
    assertEquals("E", triage.nextPatient());
    assertEquals("D", triage.nextPatient());

  }

  @Test
  public void testCorrectnessWithDuplicatesNoRemoval() {

    triage.addPatient("A", 0);
    triage.addPatient("B", 10);
    triage.addPatient("C", 0);
    triage.addPatient("D", 10);
    triage.addPatient("E", 10);
    triage.addPatient("F", 0);

    assertEquals("B", triage.nextPatient());
    assertEquals("D", triage.nextPatient());
    assertEquals("E", triage.nextPatient());

    assertEquals("A", triage.nextPatient());
    assertEquals("C", triage.nextPatient());
    assertEquals("F", triage.nextPatient());

  }

  @Test
  public void testCorrectnessWithDuplicatesAndRemoval() {

    triage.addPatient("A", 10);
    triage.addPatient("B", 0);
    triage.addPatient("C", 0);
    triage.addPatient("D", 10);
    triage.addPatient("E", 10);
    triage.addPatient("F", 0);

    triage.removePatient("B");
    triage.removePatient("C");

    assertEquals("A", triage.nextPatient());
    assertEquals("D", triage.nextPatient());
    assertEquals("E", triage.nextPatient());

    assertEquals("F", triage.nextPatient());

    triage.addPatient("A", 3);
    triage.addPatient("B", 1);
    triage.addPatient("C", 1);
    triage.addPatient("D", 2);

    triage.removePatient("B");
    triage.removePatient("C");

    assertEquals("A", triage.nextPatient());
    assertEquals("D", triage.nextPatient());

  }

  @Test
  public void testPerformance() {
    int numPatients = 2000000;
    long addTime = 0;

    long removeTime = 0;
    int removeCounter = 0;
    long nextTime = 0;
    int nextCounter = 0;


    System.out.println("TRIAGE GENERATING RANDOM PRIORITIES...");
    // First, generate random priorities...
    Random gen = new Random(100101);
    ArrayList<Integer> priorities = new ArrayList<>();
    for (int i = 0; i < numPatients; i++) {
      priorities.add(gen.nextInt(numPatients / 2)); // guarantees some
                                                    // duplicates
    }

    System.out.println("TIMING TRIAGE ADD...");

    // TIME THE ADDS...
    long start = System.nanoTime();
    for (int i = 0; i < numPatients; i++) {
      triage.addPatient("" + i, priorities.get(i));
    }
    addTime = System.nanoTime() - start;

    System.out.println("TIMING TRIAGE REMOVE...");
    // NOW TIME REMOVAL OF 40%
    start = System.nanoTime();
    for (int i = numPatients / 10; i < 5 * numPatients / 10; i++) {
      triage.removePatient("" + i);
      removeCounter++;
    }
    removeTime = System.nanoTime() - start;

    System.out.println("TIMING TRIAGE NEXT...");
    // NOW TIME NEXT CALL OF REMAINING 60%
    start = System.nanoTime();
    for (int i = 0; i < 6 * numPatients / 10; i++) {
      triage.nextPatient();
      nextCounter++;
    }
    nextTime = System.nanoTime() - start;


    System.out.printf("\nTOTAL TIME: %.2f seconds.\n",
        (addTime + removeTime + nextTime) / 1000000000.0);

    System.out.printf(
        "Add:    %8d operations performed in %4.2f seconds, %8.3f microseconds per call.\n",
        numPatients, addTime / 1000000000.0, addTime / 1000.0 / numPatients);

    System.out.printf(
        "Remove: %8d operations performed in %4.2f seconds, %8.3f microseconds per call.\n",
        removeCounter, removeTime / 1000000000.0, removeTime / 1000.0 / removeCounter);


    System.out.printf(
        "Next:   %8d operations performed in %4.2f seconds, %8.3f microseconds per call.\n",
        nextCounter, nextTime / 1000000000.0, nextTime / 1000.0 / nextCounter);



  }

}
