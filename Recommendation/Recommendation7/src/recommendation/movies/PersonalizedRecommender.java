

















// ignore all errors in this file
























/**
 * 
 */
package recommendation.movies;

import java.util.ArrayList;
import java.util.Arrays;

import recommendation.interfaces.IMovieRecommender;

/**
 * @author Franco G. Moro
 *
 */
public class PersonalizedRecommender implements IMovieRecommender{
	private double[][] usm;
	private Movie[] movArr;
	private Rating[] ratArr;
	private Rating[][] workTable;
	private int[] mostSimilarUsers;
	
	/**
	 * @author Franco G. Moro
	 * @param movArr
	 * @param ratArr
	 * This constructor calls on other methods to initialize the diffenrent fields of this class.
	 */
	public PersonalizedRecommender(Movie[] movArr,Rating[] ratArr) {
		this.movArr=movArr;
		this.ratArr=ratArr;
		fillUsm();
		this.mostSimilarUsers=createMostSimilarUsers();
	}
	/**
	 * @author Franco G. Moro
	 * @return mostSimilarUsers
	 * This method creates the mostSimilarUsers array
	 * 
	 */
	private int[] createMostSimilarUsers() {
		int[] mostSimilarUsers=new int[this.usm.length];
		for(int i=0;i<this.usm.length;i++) {
			int simi=getMostSimilar(this.usm[i]);
			mostSimilarUsers[i]=simi;
		}
		return mostSimilarUsers;
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
	 * Franco G. Moro
	 * This method uses countUsers() and getWorkTable(int nUsers) to fill the
	 * User Similarity Matrix.
	 */
	private void fillUsm() {
		int nUsers=countUsers();
		Rating[][] workTable=getWorkTable(nUsers);
		this.workTable=workTable;
		this.usm=new double[nUsers+1][nUsers+1];
		for(int i=1;i<this.usm.length;i++) {
			for(int j=1;j<this.usm[i].length;j++) {
				if(i==j)continue;
				double score=getScore(workTable[i],workTable[j]);
				this.usm[i][j]=score;
			}
		}
	}
	/**
	 * @author Franco
	 * @param a
	 * @param b
	 * @return score
	 * This helper method is used to calculate the similarity score between two users.
	 */
	private double getScore(Rating[] a,Rating[] b) {
		double score=0;
		for(int i=0;i<a.length;i++) {
			for(int j=0;j<b.length;j++) {
				if(a[i].getMovieId().equals(b[j].getMovieId())) {
					score+=(a[i].getRating()-2.5)*(b[j].getRating()-2.5);
				}
			}
		}
		return score;
	}
	/**
	 * @author Franco G. Moro
	 * @param nUsers
	 * @return output
	 * This helper method is made to create the Rating[][] workTable which is used to create the USM.
	 */
	private Rating[][] getWorkTable(int nUsers){
		Rating[][] output= new Rating[nUsers+1][];
		for(int i=1;i<output.length;i++) {
			output[i]=getUserRatings(i);
		}
		return output;
	}
	/**
	 * @author Franco G. Moro
	 * @param userId
	 * @return output
	 * This helper method is used to gather ever rating that a user has made.
	 */
	private Rating[] getUserRatings(int userId) {
		int count=0;
		for(Rating r:this.ratArr) {
			if(r.getUserId()==userId) {
				count++;
			}
		}
		Rating[] output=new Rating[count];
		int pos=0;
		for(int i=0;i<this.ratArr.length;i++) {
			if(this.ratArr[i].getUserId()==userId) {
				output[pos]=this.ratArr[i];
				pos++;
			}
		}
		return output;
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
	 * Kevin Armstrong Rwigamba 
	 * 
	 * This method returns the most similar user to the given userid
	 * 
	 * @param userid
	 * @return userId
	 */
	private int getSimilarUser(int userid) {
		int userId=-1;
		for(int i=0;i<mostSimilarUsers.length;i++) {
			if(userid == i) {
				userId=mostSimilarUsers[i];
			}
		}
		return userId;
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
	public Movie[] recommend(int userid,int n) {
		
		int similarUser=getSimilarUser(userid);
		if(similarUser==-1) {
			Movie[] recommendations=new Movie[n];
			return recommendations;
		}
		else {
			Movie[] movies=unRatedMovies(userid,similarUser);
			if(n>=movies.length) {
				return movies;
			}
			else {
				Movie[] unRated=new Movie[n];
				for(int i=0;i<unRated.length;i++) {
				unRated[i]=movies[i];
				
				}
				
				return orderMovies(unRated,similarUser);
			}
		}
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
	private Movie[] unRatedMovies(int givenUser,int similarUser) {
		int count=0;
		Rating[] givenArr=new Rating[workTable[givenUser].length];
		Rating[] similarArr=new Rating[workTable[similarUser].length];
		for(int i=0;i<givenArr.length;i++) {
			givenArr[i]=workTable[givenUser][i];
		}
		for(int i=0;i<similarArr.length;i++) {
			similarArr[i]=workTable[similarUser][i];
		}
		for(int i=0;i<similarArr.length;i++) {
			for(int j=0;j<givenArr.length;j++) {
				if(!(similarArr[i].getMovieId().equals(givenArr[j].getMovieId()))) {
					count++;
				}
			}
		}
		String[] movies=new String[count];
		int pos=0;
		for(int i=0;i<similarArr.length;i++) {
			for(int j=0;j<givenArr.length;j++) {
				if(!(similarArr[i].getMovieId().equals(givenArr[j].getMovieId()))) {
					movies[pos]=similarArr[i].getMovieId();
					pos++;
				}
			}
		}
		String[] noDupes=dupeRemoval(movies);
		Movie[] unratedMovies=new Movie[noDupes.length];
		int pos2=0;
		for(int i=0;i<noDupes.length;i++) {
			for(int j=0;j<movArr.length;j++) {
				if(noDupes[i].equals(movArr[j].getId())) {
					unratedMovies[pos2]=movArr[j];
					pos2++;
				}
			}
		}
		
		return unratedMovies;
	}

	/**
	 * @author Kevin Armstrong Rwigamba
	 * 
	 * this is a helper method to remove the movies which doesn't correspond to given genre
	 * and create a new Movie array with only specified genre
	 * 
	 * @param temp
	 * @param genre
	 * @return
	 */
	private Movie[] recommendGenre(Movie[] temp,String genre) {
		int count=0;
		for(int i=0;i<temp.length;i++) {
			if(temp[i].hasGenre(genre)) {
				count++;
			}
		}
		Movie[] sameGenre=new Movie[count];
		int pos=0;
		for(int i=0;i<temp.length;i++) {
			if(temp[i].hasGenre(genre)) {
				sameGenre[pos]=temp[i];
				pos++;
			}
		}
		return sameGenre;
		
	}
	
	/**
	 * @author Kevin Armstrong Rwigamba
	 * 
	 * This Method recommends a given user a number of specified movies by choosing also the genre
	 * 
	 * 
	 * @param userid
	 * @param n
	 * @param genre
	 * @return
	 */
	public Movie[] recommend(int userid,int n,String genre) {
		int similarUser=getSimilarUser(userid);
		if(similarUser==-1) {
			Movie[] recommendations=new Movie[n];
			return recommendations;
		}
		else {
			Movie[] movies=unRatedMovies(userid,similarUser);
			if(n>movies.length) {
				return recommendGenre(movies,genre);
				
			}
			else {
				Movie[] unRated=new Movie[n];
				for(int i=0;i<unRated.length;i++) {
				unRated[i]=movies[i];
				
				}
				//returns a sorted array of movies
				return orderMovies(recommendGenre(unRated,genre),similarUser);
		
			}
		}
	}
	
	
	/**
	  * @author Kevin Armstrong Rwigamba
	  * 
	  * Modified Selection Sort to sort the collection array 
	  * or finished
	  * 
	  * @param x
	  */
	  public static void selectionSort(RecommendAssist[] x) {
	      for(int start = 0; start < x.length; start++) {
	        // start is the index of the first element of the 
	        // unsorted part of the array
	        
	        // find the max in the unsorted part
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
	 *@author Kevin Armstrong Rwigamba
	 * 
	 * {@link https://javaconceptoftheday.com/remove-duplicate-elements-array-java/}
	 * 
	 * This Method removes duplicate movies in the array of Strings
	 * 
	 * @param movies
	 * @return
	 */
	private String[] dupeRemoval(String [] movies) {
	         
	     int noOfUniqueElements = movies.length;
	         
	      for (int i = 0; i < noOfUniqueElements; i++) {
           for (int j = i+1; j < noOfUniqueElements; j++){
        	      
	             if(movies[i].equals(movies[j])){
	                  
	                  movies[j] = movies[noOfUniqueElements-1];
	                   
	                   noOfUniqueElements--;
	                     
	                    j--;
	                }
	            }
	        }
	        String [] arrayWithoutDuplicates = Arrays.copyOf(movies, noOfUniqueElements);
	        return arrayWithoutDuplicates;
	}

	/**
	 * @author Kevin Armstrong Rwigamba
	 * 
	 * Helper Method to help return movies in order of their respective ratings
	 * 
	 * @param x
	 * @param user
	 */
	private Movie[] orderMovies(Movie [] x,int user) {
		RecommendAssist[] unrated=new RecommendAssist[x.length];
		for(int i=0;i<x.length;i++) {
			for(int j=0;j<ratArr.length;j++) {
				if(x[i].getId().equals(ratArr[j].getMovieId()) && (ratArr[j].getUserId()==user)) {
					unrated[i]=new RecommendAssist(x[i],ratArr[j].getRating());
				}
			}
		}
		selectionSort(unrated);
		return orderedRecommend(unrated);
	}
	
	/**
	 * @author Kevin Armstrong 
	 * 
	 * This is to avoid code duplication while copying movies from recommendAssist object
	 * @param movies
	 * @return
	 */
	private Movie[] orderedRecommend(RecommendAssist[] movies) {
		Movie[] temp=new Movie[movies.length];
		for(int i=0;i<movies.length;i++) {
			temp[i]=movies[i].getMedia();
		}
		return temp;
	}

}

