package main;
import java.io.*;
import java.util.ArrayList;

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
import recommendation.movies.*;
/**
 * @author Kevin Armstrong Rwigamba
 * @author Alexander Arella Girardot
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
		ComboBox<String> cbItems = new ComboBox<String>(FXCollections.observableArrayList());
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
		
		
		TextField userIdTxt=new TextField();
		TextField numOfRecommends = new TextField("N°");
		recommendBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				ArrayList<String> recommends = new ArrayList<String>();
				if (personalBtn.isSelected()) {
					if (movieBtn.isSelected()) {
						PersonalizedRecommender<> pr = new PersonalizedRecommender(items, ratArr);
						recommends = pr.recommend(Integer.parseInt(userIdTxt.getText()), Integer.parseInt(numOfRecommends.getText()));
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
		popularBtn.setToggleGroup(recommendGroup);
		thirdLine.getChildren().addAll(personalBtn, popularBtn, genreLbl, genreTxt, recommendBtn);
		
		TextArea recommendTxt = new TextArea();
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
		loadBtn.setOnAction(new EventHandler<ActionEvent>() { 
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
					String[] bookLines;
					for (Book b : bookArr) {
						bookLines = b.toRawString().split(",");
						cbItems.getItems().add(bookLines[10]);
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
					String[] movieLines;
					for (Movie m : movieArr) {
						movieLines = m.toRawString().split(",");
						cbItems.getItems().add(movieLines[1]);
						System.out.println(movieLines[1]);
					}
					items = movieArr;
				}
				ratArr = ratingArr;
			}
		}
		);
		Button saveBtn=new Button("Save");
		saveBtn.setOnAction(new EventHandler<ActionEvent>() { 
			@Override
			public void handle(ActionEvent e) {
				
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
		RadioButton rating1=new RadioButton("1");
		rating1.setToggleGroup(ratingNums);
		RadioButton rating2=new RadioButton("2");
		rating2.setToggleGroup(ratingNums);
		RadioButton rating3=new RadioButton("3");
		rating3.setToggleGroup(ratingNums);
		RadioButton rating4=new RadioButton("4");
		rating4.setToggleGroup(ratingNums);
		RadioButton rating5=new RadioButton("5");
		rating5.setToggleGroup(ratingNums);
		
		Button ratingBtn = new Button("Rate!");
		ratingBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
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