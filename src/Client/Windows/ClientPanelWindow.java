package Client.Windows;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Client.Settings;
import Server.Request.Authenticate;

import javax.swing.JPanel;

import Client.ChessClient;


public class ClientPanelWindow extends JPanel {
	private static final long serialVersionUID = 3609831945869059312L;
		
	private MainMenuWindow mainMenuWindow;
	private DashBoardWindow dashBoardWindow;
	private RegisterWindow registerWindow;
	private LoginWindow loginWindow;
	private GameBoardWindow gameBoardWindow;
	
	// SERVER
	private ChessClient chessClient = null;
	
	public ClientPanelWindow() {
		setLayout(new BorderLayout());
		createGameBoardWindow();
		
		createMainMenuWindow();
		add(mainMenuWindow);
		
	}
	
	private void createGameBoardWindow() {
		gameBoardWindow = new GameBoardWindow();
	}

	private void createMainMenuWindow() {
		mainMenuWindow = new MainMenuWindow(new ActionListener() {
			@Override
			// Action for Login in
			public void actionPerformed(ActionEvent ae) {
				try {
					chessClient = new ChessClient(Settings.host, Settings.port, ClientPanelWindow.this);
					ClientPanelWindow.this.createLoginWindow();
					ClientPanelWindow.this.removeAll();
					ClientPanelWindow.this.add(loginWindow);
					ClientPanelWindow.this.revalidate();
					ClientPanelWindow.this.repaint();
				} catch (IOException e) {
					mainMenuWindow.errorMessage("Could not connect to Server, please try again later.");
					if (Settings.debug) e.printStackTrace();
				}
			}
		}, new ActionListener() {
			// Action for Register new player
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					chessClient = new ChessClient(Settings.host, Settings.port, ClientPanelWindow.this);
					ClientPanelWindow.this.createRegisterWindow();
					ClientPanelWindow.this.removeAll();
					ClientPanelWindow.this.add(registerWindow);
					ClientPanelWindow.this.revalidate();
					ClientPanelWindow.this.repaint();
				} catch (IOException e) {
					mainMenuWindow.errorMessage("Could not connect to Server, please try again later.");
					if (Settings.debug) e.printStackTrace();
				}
			}
		});
		
	}

	protected LoginWindow createLoginWindow() {
		loginWindow = new LoginWindow(new ActionListener() {
			@Override
			// Action to login
			public void actionPerformed(ActionEvent ae) {
				if (loginWindow.getUsername().isEmpty()){
					loginWindow.errorMessage("Please enter a username.");
					return;
				}
				if (loginWindow.getPassword().isEmpty()){
					loginWindow.errorMessage("Please enter a password.");

					return;
				}
			}	
		});
		return loginWindow;
	}

	private void createRegisterWindow() {
		registerWindow = new RegisterWindow(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				ClientPanelWindow.this.removeAll();
				ClientPanelWindow.this.add(gameBoardWindow);
				ClientPanelWindow.this.revalidate();
				ClientPanelWindow.this.repaint();
			}
		}, this);
	}
	
	public DashBoardWindow createDashBoardWindow(){
		dashBoardWindow = new DashBoardWindow(this);
		return dashBoardWindow;
	}
	
	public ChessClient getChessClient(){
		return chessClient;
	}
}
