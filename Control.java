/*
 * Creates JPanel for Keyboard/Mouse.
 */
package lunar_lander;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class Control extends JPanel implements KeyListener, MouseListener {

	private static boolean[] keyboardState = new boolean[525]; // Check keyboard

	private static boolean[] mouseState = new boolean[3]; // Check mouse

	public Control() {

		this.setDoubleBuffered(true);
		this.setFocusable(true);

		if (true) // Hide mouse cursor
		{
			BufferedImage blankCursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
			Cursor blankCursor = Toolkit.getDefaultToolkit()
					.createCustomCursor(blankCursorImg, new Point(0, 0), null);
			this.setCursor(blankCursor);
		}

		this.addKeyListener(this); // Receive keyboard

		this.addMouseListener(this); // Receive mouse
	}

	public abstract void draw(Graphics2D g2d); // Draw screen

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		draw(g2d);
	}

	public static boolean keyboardKeyState(int key) // Check for pressed key
	{
		return keyboardState[key];
	}

	public void keyPressed(KeyEvent e) {
		keyboardState[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keyboardState[e.getKeyCode()] = false;
		keyReleasedFramework(e);
	}

	public void keyTyped(KeyEvent e) {
	}

	public abstract void keyReleasedFramework(KeyEvent e);

	public static boolean mouseButtonState(int button) // Check for pressed mouse
	{
		return mouseState[button - 1];
	}

	private void mouseKeyStatus(MouseEvent e, boolean status) // Disable mouse
	{
		if (e.getButton() == MouseEvent.BUTTON1)
			mouseState[0] = status;
		else if (e.getButton() == MouseEvent.BUTTON2)
			mouseState[1] = status;
		else if (e.getButton() == MouseEvent.BUTTON3)
			mouseState[2] = status;
	}

	public void mousePressed(MouseEvent e) {
		mouseKeyStatus(e, true);
	}

	public void mouseReleased(MouseEvent e) {
		mouseKeyStatus(e, false);
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
}