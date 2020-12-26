//Final Project - Rumble Royale
//Umer Ahmad
//January 5, 2018
//This program is a two person fighting game in the format of street fighter.
//Program requires Java prerequisites (JDK, java.io, JavaFx, libraries, etc.)

//Importing necessities
package rumbleRoyale;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;


import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
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

/** Character Selection Controller class that allows the player to select a character */
public class CharacterSelectionController {
	
	//Various Stage variables for global manipulation
	Stage secondaryStage;
	Stage currentStage;
	Stage previousStage;
	@FXML AnchorPane scene2;
	
	//FXML id's for image identification
	@FXML
	private ImageView reptile;
	@FXML
	private ImageView goku;
	@FXML
	private ImageView thanos;
	@FXML
	private ImageView batman;
	
	@FXML
	private ImageView unknownIcon1;
	@FXML
	private ImageView unknownIcon2; 
	
	
	//Variables for clicked iamges
	public static ImageView previouslyClickedImage;
	public static ImageView clickedImage;
	
	//Booleans to falsify or determine if something was picked
	boolean firstPlayerPicked = false;
	boolean secondPlayerPicked = false;
	
	boolean bothPicked = false;
	
	//String for player choice
	String firstPlayerCharacter = "";
	String secondPlayerCharacter = "";
	
	double volume;
	
	boolean firstVisualSet = false;
	boolean secondVisualSet = false;
	
	//All sounds, effects and music
	AudioClip gokuIntro = new AudioClip(new File("gokuintro.wav").toURI().toString());
	AudioClip reptileIntro = new AudioClip(new File("reptileintro.wav").toURI().toString());
	AudioClip thanosIntro = new AudioClip(new File("thanosintro.wav").toURI().toString());
	AudioClip batmanIntro = new AudioClip(new File("batmanintro.mp3").toURI().toString());
	static AudioClip announcerFighter = new AudioClip(new File("chooseyourfighter.mp3").toURI().toString());
	
	static String soundTrack = "selectiontheme.mp3";    
	static Media OST = new Media(new File(soundTrack).toURI().toString());
	static MediaPlayer mediaPlayer = new MediaPlayer(OST);
	

	/** Method to play music and announcer sound effect when called */
	public static void playMusic() {
		mediaPlayer.play();
		announcerFighter.play();
		
	}
	
	/** Method to retrieve variables from previous controller.
	 * @param stage A Stage variable that retrieves the previous stage to go back
	 * @param volumeLevel A double variable to determine previously set volume
	 */
	public void getVariables(Stage stage, double volumeLevel) {
		previousStage = stage;
		volume = volumeLevel;
		
		//Setting all sounds to set volume level
		mediaPlayer.setVolume(volume);
		announcerFighter.setVolume(volume);
		gokuIntro.setVolume(volume);
		reptileIntro.setVolume(volume);
		thanosIntro.setVolume(volume);
		batmanIntro.setVolume(volume);
	}
	
	
	/** Method to process button clicks based off given action.
	 * @param evt A ActionEvent variable that is referenced in FXML file for button clicks.
	 */
	public void buttonClickHandler(ActionEvent evt) {
		
			//Setting the button click to a variable, and creating an array of buttons for ease of use
			Button clickedButton = (Button)evt.getTarget();
			String buttonLabel = clickedButton.getText();

			//If the button clicked was maps, and a character was chosen, close current window, and open map selection
			if (buttonLabel.equals("MAPS")) {
				if (firstPlayerCharacter != "" && secondPlayerCharacter != "") {
					final Node source = (Node) evt.getSource();
					final Stage stage =(Stage)source.getScene().getWindow();
					currentStage = stage;
					stage.hide();
					mediaPlayer.stop();
					MapSelectionController.playMusic();
					openMapSelection();
				} else {
					announcerFighter.play();
				}
				
			}
			
			//If the button clicked was back, close current stage and stop music, and play previous stage
			if (buttonLabel.equals("BACK")){
				final Node source = (Node) evt.getSource();
				final Stage stage =(Stage)source.getScene().getWindow();
				stage.close();
				mediaPlayer.stop();
				StartController.playMusic();
				previousStage.show();
			}
					
				
			}

	
	
	
	/** Method to process image clicks.
	 * @param evt A MouseEvent variable that is referenced in FXML file for clicking on images.
	 */
	public void imageClickHandler(MouseEvent evt) throws IOException {
		
		//Setting the image click to a variable, and creating an array of buttons for ease of use
		clickedImage = (ImageView)evt.getTarget();

		
		//Setting variables for easy effect manipulation
		DropShadow borderFirstPlayer = new DropShadow( 20, Color.RED );
		DropShadow borderSecondPlayer = new DropShadow( 20, Color.BLUE );
		DropShadow borderSameCharacter = new DropShadow( 20, Color.PURPLE);

		//Opening a file path to identify an image
		InputStream filePath = Files.newInputStream(Paths.get("res/images/sprites/reptile/reptilepicked.png"));
		Image reptileStance = new Image(filePath);
		filePath.close();
		
		InputStream filePath2 = Files.newInputStream(Paths.get("res/images/sprites/goku/gokupicked.png"));
		Image gokuStance = new Image(filePath2);
		filePath2.close();
		
		InputStream filePath3 = Files.newInputStream(Paths.get("res/images/sprites/thanos/thanospicked.png"));
		Image thanosStance = new Image(filePath3);
		filePath2.close();
		
		
		InputStream filePath4 = Files.newInputStream(Paths.get("res/images/sprites/batman/batmanpicked.png"));
		Image batmanStance = new Image(filePath4);
		filePath2.close();
		
		//If first player picked or second player picked was false, based off the if statement highlight either previously clicked and determine second or first player
		if (firstPlayerPicked == false || secondPlayerPicked == false) {
			if (secondPlayerPicked == false && firstPlayerPicked == true) {
				clickedImage.setOpacity(0.6);
				previouslyClickedImage.setOpacity(1);
				clickedImage.setEffect(borderSecondPlayer);
				secondPlayerCharacter = clickedImage.getId();
				secondPlayerPicked = true;
			}
				
			if (firstPlayerPicked == false) {
				clickedImage.setOpacity(0.6);
				clickedImage.setEffect(borderFirstPlayer);
				previouslyClickedImage = clickedImage;
				firstPlayerCharacter = clickedImage.getId();
				firstPlayerPicked = true;
				
			}
			
		}
	

		//Various If statements to determine the picture to be displayed, and highlighting of icons if both players picked same character
		if (firstPlayerCharacter != "" && firstPlayerCharacter == secondPlayerCharacter && bothPicked == false) {
				clickedImage.setOpacity(0.6);
				clickedImage.setEffect(borderSameCharacter);
				bothPicked = true;
			}
		
		
		if (firstVisualSet == false) {
			if (firstPlayerCharacter.equals("reptile")) {
				unknownIcon1.setImage(reptileStance);
				reptileIntro.play();
				firstVisualSet = true;
			}
			
			if (firstPlayerCharacter.equals("goku")) {
				unknownIcon1.setImage(gokuStance);
				gokuIntro.play();
				firstVisualSet = true;
			}
			
			if (firstPlayerCharacter.equals("thanos")) {
				unknownIcon1.setImage(thanosStance);
				thanosIntro.play();
				firstVisualSet = true;
			}
			
			if (firstPlayerCharacter.equals("batman")) {
				unknownIcon1.setImage(batmanStance);
				batmanIntro.play();
				firstVisualSet = true;
			}
			
		}
		
		if (secondVisualSet == false) {
			if (secondPlayerCharacter.equals("reptile")) {
				unknownIcon2.setImage(reptileStance);
				reptileIntro.play();
				secondVisualSet = true;
			}
			
			
			if (secondPlayerCharacter.equals("goku")) {
				unknownIcon2.setImage(gokuStance);
				gokuIntro.play();
				secondVisualSet = true;
			}
	
			if (secondPlayerCharacter.equals("thanos")) {
				unknownIcon2.setImage(thanosStance);
				thanosIntro.play();
				secondVisualSet = true;
			}
			
			if (secondPlayerCharacter.equals("batman")) {
				unknownIcon2.setImage(batmanStance);
				batmanIntro.play();
				secondVisualSet = true;
			}
		}
		
	}
	
