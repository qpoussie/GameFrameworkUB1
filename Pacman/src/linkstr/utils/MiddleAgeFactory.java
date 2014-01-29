package linkstr.utils;

import linkstr.entity.soldier.Horseman;
import linkstr.entity.soldier.InfantryMan;
import linkstr.entity.soldier.Soldier;
import linkstr.entity.soldier.SoldierAbstract;
import linkstr.entity.soldier.weapon.SoldierWithShield;
import linkstr.entity.soldier.weapon.SoldierWithSword;

public class MiddleAgeFactory implements AgeFactory {
	public SoldierAbstract getSimpleSoldier(String name) {
		return new InfantryMan(name);
	}
 
	public SoldierAbstract getComplexSoldier(String name) {
		return new Horseman(name);
	}
 
	public Soldier getDefensiveWeapon(Soldier s) {
		return new SoldierWithShield(s);
	}
 
	public Soldier getOffensiveWeapon(Soldier s) {
		return new SoldierWithSword(s);
	}
}
