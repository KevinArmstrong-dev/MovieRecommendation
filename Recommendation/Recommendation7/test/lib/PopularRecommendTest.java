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
		// TODO Auto-generated method stub
		Rating r1 = new Rating("246,810042,535,24598");
		Rating r2 = new Rating("9,1,4,9.39");
		Rating r3 =new Rating("15,1,2,997938310");
		Rating r4 =new Rating("19,11,3,855192773");
		Rating [] rate= {r1,r2,r3,r4};
		String fakeCvs="11,American President%2C The (1995),Comedy|Drama|Romance";
		Movie movieTest= new Movie(fakeCvs);
		String mv="1,Toy Story%2C (1995),Adventure|Animation|Children|Comedy|Fantasy";
		Movie movieTest2=new Movie(mv);
		String mv2="2,Jumanji%2C (1995),Adventure|Children|Fantasy";
		Movie movieTest3=new Movie(mv2);
		//String mv3="100	City Hall%2C (1996),Drama|Thriller";
		//Movie movieTest4=new Movie(mv3);
		//Movie [] mv1= {movieTest,movieTest2,movieTest3};
	
		/* test= new PopularRecommender(rate,mv1);
		 System.out.println(test);
		 System.out.println(test.recommend("11",2).length);
		 Movie [] xx=test.recommend("11",2);
		 for(int i=0;i<xx.length;i++) {
			 System.out.println(xx[i].getName());
		 }
		 test.recommend("100","Fantasy");
		 
		 */
		
		
	}

}
