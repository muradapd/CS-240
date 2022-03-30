import java.util.HashMap;

/**
 * @author Patrick Muradaz
 * Worked with Ryan Vinci, Ryan Showalter
 */
public class EventSequencer {
  
  HashMap<Integer, String> map;
  int time;

  /**
   * Constructor.
   */
  public EventSequencer() {
    map = new HashMap<>();
    time = 0;
  }

  /**
   * Adds the given event and timeStamp to the HashMap.
   * 
   * @param timeStamp is the time the event occurs.
   * @param event is the event occurring at this time.
   */
  public void addEvent(int timeStamp, String event) {
    map.put(timeStamp, event);
  }

  /**
   * Returns the event that occurs next.
   * 
   * @return the next event.
   */
  public String nextEvent() {
    String event = map.get(time);
    time++;
    
    if (event == null) {
      return nextEvent();
    }
    
    return event;
  }
}