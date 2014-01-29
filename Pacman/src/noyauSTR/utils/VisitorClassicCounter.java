package noyauSTR.utils;
import noyauSTR.soldier.ArmedUnit;
import noyauSTR.soldier.ArmedUnitSquad;

public class VisitorClassicCounter implements VisitorClassicForArmedUnit{
	private Integer count = 0;
	
	public void visit(ArmedUnit f) {
		count++; 
	}

	public void visit(ArmedUnitSquad a) {		
	}

	public void reset() {
		count = 0;
	}
	public Integer getCount(){
		return count;
	}
}
 