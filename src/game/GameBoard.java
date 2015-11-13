package game;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class GameBoard extends JPanel {
	private static final long serialVersionUID = 6415716059554739910L;
	
	private Tile[][] panels;
	
	
	public GameBoard() {
		initializeElements();
		createGUI();
	}
	
	private void initializeElements() {
		panels = new Tile[8][8];
	}
	
	private void createGUI() {
		setLayout(new GridLayout(8,8));
		
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				if(i%2 == 0) {
					if(j%2 == 0) {
						panels[i][j] = new Tile(Color.WHITE);
						add(panels[i][j]);
					}
					else {
						panels[i][j] = new Tile(Color.BLACK);
						add(panels[i][j]);
					}
				}
				else {
					if(j%2 == 0) {
						panels[i][j] = new Tile(Color.BLACK);
						add(panels[i][j]);
					}
					else {
						panels[i][j] = new Tile(Color.WHITE);
						add(panels[i][j]);
					}
				}
				
			}
		}
	}
}
