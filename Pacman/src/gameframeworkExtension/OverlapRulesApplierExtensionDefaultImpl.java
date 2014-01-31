package gameframeworkExtension;

import gameframework.base.Overlap;
import gameframework.base.Overlappable;
import gameframework.game.GameUniverse;
import gameframework.game.OverlapRulesApplierDefaultImpl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * A Utiliser a la place de OverlapRulesApplierDefaultImpl pour les 
 * jeux utilisant le controle a la souris.
 * 
 */
public abstract class OverlapRulesApplierExtensionDefaultImpl extends
		OverlapRulesApplierDefaultImpl {

	protected GameUniverse universe;
	protected List<Selectable> currentSelection;
	protected Focusable currentFocused;
	
	public void applyOverlapRules(Vector<Overlap> overlaps) {
		currentSelection = new ArrayList<Selectable>();
		currentFocused = null;
		for (Overlap col : overlaps) {
			applySpecificOverlapRule(col.getOverlappable1(),
					col.getOverlappable2());
		}
		OverlappableSelection selection = OverlappableSelection.getInstance();
		if(selection.isActive()){
			selection.setActive(false);
			universe.removeGameEntity(OverlappableSelection.getInstance());
			MouseController.getInstance().setSelection(currentSelection);
		}
		if(currentFocused != null)
			currentFocused.setFocused(true);
	}

	protected void applySpecificOverlapRule(Overlappable e1, Overlappable e2) {
		if(e1 instanceof OverlappableSelection && e2 instanceof Selectable){
			currentSelection.add((Selectable)e2);
			return;
		}
		else if(e2 instanceof OverlappableSelection && e1 instanceof Selectable){
			currentSelection.add((Selectable)e1);
			return;
		}
		else if(e1 instanceof OverlappableCursor && e2 instanceof Selectable){
			currentFocused = (Focusable)e2;
			return;
		}
		else if(e2 instanceof OverlappableCursor && e1 instanceof Selectable){
			currentFocused = ((Focusable)e1);
			return;
		}
		
		Method m;
		try {
			m = getClass().getMethod("overlapRule", e1.getClass(),
					e2.getClass());
		} catch (NoSuchMethodException e) {
			// automatic commutativity handling
			reverseParameters(e1, e2);
			return;
		}
		invoke(m, e1, e2);
	}

}