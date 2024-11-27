/*Author: Steven Bates
 * Date: 5/9/17
 * Comments: Shark Panel
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JPanel;




public class SharkPanel extends JPanel {
	private ImageIcon bg;
	private Shark bruce = new Shark(700, 700);
	//private Fish nemo = new Fish();
	private Fish nemo = null;
	private ArrayList<Fish> school = new ArrayList<Fish>();
	private final int MAX_FISH_COUNT = 30;
	private javax.swing.Timer timer, spawnTimer;
	private boolean upPressed, downPressed, leftPressed, rightPressed;
	private int curFishCount = 0;
	private int score = 0;
	private int num;
	private int hi;
	private boolean gameOver = false;
	
	public SharkPanel() {
		bg = new ImageIcon("images/Tank3.jpg");
		for (int i = 0; i < 2; i++) {
			createFish();
		}
		this.addKeyListener(new Keyboard());
		this.setFocusable(true);
		timer = new javax.swing.Timer(35, new MoveListener());
		timer.start();
		spawnTimer = new javax.swing.Timer(1500, new SpawnListener());
		spawnTimer.start();
	}
	
	private void createFish(){
	
		if (curFishCount != MAX_FISH_COUNT) {
			Fish f = new Fish();
			school.add(f);
			curFishCount++;
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		bg.paintIcon(this, g, 0, 0);
		if(nemo != null){
			nemo.getFishSel().paintIcon(this, g, nemo.getImageX(), nemo.getImageY());
		}
		if(!school.isEmpty()){
			for (int i = 0; i < school.size(); i++) {
				Fish f = school.get(i);
				f.getFishSel().paintIcon(this, g, f.getImageX(), f.getImageY());
			}
		}
		bruce.getSharkSel().paintIcon(this, g, bruce.getImageX(), bruce.getImageY());
	
		Font f1 = new Font("Comic Sans MS", Font.BOLD, 46);
		g.setFont(f1);
		g.setColor(Color.red);
		g.drawString("Score: " + score, 20, 40);
		
		
		if (gameOver) {
			Font f2 = new Font("Comic Sans MS", Font.BOLD, 86);
			g.setFont(f2);
			g.setColor(Color.green);
			g.drawString("GAME OVER!!", 705, 500);
			
			PrintWriter writer;
			
			
			try {
				Scanner reader = new Scanner(new  File("score.txt"));
				while(reader.hasNext()){
				hi = reader.nextInt();
				}
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
				}
			
			if (score > hi) {
				
			try {
				writer = new PrintWriter (new File("score.txt"));
				writer.println(score);
				writer.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
			
			
			
			
			Font f3 = new Font("Narkisim", Font.BOLD, 60);
			g.setFont(f3);
			g.setColor(Color.blue);
			g.drawString("High Score: " + hi, 740, 600);
		}
		
		
		}
		
	
	private class Keyboard implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			
			int keyCode = e.getKeyCode();
			if (keyCode == KeyEvent.VK_UP) {
				upPressed = true;
				//bruce.move(0, -10);	
			}
			else if (keyCode == KeyEvent.VK_DOWN) {
				downPressed = true;
				//bruce.move(0, 10);
				
			}
			if (keyCode == KeyEvent.VK_LEFT) {
				leftPressed = true;
				//bruce.move(-10, 0);
				bruce.setSharkSel(0);
			}
			else if (keyCode == KeyEvent.VK_RIGHT) {
				rightPressed = true;
				//bruce.move(10, 0);
				bruce.setSharkSel(1);
			}
			
			// exit the program
			if (keyCode == KeyEvent.VK_ESCAPE) {
				System.exit(0);	
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode();
			if (keyCode == KeyEvent.VK_UP) {
				upPressed = false;
					
			}
			
			if (keyCode == KeyEvent.VK_DOWN) {
				downPressed = false;
					
			}
			if (keyCode == KeyEvent.VK_LEFT) {
				leftPressed = false;
					
			}
			if (keyCode == KeyEvent.VK_RIGHT) {
				rightPressed = false;
					
			}
			
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			
			
		}
		
	}
	private class MoveListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//******************************************************Moving the Shark*********************************************************
			if (upPressed && bruce.getImageY() >= 20) {
				bruce.move(0, -bruce.getVelocity());
			}
			if (downPressed && bruce.getImageY() <= (1060 - bruce.getSharkSel().getIconHeight())) {
				bruce.move(0, bruce.getVelocity());
			}
			if (leftPressed && bruce.getImageX() >= 20) {
				bruce.move(-bruce.getVelocity(), 0);
			}
			if (rightPressed && bruce.getImageX() <= (1900 - bruce.getSharkSel().getIconWidth())) {
				bruce.move(bruce.getVelocity(), 0);
			}
			//******************************************************Moving the Fish**********************************************************
			if (nemo != null) {
				nemo.move(nemo.getVelocity(), 0);		
			}
			
			if (!school.isEmpty()) {
				for (int i = 0; i < school.size(); i++) {
					Fish f = school.get(i);
					f.move(f.getVelocity(), 0);
				}
			}
			
			//******************************************************Checking for Fish out of bounds**************************************************
			if (nemo != null) {
				if (nemo.getImageX() <= -300 || nemo.getImageX() >= 2150) {
					nemo = null;
					System.out.println("HE TOUCHED THE BUTT!!!");
				}
			}
			
			if (!school.isEmpty()) {
				for (int i = 0; i < school.size(); i++) {
					Fish f = school.get(i);
					if (f.getImageX() <= -300 || f.getImageX() >= 2150) {
						school.remove(i);
						System.out.println("ur mom goes to college.");
						
					}
					
				}
			}
			
			//*******************************************************Check collision of Shark and Fish******************************************
			if (!school.isEmpty()) {
				for (int i = 0; i < school.size(); i++) {
					Fish f = school.get(i);
					if (bruce.getRect().intersects(f.getRect())) {
						school.remove(i);
						score += f.getPoint();
						num = f.getNum();
						System.out.println("fishie");
						if (num == 5) {
							try {
								Audio.main("sounds/Scream.wav");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						try {
							Audio.main("sounds/shark_chomp.wav");
						} catch (IOException e) {
							
							e.printStackTrace();
						} catch (Exception e) {
							
							e.printStackTrace();
						}
					}
				
					
				}
			}
			
			//******************************************************Check if game is over*********************************************************
			
				
			if (curFishCount == MAX_FISH_COUNT && school.isEmpty() || num == 5) {
				
				timer.stop();
				spawnTimer.stop();
				gameOver = true;
				
			}
			
			
			
			repaint();
			
		}
		
	}
	
	private class SpawnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			createFish();
		}
	}
}
















