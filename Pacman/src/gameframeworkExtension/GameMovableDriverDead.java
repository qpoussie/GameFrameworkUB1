package gameframeworkExtension;

import gameframework.base.Movable;
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;
import gameframework.game.GameMovableDriver;

public class GameMovableDriverDead implements GameMovableDriver{

	@Override
	public SpeedVector getSpeedVector(Movable m) {
		return SpeedVectorDefaultImpl.createNullVector();
	}

}
