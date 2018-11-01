/**
 * 
 */
package recommendation.movies;
import recommendation.interfaces.*;
/**
 * @author Alexander Arella Girardot
 *
 */
public interface IMovieRecommender extends IRecommender<Movie>{
	public Movie[] recommend(int userId, int n, String genres);
}
