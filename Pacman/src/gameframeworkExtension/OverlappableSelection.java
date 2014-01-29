package gameframeworkExtension;

import gameframework.base.Overlappable;
import gameframework.game.GameEntity;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

/**
 * Une GameEntity Overlappable qui sert à trouver les entitées à selectionner.
 * 
 */
public class OverlappableSelection implements Overlappable, GameEntity {
	
	private Point position;
	private Rectangle boundingBox;
	private List<Selectable> selectedUnits;

	public OverlappableSelection(DrawableGlobalSelection drawableSelection) {
		this.position = drawableSelection.getPosition();
		this.boundingBox = drawableSelection.getRect();
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

}
