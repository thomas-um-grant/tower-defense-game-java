import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/*
 * 
 * Edited by Thomas Grant
 *
 */

//tower object that won't move on the map, which is connected to the class Bullet: Tower has a Bullet

public class Tower extends Object{
	
	// Instance variables
	//add velocities for the bullets it will fire
	protected int velocityX;
	protected int velocityY;
	
	//add the type of bullet you are going to fire
	protected MovingObject bullet;
	protected MovingObject bullet2;
	protected MovingObject bullet3;
	
	//create an array list of projectile objects since we will need a lot of these
	protected ArrayList<MovingObject> arrayBullets = new ArrayList<MovingObject>();
	
	//create a variable that determines when to fire
	protected int timeBetweenShots;
	
	//create a variable that determines when to fire
	protected double shootingRadius;
	
	//create a variable that determines where to fire
	protected int range;
	
	//create a variable that determines which tower is used
		protected int whichTower;
		
	// Constructors
	
	public Tower() {
		//from superclass
		super();
		
		//specific to this class
		this.shootingRadius = 0.;
		this.range = 0;
		this.timeBetweenShots = 0;
		this.whichTower = 1;
	}
	
	public Tower(double positionX, double positionY, BufferedImage bufferedImage, int imageWidth, int imageHeight, double shootingRadius, int range, int timeBetweenShots, int whichTower) {
		//from superclass
		super(positionX, positionY, bufferedImage, imageHeight, imageWidth);

		//specific to this class
		this.shootingRadius = shootingRadius;
		this.range = range;
		this.timeBetweenShots = timeBetweenShots;
		this.whichTower = whichTower;
	}
	
	// Setters and Getters
	
	public void setShootingRadius(double shootingRadius) {
		this.shootingRadius = shootingRadius;
	}
	
	public double getShootingRadius() {
		return shootingRadius;
	}
	
	public void setRange(int range) {
		this.range = range;
	}
	
	public double getRange() {
		return range;
	}
	
	public void setTimeBetweenShots(int timeBetweenShots) {
		this.timeBetweenShots = timeBetweenShots;
	}
	
	public double getTimeBetweenShots() {
		return timeBetweenShots;
	}
	
	public void setWhichTower(int whichTower) {
		this.whichTower = whichTower;
	}
	
	public double getWhichTower() {
		return whichTower;
	}
	
	//Methods
	
	//Fire a bullet
	public void fire(Graphics g)
	{
		//for every third cycle fire create a new bullet
		
		if(timeBetweenShots == 0) {
			try {
				if(whichTower == 1) {
					BufferedImage fireBall = ImageIO.read(new File("fireBall.png"));
					if(this.positionY-this.getImageHeight()/2 <= 180) {
						bullet = new Bullet((int)this.getPositionX(), (int)this.getPositionY(), fireBall, fireBall.getWidth(), fireBall.getHeight(), -6, 10, 30);
						arrayBullets.add((Bullet) bullet);
						
						bullet2 = new Bullet((int)this.getPositionX(), (int)this.getPositionY(), fireBall, fireBall.getWidth(), fireBall.getHeight(), 0, 10, 30);
						arrayBullets.add((Bullet) bullet2);
						
						bullet3 = new Bullet((int)this.getPositionX(), (int)this.getPositionY(), fireBall, fireBall.getWidth(), fireBall.getHeight(), 6, 10, 30);
						arrayBullets.add((Bullet) bullet3);
					}
					else {
						bullet = new Bullet((int)this.getPositionX(), (int)this.getPositionY(), fireBall, fireBall.getWidth(), fireBall.getHeight(), 6, -10, 30);
						arrayBullets.add((Bullet) bullet);
						
						bullet2 = new Bullet((int)this.getPositionX(), (int)this.getPositionY(), fireBall, fireBall.getWidth(), fireBall.getHeight(), 0, -10, 30);
						arrayBullets.add((Bullet) bullet2);
						
						bullet3 = new Bullet((int)this.getPositionX(), (int)this.getPositionY(), fireBall, fireBall.getWidth(), fireBall.getHeight(), -6, -10, 30);
						arrayBullets.add((Bullet) bullet3);
					}
					
					timeBetweenShots = 50;
				}
				else if(whichTower == 2) {
					BufferedImage bossBall = ImageIO.read(new File("bossBall.png"));
					if(this.positionY-this.getImageHeight()/2 <= 180) {
						bullet = new Bullet((int)this.getPositionX(), (int)this.getPositionY(), bossBall, bossBall.getWidth(), bossBall.getHeight(), 0, 10, 100);
						arrayBullets.add((Bullet) bullet);
					}
					else {
						bullet = new Bullet((int)this.getPositionX(), (int)this.getPositionY(), bossBall, bossBall.getWidth(), bossBall.getHeight(), 0, -10, 100);
						arrayBullets.add((Bullet) bullet);
					}
					
					timeBetweenShots = 50;
				}
				else if(whichTower == 3) {
					BufferedImage canonBall = ImageIO.read(new File("canonBall.png"));
					if(this.positionY-this.getImageHeight()/2 <= 180) {
						bullet = new Bullet((int)this.getPositionX(), (int)this.getPositionY(), canonBall, canonBall.getWidth(), canonBall.getHeight(), -6, 10, 20);
						arrayBullets.add((Bullet) bullet);
						
						bullet2 = new Bullet((int)this.getPositionX(), (int)this.getPositionY(), canonBall, canonBall.getWidth(), canonBall.getHeight(), 6, 10, 20);
						arrayBullets.add((Bullet) bullet2);
					}
					else {
						bullet = new Bullet((int)this.getPositionX(), (int)this.getPositionY(), canonBall, canonBall.getWidth(), canonBall.getHeight(), -6, -10, 20);
						arrayBullets.add((Bullet) bullet);
						
						bullet2 = new Bullet((int)this.getPositionX(), (int)this.getPositionY(), canonBall, canonBall.getWidth(), canonBall.getHeight(), 6, -10, 20);
						arrayBullets.add((Bullet) bullet2);
					}
					
					timeBetweenShots = 40;
				}
				
/*
				BufferedImage bossBall = ImageIO.read(new File("bossBall.png"));
				bullet = new Bullet((int)this.getPositionX(), (int)this.getPositionY(), bossBall, bossBall.getWidth(), bossBall.getHeight(), velocityX, velocityY);
				arrayBullets.add(bullet);
				
				timeBetweenShots = 30;				
				*/
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			-- timeBetweenShots;
		}
		
	
		//draw all the bullets
	
		//if bullet position exceeds map dimensions set it to null and remove it from arraylist
		
		for(int i=0; i<arrayBullets.size(); i++)
			arrayBullets.get(i).drawImage(g);
	
	}
	
	//object doesn't need the move anymore
	public void move(int x, int y)
	{
		positionX = x;
		positionY = y;
	}
	
	public ArrayList<MovingObject> getArrayBullet(){
		return arrayBullets;
	}
}

