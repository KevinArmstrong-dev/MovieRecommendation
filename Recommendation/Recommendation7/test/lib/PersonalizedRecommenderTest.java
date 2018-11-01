package lib;

import java.io.IOException;

import recommendation.fileio.MovieLensFileReader;
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

		try {
			test();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("File Not Found");
		}
		
	}
	
	private static void test() throws IOException{
		Movie[] movies=MovieLensFileReader.loadMovies("datafiles/sorted/movies.csv");
		Rating[] ratings=MovieLensFileReader.loadRatings("datafiles/testfiles/testRatings.csv");
		PersonalizedRecommender test=new PersonalizedRecommender(movies,ratings);
		Movie[] testAlpha=test.recommend(4,1);
		Movie[] testBravo=test.recommend(4, 3,"Drama");
		Movie[] testThree=test.recommend(0, 2);
		Movie[] testDelta=test.recommend(4,2,"testing");
		//This is to check if the number of movies given is equal to the number requested by user
		if(testAlpha.length==1) {
			System.out.println("Pass! Expected 1 movie");
		}
		else {
			System.out.println("Fail! expected 1 movie");
		}
		
		//Test for corresponding Genres
		if(testBravo[0].hasGenre("Drama")){
			System.out.println("Pass! Expected A Drama Movie");
		}
		else {
			System.out.println("Fail! Expected A Drama Movie");
		}
		
		//To test when the user doesn't exist
		if(testThree[0]==null) {
			System.out.println("Pass! Expected an empty array");
		}
		else {
			System.out.println("Fail! Expected an empty array");
		}
		
		//TO check when the given Genre doesn't exist
		try {
			if(testDelta[0].hasGenre("testing")) {
			System.out.println("Fail! the given genre doesn't exist");
			}
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Pass! The genre given doesn't exist");
		}
	}


}
