/**
 * 
 */
package recommendation.interfaces;
import recommendation.movies.Movie;

/**
 * @author Alexander Arella Girardot
 * Interface for recommend methods
 */
public interface IMovieRecommender extends IRecommender<Movie> {
	public Movie[] recommend(int userId, int n, String genres);
}
