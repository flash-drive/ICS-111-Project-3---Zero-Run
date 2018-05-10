import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class spriteCoins {

	public EZImage spriteSheet;

	public int x = 0; // Position of Sprite
	int y = 0;
	int spriteWidth; // Width of each sprite
	int spriteHeight; // Height of each sprite
	int direction = 0; // Direction character is walking in
	int walkSequence = 0; // Walk sequence counter
	int cycleSteps; // Number of steps before cycling to next animation step
	int counter = 0; // Cycle counter

	// initializes the sprite sheet
	spriteCoins(String imgFile, int startX, int startY, int width, int height, int steps) {
		x = startX;
		y = startY;
		spriteWidth = width;
		spriteHeight = height;
		cycleSteps = steps;
		spriteSheet = EZ.addImage(imgFile, x, y);
		setImagePosition();
	}

	public void setCoinImagePosition(double posx, double posy) {
		spriteSheet.translateTo(posx, posy);
	}
	
	
	// Sets position of the sprite sheet
	public void setImagePosition() {
		spriteSheet.translateTo(x, y);

		spriteSheet.setFocus(walkSequence * spriteWidth, direction, walkSequence * spriteWidth + spriteWidth,
				direction + spriteHeight);
	}

	// Sprite sheet will read from left to right
	// keeps the coins stationnary
	public void moveRight(int stepSize) {
		// x = x + stepSize;
		direction = spriteHeight * 0;

		if ((counter % cycleSteps) == 0) {
			walkSequence++;
			if (walkSequence > 9)
				walkSequence = 0;
		}

		counter++;
		setImagePosition();

	}

	// Go function that will be used in other functions
	public void go() {
		// grabbed from the moveRight function
		moveRight(2);

	}

	// Position of the coins
	public int getXCenter() {
		return (int) x;
	}
	public int getYCenter() {
		return (int) y;
	}

}
