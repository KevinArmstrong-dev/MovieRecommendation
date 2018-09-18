/**
 * 
 */
package lib;
import movies.Movie;
/**
 * @author 1738714
 *Class used to test the methods and functioning of the Movie class.
 *It uses an example provided in the instructions for phase 1.
 */
public class MovieTest {
	public static void main(String[] args) {
		String fakeCvs="11,American President%2C The (1995),Comedy|Drama|Romance";
		Movie movieTest= new Movie(fakeCvs);
		testGetName(movieTest);
		testGetYear(movieTest);
		testGetId(movieTest);
		testGetGenres(movieTest);
		testHasGenre(movieTest);
		
	}
	/**
	 * @param movieTest
	 * Test if the name attribute from the Movie class is properly initiated.
	 *Prints Success if it works and prints fail if it doesn't,
	 */
	private static void testGetName(Movie movieTest) {
		String referance= "American President, The";
		System.out.print( movieTest.getName() );
		if(movieTest.getName().equals(referance)) {
			System.out.println("Name|SUCCESS");
		}
		else {
			System.out.println("Name|FAIL");
			System.out.println(referance);
			System.out.println(movieTest.getName());
		}
	}
	/**
	 * 
	 * @param movieTest
	 */
	private static void testGetYear(Movie movieTest) {
		int referance=1995;
		if(movieTest.getYear()==referance) {
			System.out.println(referance);
			System.out.println(movieTest.getYear());
			System.out.println("Year|SUCCESS");
		}
		else {
			System.out.println(referance);
			System.out.println(movieTest.getYear());
			System.out.println("Year|FAIL");
		}
	}
	private static void testGetId(Movie movieTest) {
		String ref="11";
		if(movieTest.getId().equals(ref)) {
			System.out.println(ref);
			System.out.println(movieTest.getId());
			System.out.println("Id|SUCCESS");
		}
		else {
			System.out.println(ref);
			System.out.println(movieTest.getId());
			System.out.println("Id|FAIL");
		}
	}
	private static void testGetGenres(Movie movieTest) {
		String[] ref= {"Comedy","Drama","Romance"};
		String[] movie=movieTest.getGenres();
		for(int i=0;i<movie.length;i++) {
			System.out.println(movie[i]);
		}
		for(int i=0; i< ref.length;i++) {
			if(!movie[i].equals(ref[i])) {
				System.out.println("Genres|FAIL");
				System.out.println(movie[i]);
				System.out.println(ref[i]);
				return;
			}
		}
		System.out.println("Genres|SUCESS");
	}
	private static void testHasGenre(Movie movieTest) {
		String ref="Drama";
		String fakeRef="Horror";
		if(movieTest.hasGenre(ref)) {
			System.out.println("Should Work| SUCCESS");
		}
		else {
			System.out.println("Should Work| FAIL");
		}
		
		if(!movieTest.hasGenre(fakeRef)) {
			System.out.println("Should not Work| SUCCESS");
		}
		else {
			System.out.println("Should not Work| FAIL");
		}
	}

}
