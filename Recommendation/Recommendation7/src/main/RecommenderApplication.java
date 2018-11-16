package main;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import recommendation.fileio.*;
import recommendation.interfaces.*;
import recommendation.movies.IMovieRecommender;
import recommendation.movies.Movie;
import recommendation.movies.PersonalizedMovieRecommender;
import recommendation.movies.PopularMovieRecommender;
import recommendation.movies.Rating;


/**
 * 
 * @author Michael Azem
 *
 *Needs to be updated, did not have enough time to fully update it.
 *
 */

public class RecommenderApplication {

	static MovieLensFileReader MovieFileReaderObject = new MovieLensFileReader();
	static GoodReadsFileReader FileReaderObject = new GoodReadsFileReader();
	static Scanner ScannerObj = new Scanner(System.in);
	public static String BooksOrMovies = "";
	
	/**
	 * The Main Application to give the user a recommendation of movies based on the 
	 * movie file,rating file, the userID , Genre and the Recommendation given.
	 * @param <T>
	 * @param args
	 * @throws IOException
	 */
	public static <T> void main(String[] args) throws IOException {
		
		System.out.println("Please enter the number corresponding to which Category you want.");
		System.out.println("");
		System.out.println("1 - Book");
		System.out.println("2 - Movie");
		
		int CategoryChoice = CategoryReaderHelper();
		
		System.out.println("Please enter the file name of " +  BooksOrMovies);
		ScannerObj.reset();
		String MovieorBookPath = ScannerObj.nextLine();
		
		T[] MovieorBookArray = PathReaderHelper(MovieorBookPath, CategoryChoice);

		System.out.println("Please enter the file name of Ratings");
		String RatingPath = ScannerObj.nextLine();
		Rating[] ratingArray = RatingReaderHelper(RatingPath);

		System.out.println("Please enter the number corresponding to which Recommendation you want.");
		System.out.println("");
		System.out.println("1 - Popular");
		System.out.println("2 - General");

	
		int RecommendationChoice = RecommenderReaderHelper();
		
		IMovieRecommender MovieRecommenderObject = null;
		IRecommender RecommenderObject = null;
		
		System.out.println("Please enter your User ID.");
		UserGenreReaderHelper(MovieRecommenderObject,RecommenderObject,RecommendationChoice,ratingArray,MovieorBookArray,CategoryChoice);
		System.exit(0);
		
	}
	
	/**
	 * This Helper method loops until the user gives the input of 1 or 2 to figure what 
	 * category they want recommendations for.
	 * @return Category Choice (1) - Book , (2) - Movie
	 */
	
	public static int CategoryReaderHelper() {

		String CategoryChoice;

		CategoryChoice = ScannerObj.nextLine();

		while (!CategoryChoice.equals("1") && !CategoryChoice.equals("2")) {

			System.out.println("Please Enter a number between 1 and 2.");
			CategoryChoice = ScannerObj.nextLine();

		}
		
		if(Integer.parseInt(CategoryChoice) == 1) {
			BooksOrMovies = "Book";
		}else {
			BooksOrMovies = "Movie";
		}

		return Integer.parseInt(CategoryChoice);
	}

	/**
	 * This helper method validates the file path given and throws appropriate
	 * errors if invalid, at which point if it is valid, it loads the file with the
	 * movies/Books into a Generic array and then returns it.
	 * @param <T>
	 * 
	 * @param PathString the path of the movie/Books csv file.
	 * @param CategoryChoice  by the User (1) - Book, (2) - Movie
	 * @return Generic Array loaded with the loadmovies/loadbooks method from the movie/books csv
	 *         file.
	 * @throws IOException
	 */
	
	
	@SuppressWarnings("unchecked")
	public static <T> T[] PathReaderHelper(String PathString,int CategoryChoice) throws IOException {

		if(CategoryChoice == 1) {
			try {
				GoodReadsFileReader.loadBooks(PathString);
			}
			catch (Exception NoSuchFileException) {

				System.out.println("Book file not found, Please enter a valid Book file again.");
				PathString = ScannerObj.nextLine();
				PathReaderHelper(PathString,CategoryChoice);
			}
			return (T[])GoodReadsFileReader.loadBooks(PathString);
		}
		else {
		try {
			MovieLensFileReader.loadMovies(PathString);
		} catch (Exception NoSuchFileException) {

			System.out.println("Movie file not found, Please enter a valid movie file again.");
			PathString = ScannerObj.nextLine();
		    PathReaderHelper(PathString,CategoryChoice);
		}
		return (T[])MovieLensFileReader.loadMovies(PathString);
	}
	}


