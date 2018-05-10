import java.awt.Color;
import java.util.Random;

public class Enemy1 {

	EZImage DannyPict;
	int dannyX;
	int dannyY;
	float posX=500f;
	int posY=500;
	float directionX = 2.0f;
	
	//constructor of enemy
	public Enemy1(String name,int x, int y){
		dannyX=x;
		dannyY=y;
		DannyPict=EZ.addImage(name, dannyX, dannyY);
		posX=x;
		posY=y;	
	}
	
	public int getX() {
		return dannyX;
	}
	
	public int getY() {
		return dannyY;
	}
	
	public void Move(){
		
		DannyPict.translateTo(posX, posY);
		posX=posX+directionX;
		
		//limits the enemy to move between 0 and 1000 on the screen
		if (posX > getX() + 60) {
			directionX = -directionX;
		}
		if (posX < getX() - 60){
			directionX = -directionX;
		}
		
	}
	
	 public static void Hero_Enemy_Collision(EZImage HeroPict){
		// if(DannyPict.isPointInElement(HeroPict.getXCenter(),HeroPict.getYCenter())) {
				EZ.removeEZElement(HeroPict);//remove hero
				EZ.addText(400, 400, "GAME OVER!",Color.black,40);
		// }	 
	 }
}
