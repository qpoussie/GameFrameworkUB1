package linkstr.entity;

import gameframework.base.Drawable;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;
import gameframework.game.SpriteManager;
import gameframework.game.SpriteManagerDefaultImpl;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Fairy extends GameMovable implements Drawable, GameEntity,
		Overlappable {
	protected final SpriteManager spriteManager;
	public static final int RENDERING_SIZE = 32;
	protected boolean movable = true;

	public Fairy(Canvas defaultCanvas) {
		spriteManager = new SpriteManagerDefaultImpl("images/fairy.gif",
				defaultCanvas, RENDERING_SIZE, 2);
		spriteManager.setTypes("move");
	}


	public void draw(Graphics g) {
		String spriteType = "move";
		spriteManager.setType(spriteType);	
		spriteManager.draw(g, getPosition());
		
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		if (movable) {
			spriteManager.increment();
		}
	}

	public Rectangle getBoundingBox() {
		return (new Rectangle(0, 0, RENDERING_SIZE, RENDERING_SIZE));
	}
}
