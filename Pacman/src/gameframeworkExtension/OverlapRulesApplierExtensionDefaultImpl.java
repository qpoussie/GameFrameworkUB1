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
	
	public void applyOverlapRules(Vector<Overlap> overlaps) {
		currentSelection = new ArrayList<Selectable>();
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
	}

	protected void applySpecificOverlapRule(Overlappable e1, Overlappable e2) {
		if(e1 instanceof OverlappableSelection && e2 instanceof Selectable){
			currentSelection.add((Selectable)e2);
			return;
		}
		if(e2 instanceof OverlappableSelection && e1 instanceof Selectable){
			currentSelection.add((Selectable)e1);
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

	protected void reverseParameters(Overlappable e1, Overlappable e2) {
		Method m;
		try {
			m = getClass().getMethod("overlapRule", e2.getClass(),
					e1.getClass());
		} catch (NoSuchMethodException e) {
			return;
		}
		invoke(m, e2, e1);
	}

	protected void invoke(Method m, Overlappable e1, Overlappable e2) {
		try {
			m.invoke(this, e1, e2);
		} catch (Exception e) {
			throw new RuntimeException("Reflective invocation exception", e);
		}
	}

}