import java.util.Random;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * Interface for lambdas that test if an int satisfies a constraint.
 */
interface NumericTest
{
	boolean test(int n);
}

/**
 * Interface for lambdas that modify an int value.
 */
interface NumericModifier
{
	int modify(int n);
}

/**
 * Interface for lambdas that split a string based on an int input.
 */
interface StringModifier
{
	String[] split(int n);
}

/**
 * Class for implementing and experimenting with lambdas.
 *
 * @author Michael S. Kirkpatrick and Patrick Muradaz
 * @version V1, 8/2018
 */
public class Lambda<T>
{

	/**
	 * Uses a local NumericTest lambda to determine if numbers are positive.
	 *
	 * @param nums An array of ints to test.
	 * @return Boolean array, with bool[i] true if nums[i] > 0
	 */
	public static boolean[] testNumbers(int[] nums)
	{
		boolean retval[] = new boolean[nums.length];
		NumericTest test = num -> num > 0;

		for (int i = 0; i < nums.length; i++)
		{
			retval[i] = test.test(nums[i]);
		}

		return retval;
	}

	/**
	 * Return a lambda that takes one input and multiplies it by 2.
	 *
	 * @return A NumericModifier lambda.
	 */
	public static NumericModifier doubler()
	{
		NumericModifier doubler = num -> 2 * num;
		return doubler;
	}

	/**
	 * Returns a lambda that takes one input and returns n^n.
	 *
	 * @return A NumericModifier lambda.
	 */
	public static NumericModifier nToTheN()
	{
		NumericModifier nToN = n -> (int) Math.pow(n, n);

		return nToN;
	}

	/**
	 * Returns a lambda that takes an int input and splits the String at that
	 * location.
	 *
	 * @param s The String to split.
	 *
	 * @return A StringModifier lambda.
	 */
	public static StringModifier stringSplitter(String s)
	{
		StringModifier splitter = splitLoc -> {
			String[] retval = new String[2];
			retval[0] = s.substring(0, splitLoc);
			retval[1] = s.substring(splitLoc);

			return retval;
		};

		return splitter;
	}

	/**
	 * Applies a UnaryOperator on an operand. This method needs to be made
	 * generic to pass all tests.
	 *
	 * @param uop     A UnaryOperator lambda.
	 * @param operand The argument to the lambda.
	 *
	 * @return The result of the UnaryOperator.
	 */
	@SuppressWarnings("hiding")
	public <T> T useUnary(UnaryOperator<T> uop, T operand)
	{
		T retval = uop.apply(operand);

		return retval;
	}

	/**
	 * Generates a UnaryOperator lambda that will take an Integer and calculate
	 * the sum of its digits.
	 *
	 * @return The UnaryOperator that will be used by others to sum the digits.
	 */
	public UnaryOperator<Integer> sumOfDigits()
	{
		UnaryOperator<Integer> calculate = n -> {
			Integer retval = 0;

			while (n > 0)
			{
				retval = retval + n % 10;
				n = n / 10;
			}

			return retval;
		};

		return calculate;
	}

	/**
	 * Takes a Predicate and an arg. If the Predicate returns true, return the
	 * value "FIZZ". Otherwise, return "BANG".
	 *
	 * @param pred The Predicate to use.
	 * @param arg  The argument to the lambda.
	 *
	 * @return The String "FIZZ" or "BANG".
	 */
	public String fizzBang(Predicate<Integer> pred, int arg)
	{
		String retval = "BANG";

		if (pred.test(arg))
		{
			retval = "FIZZ";
		}

		return retval;
	}

	/**
	 * Generates a Predicate that can be used to test if an Integer is a prime
	 * number. The Predicate does a brute-force search from 2 up to the square
	 * root of the lambda's argument, stopping the first time that a divisor is
	 * found.
	 *
	 * @return The Predicate that can be used to test for primality.
	 */
	public Predicate<Integer> primeTest()
	{
		Predicate<Integer> tester = n -> {
			Boolean retval = true;
			Double sqrt = Math.sqrt(n);

			for (int i = 2; i < sqrt; i++)
			{
				if (n % i == 0)
				{
					retval = false;
					break;
				}
			}

			return retval;
		};

		// Algorithm:
		// for i in 2 to sqrt(n) do
		// if i divides n return false
		// return true

		return tester;

	}

	/**
	 * Generates a BiPredicate that takes an Integer and a String. The String is
	 * parsed as an int value and added to the Integer. If the sum is over 100,
	 * the BiPredicate returns true.
	 *
	 * @return A BiPredicate that tests for the sum > 100.
	 */
	public BiPredicate<Integer, String> sumOver100()
	{
		BiPredicate<Integer, String> tester = (Integer num, String str) -> {
			boolean retval = false;
			Integer strNum = Integer.parseInt(str);
			Integer sum = num + strNum;

			if (sum > 100)
			{
				retval = true;
			}

			return retval;
		};

		return tester;
	}

	/**
	 * Generates a BiPredicate with a String argument. When used, the
	 * BiPredicate will return true if both String arguments contain the one
	 * passed to this method as an argument.
	 *
	 * @param arg The common substring to find in two other Strings.
	 *
	 * @return The BiPredicate generated.
	 */
	public BiPredicate<String, String> commonSubstring(String arg)
	{
		BiPredicate<String, String> tester = (String o1, String o2) -> {
			boolean retval = false;

			if (o1.contains(arg) && o2.contains(arg))
			{
				retval = true;
			}

			return retval;
		};

		return tester;
	}

	/**
	 * Creates a Supplier that serves as a pseudo-random number generator. Each
	 * call to the lambda's get method will return a number chosen between min
	 * (inclusive) and min+range (exclusive).
	 *
	 * @param seed  The seed for the Random instance.
	 * @param range The range of possible values to choose.
	 * @param min   The minimum value that shifts the range.
	 *
	 * @return The Supplier lambda.
	 */
	public Supplier<Integer> pseudoRandom(int seed, int range, int min)
	{
		Random random = new Random(seed);

		Supplier<Integer> generator = () -> {
			Integer num = random.nextInt(range) + min;

			return num;
		};

		return generator;
	}
}
