package noyauSTR.utils;

import noyauSTR.soldier.Soldier;
import noyauSTR.soldier.SoldierAbstract;

public interface AgeFactory {
	SoldierAbstract getSimpleSoldier(String name);
	SoldierAbstract getComplexSoldier(String name);
	Soldier getDefensiveWeapon(Soldier s);
	Soldier getOffensiveWeapon(Soldier s);
}
