package Client.Windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import Client.ChessClient;
import Library.FontLibrary;
import Library.ImageLibrary;
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
	private GameRequestDialog dialog;
	
	private JPanel livePlayersPanel, sidePanel;
	
	public DashBoardWindow(ClientPanelWindow cpw) {		
		this.cpw = cpw;
		chessClient = cpw.getChessClient();
		requestOnlinePlayers();
		initializeElements();
		createGUI();		
		DashBoardPlayersThread thread = new DashBoardPlayersThread(this);
		thread.start();
	}
	
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
				}});
    	}
    }
	
	
	private void initializeElements() {
		livePlayersPanel = new JPanel();
		livePlayersPanel.setBackground(new Color(0,0,0,20));
		//livePlayersPanel.setOpaque(true);
		
		
		sidePanel = new JPanel();
		sidePanel.setBackground(new Color(255,255,255,40));
		JLabel profileLabel = new JLabel("profile");
		profileLabel.setFont(FontLibrary.getFont("fonts/optimus.ttf", Font.PLAIN, 15));
		profileLabel.setForeground(Color.WHITE);
		sidePanel.add(profileLabel);
		
		sidePanel.setPreferredSize(new Dimension(getWidth(),getHeight()));
		
		playersList = new JList<String>();
		//playersList.setBorder(null);
		//playersList.setBackground(new Color(0,0,0,20));
		playersList.setSelectionBackground(new Color(255,255,255,0));
		playersList.setOpaque(true);
		playersList.setPreferredSize(new Dimension(cpw.getWidth()/2 + cpw.getWidth()/3,cpw.getHeight() - 10));
		playersList.setFont(FontLibrary.getFont("fonts/optimus.ttf", Font.PLAIN, 15));
		playersList.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList<String> list = (JList<String>)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            // Double-click detected
		            int index = list.locationToIndex(evt.getPoint());
		            Player requesting = players.get(index);
		            chessClient.sendToServer(new GameRequest(chessClient.getPlayer(),requesting));
		            
		            dialog = new GameRequestDialog(requesting);
		            dialog.setModal(false);
		            dialog.setVisible(true); 
		        }
		    }
		});
	}
	
	private void createGUI() {
		livePlayersPanel.add(playersList);
		
		setLayout(new BorderLayout());
		add(livePlayersPanel,BorderLayout.WEST);
		add(sidePanel, BorderLayout.EAST);
		revalidate();
		repaint();
	}

	public void setOnlinePlayers(Vector<Player> onlinePlayers) {
		players = onlinePlayers;
		int numPlayers = onlinePlayers.size();
		String[] data = new String[numPlayers];
		if (numPlayers == 0){
			data = new String[1];
			data[0] = "  no players online";
			playersList.setEnabled(false);
		}
		else{
			for (int i=0; i < numPlayers; i++) {
				data[i] = "  " + onlinePlayers.get(i).getUsername();
			}
			playersList.setEnabled(true);

		}

		playersList.setListData(data);
		playersList.repaint();
	}
	
	public GameRequestDialog getGameRequestDialog() {
		return dialog;
	}
	
	public void clearDialog() {
		if (dialog != null) {
			dialog.dispose();
			dialog = null;
		}
	}
	
	//////////// Server Communication //////////////
	protected void requestOnlinePlayers() {
		OnlinePlayers request = new OnlinePlayers();
		chessClient.sendToServer(request);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(ImageLibrary.getImage("images/background_texture.jpg"), 0, 0, getWidth(), getHeight(), null);
	}

}
