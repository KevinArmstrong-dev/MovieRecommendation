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
		// TODO Auto-generated method stub
		//this removed Line cause is related to some crashes
		Rating r1 = new Rating("1,31,2.5,1260759144");
		Rating r0=new Rating("70,88,3,853954814");
		Rating r2 = new Rating("9,1,4,9.39");
		Rating r7= new Rating("18,9,3,856007219");
		Rating r3 =new Rating("15,11,2,997938310");
		Rating r4 =new Rating("19,11,3,855192773");
		Rating r5 =new Rating("1,11,2.5,1260759144");
		Rating r6 =new Rating ("18,11,4,856007279");
		Rating r8 =new Rating("135,27,3,844996094");
		Rating [] rate= {r0,r1,r2,r3,r4,r5,r6,r7,r8};
		String fakeCvs="11,American President%2C The (1995),Comedy|Drama|Romance";
		Movie movieTest= new Movie(fakeCvs);
		Movie movieTest0=new Movie("27,Now and Then (1995),Children|Drama");
		Movie movieTest1= new Movie("9,Sudden Death%2C (1995),Action");
		Movie movieTest2=new Movie("1,Toy Story%2C (1995),Adventure|Animation|Children|Comedy|Fantasy|Common");
		Movie movieTest3=new Movie("2,Jumanji (1995),Adventure|Children|Fantasy|Common");
		Movie movieTest9=new Movie("70,From Dusk Till Dawn (1996),Action|Comedy|Horror|Thriller|Common");
		Movie movieTest5=new Movie("135,Down Periscope (1996),Comedy|Common");
		Movie movieTest6=new Movie("27,Now and Then%2C (1995),Children|Drama|Common");
		Movie movieTest7=new Movie("15,Cutthroat Island (1995),Action|Adventure|Romance|Common");
		Movie [] mv1= {movieTest5,movieTest2,movieTest,movieTest9,movieTest3,movieTest0,movieTest1,movieTest6,movieTest7,movieTest9};
	
		 PopularRecommender test= new PopularRecommender(rate,mv1);
		 Movie [] xx=test.recommendBeta("9",3);
		 //Movie [] xx=test.recommendTwo("2",2);
		 for(int i=0;i<xx.length;i++) {
			 System.out.println("REcommend KEV: " +xx[i].getName());
		 } 
		
		 System.out.println();
		Movie[] recommendTest= (test.recommendOne("100","Common"));
		 for(int i=0;i<recommendTest.length;i++) {
			 System.out.println("Recommend: " +recommendTest[i].getName());
		 }
		 //test.recommendOne("1","Fantasy"); 
		  
		 
		 
		
		
	}

}