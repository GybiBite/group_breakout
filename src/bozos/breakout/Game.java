package bozos.breakout;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Game implements Screen {

	Ellipse2D.Double ball = new Ellipse2D.Double(0, 0, 10, 10);
	float bvX = 10, bvY = 10;

	@Override
	public void render(Graphics2D g, float delta) {
		tick(delta);
		g.setColor(Color.WHITE);
		g.draw(ball);

	}

	private void tick(float delta) {
		ball.x += bvX * delta;
		ball.y += bvY * delta;
	}
}
