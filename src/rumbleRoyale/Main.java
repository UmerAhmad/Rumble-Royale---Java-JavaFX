//Final Project - Rumble Royale
//Umer Ahmad
//January 5, 2018
//This program filePath a two person fighting game in the format of street fighter.
//Program requires Java prerequisites (JDK, java.io, JavaFx, libraries, etc.)

//Importing necessities
package rumbleRoyale;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/** Main Class and Method to start primary stage and show it*/
public class Main extends Application {
	

	@Override
	public void start(Stage primaryStage) {
		try {
			//Initialize, and load scene/primary stage, apply css and show stage
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Start.fxml"));
			Pane root = (Pane)loader.load();
			
			
			Scene scene = new Scene(root,1024,768);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			root.requestFocus();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	

		
		
	/** Method to launch.
	 * @param args Launch the array string.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
