/**
 * 
 */
package recommentation.movies;

/**
 * @author Kevin Armstrong Rwigamba
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
	 
	 /**
	  * A getter for Movie attribute
	  * 
	  * @return
	  */
	 public Movie getMovie() {
	  return this.movie;
	 }
	 
	 /**
	  * A getter for Rating attribute
	  * 
	  * @return
	  */
	 public double getRating() {
	  return rating;
	 }
	 /**
	  * Overloaded Tostring method to print needed details
				  j--;
			  }
	  * 
	  */
	 public String toString() {
		 String output = this.movie.getId()+" "+this.movie.getName()+ " "+ this.movie.getYear()+ " rating: "+ rating;
		 return output;
	 }

}
