import java.awt.Graphics;
import java.awt.image.BufferedImage;

/*
 * 
 * Edited by Thomas Grant
 *
 */

public class Bullet extends MovingObject{
	
	// Instance variable
	
	public boolean bulletTouched = false;
	protected int damage;
	
	// Constructors
	
	public Bullet() {
		super();
		bulletTouched = false;
		damage = 50;
	}
	
	public Bullet(int positionX, int positionY, BufferedImage bufferedImage, int imageWidth, int imageHeight, int velocityX, int velocityY, int damage){
			super(positionX, positionY, bufferedImage, imageHeight, imageWidth, velocityX, velocityY);
			bulletTouched = false;
			this.damage = damage;
		}
		
	// Setters and Getters
	
	public int getXpos(){
		return (int)positionX+velocityX;
	}
		
	public int getYpos(){
		return (int)positionY+velocityY;
	}
	
	public int getDamage(){
		return damage;
	}
	
	public void setBulletTouched(boolean bulletTouched) {
		this.bulletTouched = bulletTouched;
	}
	public boolean getBulletTouched() {
		return bulletTouched;
	}
	
	
	//Method to render our object to the screen
	
	public void drawImage(Graphics g){
		if(bulletTouched == false) {
			g.drawImage(bufferedImage,(int)positionX+45, (int)positionY+30,imageWidth,imageHeight, null);
			positionX+=velocityX;
			positionY+=velocityY;
		}
	}
}
