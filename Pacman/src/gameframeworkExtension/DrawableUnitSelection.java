package gameframeworkExtension;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * sert à afficher l'elipse de selection des unitée.
 * 
 */

public class DrawableUnitSelection{

	public void draw(Graphics g, Point position, int renderingSize){
		Color oldColor = g.getColor();
		g.setColor(new Color(0,255,0,180));
		g.drawOval((int)position.getX(), (int)position.getY() + renderingSize*3/5, renderingSize, renderingSize*2/5);
		g.setColor(oldColor);
		
	}
	
}

