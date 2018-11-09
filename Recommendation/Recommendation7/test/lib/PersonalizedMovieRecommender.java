/**
 * 
 */
package lib;

import java.io.IOException;

import recommendation.fileio.MovieLensFileReader;
import recommendation.movies.Movie;
import recommendation.movies.PopularMovieRecommender;
import recommendation.movies.Rating;

/**
 * @author 1638876
 *
 */
public class PersonalizedMovieRecommender {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void Test() throws IOException{
		Movie[] movies=MovieLensFileReader.loadMovies("datafiles/sorted/movies.csv");
		Rating[] ratings=MovieLensFileReader.loadRatings("datafiles/sorted/ratings.csv");
		PopularMovieRecommender Alpha =new PopularMovieRecommender(movies,ratings);
		Movie[] test1=Alpha.recommend(16, 10, "Drama");
		System.out.println(test1.length);
		
		for(Movie x:test1) {
			System.out.println(x.getName());
		}
	}

}
