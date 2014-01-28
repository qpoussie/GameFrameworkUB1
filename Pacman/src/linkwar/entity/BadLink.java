package linkwar.entity;

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

public class BadLink extends GameMovable implements Drawable, GameEntity,
		Overlappable {
	protected final SpriteManager spriteManager;
	public static final int RENDERING_SIZE = 24;
	protected boolean movable = true;
	protected boolean dead = false;

	public BadLink(Canvas defaultCanvas) {
		spriteManager = new SpriteManagerDefaultImpl("images/darklink.png",
				defaultCanvas, RENDERING_SIZE, 3);
		spriteManager.setTypes(
				//
				"up", "right", "down",
				"left",//
				"hit-up", "hit-right", "hit-down",
				"hit-left", //
				"dead", "static");
	}

	public void die() {
		dead = true;
	}


	public void draw(Graphics g) {
		String spriteType = "";
		Point tmp = getSpeedVector().getDirection();
		
		if(dead){
			spriteType += "dead";
		} else {
			movable = true;
			if (tmp.getX() == 1) {
				spriteType += "right";
			} else if (tmp.getX() == -1) {
				spriteType += "left";
			} else if (tmp.getY() == 1) {
				spriteType += "down";
			} else if (tmp.getY() == -1) {
				spriteType += "up";
			} else {
				spriteType = "static";
				spriteManager.reset();
				movable = false;
			}
		}
		spriteManager.setType(spriteType);
		spriteManager.draw(g, getPosition());
		
		//this.die();

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
