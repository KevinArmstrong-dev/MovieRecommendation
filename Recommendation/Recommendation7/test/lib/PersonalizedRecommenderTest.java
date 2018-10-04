package lib;

import fileio.MovieLensFileReader;
import recommentation.movies.Movie;
import recommentation.movies.PersonalizedRecommender;
import recommentation.movies.Rating;

/**
 * 
 * @author Franco G. Moro
 * This test class test a  multitude of methods in the PersonalizedRecommender class.
 * In order to test the loadRatings, loadMovies and all the methods necessary to create the USM
 * I have created a file that ressembles the ratings file and calculated the result that the operations should have.
 *
 */
public class PersonalizedRecommenderTest {
	public static void main(String[] args) {
		Movie[] movies=MovieLensFileReader.loadMovies("datafiles/sorted/movies.csv");
		Rating[] ratings=MovieLensFileReader.loadRatings("datafiles/testfiles/testRatings.csv");
		PersonalizedRecommender test=new PersonalizedRecommender(movies,ratings);
		
		
	}

}
