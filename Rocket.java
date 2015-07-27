/*
 * Rocket physics and controls.
 */
package lunar_lander;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Rocket {

	private Random random; // Random X start position

	public int x; // X coordinate (2D)

	public int y; // Y coordinate (2D)

	public boolean landed; // Check if landed

	public boolean crashed; // Check if crashed

	private int speedAccelerating; // Acceleration

	public int maxLandingSpeed; // Max speed for land

	private double speedX; // Horizontal speed

	public double speedY; // Vertical speed

	public double speedGrav; // Gravity

	private BufferedImage landerRocket; // Lunar Lander

	private BufferedImage landerLanded; // Landed Lander

	private BufferedImage landerCrashed; // Crashed Lander

	private BufferedImage landerFlyingUp; // Lander going up Lander
	
	private BufferedImage landerFlyingDown; // Accelerating Lander
	
	private BufferedImage landerFlyingRight; // Lander flying left
	
	private BufferedImage landerFlyingLeft; // Lander flying right

	public int landerRocketWidth; // Read image width

	public int landerRocketHeight; // Read image height

	public Rocket() // Gather rocket dimensions
	{
		initialize();
		loadcontent();

		x = random.nextInt(Framework.frameWidth - landerRocketWidth); // X random start
	}

	private void initialize() {
	random = new Random(); // Initialize random start

		speedAccelerating = 1;
		speedY = 1;
		speedGrav = -0.16;
		maxLandingSpeed = 5;
	}


	private void loadcontent() // Load resources
	{
		try {
			URL landerRocketURL = this.getClass().getResource("/lunar_lander/resources/img/landerRocket.png");
			landerRocket = ImageIO.read(landerRocketURL);
			landerRocketWidth = landerRocket.getWidth();
			landerRocketHeight = landerRocket.getHeight();

			URL landerLandedURL = this.getClass().getResource("/lunar_lander/resources/img/lander_landed.png");
			landerLanded = ImageIO.read(landerLandedURL);

			URL landerCrashedURL = this.getClass().getResource("/lunar_lander/resources/img/lander_crash.png");
			landerCrashed = ImageIO.read(landerCrashedURL);

			URL landerFlyingUpURL = this.getClass().getResource("/lunar_lander/resources/img/lander_fire_up.png");
			landerFlyingUp = ImageIO.read(landerFlyingUpURL);
			
			URL landerFlyingDownURL = this.getClass().getResource("/lunar_lander/resources/img/lander_fire_down.png");
			landerFlyingDown = ImageIO.read(landerFlyingDownURL);
			
			URL landerFlyingRightURL = this.getClass().getResource("/lunar_lander/resources/img/lander_fire_right.png");
			landerFlyingRight = ImageIO.read(landerFlyingRightURL);
			
			URL landerFlyingLeftURL = this.getClass().getResource("/lunar_lander/resources/img/lander_fire_left.png");
			landerFlyingLeft = ImageIO.read(landerFlyingLeftURL);
			
		} catch (IOException ex) {
			Logger.getLogger(Rocket.class.getName())
					.log(Level.SEVERE, null, ex);
		}
	}

	public void Update() // Inverted rocket controls
	{
		if (Control.keyboardKeyState(KeyEvent.VK_DOWN)) // Key DOWN
			speedY -= speedAccelerating;

		else {
			speedY -= speedGrav;
		}

		if (Control.keyboardKeyState(KeyEvent.VK_UP)){ // Key UP
			speedY += speedAccelerating;
		}
		if (Control.keyboardKeyState(KeyEvent.VK_RIGHT)){ // Key RIGHT
			speedX -= speedAccelerating;
		}
		if (Control.keyboardKeyState(KeyEvent.VK_LEFT)){ // Key LEFT
			speedX += speedAccelerating;
	        }
		if (Control.keyboardKeyState(KeyEvent.VK_0)){ // Cheat
			speedY = 0;
			speedX = 0;
		}
		x += speedX;
		y += speedY;

	}
	

	public void draw(Graphics2D g2d) {

		if (landed) // Check if landed
		{
			g2d.drawImage(landerLanded, x, y, null);
		} else if (crashed) // Check if crashed
		{
			g2d.drawImage(landerCrashed, x, y, null);
		} else {
			if (Control.keyboardKeyState(KeyEvent.VK_UP)) // Draw fly image
			g2d.drawImage(landerFlyingDown, x, y, null);
			g2d.drawImage(landerRocket, x, y, null);

			if (Control.keyboardKeyState(KeyEvent.VK_DOWN)) // Draw fly image
			g2d.drawImage(landerFlyingUp, x, y, null);
			g2d.drawImage(landerRocket, x, y, null);

			if (Control.keyboardKeyState(KeyEvent.VK_RIGHT)) // Draw fly image
			g2d.drawImage(landerFlyingLeft, x, y, null);
			g2d.drawImage(landerRocket, x, y, null);

			if (Control.keyboardKeyState(KeyEvent.VK_LEFT)) // Draw fly image
			g2d.drawImage(landerFlyingRight, x, y, null);
			g2d.drawImage(landerRocket, x, y, null);
		}
	}
	

}