package linkstr.utils;
import linkstr.entity.soldier.ArmedUnit;
import linkstr.entity.soldier.ArmedUnitSquad;

public interface VisitorFunForArmedUnit<T> {
	T visit(ArmedUnit s);
	T visit(ArmedUnitSquad a);
	T compos(T x1, T x2);
}
