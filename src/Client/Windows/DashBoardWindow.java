package Client.Windows;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class DashBoardWindow extends JPanel {
	private static final long serialVersionUID = -8690011202797301705L;
	
	/// Connections
	private GameBoardWindow gameboard;
	private ClientPanelWindow cpw;
	
	public DashBoardWindow(ClientPanelWindow cpw) {
		this.cpw = cpw;
		initializeElements();
		createGUI();
	}
	
	private void initializeElements() {
		gameboard = new GameBoardWindow();
	}
	
	private void createGUI() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 2;
		
		add(gameboard, gbc);
		
		
	}

}
