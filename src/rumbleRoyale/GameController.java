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
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

/** GameController class to display game, and where the main loop is*/
public class GameController {

	//Scene Variables
	Scene gameScene;
	@FXML Canvas gameCanvas;
	GraphicsContext gc;
	
	Stage currentStage;
	
	//String variables that are retreived from previous controller
	String playerOne;
	String playerTwo;
	String map;
	
	//String to manipulate background
	String backgroundImage;
	
	//Volume level for sound
	double volume;
	
	//Orientation for direction of character face
	int orientation1 = 1;
	int orientation2 = 1;
	
	//Variable for offset of first palyer
	double initialPlayerOneX = 0;
	double initialPlayerOneY = 0;
	
	double initialPlayerTwoX = 0;
	double initialPlayerTwoY = 0;
	
	
	int attackLength1 = 20;
	boolean attackReady1 = false;
	
	int attackLength2 = 20;
	boolean attackReady2 = false;
	
	
	int attackCooldown1 = 0;
	int attackCooldown2 = 0;
	
	Stage menu;
	
	//Stating collision types, either from the left or right
	String collisionType1 = "";
	String collisionType2 = "";
	
	//Strings for action that are passed into character for further processing
	String action1;
	String action2;
	
	//A boolean to determine whether the garbage collector was forced or not
	boolean memoryCollect = false;
	
	boolean singlePlayer = StartController.singlePlayer;
	boolean multiPlayer = StartController.multiPlayer;
	
	String soundTrack = null;  
	
	String hit1;
	String hurt1;

	String hit2;
	String hurt2;
	
	AudioClip playerHit1;
	AudioClip playerHurt1;
	
	AudioClip playerHit2;
	AudioClip playerHurt2;
	
	MediaPlayer mediaPlayer;
	
	boolean getHurt1 = false;
	boolean getHurt2 = false;
	
	
	String winner = "";
	
	int hurtTimer1 = 60;
	int hurtTimer2 = 60;
	
