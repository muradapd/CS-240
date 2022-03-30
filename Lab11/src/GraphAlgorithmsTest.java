import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Simple tests for the GraphAlgorithms class
 * @author Nathan Sprague
 *
 */
public class GraphAlgorithmsTest {

  private Graph<String> oneVertexGraph;
  private Graph<String> fullGraph;
  private Graph<String> disconnectedGraph;
  private Graph<String> circleDirectedGraph;
  private Graph<String> circleUndirectedGraph;
  private Graph<String> star;


  @BeforeEach
  public void setUp() throws Exception {
    oneVertexGraph = new Graph<String>(new String[] {"A"});

    fullGraph = new Graph<String>(new String[] {"A", "B", "C", "D", "E"});
    for (String from : fullGraph) {
      for (String to : fullGraph) {
        if (!from.equals(to)) {
          fullGraph.addDirected(from, to);
        }
      }
    }

    disconnectedGraph = new Graph<String>(new String[] {"A", "B", "C", "D", "E"});
    disconnectedGraph.addUndirected("A", "B");
    disconnectedGraph.addUndirected("C", "D");
    disconnectedGraph.addUndirected("C", "E");


    circleDirectedGraph = new Graph<String>(new String[] {"A", "B", "C", "D"});
    circleDirectedGraph.addDirected("A", "B");
    circleDirectedGraph.addDirected("B", "C");
    circleDirectedGraph.addDirected("C", "D");
    circleDirectedGraph.addDirected("D", "A");
    
    circleUndirectedGraph = new Graph<String>(new String[] {"A", "B", "C", "D"});
    circleUndirectedGraph.addUndirected("A", "B");
    circleUndirectedGraph.addUndirected("B", "C");
    circleUndirectedGraph.addUndirected("C", "D");
    circleUndirectedGraph.addUndirected("D", "A");
    
    star = new Graph<String>(new String[] {"A", "B", "C", "D"});
    star.addDirected("A", "B");
    star.addDirected("A", "C");
    star.addDirected("A", "D");

  }

  @Test
  public void testGetOutDegree() {

    assertEquals(0, GraphAlgorithms.getOutDegreeOfVertex(oneVertexGraph, "A"));
    
    for (String vertex : circleDirectedGraph) {
      assertEquals(1, GraphAlgorithms.getOutDegreeOfVertex(circleDirectedGraph, vertex));
    }
    
    assertEquals(3, GraphAlgorithms.getOutDegreeOfVertex(star, "A"));
    assertEquals(0, GraphAlgorithms.getOutDegreeOfVertex(star, "B"));
    assertEquals(0, GraphAlgorithms.getOutDegreeOfVertex(star, "C"));
    assertEquals(0, GraphAlgorithms.getOutDegreeOfVertex(star, "D"));
    
    for (String vertex : fullGraph) {
      assertEquals(4, GraphAlgorithms.getOutDegreeOfVertex(fullGraph, vertex));
    }
   
  }
  
  @Test
  public void testGetInDegree() {

    assertEquals(0, GraphAlgorithms.getInDegreeOfVertex(oneVertexGraph, "A"));
    
    for (String vertex : circleDirectedGraph) {
      assertEquals(1, GraphAlgorithms.getInDegreeOfVertex(circleDirectedGraph, vertex));
    }
    
    assertEquals(0, GraphAlgorithms.getInDegreeOfVertex(star, "A"));
    assertEquals(1, GraphAlgorithms.getInDegreeOfVertex(star, "B"));
    assertEquals(1, GraphAlgorithms.getInDegreeOfVertex(star, "C"));
    assertEquals(1, GraphAlgorithms.getInDegreeOfVertex(star, "D"));
    
    for (String vertex : fullGraph) {
      assertEquals(4, GraphAlgorithms.getInDegreeOfVertex(fullGraph, vertex));
    }
  }
  
  @Test
  public void testIsConnected() {
    assertTrue(GraphAlgorithms.isConnected(oneVertexGraph));
    assertTrue(GraphAlgorithms.isConnected(fullGraph));
    assertTrue(GraphAlgorithms.isConnected(circleDirectedGraph));
    Graph<String> noEdgeGraph = new Graph<String>(new String[] {"A", "B", "C", "D", "E"});
    assertFalse(GraphAlgorithms.isConnected(noEdgeGraph));
    assertFalse(GraphAlgorithms.isConnected(disconnectedGraph));
  }
 

}
