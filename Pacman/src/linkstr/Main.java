package linkstr;

import gameframework.game.GameLevel;
import gameframeworkExtension.Sound;

import java.util.ArrayList;

import linkstr.rule.GameLinkImpl;

public class Main {
	public static void main(String[] args) {

		/* for test on JAR
		Sound s = new Sound("C:/Users/Quentin/Documents/GitHub/GameFrameworkUB1/Pacman/background.wav");
		s.loop();
		*/
		Sound s = new Sound("sounds/background.wav");
		s.loop();
		
		GameLinkImpl g = new GameLinkImpl();
		ArrayList<GameLevel> levels = new ArrayList<GameLevel>();

		levels.add(new GameLevelOne(g)); // only one level is available at this time
		
		g.setLevels(levels);

		Sound o = new Sound("sounds/opening.wav");
		o.play();
		g.start();
	}
}