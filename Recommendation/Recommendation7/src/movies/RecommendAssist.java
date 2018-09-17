/**
 * 
 */
package movies;

/**
 * @author kevin
 *
 */
public class RecommendAssist {
	private Movie movie;
	private double rating;
	
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

}
