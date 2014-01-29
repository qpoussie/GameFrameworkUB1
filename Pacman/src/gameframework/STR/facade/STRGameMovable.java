package gameframework.STR.facade;

import java.awt.Rectangle;

import gameframework.game.GameMovable;

public class STRGameMovable extends GameMovable {

	public int renderingSize= 24;
	
	public Rectangle getBoundingBox(int size) {
		renderingSize = size;
		return getBoundingBox();
	}
	
	@Override
	public Rectangle getBoundingBox() {
		return (new Rectangle(0, 0, renderingSize, renderingSize));
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		// TODO Auto-generated method stub
		
	}

}
