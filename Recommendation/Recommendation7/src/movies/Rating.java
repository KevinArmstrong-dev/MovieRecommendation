package movies;

/**
 * @author Alexander Arella Girardot
 * Object for the ratings from a user of a specified movie
 */
public class Rating {
	private String userId;
	private String movieId;
	private double rating;
	
	/**
	 * Constructor
	 * @param ratingText	String of the Ratings.csv file
	 */
	public Rating(String line) {
		String[] review = line.split(",");
		this.movieId = review[0];
		this.userId = review[1];
		this.rating = Double.parseDouble(review[2]);
		
	}
	
	/**
	 * 
	 * @return userId	Returns ID String of the user as a String
	 */
	public String getUserId() {
		
		return userId;
	}
	
	/**
	 * 
	 * @return movieId	Returns the ID of the movie as a String
	 */
	public String getMovieId() {
		
		return movieId;
	}
	
	/**
	 * 
	 * @return Returns the rating of the movie as a Double
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
