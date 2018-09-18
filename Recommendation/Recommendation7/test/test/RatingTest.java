package test;
import movies.Rating;
/**
 * @author 1738882
 *
 */
public class RatingTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Rating r1 = new Rating("524,81000192,4.5,235235235235");
		String test1 = r1.toString();
		if (test1.equals("User ID: 81000192" + '\n' + "Movie ID: 524" + '\n' + "Rating: 4.5")) {
			System.out.println("Test 1 works");
			System.out.println(r1);
		}
		else {
			System.out.println("Test 1 fail");
			System.out.println(r1);
			System.out.println();
			System.out.println("User ID: 81000192" + '\n' + "Movie ID: 524" + '\n' + "Rating: 4.5");
		}
		
	}

}
