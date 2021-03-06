package linkstr.rule;

import java.awt.Canvas;

import gameframework.base.ObservableValue;
import gameframework.base.Overlap;
import gameframework.game.CanvasDefaultImpl;
import gameframework.game.GameUniverse;
import gameframeworkExtension.OverlapRulesApplierExtensionDefaultImpl;
import gameframeworkExtension.Sound;

import java.util.Vector;

import linkstr.entity.Fairy;
import linkstr.entity.Grave;
import linkstr.entity.OffensiveWeapon;
import linkstr.entity.soldier.ArmedUnitSoldier;
import linkstr.entity.soldier.FocusableArmedUnit;
import linkstr.entity.soldier.SelectableArmedUnit;
public class LinkStrOverlapRules extends OverlapRulesApplierExtensionDefaultImpl{

	private final ObservableValue<Integer> soldier;
	private final ObservableValue<Integer> badSoldier;
	private final Canvas canvas = new CanvasDefaultImpl();
	private final Sound deathSound = new Sound("sounds/die.wav");
	private final Sound swordSound = new Sound("sounds/sword.wav");
	private final Sound healSound = new Sound("sounds/heal.wav");

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

			if(!enemy.alive()){
				badSoldier.setValue(badSoldier.getValue()-1);
				universe.removeGameEntity(enemy);
				universe.addGameEntity(new Grave(canvas , true, enemy.getPosition()));				
				//System.out.println("enemy "+enemy.getName()+" est mort.\n");
			}
			if(!ally.alive()){
				deathSound.play();
				soldier.setValue(soldier.getValue()-1);
				universe.removeGameEntity(ally);	
				universe.addGameEntity(new Grave( canvas , false, ally.getPosition()));			
				//System.out.println("allié "+ally.getName()+" est mort.\n");

			}
		}
	}	
	public void overlapRule(FocusableArmedUnit soldier, Fairy heal){
		if(soldier.getHealthPoints() < 100){
			this.universe.removeGameEntity(heal);
			healSound.play();
			soldier.heal();
		}
	}

	public void overlapRule(SelectableArmedUnit soldier, Fairy heal){
		if(soldier.getHealthPoints() < 100){
			this.universe.removeGameEntity(heal);
			healSound.play();
			soldier.heal(); 
		}
	}

	public void overlapRule(FocusableArmedUnit soldier, OffensiveWeapon weapon){
		if(!soldier.isOffensive()){
			this.universe.removeGameEntity(weapon);
			swordSound.play();
			soldier.addEquipment("Offensive");
		}
	}

	public void overlapRule(SelectableArmedUnit soldier, OffensiveWeapon weapon){
		if(!soldier.isOffensive()){
			this.universe.removeGameEntity(weapon);
			swordSound.play();
			soldier.addEquipment("Offensive");
		}
	}

}
