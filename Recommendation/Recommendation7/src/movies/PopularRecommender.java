package movies;
/**
 * @author 1638876
 *
 */
public class PopularRecommender {
	 private Movie[] movies;
	 private Rating[] ratings; 
	 private RecommendAssist [] collection;
	 
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
	  // System.out.println(collection[i]);
	  }
	  selectionSort(collection);
	  for(int i=0;i<this.collection.length;i++) {
		  System.out.println(this.collection[i]);
	  }
	  quickSortCollection(0,this.collection.length-1);
	 }
	 
	 /**This method uses two arrays which are movie and ratings to Calculate
	  * the average rating of a given movie id
	  * 
	  * @param movieId
	  * @return averageRating
	  */
	 
	 /**
	  * Franco G. Moro
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
	  return averageRating=averageRating/nRatings;
	  
	}
	 /**
	  * Kevin Armstrong Rwigamba
	  * Modified Selection Sort to sort the collection array but not tested yet
	  * or finished
	  * 
	  * @param x
	  */
	  public static void selectionSort(RecommendAssist[] x) {
	      for(int start = 0; start < x.length; start++) {
	        // start is the index of the first element of the 
	        // unsorted part of the array
	        
	        // find the min in the unsorted part
	        int guess = start; 
	        for(int i=start+1; i<x.length; i++) {
	          if(x[guess].getRating() < x[i].getRating()) {
	            guess =i;
	          }
	        }
	        
	        // swap the min element with the first element of the
	        // unsorted part of the array
	        RecommendAssist temp = x[guess];
	        x[guess] = x[start];
	        x[start] = temp;
	        
	      }
	  }
	  /**
	   * Franco G. Moro
	   * Recommendation method, calls on multiple helper methods.
	   * returns a movie[] that the user has not seen yet in order of best rated to lowest.
	   * @param userid
	   * @param n
	   * @return output
	   */
	  public Movie[] recommendTwo(String userid,int n){
		  Movie[] output= new Movie[n];
		  int pos=0;
		  int numberReview=countRated(userid ,this.ratings);
		  if(numberReview<=1) {
			  String[] ratedMovieIds = getRatedMovies(numberReview,userid,this.ratings);
			  for(int i=0;i<this.collection.length;i++) {
				  if(!(containsId(ratedMovieIds,this.collection[i].getMovie().getId()))) {
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
		  return output;
	  }
	  /**
	   * Kevin Armstrong Rwigamba
	   * 
	   * This method looks through an array and keeps aside all the unrated movies by the given user
	   *  and if the the user specify a smaller number than the movies we have i return a specified
	   *  number of movies and if the number is bigger than what i have i return all the movies i have
	   *      
	   * 
	   * @param userid
	   * @param n
	   * @return unRatedMovies
	   */
	  public Movie[] recommendBeta(String userid,int n) {
		  String[] idbeta=new String[this.movies.length];
		   //This is To count How many movies have not been rated by the given user 
		   int count=0;
		   int xz=0; //temp var
		   
		   for(int i=0;i<this.ratings.length;i++) {
		    //To find the corresponding user
			   if(!(this.ratings[i].getUserId().equals(userid))) {
				    idbeta[xz]=this.ratings[i].getMovieId();
				    xz++; //temp variable
					count++;
			   }
		   }
		  Movie [] unrated=new Movie[count];
		  //For testing purpose
		  System.out.println("Rating length is :"+this.ratings.length +" id: "+idbeta.length +" Collection Length: " +this.collection.length +" Movie Length: " +this.movies.length);
		  int c=0;
		  for(int x=0;x<idbeta.length;x++) {
			  for(int y=0;y<this.movies.length;y++) {
				  if(idbeta[x]!=null) { 
				  if(idbeta[x].equals(movies[y].getId())) {
					  unrated[c]=this.movies[y];
					  c++;
				  }
			   }
			  }
		  }
		  //This is to check how many null positions i have in my array
		  int countNull=0;
		  for(int i=0;i<unrated.length;i++) {
			  if(unrated[i]==null) {
				  countNull++;
			  } 
		  }
		  
		  //New movie array minus the empty positions
		  Movie[] unratedx= new Movie[unrated.length-countNull];
		  for(int i=0;i<unratedx.length;i++) {
			  unratedx[i]=unrated[i];
			  
		  }
		  if(n<unratedx.length) {
			  Movie [] unRatedMovies=new Movie[n];
			  for(int i=0;i<unRatedMovies.length;i++) {
				  unRatedMovies[i]=unratedx[i];
			  }
			  return unRatedMovies;
		  }
		  else{
		  return unratedx;
		  }
	  }
	 /**
	  * Kevin Armstrong Rwigamba
	  * 
	  * This method looks through unrated movies by the given user and cross check the array with given 
	  * genre and then return all the matching genres
	  * 
	  * @param userid
	  * @param genre
	  * @return x
	  */
	 public Movie[] recommendOne(String userid,String genre) {
		  String[] idbeta=new String[this.movies.length];
		   //This is To count How many movies have not been rated by the given user 
		   int count=0;
		   int xz=0; //temp var
		   
		   for(int i=0;i<this.ratings.length;i++) {
		    //To find the corresponding user
			   if(!(this.ratings[i].getUserId().equals(userid))) {
				    idbeta[xz]=this.ratings[i].getMovieId();
				    xz++; //temp variable
					count++;
			   }
		   }
		   
		   /*for(int i=0;i<idbeta.length;i++) {
			   System.out.println(idbeta[i]);
		   } */
		  Movie [] unrated=new Movie[count];
		  //For testing purpose
		  //System.out.println("Rating length is :"+this.ratings.length +" id: "+idbeta.length +" Collection Length: " +this.collection.length +" Movie Length: " +this.movies.length);
		  int c=0;
		  for(int x=0;x<idbeta.length;x++) {
			  for(int y=0;y<this.collection.length;y++) {
				  //System.out.println(this.collection[y].getMovie().getId() + "====id: "+idbeta[x]);
				  if(idbeta[x]!=null) { 
				  if(idbeta[x].equals(this.collection[y].getMovie().getId())) {
					  //System.out.println("+++:> "+ this.collection[y].getMovie().getId() + "====id: "+idbeta[x]);
					  unrated[c]=this.collection[y].getMovie();
					  c++;
				  }
			   }
			  }
		  }
		  //This is to check how many null positions i have in my array
		  int countNull=0;
		  for(int i=0;i<unrated.length;i++) {
			  if(unrated[i]==null) {
				  countNull++;
			  } 
		  }
		  
		  //New movie array minus the empty positions
		  Movie[] unratedx= new Movie[unrated.length-countNull];
		  for(int i=0;i<unratedx.length;i++) {
			  unratedx[i]=unrated[i];
			  
		  }
		  int genreCount=0;
		  for(int i=0;i<unratedx.length;i++){
			  if(unratedx[i].hasGenre(genre)==true) {
				  genreCount++;
			  }
		  }
		  Movie []x=new Movie[genreCount];
		  int m=0;
		  for(int i=0;i<unratedx.length;i++){
			  if(unratedx[i].hasGenre(genre)==true) {
				  x[m]=unratedx[i];
				  m++;
			  }
		  }
		  //System.out.println(genreCount);
		  //return unratedx;
		  return x;
		 }
		 

	 public Movie[] recommend(String userId, String genre ) {
		 int nRated=countRated(userId,this.ratings);
		 Movie[] output = new Movie[25];
		 int pos =0;
		 String[] ratedMovies= getRatedMovies(nRated,userId,this.ratings);
		 for(int i=0;i<this.collection.length;i++) {
			 if(this.collection[i].getMovie().hasGenre(genre)&&!containsId(ratedMovies,this.collection[i].getMovie().getId() )) {
				 output[pos]=this.collection[i].getMovie();
			 }
		 }
		 return output;
		 
	 }
	  /**
	   * Franco G. Moro
	   * A method that returns the amount of reviews a specific user made.
	   * Searches from top to bottom or from bottom to top depending on which way is faster.
	   * @param userId
	   * @param list
	   * @return n
	   */
	  private static int countRated(String userId, Rating[] list) {
		  int count=0;
		  int k=list.length-1;
		  for(int i=0;i<k;i++) {
				 if((userId.equals(list[i].getUserId()))||(userId.equals(list[k].getUserId())))
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
	  private static String[] getRatedMovies(int numberRated,String userId, Rating[] list) {
		  String[] ratedMovies=new String[numberRated];
		  int pos=0;
		  int k = list.length-1;
		  for(int i=0;i<=k;i++) {
			  if(list[i].getUserId().equals(userId)) {
				  ratedMovies[pos]=list[i].getMovieId();
				  pos++;
			  }
			  if(list[k].getUserId().equals(userId)) {
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
	  private void quickSortCollection(int first, int last) {
		  int i=first;
		  int j=last;
		  double pivot= this.collection[(i+j)/2].getRating();
		  while(i<=j) {
			  while(this.collection[i].getRating()>pivot) {
				  i++;
			  }
			  while(this.collection[j].getRating()<pivot) {
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
		  if(first<j)quickSortCollection(first,j);
		  if(last<i)quickSortCollection(last,i);
	  }
}
