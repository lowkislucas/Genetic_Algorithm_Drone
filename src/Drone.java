import java.awt.Color;
import java.awt.Graphics2D;

public class Drone {
	public int x, y;
	public int speed;
	DronePanel dronepanel;
	KeyHandler keyH;
	public Drone(DronePanel dronepanel, KeyHandler keyH) {
		this.dronepanel = dronepanel;
		this.keyH = keyH;
		setDefaultValues();
	}
	public void setDefaultValues() {
		x = 100;
		y = 100;
		speed = 4;
		
	}
	public void update() {
		if(keyH.up==true) {
			y-=speed;
		}
		if(keyH.down==true) {
			y+=speed;
		}
		if(keyH.right==true) {
			x+=speed;
		}
		if(keyH.left==true) {
			x-=speed;
		}
	}
	public void draw(Graphics2D g2) {
		g2.setColor(Color.white);
		g2.fillRect(x, y, 50, 50);
	}
}
