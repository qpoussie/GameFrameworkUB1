package linkstr.rule;

import gameframework.base.ObservableValue;
import gameframework.base.Overlap;
import gameframework.game.GameUniverse;
import gameframeworkExtension.OverlapRulesApplierExtensionDefaultImpl;

import java.util.Vector;

import linkstr.entity.Fairy;
import linkstr.entity.OffensiveWeapon;
import linkstr.entity.soldier.ArmedUnitSoldier;
import linkstr.entity.soldier.FocusableArmedUnit;
import linkstr.entity.soldier.SelectableArmedUnit;

public class LinkStrOverlapRules extends OverlapRulesApplierExtensionDefaultImpl{

	private final ObservableValue<Integer> soldier;
	private final ObservableValue<Integer> badSoldier;

	public LinkStrOverlapRules(ObservableValue<Integer> soldier, ObservableValue<Integer> badSoldier){
		this.soldier = soldier;
		this.badSoldier = badSoldier;
	}

	@Override
	public void applyOverlapRules(Vector<Overlap> overlappables) {
		super.applyOverlapRules(overlappables);
	}

	@Override
	public void setUniverse(GameUniverse universe) {
		this.universe = universe;

	}

	public void overlapRule(SelectableArmedUnit ally, FocusableArmedUnit enemy){
		if(ally.alive() && enemy.alive()){
			enemy.parry(ally.strike());
			ally.parry(enemy.strike());

			if(!enemy.alive())
				badSoldier.setValue(badSoldier.getValue()-1);
			if(!ally.alive())
				soldier.setValue(soldier.getValue()-1);				
		}
	}	
	public void overlapRule(FocusableArmedUnit soldier, Fairy heal){
		if(soldier.getHealthPoints() < 100){
			this.universe.removeGameEntity(heal);
			soldier.heal();
		}
	}

	public void overlapRule(SelectableArmedUnit soldier, Fairy heal){
		if(soldier.getHealthPoints() < 100){
			this.universe.removeGameEntity(heal);
			soldier.heal(); 
		}
	}

	public void overlapRule(FocusableArmedUnit soldier, OffensiveWeapon weapon){
		if(!soldier.isOffensive()){
			this.universe.removeGameEntity(weapon);
			soldier.addEquipment("Offensive");
		}
	}

	public void overlapRule(SelectableArmedUnit soldier, OffensiveWeapon weapon){
		if(!soldier.isOffensive()){
			this.universe.removeGameEntity(weapon);
			soldier.addEquipment("Offensive");
		}
	}

}
