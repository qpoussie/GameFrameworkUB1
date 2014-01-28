package rtsCDQ.weapon;
import rtsCDQ.soldier.Soldier;

public class SoldierWithSword extends SoldierArmed<Sword> {

	public SoldierWithSword(Soldier s) {
		super(s, new Sword());
	}
}
