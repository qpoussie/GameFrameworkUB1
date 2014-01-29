package noyauSTR.utils;
import noyauSTR.soldier.ArmedUnit;
import noyauSTR.soldier.ArmedUnitSquad;

public interface VisitorClassicForArmedUnit {
	void visit(ArmedUnit s);
	void visit(ArmedUnitSquad a);
}
 