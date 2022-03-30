/**
 * Immutable 1-tuple type.
 *
 * @author Michael S. Kirkpatrick
 * @version V1, 1/2018
 */
public class Single<T>
{

	private final T obj;

	public Single(T obj)
	{
		this.obj = obj;
	}

	public String toString()
	{
		return obj.toString();
	}

}
