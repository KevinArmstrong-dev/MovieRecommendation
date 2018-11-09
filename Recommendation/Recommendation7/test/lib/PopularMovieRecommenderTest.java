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
 * @author kevin Armstrong Rwigamba 
 *
 */
public class PopularMovieRecommenderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Test();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Incorrect file Path");
		}

	}
	/**
	 *This method is not over since the inherited class is not finished
	 * 
	 * @throws IOException
	 */
	public static void Test() throws IOException{
		Movie[] movies=MovieLensFileReader.loadMovies("datafiles/sorted/movies.csv");
		Rating[] ratings=MovieLensFileReader.loadRatings("datafiles/testfiles/testRatings.csv");
		PopularMovieRecommender Alpha =new PopularMovieRecommender(movies,ratings);
		Movie[] test1=Alpha.recommend(16, 10, "Drama");
		System.out.println(test1.length);
		
		for(Movie x:test1) {
			System.out.println(x.getName());
		}
	}

}
