package movies;

/**
 * @author Franco G. Moro
 *
 */
public class PersonalizedRecommender {
	private double[][] usm;
	private Movie[] movArr;
	private Rating[] ratArr;
	
	public PersonalizedRecommender(Movie[] movArr,Rating[] ratArr) {
		this.movArr=movArr;
		this.ratArr=ratArr;
		int numUsers=findNumUsers(this.ratArr);
		this.usm=new double[numUsers][numUsers];
		
	}
	
	private static int findNumUsers(Rating[] ratings) {
		int count=0;
		for(int i=0;i<ratings.length;i++) {
			if(ratings[i].getUserId()>count) {
				count=ratings[i].getUserId();
			}
		}
		return count;
	}
	/**
	 * Alexander Arella & Franco G. Moro
	 */
	private void fillUsm() {
		int[] missingUsers = getMissingUsers();
		for(int i=0;i<this.usm.length;i++) {
			if(isMissing(missingUsers,i))continue;
			int set=i+1;
			for(int j=0;j<this.usm.length;j++) {
				if(set>this.usm.length) {
					set=1;
				}
				if(isMissing(missingUsers,i)) {
					set++;
					continue;
				}
				else {
					this.usm[i][j]=set;
					set++;
				}
			}
		}
	}
	
	private static boolean isMissing(int[] missingUsers,int id) {
		for(int i=0;i<missingUsers.length;i++) {
			if(missingUsers[i]==id)return true;
		}
		return false;
	}
	private int[] getMissingUsers() {
		int count=0;
		int position=0;
		for(int i=0;i<this.ratArr.length;i++) {
			if(!(isReal(this.ratArr[i].getUserId() ))) {
				count++;
			}
		}
		int[] output=new int[count];
		for(int i=0;i<this.ratArr.length;i++) {
			if(!(isReal(this.ratArr[i].getUserId() ))) {
				output[position]=this.ratArr[i].getUserId();
				position++;
			}
		}
		return output;
		
	}
	
	private boolean isReal(int user) {
		for(int i=0;i<this.ratArr.length;i++) {
			if(this.ratArr[i].getRating()==user)return true;
		}
		return false;
	}
}
