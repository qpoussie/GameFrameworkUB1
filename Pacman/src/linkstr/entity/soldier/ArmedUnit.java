package linkstr.entity.soldier;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import linkstr.utils.AgeFactory;
import linkstr.utils.Observer;
import linkstr.utils.VisitorClassicForArmedUnit;
import linkstr.utils.VisitorFunForArmedUnit;
import gameframework.base.SpeedVector;
import gameframework.game.GameMovableDriver;

public interface ArmedUnit {
	public String getName();
	public float getHealthPoints();
	public AgeFactory getAge();
	public boolean alive();
	public void heal();
	public boolean parry(float force); // true if the receiver is still alive after the strike
	public float strike();
	public void addEquipment(String weaponType);  
	public void accept(VisitorClassicForArmedUnit v);
	public <T> T accept(VisitorFunForArmedUnit<T> v);
	public void register(Observer<ArmedUnit> o);
	
	public void setDriver(GameMovableDriver driver);
	public void setPosition(Point p);
	void oneStepMoveAddedBehavior();
	public void draw(Graphics g);
	public Point getPosition();
	public Rectangle getBoundingBox();
	public SpeedVector getSpeedVector();
	public void setSpeedVector(SpeedVector m);
	public void oneStepMove();
	public GameMovableDriver getDriver();
	public void setSelected(boolean b);
}