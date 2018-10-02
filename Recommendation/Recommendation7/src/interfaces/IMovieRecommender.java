/**
 * 
 */
package interfaces;
import recommentation.movies.Movie;

/**
 * @author Alexander Arella Girardot
 * Interface for recommender methods
 */
public interface IMovieRecommender {
	public Movie[] recommend(int userId, int n, String genres);
	public Movie[] recommend(int userId, int n);
	
}