	int trueSpeed1 = 6;
	int trueSpeed2 = 6;
	
	
	
	
	/** Method to retrieve variables from previous controller.
	 * @param stage A Stage variable that retrieves the previous stage to go back
	 * @param first A String variable that determines the first player picked
	 * @param second A String variable that determines the second player picked
	 * @param mapChoice A String variable that determined the map choice
	 * @param volumeLevel A double variable that sets volume level based off player choice
	 */
	public void getVariables(Stage primaryStage, String first, String second, String mapChoice, double volumeLevel, Stage firstMenu) {
		menu = firstMenu;
		currentStage = primaryStage;
		gameScene = primaryStage.getScene();
		playerOne = first;
		playerTwo = second;
		map = mapChoice;
		volume = volumeLevel;

		
	}
	

	
	/** Main method of game, gameloop with animation typer to display objects onto canvas at 60 FPS */
	public void gameLoop() throws IOException {
	
		//Setting up background and canvas
		gc = gameCanvas.getGraphicsContext2D();
		
		//Various filepaths that are dynamic based off player choices
		InputStream filePath = null;

		
		InputStream filePath2 = null;
		InputStream filePath3 = null;
		InputStream filePath4 = null;
		InputStream filePath5 = null;
		InputStream filePath6 = null;
		InputStream filePath7 = null;
		InputStream filePath8 = null;
		InputStream filePath9 = null;
		InputStream filePath10 = null;
		InputStream filePath11 = null;
		InputStream filePath12 = null;
		InputStream filePath13 = null;
		InputStream filePath14 = Files.newInputStream(Paths.get("res/images/sprites/reptile/reptileidle.png"));
		InputStream filePath15 = Files.newInputStream(Paths.get("res/images/sprites/reptile/reptileidle.png"));
		
		//Initializing Health Bars
		HealthBar healthPlayer1 = new HealthBar(gc, gameCanvas);
		HealthBar healthPlayer2 = new HealthBar(gc, gameCanvas);
		
		EnergyBar energyPlayer1 = new EnergyBar(gc, gameCanvas);
		EnergyBar energyPlayer2 = new EnergyBar(gc, gameCanvas);
		
		//Resetting effects on clicked images so they can be used as player portraits
		CharacterSelectionController.previouslyClickedImage.setEffect(null);
		CharacterSelectionController.previouslyClickedImage.setOpacity(1);
		
		CharacterSelectionController.clickedImage.setEffect(null);
		CharacterSelectionController.clickedImage.setOpacity(1);
		
		//Player portraits created as image views through previous controller clicked images.
		ImageView player1Icon = CharacterSelectionController.previouslyClickedImage;
		ImageView player2Icon = CharacterSelectionController.clickedImage;
		
		//Setting dimensions of player icon
		player1Icon.setFitHeight(78);
		player1Icon.setFitWidth(78);
		
		player2Icon.setFitHeight(78);
		player2Icon.setFitWidth(78);
		
		
		//Switch Map to set background dependent on previously picked map
		switch (map) {
			case "map1": 
				filePath = Files.newInputStream(Paths.get("res/images/maps/map (7).gif")); 
				soundTrack = "monkeytheme.mp3";
				break;
			case "map2": 
				filePath = Files.newInputStream(Paths.get("res/images/maps/map (8).gif")); 
				soundTrack = "boattheme.mp3";
				break;
			case "map3": 
				filePath = Files.newInputStream(Paths.get("res/images/maps/map (1).gif")); 
				soundTrack = "hauntedtheme.mp3";
				break;
			case "map4": 
				filePath = Files.newInputStream(Paths.get("res/images/maps/map (2).gif")); 
				soundTrack = "streettheme.mp3";
				break;
			case "map5": 
				filePath = Files.newInputStream(Paths.get("res/images/maps/map (3).gif")); 
				soundTrack = "streettheme.mp3";
				break;
			case "map6": 
				filePath = Files.newInputStream(Paths.get("res/images/maps/map (4).gif")); 
				soundTrack = "streettheme.mp3";
				break;
			case "map7": 
				filePath = Files.newInputStream(Paths.get("res/images/maps/map (5).gif")); 
				soundTrack = "streettheme.mp3";
				break;
			case "map8": 
				filePath = Files.newInputStream(Paths.get("res/images/maps/map (6).gif")); 
				soundTrack = "hauntedtheme.mp3";
				break;
			case "map9": 
				filePath = Files.newInputStream(Paths.get("res/images/maps/map (9).gif"));
				soundTrack = "hauntedtheme.mp3";
				break;
			case "map10": 
				filePath = Files.newInputStream(Paths.get("res/images/maps/map (12).gif")); 
				soundTrack = "monkeytheme.mp3";
				break;
			case "map11": 
				filePath = Files.newInputStream(Paths.get("res/images/maps/map (11).gif"));
				soundTrack = "egyptiantheme.mp3";
				break;
			case "map12": 
				filePath = Files.newInputStream(Paths.get("res/images/maps/map (10).gif"));
				soundTrack = "monkeytheme.mp3";
				break;
		}
		
		
		//Two switch cases to set animations based off picked characters
		switch (playerOne) {
			case "reptile": 
				filePath2 = Files.newInputStream(Paths.get("res/images/sprites/reptile/reptileidle.png"));
				filePath4 = Files.newInputStream(Paths.get("res/images/sprites/reptile/reptilewalk.png"));
				filePath6 = Files.newInputStream(Paths.get("res/images/sprites/reptile/reptilejump.png"));
				filePath8 = Files.newInputStream(Paths.get("res/images/sprites/reptile/reptilepunch.png"));
				filePath10 = Files.newInputStream(Paths.get("res/images/sprites/reptile/reptilehurt.png"));
				filePath12 = Files.newInputStream(Paths.get("res/images/sprites/reptile/reptilecrouch.png"));
				initialPlayerOneX = 0;
				initialPlayerOneY = gameCanvas.getHeight() - 350 - 78;
				hit1 = "res/images/sprites/reptile/reptilehit.wav";
				hurt1 = "res/images/sprites/reptile/reptilehurt.wav";
				break;
			case "goku": 
				filePath2 = Files.newInputStream(Paths.get("res/images/sprites/goku/gokuidle.png"));
				filePath4 = Files.newInputStream(Paths.get("res/images/sprites/goku/gokuwalk.png"));
				filePath6 = Files.newInputStream(Paths.get("res/images/sprites/goku/gokujump.png"));
				filePath8 = Files.newInputStream(Paths.get("res/images/sprites/goku/gokupunch.png"));
				filePath10 = Files.newInputStream(Paths.get("res/images/sprites/goku/gokuhurt.png"));
				filePath12 = Files.newInputStream(Paths.get("res/images/sprites/goku/gokucrouch.png"));
				filePath14 = Files.newInputStream(Paths.get("res/images/sprites/goku/gokupower.png"));
				initialPlayerOneX = 0;
				initialPlayerOneY = gameCanvas.getHeight() - 340;
				hit1 = "res/images/sprites/goku/gokuhit.wav";
				hurt1 = "res/images/sprites/goku/gokuhurt.wav";
				break;
			case "thanos":
				filePath2 = Files.newInputStream(Paths.get("res/images/sprites/thanos/thanosidle.png"));
				filePath4 = Files.newInputStream(Paths.get("res/images/sprites/thanos/thanoswalk.png"));
				filePath6 = Files.newInputStream(Paths.get("res/images/sprites/thanos/thanosjump.png"));
				filePath8 = Files.newInputStream(Paths.get("res/images/sprites/thanos/thanospunch.png"));
				filePath10 = Files.newInputStream(Paths.get("res/images/sprites/thanos/thanoshurt.png"));
				filePath12 = Files.newInputStream(Paths.get("res/images/sprites/thanos/thanoscrouch.png"));
				filePath14 = Files.newInputStream(Paths.get("res/images/sprites/thanos/thanospower.png"));
				initialPlayerOneX = 0;
				initialPlayerOneY = gameCanvas.getHeight() - 350 - 43;
				hit1 = "res/images/sprites/thanos/thanoshit.wav";
				hurt1 = "res/images/sprites/thanos/thanoshurt.mp3";
				break;
			case "batman":
				filePath2 = Files.newInputStream(Paths.get("res/images/sprites/batman/batmanidle.png"));
				filePath4 = Files.newInputStream(Paths.get("res/images/sprites/batman/batmanwalk.png"));
				filePath6 = Files.newInputStream(Paths.get("res/images/sprites/batman/batmanjump.png"));
				filePath8 = Files.newInputStream(Paths.get("res/images/sprites/batman/batmanpunch.png"));
				filePath10 = Files.newInputStream(Paths.get("res/images/sprites/batman/batmanhurt.png"));
				filePath12 = Files.newInputStream(Paths.get("res/images/sprites/batman/batmancrouch.png"));
				initialPlayerOneX = 0;
				initialPlayerOneY = gameCanvas.getHeight() - 350 - 43;
				hit1 = "res/images/sprites/batman/batmanhit.wav";
				hurt1 = "res/images/sprites/batman/batmanhurt.wav";
				break;
		}
		
		
		switch (playerTwo) {
			case "reptile": 
				filePath3 = Files.newInputStream(Paths.get("res/images/sprites/reptile/reptileidle.png")); 
				filePath5 = Files.newInputStream(Paths.get("res/images/sprites/reptile/reptilewalk.png")); 
				filePath7 = Files.newInputStream(Paths.get("res/images/sprites/reptile/reptilejump.png")); 
				filePath9 = Files.newInputStream(Paths.get("res/images/sprites/reptile/reptilepunch.png"));
				filePath11 = Files.newInputStream(Paths.get("res/images/sprites/reptile/reptilehurt.png"));
				filePath13 = Files.newInputStream(Paths.get("res/images/sprites/reptile/reptilecrouch.png"));
				initialPlayerTwoX = gameCanvas.getWidth() - 20;
				initialPlayerTwoY = gameCanvas.getHeight() - 350 - 78;
				hit2 = "res/images/sprites/reptile/reptilehit.wav";
				hurt2 = "res/images/sprites/reptile/reptilehurt.wav";
				break;
			case "goku": 
				filePath3 = Files.newInputStream(Paths.get("res/images/sprites/goku/gokuidle.png"));
				filePath5 = Files.newInputStream(Paths.get("res/images/sprites/goku/gokuwalk.png")); 
				filePath7 = Files.newInputStream(Paths.get("res/images/sprites/goku/gokujump.png")); 
				filePath9 = Files.newInputStream(Paths.get("res/images/sprites/goku/gokupunch.png"));
				filePath11 = Files.newInputStream(Paths.get("res/images/sprites/goku/gokuhurt.png"));
				filePath13 = Files.newInputStream(Paths.get("res/images/sprites/goku/gokucrouch.png"));
				filePath15 = Files.newInputStream(Paths.get("res/images/sprites/goku/gokupower.png"));
				initialPlayerTwoX = gameCanvas.getWidth();
				initialPlayerTwoY = gameCanvas.getHeight() - 340;
				hit2 = "res/images/sprites/goku/gokuhit.wav";
				hurt2 = "res/images/sprites/goku/gokuhurt.wav";
				break;
			case "thanos":
				filePath3 = Files.newInputStream(Paths.get("res/images/sprites/thanos/thanosidle.png"));
				filePath5 = Files.newInputStream(Paths.get("res/images/sprites/thanos/thanoswalk.png")); 
				filePath7 = Files.newInputStream(Paths.get("res/images/sprites/thanos/thanosjump.png")); 
				filePath9 = Files.newInputStream(Paths.get("res/images/sprites/thanos/thanospunch.png"));
				filePath11 = Files.newInputStream(Paths.get("res/images/sprites/thanos/thanoshurt.png"));
				filePath13 = Files.newInputStream(Paths.get("res/images/sprites/thanos/thanoscrouch.png"));
				filePath15 = Files.newInputStream(Paths.get("res/images/sprites/thanos/thanospower.png"));
				initialPlayerTwoX = gameCanvas.getWidth();
				initialPlayerTwoY = gameCanvas.getHeight() - 350 - 43;
				hit2 = "res/images/sprites/thanos/thanoshit.wav";
				hurt2 = "res/images/sprites/thanos/thanoshurt.mp3";
				break;
				
			case "batman":
				filePath3 = Files.newInputStream(Paths.get("res/images/sprites/batman/batmanidle.png"));
				filePath5 = Files.newInputStream(Paths.get("res/images/sprites/batman/batmanwalk.png")); 
				filePath7 = Files.newInputStream(Paths.get("res/images/sprites/batman/batmanjump.png")); 
				filePath9 = Files.newInputStream(Paths.get("res/images/sprites/batman/batmanpunch.png"));
				filePath11 = Files.newInputStream(Paths.get("res/images/sprites/batman/batmanhurt.png"));
				filePath13 = Files.newInputStream(Paths.get("res/images/sprites/batman/batmancrouch.png"));
				initialPlayerTwoX = gameCanvas.getWidth();
				initialPlayerTwoY = gameCanvas.getHeight() - 350 - 43;
				hit2 = "res/images/sprites/batman/batmanhit.wav";
				hurt2 = "res/images/sprites/batman/batmanhurt.wav";
				break;
	}
		
		
		
		playerHit1 = new AudioClip(new File(hit1).toURI().toString());
		playerHit2 = new AudioClip(new File(hit2).toURI().toString());
		
		playerHurt1 = new AudioClip(new File(hurt1).toURI().toString());
		playerHurt2 = new AudioClip(new File(hurt2).toURI().toString());
		
		playerHit1.setVolume(volume);
		playerHit2.setVolume(volume);
		
		playerHurt1.setVolume(volume);
		playerHurt2.setVolume(volume);
		
		//Setting up background variable
		Image background = new Image(filePath,1000,725,false,false);
		filePath.close();
		
		
		//Dynamic image variables for sprite sheets, that are based off character picked
		Image sheetOneIdle = new Image(filePath2);
		filePath2.close();
		
		Image sheetOneWalk = new Image(filePath4);
		filePath4.close();
	
		Image sheetOneJump = new Image(filePath6);
		filePath6.close();
		
		Image sheetOnePunch = new Image(filePath8);
		filePath8.close();
		
		Image sheetOneHurt = new Image(filePath10);
		filePath10.close();
		
		Image sheetOneCrouch = new Image(filePath12);
		filePath12.close();
		
		Image sheetOnePower = new Image(filePath14);
		filePath14.close();
		
		
		Image sheetTwoIdle = new Image(filePath3);
		filePath3.close();
		
		Image sheetTwoWalk = new Image(filePath5);
		filePath5.close();
		
		Image sheetTwoJump = new Image(filePath7);
		filePath7.close();
		
		Image sheetTwoPunch = new Image(filePath9);
		filePath9.close();

		Image sheetTwoHurt = new Image(filePath11);
		filePath11.close();
		
		Image sheetTwoCrouch = new Image(filePath13);
		filePath13.close();
		
		Image sheetTwoPower = new Image(filePath15);
		filePath15.close();
		

		//Initializing player class and sending all required variables
		Player player1 = new Player(sheetOneIdle, sheetOneWalk, sheetOneJump,sheetOnePunch,sheetOneHurt,sheetOneCrouch, sheetOnePower, gc, gameCanvas, playerOne);
		Player player2 = new Player(sheetTwoIdle, sheetTwoWalk, sheetTwoJump,sheetTwoPunch,sheetTwoHurt, sheetTwoCrouch, sheetTwoPower,gc, gameCanvas, playerTwo);
		
		
		
		if (playerOne.equals("goku")) {
			player1.setDamage(0.75);
			player1.setSpeed(10);
			trueSpeed1 = 10;
			
		}
		
		if (playerTwo.equals("goku")) {
			player2.setDamage(0.75);
			player2.setSpeed(10);
			trueSpeed2 = 10;
		}
		
		
		if (playerOne.equals("thanos")) {
			player1.setDamage(1.75);
			player1.setSpeed(2);
			trueSpeed1 = 2;
		}
		
		if (playerTwo.equals("thanos")) {
			player2.setDamage(1.75);
			player2.setSpeed(2);
			trueSpeed2 = 2;
		}
		
		//Setting the x for initial positions
		player1.setX(initialPlayerOneX); player1.setY(initialPlayerOneY);
		player2.setX(initialPlayerTwoX); player2.setY(initialPlayerTwoY);
	
	
		//Input array lists
		ArrayList<String> input = new ArrayList<String>();
		ArrayList<String> input2 = new ArrayList<String>();
		
		  
		Media OST = new Media(new File(soundTrack).toURI().toString());
		mediaPlayer = new MediaPlayer(OST);
		
		mediaPlayer.setVolume(volume);
		mediaPlayer.play();
		
		
		if (multiPlayer == true) {
			 //Add every button press to two different arrays
			 gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
		            @Override
		            public void handle(KeyEvent e) {
		                String code = e.getCode().toString();
		                if (!input.contains(code)) {
		                    input.add(code);	
		                }
		                if (!input2.contains(code)) {
		                	input2.add(code);
		                }
		            }		
		        });
			 	
