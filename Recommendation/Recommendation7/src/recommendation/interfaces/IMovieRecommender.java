/**
 * 
 */
package recommendation.interfaces;
import recommendation.movies.Movie;

/**
 * @author Alexander Arella Girardot
 * Interface for recommend methods
 */
public interface IMovieRecommender {
	public Movie[] recommend(int userId, int n, String genres);
	public Movie[] recommend(int userId, int n);
	
}
