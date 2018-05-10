import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

public class Player {
	
	EZImage spriteSheet;	//Declares EZImage called spriteSheet
	
	int x = 0;				// x position of Sprite
	int y = 0;				// y position of Sprite
	int spriteWidth;		// Width of each sprite
	int spriteHeight;		// Height of each sprite
	int direction = 0;		// Direction character is walking in
	int walkSequence = 0;	// Walk sequence counter
	int cycleSteps;			// Number of steps before cycling to next animation step
	int counter = 0;		// Cycle counter
	int jumpCounter = 0;
	
	int playerState = STAND;// Set player state to STAND
	EZImage zeroStand;		// Declares EZImage called zeroStand
	EZImage zeroJumpR;		// Declares EZImage called zeroJump
	EZImage zeroJumpL;
	EZImage zeroLand;		// Declares EZImage called zeroLand
	EZRectangle test;
	
	static final int STAND = 1; 			// Create static final int called STAND, set to 1
	static final int JUMPR = 2;				// Create static final int called JUMPR, set to 2
	static final int JUMPL = 3;				// Create static final int called JUMPL, set to 3
	static final int LAND = 4;				// Create static final int called LAND, set to 4
	static final int HOVER = 5;				// Create static final int called HOVER, set to 5
	static final int JUMPHEIGHT = 120; 		// Create static final int called JUMPHEIGHT, set to 120
	
	Player(String imgFile, int startX, int startY, int width, int height, int steps) {
		 super();
		
		x = startX; //set int x to startX
		y = startY;	//set int y to startY
		spriteWidth = width; //set spriteWidth to width
		spriteHeight = height; //set spriteHeight to height
		cycleSteps = steps; //sets cycleSteps
		spriteSheet = EZ.addImage(imgFile, startX, y); //creates spriteSheet image for animations
		zeroJumpR = EZ.addImage("zeroJump.png", startX, startY);	//Jump picture
		zeroJumpR.hide();	//hides (loaded) jump picture
		zeroLand = EZ.addImage("zeroLand.png", startX, startY);	//Land picture
		zeroLand.hide();	//hides (loaded) land picture
		setImagePosition();	//movement and animation function
		spriteSheet.scaleTo(1.6);
		zeroLand.scaleTo(1.5);
		zeroJumpR.scaleTo(1.5);
		test = EZ.addRectangle(900, 400, 800, 10, Color.RED, true);
	}
	
	void processStates() { //function for states of player
		switch(playerState) {	//switch statement
		
		case STAND:									//when in STAND, can:
			movePlayer();							//move player left and right
			if (((EZInteraction.isKeyDown('d') || EZInteraction.isKeyDown('a')) && EZInteraction.isKeyDown('w')) || EZInteraction.isKeyDown('w')) {		//jump, land						
				playerState = JUMPR;	//sets playerState to JUMP
				jumpCounter = 0;	//sets jumpCounter to 0
				//jumpRight();
			}
			if (y + 15 < test.getYCenter() ) {
				y += 13;
				setImagePosition();
			}
			
			break;	
		case JUMPR: 									//when in JUMP:
			//Jump(1);
			jumpCounter += 7;						//increments jumpCounter by 7 until it hits/passes JUMPHEIGHT(120)
			movePlayer();							//move player left and right
			
			zeroJumpR.show();						//show zeroJump image
			spriteSheet.hide();						//hide spriteSheet image(s)
			
			if (jumpCounter > JUMPHEIGHT) {			//condition for "limiting" player's jump
				playerState = LAND;	//set playerState to LAND	
			} else { 								//continue to "jump" higher
			y -= 13;									//by 7
			setImagePosition();						//movement and animation function
			}

			break;
		case LAND:									//when in LAND:
			//jumpRight();
			jumpCounter -= 7;						//decrements jumpCounter by 7 until it's <= 0
			movePlayer();							//move player left/right
			
			zeroJumpR.hide();						//hide zeroJump image
			zeroLand.show();						//show zeroLand image
			
			if (jumpCounter <= 0) {					//when jumpCounter <= 0,
				playerState = STAND;					//playerState is set to STAND
				zeroLand.hide();						//hide zeroLand image				
				spriteSheet.show();						//show spriteSheet image
				setImagePosition();
			} else {								//otherwise 
				y += 13;									//increment(land) by increments of 7
				setImagePosition();
			}
		}
	}

	private void setImagePosition() {	//movement/animation function
		spriteSheet.translateTo(x, y);	//move spriteSheet,
		zeroJumpR.translateTo(x, y);		//zeroJump,
		zeroLand.translateTo(x, y);		//zeroLand to wherever x and y are.
		spriteSheet.setFocus(walkSequence * spriteWidth, direction,	//focuses on parts of spriteSheet
				walkSequence * spriteWidth + spriteWidth, direction	//to simulate animation of running
						+ spriteHeight);
	}
	
	public void moveRight(int stepSize) {	//function for running right
		//x = x + stepSize;					//move player right by stepSize (4)
		direction = 0;						//which part of spriteSheet image(first row)
		
		if ((counter % cycleSteps) == 0) {	
			walkSequence++;					//read spriteSheet from left to right
			if (walkSequence > 14)			//if reach rightmost image,
				//walkSequence = 0;
				walkSequence = 1;			//go back to first image
		}
		
		counter--;							//decrement counter by 1
		setImagePosition();					//move image(s) to x, y position
	}
	
	public void moveLeft(int stepSize) {	//function for running left
		//x = x - stepSize;					//move player left by stepSize (4)
		direction = spriteHeight;			//which part of spriteSheet image(second row)

		if ((counter % cycleSteps) == 0) {
			walkSequence--;					//read spriteSheet from right to left focus
			if (walkSequence < 0)			//if reach leftmost image,
				walkSequence = 13;			//go back to rightmost image
		}
		counter++;							//increment counter by 1
		setImagePosition();					//move image(s) to x, y position
	}

	public void jumpRight() {				
		direction = spriteHeight * 2;
		
		if ((counter % cycleSteps) == 0) {
			walkSequence++;
			if (walkSequence > 9)
				walkSequence = 0;
		}
		counter--;
	}
	
	public void jumpLeft() {
		direction = spriteHeight * 3;
		
		if ((counter % cycleSteps) == 0) {
			walkSequence--;
			if (walkSequence < 0)
				walkSequence = 9;
		}
	}

	// Keyboard controls for moving the character.
	public void movePlayer() {
		if (EZInteraction.isKeyDown('a')) {	//press/hold a: move left
			moveLeft(8);
			if (EZInteraction.wasKeyReleased('w')) {
				//jumpLeft();
			}
		}
		
		if (EZInteraction.isKeyDown('d')) { //press/hold d: move right
			moveRight(8);
		}
			if (EZInteraction.wasKeyReleased('w')) {
				//jumpRight();
			}
		
	}
	
	public void translateBy(double posx, double posy) {
		spriteSheet.translateTo(posx, posy);
	}

}