package recommendation.book;
import recommendation.interfaces.Item;
import recommendation.interfaces.Saveable;

public class Book implements Item,Saveable{
	 private String id;
	 private String name;
	 private String[] authors;
	 private String isbn;
	 
	 public Book(String line) {
		 String[] fields= line.split(",");
		 if(fields.length==23) {
			 this.id=fields[0];
			 this.name=fields[10].replaceAll("%2C", ",");
			 this.isbn=fields[5];
			 this.authors=fields[7].split("%2C");
		 }
		 else if(fields.length==4) {
			 this.id=fields[0];
			 this.isbn=fields[1];
			 this.authors=fields[2].split("%2C");
			 this.name=fields[3];
		 }
		 else{
			 throw new IllegalArgumentException("Error:The number of commas in the movie string is not equal to 2.");
		 }
	 }
	 public String getName() {
		 return this.name;
	 }
	 public String getId() {
		 return this.id;
	 }
	 public String[] getAuthors() {
		 return this.authors;
	 }
	 public String getIsbn() {
		 return this.isbn;
	 }
	 public String toString() {
		 String output="";
		 output= this.name+ " Id: "+this.id+" Authors: ";
		 for(String x : this.authors) {
			 output+= x+ " ";
		 }
		 output+="ISBN: "+this.isbn;
		 return output;
	 }
	 public String toRawString() {
		 String output=this.id+","+this.isbn+",";
		 for(String s :authors) {
			 output+=s+",";
		 }
		 output+=this.name;
		 return output;
	 }

}
