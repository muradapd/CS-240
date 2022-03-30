import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GraphTest {

  private Graph<String> graph;
  
  @BeforeEach
  public void setUp() throws Exception {
    String[] vertices = {"A", "B", "C", "D", "E"};
    graph = new Graph<>(vertices);
  }

  @Test
  public void testIsAdjacentAfterAddDirected() {
    assertFalse(graph.isAdjacent("A", "B"));
    graph.addDirected("A", "B");
    assertTrue(graph.isAdjacent("A", "B"));
    assertFalse(graph.isAdjacent("B", "A"));
  }
  
  @Test
  public void testIsAdjacentAfterAddUndirected() {
    assertFalse(graph.isAdjacent("A", "B"));
    graph.addUndirected("A", "B");
    assertTrue(graph.isAdjacent("A", "B"));
    assertTrue(graph.isAdjacent("B", "A"));
    assertFalse(graph.isAdjacent("A", "C"));
  }
  
  @Test
  public void testIteratorHasNextAndNext() {
    String[] vertices = {"A", "B", "C"};
    Graph<String> graph = new Graph<>(vertices);
    Iterator<String> iter = graph.iterator();
    
    assertTrue(iter.hasNext());
    assertTrue(iter.hasNext());
    assertEquals("A", iter.next());
    assertEquals("B", iter.next());
    assertTrue(iter.hasNext());
    assertEquals("C", iter.next());
    assertFalse(iter.hasNext());
  }
  
  @Test
  public void testIteratorThrowsExceptionIfNextCalledWhenHasNextFalse() {
    String[] vertices = {"A"};
    Graph<String> graph = new Graph<>(vertices);
    Iterator<String> iter = graph.iterator();
    iter.next();
    assertThrows(NoSuchElementException.class, () -> iter.next());
  }
  
  @Test
  public void testNeighborIteratorSingleNeighbor() {
    graph.addDirected("A", "D");
    
    Iterator<String> iter = graph.neighbors("A");
    
    assertTrue(iter.hasNext());
    assertTrue(iter.hasNext());
    assertEquals("D", iter.next());
    assertFalse(iter.hasNext());
  }
  
  @Test
  public void testNeighborIteratorMultipleNeighbors() {
    graph.addDirected("C", "D");
    graph.addDirected("C", "A");
    graph.addDirected("C", "B");
    
    HashSet<String> expectedNeighbors = new HashSet<>();
    expectedNeighbors.add("D");
    expectedNeighbors.add("A");
    expectedNeighbors.add("B");
    
    Iterator<String> iter = graph.neighbors("C");
    HashSet<String> actualNeighbors = new HashSet<>();
    
    while (iter.hasNext()) {
      assertTrue(actualNeighbors.add(iter.next())); //false if already there.
    }
    
    assertEquals(expectedNeighbors, actualNeighbors);
    assertFalse(iter.hasNext());
    
  }
  
  @Test
  public void testNeighborsIteratorThrowsExceptionIfNextCalledWhenHasNextFalse() {
    String[] vertices = {"A"};
    Graph<String> graph = new Graph<>(vertices);
    Iterator<String> iter = graph.neighbors("A");
    assertThrows(NoSuchElementException.class, () -> iter.next());
  }
  
  
}
