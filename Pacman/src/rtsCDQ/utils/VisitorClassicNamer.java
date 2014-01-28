package rtsCDQ.utils;
import rtsCDQ.soldier.ArmedUnit;
import rtsCDQ.soldier.ArmedUnitSquad;

public class VisitorClassicNamer implements VisitorClassicForArmedUnit{
	private String s = "";
	
	public void visit(ArmedUnit f) {
		s += "InfantryMan " + f.getName() + "\n";
	}
 
	public void visit(ArmedUnitSquad a) {
		s += "Squad " + a.getName() + " : \n";
	}

	public void reset() {
		s = "";
	}
	
	public String getNames(){
		return s;
	}
}

