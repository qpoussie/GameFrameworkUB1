package rtsCDQ.utils;
import rtsCDQ.soldier.ArmedUnit;
import rtsCDQ.soldier.ArmedUnitSquad;

public interface VisitorClassicForArmedUnit {
	void visit(ArmedUnit s);
	void visit(ArmedUnitSquad a);
}
 