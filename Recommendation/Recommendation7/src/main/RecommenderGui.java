/**
 * 
 */
package main;
import javafx.application.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 * @author Kevin Armstrong Rwigamba
 *
 */
public class RecommenderGui extends Application {
	

	@Override
	public void start(Stage stage) {
		
		Group root=new Group();
		Scene  scene  =  new  Scene(root,  400,  300);        
		scene.setFill(Color.WHITESMOKE);
		
		//Set the title for the application
		stage.setTitle("Recommender");
		HBox firstLine=new HBox();
		
		Label Item=new Label("Item");
		firstLine.getChildren().add(Item);
		root.getChildren().add(firstLine);
		
		stage.setScene(scene);       
		stage.show();
	
	}
	public  static  void  main(String[]  args)  {                                        
		Application.launch(args);              
	}

}
