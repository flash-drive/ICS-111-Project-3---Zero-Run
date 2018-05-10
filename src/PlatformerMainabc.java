import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class PlatformerMainabc {
	
	public static EZImage back;

	
	public static void main(String[] args) throws java.io.IOException {
				
		// init EZ
		EZ.initialize(950, 460);
		//loadingScreen
		back = EZ.addImage("Back1.png",475,230);
		EZText r = EZ.addText(475, 230, "Press j to Start", Color.WHITE, 100);
		back.pullToFront();
		r.pullToFront();
		
		if(EZInteraction.wasKeyReleased('j')){
			EZ.removeEZElement(back);
			EZ.removeEZElement(r);
		}

		// container that will hold all EZ elements
		EZGroup root = EZ.addGroup();

		EZSound cSound = EZ.addSound("coinsounds.wav");
		EZSound music = EZ.addSound("guile.wav");
		music.loop();

		////////////////////////////////
		// Implement filescanner and filewriter to read from level text
		Scanner fileScanner = new Scanner(new FileReader("level.txt"));
		FileWriter fw = new FileWriter("cleanlevel.txt");

		int width = fileScanner.nextInt();
		int height = fileScanner.nextInt();

		fw.write(width);
		fw.write(height);
		String inputText = fileScanner.nextLine();
		EZImage back = EZ.addImage("Back2.png", width * 7, height); // implement
																	// background
		ArrayList<EZImage> tiles = new ArrayList<EZImage>();
		////////////////////////////////

		int scores = -10;
		EZText score = EZ.addText(150, 50, "Score  " + scores, Color.WHITE); // implement
																				// score
																				// board
		score.fontSize = 50;
		Random randomGenerator = new Random();
		ArrayList<spriteCoins> coins = new ArrayList<spriteCoins>(); // arraylist
																		// for
																		// the
																		// coins
																		// being
																		// stored
		for (int i = 0; i < 10; i++) {
			coins.add(new spriteCoins("coinsprite3.png", -50, 390, 44, 44, 4)); // position
																				// of
																				// the
																				// coins
		}

		Random randomPos = new Random();
		/////////////////////////////////////////////////////////////

		ArrayList<Enemy1> Enemy = new ArrayList<Enemy1>();

		Enemy.add(new Enemy1("Danny.png", 600, 370));
		Enemy.add(new Enemy1("Danny.png", 1600, 370));
		Enemy.add(new Enemy1("Danny.png", 2100, 370));
		Enemy.add(new Enemy1("Danny.png", 3000, 370));
		Enemy.add(new Enemy1("Danny.png", 3600, 370));
		Enemy.add(new Enemy1("Danny.png", 3800, 370));
		Enemy.add(new Enemy1("Danny.png", 4400, 370));
		Enemy.add(new Enemy1("Danny.png", 4600, 370));
		Enemy.add(new Enemy1("Danny.png", 5200, 370));
		Enemy.add(new Enemy1("Danny.png", 5700, 175));
		Enemy.add(new Enemy1("Danny.png", 6400, 370));
		Enemy.add(new Enemy1("Danny.png", 7000, 370));
		Enemy.add(new Enemy1("Danny.png", 7500, 370));
		Enemy.add(new Enemy1("Danny.png", 7700, 370));

		ArrayList<flowerClass> flower = new ArrayList<flowerClass>();

		flower.add(new flowerClass("OpenFlower.png", 156, 370));
		flower.add(new flowerClass("OpenFlower.png", 2815, 370));
		flower.add(new flowerClass("OpenFlower.png", 6244, 370));
		flower.add(new flowerClass("OpenFlower.png", 6750, 370));
		flower.add(new flowerClass("OpenFlower.png", 7170, 370));

		int widthPic = back.getWidth();
		int heightPic = back.getHeight();

		// backdrop.setFocus(0, 0, 900, 700);
		int x = 0;
		int y = 0;
		int playerY = 385;
		////////////////////////////////////////////////////////////
		// add elements

		Player Zero = new Player("zeroSheet.png", 80, 100, 50, 80, 3);
		EZImage player = EZ.addImage("chicken.png", 80, 365);
		player.hide();

		// add elements to the root group
		root.addElement(player);
		// root.addElement(Zero);
		// setup player speed
		float velocityX = 0f;
		float velocityY = 0f;
		float friction = 0.8f;

		for (int line = 0; line < height; line++) {

			inputText = fileScanner.nextLine();
			System.out.println(inputText);

			for (int i = 0; i < inputText.length(); i++) {

				char ch = inputText.charAt(i);

				switch (ch) {

				// Square tiles

				case 'C':
					coins.add(new spriteCoins("coinsprite3.png", i * 32, line * 32, 44, 44, 4));
					break;
				case 'L':
					tiles.add(EZ.addImage("LGrass.png", i * 32, line * 32));
					break;
				case 'M':
					tiles.add(EZ.addImage("MGrass.png", i * 32, line * 32));
					break;
				case 'R':
					tiles.add(EZ.addImage("RGrass.png", i * 32, line * 32));
					break;
				case 'D':
					tiles.add(EZ.addImage("Mdirt.png", i * 32, line * 32));
					break;
				case 'Y':
					tiles.add(EZ.addImage("Rdirt.png", i * 32, line * 32));
					break;
				case 'F':
					tiles.add(EZ.addImage("Ldirt.png", i * 32, line * 32));
					break;
				// Angle tiles
				case 'H':
					tiles.add(EZ.addImage("3.png", i * 32, line * 32));
					break;
				case 'J':
					tiles.add(EZ.addImage("4.png", i * 32, line * 32));
					break;
				case 'K':
					tiles.add(EZ.addImage("boto3.png", i * 32, line * 32));
					break;
				case 'Q':
					tiles.add(EZ.addImage("boto2.png", i * 32, line * 32));
					break;
				case 'W':
					tiles.add(EZ.addImage("boto5.png", i * 32, line * 32));
					break;
				case 'E':
					tiles.add(EZ.addImage("boto6.png", i * 32, line * 32));
					break;
				case 'T':
					tiles.add(EZ.addImage("boto9.png", i * 32, line * 32));
					break;
				case 'U':
					tiles.add(EZ.addImage("pipe.png", i * 32, line * 32));
					break;
				default:
					// Do nothing
					break;

				}

			}

		}

		// for(objectname variablename : arrayname)

		for (int i = 0; i < tiles.size(); i++) {
			// if (Zero.isPointInElement(coins.get(i))) {

			// }
			root.addElement(tiles.get(i));

			// root.addElement(coins.get(0).spriteSheet);

			// root.addElement(Enemy.get(i));
			// root.addElement(coins.get(i));
		}

		for (int i = 0; i < coins.size(); i++) {
			// if (!(coins.isPointInElement(tiles.get(i).getXCenter()))) {

			root.addElement(coins.get(i).spriteSheet);

			// }
		}

		for (int i = 0; i < Enemy.size(); i++) {
			root.addElement(Enemy.get(i).DannyPict);
		}
		for (int i = 0; i < flower.size(); i++) {
			root.addElement(flower.get(i).spriteSheet);
		}

		// GROUP PULL
		root.pullToFront();
		player.pullToFront();
		Zero.spriteSheet.pullToFront();
		Zero.zeroJumpR.pullToFront();
		Zero.zeroLand.pullToFront();

		// (for EZIMAGE(name of object) new name for coins : coins)
		// for (spriteCoins coinscollision : coins) {
		//
		// // coin will be somewhere else
		//
		// // randomPos.nextInt(500));
		//
		// if (Zero.spriteSheet.isPointInElement(coinscollision.getXCenter(),
		// coinscollision.getYCenter())) {
		//
		//
		// coinscollision.setCoinImagePosition(1, 1);
		// // //coin will be somewhere else
		// // coins.translateTo(randomPos.nextInt(600),
		// // randomPos.nextInt(500));
		// //
		// //
		// //
		// // //increment the score by 1
		// // scores++;
		// //
		// // //show the score on the board
		// // score.msg="Score "+scores;
		// //
		// // //play a box sound when the play touches the replinish box
		// // cSound.play();
		// }
		//
		// }

		// main loop
		while (true) {

			int zeroX = Zero.spriteSheet.getXCenter();
			int zeroY = Zero.spriteSheet.getYCenter();

			//
			// for(int i=0; i<Enemy.size(); i++) {
			//
			// if
			// (Enemy.get(i).DannyPict.isPointInElement(Zero.spriteSheet.getXCenter()
			// - Zero.spriteSheet.getWidth()/2, Zero.spriteSheet.getYCenter() -
			// Zero.spriteSheet.getHeight() / 2) ||
			// Enemy.get(i).DannyPict.isPointInElement(Zero.spriteSheet.getXCenter()
			// + Zero.spriteSheet.getWidth()/2, Zero.spriteSheet.getYCenter() -
			// Zero.spriteSheet.getHeight() / 2)
			// ||
			// Enemy.get(i).DannyPict.isPointInElement(Zero.spriteSheet.getXCenter()
			// - Zero.spriteSheet.getWidth()/2, Zero.spriteSheet.getYCenter() +
			// Zero.spriteSheet.getHeight() / 2) ||
			// Enemy.get(i).DannyPict.isPointInElement(Zero.spriteSheet.getXCenter()
			// + Zero.spriteSheet.getWidth()/2, Zero.spriteSheet.getYCenter() +
			// Zero.spriteSheet.getHeight() / 2)) {
			//
			//
			// isGameRunning = !myZombies[i].isColliding(Zero.());
			//
			// }

			// for (spriteCoins coinscollision : coins) {
			for (int i = 0; i < coins.size(); i++) {

				// for(int i=0; i<coins.size(); i++){
				// Zero.spriteSheet.yCenter=coins.get(i).getYCenter();
				// coin will be somewhere else

				// randomPos.nextInt(500));
				// System.out.println(Zero.spriteSheet.getXCenter()+","+Zero.spriteSheet.getYCenter());
				// System.out.println(coinscollision.getXCenter()+","+coinscollision.getYCenter());
				System.out.println(coins.get(i).getXCenter() + "," + coins.get(i).getYCenter());

				System.out.println(player.getXCenter() + "," + Zero.spriteSheet.getYCenter());

				// Zero.spriteSheet.yCenter=coinscollision.spriteSheet.getYCenter();
				// if
				// (coins.get(10).spriteSheet.isPointInElement(player.getXCenter(),
				// Zero.spriteSheet.getYCenter())) {

				if (coins.get(i).spriteSheet.isPointInElement(
						Zero.spriteSheet.getXCenter() - Zero.spriteSheet.getWidth() / 2,
						Zero.spriteSheet.getYCenter() - Zero.spriteSheet.getHeight() / 2)
						|| coins.get(i).spriteSheet.isPointInElement(
								Zero.spriteSheet.getXCenter() + Zero.spriteSheet.getWidth() / 2,
								Zero.spriteSheet.getYCenter() - Zero.spriteSheet.getHeight() / 2)
						|| coins.get(i).spriteSheet.isPointInElement(
								Zero.spriteSheet.getXCenter() - Zero.spriteSheet.getWidth() / 2,
								Zero.spriteSheet.getYCenter() + Zero.spriteSheet.getHeight() / 2)
						|| coins.get(i).spriteSheet.isPointInElement(
								Zero.spriteSheet.getXCenter() + Zero.spriteSheet.getWidth() / 2,
								Zero.spriteSheet.getYCenter() + Zero.spriteSheet.getHeight() / 2)) {
					//
					// if
					// (coins.get(i).spriteSheet.isPointInElement(Zero.spriteSheet.getXCenter(),
					// Zero.spriteSheet.getYCenter())
					// //&&
					// coins.get(i).spriteSheet.isPointInElement(Zero.spriteSheet.getYCenter(),
					// Zero.spriteSheet.getYCenter())
					// ) {

					// if (true){

					System.out.println("touch");

					coins.get(i).x = 1;
					coins.get(i).y = 1;
					coins.get(i).setImagePosition();

					// //coin will be somewhere else
					// coins.translateTo(randomPos.nextInt(600),
					// randomPos.nextInt(500));
					//
					// //increment the score by 1
					scores++;
					//
					// //show the score on the board
					score.msg = "Score " + scores;
					//
					// //play a box sound when the play touches the replinish
					// box
					cSound.play();
				}
				// }

			}
			for (int i = 0; i < tiles.size(); i++) {
			if (tiles.get(i).isPointInElement(
					Zero.spriteSheet.getXCenter() - Zero.spriteSheet.getWidth() / 2,
					Zero.spriteSheet.getYCenter() - Zero.spriteSheet.getHeight() / 2)
					|| tiles.get(i).isPointInElement(
							Zero.spriteSheet.getXCenter() + Zero.spriteSheet.getWidth() / 2,
							Zero.spriteSheet.getYCenter() - Zero.spriteSheet.getHeight() / 2)
					|| tiles.get(i).isPointInElement(
							Zero.spriteSheet.getXCenter() - Zero.spriteSheet.getWidth() / 2,
							Zero.spriteSheet.getYCenter() + Zero.spriteSheet.getHeight() / 2)
					|| tiles.get(i).isPointInElement(
							Zero.spriteSheet.getXCenter() + Zero.spriteSheet.getWidth() / 2,
							Zero.spriteSheet.getYCenter() + Zero.spriteSheet.getHeight() / 2)) {
			}
			
			}
			Zero.processStates();

			for (int i = 0; i < coins.size(); i++) {
				coins.get(i).go();

			}
			for (int i = 0; i < Enemy.size(); i++) {
				Enemy.get(i).Move();
			}
			for (int i = 0; i < flower.size(); i++) {
				flower.get(i).Move();
			}

			// if (coins.)

			/* move player */
			if (EZInteraction.isKeyDown('a')) {
				velocityX -= 2f;
				x -= 2;
				if (x < 0)
					x = widthPic / 2;
			}

			if (EZInteraction.isKeyDown('d')) {
				velocityX += 2f;
				x += 2;
				if (x > widthPic / 2)
					x = 0;
			}

			if (EZInteraction.isKeyDown('w')) {
				player.translateBy(0f, -4f);

			}

			if (EZInteraction.isKeyDown('s')) {
				player.translateBy(0f, 4f);
			}
			if (zeroY > 460) {
				EZ.removeAllEZElements();
			}
			// for (int i = 0; i < Enemy.size(); i++ ) {
			// if (Zero.spriteSheet.isPointInElement(Enemy.get(i).getX(),
			// Enemy.get(i).getX())) {
			// System.out.println(Enemy.get(i).getX());
			// }
			// }

			back.setFocus(x, y, x + 1000, y + 1100);

			// apply friction
			velocityX *= friction;
			velocityY *= friction;
			// move player
			// Zero.translateBy(velocityX, velocityY);
			player.translateBy(velocityX, velocityY);

			// System.out.println(player.getXCenter()+","+
			// Zero.spriteSheet.getYCenter());
			// NOTE: the other images don't move within the group
			// System.out.println(picture11.getXCenter());

			// keep the root group following the opposite of the character
			root.translateTo(-player.getXCenter() + 200f, 0);

			// update screen
			EZ.refreshScreen();
			EZ.setFrameRate(60);
			

		}

	}

}
