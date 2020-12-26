//Final Project - Rumble Royale
//Umer Ahmad
//January 5, 2018
//This program is a two person fighting game in the format of street fighter.
//Program requires Java prerequisites (JDK, java.io, JavaFx, libraries, etc.)

//Importing necessities
package rumbleRoyale;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

/** StartController class to store all methods and constructor - first to be initialized when loading game. */
public class EndScreenController {
	

	Stage menu;
	double volume;
	
	String playerOne = "";
	String playerTwo = "";
	
	String winner = "";
	
	ImageView player1Portrait = CharacterSelectionController.previouslyClickedImage;
	ImageView player2Portrait = CharacterSelectionController.clickedImage;
	
	
	@FXML ImageView portraitPlaceholder;
	@FXML ImageView textPlaceholder;
	
	public void getVariables(double volumeLevel, Stage firstMenu, String firstPlayer, String secondPlayer, String victor) throws IOException {
		menu = firstMenu;
		volume = volumeLevel;
		playerOne = firstPlayer;
		playerTwo = secondPlayer;
		winner = victor;
		
		
		
		
		if (winner.equals("player1")) {
			InputStream filePath = Files.newInputStream(Paths.get("res/images/player1wins.gif"));
			Image img = new Image(filePath);
			filePath.close();
			portraitPlaceholder.setImage(player1Portrait.getImage());
			textPlaceholder.setImage(img);
		}
		
		if (winner.equals("player2")) {
			InputStream filePath = Files.newInputStream(Paths.get("res/images/player2wins.gif"));
			Image img = new Image(filePath);
			filePath.close();
			portraitPlaceholder.setImage(player2Portrait.getImage());
			textPlaceholder.setImage(img);
		}
		
	}
	
	
	/** Method to process button clicks based off given action.
	 * @param evt A ActionEvent variable that is referenced in FXML file for button clicks.
	 */
	public void buttonClickHandler(ActionEvent evt) {
		
			//Setting the button click to a variable, and creating an array of buttons for ease of use
			Button clickedButton = (Button)evt.getTarget();
			String buttonLabel = clickedButton.getText();

			//If the button clicked was main menu, close current window, and call openStart method
			if (buttonLabel.equals("MAIN MENU")) {
				final Node source = (Node) evt.getSource();
				final Stage stage =(Stage)source.getScene().getWindow();
				stage.close();
				CharacterSelectionController.mediaPlayer.stop();
				StartController.mediaPlayer.play();
				menu.show();
			}
			

		}

	
	
	
}
