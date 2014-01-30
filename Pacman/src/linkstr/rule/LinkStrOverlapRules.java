package linkstr.rule;

import java.util.Vector;

import linkstr.entity.soldier.ArmedUnitSoldier;
import linkstr.entity.soldier.SelectableArmedUnit;
import gameframework.base.ObservableValue;
import gameframework.base.Overlap;
import gameframework.game.GameUniverse;
import gameframeworkExtension.OverlapRulesApplierExtensionDefaultImpl;

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
	
	public void overlapRule(SelectableArmedUnit ally, ArmedUnitSoldier enemy){
		if(ally.alive() && enemy.alive()){
			enemy.parry(ally.strike());
			ally.parry(enemy.strike());
			
			
			if(!enemy.alive())
				badSoldier.setValue(badSoldier.getValue()-1);
			 	//System.out.println("enemy "+enemy.getName()+" est mort.\n");
			if(!ally.alive())
				soldier.setValue(soldier.getValue()-1);				
				//System.out.println("allié "+ally.getName()+" est mort.\n");
		}
	}

}
