package linkstr.entity;
import gameframework.base.Drawable;
import gameframework.base.DrawableImage;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Grave implements Drawable, GameEntity, Overlappable {
	protected DrawableImage image = null;
	protected Point position;
	public static final int RENDERING_SIZE = 32;

	public Grave(Canvas defaultCanvas,boolean bad, Point pos) {
		if(bad)
			image = new DrawableImage("images/grave.png", defaultCanvas);
		else
			image = new DrawableImage("images/graveNice.png", defaultCanvas);
		position = pos;
	}

	public Point getPosition() {
		return position;
	}

	public void draw(Graphics g) {
		g.drawImage(image.getImage(), (int) getPosition().getX(),
				(int) getPosition().getY(), RENDERING_SIZE, RENDERING_SIZE,
				null);

	}

	public Rectangle getBoundingBox() {
		return (new Rectangle((int) position.getX(), (int) position.getY(),
				RENDERING_SIZE, RENDERING_SIZE));
	}
}

