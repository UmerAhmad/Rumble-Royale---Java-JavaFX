//JavaFX Health Bar Game
//Umer Ahmad
//December 5, 2018
//This program is a game in which you play as a Health Bar and try to collect as many bananas as possible, avoiding obstacles
//Program requires Java prerequisites (JDK, java.io, JavaFx, libraries, etc.)

//Importing necessities
package rumbleRoyale;
import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JProgressBar;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


/** Health Bar class to store all getters and setters. */
public class HealthBar {    
    
    // declare fields (i.e. variables)
	

    double health = 300;
    JProgressBar healthBar;

   
    
    InputStream filePath = null;
    
    
    Rectangle bar = new Rectangle();
    Rectangle bar2 = new Rectangle();

    
    // GraphicsContext variable that allows us to refer to gfx.*/
    GraphicsContext gc;
    GraphicsContext gc2;
    
    // FXML id of gameCanvas to draw on. */
    @FXML Canvas gameCanvas;
      
    
    
    // contructors -----------------------------------------------
    
    /** Health Bar constructor that initializes canvas and graphics context without an image name
	 * @param gc A graphics context variable to refer for gfx.
	 * @param canvas A FXML variable to represent the canvas to be drawed on.
	 */
    public HealthBar(GraphicsContext gc, Canvas canvas) {
        super();
        this.gc = gc;
        this.gc2 = gc;
        this.gameCanvas = canvas;
        // can put code for random x and y here
        // now that we have a reference to our gc and gameCanvas
    }


    /** Gets the health value of the Health Bar.
   	 * @return A double representing the health value.
   	 */
       public double getHealth() {
           return health;
       }

       /** Sets the health value of the Health Bar to the parametered integer.
   		* @param health A integer representing the health value. 
   		*/
       public void setHealth(double health) {
           this.health = health;
       }
    
    /** Method to display paramatered statistics onto the screen.
   	 * @param x A int variable that is used to set the x position of the health bar.
   	 * @param y A int variable that is used to set the y position of the health bar.
   	 * @param icon A image variable that grabs the icon clicked in character selection controler to be displayed.
   	 * @param place A int variable that sets various bars if player 1 or player 2
   	 */
   	public void display(int x, int y, Image icon, int place){

   		bar.setWidth(health);
   		bar.setHeight(50);
   		bar.setX(x);
   		bar.setY(y);
   		
   		
   		bar2.setWidth(300);
   		bar2.setHeight(50);
   		bar2.setX(x);
   		bar2.setY(y);
   		
   		
   		this.gc2.setFill(Color.GRAY);
   		this.gc2.fillRect(bar2.getX(), bar2.getY(), bar2.getWidth(), bar2.getHeight());
   		
   		if (place == 2) {
   			if (health != 300) {
   				bar.setX(x + (300 - health));
   			}
   		}
   			
   		this.gc.setFill(Color.RED);
   		this.gc.fillRect(bar.getX(), bar.getY(), bar.getWidth(), bar.getHeight());
   		
   		if (place == 1) {
   			this.gc.drawImage(icon, x-80, y - 15);
   		}
   		
   		if (place == 2) {
   			this.gc.drawImage(icon, x+ 302, y - 15);
   		}
   		

   	}


}
