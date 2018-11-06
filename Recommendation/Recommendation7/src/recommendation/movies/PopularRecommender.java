
package recommendation.movies;
import recommendation.interfaces.IMovieRecommender;
/**
 * @author Franco G. Moro
 * @author Kevin Armstrong Rwigamba
 *
 */
public class PopularRecommender implements IMovieRecommender {
	 private Movie[] movies;
	 private Rating[] ratings; 
	 private RecommendAssist [] collection;
	 /**
	  * Kevin Armstrong Rwigamba
	  * This constructor creates a Popular Recommender object, it also sorts the arry from highest rated to lowest.
	  * @param ratings
	  * @param movie
	  */
	 public PopularRecommender(Rating[] ratings, Movie[] movie) {
	  this.movies=new Movie[movie.length];
	  //to make movies[] immutable and avoid aliasing
	  for(int i=0;i<movie.length;i++) {
	   this.movies[i]=movie[i];
	  }
	  this.ratings=new Rating[ratings.length];
	  
	  //To make ratings[] immutable and avoid aliasing
	  for(int i=0;i<ratings.length;i++) {
	   this.ratings[i]=ratings[i];
	  }
	  collection=new RecommendAssist [movie.length];
	  for(int i=0;i<movie.length;i++) {
	   collection[i]=new RecommendAssist(movie[i],getAverageRatingMovie(movie[i].getId()));
	  }
	  quickSortCollection(0,this.collection.length-1);
	 // for(int i=0;i<this.collection.length;i++) {
		//  System.out.println(this.collection[i]);
	  }
	// }
	 

	 
	 /**
	  * Franco G. Moro & Kevin Armstrong Rwigamba
	  * This method goes through the ratings[] and gets the average of every review made to a specific Movie id.
	  * @param movieId
	  * @return
	  */
	private double getAverageRatingMovie(String movieId) {
	  double averageRating=0;
	  int nRatings=0;
	  for(int i=0;i<ratings.length;i++) {
	   if(movieId.equals(this.ratings[i].getMovieId())) {
		   averageRating+=this.ratings[i].getRating();
		   nRatings++;
	   }
	  }
	  if(nRatings==0)return 0.0;
	  return averageRating=averageRating/nRatings;
	  
	}
	  /**
	   * Franco G. Moro
	   * Recommendation method, calls on multiple helper methods.
	   * returns a movie[] that the user has not seen yet in order of best rated to lowest.
	   * @param userid
	   * @param n
	   * @return output
	   */
	  public Movie[] recommend(int userid,int n){
		  Movie[] output= new Movie[n];
		  int pos=0;
		  int numberReview=countRated(userid ,this.ratings);
		  if(numberReview<=1) {
			  String[] ratedMovieIds = getRatedMovies(numberReview,userid,this.ratings);
			  for(int i=0;i<this.collection.length;i++) {
				  if(!(containsId(ratedMovieIds,this.collection[i].getMovie().getId()))) {
					  if(pos==n) return output ;
					  output[pos]=this.collection[i].getMovie();
						  pos++;  
				  }
			  }
		  }
		  else {
			  for(int i=0;i<this.collection.length;i++) {
				  output[pos]=this.collection[i].getMovie();
				  pos++;
				  
			  }
		  }
		  if(pos<n) {
			  Movie[] temp =new Movie[pos];
			  for(int x=0;x<temp.length;x++) {
				  temp[x]=output[x];
			  }
			  return temp;
		  }
		  else {
			  return output;  
		  }
	  }
	  
	  /**
	   * Franco G. Moro
	   * This method returns an array of movies that are the highest ranked and have the specified genre. They also musn't have been rated by the user.
	   * @param userId
	   * @param n
	   * @param genre
	   * @return
	   */
	  public Movie[] recommend(int userId, int n, String genre ) {
			 int nRated=countRated(userId,this.ratings);
			 Movie[] output = new Movie[n];
			 int pos =0;
			 if(nRated<=1) {
				 String[] ratedMovies= getRatedMovies(nRated,userId,this.ratings);
				 for(int i=0;i<this.collection.length;i++) {
					 if(this.collection[i].getMovie().hasGenre(genre)&&!containsId(ratedMovies,this.collection[i].getMovie().getId() )) {
						 if(pos==n)return output;
						 output[pos]=this.collection[i].getMovie();
						 pos++;
					 }
				 }
			 }
			 else {
				 for(int i=0;i<this.collection.length;i++) {
					 if(this.collection[i].getMovie().hasGenre(genre)) {
						 if(pos==n)return output;
						 output[pos]=this.collection[i].getMovie();
						 pos++;
					 }
				 }
			 }
			 if(pos<n) {
				  Movie[] temp =new Movie[pos];
				  for(int x=0;x<temp.length;x++) {
					  temp[x]=output[x];
				  }
				  return temp;
			  }
			  else {
				  return output;  
			  }
	  }

	  /**
	   * Franco G. Moro
	   * A method that returns the amount of reviews a specific user made.
	   * Searches from top to bottom or from bottom to top depending on which way is faster.
	   * @param userId
	   * @param list
	   * @return n
	   */
	  private static int countRated(int userId, Rating[] list) {
		  int count=0;
		  int k=list.length-1;
		  for(int i=0;i<k;i++) {
				 if(( userId==list[i].getUserId()) ||(userId==list[k].getUserId() ))
					 count++;
				 k--;
			  }
		  return count;
		  }
	 
	  /**
	   * Franco G. Moro 
	   * Makes an array with the id's of all the movies the user rated.
	   * @param numberRated
	   * @param userId
	   * @param list
	   * @return
	   */
	  private static String[] getRatedMovies(int numberRated,int userId, Rating[] list) {
		  String[] ratedMovies=new String[numberRated];
		  int pos=0;
		  int k = list.length-1;
		  for(int i=0;i<=k;i++) {
			  if(list[i].getUserId()==userId) {
				  ratedMovies[pos]=list[i].getMovieId();
				  pos++;
			  }
			  if(list[k].getUserId()==userId) {
				  ratedMovies[pos]=list[k].getMovieId();
				  pos++;
			  }
			  k--;
		  }
		  return ratedMovies;
	  }
	  /**
	   * Franco G. Moro
	   * This method checks if a specific movie id is contained within a String[]
	   * @param reviewed
	   * @param movieId
	   * @return
	   */
	  private static boolean containsId(String[] reviewed,String movieId) {
		  for(int i=0; i<reviewed.length;i++) {
			  if(reviewed[i].equals(movieId))return true;
		  }
		  return false;
	  }
	  /**
	   * Franco G. Moro
	   * QuickSort adapted to the RecommendAssist object.
	   * @param start
	   * @param last
	   */
	  private void quickSortCollection(int start, int last) {
		  int i=start;
		  int j=last;
		  int pivot= (i+j)/2;
		  while(i<=j) {
			  while(this.collection[i].getRating()>this.collection[pivot].getRating()){
				  i++;
			  }
			  while(this.collection[j].getRating()<this.collection[pivot].getRating()) {
				  j--;
			  }
			  if(i<=j) {
				  RecommendAssist temp ;
				  temp=this.collection[i];
				  this.collection[i]=this.collection[j];
				  this.collection[j]=temp;
				  i++;
				  j--;
			  }
		  }
		  if(start<j)quickSortCollection(start,j);
		  if(last>i)quickSortCollection(i,last);
	  }
}
