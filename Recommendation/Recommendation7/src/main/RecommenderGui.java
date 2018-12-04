package main;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.geometry.Insets;
import javafx.application.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import recommendation.book.*;
import recommendation.fileio.*;
import recommendation.interfaces.*;
import recommendation.interfaces.PersonalizedRecommender;
import recommendation.movies.PopularMovieRecommender;
import recommendation.movies.Rating;
import recommendation.movies.Movie;
/**
 * @author Alexander Arella Girardot
 * @author Kevin Armstrong Rwigamba
 */
public class RecommenderGui extends Application {
	Item[] items; // thinking of using a field along the lines of this, maybe a list, for reading the item files
	Rating[] ratArr;
	@Override
	public void start(Stage stage) {
		stage.setWidth(1500);
		stage.setHeight(700);
		Group root=new Group();
		Scene  scene  =  new  Scene(root, 500, 300);
		scene.setFill(Color.WHITESMOKE);
		
		stage.setTitle("Recommender");
		VBox container=new VBox();
		HBox firstLine=new HBox();
		HBox secondLine=new HBox();
		HBox thirdLine = new HBox();
		HBox fourthLine = new HBox();
		//Select list
		ComboBox<Item> cbItems = new ComboBox<Item>(FXCollections.observableArrayList());
		cbItems.setPromptText("Select");
		//Radio buttons For Movie or Selection
		ToggleGroup itemType=new ToggleGroup();
		RadioButton bookBtn=new RadioButton("Book");
		bookBtn.setToggleGroup(itemType);
		RadioButton movieBtn=new RadioButton("Movie");
		movieBtn.setToggleGroup(itemType);
		
		
		ToggleGroup recommendGroup = new ToggleGroup();
		RadioButton personalBtn = new RadioButton("Personal Recommender");
		RadioButton popularBtn = new RadioButton("Popular Recommender");
		TextField genreTxt = new TextField();
		Label genreLbl = new Label("Genre: ");
		Button recommendBtn = new Button("Get Recommendations!");

		TextArea recommendTxt = new TextArea();
		
		TextField userIdTxt=new TextField();
		TextField numOfRecommendsTxt = new TextField("Number of recommends");
		recommendBtn.setOnAction(new EventHandler<ActionEvent>() {
			/**
			 * @author Alexander Arella Girardot
			 */
			@Override
			public void handle(ActionEvent e) {
				if (personalBtn.isSelected()) {
					if (movieBtn.isSelected()) {
						ArrayList<Movie> recommends = new ArrayList<Movie>();
						PersonalizedRecommender<Movie> pr = new PersonalizedRecommender(items, ratArr);
						recommends = pr.recommend(Integer.parseInt(userIdTxt.getText()), Integer.parseInt(numOfRecommendsTxt.getText()));
						for (Movie mov : recommends) {
							recommendTxt.setText(recommendTxt.getText() + mov.toString() + '\n');
						}
					}
					else if (bookBtn.isSelected()) {
						ArrayList<Book> recommends = new ArrayList<Book>();
						PersonalizedRecommender<Book> pr = new PersonalizedRecommender(items, ratArr);
						recommends = pr.recommend(Integer.parseInt(userIdTxt.getText()), Integer.parseInt(numOfRecommendsTxt.getText()));
						for (Book bok : recommends) {
							recommendTxt.setText(recommendTxt.getText() + bok.toString() + '\n');
						}
					}
				}
				else if (popularBtn.isSelected()) {
					if (movieBtn.isSelected()) {
						ArrayList<Movie> recommends = new ArrayList<Movie>();
						if (genreTxt.getText().equals("")) {
							PopularRecommender<Movie> pr = new PopularRecommender(ratArr, items);
							recommends = pr.recommend(Integer.parseInt(userIdTxt.getText()), Integer.parseInt(numOfRecommendsTxt.getText()));
							for (Movie mov : recommends) {
								recommendTxt.setText(recommendTxt.getText() + mov.toString() + '\n');
							}
						}
						else {
							PopularMovieRecommender pr = new PopularMovieRecommender((Movie[])items, ratArr);
							recommends = pr.recommend(Integer.parseInt(userIdTxt.getText()), Integer.parseInt(numOfRecommendsTxt.getText()), genreTxt.getText());
							for (Movie mov : recommends) {
								recommendTxt.setText(recommendTxt.getText() + mov.toString() + '\n');
							}
						}
					}
					else if (bookBtn.isSelected()) {
						ArrayList<Book> recommends = new ArrayList<Book>();
						PopularRecommender<Book> pr = new PopularRecommender(ratArr, items);
						recommends = pr.recommend(Integer.parseInt(userIdTxt.getText()), Integer.parseInt(numOfRecommendsTxt.getText()));
						for (Book bok : recommends) {
							recommendTxt.setText(recommendTxt.getText() + bok.toString() + '\n');
						}
					}
				}
			}
		}
		);
		personalBtn.setToggleGroup(recommendGroup);
		HBox.setMargin(recommendBtn, new Insets(2.5, 5, 0, 0));
		HBox.setMargin(genreLbl, new Insets(5, 5, 0, 0));
		HBox.setMargin(genreTxt, new Insets(2.5, 5, 0, 0));
		HBox.setMargin(personalBtn, new Insets(5, 5, 0, 0));
		HBox.setMargin(popularBtn, new Insets(5, 20, 0, 0));
		HBox.setMargin(numOfRecommendsTxt, new Insets(2.5,5,0,0));
		popularBtn.setToggleGroup(recommendGroup);
		thirdLine.getChildren().addAll(personalBtn, popularBtn, genreLbl, genreTxt, numOfRecommendsTxt, recommendBtn);
		
		recommendTxt.setEditable(false);
		recommendTxt.setPrefHeight(400);
		recommendTxt.setPrefWidth(1000);
		fourthLine.getChildren().add(recommendTxt);
		
		
		//file path
		TextField pathTxt =new TextField("datafiles/");
		TextField ratePath=new TextField("datafiles/");
		Label ratingsLbl=new Label("Ratings: ");
		
		//Load and Save Button
		Button loadBtn=new Button("Load");
		//this is to check if the ratings file has been loaded

		loadBtn.setOnAction(new EventHandler<ActionEvent>() { 
			/**
			 * @author Alexander Arella Girardot
			 */
			@Override
			public void handle(ActionEvent e) {
				items = null;
				cbItems.getItems().removeAll(); // this doesn't even work
				Rating[] ratingArr = null;
				try {
					ratingArr = GoodReadsFileReader.loadRatings(ratePath.getText());
					
				} catch (IOException e3){
					System.out.println("Rating IOException");
				}
				if (bookBtn.isSelected()) {
					Book[] bookArr = null;
					try {
						bookArr = GoodReadsFileReader.loadBooks(pathTxt.getText());
					} catch (IOException e1) {
						System.out.println("Book IOException");
					}
					for (Book b : bookArr) {
						cbItems.getItems().add(b);
					}
					items = bookArr;
				}
				
				else if (movieBtn.isSelected()) {
					Movie[] movieArr = null;
					try {
						movieArr = MovieLensFileReader.loadMovies(pathTxt.getText());
					} catch (IOException e2) {
						System.out.println("Movie IOException");
					}
					for (Movie m : movieArr) {
						cbItems.getItems().add(m);
						System.out.println(m);
					}
					items = movieArr;
				}
				ratArr = ratingArr;
			}
		}
		);
		Button saveBtn=new Button("Save");
		
		/**
		 * @author kevin Armstrong Rwigamba
		 * 
		 * This event will save the ratings given by the user to a new file
		 */
		saveBtn.setOnAction(new EventHandler<ActionEvent>() { 
			
			@Override
			public void handle(ActionEvent e) {
				if(movieBtn.isSelected()) {
				MovieLensFileReader.saveToFile(ratArr, "datafiles/sorted/ratings2.csv", "userid,movieid,rating,timestamp"); 
				
				
				}
				else if(bookBtn.isSelected()) {
					GoodReadsFileReader.saveToFile(ratArr, "datafiles/books/ratings2.csv", "user_id,book_id,rating");
				}
			}
		}
		);
		//Adding the Third Line to the VBox
		Label itemLbl=new Label("Item: ");
		firstLine.getChildren().addAll(itemLbl, bookBtn, movieBtn, pathTxt,ratingsLbl,ratePath,loadBtn,saveBtn);
		
		//Adding The fourth Line the VBox
		Label userIdLbl=new Label("UserId: ");
		//Here we shall add the list view after
		ToggleGroup ratingNums = new ToggleGroup();
		RadioButton rating0=new RadioButton("0");
		rating0.setToggleGroup(ratingNums);
		rating0.setUserData("rating0");
		
		RadioButton rating1=new RadioButton("1");
		rating1.setToggleGroup(ratingNums);
		rating1.setUserData("rating1");
		
		RadioButton rating2=new RadioButton("2");
		rating2.setToggleGroup(ratingNums);
		rating2.setUserData("rating2");
		
		RadioButton rating3=new RadioButton("3");
		rating3.setToggleGroup(ratingNums);
		rating3.setUserData("rating3");
		
		RadioButton rating4=new RadioButton("4");
		rating4.setToggleGroup(ratingNums);
		rating4.setUserData("rating4");
		
		RadioButton rating5=new RadioButton("5");
		rating5.setToggleGroup(ratingNums);
		rating5.setUserData("rating5");
		
		Button ratingBtn = new Button("Rate!");
		
		/**
		 * @author kevin Armstrong Rwigamba
		 * 
		 * This event will take the rating array and add the rating entered by the user
		 * 
		 */
		ratingBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			/**
			 * This method will return the matching rating selected by the user
			 * @return
			 */
			private double ratingFinder() {
				double rating=0;
				switch(ratingNums.getSelectedToggle().getUserData().toString()) {
				
				case "rating0":
					rating=0;
					break;
					
				case "rating1":
					rating=1;
					break;
					
				case "rating2":
					rating=2;
					break;
					
				case "rating3":
					 rating=3;
					 break;
					
				case "rating4":
					 rating=4;
					 break;
					
			   case "rating5":
					rating=5;
					break;
					
					default:
					rating=-1;
					break;
					
				}
				return rating;
			}
			@Override
			public void handle(ActionEvent e) {
				
				//to update the rating given by the user
				if(movieBtn.isSelected()) {
					
				Rating[] ratings2=Arrays.copyOf(ratArr,ratArr.length+1);
				
				for(int i=0;i<ratArr.length;i++) {
					ratings2[i]=ratArr[i];
				}
				ratArr=ratings2;
				//retrieving the rating given by the user by a helper method
				double rating=ratingFinder();
				
				String tmp=userIdTxt.getText()+","+((Movie)cbItems.getValue()).getId()+","+Double.toString(rating)+","+"10101010";
				//System.out.println(tmp); for debugging purpose
				
				ratings2[ratings2.length-1]=new Rating(tmp);
				
			
				}
				else if(bookBtn.isSelected()) {
					//Saving the rating of a book given by the current user
					
					Rating[] bookRating2=Arrays.copyOf(ratArr,ratArr.length+1);
					for(int i=0;i<ratArr.length;i++) {
						bookRating2[i]=ratArr[i];
					}
					ratArr=bookRating2;
					double bookrating=ratingFinder();
					String tmpBook=userIdTxt.getText()+","+((Book)cbItems.getValue()).getId()+","+Double.toString(bookrating);
					System.out.println(tmpBook);
					bookRating2[bookRating2.length-1]=new Rating(tmpBook) ;
				}
				//in case the user has not selected what to rate (Book or Movie) this button will be disabled 
				else {
					ratingBtn.isDisabled();
				}
			}
		}
				
				
				);
		
		secondLine.getChildren().addAll(userIdLbl,userIdTxt, cbItems, rating0, rating1, rating2, rating3, rating4, rating5, ratingBtn);
		HBox.setMargin(rating0, new Insets(5, 5, 0, 5));
		HBox.setMargin(rating1, new Insets(5, 5, 0, 5));
		HBox.setMargin(rating2, new Insets(5, 5, 0, 5));
		HBox.setMargin(rating3, new Insets(5, 5, 0, 5));
		HBox.setMargin(rating4, new Insets(5, 5, 0, 5));
		HBox.setMargin(rating5, new Insets(5, 5, 0, 5));
		HBox.setMargin(ratingBtn, new Insets(2.5, 5, 0, 5));
		HBox.setMargin(movieBtn, new Insets(2.5, 5, 0, 5));
		HBox.setMargin(bookBtn, new Insets(2.5, 5, 0, 5));
		HBox.setMargin(itemLbl, new Insets(2.5,0 , 0, 0));
		HBox.setMargin(ratingsLbl, new Insets(2.5,0 ,0 ,0));
		HBox.setMargin(userIdLbl, new Insets(2.5, 0, 0, 0));
		//Here we shall add the list view after
		container.getChildren().addAll(firstLine, secondLine, thirdLine, fourthLine);
		root.getChildren().add(container);
		stage.setScene(scene);       
		stage.show();
	}
	
	public  static  void  main(String[]  args)  {                                        
		Application.launch(args);              
	}
}