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
		test();
		
	}
	
	private static void test() {
		Movie[] movies=MovieLensFileReader.loadMovies("datafiles/sorted/movies.csv");
		Rating[] ratings=MovieLensFileReader.loadRatings("datafiles/testfiles/testRatings.csv");
		PersonalizedRecommender test=new PersonalizedRecommender(movies,ratings);
		Movie[] testAlpha=test.recommend(4,1);
		
		for(int i=0;i<testAlpha.length;i++) {
			System.out.println(testAlpha[i].getName());
		}
		
		Movie[] testBravo=test.recommend(4, 3,"Drama");
		/*for(int i=0;i<testBravo.length;i++) {
			System.out.println(testBravo[i]);
		}
		*/
		if(testAlpha.length==1) {
			System.out.println("Pass! Expected 1 movie");
		}
		else {
			System.out.println("Fail! expected 1 movie");
		}
		
		if(testBravo[0].hasGenre("Drama")){
			System.out.println("Pass! Expected A Drama Movie");
		}
		else {
			System.out.println("Fail! Expected A Drama Movie");
		}
		
	}


}
