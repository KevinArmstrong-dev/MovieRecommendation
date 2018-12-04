package lib;

import java.io.IOException;

import recommendation.book.Book;
import recommendation.fileio.GoodReadsFileReader;
import recommendation.fileio.MovieLensFileReader;
import recommendation.movies.Movie;
import recommendation.movies.Rating;

/**
 * 
 * @author Franco G. Moro
 * Test class that tests the saving for all types.
 *
 */
public class SaveableTest {
	/**
	 * 
	 * @param args
	 * The main runs a method for all the different tests for each object, the files will be located datafiles/testfiles/test[Object]Copy.csv
	 */
	public static void main(String[] args) {
		MovieTest();
		RatingTest();
		BookTest();
		BookRatingTest();
	}
	private static void BookTest() {
		Book[] bookArr=new Book[0];
		try {
			bookArr=GoodReadsFileReader.loadBooks("datafiles/books/books.csv");
		}
		catch(IOException e) {
			System.out.println(e);
		}
		MovieLensFileReader.saveToFile(bookArr, "datafiles/testfiles/testBookCopy1.csv", "userid,movieid,rating,timestamp");
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
