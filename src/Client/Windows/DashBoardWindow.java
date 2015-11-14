package Client.Windows;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JPanel;

import Client.ChessClient;
import Client.PlayersRequest;
import Server.Player;

public class DashBoardWindow extends JPanel {
	private static final long serialVersionUID = -8690011202797301705L;
	
	/// Connections
	private GameBoardWindow gameboard;
	private ClientPanelWindow cpw;
	private ChessClient chessClient;
	
	// Components
	private JList playersList;
	
	public DashBoardWindow(ClientPanelWindow cpw) {
		this.cpw = cpw;
		chessClient = cpw.getChessClient();
		requestOnlinePlayers();
		initializeElements();
		createGUI();
	}
	
	
	private void initializeElements() {
//		gameboard = new GameBoardWindow();
		playersList = new JList<String>();
	}
	
	private void createGUI() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 2;
		
		add(gameboard, gbc);
		
	}

	private void setOnlinePlayers(Vector<Player> onlinePlayers) {
		int numPlayers = onlinePlayers.size();
		String[] data = new String[numPlayers];
		
		for (int i=0; i < onlinePlayers.size(); i++) {
			data[i] = onlinePlayers.get(i).getUsername();
		}

		playersList.setListData(data);
		playersList.repaint();
	}
	
	//////////// Server Communication //////////////
	private void requestOnlinePlayers() {
		PlayersRequest request = new PlayersRequest();
		chessClient.sendToServer(request);
	}


}
