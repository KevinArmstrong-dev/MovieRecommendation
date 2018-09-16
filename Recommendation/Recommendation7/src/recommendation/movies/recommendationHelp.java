/**
 * 
 */
package recommendation.movies;

/**
 * @author kevin
 * This is a helper class for the PopularRecommender class
 *
 */
public class recommendationHelp {
	private Movie movie;
	private double rating;
	
	/**
	 * @param movie
	 * @param rating
	 */
	public recommendationHelp(Movie movie,double rating) {
		this.movie=movie;
		this.rating=rating;
	}
	public Movie getMovie() {
		return this.movie;
	}
	public rating getRating() {
		return this.rating;
	}

}
