package linkstr.entity.soldier.weapon;
import linkstr.entity.soldier.Soldier;

public class SoldierWithSword extends SoldierArmed<Sword> {

	public SoldierWithSword(Soldier s) {
		super(s, new Sword());
	}
}
