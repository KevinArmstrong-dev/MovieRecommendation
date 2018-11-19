/**
 * 
 */
package recommendation.interfaces;
import java.util.*;
/**
 * @author Alexander Arella Girardot
 *
 */
public interface IRecommender<T> {
	public ArrayList<T> recommend(int userId, int n);
}
