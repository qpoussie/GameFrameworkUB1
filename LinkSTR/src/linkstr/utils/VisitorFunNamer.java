package linkstr.utils;
import linkstr.entity.soldier.ArmedUnit;
import linkstr.entity.soldier.ArmedUnitSquad;

public class VisitorFunNamer implements VisitorFunForArmedUnit<String>{

	public String visit(ArmedUnit f) {
		return ("InfantryMan " + f.getName() + "\n");
	}
 
	public String visit(ArmedUnitSquad a) {
		return ("Squad " + a.getName() + " : \n");
	}

	public String compos(String s1, String s2){
		return s1 + s2;
	}
}
