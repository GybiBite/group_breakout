package bozos.pong;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Game implements IScreen {

	/** This variable stores the location and size of the ball */
	Ellipse2D.Double ball = new Ellipse2D.Double(400, 300, 10, 10);
	Rectangle2D.Double paddleOne = new Rectangle2D.Double(20, 250, 10, 100);
	Rectangle2D.Double paddleTwo = new Rectangle2D.Double(755, 250, 10, 100);
	
	/** The width of the screen, in pixels. */
	static final int S_WIDTH = 800;
	/** The height of the screen, in pixels. */
	static final int S_HEIGHT = 600;
	
	/** Indicates whether the corresponding key is pressed on the keyboard */
	static boolean wPressed, sPressed, upPressed, downPressed;
	
	/** This is currently just an example velocity to make sure the ball works.</p>
	 * This can be modified later when the game is more developed. */
	float bvX = 10, bvY = 0;

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
		g.draw(paddleOne);
		g.draw(paddleTwo);
		g.draw(ball);
	}

	/**
	 * Local tick function for handling all game logic (although some of it is expected to be separated in discrete methods)
	 * @param delta Amount of time in seconds since last frame (Identical to delta given in {@link Game#render(Graphics2D, float)} )
	 */
	private void tick(float delta) {
		ball.x += bvX * delta;
		ball.y += bvY * delta;
		if((ball.y >= 556- ball.width)||( ball.y <= 0))
		{
			bvY = bvY *= -1;
		}
		if  ((ball.x >= 785 - ball.width)||( ball.x <= 0+ball.width))
		{
			ball.x = 400;
			ball.y = 300;
			bvY = 5;
			bvX =10;
			bvX = bvX *= -1;
		}
		if((ball.x <= paddleOne.x+paddleOne.width+ball.width)&&(ball.y<= paddleOne.y + paddleOne.height/2)&&(ball.y >= paddleOne.y - paddleOne.height/2))
		{
			
			bvX = bvX *= -1;
			bvX = bvX +5;
			
			
		}
		if((ball.x >= paddleTwo.x-paddleTwo.width-ball.width)&&(ball.y<= paddleTwo.y + paddleTwo.height/2)&&(ball.y >= paddleTwo.y - paddleTwo.height/2))
		{
			
			bvX = bvX *= -1;
			bvX = bvX -5;
			
		}
	}
}
