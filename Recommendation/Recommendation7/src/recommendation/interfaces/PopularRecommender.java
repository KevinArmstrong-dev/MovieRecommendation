/**
 * 
 */
package recommendation.interfaces;

import java.awt.List;
import java.util.*;
import recommendation.movies.Movie;
import recommendation.movies.Rating;
import recommendation.movies.RecommendAssist;

/**
 * @author Franco G. Moro
 * @author Kevin Armstrong Rwigamba
 * @author Alexander Arella Girardot
 */
public class PopularRecommender<T extends Item> implements IRecommender<T> {
	private ArrayList<T> movies;
	private Rating[] ratings;
	private ArrayList<RecommendAssist<T>> collection;
	/**
	 * Kevin Armstrong Rwigamba
	 * This constructor creates a Popular Recommender object, it also sorts the array from highest rated to lowest.
	 * @param ratings
	 * @param media
	 */
	public PopularRecommender(Rating[] ratings, T[] media) {
		this.movies= new ArrayList<T>();
		//to make movies[] immutable and avoid aliasing
		for(int i = 0; i < media.length; i++) {
			this.movies.add(media[i]);
		}
		this.ratings=new Rating[ratings.length];
		  
		//To make ratings[] immutable and avoid aliasing
		for(int i = 0; i < ratings.length; i++) {
			this.ratings[i]=ratings[i];
		}
		collection = new ArrayList<RecommendAssist<T>>();
		for(int i = 0; i < media.length; i++) {
			collection.add(new RecommendAssist<T>(media[i], getAverageRatingMovie(media[i].getId())));
		}

		quickSortCollection(0, this.collection.size()-1);

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
			  while(this.collection.get(i).getRating()>this.collection.get(pivot).getRating()){
				  i++;
			  }
			  while(this.collection.get(j).getRating()<this.collection.get(pivot).getRating()) {
				  j--;
			  }
			  if(i<=j) {
				  RecommendAssist<T> temp ;
				  temp=this.collection.get(i);
				  this.collection.set(i, this.collection.get(j));
				  this.collection.set(j, temp);
				  i++;
				  j--;
			  }
		  }
		  if(start<j)quickSortCollection(start,j);
		  if(last>i)quickSortCollection(i,last);
	  }
	  /**
	   * Franco G. Moro
	   * Recommendation method, calls on multiple helper methods.
	   * returns a movie[] that the user has not seen yet in order of best rated to lowest.
	   * @param userid
	   * @param n
	   * @return output
	   */
	public ArrayList<T> recommend(int userid,int n){
		ArrayList<T> output= new ArrayList<T>();// Problem: Need to use a Collection instead of arrays now.
		
		int pos=0;
		int numberReview=countRated(userid ,this.ratings);
		if(numberReview <= 1) {
			String[] ratedMovieIds = getRatedMovies(numberReview,userid,this.ratings);
			for(int i=0;i<this.collection.size();i++) {
				if(!(containsId(ratedMovieIds,this.collection.get(i).getMedia().getId()))) {
					if(pos==n) {return output ;}
					output.add(pos, this.collection.get(i).getMedia());
					pos++;  
				}
			}
		}
		else {
			for(int i=0;i<this.collection.size();i++) {
				output.add(pos, this.collection.get(i).getMedia());
				pos++;
				  
			}
		}
		if(pos<n) {
			ArrayList<T> temp = new ArrayList<T>(pos);
			for(int x=0;x<temp.size();x++) {
				temp.add(output.get(x));
			}
			return temp;
		}
		else {
			return output;  
		}
	}
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
	if (nRatings == 0) return 0.0;
	return averageRating=averageRating/nRatings;  
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
	   * This method checks if a specific movie id is contained within a String[]
	   * @param reviewed
	   * @param mediaId
	   * @return
	   */
	private static boolean containsId(String[] reviewed,String mediaId) {
		for(int i = 0; i < reviewed.length; i++) {
			if(reviewed[i].equals(mediaId))return true;
		}
		return false;
	}
}