	/**
	 * This helper method validates the file path given and throws appropriate
	 * errors if invalid, at which point if it is valid, it loads the file with the
	 * ratings into a rating array and then returns it.
	 * 
	 * Side note, regardless of which category we get, we can use the same load ratings regardless
	 * since both have the exact same logic behind it.
	 * 
	 * @param RatingPathString the Path of the rating csv file.
	 * @return Rating Array loaded with the loadratings method from the ratings csv
	 *         file.
	 * @throws IOException
	 */
	public static Rating[] RatingReaderHelper(String RatingPathString) throws IOException {

		try {
			MovieLensFileReader.loadRatings(RatingPathString);
		} catch (Exception NoSuchFileException) {

			System.out.println("Rating file not found, Please enter a valid Rating file again.");
			RatingPathString = ScannerObj.nextLine();
			RatingReaderHelper(RatingPathString);
		}
		return MovieLensFileReader.loadRatings(RatingPathString);
	}

	/**
	 * This Helper Method checks and validates the users input and asks the users
	 * input until they either enter their choice of 1 or 2, and then returns their
	 * choice.
	 * 
	 * @return 1 (Popular) or 2 (General)
	 */
	public static int RecommenderReaderHelper() {

		String RecommendationChoice;

		RecommendationChoice = ScannerObj.nextLine();

		while (!RecommendationChoice.equals("1") && !RecommendationChoice.equals("2")) {

			System.out.println("Please Enter a number between 1 and 2.");
			RecommendationChoice = ScannerObj.nextLine();

		}

		return Integer.parseInt(RecommendationChoice);
	}
	
	/** this Helper method is used to help validate that the User ID entered actually exist in
	 * the file by going through the file and then passing all existing user IDs into a list and then
	 * array. 
	 * 
	 * @param ratingArray 
	 * @return Array with only existing User IDs used in the file
	 */
	public static int[] UserArrayHelper(Rating[] ratingArray) {
		
		ArrayList UserList = new ArrayList();
		
		for(int i = 0;i < ratingArray.length;i++) {
			 if(!UserList.contains(ratingArray[i].getUserId())) {
				 UserList.add(ratingArray[i].getUserId());
			 }
			 
		}
		UserList.removeAll(Collections.singleton(null));
		int[] UserArray = new int[UserList.size()];
		for(int i = 0; i<UserList.size();i++) {
			UserArray[i] = (int)UserList.get(i);
		}
		
		return UserArray;
		
	}
	
	/**
	 *  This Helper Method is used to validate the Genre that the user enters. this method passes
	 *  through all the movies and stores all possible genres used in the file.
	 * @param <T>
	 * @param movieorBookArray
	 * @return String Genre Array with All existing Genres used in the file.
	 */
	
public static <T> String[] GenreArrayHelper(T[] movieorBookArray) {
		
		ArrayList GenreList = new ArrayList();
		
		for(int i = 0;i < movieorBookArray.length;i++) {
			String[] TempGenreArray = ((Movie) movieorBookArray[i]).getGenres();
			
			for(int j=0; j< TempGenreArray.length;j++) {
				
			 if(!GenreList.contains(TempGenreArray[j])) {
				 GenreList.add(TempGenreArray[j]);
			    }
			 }
			 
		}
		GenreList.removeAll(Collections.singleton(null));
		String[] GenreArray = new String[GenreList.size()];
		for(int k = 0; k<GenreList.size();k++) {
			GenreArray[k] = (String)GenreList.get(k);
		}
		
		return GenreArray;
		
	}
	
    /**
     * this Method takes as input the UserID and a Genre from the user and validates it with 
     * helper methods, then depending on the value in userchoice it will then display you
     * all the movies with that genre. (will take userid into consideration if the choice General is taken)
     * @param <T>
     * @param RecommenderObject is a default IMovieRecommender Object used for polymorphism.
     * @param userchoice is the choice of the user (1) Popular (2) General
     * @param ratingArray 
     * @param movieorBookArray
     */
	
