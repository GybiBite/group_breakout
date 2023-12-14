package bozos.pong;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class Launcher {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Pong");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(new Pong(), BorderLayout.CENTER);

		frame.setLocationByPlatform(true);
		frame.setSize(800, 600);

		frame.setBackground(Color.WHITE);
		frame.setVisible(true);
		frame.setResizable(false);

		while (true) {
			frame.repaint();
		}
	}
}

@SuppressWarnings("serial")
class Pong extends JPanel implements KeyListener {
	Game game = new Game();

	long lastTime = System.currentTimeMillis();
	long currTime = System.currentTimeMillis();

	public Pong() {
		setPreferredSize(new Dimension(800, 600));
		setBackground(Color.BLACK);
		setFocusTraversalKeysEnabled(false);
		setFocusable(true);
		addKeyListener(this);
	}

	public void paintComponent(Graphics gx) {
		super.paintComponent(gx);
		Graphics2D g = (Graphics2D) gx;
		int width = 800;
		int height = 600;
		g.setBackground(Color.BLACK); // To make sure you cover the base rectangle!
		g.clearRect(0, 0, width, height);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.WHITE);

		lastTime = currTime;
		currTime = System.currentTimeMillis();

		game.render(g, (currTime - lastTime) / 100F);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			Game.wPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			Game.sPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			Game.upPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			Game.downPressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			Game.wPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			Game.sPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			Game.upPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			Game.downPressed = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		/* Placeholder. Do nothing. */
	}

}