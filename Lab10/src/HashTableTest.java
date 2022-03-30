import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Minimal tests for a simple HashTable class.
 * 
 * @author Nathan Sprague
 *
 */
public class HashTableTest {
  private HashTable<Integer, Integer> table;

  @BeforeEach
  public void setUp() throws Exception {
    table = new HashTable<>();

  }
  
  // TESTS FOR PUT AND GET WITH NO REHASHING OR REMOVAL

  @Test
  public void testPutGetSingleItem() {
    HashTable<String, String> sTable = new HashTable<>();
    sTable.put("A", "B");
    assertEquals(1, sTable.size());
    assertEquals("B", sTable.get("A"));

  }

  @Test
  public void testPutGetSingleItemPutReplacesOldValue() {
    HashTable<String, String> sTable = new HashTable<>();
    sTable.put("A", "B");
    assertEquals(1, sTable.size());
    assertEquals("B", sTable.get("A"));
    sTable.put("A", "C");
    assertEquals(1, sTable.size());
    assertEquals("C", sTable.get("A"));

  }

  @Test
  public void testPutSeveralItemsGetReturnsNull() {
    for (int i = 0; i < HashTable.INITIAL_CAPACITY / 2; i++) {
      table.put(i, i + 100);
      assertEquals(i + 1, table.size());
    }

    for (int i = HashTable.INITIAL_CAPACITY / 2; i < 1000; i++) { // is the i < 1000 a typo?
      assertEquals(null, table.get(i));
    }

  }

  @Test
  public void testPutGetSeveralItemsNoReshashingNeededIntegers() {
    for (int i = 0; i < HashTable.INITIAL_CAPACITY / 2; i++) {
      table.put(i, i + 100);
      assertEquals(i + 1, table.size());
    }
    for (int i = 0; i < HashTable.INITIAL_CAPACITY / 2; i++) {
      assertEquals(i + 100, table.get(i).intValue());
    }
  }

  @Test
  public void testPutGetSeveralItemsManyDataSets() {
    HashTable<String, Integer> sTable;
    for (int j = 1; j < 100; j++) {

      sTable = new HashTable<>();
      for (int i = 0; i < HashTable.INITIAL_CAPACITY / 2; i++) {
        sTable.put(new String("The  string: " + i * j), i + 100);
        assertEquals(i + 1, sTable.size());
      }
      for (int i = 0; i < HashTable.INITIAL_CAPACITY / 2; i++) {
        assertEquals(i + 100, sTable.get(new String("The  string: " + i * j)).intValue());
      }
    }
  }

  // REMOVING TESTS...

  @Test
  public void testPutGetRemoveSingleItem() {
    HashTable<String, String> sTable = new HashTable<>();
    sTable.put("A", "B");
    assertEquals(1, sTable.size());
    assertEquals("B", sTable.get("A"));
    sTable.remove("A");
    assertEquals(0, sTable.size());
    assertEquals(null, sTable.get("A"));
  }

  @Test
  public void testRemoveSingleItemTwice() {
    HashTable<String, String> sTable = new HashTable<>();
    sTable.put("A", "B");
    assertEquals(1, sTable.size());
    assertEquals("B", sTable.get("A"));
    assertEquals("B", sTable.remove("A"));
    assertEquals(0, sTable.size());
    assertEquals(null, sTable.get("A"));
    assertEquals(null, sTable.remove("A"));
  }

  // REHASHING TESTS...

  @Test
  public void testPutCausesRehashAtCorrectLoad() {

    assertEquals(HashTable.INITIAL_CAPACITY, table.table.length);

    for (int i = 0; i < HashTable.INITIAL_CAPACITY / 2; i++) { // is the init_cap / 2 a typo?
      table.put(i, i + 100);
      assertEquals(HashTable.INITIAL_CAPACITY, table.table.length);
    }
    table.put(11, 50);
    assertEquals(HashTable.INITIAL_CAPACITY * 2, table.table.length);

  }

  @Test
  public void testPutGetReshashingNeeded() {

    for (int i = 0; i < HashTable.INITIAL_CAPACITY + 1; i++) {
      table.put(i, i + 100);
      assertEquals(i + 1, table.size());
    }
    for (int i = 0; i < HashTable.INITIAL_CAPACITY + 1; i++) {
      assertEquals(i + 100, table.get(i).intValue());
    }
  }


}
