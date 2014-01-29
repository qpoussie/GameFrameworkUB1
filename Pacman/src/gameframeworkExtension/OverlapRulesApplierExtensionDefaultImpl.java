package gameframeworkExtension;

import gameframework.base.Overlap;
import gameframework.game.OverlapRulesApplierDefaultImpl;

import java.util.Vector;

import linkstr.entity.NiceLink;

/**
 * A Utiliser � la place de OverlapRulesApplierDefaultImpl pour les 
 * jeux utilisant le controle � la souris.
 * 
 * Il s'emblerait q'un appel depuis l'introspection ne suit pas le mecanisme de polymorphisme
 * du coup je ne peux pas utiliser cette regle : overlapRule(OverlappableSelection selection, Selectable o)
 * -> oblig� de gerer la selection dans les regles du jeu pour chaques type reel Selectable (ex : NiceLink)
 * 
 */
public abstract class OverlapRulesApplierExtensionDefaultImpl extends
		OverlapRulesApplierDefaultImpl {


	public void applyOverlapRules(Vector<Overlap> overlaps) {
		/*for (Overlap col : overlaps) {
			if (col.getOverlappable1().getClass())
			

		}*/
		super.applyOverlapRules(overlaps);
	}
	
	public void overlapRule(OverlappableSelection selection, Selectable o) {
		System.out.println("coucou");
		selection.addUnit(o);
	}
}
