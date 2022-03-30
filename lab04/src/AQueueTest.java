import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Array-Queue Lab.
 * @author Nathan Sprague
 * @version 9/26/2016
 *
 *
 */
public class AQueueTest {

  @Test
  public void testDequeueFromEmpty() {
    AQueue<String> queue = new AQueue<>();
    assertEquals(null, queue.dequeue());
    assertEquals(0, queue.length());
  }

  @Test
  public void testEnqueDequeueNoWrap() {
    AQueue<String> queue = new AQueue<>(100);
    queue.enqueue("A");
    queue.enqueue("B");
    queue.enqueue("C");

    assertEquals("A", queue.dequeue());
    assertEquals("B", queue.dequeue());
    assertEquals("C", queue.dequeue());
  }

  @Test
  public void testEnqueDequeueWithWrap() {
    AQueue<String> queue = new AQueue<>(3);
    queue.enqueue("A");
    queue.enqueue("B");
    queue.enqueue("C");

    assertEquals("A", queue.dequeue());
    assertEquals("B", queue.dequeue());
    assertEquals("C", queue.dequeue());

    queue.enqueue("D");
    queue.enqueue("E");
    queue.enqueue("F");

    assertEquals("D", queue.dequeue());
    assertEquals("E", queue.dequeue());
    assertEquals("F", queue.dequeue());

    assertEquals(3, ((Object[])queue.queueArray).length);
  }

  @Test
  public void testFrontValueAfterEnqueue() {
    AQueue<String> queue = new AQueue<>();
    queue.enqueue("A");
    assertEquals("A", queue.frontValue());
    queue.enqueue("B");
    assertEquals("A", queue.frontValue());
    queue.enqueue("C");
    assertEquals("A", queue.frontValue());


  }

  @Test
  public void testFrontValueAfterDequeue() {
    AQueue<String> queue = new AQueue<>();
    queue.enqueue("A");
    queue.enqueue("B");
    queue.enqueue("C");

    assertEquals("A", queue.frontValue());
    queue.dequeue();
    assertEquals("B", queue.frontValue());
    queue.dequeue();
    assertEquals("C", queue.frontValue());
  }

  @Test
  public void testClear() {
    AQueue<String> queue = new AQueue<>();
    queue.enqueue("A");
    queue.enqueue("B");
    queue.enqueue("C");

    queue.clear();
    assertEquals(0, queue.length());
  }

  @Test
  public void testLengthAfterEnqueue() {
    AQueue<String> queue = new AQueue<>(100);
    queue.enqueue("A");
    queue.enqueue("B");
    queue.enqueue("C");

    assertEquals(3, queue.length());
    assertEquals(100, ((Object[])queue.queueArray).length);
  }

  @Test
  public void testLengthAfterEnqueueAndDequeueNoWrap() {
    AQueue<String> queue = new AQueue<>(100);
    queue.enqueue("A");
    queue.enqueue("B");
    queue.enqueue("C");
    queue.dequeue();
    queue.dequeue();

    assertEquals(1, queue.length());
    assertEquals(100, ((Object[])queue.queueArray).length);
  }

  @Test
  public void testLengthAfterEnqueueAndDequeueWithWrap() {
    AQueue<String> queue = new AQueue<>(3);
    queue.enqueue("A");
    queue.enqueue("B");
    queue.enqueue("C");
    queue.dequeue();
    queue.dequeue();
    queue.enqueue("D");
    queue.enqueue("E");

    assertEquals(3, queue.length());
    assertEquals(3, ((Object[])queue.queueArray).length);
  }

  @Test
  public void testLengthAfterGrowingArray() {
    AQueue<String> queue = new AQueue<>(3);
    queue.enqueue("A");
    queue.enqueue("B");
    queue.enqueue("C");
    assertEquals(3, queue.length());
    assertEquals(3, ((Object[])queue.queueArray).length);
    queue.enqueue("D");
    queue.enqueue("E");
    queue.enqueue("F");
    assertEquals(6, queue.length());
    assertNotEquals(3, ((Object[])queue.queueArray).length);
  }

}
