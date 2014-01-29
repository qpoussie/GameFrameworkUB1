package gameframeworkExtension;

import gameframework.base.Drawable;
import gameframework.base.Overlappable;
import gameframework.game.GameEntity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * Une GameEntity Singleton Overlappable qui sert à trouver les entitées à selectionner.
 * 
 */
public class OverlappableSelection implements Overlappable, GameEntity {
	
	
	private static OverlappableSelection uniqueInstance;
	private Point position;
	private Rectangle boundingBox;
	private boolean active;
	private List<Selectable> selectedUnits;

	private OverlappableSelection() {}
	
	public static OverlappableSelection getInstance(){
		if(uniqueInstance == null)
			uniqueInstance = new OverlappableSelection();
		return uniqueInstance;
	}
	
	public void setPositionAndRectangle(DrawableGlobalSelection drawableSelection){
		this.position = drawableSelection.getPosition();
		this.boundingBox = drawableSelection.getRect();
		selectedUnits = new ArrayList<Selectable>();
	}

	public void setPositionAndRectangle(Rectangle rectangle) {
		this.position = rectangle.getLocation();
		this.boundingBox = rectangle;
		selectedUnits = new ArrayList<Selectable>();
	}
	
	@Override
	public Rectangle getBoundingBox() {
		return boundingBox;
	}

	@Override
	public Point getPosition() {
		return position;
	}

	public void addUnit(Selectable o) {
		selectedUnits.add(o);
	}

	public void setActive(boolean b) {
		active = b;		
	}

	public boolean isActive() {
		return active;
	}


}
