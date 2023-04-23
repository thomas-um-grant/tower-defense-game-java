import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/*
 * 
 * Edited by Thomas Grant
 *
 */

@SuppressWarnings("serial")
public class MapLoader extends JPanel{
		
	// Instance variables
	protected BufferedImage[][] imgs;
	protected int numRows;
	protected int numCols;
	protected final int tileSize = 80;
	
	protected int x;
	protected int y;
	
	protected ArrayList<Object> drawMe;
	protected ArrayList<MovingObject> drawMe2;
	
	private int xClick;
	private int	yClick;
	
	protected String mapChosen;
	protected enum PATH {map1, map2};
	protected static PATH path;
	
	protected int life;

	// Constructors

	public MapLoader() {
		numRows = 0;
		numCols = 0;
		
		life = 3;
		//Ask the user to enter the name of the map file
		
		//Create a scanner to let the user choose the map he wants to display
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("Please, enter the name of the map file you want to display: ");
		
		try {
			//Create a Scanner to read the file chosen by the user
			mapChosen = input.nextLine();
			File file = new File(mapChosen);
			//System.out.println(mapChosen);
			if(mapChosen.contains("Map1.txt")) {
				path = PATH.map1;
			}
			else if(mapChosen.contains("Map2.txt")) {
				path = PATH.map2;
			}
			//System.out.println(path);
			@SuppressWarnings("resource")
			Scanner fileReader = new Scanner(file);
			
			//Use the first 2 integers in the file for the number of rows and columns
			numRows = fileReader.nextInt();
			numCols = fileReader.nextInt();
			
			imgs = new BufferedImage[numRows][numCols];

			
			//Using a Scanner to read in char associated to the pictures which compose the map
			for(int i = 0; i<numRows; i++) {
				for(int j = 0; j<numCols; j++) {
					String c = fileReader.next();
					addPicture(i, j, c.charAt(0) + ".jpg");
				}
			}
		}
		catch(Exception ioException){
			System.out.println(ioException);
		}
		
		//Make the size of the frame according to the size of the map chosen
		this.setSize(numCols*80, numRows*80+22);

		this.setVisible(true);
		
		x = 0;
		y = 0;
		
		drawMe = new ArrayList<Object>();
		drawMe2 = new ArrayList<MovingObject>();
		
		xClick = 0;
		yClick = 0;
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				xClick = e.getX();
				yClick = e.getY();
			}
		}); 
	}

	// Methods
	
	public void addPicture(int x, int y, String filename){
		if (x < 0 || x >= numRows){
			System.err.println("There is no row " + x);
		}
		else if (y < 0 || y >= numCols){
			System.err.println("There is no col " + y);
		}
		else{
			try {
				imgs[x][y] = ImageIO.read(new File(filename));
			} catch (IOException e) {
				System.err.println("Unable to read the file: " + filename);
			}
		}
	}
	
	//search for the first null position and add a stationary object to draw
	public void addObject(Object o){
		o.move(xClick-(o.getImageWidth()/2), yClick-(o.getImageHeight()/2));
		drawMe.add(o); 
	}
	
	//Add a moving object to draw
	public void addObject(MovingObject mo) {
		drawMe2.add(mo);
	}
	
	//This is  special method that continually paints our window
	//It exists by default, but by creating it here we override the default
		@Override
		public void paint(Graphics g){
			for (int i = 0; i < numRows; i++){
				for (int j = 0; j < numCols; j++){
					g.drawImage(imgs[i][j], j*tileSize, i*tileSize, null);
				}
			}
			try {
				for(Object so : drawMe)
				{
					if (so != null)
					{
						so.drawImage(g);
						//if so is a tower call the fire method
						if(so instanceof Tower)
							((Tower) so).fire(g);
					}
				}
				for(MovingObject mo : drawMe2)
				{
					if (mo != null)
					{
						mo.drawImage(g);
					}	
				}
				
				for(MovingObject mo : drawMe2) {
					for(Object mo2 : drawMe) {
						if(mo instanceof Enemy) {
							if(mo2 instanceof Tower) {
								for(MovingObject mo3 : ((Tower) mo2).getArrayBullet()) {
									if(mo3.getPositionX() > mo.getPositionX() && mo3.getPositionX() < mo.getPositionX()+mo.getImageWidth()) {
										if(mo3.getPositionY() > mo.getPositionY() && mo3.getPositionY() < (mo.getPositionY()+mo.getImageHeight())) {
											((Enemy) mo).enemyStriken(((Bullet) mo3).getDamage());
											mo3.setBulletTouched(true);
											System.out.println("enemy life: "+((Enemy) mo).getHitpoints()+" bullet status: "+mo3.getBulletTouched());
											break;
										}
									}
								}
							}
						}
					}
				}
				
				for(Object mo2 : drawMe) {
					if(mo2 instanceof Tower) {
						for(MovingObject mo3 : ((Tower) mo2).getArrayBullet()) {
							if(mo3.getBulletTouched()) {
								//System.out.println("bullet removed bulletTouched");
								//mo3.setBulletTouched(false);
								drawMe2.remove(mo3); break;
							}
							else if(mo3.getPositionX() < 0 || mo3.getPositionX() > this.getWidth()) {
								//System.out.println("bullet removed out of bounds X");
								drawMe2.remove(mo3); break;
							}
							else if(mo3.getPositionY() < 0 || mo3.getPositionY() > this.getHeight()) {
								//System.out.println("bullet removed out of bounds Y");
								drawMe2.remove(mo3); break;
							}
						}
					}
				}
				
				for(MovingObject mo : drawMe2) {
					if(mo instanceof Enemy) {
						if(((Enemy) mo).getEnemyStatus() == (Enemy.STATUS.DEAD)) {
							System.out.println("enemy removed because DEAD");
							//((Enemy) mo).setEnemyStatus(Enemy.STATUS.ALIVE);
							drawMe2.remove(mo); break;
						}
						else if(mo.getPositionX() > this.getWidth()) {
							if(life > 1) {
								life-=1;			
							}
							else {
								System.out.println("Game Over");
								System.exit(0);
							}
							System.out.println("enemy removed out of bounds");
							drawMe2.remove(mo); break;
						}
					}
				}
				
					Thread.sleep(100);//got to slow things down to see
					repaint();//refresh the screen so things aren't drawn on top of each other
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	public int getWidth() {
		return numCols*80;
	}
	
	public int getgetHeight() {
		return numRows*80;
	}
	
	public void setLife(int life) {
		this.life = life;
	}
	
	public int getLife() {
		return life;
	}

	
}
