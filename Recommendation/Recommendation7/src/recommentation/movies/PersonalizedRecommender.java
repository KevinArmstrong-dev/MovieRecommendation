/**
 * 
 */
package recommentation.movies;

/**
 * @author Franco G. Moro
 *
 */
public class PersonalizedRecommender {
	private double[][] usm;
	private Movie[] movArr;
	private Rating[] ratArr;
	
	public PersonalizedRecommender(Movie[] movArr,Rating[] ratArr) {
		this.movArr=movArr;
		this.ratArr=ratArr;
		int numUsers=findNumUsers(this.ratArr);
		this.usm=new double[numUsers][numUsers];
	}
	private static int findNumUsers(Rating[] ratings) {
		int count=0;
		for(int i=0;i<ratings.length;i++) {
			if(ratings[i].getUserId()>count) {
				count=ratings[i].getUserId();
			}
		}
		return count;
	}

}
