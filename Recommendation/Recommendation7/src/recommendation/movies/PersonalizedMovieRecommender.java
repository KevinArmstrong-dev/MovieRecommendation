/**
 * 
 */
package recommendation.movies;

/**
 * @author Kevin Armstrong Rwigamba
 *
 */
public class PersonalizedMovieRecommender extends PersonalizedRecommender<Movie> implements IMovieRecommender {

	public PersonalizedMovieRecommender(Movie[] movies,Rating[] ratings) {
		super(ratings,movies);
	}
	
	@Override
	public ArrayList<Movie> recommend(int userId, int n) {
		return super.recommend(userId, n);
	}

	@Override
	public Movie[] recommend(int userId, int n, String genres) {
		return GenreAssist(userId, n, genres);
	}
	
	/**
	 * This method will generate a movie array and it will check if there is a matching number
	 *  movies with the given genre using the recommend method from personalized recommend method.
	 * 
	 * @param userId
	 * @param n
	 * @param genres
	 * @return
	 */
	private Movie[] GenreAssist(int userId,int n,String genres) {
		Movie[] recommendation =new Movie[n];
		ArrayList<Movie> recommendations=super.recommend(userId, n);
		int count=0;
		for(Movie temp:recommendations) {
			if(temp.hasGenre(genres)) {
				count++;
			}
		}
		if(count==n) {
			for(int i=0;i<recommendations.size();i++) {
				if(recommendations.get(i).hasGenre(genres)) {
					recommendation[i]=recommendations.get(i);
				}
			}
			return recommendation;
		}
		else {
			return GenreAssist(userId, n*10, genres);
		}
	}
	

}
