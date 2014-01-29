package linkstr.entity.soldier.weapon;

interface Weapon {
	float getParryValue();
	float getStrikeValue();
	float getResistanceToDamage();
	void damageCompute(float coef);
	void fix();
}
