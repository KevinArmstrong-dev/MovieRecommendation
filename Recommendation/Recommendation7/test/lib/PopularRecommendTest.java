/**
 * 
 */
package lib;

import recommentation.movies.Movie;
import recommentation.movies.PopularRecommender;
import recommentation.movies.Rating;

/**
 * @author Kevin Armstrong Rwigamba
 *
 */
public class PopularRecommendTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		PopularRecommendTesting();
	}
	/**
	 * This Will Create a Testing Rating Array
	 * 
	 * @return
	 */
	private static Rating[] createRateArr() {
		Rating r1 = new Rating("10,1,2.0,1063502716");
		Rating r2 = new Rating("12,2,2.0,1063502716");
		Rating r3 =new Rating("13,3,2.5,1063502716");
		Rating r4 =new Rating("12,2,4.9,1063502716");
		Rating [] rate= {r1,r2,r3,r4};
		return rate;
	}
	/**
	 * Kevin Armstrong Rwigamba 
	 * 
	 * This will Create a Testing Movie Array
	 * 
	 * @return
	 */
	private static Movie[] createMovieArr() {
		Movie movieTest1= new Movie("11,American President%2C The (1995),Comedy|Drama|Romance|Common");
		Movie movieTest2=new Movie("1,Toy Story%2C (1995),Adventure|Animation|Children|Comedy|Common");
		Movie movieTest3=new Movie("2,Jumanji (1995),Adventure|Children|Fantasy|Common");
		Movie movietest4=new Movie("70,From Dusk Till Dawn (1996),Action|Comedy|Horror|Thriller|Common");
		Movie movieTest5=new Movie("135,Down Periscope (1996),Comedy|Common");
		Movie movieTest6=new Movie("27,Now and Then%2C (1995),Children|Drama|Common");
		Movie movieTest7=new Movie("15,Cutthroat Island (1995),Action|Adventure|Romance|Common");
		Movie m1 = new Movie("1,One (1995),Fun|Adventure|Animation|Children");
		Movie m2 = new Movie("2,Two (1995),Adventure|Animation|Children");
		Movie m3 = new Movie("3,Three (1995),Adventure|Animation|Children");

		Movie [] mvArr= {movieTest1,movieTest2,movieTest3,movietest4,movieTest5,movieTest6,movieTest7,m1,m2,m3};
		return mvArr;
	}
	
	/**
	 * Kevin Armstrong Rwigamba
	 * 
	 * This is the testing Method Body which will evaluate different Test cases
	 */
	private static void PopularRecommendTesting() {
		Rating[] rate=createRateArr();
		Movie[] mvArr=createMovieArr();
		PopularRecommender test= new PopularRecommender(rate,mvArr);
		System.out.println("TEST 1 : Should print every film except: The american president Because it was review by user 19");
		Movie [] testOne=test.recommend(19,4);
		
		//This should not work and print an error statement.
		try{
		 for(int i=0;i<testOne.length;i++) {
			 System.out.println(testOne[i].getName());			 }
		}
		 catch(NullPointerException e) {
			 System.out.println("Number of Reviewed movies>Available movies.");
		 }
		System.out.println("TEST 2: Should print print a few movies then say that there are no more non-reviewed movies");
		System.out.println("TEST 3 : Should print the entire movie Array because they all have the genre: Common & it wasn't rated");
		
		//This should not be able to find a match
		 try {
			 Movie[] testThree=test.recommend(200,10, "Common"); 
			 for(int i=0;i<testThree.length;i++) {
				 System.out.println(testThree[i]);
			 }
			 System.out.println("");
		 }
		 catch(ArrayIndexOutOfBoundsException e){
			 System.out.println("No match");
		 }
		 catch(NullPointerException e) {
			 System.out.println("No match");
		 }
		 
		 
		 //Testing for Corresponding genres
		 Movie[] testThree=test.recommend(19,3,"Horror");
		 if(testThree[0].hasGenre("Horror")) {
			 System.out.println("Pass! The expected Genre is Horror");
		 }
		 else {
			 System.out.println("Fail! The expected Genre is Horror");
		 }
		 
		 //if the movies asked for are greater than the number we have 
		 
		try {
				Movie [] testFive=test.recommend(15,8);
				for(int i=0;i<testFive.length;i++) {
				 System.out.println(testThree[i].getName());
				}
		}
		catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("No More movies which were not rated by the given user");
		}
		 

	}
}
