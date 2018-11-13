/**
 * 
 */
package recommendation.interfaces;

import java.util.*;
import recommendation.movies.Movie;
import recommendation.movies.Rating;
import recommendation.movies.RecommendAssist;

/**
 * @author Franco G. Moro
 * @author Alexander Arella Girardot
 * @author Kevin Armstrong Rwigamba
 *
 */
public class PersonalizedRecommender<T extends Item> implements IRecommender<T> {
	private double[][] usm;
	private T[] movArr;
	private Rating[] ratArr;
	private HashMap<Integer,ArrayList<Rating>> workTable;
	private HashMap<Integer,Integer> mostSimilarUsers;
	
	/**
	 * @author Franco G. Moro
	 * @param movArr
	 * @param ratArr
	 * This constructor calls on other methods to initialize the diffenrent fields of this class.
	 */
	public PersonalizedRecommender(T[] movArr,Rating[] ratArr) {
		this.movArr=movArr;
		this.ratArr=ratArr;
		fillUsm();
		createMostSimilarUsers();
	}
	
	/**
	 * Franco G. Moro
	 * This method uses countUsers() and getWorkTable(int nUsers) to fill the
	 * User Similarity Matrix.
	 */
	private void fillUsm() {
		int nUsers=countUsers();
		this.workTable=getWorkTable(nUsers);
		this.usm=new double[nUsers+1][nUsers+1];
		for(int i=1;i<this.usm.length;i++) {
			for(int j=1;j<this.usm[i].length;j++) {
				if(i==j)continue;
				double score=getScore(workTable.get(i),workTable.get(j));
				this.usm[i][j]=score;
			}
		}
	}
	
	/**
	 * @author Franco G. Moro
	 * @return mostSimilarUsers
	 * This method creates the mostSimilarUsers array
	 * 
	 */
	private void createMostSimilarUsers() {
		for(int i=0;i<this.usm.length;i++) {
			int simi=getMostSimilar(this.usm[i]);
			this.mostSimilarUsers.put(i,simi);
		}
	}
	
	/**
	 * @author Franco G. Moro
	 * @return count
	 * This helper method counts what the number of users that made reviews is.
	 */
	private int countUsers(){
		int count=0;
		for(int i=0;i<this.ratArr.length;i++) {
			if(ratArr[i].getUserId() > count) {
				count=ratArr[i].getUserId();
			}
		}
		return count;
	}
	
	/**
	 * @author Franco G. Moro
	 * @param client
	 * @return index
	 * This helper method return the index +1 of the user that is most similar to the one who's array the input is.
	 * 
	 */
	private int getMostSimilar(double[] client) {
		double highScore=0;
		int index=0;
		for(int i=0;i<client.length;i++) {
			if(client[i]>highScore) {
				highScore=client[i];
				index=i;
			}
		}
		if(highScore<=0)return -1;
		return index;
	}
	
	/**
	 * @author Franco G. Moro
	 * @param nUsers
	 * @return output
	 * This helper method is made to create the Rating[][] workTable which is used to create the USM.
	 */
	private HashMap<Integer,ArrayList<Rating>> getWorkTable(int nUsers){
		HashMap<Integer,ArrayList<Rating>> output=new HashMap<Integer,ArrayList<Rating>>();
		for(int i=1;i<nUsers;i++) {
			output.put(i,getUserRatings(i));
		}
		return output;
	}
	
	/**
	 * @author Franco G. Moro
	 * @param userId
	 * @return output
	 * This helper method is used to gather ever rating that a user has made.
	 */
	private ArrayList<Rating> getUserRatings(int userId) {
		ArrayList<Rating> output=new ArrayList<Rating>();
		for(int i=0;i<this.ratArr.length;i++) {
			if(this.ratArr[i].getUserId()==userId) {
				output.add(this.ratArr[i]);
			}
		}
		return output;
	}
	
	/**
	 * @author Franco
	 * @param a
	 * @param b
	 * @return score
	 * This helper method is used to calculate the similarity score between two users.
	 */
	private double getScore(ArrayList<Rating> a,ArrayList<Rating> b) {
		double score=0;
		for(int i=0;i<a.size();i++) {
			for(int j=0;j<b.size();j++) {
				if(a.get(i).getMovieId().equals(b.get(j).getMovieId())) {
					score+=(a.get(i).getRating()-2.5)*(b.get(j).getRating()-2.5);
				}
			}
		}
		return score;
	}
	
	/**
	 * @author Kevin Armstrong Rwigamba
	 * 
	 * This Recommend Method Takes a userid and a number of movies to be recommended
	 * then a recommendation is made based on another similar user 
	 * 
	 * @param userid
	 * @param n
	 * @return
	 */
	public ArrayList<T> recommend(int userid,int n) {
		
		int similarUser=getSimilarUser(userid);
		if(similarUser==-1) {
			ArrayList<T> recommendations= new ArrayList<T>(n);
			return recommendations;
		}
		else {
			ArrayList<T> movies=unRatedMovies(userid,similarUser);
			if(n>=movies.size()) {
				return movies;
			}
			else {
				ArrayList<T> unRated = new ArrayList<T>(n);
				for(int i=0;i<n;i++) {
				unRated.add(movies.get(i));
				
				}
				
				return orderMovies(unRated,similarUser);
			}
		}
	}
	
