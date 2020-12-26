//JavaFX energy Bar Game
//Umer Ahmad
//December 5, 2018
//This program is a game in which you play as a energy Bar and try to collect as many bananas as possible, avoiding obstacles
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


/** energy Bar class to store all getters and setters. */
public class EnergyBar {    
    
    // declare fields (i.e. variables)
	

    double energy = 150;
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
    
    /** energy Bar constructor that initializes canvas and graphics context without an image name
	 * @param gc A graphics context variable to refer for gfx.
	 * @param canvas A FXML variable to represent the canvas to be drawed on.
	 */
    public EnergyBar(GraphicsContext gc, Canvas canvas) {
        super();
        this.gc = gc;
        this.gc2 = gc;
        this.gameCanvas = canvas;
        // can put code for random x and y here
        // now that we have a reference to our gc and gameCanvas
    }


    /** Gets the energy value of the energy Bar.
   	 * @return A double representing the energy value.
   	 */
       public double getEnergy() {
           return energy;
       }

       /** Sets the energy value of the energy Bar to the parametered integer.
   		* @param energy A integer representing the energy value. 
   		*/
       public void setEnergy(double energy) {
           this.energy = energy;
       }
    
    /** Method to display paramatered statistics onto the screen.
   	 * @param x A int variable that is used to set the x position of the energy bar.
   	 * @param y A int variable that is used to set the y position of the energy bar.
   	 * @param icon A image variable that grabs the icon clicked in character selection controler to be displayed.
   	 * @param place A int variable that sets various bars if player 1 or player 2
   	 */
   	public void display(int x, int y){

   		bar.setWidth(energy);
   		bar.setHeight(25);
   		bar.setX(x);
   		bar.setY(y);
   		
   		
   		bar2.setWidth(150);
   		bar2.setHeight(25);
   		bar2.setX(x);
   		bar2.setY(y);
   		
   		
   		this.gc2.setFill(Color.BLUE);
   		this.gc2.fillRect(bar2.getX(), bar2.getY(), bar2.getWidth(), bar2.getHeight());
   		
   			
   		if (x == 744) {
   			if (energy != 150) {
   				bar.setX(x + (150 - energy));
   			}
   		}
   		
   		this.gc.setFill(Color.YELLOW);
   		this.gc.fillRect(bar.getX(), bar.getY(), bar.getWidth(), bar.getHeight());

   		

   	}


}
