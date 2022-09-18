import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Drone {
	int x, y;
	int speed;
	double rightThrusterAngle = 0;
	double leftThrusterAngle = 0;
	double rightThrusterSpeed = 0;
	double leftThrusterSpeed = 0;
	double rightThrusterPower = 0;
	double leftThrusterPower = 0;
	DronePanel dronepanel;
	KeyHandler keyH;
	BufferedImage droneImg;
	BufferedImage leftThrusterImg;
	BufferedImage rightThrusterImg;
	BufferedImage leftThrusterOnImg;
	BufferedImage rightThrusterOnImg;
	public Drone(DronePanel dronepanel, KeyHandler keyH) {
		this.dronepanel = dronepanel;
		this.keyH = keyH;
		setDefaultValues();
		loadImages();
	}
	
	public void loadImages() {
		try {
			droneImg = ImageIO.read(new File("res/drone.png"));
			leftThrusterImg = ImageIO.read(new File("res/thruster.png"));
			rightThrusterImg = ImageIO.read(new File("res/thruster.png"));
			leftThrusterOnImg = ImageIO.read(new File("res/thrusterOn.png"));
			rightThrusterOnImg = ImageIO.read(new File("res/thrusterOn.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }
	
	public void setDefaultValues() {
		x = 100;
		y = 100;
		speed = 4;
		
	}
	public void update() {
		y+=10;
		if(y>650) {
			y=650;
		}
		if(keyH.leftThrusterLeft==true) {
			leftThrusterAngle += 0.1;
		}
		if(keyH.leftThrusterRight==true) {
			leftThrusterAngle -= 0.1;
		}
		if(keyH.rightThrusterLeft==true) {
			rightThrusterAngle += 0.1;
		}
		if(keyH.rightThrusterRight==true) {
			rightThrusterAngle -= 0.1;
		}
		if(keyH.leftThrustOn==true) {
			leftThrusterPower+=0.1;
		}
		if(keyH.rightThrustOn==true) {
			rightThrusterPower+=0.1;
		}
		if(leftThrusterPower>0) {
			leftThrusterPower-=0.05;
		}
		if(rightThrusterPower>0) {
			rightThrusterPower-=0.05;
		}
		x -= leftThrusterPower * Math.sin(leftThrusterAngle);
		y -= leftThrusterPower * Math.cos(leftThrusterAngle);
		
	}
	public void drawBody(Graphics2D g2) {
		
		g2.drawImage(droneImg, x, y, 96, 96, null);
		
	}
	public void drawLeftThruster(Graphics2D g2) {
		AffineTransform at = AffineTransform.getTranslateInstance(x-21, y+32);
		at.rotate(-leftThrusterAngle,leftThrusterImg.getWidth()/2, leftThrusterImg.getHeight()/2);
		if(leftThrusterPower>0) {
			g2.drawImage(leftThrusterOnImg, at, null);
		}
		else {
			g2.drawImage(leftThrusterImg, at, null);
		}
	}
	public void drawRightThruster(Graphics2D g2) {
		AffineTransform at = AffineTransform.getTranslateInstance(x+85, y+32);
		at.rotate(-rightThrusterAngle,rightThrusterImg.getWidth()/2, rightThrusterImg.getHeight()/2);
		if(rightThrusterPower>0) {
			g2.drawImage(rightThrusterOnImg, at, null);
		}	
		else {
			g2.drawImage(rightThrusterImg, at, null);
		}
		System.out.println(rightThrusterPower);
	}
}
