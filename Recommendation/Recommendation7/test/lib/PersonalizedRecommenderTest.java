package lib;

import java.io.IOException;

import fileio.MovieLensFileReader;
import recommendation.movies.Movie;
import recommendation.movies.PersonalizedRecommender;
import recommendation.movies.Rating;

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
		//If anyone wants to remove the print statements from PersonalizedREcommender to put them here it would be great.
		try {
			Movie[] movies=MovieLensFileReader.loadMovies("datafiles/sorted/movies.csv");
			Rating[] ratings=MovieLensFileReader.loadRatings("datafiles/testfiles/testRatings.csv");
			PersonalizedRecommender test=new PersonalizedRecommender(movies,ratings);
		}
		catch(IOException e) {
			System.out.println("File Not Found");
		}
		
		
	}

}
