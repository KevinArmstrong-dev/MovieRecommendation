/**
 * 
 */
package recommendation.movies;

import java.util.ArrayList;

import recommendation.interfaces.PersonalizedRecommender;

/**
 * @author Kevin Armstrong Rwigamba
 *
 */
public class PersonalizedMovieRecommender extends PersonalizedRecommender<Movie> implements IMovieRecommender {

	public PersonalizedMovieRecommender(Movie[] movies,Rating[] ratings) {
		super(movies,ratings);
	}
	
	@Override
	public ArrayList<Movie> recommend(int userId, int n) {
		return super.recommend(userId, n);
	}

	@Override
	public ArrayList<Movie> recommend(int userId, int n, String genre) {
		ArrayList<Movie> output=new ArrayList<Movie>();
	//	int count=n;
	//	while(output.size()<n) {
			output=recommend(userId,n);
			output=filter(output,n, genre);
	//		count+=count*10;
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
	

}
