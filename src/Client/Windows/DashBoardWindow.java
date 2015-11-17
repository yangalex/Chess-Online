package Client.Windows;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import Client.ChessClient;
import Server.Player;
import Server.Request.ChatMessage;
import Server.Request.GameRequest;
import Server.Request.OnlinePlayers;

public class DashBoardWindow extends JPanel{
	private static final long serialVersionUID = -8690011202797301705L;
	
	/// Connections
	private ClientPanelWindow cpw;
	private ChessClient chessClient;
	
	// Components
	private JList playersList;
	private Vector<Player> players;
	
	public DashBoardWindow(ClientPanelWindow cpw) {
		this.cpw = cpw;
		chessClient = cpw.getChessClient();
		requestOnlinePlayers();
		initializeElements();
		createGUI();
		DashBoardPlayersThread thread = new DashBoardPlayersThread(this);
		thread.start();
	}
	
	
	private void initializeElements() {
		playersList = new JList<String>();
		playersList.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList<String> list = (JList<String>)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            // Double-click detected
		            int index = list.locationToIndex(evt.getPoint());
		            Player requesting = players.get(index);
		            chessClient.sendToServer(new GameRequest(chessClient.getPlayer(),requesting));
		            
		            class GameRequestDialog extends JDialog{
						private static final long serialVersionUID = 1L;
						private JButton cancelButton;
						private Player requesting;
						GameRequestDialog(Player requesting){
			            	super(chessClient.getClientWindow(),"Game Request",true);
			            	this.requesting = requesting;
			            	cancelButton = new JButton("Cancel Request");
			            	
			            	setLayout(new GridBagLayout());
			        		GridBagConstraints gbc = new GridBagConstraints();
			        		gbc.insets = new Insets(10,10,10,10);
			        		
			        		gbc.gridy = 1;
			            	add(new JLabel("Waiting for " + requesting.getUsername() + "..."),gbc);
			        		
			            	gbc.gridy = 2;
			            	add(cancelButton,gbc);
			            	setLocationRelativeTo(chessClient.getClientWindow());
			            	pack();
			            	
			            	cancelButton.addActionListener(new ActionListener(){

								@Override
								public void actionPerformed(ActionEvent e) {
									dispose();
									GameRequest gr = new GameRequest(chessClient.getPlayer(), GameRequestDialog.this.requesting);
									gr.setCancel(true);
						            chessClient.sendToServer(gr);
						            chessClient.sendToServer(new ChatMessage("Hello"));
								}});
		            	}
		            }
		            
		            GameRequestDialog dialog = new GameRequestDialog(requesting);
		            dialog.setVisible(true);
		            
		        }
		    }
		});
	}
	
	private void createGUI() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 2;
		
		add(playersList,gbc);
		
	}

	public void setOnlinePlayers(Vector<Player> onlinePlayers) {
		players = onlinePlayers;
		int numPlayers = onlinePlayers.size();
		String[] data = new String[numPlayers];
		if (numPlayers == 0){
			data = new String[1];
			data[0] = "No players online";
			playersList.setEnabled(false);
		}
		else{
			for (int i=0; i < numPlayers; i++) {
				data[i] = onlinePlayers.get(i).getUsername();
			}
			playersList.setEnabled(true);

		}

		playersList.setListData(data);
		playersList.repaint();
	}
	
	//////////// Server Communication //////////////
	protected void requestOnlinePlayers() {
		OnlinePlayers request = new OnlinePlayers();
		chessClient.sendToServer(request);
	}


}
