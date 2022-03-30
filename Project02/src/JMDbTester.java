import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

public class JMDbTester {

  /**
   * ======================== Build test variables ========================.
   */
  private String starter = new String("starter.csv");
  private String medium = new String("medium.csv");
  private String imdb = new String("imdb.csv");

  private String bubble = "bubble";
  private String insert = "insertion";
  private String select = "selection";
  private String merge = "merge";
  private String quick = "quick";

  /** ============================== Tests ==============================. */
  @Test
  public void testStarterSearch() throws IOException {
    List<Movie> list;
    System.out.println("/** ====================== testStarterSearch ====================== */");
    Movie rocky = new Movie("tt0075148", "Rocky", 8.1, 120, 1976, null);
    // don't care about genres
    Movie taxiDriver = new Movie("tt0075314", "Taxi Driver", 8.3, 114, 1976, null);

    list = JMDb.run(new String[] { starter, bubble,
        "runtime 100 120 AND year 1970 1980 AND genre Crime Sport" });

    assertEquals(list.get(0).getIdentifier(), taxiDriver.getIdentifier());
    assertEquals(list.get(1).getIdentifier(), rocky.getIdentifier());

    list = JMDb.run(new String[] { starter, insert,
        "runtime 100 120 AND year 1970 1980 AND rating 8.1 8.2 AND genre Crime Sport" });

    assertEquals(list.get(0).getIdentifier(), rocky.getIdentifier());
  }

  @Test
  public void testMediumSearch() throws IOException {
    List<Movie> list;
    System.out.println("\n/** ====================== testMediumSearch ====================== */");
    Movie taxiDriver = new Movie("tt0075314", "Taxi Driver", 8.3, 114, 1976, null);
    Movie rocky = new Movie("tt0075148", "Rocky", 8.1, 120, 1976, null);

    list = JMDb.run(new String[] { medium, select, "title \"Rocky\"" });

    assertEquals(list.get(0).getIdentifier(), rocky.getIdentifier());

    list = JMDb.run(new String[] { medium, merge, "identifier tt0075148" });

    assertEquals(list.get(0).getIdentifier(), rocky.getIdentifier());

    list = JMDb.run(
        new String[] { medium, quick, "year 1970 1977 AND genre Crime Sport AND rating 8.1 8.2" });

    assertEquals(list.get(0).getIdentifier(), rocky.getIdentifier());
  }

  @Test
  public void testFullSearch() throws IOException {
    List<Movie> list;
    System.out.println("\n/** ====================== testFullSearch ====================== */");
    Movie riderOnTheRain = new Movie("tt0064791", "Rider On The Rain", 7.1, 120, 1970, null);

    list = JMDb.run(new String[] { imdb, merge,
        "runtime 120 120 AND year 1970 1971 AND genre Mystery AND rating 7.0 7.2" });

    assertEquals(list.get(0).getIdentifier(), riderOnTheRain.getIdentifier());
  }
}
