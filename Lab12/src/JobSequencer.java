import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Patrick Muradaz
 * Worked with Ryan Vinci, Ryan Showalter
 */
public class JobSequencer {
  
  HashMap<String, List<Integer>> map;
  
  /**
   * Constructor.
   */
  public JobSequencer() {
    map = new HashMap<>();
  }
  
  /**
   * Adds the specified jobID to the queue contained at the given type in the map.
   * 
   * @param jobType is the type of job to add.
   * @param jobID is the ID of the job to add.
   */
  public void addJob(String jobType, int jobID) {
    List<Integer> list = map.get(jobType);
    
    if (list == null) {
      list = new LinkedList<>();
    }

    list.add(jobID);
    map.put(jobType, list);
  }
  
  /**
   * Return the next job of the given type.
   * 
   * @param jobType is the type of the job to return.
   * @return the next job of the given type in the queue.
   */
  public int nextJob(String jobType) {
    List<Integer> list = map.get(jobType);
    
    return list.remove(0);
  }
}
