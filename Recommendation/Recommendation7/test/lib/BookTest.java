package lib;
import recommendation.book.*;
public class BookTest {

	public static void main(String[] args) {
		String test1="1,2767052,2767052,2792775,272,439023483,9780000000000,Suzanne Collins,2008,The Hunger Games,The Hunger Games (The Hunger Games%2C #1),e,a,r,w,w,r,r,r,r,r,i,i";
		Book test = new Book(test1);
		System.out.println(test);
	}
}
