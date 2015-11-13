package client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import Game.GameBoard;

public class DashBoard extends JPanel {
	
	private GameBoard gameboard;
	
	public DashBoard() {
		initializeElements();
		createGUI();
	}
	
	private void initializeElements() {
		gameboard = new GameBoard();
	}
	
	private void createGUI() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 2;
		
		add(gameboard, gbc);
		
		
	}

}
