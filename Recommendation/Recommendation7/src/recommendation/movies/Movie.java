package recommendation.movies;
import recommendation.interfaces.Item;

/**
 * @author Michael Azem
 *
 */

public class Movie implements Item {

	
	private int Year;
	private String ID;
	private String MovieName;
	private String[] Genres;
	
	/**
	 * The Movie constructor with 1 parameter takes as input a full string of the movie containing all it's 
	 * information. where it then separates the String into it's multiple field into a Temporary Movie Array.
	 * It then does verification for each field and sets them accordingly to there respective fields (Year, ID,
	 * MovieName,Genres) and then initializes the 4 parameter Movie constructor with them to Intialize a new Movie
	 * Object.
	 * @param  is a String containing fields of information about the movie where the format is a CSV file
	 * where commas in the title are translated to %2C in this format. where the order of Fields should be as 
	 * follows: Movie ID, Movie Name, Movie Genres. Each field should be separated by a Comma and if there is
	 * multiple genres, to separate each genre by a Pipe ('|') and if you wish to put the release date of the
	 * movie you have to put it at the end of the Movie Name in Brackets. an example of such string is as follows:
	 * 11,American President%2C The (1995),Comedy|Drama|Romance
	 */
	public Movie(String fullmovieString) {
		
		String TempYear = "";
		String TempMovieName = "";
		int numofcommas = 0;
		
		for(int i= 0; i < fullmovieString.length();i++) {		
			if(fullmovieString.charAt(i) == ',') {
				numofcommas++;
			}
		}
		
		if(numofcommas != 2) {
			throw new IllegalArgumentException("Error:The number of commas in the movie string is not equal to 2.");
		}
		
		String[] movieArray = fullmovieString.split(",", 3);
		
		if(movieArray[1].contains("%2C")) {
			movieArray[1]=movieArray[1].replaceAll("%2C", ",");
		}
		
		TempMovieName = movieArray[1];
		
		if(movieArray[1].charAt(movieArray[1].length()-1) == ')' && movieArray[1].charAt(movieArray[1].length()-6) == '(') {
				
				
				
				TempYear = movieArray[1].substring(movieArray[1].length()-5, movieArray[1].length()-1);
				this.Year = Integer.parseInt(TempYear);
				TempMovieName = movieArray[1].substring(0, movieArray[1].length()-7);
				
			}
			else 
				this.Year = -1;
	
		
		this.Genres = movieArray[2].split("\\|");
		this.ID = movieArray[0];
		this.MovieName = TempMovieName;
		
	}
	/**
	 * getName method returns a String of the movie
	 * @return a String of the movie name
	 */
	
	public String getName() {
		
		return this.MovieName;
	}
	
	/**
	 * getYear method returns an int of the release date of the movie (if put in the title) if not, then returns -1.
	 * @return a String of the Year of the movie came out (if the year was in the title) if not, it returns -1.
	 */
	
	public int getYear() {
		
		return this.Year;
	}
	
	/**
	 * GetID method that returns a string of the Movie ID
	 * @return a String of the Movie ID
	 */
	
	public String getId() {
		
		return this.ID;
	}
	
	/**
	 * GetGenres method returns a string array of genres.
	 * 
	 * @return a String array of genres
	 */
	
	public String[] getGenres() {
		
		String[] GenresArray = new String[Genres.length];
		
		for(int i = 0;i < Genres.length;i++) {
			GenresArray[i] = Genres[i];
		}
 		
		return GenresArray;
	}
	/**
	 * HasGenre method used to see if the genre string passed is in the genre string array.
	 * 
	 * @param a String of a movie genre.
	 * @return Boolean true if the parameter Genre is in the Genre string array, false if not there.
	 */
	
	public Boolean hasGenre(String Genre) {
		
		for(int i = 0; i < this.Genres.length; i++) {
			if(Genre.equals(this.Genres[i])) {
				return true;
			}
		}
		return false;
	}
	public String toString() {
		String output=this.ID+ '\t' + this.MovieName+'\t'+ this.Year;
		return output;
	}
}
