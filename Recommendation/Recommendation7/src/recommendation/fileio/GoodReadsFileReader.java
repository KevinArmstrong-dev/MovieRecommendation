package recommendation.fileio;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import recommendation.book.Book;
import recommendation.movies.Movie;
import recommendation.movies.Rating;

/**
 * 
 * @author Michael Azem
 *
 */

public class GoodReadsFileReader {

	public static Book[] loadBooks(String BookPath) throws IOException {
		
		Path p = Paths.get(BookPath);
		List<String> lines;
		lines = Files.readAllLines(p);
		lines.remove(0);
		Book[] BookArray = new Book[lines.size()];
		int index = 0;
		for (String s : lines) {
			BookArray[index] = new Book(s);
			index++;
		}
		return BookArray;
	}
	
	public static Rating[] loadRatings(String RatingPath) throws IOException {
		Path p = Paths.get(RatingPath);
		List<String> lines;
		lines = Files.readAllLines(p);
		lines.remove(0);
		Rating[] RatingArray = new Rating[lines.size()];
		int index = 0;
		for (String s : lines) {
				RatingArray[index] = new Rating(s);
				index++;
		}
		return RatingArray;
	}
}
