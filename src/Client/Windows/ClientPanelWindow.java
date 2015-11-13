package Client.Windows;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;


public class ClientPanelWindow extends JPanel {
	private static final long serialVersionUID = 3609831945869059312L;
		
	private MainMenuWindow mainMenu;
	private DashBoardWindow dashBoard;
	private RegisterWindow register;
	private GameBoardWindow gameboard;
	
	public ClientPanelWindow() {

		mainMenu = new MainMenuWindow(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				ClientPanelWindow.this.removeAll();
				//ClientPanel.this.add(dashBoard);
				ClientPanelWindow.this.revalidate();
				ClientPanelWindow.this.repaint();
			}
		}, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				ClientPanelWindow.this.removeAll();
				ClientPanelWindow.this.add(register);
				ClientPanelWindow.this.revalidate();
				ClientPanelWindow.this.repaint();
			}
		});
		
		gameboard = new GameBoardWindow();
		
		setLayout(new BorderLayout());
		add(mainMenu);
		
		refreshElements();	
	}
	
	private void refreshElements() {
		register = new RegisterWindow(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				ClientPanelWindow.this.removeAll();
				ClientPanelWindow.this.add(gameboard);
				ClientPanelWindow.this.revalidate();
				ClientPanelWindow.this.repaint();
			}
		});
	}
	

}
