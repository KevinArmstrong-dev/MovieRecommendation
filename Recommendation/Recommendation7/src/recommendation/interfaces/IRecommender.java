/**
 * 
 */
package recommendation.interfaces;

/**
 * @author Alexander Arella Girardot
 *
 */
public interface IRecommender<T> {
	public T[] recommend(int userId, int n);
}
