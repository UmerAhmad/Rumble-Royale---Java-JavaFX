//JavaFX Player Game
//Umer Ahmad
//December 5, 2018
//This program is a game in which you play as a Player and try to collect as many bananas as possible, avoiding obstacles
//Program requires Java prerequisites (JDK, java.io, JavaFx, libraries, etc.)

//Importing necessities
package rumbleRoyale;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


/** Player class to store all getters and setters. */
public class Player {    
    
    // declare fields (i.e. variables)
	
	double speed = 6;
    double damage = 1;
    
    
    double velocity = 0;
    double acceleration = 0.4;
    
    String collision;
    
    // Global double randomized x value (co-ordinate on x-axis).*/
    double x;
    // Global double randomized y value (co-ordinate on y-axis).*/
	double y;
	
	// Global double dx value (value to add onto the x - simulating movement).*/
    double dx = 0; 
    // Global double dy value (value to add onto the y - simulating movement).*/
    double dy = - 0;
    
    int width = 0;
    int widthCounter = 64;
    
    InputStream filePath = null;
    
    double spriteWidth;
    double spriteHeight;
    
    double varyingSlideWidth;
    double totalSlideWidth;
    
    int frameCounter = 0 ;
    double slide = 0;
    
    Image idleImage;
    Image walkImage;
    Image jumpImage;
    Image punchImage;
    Image hurtImage;
    Image crouchImage;
    Image powerImage;
    
    String player;
    
    // Global String of image name (to insert into image). */
    String imageName;
    // Image variable that makes use of image name, to display onto canvas.*/
    Image image;
    
    // GraphicsContext variable that allows us to refer to gfx.*/
    GraphicsContext gc;
    // FXML id of gameCanvas to draw on. */
    @FXML Canvas gameCanvas;
      

    
    // contructors -----------------------------------------------
    
    /** Player constructor that initializes canvas and graphics context without an image name
	 * @param gc A graphics context variable to refer for gfx.
	 * @param canvas A FXML variable to represent the canvas to be drawed on.
	 */
    public Player(GraphicsContext gc, Canvas canvas) {
        super();
        this.gc = gc;
        this.gameCanvas = canvas;
        // can put code for random x and y here
        // now that we have a reference to our gc and gameCanvas
    }

    /** Player constructor that initializes canvas and graphics context without an image name
	 * @param gc A graphics context variable to refer for gfx.
	 * @param canvas A FXML variable to represent the canvas to be drawed on.
	 * @param imageName A string value to dynamically change image of object.
	 */
    public Player(Image idleImage, Image walkImage, Image jumpImage, Image punchImage , Image hurtImage,Image crouchImage,Image powerImage,GraphicsContext gc, Canvas canvas, String player) {
        super();
        this.image = idleImage;
        this.player = player;
        this.idleImage = idleImage;
        this.walkImage = walkImage;
        this.jumpImage = jumpImage;
        this.punchImage = punchImage;
        this.hurtImage = hurtImage;
        this.crouchImage = crouchImage;
        this.powerImage = powerImage;
        this.gc = gc;
        this.gameCanvas = canvas;
    }

    // all the getters and setters ----------------------------------
    
    /** Gets the speed value of the Player.
	 * @return A integer representing the speed value.
	 */
    public double getSpeed() {
        return speed;
    }

    /** Sets the speed value of the Player to the parametered integer.
	 * @param speed A integer representing the speed value. 
	 */
    public void setSpeed(double speed) {
        this.speed = speed;
    }


