package Client.Windows;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Client.ChessClient;
import Server.Player;
import Server.Request.GameRequest;

public class GameRequestedDialog extends JDialog{
	private static final long serialVersionUID = 1L;
	private JButton denyButton;
	private JButton acceptButton;
	
	private Player requesting;
	private ChessClient cc;
	
	public GameRequestedDialog(GameRequest gr,ChessClient cc){
    	super(cc.getClientWindow(),"Game Request",true);
    	this.cc = cc;
    	this.requesting = gr.getAsking();
    	
    	denyButton = new JButton("Deny");
    	acceptButton = new JButton("Accept");
    	setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10,10,10,10);
		
		gbc.gridy = 1;
    	add(new JLabel(requesting.getUsername() + " is requesting to play with you."),gbc);
		
    	gbc.gridy = 2;
    	JPanel buttonsPanel = new JPanel();
    	buttonsPanel.setOpaque(false);
    	buttonsPanel.setLayout(new FlowLayout());
    	buttonsPanel.add(acceptButton);
    	buttonsPanel.add(denyButton);
    	
    	add(buttonsPanel,gbc);
    	setLocationRelativeTo(cc.getClientWindow());
    	pack();
    	
    	denyButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				// IF YOU GET FROM THE SAME PLAYER IT MEANS CANCEL
	            GameRequestedDialog.this.cc.sendToServer(new GameRequest(GameRequestedDialog.this.cc.getPlayer(),GameRequestedDialog.this.requesting));
			}
		});
    	acceptButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				// IF YOU GET FROM THE SAME PLAYER IT MEANS CANCEL
	            //chessClient.sendToServer(new GameRequest(chessClient.getPlayer(),GameRequestDialog.this.requesting));
			}
		});
	}
}
