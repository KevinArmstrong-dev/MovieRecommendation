/**
 * 
 */
package main;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.Insets;
import javafx.application.*;
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
/**
 * @author Kevin Armstrong Rwigamba
 *@author Alexander Arella Girardot
 */
public class RecommenderGui extends Application {
	

	@Override
	public void start(Stage stage) {
		
		Group root=new Group();
		Scene  scene  =  new  Scene(root,  500,  300);        
		scene.setFill(Color.WHITESMOKE);
		
		stage.setTitle("Recommender");
		VBox container=new VBox();
		HBox thirdLine=new HBox();
		HBox fourthLine=new HBox();

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
		thirdLine.getChildren().addAll(Item,path,ratings,ratePath,load,save);
		
		//Adding The fourth Line the Vbox
		Label userIdLbl=new Label("UserId: ");
		TextField userIdTxt=new TextField();
		//Here we shall add the list view after
		RadioButton rating0=new RadioButton("0"); // what is this?
		fourthLine.getChildren().addAll(userIdLbl,userIdTxt,rating0);
		HBox.setMargin(rating0, new Insets(5,5,0,5));
		HBox.setMargin(movie, new Insets(5,5,0,5));
		HBox.setMargin(book, new Insets(5,5,0,5));
		HBox.setMargin(Item, new Insets(3,0,0,0));
		HBox.setMargin(ratings, new Insets(3,0,0,0));
		HBox.setMargin(userIdLbl, new Insets(3,0,0,0));
		//Here we shall add the list view after
		
		
		fourthLine.getChildren().addAll(book, movie);
		container.getChildren().addAll(thirdLine,fourthLine);
		root.getChildren().add(container);
		//container.getChildren().addAll(book,movie);
		
		stage.setScene(scene);       
		stage.show();
	
	}
	public  static  void  main(String[]  args)  {                                        
		Application.launch(args);              
	}

}
