package linkwar;

import gameframework.base.MoveStrategyKeyboard;
import gameframework.base.MoveStrategyRandom;
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

import java.awt.Canvas;
import java.awt.Point;
import java.util.Random;

import linkstr.entity.Arrow;
import linkstr.entity.BadLink;
import linkstr.entity.GreenRoc;
import linkstr.entity.NiceLink;
import linkstr.entity.RedRoc;
import linkstr.entity.Sand;
import linkstr.entity.Tree;
import linkwar.rule.BadLinkMovableDriver;
import linkwar.rule.LinkWarOverlapRules;
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
		
		LinkWarOverlapRules overlapRules = new LinkWarOverlapRules();
		overlapProcessor.setOverlapRules(overlapRules);
		
		universe = new GameUniverseDefaultImpl(moveBlockerChecker, overlapProcessor);
		overlapRules.setUniverse(universe);

		gameBoard = new GameUniverseViewPortDefaultImpl(canvas, universe);
		((CanvasDefaultImpl) canvas).setDrawingGameBoard(gameBoard);

		//init mouseController (cyclic dependency between MouseController and canvas !)
		MouseController mouseController = new MouseController(universe);
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

		// Nicelink definition and inclusion in the universe
		NiceLink myLink1 = new NiceLink(canvas);
		NiceLink myLink2 = new NiceLink(canvas);
		NiceLink myLink3 = new NiceLink(canvas);
		GameMovableDriverDefaultImpl pacDriver = new GameMovableDriverDefaultImpl();
		MoveStrategyKeyboardLink keyStr = new MoveStrategyKeyboardLink();
		pacDriver.setStrategy(keyStr);
		pacDriver.setmoveBlockerChecker(moveBlockerChecker);
		canvas.addKeyListener(keyStr);
		
		myLink1.setDriver(pacDriver);
		myLink1.setPosition(new Point(12 * SPRITE_SIZE, 28 * SPRITE_SIZE));
		myLink2.setDriver(pacDriver);
		myLink2.setPosition(new Point(14 * SPRITE_SIZE, 28 * SPRITE_SIZE));
		myLink3.setDriver(pacDriver);
		myLink3.setPosition(new Point(16 * SPRITE_SIZE, 28 * SPRITE_SIZE));
		

		universe.addGameEntity(myLink1);
		universe.addGameEntity(myLink2);
		universe.addGameEntity(myLink3);
		

		// Enemies definition and inclusion in the universe
		BadLink[] myBLSlow = new BadLink[10];
		BadLink myBLQuick;

		//bad Link slow
		for(int i = 0; i < 10; i++){
			BadLinkMovableDriver badlinkSlowDriv = new BadLinkMovableDriver();
			badlinkSlowDriv.setLinkSpeed(random.nextInt(10)+1);
			MoveStrategyRandomBadLink mStrSlow = new MoveStrategyRandomBadLink();
			mStrSlow.setChangeFreq(40);
			badlinkSlowDriv.setStrategy(mStrSlow);
			badlinkSlowDriv.setmoveBlockerChecker(moveBlockerChecker);
			myBLSlow[i] = new BadLink(canvas);
			myBLSlow[i].setDriver(badlinkSlowDriv);
			myBLSlow[i].setPosition(new Point(SPRITE_SIZE * 2, 1 * SPRITE_SIZE));
			universe.addGameEntity(myBLSlow[i]);
		}

		//bad Link quick
		BadLinkMovableDriver badlinkQuickDriv = new BadLinkMovableDriver();
		badlinkQuickDriv.setLinkSpeed(16);
		MoveStrategyRandomBadLink mStrQuick = new MoveStrategyRandomBadLink();
		mStrQuick.setChangeFreq(6);
		badlinkQuickDriv.setStrategy(mStrQuick);
		badlinkQuickDriv.setmoveBlockerChecker(moveBlockerChecker);
		myBLQuick = new BadLink(canvas);
		myBLQuick.setDriver(badlinkQuickDriv);
		myBLQuick.setPosition(new Point(24 * SPRITE_SIZE, 2 * SPRITE_SIZE));
		
		
		universe.addGameEntity(myBLQuick);



	}

	public GameLevelOne(Game g) {
		super(g);
		canvas = g.getCanvas();
	}

}
