package recommendation.fileio;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import recommendation.book.Book;
import recommendation.interfaces.Saveable;
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
	/**
	 * @author Franco G Moro
	 * @param objects
	 * @param filepath
	 * @param fileHeader
	 * Saves a copy of an array of Books/Ratings, one could also use the same method from MovieLensFileReader instead.
	 */
	public static <T extends Saveable> void saveToFile(T[] objects,String filepath,String fileHeader) {
		Path path= Paths.get(filepath);
		List<String> filecontent=new ArrayList<String>();
		filecontent.add(fileHeader);
		for(Object x:objects) {
			Saveable Field=(Saveable)x;
			String s=Field.toRawString();
			filecontent.add(s);
		}
		try {
			Files.write(path, filecontent);			
		}
		catch(IOException e) {
			System.out.println(e+"--Invalid Path");
		}
		
		
	}
}
