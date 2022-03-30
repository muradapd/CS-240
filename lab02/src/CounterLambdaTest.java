import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CounterLambdaTest {

  @Test
  public void testCounterAdderPositive() {
    Counter<String> c = new Counter<>("foo");
    CounterLambda<String> lambda = new CounterLambda<>(c);

    assertEquals(11, lambda.adder().apply(10).intValue(),
        "Result of apply should be 11");
    assertEquals(11, c.count(), "Counter should be modified");
  }

  @Test
  public void testCounterAdderNegative() {
    Counter<String> c = new Counter<>("foo");
    CounterLambda<String> lambda = new CounterLambda<>(c);

    assertEquals(11, lambda.adder().apply(10).intValue(),
        "Result of apply should be 11");
    assertEquals(11, c.count(), "Counter should be modified");
    assertEquals(8, lambda.adder().apply(-3).intValue(),
        "Result of apply should be 8");
    assertEquals(8, c.count(), "Counter should be modified");
  }

  @Test
  public void testCounterCompare() {
    Counter<String> c1= new Counter<>("foo");
    CounterLambda<String> l1 = new CounterLambda<>(c1);
    assertEquals(11, l1.adder().apply(10).intValue(),
        "Result of apply should be 11");

    Counter<String> c2 = new Counter<>("bar");
    CounterLambda<String> l2 = new CounterLambda<>(c2);
    assertEquals(6, l2.adder().apply(5).intValue(),
        "Result of apply should be 6");

    assertEquals(5, l1.compare().apply(c2).intValue(), "Result should be 5");
    assertEquals(-5, l2.compare().apply(c1).intValue(), "Result should be -5");

    assertEquals(11, l2.adder().apply(5).intValue(),
        "Result of apply should be 11");
    assertEquals(0, l2.compare().apply(c1).intValue(), "Result should be 0");
    assertEquals(0, l1.compare().apply(c2).intValue(), "Result should be 0");
  }

  @Test
  public void testCounterEquals() {
    Counter<String> c1= new Counter<>("foo");
    CounterLambda<String> l1 = new CounterLambda<>(c1);
    assertEquals(11, l1.adder().apply(10).intValue(),
        "Result of apply should be 11");

    Counter<String> c2 = new Counter<>("bar");
    CounterLambda<String> l2 = new CounterLambda<>(c2);
    assertEquals(6, l2.adder().apply(5).intValue(),
        "Result of apply should be 6");

    assertFalse(l1.equals().apply(c2), "Counters should not be equal");
    assertFalse(l2.equals().apply(c1), "Counters should not be equal");

    assertEquals(11, l2.adder().apply(5).intValue(),
        "Result of apply should be 11");

    assertFalse(l1.equals().apply(c2), "Counters should not be equal");
    assertFalse(l2.equals().apply(c1), "Counters should not be equal");

    Counter<String> c3 = new Counter<>("bar");
    CounterLambda<String> l3 = new CounterLambda<>(c3);
    assertEquals(6, l3.adder().apply(5).intValue(),
        "Result of apply should be 6");

    assertFalse(l3.equals().apply(c2), "Counters should not be equal");
    assertFalse(l2.equals().apply(c3), "Counters should not be equal");

    assertEquals(11, l3.adder().apply(5).intValue(),
        "Result of apply should be 11");

    assertTrue(l3.equals().apply(c2), "Counters should be equal");
    assertTrue(l2.equals().apply(c3), "Counters should be equal");

  }

}

