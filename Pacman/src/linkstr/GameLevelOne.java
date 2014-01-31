package linkstr;

import gameframework.base.MoveStrategyStraightLine;
import gameframework.game.CanvasDefaultImpl;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.GameUniverseDefaultImpl;
import gameframework.game.GameUniverseViewPortDefaultImpl;
import gameframework.game.MoveBlockerChecker;
import gameframework.game.MoveBlockerCheckerDefaultImpl;
import gameframework.game.OverlapProcessor;
import gameframework.game.OverlapProcessorDefaultImpl;
import gameframeworkExtension.GameLevelLinkImpl;
import gameframeworkExtension.MouseController;

import java.awt.Canvas;
import java.awt.Point;
import java.util.Random;

import linkstr.entity.Fairy;
import linkstr.entity.GreenRoc;
import linkstr.entity.OffensiveWeapon;
import linkstr.entity.RedRoc;
import linkstr.entity.Sand;
import linkstr.entity.Tree;
import linkstr.entity.soldier.ArmedUnitSoldier;
import linkstr.entity.soldier.SelectableArmedUnit;
import linkstr.rule.BadLinkMovableDriver;
import linkstr.rule.GameLinkImpl;
import linkstr.rule.LinkStrOverlapRules;
import linkstr.rule.MoveStrategyRandomBadLink;
import linkstr.rule.MoveStrategyRandomFairy;
import linkstr.utils.MiddleAgeFactory;
import pacman.rule.PacmanMoveBlockers;

public class GameLevelOne extends GameLevelLinkImpl {
	Canvas canvas;
	
