package linkstr.entity.soldier;

import gameframework.STR.facade.DrawableOverlappableGameEntity;
import gameframework.STR.facade.STRGameMovable;
import gameframework.base.Movable;
import gameframework.base.SpeedVector;
import gameframework.game.GameMovableDriver;
import gameframework.game.SpriteManager;
import gameframework.game.SpriteManagerDefaultImpl;
import gameframeworkExtension.DrawableUnitSelection;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import linkstr.utils.AgeFactory;
import linkstr.utils.ObservableAbstract;
import linkstr.utils.VisitorClassicForArmedUnit;
import linkstr.utils.VisitorFunForArmedUnit;

public class ArmedUnitSoldier extends ObservableAbstract<ArmedUnit> implements
		ArmedUnit, DrawableOverlappableGameEntity, Movable {


	protected Soldier soldier;
	protected List<String> equipments = new ArrayList<String>();
	protected AgeFactory age;
	
	private DrawableHealthBar drawableHealthBar = new DrawableHealthBar();
	private DrawableUnitSelection drawableUnitSelection = new DrawableUnitSelection();
	protected final SpriteManager spriteManager;
	public static final int RENDERING_SIZE = 32;
	protected boolean movable = true;
	protected boolean selected = false;
	protected boolean focused = false;
	protected boolean dead = false;
	protected int attack = 3; 
	
	STRGameMovable gameMovable;

	public ArmedUnitSoldier(AgeFactory factory, String soldatType, String name, Canvas defaultCanvas, String spriteFile) {
		this.age = factory;
		String methodName = "get" + soldatType + "Soldier";
		try {
			Method method = factory.getClass().getMethod(methodName,
					String.class);
 			soldier = (SoldierAbstract) method.invoke(factory, name);
		} catch (Exception e) {
			throw new UnknownSoldierTypeException("Unknown soldier type"
					+ e.toString());
		}
		
		
		spriteManager = new SpriteManagerDefaultImpl(spriteFile,
				defaultCanvas, RENDERING_SIZE, 3);
		spriteManager.setTypes(
				//
				"up", "right", "down",
				"left",//
				"hit-up", "hit-right", "hit-down",
				"hit-left", //
				"dead", "static");
		
		gameMovable = new STRGameMovable();
	}

	public void addEquipment(String equipmentType) {
		if (alive()) { // XXX "else" not treated
			if (equipments.contains(equipmentType)) {
				return; // decoration not applied
			} else {
				String methodName = "get" + equipmentType + "Weapon";
				try {
					Method method = age.getClass().getMethod(methodName,
							Soldier.class);
					soldier = (Soldier) method.invoke(age, soldier);
					equipments.add(equipmentType);
				} catch (Exception e) {
					throw new RuntimeException("Unknown equipment type "
							+ e.toString());
				}
			}
		}
	}

	public String getName() {
		return soldier.getName();
	}

	public float getHealthPoints() {
		return soldier.getHealthPoints();
	}

	public AgeFactory getAge() {
		return age;
	}

	public boolean alive() {
		return soldier.alive();
	}

	public void heal() {
		soldier.heal();
	}

	public float strike() {
		attack = 0;
		return soldier.strike();
	}

	public boolean parry(float force) {
		notify(this);
		return soldier.parry(force);
	}

	public void accept(VisitorClassicForArmedUnit v) {
		v.visit(this);
	}

	public <T> T accept(VisitorFunForArmedUnit<T> v) {
		return v.visit(this);
	}

	@Override
	public void draw(Graphics g) {
		String spriteType = "";
		Point tmp = gameMovable.getSpeedVector().getDirection();
		
		if(!soldier.alive()){
			spriteType += "dead";
		} else {
			movable = true;
			if(attack<3){
				if (tmp.getX() == 1) {
					spriteType += "hit-right";
					attack++;
				} else if (tmp.getX() == -1) {
					spriteType += "hit-left";
					attack++;
				} else if (tmp.getY() == 1) {
					spriteType += "hit-down";
					attack++;
				} else if (tmp.getY() == -1) {
					spriteType += "hit-up";
					attack++;
				} else {
					spriteType = "hit-down";
					attack++;
					spriteManager.reset();
					movable = false;
				}
			} else { 
				if (tmp.getX() == 1) {
					spriteType += "right";
				} else if (tmp.getX() == -1) {
					spriteType += "left";
				} else if (tmp.getY() == 1) {
					spriteType += "down";
				} else if (tmp.getY() == -1) {
					spriteType += "up";
				} else {
					spriteType = "static";
					spriteManager.reset();
					movable = false;
				}
			}
		}
		spriteManager.setType(spriteType);
		if(selected == true){
			drawableUnitSelection.draw(g, getPosition(), RENDERING_SIZE, Color.green);
		}
		if(focused == true){
			drawableUnitSelection.draw(g, getPosition(), RENDERING_SIZE, Color.green);
			focused = false;
		}

		drawableHealthBar.draw(g, getPosition(), RENDERING_SIZE, ((SoldierAbstract)soldier).getTotalHealthPoints(), soldier.getHealthPoints());

		spriteManager.draw(g, getPosition());
		
	}

	public SpeedVector getSpeedVector(){
		return gameMovable.getSpeedVector();
	}

	public void setSpeedVector(SpeedVector m){
		gameMovable.setSpeedVector(m);

	}

	public void oneStepMove(){
		gameMovable.oneStepMove();
	}
	
	public void setDriver(GameMovableDriver driver){
		gameMovable.setDriver(driver);
	}
	
	public GameMovableDriver getDriver(){
		return gameMovable.getDriver();
	}
	
	public void setPosition(Point p){
		gameMovable.setPosition(p);
	}
	
	@Override
	public Point getPosition() {
		return gameMovable.getPosition();
	}

	@Override
	public Rectangle getBoundingBox() {
		return (new Rectangle(0, 0, RENDERING_SIZE, RENDERING_SIZE));
	}
	
	@Override
	public void oneStepMoveAddedBehavior() {
		if (movable) {
			spriteManager.increment();
		}
	}

	@Override
	public void setSelected(boolean b) {
		selected = b;
		
	}

	@Override
	public void setFocused(boolean b) {
		focused = b;
	}

}
