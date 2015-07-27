/**
 * @author Rey Koxha 
 * Main game
 */
package lunar_lander;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class Main {

    private Rocket player; // Set player model

    private Landingspace landingSpace; // Area to land

    private BufferedImage background; // Background image

    private BufferedImage failed; // Failed image

    public Main() {

	Thread threadForInitGame = new Thread() {

	    public void run() {
		initialize(); // Set all variables

		loadcontent(); // Get game files

		Framework.gameState = Framework.GameState.RUNNING;
	    }
	};
	threadForInitGame.start();
    }

    private void initialize() { // Start new game
	player = new Rocket();
	landingSpace = new Landingspace();

    }

    private void loadcontent() {
	try {

	    URL backgroundUrl = this.getClass().getResource("/lunar_lander/resources/img/background.png");
	    background = ImageIO.read(backgroundUrl);

	    URL failedUrl = this.getClass().getResource("/lunar_lander/resources/img/failed.png");
	    failed = ImageIO.read(failedUrl);

	} catch (IOException ex) {
	    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
	}
	URL audioURL = getClass().getResource("/lunar_lander/resources/wav/main.wav");
	AudioClip clip = Applet.newAudioClip(audioURL);
	clip.play();

	if (Control.keyboardKeyState(KeyEvent.VK_ENTER)) {
	    clip.stop();
	}
    }

    public void UpdateGame(double gameTime, Point mousePosition) // Get position of rocket
    {
	player.Update();

	if (player.y + player.landerRocketHeight - 10 > landingSpace.y) {
	    if ((player.x > landingSpace.x)
		    && (player.x < landingSpace.x + landingSpace.landingSpaceWidth - player.landerRocketWidth)) {
		if (player.speedY <= player.maxLandingSpeed)
		    player.landed = true;
		else
		    player.crashed = true;
	    } else
		player.crashed = true;

	    Framework.gameState = Framework.GameState.GAMEOVER;
	}
    }

    public void draw(Graphics2D g2d, Point mousePosition) {
	g2d.drawImage(background, 0, 0, Framework.frameWidth,
		Framework.frameHeight, null);

	landingSpace.draw(g2d);

	player.draw(g2d);
    }

    public void drawgameover(Graphics2D g2d, Point mousePosition,
	    double gameTime) // Outputs if landed or crashed
    {
	draw(g2d, mousePosition);

	if (player.landed) {
	    g2d.setColor(Color.green);
	    g2d.drawString("Congrats!", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3);
	    g2d.drawString("You landed in " + gameTime / Framework.SECINNANO + " seconds.", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3 + 20);
	    g2d.drawString("Press Enter to return to the menu.", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3 + 60);
	} else {
	    g2d.drawImage(failed, 0, 0, Framework.frameWidth, Framework.frameHeight, null);
	    g2d.setColor(Color.red);
	    g2d.drawString("Fail!", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3 + 70);
	    g2d.drawString("Press Enter to return to the main menu.", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3 + 90);
	}
    }
}