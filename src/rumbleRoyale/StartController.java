//Final Project - Rumble Royale
//Umer Ahmad
//January 5, 2018
//This program is a two person fighting game in the format of street fighter.
//Program requires Java prerequisites (JDK, java.io, JavaFx, libraries, etc.)

//Importing necessities
package rumbleRoyale;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.Parent;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.scene.Scene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;


/** StartController class to store all methods and constructor - first to be initialized when loading game. */
public class StartController {
	
	//Stage variable that allows us to refer create a new stage(instructions).
	Stage secondaryStage;
	Stage currentStage;
	private GameMenu gameMenu;
	private InstructionsMenu instructionsMenu;
	
    double volumeLevel = 1.0;
	
    @FXML AnchorPane scene1;
	@FXML Pane screen;
	@FXML ImageView mainLogo;
	@FXML ImageView anyKey;
	
	
	static String soundTrack = "menutheme.mp3";    
	static Media OST = new Media(new File(soundTrack).toURI().toString());
	static MediaPlayer mediaPlayer = new MediaPlayer(OST);
	

	static AudioClip select = new AudioClip(new File("menuselect.mp3").toURI().toString());
	
	boolean ready = false;

	static boolean singlePlayer = false;
	static boolean multiPlayer = false;
	
	public StartController() {
		ready = false;
		playMusic();
	}
	
	public static void playMusic() {
		mediaPlayer.play();
	}
	
