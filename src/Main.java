import javax.swing.JFrame;

public class Main {
	
	public Main() {
		JFrame window = new JFrame();
		DronePanel dronepanel = new DronePanel();
		window.add(dronepanel);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Drone!!");
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		dronepanel.startDroneThread();
	}
	
	public static void main(String[] args) {
		new Main();
	}
	
}