			 	//When key is released, remove input from arraylist
		        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
		            @Override
		            public void handle(KeyEvent e) {
		                String code = e.getCode().toString();
		                if (input.contains(code)) {
		                    input.remove(code);
		                }
		                if (input2.contains(code)) {
		                    input2.remove(code);
		                }
		            }
		        });
		}

		
		if (singlePlayer == true) {
			 //Add every button press to two different arrays
			 gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
		            @Override
		            public void handle(KeyEvent e) {
		                String code = e.getCode().toString();
		                if (!input.contains(code)) {
		                    input.add(code);	
		                }
		            }		
		        });
			 	
			 	//When key is released, remove input from arraylist
		        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
		            @Override
		            public void handle(KeyEvent e) {
		                String code = e.getCode().toString();
		                if (input.contains(code)) {
		                    input.remove(code);
		                }
		            }
		        });
		}
		
	    //Request garbage collection so game starts with full memory
	    System.gc();
	 
	    
			new AnimationTimer() {
				// actual game loop that repeats
				@Override
				public void handle(long currentNanoTime) {
				if (multiPlayer == true) {
					//If memory hasn't been collected yet, attempt to collect
					if (memoryCollect == false) {
						System.gc();
						memoryCollect = true;
					}
					
					//Clear canvas every loop
					gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
					gc.drawImage(background, 0, 0);
	
					mediaPlayer.setOnEndOfMedia(new Runnable() {
						public void run() {
							mediaPlayer.seek(Duration.ZERO);
					}
				});
					
					
					if (attackReady1 == true && attackLength1 > 0) {
						action1 = "attack";
						if (!(collisionType1.equals("")) && action2 != "crouch") {
							getHurt2 = true;
							healthPlayer2.setHealth(healthPlayer2.getHealth() - player1.getDamage());
							action2 = "hurt";
						}
						
						attackLength1 -= 1;
					}
					
					if (attackReady2 == true && attackLength2 > 0 ) {
						action2 = "attack";
						if (!(collisionType2.equals("")) && action1 != "crouch") {
							getHurt1 = true;
							healthPlayer1.setHealth(healthPlayer1.getHealth() - player2.getDamage());
							action1 = "hurt";
						}
						
						attackLength2 -= 1;
					}
					
					
					hurtTimer2 -= 1;
					hurtTimer1 -= 1;
					
					
					if (getHurt1 == true & hurtTimer1 <= 0) {
						playerHurt1.play();
						hurtTimer1 = 60;
						getHurt1 = false;
					}
					
					
	
					if (getHurt2 == true & hurtTimer2 <= 0) {
						playerHurt2.play();
						hurtTimer2 = 60;
						getHurt2 = false;
					}
						
					
					if (attackLength1 == 0) {
						playerHit1.play();
						attackReady1 = false;
						attackLength1 = 20;
						attackCooldown1 = 70;
					}
					
					if (attackLength2 == 0) {
						playerHit2.play();
						attackReady2 = false;
						attackLength2 = 20;
						attackCooldown2 = 70;
					}
					
					
					if (attackReady1 == false && attackCooldown1 > 0) {
						attackCooldown1 -= 1;
					}
					
					if (attackReady2 == false && attackCooldown2 > 0) {
						attackCooldown2 -= 1;
					}
					
					
					if (input.contains("S") && player1.getDy() == 0 && !(energyPlayer1.getEnergy() <= 0)) {
						action1 = "crouch";
						
					}
					
					if (input2.contains("DOWN") && player2.getDy() == 0 && !(energyPlayer2.getEnergy() <= 0)) {
						action2 = "crouch";
					}
					
					
					if (action1 == "crouch") {
						energyPlayer1.setEnergy(energyPlayer1.getEnergy() - 0.6);
					}
					
					if (action2 == "crouch") {
						energyPlayer2.setEnergy(energyPlayer2.getEnergy() - 0.6);
					}
	
					
					if (energyPlayer1.getEnergy() <= 0 && action1 == "crouch") {
						action1 = "idle";
					}
					
					if (energyPlayer2.getEnergy() <= 0 && action2 == "crouch") {
						action2 = "idle";
					}


					if (energyPlayer1.getEnergy() <= 0 && action1 == "power") {
						action1 = "idle";
					}
					
					if (energyPlayer2.getEnergy() <= 0 && action2 == "power") {
						action2 = "idle";
					}
					
					//Send created players WASD, arrow keys, orientation, and collision type
					player1.move(input, "A", "W", "D", orientation1, action1, collisionType1);
					player2.move(input2, "LEFT", "UP", "RIGHT", orientation2, action2, collisionType2);
					
					
					//Clearing collision type every loop
					collisionType1 = "";
					collisionType2 = "";
					
					
					//Various if statements to determine players action at that moment
					if (player1.getDx() == 0  && player1.getDy() == 0 && action1 != "crouch") {
						action1 = "idle";
					}
					
					
					if (player2.getDx() == 0  && player2.getDy() == 0 && action2 != "crouch") {
						action2 = "idle";
					}
					
					
					if (Math.abs(player1.getDx()) > 0  && Math.abs(player1.getDy()) == 0) {
						action1 = "walk";
					}
					
					
					if (Math.abs(player2.getDx()) > 0  && Math.abs(player2.getDy()) == 0) {
						action2 = "walk";
					}
					
	
					if (playerOne.equals("reptile")) {
						if (input.contains("W") || (player1.getY() < gameCanvas.getHeight() - 350 - 78)) {
							action1 = "jump";
						}
						} else if (playerOne.equals("goku")) { 
							if (input.contains("W") || (player1.getY() < gameCanvas.getHeight() - 340)) {
								action1 = "jump";
							}
						}else {
							if (input.contains("W") || (player1.getY() < gameCanvas.getHeight() - 350 - 43)) {
								action1 = "jump";
							}
						}
					
					
					if (playerTwo.equals("reptile")) {
						if (input2.contains("UP") || (player2.getY() < gameCanvas.getHeight() - 350 - 78)) {
							action2 = "jump";
							}
						} else if (playerTwo.equals("goku")) { 
							if (input2.contains("UP") || (player2.getY() < gameCanvas.getHeight() - 340)) {
								action2 = "jump";
							}
						} else {
							if (input2.contains("UP") || (player2.getY() < gameCanvas.getHeight() - 350 - 43)) {
								action2 = "jump";
							}
						}
					
	
					
					//If the player's X is greater than the enemies, flip stances accoringly
					if (player1.getX() >= player2.getX() + 10) {
						orientation1 = -1;
					} else {
						orientation1 = 1;
					}
					
					if (player2.getX() >= player1.getX() + 10) {
						orientation2 = -1;
					} else {
						orientation2 = 1;
					}
					
					
					//Set collision types dependent on types of collision
					if (player1.getDy() == 0) {
						if ((player1.collision(player2.getX() - 50, player2.getY(), player2.getWidth() + 30, player2.getHeight() - 50) && orientation1 == 1)) {
							collisionType1 = "fromLeft";
						} 
						
						
						if ((player1.collision(player2.getX(), player2.getY(), player2.getWidth(), player2.getHeight() - 50) && orientation1 == -1)) {
							collisionType1 = "fromRight";
						}
					
					}	
					
						
					if (player2.getDy() == 0) {
						if ((player2.collision(player1.getX(), player1.getY(), player1.getWidth(), player1.getHeight() - 50) && orientation2 == 1)) {
							collisionType2 = "fromLeft";
						} 
						
						if ((player2.collision(player1.getX(), player1.getY(), player1.getWidth() - 35, player1.getHeight() - 50) && orientation2 == -1)) {
							collisionType2 = "fromRight";
						}
					}
					
					
					//Attack inputs
					if (attackCooldown1 == 0 && !(energyPlayer1.getEnergy() <= 30)) {
						if (input.contains("E") && !(action1.equals("jump") && attackReady1 == false)) {
							energyPlayer1.setEnergy(energyPlayer1.getEnergy() - 9);
							playerHit1.stop();
							playerHit1.play();
							attackReady1 = true;
							attackLength1 = 20;
						}
					}
					
					
					if (attackCooldown2 == 0 && !(energyPlayer2.getEnergy() <= 30)) {
						if (input2.contains("K") && !(action2.equals("jump") && attackReady2 == false)) {
							energyPlayer2.setEnergy(energyPlayer2.getEnergy() - 9);
							playerHit2.stop();
							playerHit2.play();
							attackReady2 = true;
							attackLength2 = 20;
						}
					}
					
					
					if(playerOne.equals("reptile")) {
						if (input.contains("R") && !(energyPlayer1.getEnergy() <= 0)) {
							action1 = "power";
							energyPlayer1.setEnergy(energyPlayer1.getEnergy() - 1);
						}
					}
					
					if (playerTwo.equals("reptile")) {
						if (input2.contains("L") && !(energyPlayer2.getEnergy() <= 0)) {
							action2 = "power";
							energyPlayer2.setEnergy(energyPlayer2.getEnergy() - 1);
						}
					}
					
					
					if(playerOne.equals("goku")) {
						if (input.contains("R") && !(energyPlayer1.getEnergy() <= 0)) {
							action1 = "power";
							player1.setSpeed(player1.getSpeed() + 0.01);
							player1.setDamage(player1.getDamage() + 0.001);
							energyPlayer1.setEnergy(energyPlayer1.getEnergy() - 1);
						}
					}
					
					if (playerTwo.equals("goku")) {
						if (input2.contains("L") && !(energyPlayer2.getEnergy() <= 0)) {
							action2 = "power";
							player2.setSpeed(player2.getSpeed() + 0.01);
							player2.setDamage(player2.getDamage() + 0.001);
							energyPlayer2.setEnergy(energyPlayer2.getEnergy() - 1);
						}
					}
					

					
					
					if(playerOne.equals("thanos")) {
						if (input.contains("R") && !(energyPlayer1.getEnergy() <= 0)) {
							action1 = "power";
							player2.setSpeed(0.2);
							player2.setAcceleration(0);
							player2.setVelocity(0);
							energyPlayer1.setEnergy(energyPlayer1.getEnergy() - 1);
						} else {
							player2.setSpeed(trueSpeed2);
							player2.setAcceleration(0.4);
						}
					}
					
					if (playerTwo.equals("thanos")) {
						if (input2.contains("L") && !(energyPlayer2.getEnergy() <= 0)) {
							action2 = "power";
							player1.setSpeed(0.2);
							player1.setAcceleration(0);
							player1.setVelocity(0);
							energyPlayer2.setEnergy(energyPlayer2.getEnergy() - 1);
						} else {
							player1.setSpeed(trueSpeed1);
							player1.setAcceleration(0.4);
						}
					}
					
		
					
					
					
					
					//Display health bars using a snap shot of the player icon
					healthPlayer1.display(110,50, player1Icon.snapshot(null, null),1);
					healthPlayer2.display(594,50,player2Icon.snapshot(null,null), 2);
		
					energyPlayer1.display(110, 100);
					energyPlayer2.display(744, 100);
					
					if(!(energyPlayer1.getEnergy() >= 150)) {
						if (playerOne.equals("batman")) {
							energyPlayer1.setEnergy(energyPlayer1.getEnergy() + 0.8);
						} else {
							energyPlayer1.setEnergy(energyPlayer1.getEnergy() + 0.4);
						}
					}
					
					if(!(energyPlayer2.getEnergy() >= 150)) {
						if (playerTwo.equals("batman")) {
							energyPlayer2.setEnergy(energyPlayer2.getEnergy() + 0.8);
						} else {
							energyPlayer2.setEnergy(energyPlayer2.getEnergy() + 0.4);
						}
					}
					
					
					if (healthPlayer1.getHealth() <= 0) {
						winner = "player2";
					}
					
					if (healthPlayer2.getHealth() <= 0) {
						winner = "player1";
					}
					
					//If either health reaches 0, end game
					if (!(winner.equals(""))) {
						multiPlayer = false;
						currentStage.close();
						openEndScreen();
						playerHit1.stop();
						playerHit2.stop();
						playerHurt1.stop();
						playerHurt2.stop();
						mediaPlayer.stop();
						
					}

					
	            	}
				
				if (singlePlayer == true) {
					//If memory hasn't been collected yet, attempt to collect
					if (memoryCollect == false) {
						System.gc();
						memoryCollect = true;
					}
					
					//Clear canvas every loop
					gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
					gc.drawImage(background, 0, 0);
	
					mediaPlayer.setOnEndOfMedia(new Runnable() {
						public void run() {
							mediaPlayer.seek(Duration.ZERO);
					}
				});
					
					
					if (attackReady1 == true && attackLength1 > 0) {
						action1 = "attack";
						if (!(collisionType1.equals("")) && action2 != "crouch") {
							getHurt2 = true;
							healthPlayer2.setHealth(healthPlayer2.getHealth() - player1.getDamage());
							action2 = "hurt";
						}
						
						attackLength1 -= 1;
					}
					
					if (attackReady2 == true && attackLength2 > 0 ) {
						action2 = "attack";
						if (!(collisionType2.equals("")) && action1 != "crouch") {
							getHurt1 = true;
							healthPlayer1.setHealth(healthPlayer1.getHealth() - player2.getDamage());
							action1 = "hurt";
						}
						
						attackLength2 -= 1;
					}
					
					
					hurtTimer2 -= 1;
					hurtTimer1 -= 1;
					
					
					if (getHurt1 == true & hurtTimer1 <= 0) {
						playerHurt1.play();
						hurtTimer1 = 60;
						getHurt1 = false;
					}
					
					
	
					if (getHurt2 == true & hurtTimer2 <= 0) {
						playerHurt2.play();
						hurtTimer2 = 60;
						getHurt2 = false;
					}
						
					
					if (attackLength1 == 0) {
						playerHit1.play();
						attackReady1 = false;
						attackLength1 = 20;
						attackCooldown1 = 70;
					}
					
					if (attackLength2 == 0) {
						playerHit2.play();
						attackReady2 = false;
						attackLength2 = 20;
						attackCooldown2 = 70;
					}
					
					
					if (attackReady1 == false && attackCooldown1 > 0) {
						attackCooldown1 -= 1;
					}
					
					if (attackReady2 == false && attackCooldown2 > 0) {
						attackCooldown2 -= 1;
					}
					
					
					if (input.contains("S") && player1.getDy() == 0 && !(energyPlayer1.getEnergy() <= 0)) {
						action1 = "crouch";
						
					}
					
					if (action1 == "attack" && player2.getDy() == 0 && !(energyPlayer2.getEnergy() <= 0)) {
						action2 = "crouch";
					}
					
					
					if (action1 == "crouch") {
						energyPlayer1.setEnergy(energyPlayer1.getEnergy() - 0.6);
					}
					
					if (action2 == "crouch") {
						energyPlayer2.setEnergy(energyPlayer2.getEnergy() - 0.6);
					}
	
					
					if (energyPlayer1.getEnergy() <= 0 && action1 == "crouch") {
						action1 = "idle";
					}
					
					if (energyPlayer2.getEnergy() <= 0 && action2 == "crouch") {
						action2 = "idle";
					}
					
					if (energyPlayer1.getEnergy() <= 0 && action1 == "power") {
						action1 = "idle";
					}
					
					if (energyPlayer2.getEnergy() <= 0 && action2 == "power") {
						action2 = "idle";
					}
					
					
					
					//Send created players WASD, arrow keys, orientation, and collision type
					player1.move(input, "A", "W", "D", orientation1, action1, collisionType1);
					player2.move(input2, "LEFT", "UP", "RIGHT", orientation2, action2, collisionType2);
					input2.clear();
					
					//Clearing collision type every loop
					collisionType1 = "";
					collisionType2 = "";
					
					
					//Various if statements to determine players action at that moment
					if (player1.getDx() == 0  && player1.getDy() == 0 && action1 != "crouch") {
						action1 = "idle";
					}
					
					
					if (player2.getDx() == 0  && player2.getDy() == 0 && action2 != "crouch") {
						action2 = "idle";
					}
					
					
					if (Math.abs(player1.getDx()) > 0  && Math.abs(player1.getDy()) == 0) {
						action1 = "walk";
					}
					
					
					if (Math.abs(player2.getDx()) > 0  && Math.abs(player2.getDy()) == 0) {
						action2 = "walk";
					}
					
	
					if (playerOne.equals("reptile")) {
						if (input.contains("W") || (player1.getY() < gameCanvas.getHeight() - 350 - 78)) {
							action1 = "jump";
						}
						} else if (playerOne.equals("goku")) { 
							if (input.contains("W") || (player1.getY() < gameCanvas.getHeight() - 340)) {
								action1 = "jump";
							}
						}else {
							if (input.contains("W") || (player1.getY() < gameCanvas.getHeight() - 350 - 43)) {
								action1 = "jump";
							}
						}
					
					
					if (playerTwo.equals("reptile")) {
						if (input2.contains("UP") || (player2.getY() < gameCanvas.getHeight() - 350 - 78)) {
							action2 = "jump";
							}
						} else if (playerTwo.equals("goku")) { 
							if (input2.contains("UP") || (player2.getY() < gameCanvas.getHeight() - 340)) {
								action2 = "jump";
							}
						} else {
							if (input2.contains("UP") || (player2.getY() < gameCanvas.getHeight() - 350 - 43)) {
								action2 = "jump";
							}
						}
					
	
					
					//If the player's X is greater than the enemies, flip stances accoringly
					if (player1.getX() >= player2.getX() + 10) {
						orientation1 = -1;
					} else {
						orientation1 = 1;
					}
					
					if (player2.getX() >= player1.getX() + 10) {
						orientation2 = -1;
					} else {
						orientation2 = 1;
					}
					
					
					//Set collision types dependent on types of collision
					if (player1.getDy() == 0) {
						if ((player1.collision(player2.getX(), player2.getY(), player2.getWidth() - 30, player2.getHeight() - 50) && orientation1 == 1)) {
							collisionType1 = "fromLeft";
						} 
						
						
						if ((player1.collision(player2.getX(), player2.getY(), player2.getWidth() - 30, player2.getHeight() - 50) && orientation1 == -1)) {
							collisionType1 = "fromRight";
						}
					
					}	
					
						
					if (player2.getDy() == 0) {
						if ((player2.collision(player1.getX(), player1.getY(), player1.getWidth() - 30, player1.getHeight() - 50) && orientation2 == 1)) {
							collisionType2 = "fromLeft";
						} 
						
						if ((player2.collision(player1.getX(), player1.getY(), player1.getWidth() - 30, player1.getHeight() - 50) && orientation2 == -1)) {
							collisionType2 = "fromRight";
						}
					}
					
					
					//Attack inputs
					if (attackCooldown1 == 0 && !(energyPlayer1.getEnergy() <= 30)) {
						if (input.contains("E") && !(action1.equals("jump") && attackReady1 == false)) {
							energyPlayer1.setEnergy(energyPlayer1.getEnergy() - 15);
							playerHit1.stop();
							playerHit1.play();
							attackReady1 = true;
							attackLength1 = 20;
						}
					}
					
					
					if (attackCooldown2 == 0 && !(energyPlayer2.getEnergy() <= 30)) {
						if (!(collisionType2.equals("")) && !(action2.equals("jump") && attackReady2 == false)) {
							energyPlayer2.setEnergy(energyPlayer2.getEnergy() - 15);
							playerHit2.stop();
							playerHit2.play();
							attackReady2 = true;
							attackLength2 = 20;
						}
					}
					
					
					if (player2.getX() < player1.getX()) {
						input2.add("RIGHT");
					}
						
					
					if (player2.getX() > player1.getX()) {
						input2.add("LEFT");
					}
					
					
					
					
					
					if(playerOne.equals("reptile")) {
						if (input.contains("R") && !(energyPlayer1.getEnergy() <= 0)) {
							action1 = "power";
							energyPlayer1.setEnergy(energyPlayer1.getEnergy() - 1);
						}
					}
					
					if (playerTwo.equals("reptile")) {
						if (input2.contains("L") && !(energyPlayer2.getEnergy() <= 0)) {
							action2 = "power";
							energyPlayer2.setEnergy(energyPlayer2.getEnergy() - 1);
						}
					}
					
					
					if(playerOne.equals("goku")) {
						if (input.contains("R") && !(energyPlayer1.getEnergy() <= 0)) {
							action1 = "power";
							player1.setSpeed(player1.getSpeed() + 0.01);
							player1.setDamage(player1.getDamage() + 0.001);
							energyPlayer1.setEnergy(energyPlayer1.getEnergy() - 1);
						}
					}
					
					if (playerTwo.equals("goku")) {
						if (input2.contains("L") && !(energyPlayer2.getEnergy() <= 0)) {
							action2 = "power";
							player2.setSpeed(player2.getSpeed() + 0.01);
							player2.setDamage(player2.getDamage() + 0.001);
							energyPlayer2.setEnergy(energyPlayer2.getEnergy() - 1);
						}
					}
					
					
					
					if(playerOne.equals("thanos")) {
						if (input.contains("R") && !(energyPlayer1.getEnergy() <= 0)) {
							action1 = "power";
							player2.setSpeed(0.2);
							player2.setAcceleration(0);
							player2.setVelocity(0);
							energyPlayer1.setEnergy(energyPlayer1.getEnergy() - 1);
						} else {
							player2.setSpeed(trueSpeed2);
							player2.setAcceleration(0.4);
						}
					}
					
					if (playerTwo.equals("thanos")) {
						if (input2.contains("L") && !(energyPlayer2.getEnergy() <= 0)) {
							action2 = "power";
							player1.setSpeed(0.2);
							player1.setAcceleration(0);
							player1.setVelocity(0);
							energyPlayer2.setEnergy(energyPlayer2.getEnergy() - 1);
						} else {
							player1.setSpeed(trueSpeed1);
							player1.setAcceleration(0.4);
						}
					}
					
					
					
					
					//Display health bars using a snap shot of the player icon
					healthPlayer1.display(110,50, player1Icon.snapshot(null, null),1);
					healthPlayer2.display(594,50,player2Icon.snapshot(null,null), 2);
		
					energyPlayer1.display(110, 100);
					energyPlayer2.display(744, 100);
					
					if(!(energyPlayer1.getEnergy() >= 150)) {
						if (playerOne.equals("batman")) {
							energyPlayer1.setEnergy(energyPlayer1.getEnergy() + 0.8);
						} else {
							energyPlayer1.setEnergy(energyPlayer1.getEnergy() + 0.4);
						}
					}
					
					if(!(energyPlayer2.getEnergy() >= 150)) {
						if (playerTwo.equals("batman")) {
							energyPlayer2.setEnergy(energyPlayer2.getEnergy() + 0.8);
						} else {
							energyPlayer2.setEnergy(energyPlayer2.getEnergy() + 0.4);
						}
					}
					
					
					if (healthPlayer1.getHealth() <= 0) {
						winner = "player2";
					}
					
					if (healthPlayer2.getHealth() <= 0) {
						winner = "player1";
					}
					
					//If either health reaches 0, end game
					if (!(winner.equals(""))) {
						singlePlayer = false;
						currentStage.close();
						openEndScreen();
						playerHit1.stop();
						playerHit2.stop();
						playerHurt1.stop();
						playerHurt2.stop();
						mediaPlayer.stop();
						
					}

					
	            	}
					
				}
			}.start();
			
		}
	
	
	
	/** Method to open end screen once either player is defeated. */
	private void openEndScreen() {
		try {
			//Create a new scene under the end.fxml guidance, and apply rankings.css
			FXMLLoader loader = new FXMLLoader(getClass().getResource("EndScreen.fxml"));
			
			Pane endScreenRoot = (Pane)loader.load();
			Scene endScreenScene = new Scene(endScreenRoot,1024,768);
			endScreenScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//Send queue and stack to be retrieved in the next controller
			
			
			CharacterSelectionController.mediaPlayer.play();
			EndScreenController currentController = (loader.getController());
			currentController.getVariables(volume, menu,playerOne,playerTwo,winner);
			
			//Initialize stage and show 
			Stage endScreenStage = new Stage();
			endScreenStage.setResizable(false);
			endScreenStage.setScene(endScreenScene);
			endScreenStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}

