/*
 * Initialize frame
 */
package lunar_lander;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Window extends JFrame {

	private Window() {

		URL iconURL = getClass().getResource("/lunar_lander/resources/img/icon.png");
		ImageIcon icon = new ImageIcon(iconURL);
		this.setIconImage(icon.getImage());
		this.setTitle("Lunar Lander 1.1");
		{
			this.setSize(1280, 720); // 1280x720px
			this.setLocationRelativeTo(null); // Centered
			this.setResizable(false); // Not resizable
		}

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Stop thread when user exists frame

		this.setContentPane(new Framework());

		this.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() { // Run thread

					public void run() {
						new Window();
						

					}
				});
	}
}