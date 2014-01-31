package linkstr.rule;

import gameframework.base.ObservableValue;
import gameframework.game.CanvasDefaultImpl;
import gameframework.game.Game;
import gameframework.game.GameLevel;
import gameframework.game.GameLevelDefaultImpl;
import gameframeworkExtension.GameLevelLinkImpl;
import gameframeworkExtension.Sound;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

/**
 * Create a basic game application with menus and displays of lives and score
 */
public class GameLinkImpl implements Game, Observer {
	protected static final int NB_ROWS = 31;
	protected static final int NB_COLUMNS = 28;
	protected static final int SPRITE_SIZE = 32;
	public static final int MAX_NUMBER_OF_PLAYER = 1;

	protected CanvasDefaultImpl defaultCanvas = null;
	protected CanvasDefaultImpl LinkCanvas = null;
	protected ObservableValue<Integer> score[] = new ObservableValue[MAX_NUMBER_OF_PLAYER];
	protected ObservableValue<Integer> linkAlive[] = new ObservableValue[MAX_NUMBER_OF_PLAYER];
	protected ObservableValue<Integer> badLinkAlive[] = new ObservableValue[MAX_NUMBER_OF_PLAYER];

	// initialized before each level
	protected ObservableValue<Boolean> endOfGame = null;

	private Frame f;
	private GameLevelLinkImpl currentPlayedLevel = null;

	protected int levelNumber;
	protected ArrayList<GameLevel> gameLevels;

	protected Label lifeText, badLifeText,scoreText;
	protected Label information;
	protected Label informationValue;
	protected Label linkAliveValue, badLinkAliveValue,scoreValue;
	protected Label currentLevel;
	protected Label currentLevelValue;

	public GameLinkImpl() {
		score[0] = new ObservableValue<Integer>(0);
		linkAlive[0] = new ObservableValue<Integer>(0);
		badLinkAlive[0] = new ObservableValue<Integer>(0);
		lifeText = new Label("Soldier Alive:");
		badLifeText = new Label("Enemy Soldier Alive:");
		scoreText = new Label("Score:");
		information = new Label("State:");
		informationValue = new Label("Playing");
		currentLevel = new Label("Level:");
		createGUI();
	}

	public void createGUI() {
		f = new Frame("Link STR");
		f.dispose();

		createMenuBar();
		Container c = createStatusBar();

		defaultCanvas = new CanvasDefaultImpl();
		defaultCanvas.setSize(SPRITE_SIZE * NB_COLUMNS, SPRITE_SIZE * NB_ROWS);
		f.add(defaultCanvas);
		f.add(c, BorderLayout.NORTH);
		f.pack();
		f.setVisible(true);

		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	private void createMenuBar() {
		MenuBar menuBar = new MenuBar();
		Menu file = new Menu("file");
		MenuItem start = new MenuItem("new game");
		MenuItem save = new MenuItem("save");
		MenuItem restore = new MenuItem("load");
		MenuItem quit = new MenuItem("quit");
		Menu game = new Menu("game");
		MenuItem pause = new MenuItem("pause");
		MenuItem resume = new MenuItem("resume");
		menuBar.add(file);
		menuBar.add(game);
		f.setMenuBar(menuBar);

		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		restore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restore();
			}
		});
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pause();
			}
		});
		resume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resume();
			}
		});

		file.add(start);
		file.add(save);
		file.add(restore);
		file.add(quit);
		game.add(pause);
		game.add(resume);
	}

	private Container createStatusBar() {
		JPanel c = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		c.setLayout(layout);
		linkAliveValue = new Label(Integer.toString(linkAlive[0].getValue()));
		badLinkAliveValue = new Label(Integer.toString(badLinkAlive[0].getValue()));
		scoreValue = new Label(Integer.toString(score[0].getValue()));
		currentLevelValue = new Label(Integer.toString(levelNumber));
		c.add(lifeText);
		c.add(linkAliveValue);
		c.add(badLifeText);
		c.add(badLinkAliveValue);
		/*
		c.add(scoreText);
		c.add(scoreValue);
		*/
		c.add(currentLevel);
		c.add(currentLevelValue);
		c.add(information);
		c.add(informationValue);
		return c;
	}

	public Canvas getCanvas() {
		return defaultCanvas;
	}

	public void start() {
		score[0].addObserver(this);
		badLinkAlive[0].addObserver(this);
		badLinkAlive[0].setValue(1);
		linkAlive[0].addObserver(this);
		linkAlive[0].setValue(1);
		score[0].setValue(0);
		levelNumber = 0;
		for (GameLevel level : gameLevels) {
			endOfGame = new ObservableValue<Boolean>(false);
			endOfGame.addObserver(this);
			try {
				if (currentPlayedLevel != null && currentPlayedLevel.isAlive()) {
					currentPlayedLevel.interrupt();
					currentPlayedLevel = null;
				}
				currentPlayedLevel = (GameLevelLinkImpl) level;
				levelNumber++;
				currentLevelValue.setText(Integer.toString(levelNumber));
				currentPlayedLevel.start();
				currentPlayedLevel.join();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void restore() {
		System.out.println("restore(): Unimplemented operation");
	}

	public void save() {
		System.out.println("save(): Unimplemented operation");
	}

	public void pause() {
		System.out.println("pause(): Unimplemented operation");
		// currentPlayedLevel.suspend();
	}

	public void resume() {
		System.out.println("resume(): Unimplemented operation");
		// currentPlayedLevel.resume();
	}

	public ObservableValue<Integer>[] score() {
		return score;
	}

	public ObservableValue<Integer>[] life() {
		return linkAlive;
	}

	public ObservableValue<Boolean> endOfGame() {
		return endOfGame;
	}

	public void setLevels(ArrayList<GameLevel> levels) {
		gameLevels = levels;
	}

	public void update(Observable o, Object arg) {
		if (o == endOfGame) {
			if (endOfGame.getValue()) {
				informationValue.setText("You win");
				Sound winSound = new Sound("sounds/win.wav");
				winSound.play();
				currentPlayedLevel.interrupt();
				currentPlayedLevel.end();
			}
		} else {
			if(o == linkAlive[0]){
				for (ObservableValue<Integer> lifeObservable : linkAlive) {
					if (o == lifeObservable) {
						int lives = ((ObservableValue<Integer>) o).getValue();
						linkAliveValue.setText(Integer.toString(lives));
						if (lives == 0) {
							informationValue.setText("Defeat");
							Sound winSound = new Sound("sounds/loose.wav");
							winSound.play();
							currentPlayedLevel.interrupt();
							currentPlayedLevel.end();
						}
					}
				}
				for (ObservableValue<Integer> scoreObservable : score) {
					if (o == scoreObservable) {
						scoreValue
								.setText(Integer
										.toString(((ObservableValue<Integer>) o)
												.getValue()));
					}
				}
			}
			else{
				if(badLinkAlive[0].getValue() == 0)
					endOfGame();
			}
		}
	}

	public ObservableValue<Integer>[] getLinkAlive() {
		return linkAlive;
	}
	
	public ObservableValue<Integer>[] getBadLinkAlive() {
		return badLinkAlive;
	}
}
