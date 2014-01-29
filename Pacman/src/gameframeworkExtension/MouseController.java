package gameframeworkExtension;

import gameframework.STR.facade.DrawableOverlappableGameEntity;
import gameframework.base.MoveStrategyStraightLine;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.GameUniverse;
import gameframework.game.MoveBlockerChecker;
import gameframework.game.MoveBlockerCheckerDefaultImpl;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import linkstr.entity.soldier.SelectableArmedUnit;

/**
 * Singleton qui permet la gestion de la souris : 
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

	public void mouseClicked(MouseEvent e){
		switch (e.getButton()){
		
		case MouseEvent.BUTTON1:
			OverlappableSelection overlappableSelection = OverlappableSelection.getInstance();
			overlappableSelection.setPositionAndRectangle(new Rectangle((int)e.getPoint().getX(), (int)e.getPoint().getY(), (int)e.getPoint().getX()+1, (int)e.getPoint().getY()+1));
			break;
			
		case MouseEvent.BUTTON2:
			break;
			
		case MouseEvent.BUTTON3:
			for(Selectable s : currentSelection){
				s.setSelected(true);
				MoveStrategyStraightLine strat = new MoveStrategyStraightLine(((DrawableOverlappableGameEntity) s).getPosition(), e.getPoint());
				GameMovableDriverDefaultImpl niceLinkDriver = new GameMovableDriverDefaultImpl();
				niceLinkDriver.setStrategy(strat);
				niceLinkDriver.setmoveBlockerChecker(new MoveBlockerCheckerDefaultImpl());
				((SelectableArmedUnit)s).setDriver(niceLinkDriver);
			}
			break;
		}
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
