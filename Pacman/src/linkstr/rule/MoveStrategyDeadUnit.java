package linkstr.rule;

import java.awt.Point;

import gameframework.base.MoveStrategy;
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;

public class MoveStrategyDeadUnit implements MoveStrategy {
	private SpeedVector stopped = new SpeedVectorDefaultImpl(new Point(0,
			0));
	
	@Override
	public SpeedVector getSpeedVector() {
		return stopped;
	}

}
