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
	
	public PersonalizedRecommender(Movie[] movArr,Rating[] ratArr) {
		this.movArr=movArr;
		this.ratArr=ratArr;
		fillUsm();
		
	}
	private void fillUsm() {
		Rating[][] workTable=getWorkTable(countUsers());
		for(int i=0;i<workTable.length;i++) {
			System.out.print("{");
			for(int j=0;j<workTable[i].length;j++) {
				System.out.print(workTable[i][j]+ ",");
			}
			System.out.print("}");
			System.out.println();
		}
		
		
	}
	
	private Rating[][] getWorkTable(int nUsers){
		Rating[][] output= new Rating[nUsers][];
		for(int i=0;i<output.length;i++) {
			output[i]=getUserRatings(i);
		}
		return output;
		
		
	}
	private Rating[] getUserRatings(int userId) {
		int count=0;
		for(Rating r:this.ratArr) {
			if(r.getUserId()==userId) {
				count++;
			}
		}
		Rating[] output=new Rating[count];
		int pos=0;
		for(Rating r:this.ratArr) {
			if(r.getUserId()==userId) {
				output[pos]=r;
				pos++;
			}
		}
		return output;
	}
	private int countUsers(){
		int count=0;
		for(int i=1;i<this.ratArr.length;i++) {
			if(ratArr[i].getUserId() >= count) {
				count=ratArr[i].getUserId();
			}
		}
		return count;
	}
}
