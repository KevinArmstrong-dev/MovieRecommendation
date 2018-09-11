package movies;
import interfaces.Item;

public class Movie implements Item {

	
	private int Year;
	private String ID;
	private String MovieName;
	private String[] Genres;
	private Movie MovieObject;
	
	public Movie(String fullmovieString) {
		
		String TempYear = "";
		String[] movieArray = fullmovieString.split(".", 3);
		
		if(movieArray[1].contains("%2C")) {
			movieArray[1].replaceAll("%2C", ",");
		}
		
		if(movieArray[1].charAt(movieArray[1].length()) == ')' ) {
			if(movieArray[1].charAt(movieArray[1].length()-5) == '(') {
				
				for(int i = 4; i > 0;i--) {
				
			     TempYear = TempYear + movieArray[1].charAt(movieArray[1].length()-i);
			   }
				this.Year = Integer.parseInt(TempYear);
			}
			else 
				this.Year = -1;
		}
		else
			this.Year = -1;
		
		this.Genres = movieArray[2].split("|", 9);
		this.ID = movieArray[0];
		this.MovieName = movieArray[1];
		Movie TempMovieObject = new Movie(this.getId(),this.getName(),this.getYear(),this.getGenres());
		
	}
	
	public Movie(String ID,String MovieName,int Year,String[] Genres) {
		
		
		this.MovieObject = new Movie(ID,MovieName,Year,Genres);
	}
	
	@Override
	public String getName() {
		
		return this.MovieName;
	}
	
	public int getYear() {
		
		return this.Year;
	}
	
	public String getId() {
		
		return this.ID;
	}
	
	public String[] getGenres() {
		
		return this.Genres;
	}
	
	public Boolean HasGenre(String Genre) {
		
		for(int i = 0; i <= this.Genres.length; i++) {
			if(Genre.equals(this.Genres[i])) {
				return true;
			}
		}
		return false;
	}
}
