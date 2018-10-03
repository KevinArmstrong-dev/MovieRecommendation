package lib;

import fileio.MovieLensFileReader;
import recommentation.movies.Movie;
import recommentation.movies.PersonalizedRecommender;
import recommentation.movies.Rating;

public class PersonalizedRecommenderTest {
	public static void main(String[] args) {
		Movie[] movies=MovieLensFileReader.loadMovies("datafiles/sorted/movies.csv");
		Rating[] ratings=MovieLensFileReader.loadRatings("datafiles/sorted/ratings.csv");
		PersonalizedRecommender test=new PersonalizedRecommender(movies,ratings);
		
		
	}

}
