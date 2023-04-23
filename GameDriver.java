import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * 
 * Edited by Thomas Grant
 *
 */

@SuppressWarnings({ "serial", "unused" })

public class GameDriver extends JFrame {
	
	protected int money;
	protected int fireTowerCost;
	protected int bossTowerCost;
	protected int canonTowerCost;
	
	public GameDriver(){
		
		super("Tower Defense Game");
		
		money = 200;
		fireTowerCost = 50;
		bossTowerCost = 100;
		canonTowerCost = 50;
		
		/*
		 * 
		 */
		//Create the menu panel on the left
		
		JPanel panelMain = new JPanel();
		panelMain.setBackground(Color.LIGHT_GRAY);
		this.getContentPane().add(BorderLayout.WEST, panelMain);
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(Color.LIGHT_GRAY);
		panelMain.add(panelMenu);
		
		//Make the panelMenu non-re-sizable
		GridBagLayout gbl_panelMenu = new GridBagLayout();
		gbl_panelMenu.columnWidths = new int[]{70, 23, 23, 23};
		gbl_panelMenu.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelMenu.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelMenu.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelMenu.setLayout(gbl_panelMenu);

		JButton buttonStart = new JButton("Start");
		buttonStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Start pressed");
			}
		});
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 5;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		panelMenu.add(buttonStart, constraints);
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.gridy ++;
		
		/*
		 * 
		 */
		//Create the field (map)
		
		MapLoader map = new MapLoader();
		this.getContentPane().add(BorderLayout.CENTER, map);
		
		//Make the size of the frame according to the size of the map chosen
		this.setSize(map.getWidth()+260, map.getHeight());
		//this.setResizable(false);

		// Life
		
		BufferedImage lifeLogo;
		JLabel lifeText = new JLabel(""+map.getLife(), JLabel.CENTER);
		
		try {
			lifeLogo = ImageIO.read(new File("lifeLogo.png"));
			JLabel lifeDisplay = new JLabel(new ImageIcon(lifeLogo));
			panelMenu.add(lifeDisplay,constraints);
			constraints.gridx++;
			constraints.gridwidth =3;
			panelMenu.add(lifeText, constraints);
			lifeText.setFont(new Font("Serif", Font.PLAIN, 30));
			constraints.gridx--;
			constraints.gridy++;
			constraints.gridwidth = 1;
			
		} catch (IOException e6) {
			e6.printStackTrace();
		}
		
		lifeText.setText(""+map.getLife());
		
		// Money
		
		BufferedImage moneyLogo;
		JLabel moneyValue = new JLabel(""+money, JLabel.CENTER);
		try {
			moneyLogo = ImageIO.read(new File("moneyLogo.png"));
			JLabel moneyDisplay = new JLabel(new ImageIcon(moneyLogo));
			moneyValue.setFont(new Font("Serif", Font.PLAIN, 30));
			panelMenu.add(moneyDisplay, constraints);
			constraints.gridx++;
			constraints.gridwidth = 3;
			panelMenu.add(moneyValue, constraints);
			constraints.gridx--;
			constraints.gridy++;
			constraints.gridwidth = 1;
			
		} catch (IOException e6) {
			e6.printStackTrace();
		}
		
		// button to add tower on the field
		// fire tower
		
		ImageIcon GIcon = new ImageIcon("fireTower.png");
		Image image = GIcon.getImage();
					
		JButton btnFireTower = new JButton(GIcon);
		btnFireTower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("fireTower added to field");
	
				try {
					if(money-fireTowerCost < 0) {
						System.out.println("Not enough money");
					}
					else {
						BufferedImage fireTower = ImageIO.read(new File("fireTower.png"));
						Object t1 = new Tower(0,0, fireTower, fireTower.getHeight(), fireTower.getWidth(), 5.5, 10, 3, 1);
						map.addObject(t1);
						money -= fireTowerCost;
						moneyValue.setText(""+money);
					}
				}
				catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JLabel fireTower = new JLabel();
		panelMenu.add(new JLabel("Fire Tower: "+fireTowerCost, JLabel.CENTER), constraints);
		
		constraints.gridx++;
		constraints.gridwidth = 3;
		panelMenu.add(btnFireTower, constraints);
		constraints.gridx--;
		constraints.gridy++;
		constraints.gridwidth = 1;
		
		// boss tower
		
		ImageIcon GIcon2 = new ImageIcon("bossTower.png");
		Image image2 = GIcon2.getImage();
					
		JButton btnBossTower = new JButton(GIcon2);
		btnBossTower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("bossTower added to field");
	
				try {
					if(money-bossTowerCost < 0) {
						System.out.println("Not enough money");
					}
					else {
						BufferedImage bossTower = ImageIO.read(new File("bossTower.png"));
						Object t2 = new Tower(0,0, bossTower, bossTower.getHeight(), bossTower.getWidth(), 5.5, 10, 3, 2);
						map.addObject(t2);
						money -= bossTowerCost;
						moneyValue.setText(""+money);
					}
				}
				catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JLabel bossTower = new JLabel();
		panelMenu.add(new JLabel("Boss Tower: "+bossTowerCost, JLabel.CENTER), constraints);
		
		constraints.gridx++;
		constraints.gridwidth = 3;
		panelMenu.add(btnBossTower, constraints);
		constraints.gridx--;
		constraints.gridy++;
		constraints.gridwidth = 1;
		
		
		// canon Tower
		
				ImageIcon GIcon3 = new ImageIcon("canonTower.png");
				Image image3 = GIcon3.getImage();
							
				JButton btnCanonTower = new JButton(GIcon3);
				btnCanonTower.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//System.out.println("bossTower added to field");
			
						try {
							if(money-canonTowerCost < 0) {
								System.out.println("Not enough money");
							}
							else {
								BufferedImage canonTower = ImageIO.read(new File("canonTower.png"));
								Object t3 = new Tower(0,0, canonTower, canonTower.getHeight(), canonTower.getWidth(), 5.5, 10, 3, 3);
								map.addObject(t3);
								money -= canonTowerCost;
								moneyValue.setText(""+money);
							}
						}
						catch (IOException e2) {
							e2.printStackTrace();
						}
					}
				});
				
				JLabel canonTower = new JLabel();
				panelMenu.add(new JLabel("Canon Tower: "+canonTowerCost, JLabel.CENTER), constraints);
				
				constraints.gridx++;
				constraints.gridwidth = 3;
				panelMenu.add(btnCanonTower, constraints);
				constraints.gridx--;
				constraints.gridy++;
				constraints.gridwidth = 1;
				
				
		// button to start a game (add 5 enemies on the map)
		
		buttonStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event1) {
				//System.out.println("Game started");
	
				try {
					if(MapLoader.path == MapLoader.PATH.map1) {
						BufferedImage redBalloon = ImageIO.read(new File("redBalloon.png"));
						BufferedImage fighter = ImageIO.read(new File("fighter.png"));
						BufferedImage cochon = ImageIO.read(new File("cochon.png"));
						BufferedImage dragon = ImageIO.read(new File("dragon.png"));
						BufferedImage blueBalloon = ImageIO.read(new File("blueBalloon.png"));
						
						MovingObject e1 = new Enemy(-150,235, redBalloon, redBalloon.getWidth(), redBalloon.getHeight(), 6, 0, 100, Enemy.STATUS.ALIVE);
						map.addObject(e1);
						MovingObject e2 = new Enemy(-300,235, fighter, fighter.getWidth(), fighter.getHeight(), 6, 0, 100, Enemy.STATUS.ALIVE);
						map.addObject(e2);
						MovingObject e3 = new Enemy(-450,235, cochon, cochon.getWidth(), cochon.getHeight(), 6, 0, 100, Enemy.STATUS.ALIVE);
						map.addObject(e3);
						MovingObject e4 = new Enemy(-600,235, dragon, dragon.getWidth(), dragon.getHeight(), 6, 0, 100, Enemy.STATUS.ALIVE);
						map.addObject(e4);
						MovingObject e5 = new Enemy(-750,235, blueBalloon, blueBalloon.getWidth(), blueBalloon.getHeight(), 6, 0, 100, Enemy.STATUS.ALIVE);
						map.addObject(e5);
						
						//test to refresh life
						/*
						if(e1.getPositionX() > map.getWidth()) {
							lifeText.setText(""+map.getLife());
						}
						lifeText.setText(""+map.getLife());
						*/
						
						//test to end the game
						/*
						if(life <= 0) {
							JButton buttonGameOver = new JButton("Game Over");
							buttonGameOver.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									System.exit(0);
								}
							});
							map.add(buttonGameOver, BorderLayout.CENTER);
						}
						*/
					}
					else if(MapLoader.path == MapLoader.PATH.map2) {
						/*
						BufferedImage redBalloon = ImageIO.read(new File("redBalloon.png"));
						BufferedImage fighter = ImageIO.read(new File("fighter.png"));
						BufferedImage cochon = ImageIO.read(new File("cochon.png"));
						BufferedImage dragon = ImageIO.read(new File("dragon.png"));
						BufferedImage blueBalloon = ImageIO.read(new File("blueBalloon.png"));
						
						MovingObject e1 = new Enemy(-100,235, redBalloon, redBalloon.getWidth(), redBalloon.getHeight(), 0, 0, 100, Enemy.STATUS.ALIVE);
						map.addObject(e1);
						MovingObject e2 = new Enemy(-200,235, fighter, fighter.getWidth(), fighter.getHeight(), 4, 0, 100, Enemy.STATUS.ALIVE);
						map.addObject(e2);
						MovingObject e3 = new Enemy(-300,235, cochon, cochon.getWidth(), cochon.getHeight(), 4, 0, 100, Enemy.STATUS.ALIVE);
						map.addObject(e3);
						MovingObject e4 = new Enemy(-400,235, dragon, dragon.getWidth(), dragon.getHeight(), 4, 0, 100, Enemy.STATUS.ALIVE);
						map.addObject(e4);
						MovingObject e5 = new Enemy(-500,235, blueBalloon, blueBalloon.getWidth(), blueBalloon.getHeight(), 4, 0, 100, Enemy.STATUS.ALIVE);
						map.addObject(e5);
						
						while(e1.getPositionX()<200) {
							e1.setVelocityX(4);
							e1.setVelocityY(0);
						}
						while(e1.getPositionY()<200) {
							e1.setVelocityX(0);
							e1.setVelocityY(4);
						}
						*/
						System.out.println("Path in creation");
					}
					else
						System.out.println("Not working");
					
					//Collision bullet/enemy
				}
				catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		});
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new GameDriver();
	}
}