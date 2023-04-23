import java.awt.Graphics;
import java.awt.image.BufferedImage;

/*
 * 
 * Edited by Thomas Grant
 *
 */

public abstract class Object {
	
	// Instance variables
	protected double positionX;
	protected double positionY;
	protected BufferedImage bufferedImage;
	protected int imageWidth;
	protected int imageHeight;
	
	// Constructors
	
	public Object() {
		
		positionX = 0.;
		positionY = 0.;
		imageWidth = 0;
		imageHeight = 0;
		this.bufferedImage = null;
	}

	public Object(double positionX, double positionY, BufferedImage bufferedImage, int imageWidth, int imageHeight){
		
		this.positionX = positionX;
		this.positionY = positionY;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		this.bufferedImage = bufferedImage;
	}
	
	// Setters and Getters
	
	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}
	
	public double getPositionX() {
		return positionX;
	}
	
	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}
	
	public double getPositionY() {
		return positionY;
	}
	
	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}
	
	public int getImageWidth() {
		return imageWidth;
	}
	
	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}
	
	public int getImageHeight() {
		return imageHeight;
	}

	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}
	
	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}
	
	// Methods
	
	//renders our object to the screen
	public void drawImage(Graphics g)
	{
		g.drawImage(bufferedImage,(int)positionX, (int)positionY,imageWidth,imageHeight, null);
	}
	
	public void move(int x, int y) {}
		
}
