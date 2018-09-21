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
		Rating r1 = new Rating("1,31,2.5,1260759144");
		Rating r2 = new Rating("9,1,4,9.39");
		Rating r7= new Rating("18,9,3,856007219");
		Rating r3 =new Rating("15,1,2,997938310");
		Rating r4 =new Rating("19,11,3,855192773");
		Rating r5 =new Rating("1,31,2.5,1260759144");
		Rating r6 =new Rating ("18,100,4,856007279");
		Rating [] rate= {r1,r2,r3,r4,r5,r6};
		String fakeCvs="11,American President%2C The (1995),Comedy|Drama|Romance";
		Movie movieTest= new Movie(fakeCvs);
		Movie movieTest1= new Movie("9,Sudden Death%2C (1995),Action");
		Movie movieTest2=new Movie("1,Toy Story%2C (1995),Adventure|Animation|Children|Comedy|Fantasy");
		Movie movieTest3=new Movie("2,Jumanji%2C (1995),Adventure|Children|Fantasy");
		Movie movieTest4=new Movie("100,City Hall%2C (1996),Drama|Thriller");
		Movie movieTest5=new Movie("246,Hoop Dreams%2C (1994),Documentary");
		Movie [] mv1= {movieTest4,movieTest,movieTest2,movieTest3,movieTest5,movieTest1};
	
		 PopularRecommender test= new PopularRecommender(rate,mv1);
		 Movie [] xx=test.recommend("15",1);
		 for(int i=0;i<xx.length;i++) {
			 System.out.println("REcommend KEV: " +xx[i].getName());
		 }
		
		/* System.out.println();
		Movie[] recommendTest= (test.recommend("100","Fantasy"));
		 for(int i=0;i<recommendTest.length;i++) {
			 System.out.println("Recommend: " +recommendTest[i]);
		 }
		 //test.recommendOne("1","Fantasy");
		 */
		 
		
		
	}

}
