/*Author: Steven Bates
 * Date: 5/9/17
 * Comments: Shark GUI
 */
import java.io.IOException;

import javax.swing.JFrame;


public class SharkApp {

	
	public static void main(String[] args) throws IOException, Exception {
		JFrame theGUI = new JFrame();
		theGUI.setTitle("Shark Game");
		theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theGUI.setSize(1920, 1080);
		SharkPanel panel = new SharkPanel();
		theGUI.add(panel);
		theGUI.setUndecorated(true);
		
	
		
		
		theGUI.setVisible(true);
		Audio.main("sounds/LOST.wav");
	}

}
