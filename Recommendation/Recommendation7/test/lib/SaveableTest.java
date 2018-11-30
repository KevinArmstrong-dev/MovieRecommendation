package lib;

import java.io.IOException;
import java.nio.file.Path;

import recommendation.fileio.MovieLensFileReader;
import recommendation.movies.Movie;
import recommendation.movies.Rating;

public class SaveableTest {
	public static void main(String[] args) {
		MovieTest();
		RatingTest();
		BookRatingTest();
	}
	private static void BookRatingTest() {
		Rating[] ratArr=new Rating[0];
		try {
			ratArr=MovieLensFileReader.loadRatings("datafiles/books/ratings.csv");
		}
		catch(IOException e) {
			System.out.println(e);
		}
		MovieLensFileReader.saveToFile(ratArr, "datafiles/testfiles/testBookRatingsCopy.csv", "userid,movieid,rating,timestamp");
	}
	private static void RatingTest() {
		Rating[] ratArr=new Rating[0];
		try {
			ratArr=MovieLensFileReader.loadRatings("datafiles/testfiles/testRatings.csv");
		}
		catch(IOException e) {
			System.out.println(e);
		}
		MovieLensFileReader.saveToFile(ratArr, "datafiles/testfiles/testRatingsCopy.csv", "userid,movieid,rating,timestamp");
	}
	private static void MovieTest() {
		Movie[] movArr=new Movie[0];
		try {
			movArr=MovieLensFileReader.loadMovies("datafiles/testfiles/testMovies.csv");
		}
		catch(IOException e) {
			System.out.println(e);
		}
		MovieLensFileReader.saveToFile(movArr, "datafiles/testfiles/testMoviesCopy.csv", "moveId,title,genres");
	}

}
