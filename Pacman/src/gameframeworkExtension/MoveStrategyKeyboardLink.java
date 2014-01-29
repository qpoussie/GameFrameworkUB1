package gameframeworkExtension;

import gameframework.base.MoveStrategy;
import gameframework.base.SpeedVector;
import gameframework.base.SpeedVectorDefaultImpl;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MoveStrategyKeyboardLink implements MoveStrategy, KeyListener {
		protected SpeedVector speedVector = new SpeedVectorDefaultImpl(new Point(0,
				0));
		
		private int currentKey = 0;

		public SpeedVector getSpeedVector() {
			return speedVector;
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyCode() == currentKey)
				speedVector.setDirection(new Point(0, 0));	
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int keycode = e.getKeyCode();
			switch (keycode) {
			case KeyEvent.VK_RIGHT:
				speedVector.setDirection(new Point(1, 0));
				currentKey = keycode;
				break;
			case KeyEvent.VK_LEFT:
				speedVector.setDirection(new Point(-1, 0));
				currentKey = keycode;
				break;
			case KeyEvent.VK_UP:
				speedVector.setDirection(new Point(0, -1));
				currentKey = keycode;
				break;
			case KeyEvent.VK_DOWN:
				speedVector.setDirection(new Point(0, 1));
				currentKey = keycode;
				break;
			}
		}
	}
