import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class DronePanel extends JPanel implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	KeyHandler keyH = new KeyHandler();
	Thread droneThread;
	Drone drone = new Drone(this,keyH);
	final int FPS = 60;
	
	public DronePanel() {
		this.setPreferredSize(new Dimension(1280,720));
		this.setBackground(Color.black);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startDroneThread() {
		droneThread = new Thread(this);
		droneThread.start();
	}
	
	public void update() {
		drone.update();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		drone.drawBody(g2);
		drone.drawRightThruster(g2);
		drone.drawLeftThruster(g2);
		g2.dispose();
	}

	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		while(droneThread!=null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			if(delta >= 1) {
				update();
				repaint();
				delta--;
			}
			
		}
		
	}
}
