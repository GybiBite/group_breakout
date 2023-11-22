package bozos.breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Game implements Screen {

	/** This variable stores the location and size of the ball */
	Ellipse2D.Double ball = new Ellipse2D.Double(0, 0, 10, 10);
	
	/** This is currently just an example velocity to make sure the ball works.</p>
	 * This can be modified later when the game is more developed. */
	float bvX = 10, bvY = 10;

	/**
	 * This method is the primary method to be called each game tick, as per the loop specified in the main function
	 * 
	 * @see Launcher#main(String[])
	 * @param g Graphics2D object for drawing things to the screen with
	 * @param delta Variable representing the amount of time in between the last frame and the current frame. Necessary for consistent game speed across devices.
	 */
	@Override
	public void render(Graphics2D g, float delta) {
		tick(delta);
		g.setColor(Color.WHITE);
		g.draw(ball);

	}

	/**
	 * Local tick function for handling all game logic (although some of it is expected to be separated in discrete methods)
	 * @param delta Amount of time in seconds since last frame (Identical to delta given in {@link Game#render(Graphics2D, float)} )
	 */
	private void tick(float delta) {
		ball.x += bvX * delta;
		ball.y += bvY * delta;
	}
}
