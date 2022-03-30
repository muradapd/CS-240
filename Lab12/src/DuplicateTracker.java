import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Patrick Muradaz
 * Worked with Ryan Vinci, Ryan Showalter
 */
public class DuplicateTracker {
 
  HashMap<Integer, Boolean> map;
  List<Integer> duplicates;
  
  /**
   * Constructor.
   */
  public DuplicateTracker() {
    map = new HashMap<>();
    duplicates = new ArrayList<Integer>();
  }
  
  /**
   * Adds the given ID to the map.
   * 
   * @param id is the ID to add to the map.
   */
  public void addID(int id) {
	if (map.containsKey(id)) {
	  if (!map.get(id)) {
		  duplicates.add(id);
	  }
      map.put(id, true);
    } else {
      map.put(id, false);
    }
  }
  
  /**
   * Returns the list of duplicate IDs in the map.
   * 
   * @return all duplicates supplied to the map.
   */
  public List<Integer> getDuplicates() {
    duplicates.sort(null);
    return duplicates;
  }
}
