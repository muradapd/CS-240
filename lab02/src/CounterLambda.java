import java.util.function.Function;
import java.util.function.UnaryOperator;

public class CounterLambda<T>
{

	Counter<T> counter;

	public CounterLambda(Counter<T> counter)
	{
		this.counter = counter;
	}

	/**
	 * Create a lambda that repeatedly calls the counter's increment method. The
	 * lambda should take an integer that indicates how many to add. If the
	 * integer is negative, then you should decrement.
	 *
	 * @return The lambda created.
	 */
	public UnaryOperator<Integer> adder()
	{
		UnaryOperator<Integer> adder = num -> {
			if (num > 0)
			{
				for (int i = 0; i < num; i++)
				{
					counter.increment();
				}
			} else
			{
				for (int i = 0; i > num; i--)
				{
					counter.decrement();
				}
			}

			return counter.count();
		};

		return adder;
	}

	/**
	 * Returns a Function for comparing this counter with another. Note that
	 * compare returns this counter's count minus the other counter's.
	 *
	 * @return The lambda created.
	 */
	public Function<Counter<T>, Integer> compare()
	{
		Function<Counter<T>, Integer> comparer = compare -> counter.count()
				- compare.count();

		return comparer;
	}

	/**
	 * Returns a Function for determining if the counters have equal counts.
	 * Counters are equal if their elements and their counts match.
	 *
	 * @return The lambda created.
	 */
	public Function<Counter<T>, Boolean> equals()
	{
		Function<Counter<T>, Boolean> isEqual = compare -> counter.element()
				.equals(compare.element())
				&& counter.count() == compare.count();

		return isEqual;
	}
}
