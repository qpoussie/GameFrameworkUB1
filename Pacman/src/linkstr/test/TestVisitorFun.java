package linkstr.test;

import static org.junit.Assert.assertEquals;
import linkstr.entity.soldier.ArmedUnit;
import linkstr.entity.soldier.ArmedUnitSoldier;
import linkstr.entity.soldier.ArmedUnitSquad;
import linkstr.utils.AgeFactory;
import linkstr.utils.MiddleAgeFactory;
import linkstr.utils.VisitorFunCounter;
import linkstr.utils.VisitorFunForArmedUnit;
import linkstr.utils.VisitorFunNamer;

import org.junit.Test;

public class TestVisitorFun {
	@Test
	public void visitors() throws Exception {
		ArmedUnitSquad squad1, squad2;
		ArmedUnit sf1, sf2, sc1, sc2, s;
        AgeFactory age;
		
		age = new MiddleAgeFactory();
		squad1 = new ArmedUnitSquad(age, "Lincoln");
		squad2 = new ArmedUnitSquad(age, "Kieffer");
		/*sf1 = new ArmedUnitSoldier(age, "Simple", "Gogol1");
		sf2 = new ArmedUnitSoldier(age, "Simple", "Gogol2");
		sc1 = new ArmedUnitSoldier(age, "Complex", "Sanchez1");
		sc2 = new ArmedUnitSoldier(age, "Complex", "Sanchez2");
		s = new ArmedUnitSoldier(age, "Complex", "St Georges");
 
		squad1.addUnit(sf1);
		squad1.addUnit(sf2);
		squad2.addUnit(sc1);
		squad2.addUnit(sc2);
		squad2.addUnit(s);
		*/
		VisitorFunForArmedUnit<String> v = new VisitorFunNamer();
		assertEquals("Squad Lincoln : \nInfantryMan Gogol1\nInfantryMan Gogol2\n", 
				squad1.accept(v));
		assertEquals("Squad Kieffer : \nInfantryMan Sanchez1\nInfantryMan Sanchez2\nInfantryMan St Georges\n", 
				squad2.accept(v));

		VisitorFunForArmedUnit<Integer> vi = new VisitorFunCounter();
		assertEquals((Integer)2, squad1.accept(vi));
		assertEquals((Integer)3, squad2.accept(vi));
	}


}
