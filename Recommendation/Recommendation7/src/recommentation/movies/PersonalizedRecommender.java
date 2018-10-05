/**
 * 
 */
package recommentation.movies;

/**
 * @author Franco G. Moro
 *
 */
public class PersonalizedRecommender {
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
		System.out.print("{");
		for(int x:this.mostSimilarUsers) {
			System.out.print(x);
			if(!(x==mostSimilarUsers[this.mostSimilarUsers.length-1])){
				System.out.print(",");
			}
		}
		System.out.print("}");
		System.out.println();
		for(double[] i:this.usm) {
			System.out.print("{");
			for(double j:i) {
				System.out.print(j);
				System.out.print(",");
			}
			System.out.print("}");
			System.out.println();
		}/*
		System.out.println(this.usm.length);
		System.out.println(this.usm[0].length);
		System.out.println(this.usm[670].length);*/
		
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
		this.usm=new double[nUsers][nUsers];
		for(int i=0;i<this.usm.length;i++) {
			for(int j=0;j<this.usm[i].length;j++) {
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
		Rating[][] output= new Rating[nUsers][];
		for(int i=0;i<output.length;i++) {
			output[i]=getUserRatings(i+1);
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
		int userId=0;
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
			if(n>movies.length) {
				return movies;
			}
			else {
				Movie[] unRated=new Movie[n];
				for(int i=0;i<unRated.length;i++) {
				unRated[i]=movies[i];
				
				}
				return unRated;
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
		Movie[] unratedMovies=new Movie[count];
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
		int pos2=0;
		for(int i=0;i<movies.length;i++) {
			for(int j=0;j<movArr.length;j++) {
				if(movies[i].equals(movArr[j].getId())) {
					unratedMovies[pos2]=movArr[j];
					pos2++;
				}
			}
		}
		
		return unratedMovies;
	}
	
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
				return recommendGenre(unRated,genre);
			}
		}
	}
}

