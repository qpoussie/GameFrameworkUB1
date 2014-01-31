package gameframeworkExtension;

import java.awt.Point;
import java.awt.Rectangle;

import gameframework.base.Overlappable;
import gameframework.game.GameEntity;

public class OverlappableCursor implements Overlappable, GameEntity {

	private static OverlappableCursor uniqueInstance;
	private static Point position;
	private static Rectangle boundingbox;
	
	private OverlappableCursor() {}
	
	private OverlappableCursor(Point position){
		OverlappableCursor.position = position;
		OverlappableCursor.boundingbox = new Rectangle((int)position.getX(), (int)position.getY(), 1, 1);
	}
	
	public static OverlappableCursor getInstance(Point position){
		if(uniqueInstance == null)
			uniqueInstance = new OverlappableCursor(position);
		return uniqueInstance;
		
	}
	
	@Override
	public Rectangle getBoundingBox() {
		return boundingbox;
	}

	@Override
	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		OverlappableCursor.position = position;
		OverlappableCursor.boundingbox.setBounds((int)position.getX(), (int)position.getY(), 1, 1);
	}

}
