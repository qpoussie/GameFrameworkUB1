package gameframeworkExtension;

import gameframework.base.Movable;
import gameframework.game.GameEntity;
import gameframework.game.GameMovableDriver;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.GameUniverse;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import linkstr.entity.soldier.SelectableArmedUnit;
import linkstr.rule.MoveStrategyStraightLineGoodStop;

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
	private OverlappableSelection overlappableSelection;
	private OverlappableCursor overlappableCursor;
	
	private List<Selectable> currentSelection;
	private Focusable currentFocused;

	private MouseController(){
		drawableSelection = DrawableGlobalSelection.getInstance();
		overlappableSelection = OverlappableSelection.getInstance();
		overlappableCursor = OverlappableCursor.getInstance(new Point(0,0));
	}

	public static MouseController getInstance(){
		if(uniqueInstance == null)
			uniqueInstance = new MouseController();
		return uniqueInstance;
	}
	
	public void setGameUnivers(GameUniverse gameUniverse){
		this.gameUniverse = gameUniverse;
		gameUniverse.addGameEntity(overlappableCursor);
	}

	public void mousePressed(MouseEvent e){
		switch(e.getButton()){
		case MouseEvent.BUTTON1:
			drawableSelection.setOriginePoint(e.getPoint());
			gameUniverse.addGameEntity(drawableSelection);
			break;
		case MouseEvent.BUTTON3:
			if(currentSelection != null){
				for(Selectable s : currentSelection){
					s.setSelected(true);
					int xOffset = (int)((Movable)s).getBoundingBox().getWidth()/2;
					int yOffset = (int)((Movable)s).getBoundingBox().getHeight();
					MoveStrategyStraightLineGoodStop strat = new MoveStrategyStraightLineGoodStop(((Movable) s).getPosition(), new Point((int)(e.getPoint().getX()-xOffset), (int)(e.getPoint().getY()-yOffset)));
					GameMovableDriver driver = ((SelectableArmedUnit) s).getDriver(); 
					((GameMovableDriverDefaultImpl) driver).setStrategy(strat);
				}
			}
			break;
		}
	}

	public void mouseReleased(MouseEvent e){
		if (e.getButton() == MouseEvent.BUTTON1){
			gameUniverse.removeGameEntity(drawableSelection);
			overlappableSelection.setPositionAndRectangle(drawableSelection);
			if(overlappableSelection.getBoundingBox() != null){
				gameUniverse.addGameEntity(overlappableSelection);
				overlappableSelection.setActive(true);
			}
		}
	}

	public void mouseDragged(MouseEvent e){
		if(drawableSelection != null)
			drawableSelection.setDragActualPos(e.getPoint());
	}

	public void mouseClicked(MouseEvent e){
		if(e.getButton() == MouseEvent.BUTTON1){
			OverlappableSelection overlappableSelection = OverlappableSelection.getInstance();
			overlappableSelection.setPositionAndRectangle(new Rectangle((int)e.getPoint().getX(), (int)e.getPoint().getY(), 1, 1));
			overlappableSelection.setActive(true);
			
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		overlappableCursor.setPosition(e.getPoint());	
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

	public void setFocused(Focusable currentFocused) {
		System.out.println("coucou");
		if(this.currentFocused != null)
			currentFocused.setFocused(false);
		this.currentFocused = currentFocused;
		currentFocused.setFocused(true);
		
	}
}
