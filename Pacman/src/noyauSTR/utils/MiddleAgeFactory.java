package noyauSTR.utils;

import noyauSTR.soldier.Horseman;
import noyauSTR.soldier.InfantryMan;
import noyauSTR.soldier.Soldier;
import noyauSTR.soldier.SoldierAbstract;
import noyauSTR.weapon.SoldierWithShield;
import noyauSTR.weapon.SoldierWithSword;

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
