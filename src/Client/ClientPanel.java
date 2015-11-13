package client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import game.GameBoard;


public class ClientPanel extends JPanel {
	private static final long serialVersionUID = 3609831945869059312L;
		
	private MainMenu mainMenu;
	//private DashBoard dashBoard;
	private RegisterWindow register;
	private GameBoard gameboard;
	
	public ClientPanel() {

		mainMenu = new MainMenu(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				ClientPanel.this.removeAll();
				//ClientPanel.this.add(dashBoard);
				ClientPanel.this.revalidate();
				ClientPanel.this.repaint();
			}
		}, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				ClientPanel.this.removeAll();
				ClientPanel.this.add(register);
				ClientPanel.this.revalidate();
				ClientPanel.this.repaint();
			}
		});
		
		gameboard = new GameBoard();
		
		setLayout(new BorderLayout());
		add(mainMenu);
		
		refreshElements();	
	}
	
	private void refreshElements() {
		register = new RegisterWindow(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				ClientPanel.this.removeAll();
				ClientPanel.this.add(gameboard);
				ClientPanel.this.revalidate();
				ClientPanel.this.repaint();
			}
		});
	}
	

}
