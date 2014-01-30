package linkstr;

import gameframework.base.MoveStrategyStraightLine;
import gameframework.game.CanvasDefaultImpl;
import gameframework.game.Game;
import gameframework.game.GameLevelDefaultImpl;
import gameframework.game.GameMovableDriverDefaultImpl;
import gameframework.game.GameUniverseDefaultImpl;
import gameframework.game.GameUniverseViewPortDefaultImpl;
import gameframework.game.MoveBlockerChecker;
import gameframework.game.MoveBlockerCheckerDefaultImpl;
import gameframework.game.OverlapProcessor;
import gameframework.game.OverlapProcessorDefaultImpl;
import gameframeworkExtension.MouseController;
import gameframeworkExtension.MoveStrategyKeyboardLink;
import gameframeworkExtension.MoveStrategyRandomBadLink;
import gameframeworkExtension.Sound;

import java.awt.Canvas;
import java.awt.Point;
import java.util.Random;

import linkstr.entity.Arrow;
import linkstr.entity.GreenRoc;
import linkstr.entity.RedRoc;
import linkstr.entity.Sand;
import linkstr.entity.Tree;
import linkstr.entity.soldier.ArmedUnitSoldier;
import linkstr.entity.soldier.SelectableArmedUnit;
import linkstr.rule.STROverlapRules;
import linkstr.utils.MiddleAgeFactory;
import linkwar.rule.BadLinkMovableDriver;
import pacman.rule.PacmanMoveBlockers;

public class GameLevelOne extends GameLevelDefaultImpl {
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
		{ 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
		{ 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1 },
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
		
		STROverlapRules overlapRules = new STROverlapRules();
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
					universe.addGameEntity(new Arrow(canvas, new Point(j * SPRITE_SIZE, i * SPRITE_SIZE)));
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
		
		SelectableArmedUnit[] niceUnits = new SelectableArmedUnit[3];
		
		for(int i=0; i<3; i++){
			niceUnits[i] = new SelectableArmedUnit(new ArmedUnitSoldier(ageFactory, "Simple", "niceLink"+1, canvas, "images/brownLink.png"));
			
			GameMovableDriverDefaultImpl niceLinkDriver = new GameMovableDriverDefaultImpl();
			/*MoveStrategyKeyboardLink keyStr = new MoveStrategyKeyboardLink();
			
			niceLinkDriver.setStrategy(keyStr);
			niceLinkDriver.setmoveBlockerChecker(moveBlockerChecker);
			canvas.addKeyListener(keyStr);
			
			
			niceUnits[i].setDriver(niceLinkDriver);
			*/
			MoveStrategyStraightLine straightLine = new MoveStrategyStraightLine(new Point(), new Point());
			niceLinkDriver.setStrategy(straightLine);
			niceUnits[i].setDriver(niceLinkDriver);
			
			niceUnits[i].setPosition(new Point((12 + i) * SPRITE_SIZE, 28 * SPRITE_SIZE));
			universe.addGameEntity(niceUnits[i]);
		}
		
		
		
		// Enemies definition and inclusion in the universe
		ArmedUnitSoldier[] myBLSlow = new ArmedUnitSoldier[10];

		//bad Link slow
		for(int i = 0; i < 10; i++){
			BadLinkMovableDriver badlinkSlowDriv = new BadLinkMovableDriver();
			badlinkSlowDriv.setLinkSpeed(random.nextInt(10)+1);
			MoveStrategyRandomBadLink mStrSlow = new MoveStrategyRandomBadLink();
			mStrSlow.setChangeFreq(40);
			badlinkSlowDriv.setStrategy(mStrSlow);
			badlinkSlowDriv.setmoveBlockerChecker(moveBlockerChecker);
			myBLSlow[i] = new ArmedUnitSoldier(ageFactory, "Simple", "badLink"+i, canvas, "images/darklink.png");
			myBLSlow[i].setDriver(badlinkSlowDriv);
			myBLSlow[i].setPosition(new Point(SPRITE_SIZE * (4 + i*2), 1 * SPRITE_SIZE));
			universe.addGameEntity(myBLSlow[i]);
		}



	}

	public GameLevelOne(Game g) {
		super(g);
		canvas = g.getCanvas();
	}

}
