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
			 this.id=fields[0];
			 this.name=fields[10].replaceAll("%2C", ",");
			 this.isbn=fields[5];
			 this.authors=fields[7].split("%2C");
		 
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
		 String output=this.id+",,,,,"+this.isbn+","+",";
		 for(int i=0;i<this.authors.length;i++) {
			 output+=this.authors[i];
			 if(i!=this.authors.length-1) {
				 output+="%2C ";
			 }
		 }
		 output+=",,,";
		 output+=this.name;
		 
		 return output;
	 }

}
