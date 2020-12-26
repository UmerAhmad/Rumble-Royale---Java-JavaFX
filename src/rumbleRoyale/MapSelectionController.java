//Final Project - Rumble Royale
//Umer Ahmad
//January 5, 2018
//This program is a two person fighting game in the format of street fighter.
//Program requires Java prerequisites (JDK, java.io, JavaFx, libraries, etc.)

//Importing necessities
package rumbleRoyale;
import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

/** StartController class to store all methods and constructor - first to be initialized when loading game. */
public class MapSelectionController {
	
	//Stage variable that allows us to refer create a new stage(instructions).
	Stage previousStage;
	Stage currentStage;
	Stage firstStage;
	
	@FXML AnchorPane scene3;
	
	String mapChoice = "";
	String playerOne;
	String playerTwo;
	
	double volume;
	
	ImageView previouslyClicked;
	AudioClip select = new AudioClip(new File("mapselect.mp3").toURI().toString());
	AudioClip beginFight = new AudioClip(new File("fightbegin.mp3").toURI().toString());
	boolean mapPicked = false;
	
	@FXML ImageView placeholder;
	
	ImageView clickedImage;
	
	static String soundTrack = "selectiontheme.mp3";    
	static Media OST = new Media(new File(soundTrack).toURI().toString());
	static MediaPlayer mediaPlayer = new MediaPlayer(OST);
	
	public static void playMusic() {
		mediaPlayer.play();
	}

	//Method to retrieve queue from previous controller
	public void getVariables(Stage stage, String first, String second, double volumeLevel, Stage menuStage) {
		previousStage = stage;
		firstStage = menuStage;
		playerOne = first;
		playerTwo = second;
		volume = volumeLevel;
		
		mediaPlayer.setVolume(volume);
		select.setVolume(volume);
		beginFight.setVolume(volume);
	}
		

	/** Method to process button clicks based off given action.
	 * @param evt A ActionEvent variable that is referenced in FXML file for button clicks.
	 */
	public void buttonClickHandler(ActionEvent evt) {
		
			//Setting the button click to a variable, and creating an array of buttons for ease of use
			Button clickedButton = (Button)evt.getTarget();
			String buttonLabel = clickedButton.getText();
		
			if (buttonLabel.equals("START")) {
				final Node source = (Node) evt.getSource();
				final Stage stage =(Stage)source.getScene().getWindow();
				stage.close();
				mediaPlayer.stop();
				previousStage.close();
				playGame();
			}
		
			
			//If the button clicked was play, close current window, and call openPlayerData method
			if (buttonLabel.equals("BACK")) {
				final Node source = (Node) evt.getSource();
				final Stage stage =(Stage)source.getScene().getWindow();
				currentStage = stage;
				stage.hide();
				mediaPlayer.stop();
				CharacterSelectionController.playMusic();
				previousStage.show();
			}
		}
	
	
	
	public void imageClickHandler(MouseEvent evt) throws IOException {
		
		
		//Setting the button click to a variable, and creating an array of buttons for ease of use
		clickedImage = (ImageView)evt.getTarget();
		DropShadow borderMap = new DropShadow( 20, Color.ORANGE );
		if (mapPicked == false) {
			clickedImage.setOpacity(0.6);
			clickedImage.setEffect(borderMap);
			previouslyClicked = clickedImage;
			mapChoice = clickedImage.getId();
			mapPicked = true;
			select.play();
		} else {
			previouslyClicked.setOpacity(1);
			previouslyClicked.setEffect(null);
			clickedImage.setOpacity(0.6);
			clickedImage.setEffect(borderMap);
			mapChoice = clickedImage.getId();
			if (previouslyClicked != clickedImage) {
				select.play();
			}
			previouslyClicked = clickedImage;
		}
		
	
		
		Image preview = clickedImage.getImage();
		placeholder.setImage(preview);
		
		
	}
	
	
	private void playGame() {
		try {
			//Create a new scene under the MonkeyGame.fxml guidance, and apply css
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
			Pane gameRoot = (Pane)loader.load();
			Scene gameScene = new Scene(gameRoot,1000,725);
			gameScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());	
			

			beginFight.play();
			//Initialize stage and show 
			Stage gameStage = new Stage();
			gameStage.setResizable(false);
			gameStage.setScene(gameScene);
			GameController controller = loader.getController();
			gameStage.setScene(gameScene);
			controller.getVariables(gameStage, playerOne, playerTwo, mapChoice, volume, firstStage);
			controller.gameLoop();
			gameStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
		
	
	
}
