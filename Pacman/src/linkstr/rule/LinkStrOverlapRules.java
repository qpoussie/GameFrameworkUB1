package linkstr.rule;

import java.awt.Canvas;
import java.util.Vector;

import linkstr.entity.Fairy;
import linkstr.entity.Grave;
import linkstr.entity.OffensiveWeapon;
import linkstr.entity.soldier.ArmedUnit;
import linkstr.entity.soldier.ArmedUnitSoldier;
import linkstr.entity.soldier.SelectableArmedUnit;
import gameframework.base.ObservableValue;
import gameframework.base.Overlap;
import gameframework.game.CanvasDefaultImpl;
import gameframework.game.GameUniverse;
import gameframeworkExtension.OverlapRulesApplierExtensionDefaultImpl;

public class LinkStrOverlapRules extends OverlapRulesApplierExtensionDefaultImpl{

	private final ObservableValue<Integer> soldier;
	private final ObservableValue<Integer> badSoldier;
	private final Canvas canvas = new CanvasDefaultImpl();

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

			if(!enemy.alive()){
				badSoldier.setValue(badSoldier.getValue()-1);
				universe.removeGameEntity(enemy);
				universe.addGameEntity(new Grave(canvas , true, enemy.getPosition()));				
				//System.out.println("enemy "+enemy.getName()+" est mort.\n");
			}
			{
				if(!ally.alive()){
					soldier.setValue(soldier.getValue()-1);
					universe.removeGameEntity(ally);	
					universe.addGameEntity(new Grave( canvas , false, ally.getPosition()));			
					//System.out.println("allié "+ally.getName()+" est mort.\n");
				}
			}
		}
	}	
	public void overlapRule(ArmedUnitSoldier soldier, Fairy heal){
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

	public void overlapRule(ArmedUnitSoldier soldier, OffensiveWeapon weapon){
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
