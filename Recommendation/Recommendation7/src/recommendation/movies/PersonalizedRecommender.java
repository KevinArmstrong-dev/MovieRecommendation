/**
 * 
 */
package recommendation.movies;

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
		//Uncomment this to print out the matrices.
		/*System.out.print("{");
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
		System.out.println(this.usm[670].length);
		System.out.println("User 2's most similar:"+mostSimilarUsers[2]);
		System.out.println("To make sure this is really user 2:");
		for(Rating x:workTable[2]) {
			System.out.println(x);
		}*/
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
}
