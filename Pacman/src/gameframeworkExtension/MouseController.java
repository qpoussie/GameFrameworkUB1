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
	
	private static MouseController uniqueInstance;
	private GameUniverse gameUniverse;
	private DrawableGlobalSelection drawableSelection;
	private List<Selectable> currentSelection;
	
	private MouseController(){}

	public static MouseController getInstance(){
		if(uniqueInstance == null)
			uniqueInstance = new MouseController();
		return uniqueInstance;
	}
	
	public void setGameUnivers(GameUniverse gameUniverse){
		this.gameUniverse = gameUniverse;
	}

	public void mousePressed(MouseEvent e){
		drawableSelection = DrawableGlobalSelection.getInstance();
		drawableSelection.setOriginePoint(e.getPoint());
		gameUniverse.addGameEntity(drawableSelection);
	}
	
	public void mouseReleased(MouseEvent e){
		gameUniverse.removeGameEntity(drawableSelection);
		OverlappableSelection overlappableSelection = OverlappableSelection.getInstance();
		overlappableSelection.setPositionAndRectangle(drawableSelection);
		if(overlappableSelection.getBoundingBox() != null){
			gameUniverse.addGameEntity(overlappableSelection);
			overlappableSelection.setActive(true);
		}
	}
	
	public void mouseDragged(MouseEvent e){
		drawableSelection.setDragActualPos(e.getPoint());
	}

	public void setUniverse(GameUniverse universe) {
		gameUniverse = universe;
	}
	
	public void setSelection(List<Selectable> currentSelection){
		if(this.currentSelection != null)
			for(Selectable s : this.currentSelection){
				s.setSelected(false);
			}
		this.currentSelection = currentSelection;
		for(Selectable s : currentSelection){
			s.setSelected(true);
		}
	}
}
