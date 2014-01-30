package linkstr.entity.soldier;

import gameframework.STR.facade.DrawableOverlappableGameEntity;
import gameframework.base.SpeedVector;
import gameframework.game.GameMovableDriver;
import gameframeworkExtension.GameMovableDriverDead;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import linkstr.utils.AgeFactory;
import linkstr.utils.Observer;
import linkstr.utils.VisitorClassicForArmedUnit;
import linkstr.utils.VisitorFunForArmedUnit;

public class DeadArmedUnit implements DrawableOverlappableGameEntity,
		ArmedUnit {

	protected ArmedUnit armedUnit;
	
	public DeadArmedUnit(ArmedUnit au){
		this.armedUnit = au;
		this.armedUnit.setDriver(new GameMovableDriverDead());
	}
	
	public String getName() {
		return armedUnit.getName();
	}

	public float getHealthPoints() {
		return armedUnit.getHealthPoints();
	}

	public AgeFactory getAge() {
		return armedUnit.getAge();
	}

	public boolean alive() {
		return armedUnit.alive();
	}

	public void heal() {
		armedUnit.heal();
	}

	public boolean parry(float force) {
		return armedUnit.parry(force);
	}

	public float strike() {
		return armedUnit.strike();
	}

	public void addEquipment(String weaponType) {
		armedUnit.addEquipment(weaponType);
		
	}

	public void accept(VisitorClassicForArmedUnit v) {
		armedUnit.accept(v);
		
	}

	public <T> T accept(VisitorFunForArmedUnit<T> v) {
		return armedUnit.accept(v);
	}


	public void draw(Graphics g) {
		armedUnit.draw(g);
	}

	
	public Point getPosition() {
		return armedUnit.getPosition();
	}

	public void setPosition(Point p){
		armedUnit.setPosition(p);
	}
	

	
	public void setDriver(GameMovableDriver driver){
		armedUnit.setDriver(driver);
	}
	

	public void oneStepMoveAddedBehavior() {
		armedUnit.oneStepMoveAddedBehavior();
		
	}
	
	public SpeedVector getSpeedVector(){
		return armedUnit.getSpeedVector();
	}

	public void setSpeedVector(SpeedVector m){
		armedUnit.setSpeedVector(m);
	}

	public void oneStepMove(){
		armedUnit.oneStepMove();
	}

	public GameMovableDriver getDriver(){
		return armedUnit.getDriver();
	}
	
	public Rectangle getBoundingBox() {
		return armedUnit.getBoundingBox();
	}

	@Override
	public void setSelected(boolean b) {
		armedUnit.setSelected(b);
	}
	
	public void register(Observer<ArmedUnit> o){
		armedUnit.register(o);
	}

}
