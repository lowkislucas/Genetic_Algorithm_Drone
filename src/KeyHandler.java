import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	boolean leftThrustOn, rightThrustOn, leftThrusterLeft, leftThrusterRight, rightThrusterLeft, rightThrusterRight;
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		if(c==KeyEvent.VK_UP) {
			rightThrustOn = true;
		}
		if(c==KeyEvent.VK_LEFT) {
			rightThrusterLeft = true;
		}
		if(c==KeyEvent.VK_RIGHT) {
			rightThrusterRight = true;
		}
		if(c==KeyEvent.VK_W) {
			leftThrustOn = true;
		}
		if(c==KeyEvent.VK_A) {
			leftThrusterLeft = true;
		}
		if(c==KeyEvent.VK_D) {
			leftThrusterRight = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int c = e.getKeyCode();
		if(c==KeyEvent.VK_UP) {
			rightThrustOn = false;
		}
		if(c==KeyEvent.VK_LEFT) {
			rightThrusterLeft = false;
		}
		if(c==KeyEvent.VK_RIGHT) {
			rightThrusterRight = false;
		}
		if(c==KeyEvent.VK_W) {
			leftThrustOn = false;
		}
		if(c==KeyEvent.VK_A) {
			leftThrusterLeft = false;
		}
		if(c==KeyEvent.VK_D) {
			leftThrusterRight = false;
		}
		
	}

}
