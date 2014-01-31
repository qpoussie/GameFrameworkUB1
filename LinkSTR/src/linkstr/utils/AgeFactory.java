package linkstr.utils;

import linkstr.entity.soldier.Soldier;
import linkstr.entity.soldier.SoldierAbstract;

public interface AgeFactory {
	SoldierAbstract getSimpleSoldier(String name);
	SoldierAbstract getComplexSoldier(String name);
	Soldier getDefensiveWeapon(Soldier s);
	Soldier getOffensiveWeapon(Soldier s);
}