	static Random random = new Random();
	static int[][] tab = { 
		{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
		{ 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
		{ 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
		{ 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
		{ 1, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
		{ 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
		{ 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
		{ 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
		{ 1, 0, 0, 0, 0, 0, 0, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 0, 0, 0, 0, 0, 0, 1 },
		{ 1, 0, 0, 0, 0, 0, 0, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 0, 0, 0, 0, 0, 0, 1 },
		{ 1, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 1 },
		{ 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
		{ 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
		{ 1, 5, 5, 5, 5, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
		{ 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 4, 5, 5, 5, 5, 1 },
		{ 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
		{ 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
		{ 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
		{ 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
		{ 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
		{ 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
		{ 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
		{ 1, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 1 },
		{ 1, 0, 0, 0, 0, 0, 0, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 0, 0, 0, 0, 0, 0, 1 },
		{ 1, 0, 0, 0, 0, 0, 0, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 0, 0, 0, 0, 0, 0, 1 },
		{ 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
		{ 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
		{ 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 4, 5, 5, 5, 5, 1 },
		{ 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
		{ 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
		{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }};

	public static final int SPRITE_SIZE = 32;

	@Override
	protected void init() {

		OverlapProcessor overlapProcessor = new OverlapProcessorDefaultImpl();

		MoveBlockerChecker moveBlockerChecker = new MoveBlockerCheckerDefaultImpl();
		moveBlockerChecker.setMoveBlockerRules(new PacmanMoveBlockers());
		
		LinkStrOverlapRules overlapRules = new LinkStrOverlapRules(linkAlive[0], badLinkAlive[0]);
		overlapProcessor.setOverlapRules(overlapRules);
		
		universe = new GameUniverseDefaultImpl(moveBlockerChecker, overlapProcessor);
		overlapRules.setUniverse(universe);

		gameBoard = new GameUniverseViewPortDefaultImpl(canvas, universe);
		((CanvasDefaultImpl) canvas).setDrawingGameBoard(gameBoard);

		//init mouseController (cyclic dependency between MouseController and canvas !)
		MouseController mouseController = MouseController.getInstance();
		mouseController.setGameUnivers(universe);
		canvas.addMouseListener(mouseController);
		canvas.addMouseMotionListener(mouseController);
		
		// Filling up the universe with basic non movable entities and inclusion in the universe
		for (int i = 0; i < 31; ++i) {
			for (int j = 0; j < 28; ++j) {
				if (tab[i][j] == 0) {
					universe.addGameEntity(new Tree(canvas, j * SPRITE_SIZE, i * SPRITE_SIZE));
				}
				if (tab[i][j] == 1) {
					universe.addGameEntity(new GreenRoc(canvas, j * SPRITE_SIZE, i * SPRITE_SIZE));
				}
				if (tab[i][j] == 2) {
					universe.addGameEntity(new OffensiveWeapon(canvas, new Point(j * SPRITE_SIZE, i * SPRITE_SIZE)));
				}
				if (tab[i][j] == 4) {
					universe.addGameEntity(new RedRoc(canvas, j * SPRITE_SIZE, i * SPRITE_SIZE));
				}
				if (tab[i][j] == 5) {
					universe.addGameEntity(new Sand(canvas,j * SPRITE_SIZE, i * SPRITE_SIZE));
				}
			}
		}

		// Nice ArmedUnitSoldier definition and inclusion in the universe
		
		MiddleAgeFactory ageFactory = new MiddleAgeFactory();
		
		SelectableArmedUnit[] niceUnits = new SelectableArmedUnit[5];

		
		for(int i=0; i<5; i++){
			niceUnits[i] = new SelectableArmedUnit(new ArmedUnitSoldier(ageFactory, "Simple", "niceLink"+i, canvas, "images/brownLink.png"));

			GameMovableDriverDefaultImpl niceLinkDriver = new GameMovableDriverDefaultImpl();
			MoveStrategyStraightLine straightLine = new MoveStrategyStraightLine(new Point(), new Point());
			
			niceLinkDriver.setStrategy(straightLine);
			niceLinkDriver.setmoveBlockerChecker(moveBlockerChecker);
			niceUnits[i].setDriver(niceLinkDriver);
			
			niceUnits[i].setPosition(new Point((12 + i) * SPRITE_SIZE, 28 * SPRITE_SIZE));
			universe.addGameEntity(niceUnits[i]);
		}
		
		
		
		// Enemies definition and inclusion in the universe
		ArmedUnitSoldier[] myBLSlow = new ArmedUnitSoldier[10];
		//FocusableArmedUnit[] myBLSlow = new FocusableArmedUnit[10];
		
		//bad Link slow
		for(int i = 0; i < 10; i++){

			BadLinkMovableDriver badlinkSlowDriv = new BadLinkMovableDriver();
			MoveStrategyRandomBadLink mStrSlow = new MoveStrategyRandomBadLink();
			badlinkSlowDriv.setLinkSpeed(random.nextInt(10)+1);
			mStrSlow.setChangeFreq(40);
			
			badlinkSlowDriv.setStrategy(mStrSlow);
			badlinkSlowDriv.setmoveBlockerChecker(moveBlockerChecker);
			
			myBLSlow[i] = new ArmedUnitSoldier(ageFactory, "Simple", "badLink"+i, canvas, "images/darklink.png");
			//myBLSlow[i] = new FocusableArmedUnit(new ArmedUnitSoldier(ageFactory, "Simple", "badLink"+i, canvas, "images/darklink.png"));
			myBLSlow[i].setDriver(badlinkSlowDriv);
			myBLSlow[i].setPosition(new Point(SPRITE_SIZE * (4 + i*2), 1 * SPRITE_SIZE));
			
			universe.addGameEntity(myBLSlow[i]);
		}
		
		
		linkAlive[0].setValue(5);
		badLinkAlive[0].setValue(10);
		
		universe.addGameEntity(new OffensiveWeapon(canvas, new Point(12 * SPRITE_SIZE, 15 * SPRITE_SIZE)));
		universe.addGameEntity(new OffensiveWeapon(canvas, new Point(15 * SPRITE_SIZE, 15 * SPRITE_SIZE)));
		
		Fairy fairy = new Fairy(canvas);

		BadLinkMovableDriver fairyDriv = new BadLinkMovableDriver();
		MoveStrategyRandomFairy mStrFairy = new MoveStrategyRandomFairy();
		fairyDriv.setLinkSpeed(5);
		mStrFairy.setChangeFreq(20);
		fairyDriv.setStrategy(mStrFairy);
		fairy.setDriver(fairyDriv);
		fairy.setPosition(new Point(14 * SPRITE_SIZE, 16 * SPRITE_SIZE));
		universe.addGameEntity(fairy);
	
	}

	public GameLevelOne(GameLinkImpl g) {
		super(g);
		//GameLinkImpl concreteGame = (GameLinkImpl)g;
		canvas = g.getCanvas();
	}

}
