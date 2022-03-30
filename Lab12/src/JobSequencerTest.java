import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


/**
 * Correctness and performance tests for JobSequencer
 * @author Nathan Sprague
 *
 */
public class JobSequencerTest {

  
  @Test
  public void testCorrectness() {
    String typesIn = "ABCDEF";
    String typesOut = "FEDCBA";
    JobSequencer sequencer = new JobSequencer();

    for (int i = 0; i < typesIn.length(); i++) {
      for (int j = 0; j < 10; j++) {
        sequencer.addJob(typesIn.substring(i, i + 1), typesIn.charAt(i) * 100 + j);
      }
    }

    for (int i = 0; i < typesOut.length(); i++) {
      for (int j = 0; j < 10; j++) {
        int jobID = sequencer.nextJob(typesIn.substring(i, i + 1));
        assertEquals(typesIn.charAt(i) * 100 + j, jobID);
      }
    }
  }

  @Test
  public void testPerfomance() {
    long addTime = 0;
    int addCounter = 0;
    long getTime = 0;
    int getCounter = 0;

    JobSequencer sequencer = new JobSequencer();

    String typesIn = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    typesIn = typesIn + typesIn.toLowerCase();
    String typesOut = "";

    for (int i = typesIn.length() - 1; i >= 0; i--) {
      typesOut += typesIn.substring(i, i + 1);
    }
    System.out.println("TIMING JOB SEQUENCER...");
    for (int trial = 0; trial < 50; trial++) {
      
      long start = System.nanoTime();
      for (int i = 0; i < typesIn.length(); i++) {
        for (int j = 0; j < 10000; j++) {
          sequencer.addJob(typesIn.substring(i, i + 1), typesIn.charAt(i) * 100 + j);
          addCounter++;
        }
      }
      addTime += (System.nanoTime() - start);
      
     
      start = System.nanoTime();
      
      for (int i = 0; i < typesOut.length(); i++) {
        for (int j = 0; j < 10000; j++) {
          int jobID = sequencer.nextJob(typesIn.substring(i, i + 1));
          getCounter++;
        }
      }
      getTime += (System.nanoTime() - start);
    }
    
    System.out.printf("TOTAL TIME: %.2f seconds.\n", (addTime + getTime)/1000000000.0);

    System.out.printf(
        "Add:  %8d operations performed in %4.2f seconds, %8.3f microseconds per call.\n",
       addCounter, addTime / 1000000000.0, addTime / 1000.0 / addCounter);

    System.out.printf(
        "Next: %8d operations performed in %4.2f seconds, %8.3f microseconds per call.\n",
        getCounter, getTime / 1000000000.0,
        getTime / 1000.0 / getCounter);
    

  }
}
