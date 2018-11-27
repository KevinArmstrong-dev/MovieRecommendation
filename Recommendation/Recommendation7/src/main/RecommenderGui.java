/**
 * 
 */
package main;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.*;
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
 *
 */
public class RecommenderGui extends Application {
	

	@Override
	public void start(Stage stage) {
		
		Group root=new Group();
		Scene  scene  =  new  Scene(root,  500,  300);        
		scene.setFill(Color.WHITESMOKE);
		
		//Set the title for the application
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
		TextField path =new TextField("/data/..");
		TextField ratePath=new TextField("/data/..");
		Label ratings=new Label("Ratings");
		
		//Load and Save Button
		Button load=new Button("Load");
		Button save=new Button("Save");
		
		container.getChildren().addAll(book,movie);
		//Adding the Third Line to the VBox
		Label Item=new Label("Item");
		thirdLine.getChildren().addAll(Item,path,ratings,ratePath,load,save);
		
		//Adding The fourth Line the Vbox
		Label userIdLbl=new Label("UserId");
		TextField userIdTxt=new TextField();
		//Here we shall add the list view after
		RadioButton rating0=new RadioButton("0");
		fourthLine.getChildren().addAll(userIdLbl,userIdTxt,rating0);
		//Here we shall add the list view after
		
		
		
		container.getChildren().addAll(thirdLine,fourthLine);
		root.getChildren().add(container);
		
		stage.setScene(scene);       
		stage.show();
	
	}
	public  static  void  main(String[]  args)  {                                        
		Application.launch(args);              
	}

}
