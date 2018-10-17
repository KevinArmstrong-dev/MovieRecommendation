package recommendation.movies;

/**
 * @author Alexander Arella Girardot
 * Object for the ratings from a user of a specified movie
 */
public class Rating {
	private int userId;
	private String movieId;
	private double rating;
	
	/**
	 * Constructor
	 * @param ratingText	String of the Ratings.csv file
	 */
	public Rating(String line) {
		boolean isNumbersOnly = true;
		String[] review = line.split(",");
		if (review.length < 4) {
			throw new RuntimeException("Line is missing content");
		}
		else if (review.length > 4) {
			throw new RuntimeException("Line has too much content");
		}
		this.movieId = review[1];
		for (int i = 0; i < movieId.length(); i++) {
			if (movieId.charAt(i) < '0' || movieId.charAt(i) > '9') {
				isNumbersOnly = false;
			}
		}
		if (!isNumbersOnly) {
			throw new IllegalArgumentException("Illegal character input (only numbers are needed)");
		}
		
		isNumbersOnly = true;
		this.userId = Integer.parseInt(review[0]);
		if (!isNumbersOnly) {
			throw new IllegalArgumentException("Illegal character input (only numbers are needed)");
		}
		
		isNumbersOnly = true;
		for (int i = 0; i < review[2].length(); i++) {
			if (!(review[2].charAt(i) >= '0' || review[2].charAt(i) <= '9'||review[2].charAt(i)=='.')) {
				isNumbersOnly = false;
			}
		}
		if (!isNumbersOnly) {
			throw new IllegalArgumentException("Illegal character input (only numbers are needed)");
		}
		this.rating = Double.parseDouble(review[2]);
		
	}
	
	/**
	 * 
	 * @return userId	Returns ID String of the user as a String
	 */
	public int getUserId() {
		
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
		return "User ID: " + this.userId + '\t' + "Movie ID: " + this.movieId + '\t' + "Rating: " + this.rating;
	}
}
