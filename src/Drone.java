import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
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
	
	double maxRightThrusterSpeed = 3;
	double maxLeftThrusterSpeed = 3;
	
	double droneAngle = 0;
	
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
		x = 300;
		y = 100;
		speed = 4;
		
	}
	public void update() {
		y+=5;
		if(y>650) {
			y=0;
		}
		if(y<0) {
			y=650;
		}
		if(x>1100) {
			x=0;
		}
		if(x<0) {
			x=1100;
		}
		if(keyH.leftThrusterLeft==true) {
			leftThrusterAngle += 0.05;
		}
		if(keyH.leftThrusterRight==true) {
			leftThrusterAngle -= 0.05;
		}
		if(keyH.rightThrusterLeft==true) {
			rightThrusterAngle += 0.05;
		}
		if(keyH.rightThrusterRight==true) {
			rightThrusterAngle -= 0.05;
		}
		if(keyH.leftThrustOn==true) {
			leftThrusterSpeed+=0.05;
		}
		if(keyH.rightThrustOn==true) {
			rightThrusterSpeed+=0.05;
		}
		if(leftThrusterSpeed>0) {
			leftThrusterSpeed-=0.005;
		}
		if(rightThrusterSpeed>0) {
			rightThrusterSpeed-=0.005;
		}
		if(leftThrusterSpeed>maxLeftThrusterSpeed) {
			leftThrusterSpeed = maxLeftThrusterSpeed;
		}
		if(rightThrusterSpeed>maxRightThrusterSpeed) {
			rightThrusterSpeed = maxRightThrusterSpeed;
		}
		if(droneAngle==0) {
			if(leftThrusterAngle==0&&keyH.leftThrustOn==true) {
				leftThrusterAngle-=0.15;
			}
			if(rightThrusterAngle==0&&keyH.rightThrustOn==true) {
				rightThrusterAngle+=0.15;
			}
		}
		droneAngle-=leftThrusterSpeed*Math.cos(Math.PI/2-leftThrusterAngle)/70+rightThrusterSpeed*Math.cos(Math.PI/2-rightThrusterAngle)/70;
		//x -= leftThrusterSpeed * Math.sin(-droneAngle);
		if(keyH.leftThrustOn==true) {
			x -= leftThrusterSpeed * Math.sin(-droneAngle+leftThrusterAngle);
			y -= leftThrusterSpeed * Math.cos(-droneAngle+leftThrusterAngle);
		}
		if(keyH.rightThrustOn==true) {
			x -=  rightThrusterSpeed * Math.sin(-droneAngle+rightThrusterAngle);
			y -= rightThrusterSpeed * Math.cos(-droneAngle+rightThrusterAngle);
			
		}
		x -= leftThrusterSpeed * Math.sin(-droneAngle) + rightThrusterSpeed * Math.sin(-droneAngle);
		y -= leftThrusterSpeed * Math.cos(-droneAngle) + rightThrusterSpeed * Math.cos(-droneAngle);
		//x -= leftThrusterSpeed * Math.sin(-droneAngle+leftThrusterAngle) + rightThrusterSpeed * Math.sin(-droneAngle+rightThrusterAngle);
		//y -= leftThrusterSpeed * Math.cos(-droneAngle+leftThrusterAngle) + rightThrusterSpeed * Math.cos(-droneAngle+rightThrusterAngle);
		System.out.println(Math.toDegrees(droneAngle)+"             "+Math.toDegrees(leftThrusterAngle)+"             "+Math.toDegrees(rightThrusterAngle));
		
	}
	public void drawBody(Graphics2D g2) {
		g2.rotate(droneAngle,x+48,y+48);
		g2.drawImage(droneImg, x, y, 96, 96, null);
		
	}
	public void drawLeftThruster(Graphics2D g2) {
		AffineTransform at = AffineTransform.getTranslateInstance(x-21, y+32);
		at.rotate(-leftThrusterAngle,leftThrusterImg.getWidth()/2, leftThrusterImg.getHeight()/2);
		if(keyH.leftThrustOn==true) {
			g2.drawImage(leftThrusterOnImg, at, null);
		}
		else {
			g2.drawImage(leftThrusterImg, at, null);
		}
	}
	public void drawRightThruster(Graphics2D g2) {
		AffineTransform at = AffineTransform.getTranslateInstance(x+85, y+32);
		at.rotate(-rightThrusterAngle,rightThrusterImg.getWidth()/2, rightThrusterImg.getHeight()/2);
		if(keyH.rightThrustOn==true) {
			g2.drawImage(rightThrusterOnImg, at, null);
		}	
		else {
			g2.drawImage(rightThrusterImg, at, null);
		}
	}
}
