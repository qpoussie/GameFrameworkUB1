package gameframeworkExtension;

import gameframework.base.ObservableValue;
import gameframework.game.GameLevel;
import gameframework.game.GameUniverse;
import gameframework.game.GameUniverseViewPort;

import java.util.Date;

import linkstr.rule.GameLinkImpl;

/**
 * To be implemented with respect to a specific game. Expected to initialize the
 * universe and the gameBoard
 */

public abstract class GameLevelLinkImpl extends Thread implements GameLevel {
	private static final int MINIMUM_DELAY_BETWEEN_GAME_CYCLES = 100;
	protected GameUniverse universe;
	protected GameUniverseViewPort gameBoard;
	protected ObservableValue<Integer> linkAlive[];
	protected ObservableValue<Integer> badLinkAlive[];
	protected ObservableValue<Boolean> endOfGame;

	boolean stopGameLoop;
	protected final GameLinkImpl g;

	protected abstract void init();

	public GameLevelLinkImpl(GameLinkImpl g) {
		this.g = g;
		this.linkAlive = g.getLinkAlive();
		this.badLinkAlive = g.getBadLinkAlive();
	}

	@Override
	public void start() {
		System.out.println("start");
		endOfGame = g.endOfGame();
		init();
		super.start();
		try {
			super.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		stopGameLoop = false;
		// main game loop :
		long start;
		System.out.println("avant boucle");
		while (!stopGameLoop && !this.isInterrupted()) {
			System.out.println("main boucle");
			start = new Date().getTime();
			gameBoard.paint();
			universe.allOneStepMoves();
			universe.processAllOverlaps();
			try {
				long sleepTime = MINIMUM_DELAY_BETWEEN_GAME_CYCLES
						- (new Date().getTime() - start);
				if (sleepTime > 0) {
					Thread.sleep(sleepTime);
				}
			} catch (Exception e) {
			}
		}
	}

	public void end() {
		stopGameLoop = true;
	}

	protected void overlap_handler() {
	}

}
