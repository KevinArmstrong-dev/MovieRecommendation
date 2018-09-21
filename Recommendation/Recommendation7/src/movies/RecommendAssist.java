/**
 * 
 */
package movies;

/**
 * @author 1638876
 *
 */
public class RecommendAssist {
	
	private Movie movie;
	private double rating;
	 
	 /**Helper object to store movies to be recommended
	  * 
	  * @param movie
	  * @param rating
	  */
	 public RecommendAssist(Movie movie,double rating) {
	  
	  this.movie=movie;
	  this.rating=rating;
	 }
	 
	 public Movie getMovie() {
	  return this.movie;
	 }
	 public double getRating() {
	  return rating;
	 }
	 public String toString() {
		 String output = this.movie.getId()+" "+this.movie.getName()+ " "+ this.movie.getYear()+ " rating: "+ rating;
		 return output;
	 }

}
