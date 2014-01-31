package linkstr.entity.soldier;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class DrawableHealthBar {

	private static final Color barColors[] = {Color.green, Color.yellow, Color.orange, Color.red};
	private static final int BARHEIGHT = 4;
	
	public void draw(Graphics g, Point position, int renderingSize, float totalHealth, float currentHealth){
		Color oldColor = g.getColor();
		Color barColor;
		
		float ratio = currentHealth/totalHealth;
		
		if (ratio>0.8)
			barColor = barColors[0];
		else if(ratio > 0.6)
			barColor = barColors[1];
		else if(ratio > 0.3)
			barColor = barColors[2];
		else
			barColor = barColors[3];
		
		g.setColor(barColor);
		
		g.fillRect((int)position.getX(), (int)position.getY(), (int) (renderingSize*ratio), BARHEIGHT);
		g.setColor(oldColor);
		
	}
}
