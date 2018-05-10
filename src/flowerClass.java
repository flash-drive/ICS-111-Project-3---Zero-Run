
public class flowerClass {
	
	EZImage spriteSheet;
	
	int flowerX;
	int flowerY;
	float posX=500f;
	float posY=500f;
	float directionY = 2.0f;
	
	public flowerClass(String name, int x, int y){
		flowerX=x;
		flowerY=y;
		spriteSheet=EZ.addImage(name, flowerX, flowerY);
		posX=x;
		posY=y;
		spriteSheet.scaleTo(2);
	}
	
public void Move(){
		
		spriteSheet.translateTo(posX, posY);
		posY=posY+directionY;
		
		
		//limits the enemy to move between 0 and 1000 on the screen
		if (posY > 400 ) {
				directionY = -directionY;
		}
		if(posY < 100){
				directionY = -directionY;
		}
		
		
	}
	
}
	
