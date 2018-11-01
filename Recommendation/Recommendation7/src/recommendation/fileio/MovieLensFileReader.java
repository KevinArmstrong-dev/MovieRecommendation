package recommendation.fileio;
import java.io.IOException;
import java.nio.*;
import java.nio.file.*;
import java.util.List;

import recommendation.movies.Movie;
import recommendation.movies.Rating;
/**
 * @author Alexander Arella Girardot and Franco Gabriel Moro
 *
 */
public class MovieLensFileReader {
	
	public static Movie[] loadMovies(String path) throws IOException {
		Path p = Paths.get(path);
		List<String> lines;
		lines = Files.readAllLines(p);
		Movie[] movArr = new Movie[lines.size()];
		int index = 0;
		for (String s : lines) {
			movArr[index] = new Movie(s);
			index++;
		}
		return movArr;
	}
	public static Rating[] loadRatings(String path) throws IOException {
		Path p = Paths.get(path);
		List<String> lines;
		lines = Files.readAllLines(p);
		Rating[] ratArr = new Rating[lines.size()-1];
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
}
