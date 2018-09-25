/**
 * 
 */
package lib;

import movies.Movie;
import movies.PopularRecommender;
import movies.Rating;

/**
 * @author Kevin Armstrong Rwigamba
 *
 */
public class PopularRecommendTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestPopularRecommender();
	}
	
	/**
	 * This creates a Testing Rating array
	 * 
	 * @return rate
	 */
	private static Rating[] createRateArr() {
		Rating r1 = new Rating("246,810042,2.6,24598");
		Rating r2 = new Rating("9,1,4,4.5.39");
		Rating r3 =new Rating("15,1,3.5,997938310");
		Rating r4 =new Rating("19,11,3,855192773");
		Rating [] rate= {r1,r2,r3,r4};
		return rate;
	}
	/**
	 * This creates a Testing Movie array
	 * 
	 * @return mvArr
	 */
	private static Movie[] createMovieArr() {
		Movie movieTest= new Movie("11,American President%2C The (1995),Comedy|Drama|Romance|Common");
		Movie movieTest2=new Movie("1,Toy Story%2C (1995),Adventure|Animation|Children|Comedy|Common");
		Movie movieTest3=new Movie("2,Jumanji (1995),Adventure|Children|Fantasy|Common");
		Movie [] mvArr= {movieTest,movieTest2,movieTest3};
		return mvArr;
	}
	/**
	 * Testing method to evaluate the popular recommend class
	 * 
	 */

	private static void TestPopularRecommender(){
		Rating[] rate=createRateArr();
		Movie[] mvArr=createMovieArr();
		PopularRecommender test= new PopularRecommender(rate,mvArr);
		System.out.println("TEST 1 : Should print every film except: The american president Because it was review by user 19");
		try{
			Movie [] testOne=test.recommendTwo("19",1);
		 for(int i=0;i<testOne.length;i++) {
			 System.out.println(testOne[i].getName());			 }
		}
		 catch(NullPointerException e) {
			 System.out.println("Number of Reviewed movies>Available movies.");
		 }
		 catch(ArrayIndexOutOfBoundsException e) {
			 System.out.println("No match");
		 }
		//System.out.println("TEST 2: Should print print a few movies then say that there are no more non-reviewed movies");
		//System.out.println("TEST 3 : Should print the entire movie Array because they all have the genre: Common & it wasn't rated");
		 //This should not be able to find a match
		 try {
			 Movie[] testThree=test.recommendTwo("100",1); 
			 
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
		 
		 try {
			 Movie[] testFour=test.recommend("19","Revi");
			 for(int i=0;i<testFour.length;i++) {
				 if(testFour[i].hasGenre("Commedy")!=true){
					 System.out.println("Fail");
				 }
				 else {
					 System.out.println("Pass");
				 }
		  }
		 }
		 catch(ArrayIndexOutOfBoundsException e) {
			 System.out.println("No match");
		 }
		 catch(NullPointerException e) {
			 System.out.println("No match");
		 }
	}
}
