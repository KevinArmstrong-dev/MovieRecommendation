package recommendation.fileio;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import recommendation.interfaces.Saveable;
import java.nio.*;
import java.nio.file.*;
import java.util.ArrayList;
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
		lines.remove(0);
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
		lines.remove(0);
		Rating[] ratArr = new Rating[lines.size()];
		int index = 0;
		for (String s : lines) {
				ratArr[index] = new Rating(s);
				index++;
		}
		return ratArr;
	}
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
