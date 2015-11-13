package client;

import java.awt.Dimension;

import javax.swing.JFrame;


public class ClientWindow extends JFrame {
	private static final long serialVersionUID = 5147395078473323173L;
	
	private final static Dimension minSize = new Dimension(640,480);
	private final static Dimension maxSize = new Dimension(960,640);
	
	{
		setTitle("Chess");
		setSize(minSize);
		setMinimumSize(minSize);
		setMaximumSize(maxSize);
		add(new ClientPanel());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		ClientWindow mainWindow = new ClientWindow();
		mainWindow.setVisible(true);
	}
}