	/**
	 * @author Kevin Armstrong Rwigamba
	 * 
	 * Helper Method to help return movies in order of their respective ratings
	 * 
	 * @param unRated2
	 * @param user
	 */
	private ArrayList<T> orderMovies(ArrayList<T> unRated2,int user) {
		ArrayList<RecommendAssist<T>> unrated=new ArrayList<RecommendAssist<T>>(unRated2.size());
		for(int i=0;i<unRated2.size();i++) {
			for(int j=0;j<ratArr.length;j++) {
				if(unRated2.get(i).getId().equals(ratArr[j].getMovieId()) && (ratArr[j].getUserId()==user)) {
					unrated.add(new RecommendAssist<T>(unRated2.get(i),ratArr[j].getRating()));
				}
			}
		}
		selectionSort(unrated);
		return orderedRecommend(unrated);
	}
	
	/**
	 * @author Kevin Armstrong 
	 * @author Alexander Arella Girardot
	 * 
	 * This is to avoid code duplication while copying movies from recommendAssist object
	 * @param movies
	 * @return
	 */
	private ArrayList<T> orderedRecommend(ArrayList<RecommendAssist<T>> movies) {
		ArrayList<T> temp = new ArrayList<T>(movies.size());
		for(int i=0;i<movies.size();i++) {
			temp.add(movies.get(i).getMedia());
		}
		return temp;
	}
	/**
	  * @author Kevin Armstrong Rwigamba
	  * 
	  * Modified Selection Sort to sort the collection array 
	  * or finished
	  * 
	  * @param x
	  */
	  public void selectionSort(ArrayList<RecommendAssist<T>> x) {
	      for(int start = 0; start < x.size(); start++) {
	        // start is the index of the first element of the 
	        // unsorted part of the array
	        
	        // find the max in the unsorted part
	        int guess = start; 
	        for(int i=start+1; i<x.size(); i++) {
	          if((x.get(guess)).getRating() < (x.get(i)).getRating()) {
	            guess =i;
	          }
	        }
	        
	        // swap the min element with the first element of the
	        // unsorted part of the array
	        RecommendAssist<T> temp = x.get(guess);
	        x.remove(guess);
	        x.add(guess, x.get(start));
	        x.remove(start);
	        x.add(start, temp);
	        
	      }
	  }
	
	/**
	 * Kevin Armstrong Rwigamba 
	 * 
	 * This method returns the most similar user to the given userid
	 * 
	 * @param userid
	 * @return userId
	 */
	private int getSimilarUser(int userid) {
		return mostSimilarUsers.get(userid);
	}
	
	/**
	 * @author Kevin Armstrong Rwigamba
	 *
	 * This method takes two users and returns an array of unrated movies by the given user
	 * in contrast to the similar user
	 * 
	 * @param givenUser
	 * @param similarUser
	 * @return
	 */
	private ArrayList<T> unRatedMovies(int givenUser,int similarUser) {
		ArrayList<Rating> givenArr=this.workTable.get(givenUser);
		ArrayList<Rating> similarArr=this.workTable.get(similarUser);
		ArrayList<String> movies=new ArrayList<String>();
		for(int i=0;i<similarArr.size();i++) {
			for(int j=0;j<givenArr.size();j++) {
				if(!(similarArr.get(i).getMovieId().equals(givenArr.get(j).getMovieId()))) {
					movies.add(similarArr.get(i).getMovieId());
				}
			}
		}
		/*String[] noDupes = dupeRemoval(movies);
		ArrayList<T> unratedMovies = new ArrayList<T>(noDupes.length);
		for(int i=0;i<noDupes.length;i++) {
			for(int j=0;j<movArr.length;j++) {
				if(noDupes[i].equals(movArr[j].getId())) {
					unratedMovies.add(movArr[j]);
				}
			}
		}*/
		ArrayList<T> output= new ArrayList<T>();
		for(int i=0;i<movies.size();i++) {
			for(int j=0;j<movArr.length;j++) {
				if(movArr[j].getId().equals(movies.get(i))) {
					output.add(movArr[j]);
				}
			}
		}
		return output;
	}
	
	/**
	 *@author Kevin Armstrong Rwigamba
	 * 
	 * {@link https://javaconceptoftheday.com/remove-duplicate-elements-array-java/}
	 * 
	 * This Method removes duplicate movies in the array of Strings
	 * 
	 * @param movies
	 * @return
	 */
	/*private String[] dupeRemoval(ArrayList<String> movies) {
	         
	     int noOfUniqueElements = movies.size();
	         
	      for (int i = 0; i < noOfUniqueElements; i++) {
           for (int j = i+1; j < noOfUniqueElements; j++){
        	      
	             if(movies.get(i).equals(movies.get(j))){
	                  
	                  movies[j] = movies[noOfUniqueElements-1];
	                   
	                   noOfUniqueElements--;
	                     
	                    j--;
	                }
	            }
	        }
	        String [] arrayWithoutDuplicates = Arrays.copyOf(movies, noOfUniqueElements);
	        return arrayWithoutDuplicates;
	}*/
}
