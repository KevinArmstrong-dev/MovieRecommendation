package fileio;
import java.io.IOException;
import java.nio.*;
import java.nio.file.*;
import java.util.List;

import recommentation.movies.Movie;
import recommentation.movies.Rating;
/**
 * @author Alexander Arella Girardot and Franco Gabriel Moro
 *
 */
public class MovieLensFileReader {
	
	public static Movie[] loadMovies(String path) {
		Path p = Paths.get(path);
		List<String> lines;
		try {
			lines = Files.readAllLines(p);
		}
		catch (IOException e) {
			throw new IllegalArgumentException("File not found, wrong input");
		}
		Movie[] movArr = new Movie[lines.size()];
		int index = 0;
		for (String s : lines) {
			movArr[index] = new Movie(s);
			index++;
		}
		return movArr;
	}
	public static Rating[] loadRatings(String path) {
		Path p = Paths.get(path);
		List<String> lines;
		try {
			lines = Files.readAllLines(p);
		}
		catch (IOException e) {
			throw new IllegalArgumentException("File not found, wrong input");
		}
		Rating[] ratArr = new Rating[lines.size()];
		int index = 0;
		for (String s : lines) {
			if(!(s.equals("userId,movieId,rating,timestamp")) ) {
				ratArr[index] = new Rating(s);
				index++;	
			}
			else {
				continue;
			}
		}
		return ratArr;
	}
	
	public static void main(String[] args) {
		Movie[] awef = loadMovies("datafiles/sorted/movies.csv");
		Rating[] fewa =loadRatings("datafiles/sorted/ratings.csv");
	}
}
