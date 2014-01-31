package gameframeworkExtension;

import gameframework.base.Drawable;
import gameframework.game.GameEntity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * Une GameEntity Singleton Drawable qui sert à afficher le rectangle vert qui suit le drag de la souris lors de la selection
 * 
 */
public class DrawableGlobalSelection implements Drawable, GameEntity {

	private static DrawableGlobalSelection uniqueInstance;
	private Point dragOrigine;
	private Point dragActualPos;
	private Point position;
	private Rectangle boundingRect;
	
	private DrawableGlobalSelection() {}
	
	public static DrawableGlobalSelection getInstance(){
		if(uniqueInstance == null)
			uniqueInstance = new DrawableGlobalSelection();
		return uniqueInstance;
	}
	
	public void setOriginePoint(Point dragOrigine){
		this.dragOrigine = dragOrigine;
		this.dragActualPos = new Point((int)dragOrigine.getX()+1,(int)dragOrigine.getY()+1);
		this.boundingRect = new Rectangle((int)(dragOrigine.getX()),(int)(dragOrigine.getY()), 1, 1);
	}

	public void setDragActualPos(Point dragActualPos){
		this.dragActualPos = dragActualPos;
	}
	
	@Override
	public void draw(Graphics g) {
		// drawRectangle et fillRectangle ne supportent pas de width et de height négatives, nous avons donc du dupliquer le code pour chaque quartiers du plan.
		if((dragOrigine.getX() < dragActualPos.getX()) && (dragOrigine.getY() < dragActualPos.getY())){
			Color oldColor = g.getColor();
			g.setColor(new Color(0,255,0,180));
			g.drawRect((int)dragOrigine.getX(), (int)dragOrigine.getY(), (int)(dragActualPos.getX() - dragOrigine.getX()), (int)(dragActualPos.getY() - dragOrigine.getY()));
			g.setColor(new Color(0,255,0,60));
			g.fillRect((int)dragOrigine.getX(), (int)dragOrigine.getY(), (int)(dragActualPos.getX() - dragOrigine.getX()), (int)(dragActualPos.getY() - dragOrigine.getY()));
			g.setColor(oldColor);
			position = new Point(dragOrigine);
			boundingRect = new Rectangle((int)dragOrigine.getX(), (int)dragOrigine.getY(), (int)(dragActualPos.getX() - dragOrigine.getX()), (int)(dragActualPos.getY() - dragOrigine.getY()));
		}
		else if((dragOrigine.getX() < dragActualPos.getX()) && (dragOrigine.getY() > dragActualPos.getY())){
			Color oldColor = g.getColor();
			g.setColor(new Color(0,255,0,180));
			g.drawRect((int)dragOrigine.getX(), (int)dragActualPos.getY(), (int)(dragActualPos.getX() - dragOrigine.getX()), (int)(dragOrigine.getY() - dragActualPos.getY()));
			g.setColor(new Color(0,255,0,60));
			g.fillRect((int)dragOrigine.getX(), (int)dragActualPos.getY(), (int)(dragActualPos.getX() - dragOrigine.getX()), (int)(dragOrigine.getY() - dragActualPos.getY()));
			g.setColor(oldColor);		
			position = new Point((int)dragOrigine.getX(), (int)dragActualPos.getY());
			boundingRect = new Rectangle((int)dragOrigine.getX(), (int)dragActualPos.getY(), (int)(dragActualPos.getX() - dragOrigine.getX()), (int)(dragOrigine.getY() - dragActualPos.getY()));
		}
		else if((dragOrigine.getX() > dragActualPos.getX()) && (dragOrigine.getY() > dragActualPos.getY())){
			Color oldColor = g.getColor();
			g.setColor(new Color(0,255,0,180));
			g.drawRect((int)dragActualPos.getX(), (int)dragActualPos.getY(), (int)(dragOrigine.getX() - dragActualPos.getX()), (int)(dragOrigine.getY() - dragActualPos.getY()));
			g.setColor(new Color(0,255,0,60));
			g.fillRect((int)dragActualPos.getX(), (int)dragActualPos.getY(), (int)(dragOrigine.getX() - dragActualPos.getX()), (int)(dragOrigine.getY() - dragActualPos.getY()));
			g.setColor(oldColor);
			position = new Point(dragActualPos);
			boundingRect = new Rectangle((int)dragActualPos.getX(), (int)dragActualPos.getY(), (int)(dragOrigine.getX() - dragActualPos.getX()), (int)(dragOrigine.getY() - dragActualPos.getY()));
		}
		else if((dragOrigine.getX() > dragActualPos.getX()) && (dragOrigine.getY() < dragActualPos.getY())){
			Color oldColor = g.getColor();
			g.setColor(new Color(0,255,0,180));
			g.drawRect((int)dragActualPos.getX(), (int)dragOrigine.getY(), (int)(dragOrigine.getX() - dragActualPos.getX()), (int)(dragActualPos.getY() - dragOrigine.getY()));
			g.setColor(new Color(0,255,0,60));
			g.fillRect((int)dragActualPos.getX(), (int)dragOrigine.getY(), (int)(dragOrigine.getX() - dragActualPos.getX()), (int)(dragActualPos.getY() - dragOrigine.getY()));
			g.setColor(oldColor);
			position = new Point((int)dragActualPos.getX(), (int)dragOrigine.getY());
			boundingRect = new Rectangle((int)dragActualPos.getX(), (int)dragOrigine.getY(), (int)(dragOrigine.getX() - dragActualPos.getX()), (int)(dragActualPos.getY() - dragOrigine.getY()));
		}
		
	}

	public Point getPosition() {
		return position;
	}

	public Rectangle getRect() {
		return boundingRect;
	}

}
