package game;

import java.awt.Color;

import javax.swing.JPanel;

public class Tile extends JPanel {
	private static final long serialVersionUID = 6415716059554739910L;
	
	private final Color color;
	
	public Tile(Color c) {
		color = c;
		
		setBackground(c);
		setOpaque(true);
	}
	
}
