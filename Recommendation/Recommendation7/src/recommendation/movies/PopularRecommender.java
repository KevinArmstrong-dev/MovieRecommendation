/**
 * 
 */
package recommendation.movies;

/**
 * @author kevin
 *
 */
public class PopularRecommender {
	//Attributes
	
	private Movie[] movies;
	private Rating[] ratings;
		
	public PopularRecommender(Rating[] ratings, Movie[] movie) {
			this.ratings=ratings;
		
	}
	
	/**This method uses two arrays which are movie and ratings to Calculate
	 * the average rating of a given movie id
	 * 
	 * @param movieId
	 * @return averageRating
	 */
	public double getAverageRatingMovie(int movieId) {
		double averageRating=0;
		for(int i;i<movies.length;i++) {
			for(int x=0;x<ratings.length;x++) {
				if(movie[i].getMovieId()==ratings[x].getMovieId()) {
					averageRating=+ratings[x].getRating();
				}
			}
		}
		return avaerageRating;
	}


}
