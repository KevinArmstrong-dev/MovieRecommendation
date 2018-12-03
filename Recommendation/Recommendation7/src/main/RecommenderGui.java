/**
 * 
 */
package main;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.Insets;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import recommendation.fileio.MovieLensFileReader;
/**
 * @author Kevin Armstrong Rwigamba
 *@author Alexander Arella Girardot
 */
public class RecommenderGui extends Application {
	

	@Override
	public void start(Stage stage) {
		stage.setWidth(800);
		Group root=new Group();
		Scene  scene  =  new  Scene(root,  600,  300);        
		scene.setFill(Color.WHITESMOKE);
		
		stage.setTitle("Recommender");
		VBox container=new VBox();
		HBox thirdLine=new HBox();
		HBox fourthLine=new HBox();
		HBox fifthLine=new HBox();

		//Radio buttons For Movie or Selection
		ToggleGroup itemType=new ToggleGroup();
		RadioButton book=new RadioButton("Book");
		book.setToggleGroup(itemType);
		RadioButton movie=new RadioButton("Movie");
		movie.setToggleGroup(itemType);
		container.getChildren().addAll(movie,book);
		
		//file path
		TextField pathTxt =new TextField("datafiles\\");
		TextField ratePathTxt=new TextField("datafiles\\");
		Label ratings=new Label("Ratings: ");
		
		//Load and Save Button
		Button loadBtn=new Button("Load");
		//This will handle the event of loading the files
		loadBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
					System.out.println("Test");
				}
		});
		Button saveBtn=new Button("Save");
		
		//Adding the Third Line to the VBox
		Label Item=new Label("Item: ");
		thirdLine.getChildren().addAll(Item,pathTxt,ratings,ratePathTxt,loadBtn,saveBtn);
		
		//Adding The fourth Line the Vbox
		Label userIdLbl=new Label("UserId: ");
		TextField userIdTxt=new TextField();
		//Here we shall add the list view after
		
		//Adding the rating Buttons and setting them to the same toggle group
		ToggleGroup ratingTgrp=new ToggleGroup();
		RadioButton rating0Btn=new RadioButton("0");
		rating0Btn.setToggleGroup(ratingTgrp);
		
		RadioButton rating1Btn=new RadioButton("1");
		rating1Btn.setToggleGroup(ratingTgrp);
		
		RadioButton rating2Btn=new RadioButton("2");
		rating2Btn.setToggleGroup(ratingTgrp);
		
		RadioButton rating3Btn=new RadioButton("3");
		rating3Btn.setToggleGroup(ratingTgrp);
		
		RadioButton rating4Btn=new RadioButton("4");
		rating4Btn.setToggleGroup(ratingTgrp);
		
		RadioButton rating5Btn=new RadioButton("5");
		rating5Btn.setToggleGroup(ratingTgrp);
		
		Button addRating=new Button("Add rating");
		// what is this?
		fourthLine.getChildren().addAll(userIdLbl,userIdTxt,rating0Btn,rating1Btn,rating2Btn,rating3Btn,rating4Btn,rating5Btn,addRating);
		//HBox.setMargin(rating0, new Insets(5,5,0,5));
		HBox.setMargin(movie, new Insets(5,5,0,5));
		HBox.setMargin(book, new Insets(5,5,0,5));
		HBox.setMargin(Item, new Insets(3,0,0,0));
		HBox.setMargin(ratings, new Insets(3,0,0,0));
		HBox.setMargin(userIdLbl, new Insets(3,0,0,0));
		
		//Here we shall add the list view after
		
		//adding The last line of the Gui
		ToggleGroup recommendOptnGrp=new ToggleGroup();
		RadioButton popularBtn=new RadioButton("Popular");
		
		popularBtn.setToggleGroup(recommendOptnGrp);
		RadioButton personalBtn=new RadioButton("Personal");
		personalBtn.setToggleGroup(recommendOptnGrp);
		
		Label genreLbl=new Label("Genre");
		TextField genreTxt=new TextField();
		Button goBtn=new Button("Go!");
		
		HBox.setMargin(genreLbl, new Insets(0,0,0,78));
		fifthLine.getChildren().addAll(popularBtn,personalBtn,genreLbl,genreTxt,goBtn);
		
		container.getChildren().addAll(thirdLine,fourthLine,fifthLine);
		root.getChildren().add(container);
		//container.getChildren().addAll(book,movie);
		
		stage.setScene(scene);       
		stage.show();
	
	}
	public  static  void  main(String[]  args)  {                                        
		Application.launch(args);              
	}

}
