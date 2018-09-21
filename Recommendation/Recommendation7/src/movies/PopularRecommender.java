
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
	 }
	 
	 /**This method uses two arrays which are movie and ratings to Calculate
	  * the average rating of a given movie id
	  * 
	  * @param movieId
	  * @return averageRating
	  */

	private double getAverageRatingMovie(String movieId) {
	  double averageRating=0;
	   for(int x=0;x<ratings.length;x++) {
	    if(movieId.equals(ratings[x].getMovieId())) {
	     averageRating=+ratings[x].getRating();
	    }
	   }
	  return averageRating=averageRating/movies.length;
	  
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
	          if(x[guess].getRating() > x[i].getRating()) {
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
		  String[] ratedMovieIds = getRatedMovies(numberReview,userid,this.ratings);
		  
		  for(int i=0;i<this.collection.length;i++) {
			  if(!containsId(ratedMovieIds,this.collection[i].getMovie().getId())) {
				  output[pos]=this.collection[i].getMovie();
				  
			  }
		  }
		  return output;
	  }
	/**
	 * Kevin Armstrong Rwigamba
	 * 
	 * @param userid
	 * @param n
	 * @return unRatedMovies
	 */
	  public Movie[] recommend(String userid,int n) {
	   String[] id=new String[this.movies.length];
	   //This is To count How many movies have not been rated by the given user 
	   int count=0;
	   int xy=0; //temp var
	   
	   for(int i=0;i<this.ratings.length;i++) {
	    //To find the corresponding user
		   if(!(this.ratings[i].getUserId().equals(userid))) {
			    id[xy]=this.ratings[i].getMovieId();
			    xy++; //temp variable
				count++;
		   }
	   }
	   //This will make sure the provided number is not more than movies not rated by the user
	   Movie []unRatedMovies;
	   System.out.println(n+ " Value of N");
	   if(n<count) {
	  //Since the temporary array will be bigger than the provided number this should work
	   unRatedMovies=new Movie[n];
	   for(int x=0;x<id.length;x++) {
		   System.out.println(id[x]);
	   }
	   for(int i=0;i<n;i++){
		   int temp =0;
		   for(int x=0;x<id.length;x++) {
			   for(int z=0;z<collection.length;z++) {
				   	if(id[x].equals(collection[z].getMovie().getId())) {
				   		unRatedMovies[temp]=collection[x].getMovie();
				   		temp++;
				   	}
				   
		   }
			   }
		   //For the while loop
	   	}
	   }
	   else {
	    unRatedMovies=new Movie[count];
	    while(count!=0) {
	    	int temp1=0;
	     for(int x=0;x<id.length;x++) {
	    	 for(int r=0;r<collection.length;r++) {
	    	 	if(id[x].equals((collection[r].getMovie().getId()))){
	    		 unRatedMovies[temp1]=collection[r].getMovie();
	    		 temp1++;
	    	 	}
	    	 }
	     }
	     count--;
	    }
	   }
	   return unRatedMovies;
	 }
	 /**
	  * Kevin Armstrong Rwigamba
	  * 
	  * @param userid
	  * @param genre
	  * @return
	  */
	 public Movie[] recommendOne(String userid,String genre) {
		   String[] id=new String[this.movies.length];
		   //This is To count How many movies have not been rated by the given user 
		   int count=0;
		   
		   for(int i=0;i<this.ratings.length;i++) {
		    //To find the corresponding user
			   if(this.ratings[i].getUserId().equals(userid)) {
				   for(int x=0;x<this.movies.length;x++) {
					 
					   //this is a comparison to find movies which have not been rated by the user
					   if((!this.movies[x].getId().equals(this.ratings[i].getMovieId()))&&(genre.equals(this.movies[x].getGenres()))) {
						  
						   id[x]=this.movies[x].getId();
						   count++;
					   }
				   }
			   }
		   }
		   //This will make sure the provided number is not more than movies not rated by the user
		    Movie[] unRatedMovies=new Movie[count];
		    for(int i=0;i<count;i++) {
		     for(int x=0;x<collection.length;x++) {
		    	 if(id[i].equals((collection[i].getMovie().getId()))){
		    		 unRatedMovies[i]=collection[i].getMovie();
		    	 }
		     }
		    }
		   
		   return unRatedMovies;
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
		  int uId=Integer.parseInt(userId);
		  int first = Integer.parseInt(list[0].getUserId());
		  int last = Integer.parseInt(list[list.length-1].getUserId());
		  if(uId-first<last-uId) {
			  for(int i=0;i<list.length;i++) {
				  if(list[i].getMovieId().equals(userId)) {
				  	while(list[i].getMovieId().equals(userId)) {
					  count++;
				  	}
				  	return count;
				  }
			  }
		  }
		  else {
			  for(int i=list.length-1;i>=0;i--) {
				  if(list[i].getMovieId().equals(userId)) {
				  	while(list[i].getMovieId().equals(userId)) {
					  count++;
				  	}
				  	return count;
				  }
			  }
			  
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
		  int uId=Integer.parseInt(userId);
		  int first = Integer.parseInt(list[0].getUserId());
		  int last = Integer.parseInt(list[list.length-1].getUserId());
		  if(uId-first<last-uId) {
			  for(int i=0;i<list.length;i++) {
				  if(list[i].getMovieId().equals(userId)) {
				  	for(int x=i;x<list.length;x++) {
					  ratedMovies[x]=list[i].getMovieId();
				  	}
				  	return ratedMovies;
				  }
			  }
		  }
		  else {
			  for(int i=list.length-1;i>=0;i--) {
				  if(list[i].getMovieId().equals(userId)) {
				  	for(int x=i;x<list.length;x++) {
					  ratedMovies[x]=list[i].getMovieId();
				  	}
				  	return ratedMovies;
				  }
			  }
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
	  
	  private static int countGenre(Movie[] list,String genre) {
		  int count=0;
		  for(int i=0;i<list.length-1;i++) {
			  String[] genresPerMovie=list[i].getGenres();
			  for(int x=0;i<genresPerMovie.length;i++) {
				  if(genresPerMovie[x].equals(genre))count++;
			  }
		  }
		  return count;
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
			  while(this.collection[i].getRating()<pivot) {
				  i++;
			  }
			  while(this.collection[j].getRating()>pivot) {
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

