package main;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.AccessDeniedException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

import recommendation.fileio.MovieLensFileReader;
import recommendation.interfaces.IMovieRecommender;
import recommendation.movies.Movie;
import recommendation.movies.PersonalizedRecommender;
import recommendation.movies.PopularRecommender;
import recommendation.movies.Rating;


/**
 * 
 * @author Michael Azem
 *
 */

public class RecommenderApplication {

	static MovieLensFileReader FileReaderObject = new MovieLensFileReader();
	static Scanner ScannerObj = new Scanner(System.in);
	
	/**
	 * The Main Application to give the user a recommendation of movies based on the 
	 * movie file,rating file, the userID , Genre and the Recommendation given.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		System.out.println("Please enter the file name of Movies");
		String MoviePath = ScannerObj.nextLine();
		Movie[] movieArray = MovieReaderHelper(MoviePath);

		System.out.println("Please enter the file name of Ratings");
		String RatingPath = ScannerObj.nextLine();
		Rating[] ratingArray = RatingReaderHelper(RatingPath);

		System.out.println("Please enter the number corresponding to which Recommendation you want.");
		System.out.println("");
		System.out.println("1 - Popular");
		System.out.println("2 - General");

	
		int RecommendationChoice = RecommenderReaderHelper();
		
		IMovieRecommender RecommenderObject = null;
		
		System.out.println("Please enter your User ID.");
		UserGenreReaderHelper(RecommenderObject,RecommendationChoice,ratingArray,movieArray);
		System.exit(0);
		
	}

	/**
	 * This helper method validates the file path given and throws appropriate
	 * errors if invalid, at which point if it is valid, it loads the file with the
	 * movies into a movie array and then returns it.
	 * 
	 * @param MoviePathString the path of the movie csv file.
	 * @return Movie Array loaded with the loadmovies method from the movie csv
	 *         file.
	 * @throws IOException
	 */
	
	
	public static Movie[] MovieReaderHelper(String MoviePathString) throws IOException {

		try {
			MovieLensFileReader.loadMovies(MoviePathString);
		} catch (Exception NoSuchFileException) {

			System.out.println("Movie file not found, Please enter a valid movie file again.");
			MoviePathString = ScannerObj.nextLine();
			MovieReaderHelper(MoviePathString);
		}
		return MovieLensFileReader.loadMovies(MoviePathString);
	}



	/**
	 * This helper method validates the file path given and throws appropriate
	 * errors if invalid, at which point if it is valid, it loads the file with the
	 * ratings into a rating array and then returns it.
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
	 * @param MovieArray
	 * @return String Genre Array with All existing Genres used in the file.
	 */
	
public static String[] GenreArrayHelper(Movie[] MovieArray) {
		
		ArrayList GenreList = new ArrayList();
		
		for(int i = 0;i < MovieArray.length;i++) {
			String[] TempGenreArray = MovieArray[i].getGenres();
			
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
     * @param RecommenderObject is a default IMovieRecommender Object used for polymorphism.
     * @param userchoice is the choice of the user (1) Popular (2) General
     * @param ratingArray 
     * @param movieArray
     */
	
	public static void UserGenreReaderHelper(IMovieRecommender RecommenderObject, int userchoice,Rating[] ratingArray,Movie[] movieArray) {

		String GenreChoice;
		String UserID;
		int[] allExistingUsers = UserArrayHelper(ratingArray);
		String[] allExistingGenres = GenreArrayHelper(movieArray);
		int userExist = 0;
		int genreExist = 0;
		int choiceall = 0;
		
		UserID = ScannerObj.nextLine();
		
		if(UserID.equals("0")) {
			System.exit(0);
		}
		try{
		   Integer.parseInt(UserID)	;
		}
		catch(NumberFormatException Exception){
			System.out.println("Please Enter a valid number");
			UserGenreReaderHelper(RecommenderObject,userchoice,ratingArray,movieArray);
		}
		for(int i = 0; i < allExistingUsers.length; i++) {
			if(Integer.parseInt(UserID) == allExistingUsers[i]) {
				userExist = 1;
			}
		}
			if(userExist == 0) {
				System.out.println("Please Enter a User ID that exists or that has done ratings");
				UserGenreReaderHelper(RecommenderObject,userchoice,ratingArray,movieArray);
			}
		
		
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
			 RecommenderObject = new PopularRecommender(ratingArray,movieArray);
			 
		}
		else {
			RecommenderObject = new PersonalizedRecommender(movieArray,ratingArray);

		}
		 Movie[] RecommendedMovies= RecommenderObject.recommend(Integer.parseInt(UserID), movieArray.length, GenreChoice);
			if(choiceall == 1) {
				RecommendedMovies= RecommenderObject.recommend(Integer.parseInt(UserID), movieArray.length);
			}
			
			
			System.out.println("there is " + RecommendedMovies.length + " movies recommended to you.");
			System.out.println("How many Movies would you like to print out?");
			int numofmovies = ScannerObj.nextInt();
			ScannerObj.nextLine();
			
			while(numofmovies>RecommendedMovies.length || numofmovies <= 0) {
				System.out.println("Please enter a valid amount of movies");
				 numofmovies = ScannerObj.nextInt();
				ScannerObj.nextLine();
			}
			
			
		System.out.println("Here are all your movies Choices:");
		System.out.println("");
		for(int i = 0; i < numofmovies;i++) {
			System.out.println(RecommendedMovies[i]);
		}
		System.out.println("");
		System.out.print("there is " + numofmovies + " movies recommended to you.");
	}
	
	

}
