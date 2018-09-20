/**
 * 
 */
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
	  }
	  
	 }
	 
	 /**This method uses two arrays which are movie and ratings to Calculate
	  * the average rating of a given movie id
	  * 
	  * @param movieId
	  * @return averageRating
	  */

	private double getAverageRatingMovie(String movieId) {
	  double averageRating=0;
	  for(int i=0;i<movies.length;i++) {
	   for(int x=0;x<ratings.length;x++) {
	    if(movies[i].getId().equals(ratings[x].getUserId())) {
	     averageRating=+ratings[x].getRating();
	    }
	   }
	  }
	  System.out.println(averageRating=averageRating/movies.length);
	  return averageRating=averageRating/movies.length;
	  
	}
	 /**Modified Selection Sort to sort the collection array but not tested yet
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
	 public Movie[] recommend(String userid,int n) {
	   String[] id=new String[this.movies.length];
	   //This is To count How many movies have not been rated by the given user 
	   int count=0;
	   
	   for(int i=0;i<this.ratings.length;i++) {
	    //To find the corresponding user
		   if(this.ratings[i].getUserId().equals(userid)) {
			   for(int x=0;x<this.movies.length;x++) {
				 
				   //this is a comparison to find movies which have not been rated by the user
				   if(!this.movies[x].getId().equals(this.ratings[i].getMovieId())) {
					  
					   id[x]=this.movies[x].getId();
					   count++;
				   }
			   }
		   }
	   }
	   //This will make sure the provided number is not more than movies not rated by the user
	   Movie []unRatedMovies=new Movie[n];
	   if(n<count) {
	  //Since the temporary array will be bigger than the provided number this should work
	   unRatedMovies=new Movie[n];
	    for(int i=0;i<movies.length;i++) {
	    	if(id[i].equals((collection[i].getMovie().getId()))){
	    		unRatedMovies[i]=collection[i].getMovie();
	    	}
	    
	    }
	   }
	   else {
	    unRatedMovies=new Movie[count];
	    for(int i=0;i<count;i++) {
	     for(int x=0;x<collection.length;x++) {
	    	 if(id[i].equals((collection[i].getMovie().getId()))){
	    		 unRatedMovies[i]=collection[i].getMovie();
	    	 }
	     }
	    }
	   }
	   return unRatedMovies;
	 }

	  }
	  */
	  /**
	   * Franco Gabriel
	   * A method that returns the amount of reviews a specific user made.
	   * Searches from top to bottom or from bottom to top depending on which way is faster.
	   * @param userId
	   * @param list
	   * @return n
	   */
	  private int countReviews(String userId, Rating[] list) {
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
}
