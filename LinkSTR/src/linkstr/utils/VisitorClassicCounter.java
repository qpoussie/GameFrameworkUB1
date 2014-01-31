package linkstr.utils;
import linkstr.entity.soldier.ArmedUnit;
import linkstr.entity.soldier.ArmedUnitSquad;

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
 