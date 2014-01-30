package linkstr.rule;

import gameframework.base.MoveStrategy;
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;

import java.awt.Point;
import java.util.Random;

public class MoveStrategyRandomBadLink implements MoveStrategy {
	SpeedVector currentMove = new SpeedVectorDefaultImpl(new Point(0, 0));
	static Random random = new Random();
	int changeFreq = 5;
	
	//plus freq élevé, moins de changement de direction
	public void setChangeFreq(int freq){
		changeFreq = freq;
	}

	public SpeedVector getSpeedVector() {
		int i = random.nextInt(changeFreq);

		switch (i) {
		case 0:
			currentMove.setDirection(new Point(1, 0));
			break;
		case 1:
			currentMove.setDirection(new Point(-1, 0));
			break;
		case 2:
			currentMove.setDirection(new Point(0, -1));
			break;
		case 3:
			currentMove.setDirection(new Point(0, 1));
			break;
		}
		return currentMove;
	}
}
