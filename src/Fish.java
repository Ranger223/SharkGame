/*Author: Steven Bates
 * Date: 5/9/17
 * Comments: Fish Object
 */
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;


public class Fish {
	private ImageIcon fishL, fishR, fishSel;
	private int imageX, imageY;
	private int velocity;
	private int size;
	public int point = 0;
	private int num;
	private Random gen = new Random();
	
	public Fish() {
		
		
		
		
		int direction = gen.nextInt(2);
		imageY = gen.nextInt(1000) + 20;
		
		size = gen.nextInt(5) + 1;
		
		if (size == 1) {//large fish
			fishL = new ImageIcon("images/LargeFishL150.png");
			fishR = new ImageIcon("images/LargeFishR150.png");
			velocity = 11;
			this.setPoint(5);
		}
		
		else if (size == 2) {//medium fish
			fishL = new ImageIcon("images/MediumFish2L125.png");
			fishR = new ImageIcon("images/MediumFish2R125.png");
			velocity = 15;
			this.setPoint(10);
		}
		
		else if (size == 3) {//small fish
			fishL = new ImageIcon("images/SmallFish2L50.png");
			fishR = new ImageIcon("images/SmallFish2R50.png");
			velocity = 20;
			this.setPoint(15);
		}
		else if (size == 4) {//small fish
			fishL = new ImageIcon("images/SmallFishL75.png");
			fishR = new ImageIcon("images/SmallFishR85.png");
			velocity = 20;
			this.setPoint(15);
		}
		else if (size == 5) {//diver
			fishL = new ImageIcon("images/DiverL.png");
			fishR = new ImageIcon("images/DiverR.png");
			velocity = 10;
			num = 5;
			this.setPoint(-15);
		}
		
		if (direction == 0) {
			imageX = 1800;
			velocity = Math.abs(velocity) * -1;
			fishSel = fishL;
		}
		else{
			imageX = -150;
			velocity = Math.abs(velocity);
			fishSel = fishR;
		}
		}
		
		public int getNum() {
		return num;
		}
	
		public void setNum(int n) {
			num = n;
		}

		public int getPoint() {
			return point;
		}
	
		public void setPoint(int p) {
			point = p;
		}
	
		public Rectangle getRect(){
			Rectangle rect = new Rectangle(imageX, imageY, getFishSel().getIconWidth(), getFishSel().getIconHeight());
			return rect;
		}
		
		public int getVelocity() {
			return velocity;
		}
	
		public void setVelocity(int v) {
			this.velocity = v;
		}
	
		public void move(int xAmount, int yAmount){
			imageX = imageX + xAmount;
			imageY = imageY + yAmount;
			}
	
		public int getImageX() {
			return imageX;
		}
	
		public void setImageX(int x) {
			this.imageX = x;
		}
	
		public int getImageY() {
			return imageY;
		}
	
		public void setImageY(int y) {
			this.imageY = y;
		}
	
		public ImageIcon getFishSel() {
			return fishSel;
		}
	
		public void setFishSel(int i) {
			//0 points left, 1 points right
			
			if (i == 0) {
				fishSel = fishL;
			}
			else{
				fishSel = fishR;
			}
		}
		
		
		
		
		
		
	}
