import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author Patrick Muradaz
 * Worked with Ryan Vinci, Ryan Showalter
 */
public class TriageTracker {

  HashMap<Integer, List<String>> integerMap;
  HashMap<String, Integer> stringMap;
  PriorityQueue<Integer> maxHeap;
  
  /**
   * Constructor.
   */
  public TriageTracker() {
    integerMap = new HashMap<>();
    stringMap = new HashMap<>();
    maxHeap = new PriorityQueue<>(Collections.reverseOrder());
  }

  /**
   * Adds a patient to the heap along with the priority of the case.
   * 
   * @param id       is the patient ID.
   * @param priority is the priority of that patients case.
   */
  public void addPatient(String id, int priority) {
    List<String> list = integerMap.get(priority);
    
    if (list == null) {
      list = new LinkedList<>();
    }

    list.add(id);
    integerMap.put(priority, list);
    stringMap.put(id, priority);
    maxHeap.add(priority);
  }

  /**
   * Returns the next patient based on priority.
   * 
   * @return the next patient in the heap.
   */
  public String nextPatient() {
    String patient = null; // initialize null, method will never return null
    boolean done = false; // used to tell when the system has found the next existing patient
    
    while (!done) {
      try {
        patient = integerMap.get(maxHeap.remove()).remove(0);
        done = true;
      } catch (IndexOutOfBoundsException ex) {
        // do nothing, move on to next element
      }
    }
    
    return patient;
  }

  /**
   * Removes the patient with the given ID.
   * 
   * @param id the ID of the patient to remove. 
   */
  public void removePatient(String id) {
    integerMap.get(stringMap.get(id)).remove(0);
  }
}
