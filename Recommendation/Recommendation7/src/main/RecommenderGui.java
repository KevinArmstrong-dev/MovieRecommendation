package main;
import java.io.*;
import javafx.geometry.Insets;
import javafx.application.*;
import javafx.collections.*;
import javafx.scene.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
/**
 * @author Kevin Armstrong Rwigamba
 *@author Alexander Arella Girardot
 */
public class RecommenderGui extends Application {
	

	@Override
	public void start(Stage stage) {
		stage.setWidth(800);
		Group root=new Group();
		Scene  scene  =  new  Scene(root,  500,  300);        
		scene.setFill(Color.WHITESMOKE);
		
		stage.setTitle("Recommender");
		VBox container=new VBox();
		HBox firstLine=new HBox();
		HBox secondLine=new HBox();

		//Select list
		ComboBox<String> cbItems = new ComboBox<String>(FXCollections.observableArrayList("Book", "Movie"));
		//Radio buttons For Movie or Selection
		ToggleGroup itemType=new ToggleGroup();
		RadioButton book=new RadioButton("Book");
		book.setToggleGroup(itemType);
		RadioButton movie=new RadioButton("Movie");
		movie.setToggleGroup(itemType);
		
		//file path
		TextField path =new TextField("datafiles\\");
		TextField ratePath=new TextField("datafiles\\");
		Label ratings=new Label("Ratings: ");
		
		//Load and Save Button
		Button load=new Button("Load");
		Button save=new Button("Save");
		
		//Adding the Third Line to the VBox
		Label Item=new Label("Item: ");
<<<<<<< HEAD
		firstLine.getChildren().addAll(Item, book, movie, path,ratings,ratePath,load,save);
=======
		firstLine.getChildren().addAll(Item,path,ratings,ratePath,load,save);
>>>>>>> 110f25634323d6fb4b5289a57edee374911ae59c
		
		//Adding The fourth Line the VBox
		Label userIdLbl=new Label("UserId: ");
		TextField userIdTxt=new TextField();
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
<<<<<<< HEAD
		secondLine.getChildren().addAll(userIdLbl,userIdTxt, cbItems, rating0, rating1, rating2, rating3, rating4, rating5);
=======
		secondLine.getChildren().addAll(userIdLbl,userIdTxt,rating0, rating1, rating2, rating3, rating4, rating5);
>>>>>>> 110f25634323d6fb4b5289a57edee374911ae59c
		HBox.setMargin(rating0, new Insets(5,5,0,5));
		HBox.setMargin(rating1, new Insets(5,5,0,5));
		HBox.setMargin(rating2, new Insets(5,5,0,5));
		HBox.setMargin(rating3, new Insets(5,5,0,5));
		HBox.setMargin(rating4, new Insets(5,5,0,5));
		HBox.setMargin(rating5, new Insets(5,5,0,5));
<<<<<<< HEAD
		HBox.setMargin(movie, new Insets(3,5,0,5));
		HBox.setMargin(book, new Insets(3,5,0,5));
=======
		HBox.setMargin(movie, new Insets(5,5,0,5));
		HBox.setMargin(book, new Insets(5,5,0,5));
>>>>>>> 110f25634323d6fb4b5289a57edee374911ae59c
		HBox.setMargin(Item, new Insets(3,0,0,0));
		HBox.setMargin(ratings, new Insets(3,0,0,0));
		HBox.setMargin(userIdLbl, new Insets(3,0,0,0));
		//Here we shall add the list view after
<<<<<<< HEAD
=======
		
		
		secondLine.getChildren().addAll(book, movie);
>>>>>>> 110f25634323d6fb4b5289a57edee374911ae59c
		container.getChildren().addAll(firstLine,secondLine);
		root.getChildren().add(container);
		//container.getChildren().addAll(book,movie);
		stage.setScene(scene);       
		stage.show();
	
	}
	public  static  void  main(String[]  args)  {                                        
		Application.launch(args);              
	}

}