package movies;

/**
 * @author Alexander Arella Girardot
 * 
 */
public class Rating {
	private String userId;
	private String movieId;
	private double rating;
	
	/**
	 * 
	 * @param ratingText	String of the Ratings.csv file
	 */
	public Rating(String line) {
		String[] review = line.split(",");
		this.movieId = review[0];
		this.userId = review[1];
		this.rating = Double.parseDouble(review[2]);
		
	}
	
	/**
	 * Returns user's ID
	 * @return userId	ID String of the user
	 */
	public String getUserId() {
		
		return userId;
	}
	
	/**
	 * Returns movie's ID
	 * @return movieId	ID String of the movie
	 */
	public String getMovieId() {
		
		return movieId;
	}
	
	/**
	 * Returns movie's rating
	 * @return Rating double of the movie
	 */
	public double getRating() {
		
		return rating;
	}
	
	/**
	 * Prints all attributes of a Rating object
	 */
	@Override
	public String toString() {
		return "User ID: " + this.userId + '\n' + "Movie ID: " + this.movieId + '\n' + "Rating: " + this.rating;
	}
}
