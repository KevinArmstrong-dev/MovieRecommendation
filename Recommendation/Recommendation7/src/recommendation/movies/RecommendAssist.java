/**
 * 
 */
package recommendation.movies;

import recommendation.interfaces.Item;

/**
 * @author Kevin Armstrong Rwigamba
 *
 */
public class RecommendAssist<T extends Item> {
	
	private T media;
	private double rating;
	 
	 /**Helper object to store movies to be recommended
	  * 
	  * @param media
	  * @param rating
	  */
	 public RecommendAssist(T media,double rating) {
	  
	  this.media=media;
	  this.rating=rating;
	 }
	 
	 /**
	  * A getter for Movie attribute
	  * 
	  * @return
	  */
	 public T getMedia() {
	  return this.media;
	 }
	 
	 /**
	  * A getter for Rating attribute
	  * 
	  * @return
	  */
	 public double getRating() {
	  return rating;
	 }
	 /**
	  * Overloaded Tostring method to print needed details
	  * 
	  */
	 public String toString() {
		 String output = this.media.getId()+" "+this.media.getName()+ " "+ " rating: "+ rating;
		 return output;
	 }

}
