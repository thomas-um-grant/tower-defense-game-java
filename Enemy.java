import java.awt.image.BufferedImage;

/*
 * 
 * Edited by Thomas Grant
 *
 */

public class Enemy extends MovingObject{
	
	//Instance variables
	
	protected int hitpoints;
	enum STATUS {DEAD, ALIVE};
	STATUS enemyStatus;
	
	//Constructors
	
	public Enemy() {
		//from superclass
		super();
		
		//specific to this class
		hitpoints = 5;
		enemyStatus = STATUS.ALIVE;
	}
	
	public Enemy(double positionX, double positionY, BufferedImage bufferedImage, int imageWidth, int imageHeight, int velocityX, int velocityY, int hitpoints, STATUS enemyStatus) {
		super(positionX, positionY, bufferedImage, imageHeight, imageWidth, velocityX, velocityY);
		
		this.hitpoints = hitpoints;
		this.enemyStatus = enemyStatus;
	}
	
	//Setters and Getters
	
	public void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
	}
	
	public int getHitpoints() {
		return hitpoints;
	}
	
	public void setEnemyStatus(STATUS enemyStatus) {
		this.enemyStatus = enemyStatus;
	}
	
	public STATUS getEnemyStatus() {
		return enemyStatus;
	}
	
	//Methods
	
	public void enemyStriken(int damage) {
		hitpoints -= damage;
		if(hitpoints <= 0) {
			enemyStatus = STATUS.DEAD;
		}
	}
}
