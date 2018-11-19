/**
 * 
 */
package recommendation.movies;
import java.util.ArrayList;

import recommendation.interfaces.*;
/**
 * @author Alexander Arella Girardot
 *
 */
public interface IMovieRecommender extends IRecommender<Movie>{
	public ArrayList<Movie> recommend(int userId, int n, String genres);
}
