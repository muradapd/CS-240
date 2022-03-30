import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Simplified graph implementation. Uses a 2-dimensional matrix for edge
 * representation.
 *
 * @author Michael S. Kirkpatrick and Patrick Muradaz
 * @version 11/25/17
 */
public class Graph<T> implements Iterable<T> {

  private T[][] matrix;
  private int size;
  private final Integer empty = 0;
  private final Integer edge = 1;

  /**
   * Create a starter graph consisting of only vertices (no edges)
   */
  @SuppressWarnings("unchecked")
  public Graph(T[] vertices) {
    matrix = (T[][]) new Object[vertices.length + 1][vertices.length + 1];

    for (int i = 1; i < matrix.length; i++) {
      for (int j = 1; j < matrix.length; j++) {
        matrix[i][j] = (T) empty;
      }
    }
    for (int i = 1; i < matrix.length; i++) {
      matrix[i][0] = vertices[i - 1];
    }
    for (int i = 1; i < matrix.length; i++) {
      matrix[0][i] = vertices[i - 1];
    }
    matrix[0][0] = (T) " ";
    size = matrix.length - 1;
  }

  /**
   * Add a directed edge from vertex "from" to vertex "to"
   */
  @SuppressWarnings("unchecked")
  public void addDirected(T from, T to) {
    int[] edgeLoc = edgeLocation(from, to);
    matrix[edgeLoc[0]][edgeLoc[1]] = (T) edge;
  }

  /**
   * Add an undirected edge between the "from" and "to" vertices
   */
  @SuppressWarnings("unchecked")
  public void addUndirected(T from, T to) {
    int[] edgeLoc = edgeLocation(from, to);
    matrix[edgeLoc[0]][edgeLoc[1]] = (T) edge;
    matrix[edgeLoc[1]][edgeLoc[0]] = (T) edge;
  }

  /**
   * Checks to see if the "to" vertex is accessible from the "from" vertex. Note
   * that the order matters for directed graphs, but not for undirected graphs.
   */
  public boolean isAdjacent(T from, T to) {
    boolean retVal = false;
    int[] edgeLoc = edgeLocation(from, to);
    if (matrix[edgeLoc[0]][edgeLoc[1]] != empty) {
      retVal = true;
    }

    return retVal;
  }

  /**
   * Iterator for traversing through all vertices in the graph. You can leave the
   * remove() operation as UnsupportedOperationException.
   */
  @Override
  public Iterator<T> iterator() {
    return new GraphIterator<>();
  }

  @SuppressWarnings("hiding")
  class GraphIterator<T> implements Iterator<T> {

    int visited = 0;
    
    @Override
    public boolean hasNext() {
      return visited < size;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T next() {
      try {
        visited++;
        return (T) matrix[0][visited];
      } catch (ArrayIndexOutOfBoundsException e) {
        throw new NoSuchElementException();
      }
    }
  }

  /**
   * Iterator for traversing through all neighbors of a given vertex in the graph.
   * You can leave the remove() operation as UnsupportedOperationException. Note
   * that a vertex should NOT be considered a neighbor of itself, even if a
   * self-loop edge was added.
   */
  public Iterator<T> neighbors(T from) {
    return new NeighborIterator<>(from);
  }

  @SuppressWarnings("hiding")
  class NeighborIterator<T> implements Iterator<T> {
    
    List<Integer> neighbors = new ArrayList<>();
    int fromLoc;
    int visited = 0;
    
    @SuppressWarnings("unlikely-arg-type")
    public NeighborIterator(T from) {
      for (int i = 1; i < matrix.length; i++) {
        if (from.equals(matrix[i][0])) {
          fromLoc = i;
          break;
        }
      }
      for (int i = 1; i < matrix.length; i++) {
        if (matrix[fromLoc][i] == edge) {
          neighbors.add(i);
        }
      }
    }
    
    @Override
    public boolean hasNext() {
      return visited < neighbors.size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T next() {
      try {
      T retVal = (T) matrix[0][neighbors.get(visited)];
      visited++;
      return retVal;
      } catch (IndexOutOfBoundsException e) {
        throw new NoSuchElementException();
      }
    }
  }
  
  /**
   * Returns the element at the specified location.
   * 
   * @param location is the location of the element to return.
   * @return the element at the given location.
   */
  public int inEdges(T v) {
    int vLoc = 0;
    int inEdges = 0;
    
    for (int i = 1; i < matrix.length; i++) {
      if (v.equals(matrix[0][i])) {
        vLoc = i;
        break;
      }
    }
    for (int i = 0; i < matrix.length; i++) {
      if (matrix[i][vLoc] == edge) {
        inEdges++;
      }
    }
    return inEdges;
  }

  /**
   * Prints this matrix.
   */
  public void printMatrix() {
    for (T[] x : matrix) {
      for (T y : x) {
        System.out.print(y + " ");
      }
      System.out.println();
    }
    System.out.println();
  }
  
  /**
   * Getter for the vertices in the graph.
   * 
   * @return all vertices in the graph.
   */
  @SuppressWarnings("unchecked")
  public T[] vertices() {
    T[] vertices = (T[]) new Object[matrix.length - 1];
    
    for (int i = 1; i < matrix.length; i++) {
      vertices[i - 1] = matrix[0][i];
    }
    return vertices;
  }

  /**
   * Finds the location to add an edge.
   * 
   * @param from is the 'from' vertex.
   * @param t   is the 'to' vertex.
   * @return the location to add the edge as an array.
   */
  private int[] edgeLocation(T from, T t) {
    int[] edgeLoc = new int[2];

    for (int i = 1; i < matrix.length; i++) {
      if (from.equals(matrix[i][0])) {
        edgeLoc[0] = i;
        break;
      }
    }
    for (int i = 1; i < matrix.length; i++) {
      if (t.equals(matrix[0][i])) {
        edgeLoc[1] = i;
        break;
      }
    }
    return edgeLoc;
  }
}
