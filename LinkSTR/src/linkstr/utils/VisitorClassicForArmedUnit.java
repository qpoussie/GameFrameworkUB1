package linkstr.utils;
import linkstr.entity.soldier.ArmedUnit;
import linkstr.entity.soldier.ArmedUnitSquad;

public interface VisitorClassicForArmedUnit {
	void visit(ArmedUnit s);
	void visit(ArmedUnitSquad a);
}
 