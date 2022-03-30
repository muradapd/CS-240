import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SingleTest
{

	@Test
	public void testSingleConstructorRuns()
	{
		Single<Integer> s1 = new Single<>(5);
		Single<String> s2 = new Single<>("hello");
	}

	@Test
	public void testSingleToString()
	{
		Single<String> s1 = new Single<>("hello");
		assertEquals("hello", s1.toString());
		Single<Integer> s2 = new Single<>(5);
		assertEquals("5", s2.toString());
	}

}
