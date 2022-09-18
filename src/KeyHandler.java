import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	boolean up, down, left, right;
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		if(c==KeyEvent.VK_UP) {
			up = true;
		}
		if(c==KeyEvent.VK_DOWN) {
			down = true;
		}
		if(c==KeyEvent.VK_LEFT) {
			left = true;
		}
		if(c==KeyEvent.VK_RIGHT) {
			right = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int c = e.getKeyCode();
		if(c==KeyEvent.VK_UP) {
			up = false;
		}
		if(c==KeyEvent.VK_DOWN) {
			down = false;
		}
		if(c==KeyEvent.VK_LEFT) {
			left = false;
		}
		if(c==KeyEvent.VK_RIGHT) {
			right = false;
		}
		
	}

}
