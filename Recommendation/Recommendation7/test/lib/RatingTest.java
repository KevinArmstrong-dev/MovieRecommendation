package lib;

import java.util.Scanner;
import movies.Rating;

/**
 * @author Alex
 *
 */
public class RatingTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean testWorks = false;
		// Test 1: Tests overrided toString() method and functionality of the object
		Rating r1 = new Rating("246,810042,4.5,24598");
		System.out.println(r1.toString() + '\n');
		if (r1.toString().equals("Movie ID: 246" + '\n' + "User ID: 810042" + '\n' + "Rating: 535.0")) {
			testWorks = true;
			System.out.println("Test 1 works");
			System.out.println();
		} else {
			System.out.println("Test 1 did not work");
		}
		testWorks = false;
		// Test 2 to 4: Tests exception handling
		Rating r2;
		try {
			r2 = new Rating("2a4,1,1,1");
		} catch (IllegalArgumentException e) {
			System.out.println("Test 2 works \n");
			testWorks = true;
		}
		if (!testWorks) {
			System.out.println("Test 2 did not work");
		}
		testWorks = false;

		// test 3 illegal character in 2nd field
		Rating r3;
		try {
			r3 = new Rating("24,w1,1,1");
		} catch (IllegalArgumentException e) {
			System.out.println("Test 3 works \n");
			testWorks = true;
		}
		if (!testWorks) {
			System.out.println("Test 3 did not work");
		}
		testWorks = false;

		// test 4 illegal arg in 3rd field
		Rating r4;
		try {
			r4 = new Rating("24,1,a1,1");
		} catch (IllegalArgumentException e) {
			System.out.println("Test 4 works \n");
			testWorks = true;
		}
		if (!testWorks) {
			System.out.println("Test 4 did not work");
		}
		testWorks = false;

		// Custom test 5: Tests with whatever input
		Scanner read = new Scanner(System.in);
		Rating r5;
		try {
			System.out.println("Enter Rating object info");
			r5 = new Rating(read.nextLine());
			read.close();
		} catch (IllegalArgumentException e) {
			System.out.println("Caught IllegalArgumentException");
		}
	}

}
