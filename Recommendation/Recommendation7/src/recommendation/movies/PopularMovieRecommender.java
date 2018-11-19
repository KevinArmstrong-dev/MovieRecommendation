/**
 * 
 */
package recommendation.movies;
import java.util.ArrayList;

import recommendation.interfaces.PopularRecommender;

/**
 * @author Kevin Armstrong Rwigamba
 *
 */
public class PopularMovieRecommender extends PopularRecommender<Movie> implements IMovieRecommender{
	
	public PopularMovieRecommender(Movie[] movies,Rating[] ratings) {
		super(ratings,movies);
	}
	
	public ArrayList<Movie> recommend(int UserId,int n) {
		return super.recommend(UserId, n);
	}
	

	@Override
	public ArrayList<Movie> recommend(int userId, int n, String genre) {
		ArrayList<Movie> output=new ArrayList<Movie>();
	//	int count=n;
	//	while(output.size()<n) {
			output=recommend(userId,n);
			output=filter(output,n, genre);
		//	count+=count*10;
	//	}
		//output=new ArrayList<Movie>(output.subList(0,n));
		return output;
	}
	private ArrayList<Movie> filter(ArrayList<Movie> movies,int n ,String genre) {
		ArrayList<Movie> filtered=new ArrayList<Movie>();
		for(int i=0;i<movies.size();i++) {
			if(movies.get(i).hasGenre(genre)){
				filtered.add(movies.get(i));				
			}
		}
		return filtered;
	}
	
	/**
	 * This method will generate a movie array and it will check if there is a matching number
	 *  movies with the given genre using the popular recommender.
	 * 
	 * @param userId
	 * @param n
	 * @param genres
	 * @return
	 */

	/*private Movie[] GenreAssist(int userId,int n,String genres) {
		Movie[] recommendation =new Movie[n];
		ArrayList<Movie> recommendations=super.recommend(userId, n);
		int count=0;
		for(Movie temp:recommendations) {
			if(temp.hasGenre(genres)) {
				count++;
			}
		}
		if(count>=n) {
			for(int i=0;i<recommendations.size();i++) {
				if(recommendations.get(i).hasGenre(genres)) {
					recommendation[i]=recommendations.get(i);
				}
			}
			return recommendation;
		}
		else {
			return GenreAssist(userId, n+10, genres);
		}
	}*/
	
	public int getTotalPossibleAmountofMovies(int userId, int n, String genre) {
		int amountofmoviespossible = 0;
	ArrayList<Movie> tempArrayList	= recommend(userId,n,genre);
	for(int i = 0; i < tempArrayList.size();i++)	{
		if(tempArrayList.get(0).getId().equals(tempArrayList.get(i).getId())){
			amountofmoviespossible = i;
			i = tempArrayList.size();
		}
	}
	return amountofmoviespossible;
	}
}
