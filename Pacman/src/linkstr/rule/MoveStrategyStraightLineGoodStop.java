package linkstr.rule;

import gameframework.base.MoveStrategy;
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;

import java.awt.Point;

public class MoveStrategyStraightLineGoodStop implements MoveStrategy {

	Point goal, currentPosition;

	public MoveStrategyStraightLineGoodStop(Point pos, Point goal) {
		this.goal = goal;
		this.currentPosition = pos;
	}

	public SpeedVector getSpeedVector() {
		double dist = currentPosition.distance(goal);
		int xDirection = (int) Math.rint((goal.getX() - currentPosition.getX())
				/ dist);
		if(goal.getX()-currentPosition.getX() < 8 && goal.getX()-currentPosition.getX() > -8)
			xDirection = 0;
		int yDirection = (int) Math.rint((goal.getY() - currentPosition.getY())
				/ dist);
		if(goal.getY()-currentPosition.getY() < 8 && goal.getY()-currentPosition.getY() > -8)
			yDirection = 0;
		SpeedVector move = new SpeedVectorDefaultImpl(new Point(xDirection,
				yDirection));
		return move;
	}
}