	public void keyPressHandler (KeyEvent evt) throws IOException {

		if (ready == false) {
			anyKey.setImage(null);
			playMusic();
			mainLogo.setLayoutY(20);
			InputStream filePath3 = Files.newInputStream(Paths.get("res/images/gokumainmenu.gif"));
			Image img3 = new Image(filePath3);
			filePath3.close();
			
	
			ImageView imgView3 = new ImageView(img3);
			imgView3.setTranslateX(600);
			imgView3.setTranslateY(120);
			
			gameMenu = new GameMenu();
			
			screen.getChildren().addAll(imgView3,gameMenu);
			ready = true;
		}
		
	}
	

	
	private class GameMenu extends Parent {
		public GameMenu() {
			
			VBox initialMenu = new VBox(10);
			VBox settingsMenu = new VBox(10);
			VBox playMenu = new VBox(10);
			VBox videoMenu = new VBox(10);
			VBox audioMenu = new VBox(10);
			
			initialMenu.setTranslateX(50);
			initialMenu.setTranslateY(275);
			
			settingsMenu.setTranslateX(50);
			settingsMenu.setTranslateY(275);
			
			playMenu.setTranslateX(50);
			playMenu.setTranslateY(275);
			
			videoMenu.setTranslateX(50);
			videoMenu.setTranslateY(275);
			
			audioMenu.setTranslateX(50);
			audioMenu.setTranslateY(275);
		
			int offset = 400;
			
			playMenu.setTranslateX(offset);
			settingsMenu.setTranslateX(offset);
			
			
			MenuButtons play = new MenuButtons("PLAY");
			play.setOnMouseClicked(event -> {
				getChildren().add(playMenu);
				TranslateTransition transition = new TranslateTransition(Duration.seconds(0.25), initialMenu);
				transition.setToX(initialMenu.getTranslateX() - offset);
				
				TranslateTransition transition2 = new TranslateTransition(Duration.seconds(0.5), playMenu);
				transition2.setToX(initialMenu.getTranslateX());
				
				transition.play();
				transition2.play();
				
				transition.setOnFinished(evt -> {
					getChildren().remove(initialMenu);
				});
			});
			
			
			MenuButtons training = new MenuButtons("TRAINING");
			training.setOnMouseClicked(event -> {
			    ((Stage)training.getScene().getWindow()).hide();
			    currentStage = ((Stage)training.getScene().getWindow());
			    CharacterSelectionController.playMusic();
			    singlePlayer = true;
			    multiPlayer = false;
				openCharacterSelection();
			});
			
			
			MenuButtons multiplayer = new MenuButtons("MULTIPLAYER");
			multiplayer.setOnMouseClicked(event -> {
			    ((Stage)multiplayer.getScene().getWindow()).hide();
			    currentStage = ((Stage)multiplayer.getScene().getWindow());
			    CharacterSelectionController.playMusic();
			    multiPlayer = true;
			    singlePlayer = false;
				openCharacterSelection();
			});
			
			
			MenuButtons back2 = new MenuButtons("BACK");
			back2.setOnMouseClicked(event -> {
				getChildren().add(initialMenu);
				TranslateTransition transition = new TranslateTransition(Duration.seconds(0.25), playMenu);
				transition.setToX(playMenu.getTranslateX() + offset);
				
				TranslateTransition transition2 = new TranslateTransition(Duration.seconds(0.5), initialMenu);
				transition2.setToX(playMenu.getTranslateX());
				
				transition.play();
				transition2.play();
				
				transition.setOnFinished(evt -> {
					getChildren().remove(playMenu);
				});
			});
			
			
			MenuButtons settings = new MenuButtons("SETTINGS");
			settings.setOnMouseClicked(event -> {
				getChildren().add(settingsMenu);
				TranslateTransition transition = new TranslateTransition(Duration.seconds(0.25), initialMenu);
				transition.setToX(initialMenu.getTranslateX() - offset);
				
				TranslateTransition transition2 = new TranslateTransition(Duration.seconds(0.5), settingsMenu);
				transition2.setToX(initialMenu.getTranslateX());
				
				transition.play();
				transition2.play();
				
				transition.setOnFinished(evt -> {
					getChildren().remove(initialMenu);
				});
				
			});
			
			
			MenuButtons audio = new MenuButtons("AUDIO");
			audio.setOnMouseClicked(event -> {
				getChildren().add(audioMenu);
				TranslateTransition transition = new TranslateTransition(Duration.seconds(0.25), settingsMenu);
				transition.setToX(settingsMenu.getTranslateX() - offset);
				
				TranslateTransition transition2 = new TranslateTransition(Duration.seconds(0.5), audioMenu);
				transition2.setToX(settingsMenu.getTranslateX());
				
				transition.play();
				transition2.play();
				
				transition.setOnFinished(evt -> {
					getChildren().remove(settingsMenu);
				});
			});
			
			Slider musicSlider = new Slider();
			musicSlider.setValue(mediaPlayer.getVolume() * 100);
			musicSlider.valueProperty().addListener(new InvalidationListener() {
				
				@Override
				public void invalidated(Observable observable) {
					mediaPlayer.setVolume(musicSlider.getValue() / 100);
					select.setVolume(musicSlider.getValue() / 100);
					volumeLevel = musicSlider.getValue() / 100;
				}
			});
			musicSlider.setShowTickLabels(true);
			musicSlider.setShowTickMarks(true);
			
			MenuButtons back5 = new MenuButtons("BACK");
			back5.setOnMouseClicked(event -> {
				getChildren().add(settingsMenu);
				TranslateTransition transition = new TranslateTransition(Duration.seconds(0.25),audioMenu);
				transition.setToX(audioMenu.getTranslateX() + offset);
				
				TranslateTransition transition2 = new TranslateTransition(Duration.seconds(0.5), settingsMenu);
				transition2.setToX(audioMenu.getTranslateX());
				
				transition.play();
				transition2.play();
				
				transition.setOnFinished(evt -> {
					getChildren().remove(audioMenu);
				});
			});
			
			
			
			
			MenuButtons video = new MenuButtons("VIDEO");
			video.setOnMouseClicked(event -> {
				getChildren().add(videoMenu);
				TranslateTransition transition = new TranslateTransition(Duration.seconds(0.25), settingsMenu);
				transition.setToX(settingsMenu.getTranslateX() - offset);
				
				TranslateTransition transition2 = new TranslateTransition(Duration.seconds(0.5), videoMenu);
				transition2.setToX(settingsMenu.getTranslateX());
				
				transition.play();
				transition2.play();
				
				transition.setOnFinished(evt -> {
					getChildren().remove(settingsMenu);
				});
			});
			
			
			MenuButtons resolution1 = new MenuButtons("2560x1440");
			resolution1.setOnMouseClicked(event -> {
				((Stage)resolution1.getScene().getWindow()).setWidth(2560);
				((Stage)resolution1.getScene().getWindow()).setHeight(1440);
				mediaPlayer.stop();
				((Stage)resolution1.getScene().getWindow()).close();
				 Platform.runLater( () -> new Main().start( new Stage() ) );
			});
			
			MenuButtons resolution2 = new MenuButtons("1920x1080");
			resolution2.setOnMouseClicked(event -> {
				((Stage)resolution2.getScene().getWindow()).setWidth(2560);
				((Stage)resolution2.getScene().getWindow()).setHeight(1440);
				mediaPlayer.stop();
				((Stage)resolution2.getScene().getWindow()).close();
				 Platform.runLater( () -> new Main().start( new Stage() ) );
			});
			
			MenuButtons resolution3 = new MenuButtons("1600x900");
			resolution3.setOnMouseClicked(event -> {
				((Stage)resolution3.getScene().getWindow()).setWidth(2560);
				((Stage)resolution3.getScene().getWindow()).setHeight(1440);
				mediaPlayer.stop();
				((Stage)resolution3.getScene().getWindow()).close();
				 Platform.runLater( () -> new Main().start( new Stage() ) );
			});
			
			MenuButtons resolution4 = new MenuButtons("1366x768");
			resolution4.setOnMouseClicked(event -> {
				((Stage)resolution4.getScene().getWindow()).setWidth(2560);
				((Stage)resolution4.getScene().getWindow()).setHeight(1440);
				mediaPlayer.stop();
				((Stage)resolution4.getScene().getWindow()).close();
				 Platform.runLater( () -> new Main().start( new Stage() ) );
			});
			
			MenuButtons resolution5 = new MenuButtons("1280x720");
			resolution5.setOnMouseClicked(event -> {
				((Stage)resolution5.getScene().getWindow()).setWidth(2560);
				((Stage)resolution5.getScene().getWindow()).setHeight(1440);
				mediaPlayer.stop();
				((Stage)resolution5.getScene().getWindow()).close();
				 Platform.runLater( () -> new Main().start( new Stage() ) );
			});
			
			MenuButtons resolution6 = new MenuButtons("1024x768");
			resolution6.setOnMouseClicked(event -> {
				((Stage)resolution6.getScene().getWindow()).setWidth(2560);
				((Stage)resolution6.getScene().getWindow()).setHeight(1440);
				mediaPlayer.stop();
				((Stage)resolution6.getScene().getWindow()).close();
				 Platform.runLater( () -> new Main().start( new Stage() ) );
			});
			
			
			MenuButtons fullscreen = new MenuButtons("FULLSCREEN");
			fullscreen.setOnMouseClicked(event -> {
				((Stage)fullscreen.getScene().getWindow()).setFullScreen(true);
			});
			
			
			
			MenuButtons back3 = new MenuButtons("BACK");
			back3.setOnMouseClicked(event -> {
				getChildren().add(settingsMenu);
				TranslateTransition transition = new TranslateTransition(Duration.seconds(0.25), videoMenu);
				transition.setToX(videoMenu.getTranslateX() + offset);
				
				TranslateTransition transition2 = new TranslateTransition(Duration.seconds(0.5), settingsMenu);
				transition2.setToX(videoMenu.getTranslateX());
				
				transition.play();
				transition2.play();
				
				transition.setOnFinished(evt -> {
					getChildren().remove(videoMenu);
				});
			});
			
			
			
			MenuButtons howToPlay = new MenuButtons("HOW TO PLAY");
			howToPlay.setOnMouseClicked(event -> {
				openInstructions();
			});
			
			MenuButtons back4 = new MenuButtons("BACK");
			back4.setOnMouseClicked(event -> {
				getChildren().add(initialMenu);
				TranslateTransition transition = new TranslateTransition(Duration.seconds(0.25), settingsMenu);
				transition.setToX(settingsMenu.getTranslateX() + offset);
				
				TranslateTransition transition2 = new TranslateTransition(Duration.seconds(0.5), initialMenu);
				transition2.setToX(settingsMenu.getTranslateX());
				
				transition.play();
				transition2.play();
				
				transition.setOnFinished(evt -> {
					getChildren().remove(settingsMenu);
				});
			});
			
			
			
			MenuButtons quit = new MenuButtons("QUIT");
			quit.setOnMouseClicked(event -> {
				System.exit(0);
			});
			
			
			initialMenu.getChildren().addAll(play,settings,quit);
			settingsMenu.getChildren().addAll(video,audio,howToPlay,back4);
			videoMenu.getChildren().addAll(resolution1,resolution2,resolution3,resolution4,resolution5,resolution6,fullscreen,back3);
			playMenu.getChildren().addAll(training,multiplayer,back2);
			audioMenu.getChildren().addAll(musicSlider,back5);

			
			getChildren().add(initialMenu);
		}
		
	}
	