    /** Sets the speed value of the Player to the parametered integer.
	 * @param speed A integer representing the speed value. 
	 */
    public void setAcceleration(double acc) {
        this.acceleration = acc;
    }
    
    
    /** Sets the speed value of the Player to the parametered integer.
	 * @param speed A integer representing the speed value. 
	 */
    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }
    
    
    

    /** Gets the speed value of the Player.
	 * @return A integer representing the speed value.
	 */
    public double getDamage() {
        return damage;
    }

    /** Sets the speed value of the Player to the parametered integer.
	 * @param speed A integer representing the speed value. 
	 */
    public void setDamage(double damage) {
        this.damage = damage;
    }
    
    /** Gets the X value of the Player.
   	 * @return A double representing the X co-ordinate.
   	 */
    public double getX() {
        return x;
    }

    /** Sets the X value to the parametered double.
	 * @param x A double representing the X co-ordinate.
	 */
    public void setX(double x) {
        this.x = x;
    }

    /** Gets the Y value of the Player.
	 * @return A double representing the Y co-ordinate.
	 */
    public double getY() {
        return y;
    }

    /** Sets the Y value to the parametered double.
	 * @param y A double representing the Y co-ordinate.
	 */
    public void setY(double y) {
        this.y = y;
    }

    /** Gets the dx value of the Player.
	 * @return A double representing the dx value (change in x).
	 */
    public double getDx() {
        return dx;
    }

    /** Sets the dx value of the Player.
	 * @param dx A double representing the dx value (change in x).
	 */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /** Gets the dy value of the Player.
	 * @return A double representing the dy value (change in y).
	 */
    public double getDy() {
        return dy;
    }

    /** Sets the dy value of the Player.
	 * @param dy A double representing the dy value (change in y).
	 */
    public void setDy(double dy) {
        this.dy = dy;
    }


    /** Gets the image name of the Player.
	 * @return A string representing the file of reference to the image of the Player.
	 */
    public Image getImage() {
        return image;
    }

    /** Sets the image name of the Player to the parameterd string.
	 * @param imageName A string representing the file of reference to the image of the Player.
     * @throws IOException 
	 */
    public void setImageName(InputStream fileLocation) throws IOException {
        image = new Image(fileLocation);
    }
    
    /** Gets the width of the Player image.
	 * @return A double representing the Player image width.
	 */
    public double getWidth() {
        return this.spriteWidth;
    }
    
    /** Gets the height of the Player image.
	 * @return A double representing the Player image height.
	 */
    public double getHeight() {
        return this.spriteHeight;
    }
    

    

    /** Collision method to detect if this object collides with another.
	 * @param otherX A double representing another items X value.
	 * @param otherY A double representing another items Y value.
	 * @param otherWidth A double representing another items width value.
	 * @param otherHeight A double representing another items height value.
	 * @return Returns true if it collides, else it's false.
	 */
    public boolean collision(double otherX, double otherY ,double otherWidth, double otherHeight) {
    	if (this.x <= (otherX + otherWidth * 1.7) && otherX <= (this.x + spriteWidth * 1.7)) {
    		   if (otherY <= (this.y + spriteHeight) && this.y <= (otherY + otherHeight)) {
    				return true;
    		   }
    		}
    		
    	return false;
    }
    
    
    /** Method to update x and y position of Player, and display onto screen based off WASD input.
	 * @param input An arraylist of strings that contain user input, depending on the input the Player will move.
	 */
    public void move(ArrayList<String> input, String left, String up, String right, int orientation, String action, String collisionType) {
    	this.dx = 0;
    	this.dy = 0;
    	
    
    	if (action != "crouch") {
			if (orientation == 1) {
		    	if (input.contains(left) && !(this.x <= 0) ){
		    		this.dx = -this.speed;
		    	}
		    	
		    	if (input.contains(right) && !(this.x >= this.gameCanvas.getWidth() - Math.abs(175 - 10))){
		 		   	this.dx = this.speed;
		 		}
	    	} else {
	    		if (input.contains(left) && !(this.x <= 175) ){
		    		this.dx = -this.speed;
		    	}
		    	
		    	if (input.contains(right) && !(this.x >= this.gameCanvas.getWidth() - 10)){
		 		   	this.dx = this.speed;
		 		}
		    }
    
    	

    	
    	if (player.equals("reptile")) {
	    	if (input.contains(up) || (this.y < this.gameCanvas.getHeight() - 350 - 78) ){
	    		this.velocity += this.acceleration;
	    		if (!(this.velocity >= 18) ) {
		    		this.dy -= velocity;
	    		} else {
	    			this.dy += velocity;
	    		}
	 		}
	    	
	    	
	    	if (this.y > this.gameCanvas.getHeight() - 350 - 78) {
	    		this.velocity = 0;
	    		this.y = this.gameCanvas.getHeight() - 350 - 78;
	    	}
	
    	} else if (player.equals("goku")) {
    		if (input.contains(up) || (this.y < this.gameCanvas.getHeight() - 340) ){
	    		this.velocity += this.acceleration;
	    		if (!(this.velocity >= 18) ) {
		    		this.dy -= velocity;
	    		} else {
	    			this.dy += velocity;
	    		}
	 		}
	    	
	    	
	    	if (this.y > this.gameCanvas.getHeight() - 340) {
	    		this.velocity = 0;
	    		this.y = this.gameCanvas.getHeight() - 340;
	
	    	}
	    
	    	} else {
	    		if (input.contains(up) || (this.y < this.gameCanvas.getHeight() - 350 - 43) ){
		    		this.velocity += this.acceleration;
		    		if (!(this.velocity >= 24) ) {
			    		this.dy -= velocity;
		    		} else {
		    			this.dy += velocity;
		    		}
		 		}
		    	
		    	
		    	if (this.y > this.gameCanvas.getHeight() - 350 - 43) {
		    		this.velocity = 0;
		    		this.y = this.gameCanvas.getHeight() - 350 - 43;
		
		    	}
	    	}
    	
    	}
    	

    	
    	if(input.contains(left) && input.contains(right)) {
    		this.dx = 0;
    	}

    	
    	if(collisionType.equals("fromLeft") && this.dx > 0) {
    		this.dx = 0;
    	}
    	
    	if(collisionType.equals("fromRight") && this.dx < 0) {
    		this.dx = 0;
    	}
    	
              
        this.x += this.dx;
        this.y += this.dy;
        
        if (left == "" && right == "") {
        	slide = 0;
        	frameCounter = 0;
        }
        
    	
        
        
        if (player.equals("reptile")) {
	        //IDLE
	        if (action == "idle") {
	        	this.image = this.idleImage;
		        frameCounter += 1;
		        
		        spriteWidth = (285/6) * 3.5;
		        spriteHeight = 107 * 3.5;		
		        		
		        this.gc.drawImage(this.image,0 + (285/6) * slide,0, (285/6), 107, this.x, this.y, spriteWidth * orientation, spriteHeight);
		        
		        if (frameCounter == 10) {
		        	slide += 1;
		        	frameCounter = 0;
		        }
		        
		        if (slide >= 6) {
		        	slide = 0;
		        }
	        }
	        
	        
	        //WALK
	        if (action == "walk") {
	        	this.image = this.walkImage;
		        frameCounter += 1;
		        
		        spriteWidth = (486/9) * 3.5;
		        spriteHeight = 112 * 3.5;	
		        
		        this.gc.drawImage(this.image,0 + (468/9) * slide,0, (468/9), 112, this.x, this.y, spriteWidth * orientation, spriteHeight);
		        
		        if (frameCounter >= 10) {
		        	slide += 1;
		        	frameCounter = 0;
		        }
		        
		        if (slide >= 9) {
		        	slide = 0;
		        }
	        }
	        
	        
	        //JUMP
	        if (action == "jump") {
	        	this.image = this.jumpImage;
		        frameCounter += 1;
		        
		        spriteWidth = (313/7) * 3.5;
		        spriteHeight = 55 * 3.5;	
		        
		        this.gc.drawImage(this.image,0 + (((313/7)) * slide) + 2,0, (313/7), 55, this.x, this.y, spriteWidth * orientation, spriteHeight);
		        if (frameCounter >= 10) {
		        	slide += 1;
		        	frameCounter = 0;
		        }
		        
		        if (slide >= 7) {
		        	frameCounter = 0;
		        	slide = 0;
		        }
	        }
	        
	        
	        //PUNCH
	        if (action == "attack") {
	        	this.image = this.punchImage;
		        frameCounter += 1;
		        
		        spriteWidth = (169/3) * 3.5;
		        spriteHeight = 111 * 3.5;	
		        
		        if (slide == 1) {
		        	varyingSlideWidth = 50;
		        	totalSlideWidth = 0;
		        }
		        
		        if (slide == 2) {
		        	varyingSlideWidth = 65;
		        	totalSlideWidth = 40;
		        }
		        
		        if (slide == 3) {
		        	varyingSlideWidth = 74;
		        	totalSlideWidth  = 92;
		        }

		        this.gc.drawImage(this.image,0 + varyingSlideWidth + totalSlideWidth,0, varyingSlideWidth, 111, this.x, this.y, spriteWidth * orientation, spriteHeight);
		        
		        
		        if (frameCounter >= 7) {
		        	slide += 1;
		        	frameCounter = 0;
		        }
		        
		        if (slide >= 3) {
		        	frameCounter = 0;
		        	slide = 1;
		        }
	        }
	        
	        //HURT
	        if (action == "hurt") {
	        	this.image = this.hurtImage;
		        frameCounter += 1;
		        
		        spriteWidth = (107/2) * 3.5;
		        spriteHeight = 105 * 3.5;	
		        
		        this.gc.drawImage(this.image,0 + (((107/2)) * slide),0, (107/2), 105, this.x, this.y, spriteWidth * orientation, spriteHeight);
		        if (frameCounter >= 10) {
		        	slide += 1;
		        	frameCounter = 0;
		        }
		        
		        if (slide >= 2) {
		        	frameCounter = 0;
		        	slide = 0;
		        }
	        }
        
	        
	      //CROUCH
	        if (action == "crouch") {
	        	this.image = this.crouchImage;
		        frameCounter += 1;
		        
		        spriteWidth = (103/2) * 3.5;
		        spriteHeight = 80 * 3.5;	
		        
		        this.gc.drawImage(this.image,0 + (((103/2)) * slide),0, (103/2), 80, this.x, this.y + 90, spriteWidth * orientation, spriteHeight);
		        if (frameCounter >= 10) {
		        	slide += 1;
		        	frameCounter = 0;
		        }
		        
		        if (slide >= 2) {
		        	frameCounter = 0;
		        	slide = 1;
		        }
	        }
	        
	      //POWER
	        if (action == "power") {
	        	this.image = null;

		        
		        this.gc.drawImage(this.image,0 + (((103/2)) * slide),0, (103/2), 80, this.x, this.y + 90, spriteWidth * orientation, spriteHeight);

	        }
        }
        
        
        
        if (player.equals("goku")) {
	        //IDLE
	        if (action == "idle") {
	        	
	        	spriteWidth = (220/4) * 5;
			    spriteHeight = 55 * 5;	
	        	
	        	this.image = this.idleImage;
		        frameCounter += 1;
		        this.gc.drawImage(this.image,0 + (220/4) * slide,0, (220/4), 55, this.x, this.y, spriteWidth * orientation, spriteHeight);
		        
		        if (frameCounter >= 10) {
		        	slide += 1;
		        	frameCounter = 0;
		        }
		        
		        if (slide >= 4 ) {
		        	frameCounter = 0;
		        	slide = 0;
		        }
	        }
	        
	        
	        //WALK
	        if (action == "walk") {
	        	this.image = this.walkImage;
	        	
	        	spriteWidth = (248/4) * 5;
			    spriteHeight = 44 * 5;	
	        	
		        frameCounter += 1;
		        this.gc.drawImage(this.image,0 + (248/4) * slide,0, (248/4), 44, this.x, this.y, spriteWidth * orientation,  spriteHeight);
		        
		        if (frameCounter >= 10) {
		        	slide += 1;
		        	frameCounter = 0;
		        }
		        
		        if (slide >= 4) {
		        	frameCounter = 0;
		        	slide = 0;
		        }
	        }
	        
	        
	        //JUMP
	        if (action == "jump") {
	        	this.image = this.jumpImage;
	        	
	        	spriteWidth = (361/7) * 5;
			    spriteHeight = 67 * 5;	
	        	
		        frameCounter += 1;
		        this.gc.drawImage(this.image,0 + (361/7) * slide,0, (361/7), 67, this.x, this.y, spriteWidth * orientation, spriteHeight);
		        
		        if (frameCounter >= 10) {
		        	slide += 1;
		        	frameCounter = 0;
		        }
		        
		        if (slide >= 7) {
		        	frameCounter = 0;
		        	slide = 0;
		        }
	        }
	        
	        //PUNCH
	        if (action == "attack") {
	        	this.image = this.punchImage;
	        	
	        	spriteWidth = (352/6) * 5;
			    spriteHeight = 57 * 5;	
	        	
		        frameCounter += 1;
		        
		        if (slide == 1) {
		        	varyingSlideWidth = 51;
		        	totalSlideWidth = 0;
		        }
		        
		        if (slide == 2) {
		        	varyingSlideWidth = 52;
		        	totalSlideWidth = 51;
		        }
		        
		        if (slide == 3) {
		        	varyingSlideWidth = 64;
		        	totalSlideWidth  = 97;
		        }
		        
		        if (slide == 4) {
		        	varyingSlideWidth = 75;
		        	totalSlideWidth = 155;
		        }
		        
		        if (slide == 5) {
		        	varyingSlideWidth = 73;
		        	totalSlideWidth = 230;
		        }
		        
		        if (slide == 6) {
		        	varyingSlideWidth = 49;
		        	totalSlideWidth  = 303;
		        }

		        this.gc.drawImage(this.image,0 + varyingSlideWidth + totalSlideWidth,0, varyingSlideWidth, 57,this.x, this.y, spriteWidth * orientation, spriteHeight);
		        
		        
		        if (frameCounter >= 7) {
		        	slide += 1;
		        	frameCounter = 0;
		        }
		        
		        if (slide >=6) {
		        	frameCounter = 0;
		        	slide = 1;
		        }
	        }
	        
	        //HURT
	        if (action == "hurt") {
	        	this.image = this.hurtImage;
		        frameCounter += 1;
		        
		        spriteWidth = (147/3) * 5;
		        spriteHeight = 62 * 5;	
		        
		        this.gc.drawImage(this.image,0 + (((147/3)) * slide),0, (147/3), 62, this.x, this.y, spriteWidth * orientation, spriteHeight);
		        if (frameCounter >= 10) {
		        	slide += 1;
		        	frameCounter = 0;
		        }
		        
		        if (slide >= 3) {
		        	frameCounter = 0;
		        	slide = 0;
		        }
	        }
	        
	        
	        
	        
	        //CROUCH
	        if (action == "crouch") {
	        	this.image = this.crouchImage;
		        frameCounter += 1;
		        
		        spriteWidth = (291/6) * 5;
		        spriteHeight = 50 * 5;	
		        
		        this.gc.drawImage(this.image,0 + (((291/6)) * slide) + 4,0, (291/6), 80, this.x, this.y + 90, spriteWidth * orientation, spriteHeight);
		        if (frameCounter >= 10) {
		        	slide += 1;
		        	frameCounter = 0;
		        }
		        
		        if (slide >= 6) {
		        	frameCounter = 0;
		        	slide = 5;
		        }
	        }
	        
	        
	        //CROUCH
	        if (action == "power") {
	        	this.image = this.powerImage;
		        frameCounter += 1;
		        
		        spriteWidth = (543/7) * 5;
		        spriteHeight = 91 * 5;	
		        
		        if (slide == 1) {
		        	varyingSlideWidth = 53;
		        	totalSlideWidth = 0;
		        }
		        
		        if (slide == 2) {
		        	varyingSlideWidth = 67;
		        	totalSlideWidth = 47;
		        }
		        
		        if (slide == 3) {
		        	varyingSlideWidth = 89;
		        	totalSlideWidth  = 100;
		        }
		        
		        if (slide == 4) {
		        	varyingSlideWidth = 93;
		        	totalSlideWidth = 187;
		        }
		        
		        if (slide == 5) {
		        	varyingSlideWidth = 101;
		        	totalSlideWidth = 280;
		        }
		        
		        if (slide == 6) {
		        	varyingSlideWidth = 107;
		        	totalSlideWidth  = 381;
		        }
		        
		        if (slide == 7) {
		        	varyingSlideWidth = 55;
		        	totalSlideWidth  = 488;
		        }

		        this.gc.drawImage(this.image,0 + varyingSlideWidth + totalSlideWidth,0, varyingSlideWidth, 91,this.x, this.y - 170, spriteWidth * orientation, spriteHeight);
		        
		        
		        if (frameCounter >= 7) {
		        	slide += 1;
		        	frameCounter = 0;
		        }
		        
		        if (slide >=7) {
		        	frameCounter = 0;
		        	slide = 1;
		        }
	        }
	        
        
        }
        
        
        if (player.equals("thanos")) {
	        //IDLE
	        if (action == "idle") {
	        	this.image = this.idleImage;
	        	
	        	spriteWidth = (1265/8) * 2.15;
			    spriteHeight = 157 * 2.15;	
	        	
		        frameCounter += 1;
		        this.gc.drawImage(this.image,0 + (1265/8) * slide,0, (1265/8), 157, this.x, this.y + 10, spriteWidth * orientation, spriteHeight);
		        
		        if (frameCounter >= 10) {
		        	slide += 1;
		        	frameCounter = 0;
		        }
		        
		        if (slide >= 8) {
		        	frameCounter = 0;
		        	slide = 0;
		        }
	        }
	        
	        
	        //WALK
	        if (action == "walk") {
	        	this.image = this.walkImage;
	        	
	        	spriteWidth = (1373/10) * 2.15;
			    spriteHeight = 141 * 2.15;	
	        	
		        frameCounter += 1;
		        
		        if (slide == 1) {
		        	varyingSlideWidth = 145;
		        	totalSlideWidth = 0;
		        }
		        
		        if (slide == 2) {
		        	varyingSlideWidth = 160;
		        	totalSlideWidth = 145;
		        }
		        
		        if (slide == 3) {
		        	varyingSlideWidth = 130;
		        	totalSlideWidth  = 305;
		        }
		        
		        if (slide == 4) {
		        	varyingSlideWidth = 111;
		        	totalSlideWidth = 444;
		        }
		        
		        if (slide == 5) {
		        	varyingSlideWidth = 139;
		        	totalSlideWidth = 555;
		        }
		        
		        if (slide == 6) {
		        	varyingSlideWidth = 144;
		        	totalSlideWidth = 694;
		        }
		        
		        if (slide == 7) {
		        	varyingSlideWidth = 143;
		        	totalSlideWidth = 838;
		        }
		        
		        if (slide == 8) {
		        	varyingSlideWidth = 143;
		        	totalSlideWidth = 981;
		        }
		        if (slide == 9) {
		        	varyingSlideWidth = 122;
		        	totalSlideWidth = 1124;
		        }
		        
		        if (slide == 10) {
		        	varyingSlideWidth = 127;
		        	totalSlideWidth = 1246;
		        }

		        this.gc.drawImage(this.image,0 + varyingSlideWidth + totalSlideWidth,0, varyingSlideWidth, 141, this.x, this.y + 10, spriteWidth * orientation, spriteHeight);
		        
		        
		        if (frameCounter >= 10) {
		        	slide += 1;
		        	frameCounter = 0;
		        }
		        
		        if (slide >= 10) {
		        	frameCounter = 0;
		        	slide = 1;
		        }
	        }
	        
	        
	        //JUMP
	        if (action == "jump") {
	        	this.image = this.jumpImage;
	        	
	        	spriteWidth = (1556/11) * 2.15;
			    spriteHeight = 174 * 2.15;	
	        	
		        frameCounter += 1;
		        this.gc.drawImage(this.image,0 + (1556/11) * slide,0, (1556/11), 174, this.x, this.y + 10, spriteWidth * orientation, spriteHeight);
		        
		        if (frameCounter >= 10) {
		        	slide += 1;
		        	frameCounter = 0;
		        }
		        
		        if (slide >= 11) {
		        	frameCounter = 0;
		        	slide = 0;
		        }
	        }

	        
	        //PUNCH
	        if (action == "attack") {
	        	this.image = this.punchImage;
	        	
	        	spriteWidth = (1526/8) * 2.15;
			    spriteHeight = 159 * 2.15;	
	        	
		        frameCounter += 1;
		        

		        
		        if (slide == 1) {
		        	varyingSlideWidth = 170;
		        	totalSlideWidth = 0;
		        }
		        
		        if (slide == 2) {
		        	varyingSlideWidth = 215;
		        	totalSlideWidth = 150;
		        }
		        
		        if (slide == 3) {
		        	varyingSlideWidth = 215;
		        	totalSlideWidth  = 365;
		        }
		        
		        if (slide == 4) {
		        	varyingSlideWidth = 232;
		        	totalSlideWidth = 580;
		        }
		        
		        if (slide == 5) {
		        	varyingSlideWidth = 180;
		        	totalSlideWidth = 830;
		        }
		        
		        if (slide == 6) {
		        	varyingSlideWidth = 160;
		        	totalSlideWidth = 1040;
		        }
		        
		        if (slide == 7) {
		        	varyingSlideWidth = 165;
		        	totalSlideWidth = 1200;
		        }
		        
		        if (slide == 8) {
		        	varyingSlideWidth = 161;
		        	totalSlideWidth = 1365;
		        }

		        this.gc.drawImage(this.image,0 + varyingSlideWidth + totalSlideWidth,0, varyingSlideWidth, 159, this.x, this.y + 10, spriteWidth * orientation, spriteHeight);
		        
		        
		        if (frameCounter >= 7) {
		        	slide += 1;
		        	frameCounter = 0;
		        }
		        
		        if (slide >= 8) {
		        	frameCounter = 0;
		        	slide = 1;
		        }
	        }
	        
	      //HURT
	        if (action == "hurt") {
	        	this.image = this.hurtImage;
		        frameCounter += 1;
		        
		        spriteWidth = (528/3) * 2.15;
		        spriteHeight = 140 * 2.15;	
		        
		        this.gc.drawImage(this.image,0 + (((528/3)) * slide),0, (528/3), 140, this.x, this.y + 10, spriteWidth * orientation, spriteHeight);
		        if (frameCounter >= 10) {
		        	slide += 1;
		        	frameCounter = 0;
		        }
		        
		        if (slide >= 3) {
		        	frameCounter = 0;
		        	slide = 0;
		        }
	        }
	        
	        
	      //CROUCH
	        if (action == "crouch") {
	        	this.image = this.crouchImage;
		        frameCounter += 1;
		        
		        spriteWidth = (283/2) * 2.15;
		        spriteHeight = 144 * 2.15;	
		        
		        this.gc.drawImage(this.image,0 + (((283/2)) * slide),0, (283/2), 144, this.x, this.y + 10, spriteWidth * orientation, spriteHeight);
		        if (frameCounter >= 10) {
		        	slide += 1;
		        	frameCounter = 0;
		        }
		        
		        if (slide >= 2) {
		        	frameCounter = 0;
		        	slide = 1;
		        }
	        }
	        
	      //CROUCH
	        if (action == "power") {
	        	this.image = this.powerImage;
		        frameCounter += 1;
		        
		        spriteWidth = (495/3) * 2.15;
		        spriteHeight = 188 * 2.15;	
		        
		        this.gc.drawImage(this.image,0 + (((495/3)) * slide),0, (495/3), 188, this.x, this.y - 50, spriteWidth * orientation, spriteHeight);
		        if (frameCounter >= 16) {
		        	slide += 1;
		        	frameCounter = 0;
		        }
		        
		        if (slide >= 3) {
		        	frameCounter = 0;
		        	slide = 2;
		        }
	        }
	        
        
        }
        
        if (player.equals("batman")) {
	        //IDLE
	        if (action == "idle") {
	        	this.image = this.idleImage;
		        frameCounter += 1;
		        
		        spriteWidth = (244/4) * 3.5;
		        spriteHeight = 99 * 3.5;		
		        		
		        this.gc.drawImage(this.image,0 + (244/4) * slide,0, (244/4), 99, this.x, this.y, spriteWidth * orientation, spriteHeight);
		        
		        if (frameCounter == 10) {
		        	slide += 1;
		        	frameCounter = 0;
		        }
		        
		        if (slide >= 4) {
		        	slide = 0;
		        }
	        }
	        
	        
	        //WALK
	        if (action == "walk") {
	        	this.image = this.walkImage;
		        frameCounter += 1;
		        
		        spriteWidth = (355/5) * 3.5;
		        spriteHeight = 100 * 3.5;	
		        
		        this.gc.drawImage(this.image,0 + ((355/5) * slide) + 4,0, (355/5), 100, this.x, this.y, spriteWidth * orientation, spriteHeight);
		        
		        if (frameCounter >= 10) {
		        	slide += 1;
		        	frameCounter = 0;
		        }
		        
		        if (slide >= 5) {
		        	slide = 0;
		        }
	        }
	        
	        
	        //JUMP
	        if (action == "jump") {
	        	this.image = this.jumpImage;
		        frameCounter += 1;
		        
		        spriteWidth = (256/4) * 3.5;
		        spriteHeight = 135 * 3.5;	
		        
		        this.gc.drawImage(this.image,0 + (256/4) * slide,0, (256/4), 135, this.x, this.y, spriteWidth * orientation, spriteHeight);
		        if (frameCounter >= 10) {
		        	slide += 1;
		        	frameCounter = 0;
		        }
		        
		        if (slide >= 4) {
		        	frameCounter = 0;
		        	slide = 0;
		        }
	        }
	        
	        
	        //PUNCH
	        if (action == "attack") {
	        	this.image = this.punchImage;
		        frameCounter += 1;
		        
		        spriteWidth = (246/3) * 3.5;
		        spriteHeight = 98 * 3.5;	
		        
		        if (slide == 1) {
		        	varyingSlideWidth = 70;
		        	totalSlideWidth = 0;
		        }
		        
		        if (slide == 2) {
		        	varyingSlideWidth = 106;
		        	totalSlideWidth = 41;
		        }
		        
		        if (slide == 3) {
		        	varyingSlideWidth = 116;
		        	totalSlideWidth  = 130;
		        }

		        this.gc.drawImage(this.image,0 + varyingSlideWidth + totalSlideWidth,0, varyingSlideWidth, 98, this.x, this.y, spriteWidth * orientation, spriteHeight);
		        
		        
		        if (frameCounter >= 7) {
		        	slide += 1;
		        	frameCounter = 0;
		        }
		        
		        if (slide >= 3) {
		        	frameCounter = 0;
		        	slide = 1;
		        }
	        }
	        
	      //HURT
	        if (action == "hurt") {
	        	this.image = this.hurtImage;
		        frameCounter += 1;
		        
		        spriteWidth = (218/3) * 3.5;
		        spriteHeight = 93 * 3.5;	
		        
		        this.gc.drawImage(this.image,0 + (((218/3)) * slide),0, (218/3), 93, this.x, this.y, spriteWidth * orientation, spriteHeight);
		        if (frameCounter >= 10) {
		        	slide += 1;
		        	frameCounter = 0;
		        }
		        
		        if (slide >= 3) {
		        	frameCounter = 0;
		        	slide = 0;
		        }
	        }
        
	      //CROUCH
	        if (action == "crouch") {
	        	this.image = this.crouchImage;
		        frameCounter += 1;
		        
		        spriteWidth = (143/2) * 3.5;
		        spriteHeight = 87 * 3.5;	
		        
		        this.gc.drawImage(this.image,0 + (((143/2)) * slide),0, (143/2), 87, this.x, this.y + 40, spriteWidth * orientation, spriteHeight);
		        if (frameCounter >= 10) {
		        	slide += 1;
		        	frameCounter = 0;
		        }
		        
		        if (slide >= 2) {
		        	frameCounter = 0;
		        	slide = 1;
		        }
	        }
	        
        }
        
    }
}
