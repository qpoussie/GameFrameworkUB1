package linkstr.entity.soldier;

import gameframework.base.SpeedVector;
import gameframework.game.GameMovableDriver;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import linkstr.utils.AgeFactory;
import linkstr.utils.ObservableAbstract;
import linkstr.utils.VisitorClassicForArmedUnit;
import linkstr.utils.VisitorFunForArmedUnit;

public class ArmedUnitSquad extends ObservableAbstract<ArmedUnit> implements
		ArmedUnit {
	protected List<ArmedUnit> armedUnitList = new ArrayList<ArmedUnit>();
	protected String name;
	protected AgeFactory age;
	private boolean selected;
	private boolean focused;

	public ArmedUnitSquad(AgeFactory factory, String name) {
		this.age = factory;
		this.name = name;
		this.selected = false;
	}

	public String getName() {
		return name;
	}

	public void addUnit(ArmedUnit s) {
		if (s.getAge() == age) // historical coherence checked
			armedUnitList.add(s);
		else
			throw new BadAgeException();
	}

	public float getHealthPoints() {
		float points = 0;
		for (ArmedUnit s : armedUnitList) {
			points += s.getHealthPoints();
		}
		return points;
	}

	public AgeFactory getAge() {
		return age;
	}

	public boolean alive() {
		boolean alive = false;
		for (ArmedUnit s : armedUnitList) {
			alive = alive || (s.alive());
		}
		return alive;
	}

	public int getStillAliveSoldiers() {
		int stillAlive = 0;
		for (ArmedUnit s : armedUnitList) {
			if (s.alive())
				stillAlive += 1;
		}
		return stillAlive;
	}

	public void heal() {
		for (ArmedUnit s : armedUnitList) {
			s.heal();
		}
	}

	public void addEquipment(String equipmentType) {
		if (alive()) {
			// Every soldier of a unit gets the same equipment
			for (ArmedUnit s : armedUnitList) {
				if (s.alive())
					s.addEquipment(equipmentType);
			}
		}
	}

	public boolean parry(float force) {
		boolean result = false;
		if (alive()) {
			float forcePart = force / getStillAliveSoldiers();
			// Each alive soldier takes an equal part in each strike
			for (ArmedUnit s : armedUnitList) {
				result = (s.parry(forcePart)) || result;
			}
		}
		notify(this);
		return result;
	}

	public float strike() {
		float result = 0;
		if (alive()) {
			for (ArmedUnit s : armedUnitList) {
				result += s.strike();
			}
		}
		return result;
	}

	public void accept(VisitorClassicForArmedUnit v) {
		v.visit(this);
		for (ArmedUnit s : armedUnitList) {
			s.accept(v);
		}
	}

	public <T> T accept(VisitorFunForArmedUnit<T> v) {
		T result = v.visit(this);
		for (ArmedUnit s : armedUnitList) {
			result = v.compos(result, s.accept(v));
		}
		return result;
	}

	@Override
	public void setDriver(GameMovableDriver driver) {
		for(ArmedUnit s: armedUnitList){
			s.setDriver(driver);
		}
		
	}

	@Override
	public void setPosition(Point p) {
		for(ArmedUnit s: armedUnitList){
			s.setPosition(p);
		}
		
	}

	@Override
	public void oneStepMoveAddedBehavior() {
		for(ArmedUnit s: armedUnitList){
			s.oneStepMoveAddedBehavior();
		}
		
	}

	@Override
	public void draw(Graphics g) {
		for(ArmedUnit s: armedUnitList){
			s.draw(g);
		}
		
	}

	@Override
	public Point getPosition() {//returns the centroid of the squad
		Point centroid = new Point(0,0);
		for(ArmedUnit s: armedUnitList){
			Point p = s.getPosition();
			centroid.x += p.x;
			centroid.y += p.y;
		}
		int numberOfPoints = armedUnitList.size();
		centroid.x /= numberOfPoints;
		centroid.y /= numberOfPoints;
		
		return centroid;
	}

	@Override
	public Rectangle getBoundingBox() {
		//Rectangle boundingBox = new Rectangle(getPosition(), 0,0);
		return null;
	}

	@Override
	public SpeedVector getSpeedVector() {//all the units have the same speed
		return armedUnitList.get(0).getSpeedVector();
	}

	@Override
	public void setSpeedVector(SpeedVector m) {
		for(ArmedUnit s: armedUnitList){
			s.setSpeedVector(m);
		}
	}

	@Override
	public void oneStepMove() {
		for(ArmedUnit s: armedUnitList){
			s.oneStepMove();
		}
		
	}

	@Override
	public GameMovableDriver getDriver() {//all the units have the same driver
		return armedUnitList.get(0).getDriver();
	}

	@Override
	public void setSelected(boolean b) {
		selected = b;
	}

	@Override
	public void setFocused(boolean b) {
		focused = b;		
	}
	
	@Override
	public boolean isOffensive() {
		//return soldier.getClass().equals(SoldierWithSword.class) ;
		boolean res = true;
		for(ArmedUnit s: armedUnitList){
			res &= s.isOffensive();
		}
		return res;
	}

}