	private static class MenuButtons extends StackPane {
		private Text text;
		
		public MenuButtons(String name) {
			
			int rectangleWidth = 250;
			int rectangleHeight = 30;
			
			text = new Text(name);
			if (name != "EXIT") {
				text.setFont(text.getFont().font(20));
				text.setFill(Color.WHITE);
				text.setTranslateX(5);
			} else {
				text.setFont(text.getFont().font(40));
				text.setFill(Color.WHITE);
				rectangleWidth = 90;
				rectangleHeight = 60;
			}
			
			Rectangle highLight = new Rectangle(rectangleWidth,rectangleHeight);
			highLight.setOpacity(0.6);
			highLight.setFill(Color.RED);
			highLight.setEffect(new GaussianBlur(3.5));
			
			
			setAlignment(Pos.CENTER_LEFT);

			
			setRotate(-0.5);
			getChildren().addAll(highLight, text);
			
			setOnMouseEntered(event -> {
				highLight.setTranslateX(10);
				text.setTranslateX(15);
				highLight.setFill(Color.WHITE);
				text.setFill(Color.BLACK);
			});
			
			setOnMouseExited(event -> {
				highLight.setTranslateX(0);
				text.setTranslateX(5);
				highLight.setFill(Color.RED);
				text.setFill(Color.WHITE);
			});
			
			DropShadow drop = new DropShadow(50,Color.WHITE);
			drop.setInput(new Glow());
			
			setOnMousePressed(event -> {
				setEffect(drop);
				select.play();
			});
			
			setOnMouseReleased(event -> setEffect(null));
		}
	}
	
	
	//Method to open instructions stage
		private void openInstructions() {
			try {
				
				//Loading the pop up that was created
				Pane instructions = (Pane)FXMLLoader.load(getClass().getResource("Instructions.fxml"));

				//Creating a new scene
				Scene instructionsScene = new Scene(instructions,800,600);

				instructionsScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				

				//Creating new stage to utilize scene
				secondaryStage = new Stage();
				secondaryStage.setScene(instructionsScene);
				secondaryStage.setResizable(false);
				instructionsMenu = new InstructionsMenu();
				
				secondaryStage.setResizable(false);
				
				instructions.getChildren().addAll(instructionsMenu);
				secondaryStage.showAndWait();
				} catch(Exception e) {
					e.printStackTrace();
				}
		}
	
	
		
		private class InstructionsMenu extends Parent {
			public InstructionsMenu() {
				MenuButtons exit = new MenuButtons("EXIT");
				exit.setOnMouseClicked(event -> {
					((Stage)exit.getScene().getWindow()).close();
					getChildren().removeAll();
				});
				getChildren().add(exit);
			}
		}
	
		
		
		//Method to act as gateway to change to GameController
		private void openCharacterSelection() {
			try {
				//Create a new scene under the end.fxml guidance, and apply rankings.css
				FXMLLoader loader = new FXMLLoader(getClass().getResource("CharacterSelection.fxml"));
				Pane characterSelectionRoot = (Pane)loader.load();
				Scene characterSelectionScene = new Scene(characterSelectionRoot,1024,768);
				characterSelectionScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				
				CharacterSelectionController currentController = (loader.getController());
				currentController.getVariables(currentStage, volumeLevel);
				
				//Initialize stage and show 
				Stage characterSelectionStage = new Stage();
				characterSelectionStage.setResizable(false);
				characterSelectionStage.setScene(characterSelectionScene);
				mediaPlayer.stop();
				characterSelectionStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	
	
}
