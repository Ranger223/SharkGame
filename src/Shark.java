/*Author: Steven Bates
 * Date: 5/9/17
 * Comments: Shark Object
 */
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class Shark {
	private ImageIcon sharkL, sharkR, sharkSel;
	private int imageX, imageY;
	private int velocity;
	
	
	public Shark(int x, int y) {
		sharkL = new ImageIcon("images/SharkL2.png");
		sharkR = new ImageIcon("images/SharkR2.png");
		sharkSel = sharkL;
		imageX = x;
		imageY = y;
		velocity = 10;
	}
	
	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int v) {
		velocity = v;
	}

	public Rectangle getRect(){
		Rectangle rect = new Rectangle(imageX, imageY, getSharkSel().getIconWidth(), getSharkSel().getIconHeight());
		return rect;
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

	public ImageIcon getSharkSel() {
		return sharkSel;
	}

	public void setSharkSel(int i) {
		//0 points left, 1 points right
		
		if (i == 0) {
			sharkSel = sharkL;
			
		}
		else{
			sharkSel = sharkR;
			
		}
	}
	
	
	
	
	
	
}
