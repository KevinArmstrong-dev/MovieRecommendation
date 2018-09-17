package movies;

/**
 * @author Kevin Armstrong Rwigamba
 *
 */
public class PopulaRecommender {
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
	public double getAverageRatingMovie(String movieId) {
		double averageRating=0;
		for(int i;i<movies.length;i++) {
			for(int x=0;x<ratings.length;x++) {
				if(movie[i].getId()==ratings[x].getId()) {
					averageRating=+ratings[x].getRating();
				}
			}
		}
		return avaerageRating;
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
	 public static Movies[] recommend(String userid,int n) {
		 Movies[] temp =new Movies[n];
		 //This is To count How many movies have not been rated by the given user 
		 int count=0;
		 for(int i=0;i<ratings.length;i++) {
			 //To find the corresponding user
			 if(ratings.getUserId().equals(userid)) {
				 for(int x=0;x<movies.length;movies++) {
					 int count1=0;
					 //this is a comparison to find movies which have not been rated by the user
					 if(!movies[x].getId().equals(ratings[i].getMovieId())) {
						 temp[count]=movies[x];
						 count++;
						 count1++;
					 }
				 }
			 }
		 }
		 //This will make sure the provided number is not more than movies not rated by the user
		 if(n<count) {
		//Since the temp array will be bigger than the provided number this should work
		 Movie []unRatedMovies=new Movie[n];
		 	for(int i=0;i<n;i++) {
		 		unRatedMovies[i]=temp[i];
		 	}
		 }
		 else {
			 Movie[] unRatedMovies=new Movie[count];
			 for(int i=0;i<count;i++) {
				 unRatedMovies[count]=temp[count];
			 }
		 }
		 return unRatedMovies;
	 }



}
