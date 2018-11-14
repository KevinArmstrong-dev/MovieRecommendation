package lib;

import java.io.IOException;
import java.util.ArrayList;

import recommendation.fileio.MovieLensFileReader;
import recommendation.movies.Movie;
import recommendation.movies.PersonalizedMovieRecommender;
import recommendation.movies.PopularMovieRecommender;
import recommendation.movies.Rating;

public class PersonalizedMovieRecommenderTest {
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
		Rating[] ratings=MovieLensFileReader.loadRatings("datafiles/sorted/ratings.csv");
		PersonalizedMovieRecommender Alpha =new PersonalizedMovieRecommender(movies,ratings);
		//ArrayList<Movie> test1 = Alpha.recommend(500, 100);
		ArrayList<Movie> test2= Alpha.recommend(17, 10, "Drama");
		for(Movie x:test2) {
			System.out.println(x.getName());
		}
		System.out.println(test2.size());
	}

}
