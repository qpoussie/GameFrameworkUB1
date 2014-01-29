package gameframeworkExtension;

import gameframework.game.GameUniverse;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Permet la gestion de la souris : 
 * le MouseController construit un OverlappableSelection, il l'ajoute au GameUniverse,
 * puis l'OverlapRulesApplierExtensionDefaultImpl le recup�re et se charge de les remplir de Selectable
 * 
 */

public class MouseController extends MouseAdapter{
	

	private GameUniverse gameUniverse;
	private DrawableGlobalSelection drawableSelection;
	private List<Selectable> currentSelection;
	
	public MouseController(GameUniverse gameUniverse){
		this.gameUniverse = gameUniverse;
	}
	
	public void mousePressed(MouseEvent e){
		drawableSelection = new DrawableGlobalSelection(e.getPoint());
		gameUniverse.addGameEntity(drawableSelection);
	}
	
	public void mouseReleased(MouseEvent e){
		gameUniverse.removeGameEntity(drawableSelection);
		OverlappableSelection overlappableSelection = new OverlappableSelection(drawableSelection);
		if(overlappableSelection.getBoundingBox() != null)
			gameUniverse.addGameEntity(overlappableSelection);
	}
	
	public void mouseDragged(MouseEvent e){
		drawableSelection.setDragActualPos(e.getPoint());
	}
}
