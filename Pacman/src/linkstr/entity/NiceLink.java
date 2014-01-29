package linkstr.entity;

import gameframework.base.Drawable;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;
import gameframework.game.GameMovable;
import gameframework.game.SpriteManager;
import gameframework.game.SpriteManagerDefaultImpl;
import gameframeworkExtension.DrawableUnitSelection;
import gameframeworkExtension.Selectable;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class NiceLink extends GameMovable implements Drawable, GameEntity,
		Overlappable, Selectable {
	private DrawableUnitSelection drawableUnitSelection = new DrawableUnitSelection();
	protected final SpriteManager spriteManager;
	public static final int RENDERING_SIZE = 32;
	protected boolean movable = true;
	protected boolean selected = false;
	protected boolean dead = false;

	public NiceLink(Canvas defaultCanvas) {
		spriteManager = new SpriteManagerDefaultImpl("images/brownLink.png",
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
		if(selected == true){
			drawableUnitSelection.draw(g, getPosition(), RENDERING_SIZE);
		}
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
