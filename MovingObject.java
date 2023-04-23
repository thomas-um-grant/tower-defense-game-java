import java.awt.image.BufferedImage;
import java.awt.Graphics;

/*
 * 
 * Edited by Thomas Grant
 *
 */

public class MovingObject extends Object{
	
	// Instance variables
	
	//add velocities for the bullets it will fire
	protected int velocityX;
	protected int velocityY;
		
	// Constructors
		
	public MovingObject() {
		
		//from superclass
		super();
		
		//specific to this class
		velocityX = 0;
		velocityY = 0;
	}
		
	public MovingObject(double positionX, double positionY, BufferedImage bufferedImage, int imageWidth, int imageHeight, int velocityX, int velocityY) {
		//from superclass
		super(positionX, positionY, bufferedImage, imageHeight, imageWidth);

		//specific to this class
		this.velocityX = velocityX;
		this.velocityY = velocityY;
	}
	
	//Setters and Getters
	
	public void setVelocityX(int velocityX) {
		this.velocityX = velocityX;
	}
	
	public int getVelocityX() {
		return velocityX;
	}
	
	public void setVelocityY(int velocityY) {
		this.velocityY = velocityY;
	}
	
	public int getVelocityY() {
		return velocityY;
	}
	//Methods
	
	//renders the object to the screen
	public void drawImage(Graphics g)
	{
		g.drawImage(bufferedImage,(int)positionX, (int)positionY,imageWidth,imageHeight,null);
		positionX+=velocityX;
		positionY+=velocityY;
	}
	
	//change the velocity
	public void changeV(int x, int y)
	{
		velocityX = x; velocityY = y;
	}

	public boolean getBulletTouched() {
		return false;
	}
	
	public void setBulletTouched(boolean b) {
		// TODO Auto-generated method stub
	}
}
