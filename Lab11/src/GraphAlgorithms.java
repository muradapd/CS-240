import java.util.HashMap;
import java.util.Iterator;

/**
 * Utility class providing some basic graph algorithms using the graph class.
 * 
 * (Based on an earlier lab designed by John Bowers.)
 * 
 * @author Nathan Sprague and ??
 *
 */

public class GraphAlgorithms {
  
  /**
   * Return the out degree of vertex v. The out degree is the number of edges
   * from the vertex. For an undirected graph, this is the same as the out degree.
   */
  public static <T> int getOutDegreeOfVertex(Graph<T> graph, T vertex) {
    Iterator<T> iter = graph.neighbors(vertex);
    int outDeg = 0;
    
    while(iter.hasNext()) {
      iter.next();
      outDeg++;
    }
    return outDeg;
  }

  /**
   * Return the in degree of vertex v. The in degree is the number of edges
   * into the vertex. For an undirected graph, this is the same as the out degree.
   */
  public static <T> int getInDegreeOfVertex(Graph<T> graph, T vertex) {
    return graph.inEdges(vertex);
  }


  /**
   * Returns true if the graph is connected. A graph is connected if a traversal
   * would visit all nodes in the graph.
   */
  public static <T> boolean isConnected(Graph<T> graph) {
    HashMap<T, Boolean> map = makeVisitedMap(graph);
    T[] vertices = graph.vertices();
    DFSHelper(graph, vertices[0], map);
    boolean retVal = true;
    
    for (T vertex : vertices) {
      if (!map.get(vertex)) {
        retVal = false;
      }
    }
    
    return retVal;
  }


  /**
   * Returns a HashMap that maps from each vertex to false. This is useful for
   * calling the DFSHelper method below.
   */
  public static <T> HashMap<T, Boolean> makeVisitedMap(Graph<T> graph) {
    HashMap<T, Boolean> visited = new HashMap<>();
    for (T vertex : graph) {
      visited.put(vertex, false);
    }
    return visited;
  }

  /**
   * A slightly modified DFS from your book. Here you pass a HashMap that maps
   * from each vertex to a boolean. A vertex is considered visited if it maps to
   * true, otherwise it is considered not visited. To do a normal DFS, just call
   * DFSHelper(graph, vertex, makeVisitedMap(graph)).
   */
  public static <T> void DFSHelper(Graph<T> graph, T vertex, HashMap<T, Boolean> visited) {

    visited.put(vertex, true);
    Iterator<T> iter = graph.neighbors(vertex);
    while (iter.hasNext()) {
      T neighbor = iter.next();
      if (!visited.get(neighbor)) {
        DFSHelper(graph, neighbor, visited);
      }
    }
  }



}