	public static <T> void UserGenreReaderHelper(IMovieRecommender MovieRecommenderObject,IRecommender RecommenderObject,int userchoice,Rating[] ratingArray,T[] movieorBookArray,int CategoryChoice) {

		String GenreChoice;
		String UserID;
		int[] allExistingUsers = UserArrayHelper(ratingArray);
		int userExist = 0;
		int genreExist = 0;
		int choiceall = 0;
		int amountofmovies;
		int numofmovies = 10;
		
		
		
		UserID = ScannerObj.nextLine();
		
		if(UserID.equals("0")) {
			System.exit(0);
		}
		try{
		   Integer.parseInt(UserID)	;
		}
		catch(NumberFormatException Exception){
			System.out.println("Please Enter a valid number");
			UserGenreReaderHelper(MovieRecommenderObject,RecommenderObject,userchoice,ratingArray,movieorBookArray,CategoryChoice);
		}
		for(int i = 0; i < allExistingUsers.length; i++) {
			if(Integer.parseInt(UserID) == allExistingUsers[i]) {
				userExist = 1;
			}
		}
			if(userExist == 0) {
				System.out.println("Please Enter a User ID that exists or that has done ratings");
				UserGenreReaderHelper(MovieRecommenderObject,RecommenderObject,userchoice,ratingArray,movieorBookArray,CategoryChoice);
			}
		
		ArrayList<Movie> RecommendedMovies = null;
		
		
		if(CategoryChoice == 2) {
			String[] allExistingGenres = GenreArrayHelper(movieorBookArray);
		System.out.println("Please enter the Genre you want Recommendations for.");
		GenreChoice = ScannerObj.nextLine();
		
		if(GenreChoice.equalsIgnoreCase("ALL")) {
			genreExist = 1;
			choiceall = 1;
		}
		
		for(int i = 0; i < allExistingGenres.length;i++) {
			if(GenreChoice.equals(allExistingGenres[i])) {
				genreExist = 1;
			}
		}
			if(genreExist == 0) {
			while(genreExist == 0) {
				System.out.println("Please Enter a Genre that exists");
				GenreChoice = ScannerObj.nextLine();
				for(int l = 0; l < allExistingGenres.length;l++) {
					if(GenreChoice.equals(allExistingGenres[l])) {
						genreExist = 1;
					}
			    }
				if(GenreChoice.equalsIgnoreCase("ALL")) {
					genreExist = 1;
					choiceall = 1;
				}
			   }
			}
			
			
				
		
		if(userchoice == 1) {
			 MovieRecommenderObject =   new PopularMovieRecommender((Movie[]) movieorBookArray,ratingArray);
			 if(choiceall == 1) {
				 RecommendedMovies= MovieRecommenderObject.recommend(Integer.parseInt(UserID), 10);
			 }
			 else {
				 RecommendedMovies= MovieRecommenderObject.recommend(Integer.parseInt(UserID), 1000,GenreChoice);
			 }
			 System.out.println("there is " + RecommendedMovies.size() + " movies recommended to you.");
				System.out.println("How many Movies would you like to print out?");
				numofmovies = ScannerObj.nextInt();
				ScannerObj.nextLine();
				
				while(numofmovies>RecommendedMovies.size() || numofmovies <= 0) {
					System.out.println("Please enter a valid amount of movies");
					 numofmovies = ScannerObj.nextInt();
					ScannerObj.nextLine();
				}
				

		}
		if(userchoice == 2) {
			MovieRecommenderObject =  new PersonalizedMovieRecommender((Movie[]) movieorBookArray,ratingArray);
			 if(choiceall == 1) {
				 RecommendedMovies= MovieRecommenderObject.recommend(Integer.parseInt(UserID), 10000);
			 }
			 else {
				 RecommendedMovies= MovieRecommenderObject.recommend(Integer.parseInt(UserID),10000,GenreChoice);
			 }
			 System.out.println("there is " + RecommendedMovies.size() + " movies recommended to you.");
				System.out.println("How many Movies would you like to print out?");
				numofmovies = ScannerObj.nextInt();
				ScannerObj.nextLine();
				
				while(numofmovies>RecommendedMovies.size() || numofmovies <= 0) {
					System.out.println("Please enter a valid amount of movies");
					 numofmovies = ScannerObj.nextInt();
					ScannerObj.nextLine();
				}
		}
		
			
		}
         if(CategoryChoice == 1) {

     		if(userchoice == 1) {
     			 RecommenderObject =   new PopularRecommender(ratingArray,(Item[]) movieorBookArray);
     			 RecommendedMovies= RecommenderObject.recommend(Integer.parseInt(UserID), 10000);
     			 
     			 System.out.println("there is " + RecommendedMovies.size() + " Books recommended to you.");
     				System.out.println("How many Books would you like to print out?");
     				numofmovies = ScannerObj.nextInt();
     				ScannerObj.nextLine();
     				
     				while(numofmovies>RecommendedMovies.size() || numofmovies <= 0) {
     					System.out.println("Please enter a valid amount of Books");
     					 numofmovies = ScannerObj.nextInt();
     					ScannerObj.nextLine();
     				}
     				

     		}
     		if(userchoice == 2) {
     			RecommenderObject =  new PersonalizedRecommender((Item[]) movieorBookArray,ratingArray);
     			 RecommendedMovies= RecommenderObject.recommend(Integer.parseInt(UserID), 10000);
     			
     			 System.out.println("there is " + RecommendedMovies.size() + " Books recommended to you.");
     				System.out.println("How many Books would you like to print out?");
     				numofmovies = ScannerObj.nextInt();
     				ScannerObj.nextLine();
     				
     				while(numofmovies>RecommendedMovies.size() || numofmovies <= 0) {
     					System.out.println("Please enter a valid amount of Books");
     					 numofmovies = ScannerObj.nextInt();
     					ScannerObj.nextLine();
     				}
     		}
         }
			
		System.out.println("Here are all your Choices:");
		System.out.println("");
		for(int i = 0; i < numofmovies;i++) {
			System.out.println(RecommendedMovies.get(i));
		}
	
	}
	
	

}
