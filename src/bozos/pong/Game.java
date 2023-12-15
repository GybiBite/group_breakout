package bozos.pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.JComponent;

public class Game {

	/** This variable stores the location and size of the ball */
	Ellipse2D.Double ball = new Ellipse2D.Double(400, 300, 10, 10);
	/** This variable stores the location and size of player one's paddle */
	Rectangle2D.Double paddleOne = new Rectangle2D.Double(20, 400, 10, 100);
	/** This variable stores the location and size of player two's paddle */
	Rectangle2D.Double paddleTwo = new Rectangle2D.Double(755, 250, 10, 100);
	
	Random rand = new Random();

	/** The width of the screen, in pixels. */
	static int S_WIDTH = 800;
	/** The height of the screen, in pixels. */
	static int S_HEIGHT = 560;

	/** Indicates whether the corresponding key is pressed on the keyboard */
	static boolean wPressed, sPressed, upPressed, downPressed;

	/**	Velocity of the ball */
	float bvX = 10, bvY = 5;

	// Player Scores :D
	int p1Score = 0;
	int p2Score = 0;

	/** Round delay for resetting the ball (Two seconds) */
	static final long BALL_WAIT_TIME = 2000;
	/** Timer placeholder for ball reset delay */
	static long ballTimer;

	/** Bit that indicates whether the ball is being reset or not */
	boolean ballReset = false;

	/**
	 * This method is the primary method to be called each game tick, as per the
	 * loop specified in the main function
	 * 
	 * @see Launcher#main(String[])
	 * @param g     Graphics2D object for drawing things to the screen with
	 * @param delta Variable representing the amount of time in between the last
	 *              frame and the current frame. Necessary for consistent game speed
	 *              across devices.
	 * @return 
	 */
	
	 
	public void render(Graphics2D g, float delta) {
		tick(delta);
		g.setColor(Color.WHITE);
		g.draw(paddleOne);
		g.draw(paddleTwo);
		g.draw(ball);
		g.setFont(new Font("Arial",Font.PLAIN,60 ));
		if(p1Score == 0)
		{
			g.drawString("0", S_WIDTH/2-220, 100);
		}
		if(p1Score == 1)
		{
			g.drawString("1", S_WIDTH/2-220, 100);
		}
		if(p1Score == 2)
		{
			g.drawString("2", S_WIDTH/2-220, 100);
		}
		if(p1Score == 3)
		{
			g.drawString("3", S_WIDTH/2-220, 100);
		}
		if(p2Score == 0)
		{
			g.drawString("0", S_WIDTH/2+220, 100);
		}
		if(p2Score == 1)
		{
			g.drawString("1", S_WIDTH/2+220, 100);
		}
		if(p2Score == 2)
		{
			g.drawString("2", S_WIDTH/2+220, 100);
		}
		if(p2Score == 3)
		{
			g.drawString("3", S_WIDTH/2+220, 100);
		}
		if(p1Score == 3)
		{
			g.drawString("Player 1 wins!", S_WIDTH/2-180, 100);
		}
		if (p2Score ==3)
		{
			g.drawString("Player 2 wins!", S_WIDTH/2-180, 100);
		}
		
			
	
		
		
	}

	{
		
	}

	/**
	 * Local tick function for handling all game logic (although some of it is expected to be separated in discrete methods)
	 * @param delta Amount of time in seconds since last frame (Identical to delta given in {@link Game#render(Graphics2D, float)} )
	 */
	
		
	
			

		
	
	private void tick(float delta) {
		if (p1Score ==3)
		{
			bvY = 0;
			bvX = 0;
			

			
		}
		if (p2Score ==3)
		{
			bvY = 0;
			bvX = 0;
		
		}
		
		if (System.currentTimeMillis() - ballTimer >= BALL_WAIT_TIME && ballReset) {
			int newDirection = rand.nextBoolean() ? 1 : -1;
			ball.x = 400;
			ball.y = 300;
			bvY = 5;
			bvX = 10 * newDirection;
			ballReset = false;
			delta = 0;
		}
		
		if (!ballReset) {
			ball.x += bvX * delta;
			ball.y += bvY * delta;
		}
	
		/* If the ball hits the top or bottom of the screen */
		if ((ball.y + ball.width >= S_HEIGHT && bvY > 0) || (ball.y <= 0 && bvY < 0)) {
			bvY *= -1;
		}
		
		/* If the ball hits the right side of the screen */
		if  (ball.x >= 785 - ball.width && !ballReset)
		{
			p1Score += 1;
			ballReset = true;
			ballTimer = System.currentTimeMillis();
		}
		
		/* If the ball hits the left side of the screen */
		if (ball.x <= 0 && !ballReset) {
			p2Score += 1;
			ballReset = true;
			ballTimer = System.currentTimeMillis();
		}
		
		/* Here's the ugliest if statement you'll ever see */
		if (
				ball.x - ball.width <= paddleOne.x + paddleOne.width / 2 && // If the ball's X is at or behind paddleOne's edge...
				ball.y >= paddleOne.y && // ...and the ball is under the top edge of the paddle...
				ball.y <= paddleOne.y + paddleOne.height - ball.height && // ...but above the bottom edge...
				bvX < 0) { // ...and moving to the left, then:
			
			bvX *= -1;
			bvX += 5;
			if(bvY < 0)
			{
				bvY -= 5;
			}
			if(bvY > 0)
			{
				bvY += 5;
			}
		}
		
		if((ball.x >= paddleTwo.x-paddleTwo.width/2)&&(ball.y<= paddleTwo.y + paddleTwo.height/2 - ball.height)&&(ball.y >= paddleTwo.y - paddleTwo.height/2)&&(bvX >0))
		{
			bvX = bvX *= -1;
			bvX = bvX -5;
			if(bvY < 0)
			{
				bvY -= 5;
			}
			if(bvY > 0)
			{
				bvY += 5;
			}
		}
		
		if (wPressed) {
			paddleOne.y -= 0.1f;
		}
		if (sPressed) {
			paddleOne.y += 0.1f;
		}
		if (upPressed) {
			paddleTwo.y -= 0.1f;
		}
		if (downPressed) {
			paddleTwo.y += 0.1f;
		}
	}
}
