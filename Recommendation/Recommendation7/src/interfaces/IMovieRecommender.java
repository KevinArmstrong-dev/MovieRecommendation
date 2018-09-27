/**
 * 
 */
package interfaces;
import movies.Movie;

/**
 * @author Alexander Arella Girardot
 * Interface for recommender methods
 */
public interface IMovieRecommender {
	public Movie[] recommender(int userId, int n, String genres);
	public Movie[] recommender(int userId, int n);
	
}