	/** Method to process key clicks.
	 * @param evt A KeyEvent variable that is referenced in FXML file for all key presses.
	 */
	public void keyPressHandler(KeyEvent evt) throws IOException {
		
		  //Setting the key press into a variable
		  String keyPress = evt.getCode().toString();
		  
		  //File path for displaying an unknown character icon
		  InputStream filePath5 = Files.newInputStream(Paths.get("res/images/unknowncharacter.png"));
		  Image unknownIconImage = new Image(filePath5);
		  filePath5.close();
			
		  //Variable for drop shadow effect, to display it simulating the removal of a second character
		  DropShadow borderFirstPlayer = new DropShadow( 20, Color.RED );
		  
		  //If key press was escape, and dependent on certain conditions remove the highlight and character selection of either first or second character
		  if (keyPress == "ESCAPE") {
			  if (firstPlayerPicked == true && secondPlayerPicked == false) {
				  previouslyClickedImage.setOpacity(1);
				  previouslyClickedImage.setEffect(null);
				  firstPlayerPicked = false;
				  unknownIcon1.setImage(unknownIconImage);
				  firstVisualSet = false;
				  firstPlayerCharacter = "";
			  }
			  
			  if (firstPlayerPicked == true && secondPlayerPicked == true) {
				  clickedImage.setOpacity(1);
				  clickedImage.setEffect(null);
				  previouslyClickedImage.setOpacity(0.6);
				  previouslyClickedImage.setEffect(borderFirstPlayer);
				  bothPicked = false;
				  secondPlayerPicked = false;
				  unknownIcon2.setImage(unknownIconImage);
				  secondVisualSet = false;
				  secondPlayerCharacter = "";
			  }
		  }
		  
		  if (unknownIcon2.getImage() == unknownIconImage && unknownIcon1.getImage() == unknownIconImage) {
			  clickedImage.setOpacity(1);
			  clickedImage.setEffect(null);
		  }
			  
} 
	
	/** Method to open map selection */
	private void openMapSelection() {
		try {
			//Create a new scene under the MapSelection.fxml guidance, and apply application.css
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MapSelection.fxml"));
			
			Pane mapSelectionRoot = (Pane)loader.load();
			Scene mapSelectionScene = new Scene(mapSelectionRoot,1024,768);
			mapSelectionScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			MapSelectionController currentController = (loader.getController());
			currentController.getVariables(currentStage, firstPlayerCharacter, secondPlayerCharacter, volume, previousStage);
			
			//Initialize stage and show 
			Stage mapSelectionStage = new Stage();
			mapSelectionStage.setResizable(false);
			mapSelectionStage.setScene(mapSelectionScene);
			mapSelectionStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
