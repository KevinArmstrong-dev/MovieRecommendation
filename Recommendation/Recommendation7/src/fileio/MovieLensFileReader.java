package fileio;
import movies.*;

import java.io.IOException;
import java.nio.*;
import java.nio.file.*;
import java.util.List;
/**
 * @author Alexander Arella Girardot and Franco Gabriel Moro
 *
 */
public class MovieLensFileReader {
	
	public static Movie[] loadMovies(String path) {
		System.out.println("review");
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
			System.out.println(s);
			index++;
		}
		return movArr;
	}
	
	public static void main(String[] args) {
		Movie[] awef = loadMovies("datafiles\\sorted\\movies.csv");
	}
}
