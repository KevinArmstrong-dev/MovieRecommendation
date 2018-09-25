/**
 * 
 */
package lib;

import movies.Movie;
import movies.PopularRecommender;
import movies.Rating;

/**
 * @author 1638876
 *
 */
public class PopularRecommendTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Rating[] rate=createRateArr();
		Movie[] mvArr=createMovieArr();
		PopularRecommender test= new PopularRecommender(rate,mvArr);
		System.out.println("TEST 1 : Should print every film except: The american president Because it was review by user 19");
		Movie [] testOne=test.recommend("19",4);
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
			 Movie[] testThree=test.recommend("200",7, "Common"); 
			 for(int i=0;i<testThree.length;i++) {
				 System.out.println(testThree[i].getName());
			 }
		 }
		 catch(ArrayIndexOutOfBoundsException e){
			 System.out.println("No match");
		 }
		 catch(NullPointerException e) {
			 System.out.println("No match");
		 }
	}
	private static Rating[] createRateArr() {
		Rating r1 = new Rating("246,810042,2.6,24598");
		Rating r2 = new Rating("9,1,4,4.5.39");
		Rating r3 =new Rating("15,1,3.5,997938310");
		Rating r4 =new Rating("19,11,3,855192773");
		Rating [] rate= {r1,r2,r3,r4};
		return rate;
	}
	private static Movie[] createMovieArr() {
		Movie movieTest1= new Movie("11,American President%2C The (1995),Comedy|Drama|Romance|Common");
		Movie movieTest2=new Movie("1,Toy Story%2C (1995),Adventure|Animation|Children|Comedy|Common");
		Movie movieTest3=new Movie("2,Jumanji (1995),Adventure|Children|Fantasy|Common");
		Movie movietest4=new Movie("70,From Dusk Till Dawn (1996),Action|Comedy|Horror|Thriller|Common");
		Movie movieTest5=new Movie("135,Down Periscope (1996),Comedy|Common");
		Movie movieTest6=new Movie("27,Now and Then%2C (1995),Children|Drama|Common");
		Movie movieTest7=new Movie("15,Cutthroat Island (1995),Action|Adventure|Romance|Common");
		Movie [] mvArr= {movieTest1,movieTest2,movieTest3,movietest4,movieTest5,movieTest6,movieTest7};
		return mvArr;
	}
}
