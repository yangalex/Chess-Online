package Client.Windows;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Client.ChessClient;
import Client.Settings;
import Server.Player;
import Server.Request.Authenticate;
import Server.Request.Register;


public class ClientPanelWindow extends JPanel {
	private static final long serialVersionUID = 3609831945869059312L;
		
	private MainMenuWindow mainMenuWindow;
	private DashBoardWindow dashBoardWindow;
	private RegisterWindow registerWindow;
	private LoginWindow loginWindow;
	private GameBoardWindow gameBoardWindow;
	private ClientWindow clientWindow;
	
	// SERVER
	private ChessClient chessClient = null;
	
	public ClientPanelWindow(ClientWindow cw) {
		clientWindow = cw;
		setLayout(new BorderLayout());

		createMainMenuWindow();
		add(mainMenuWindow);
		
	}
	
	//////////// CREATE WINDOWS ///////////////
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

	protected void createLoginWindow() {
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
				getChessClient().sendToServer(new Authenticate(loginWindow.getUsername(), loginWindow.getPassword()));
			}	
		});
	}

	private void createRegisterWindow() {
		registerWindow = new RegisterWindow(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (registerWindow.getFirstName().isEmpty()){
					registerWindow.errorMessage("Please enter a first name");
					return;
				}
				if (registerWindow.getLastName().isEmpty()){
					registerWindow.errorMessage("Please enter a last name");
					return;
				}
				if (registerWindow.getUsername().isEmpty()){
					registerWindow.errorMessage("Please enter a username");
					return;
				}
				if (registerWindow.getPasswordOne().isEmpty() || registerWindow.getPasswordTwo().isEmpty()){
					registerWindow.errorMessage("Please enter a password");
					return;
				}
				if (!registerWindow.getPasswordOne().equals(registerWindow.getPasswordTwo())){
					registerWindow.errorMessage("Password does not match");
					return;
				}
				Register r = new Register(registerWindow.getUsername(), 
						registerWindow.getPasswordOne(),
						registerWindow.getFirstName(),
						registerWindow.getLastName());
				ClientPanelWindow.this.getChessClient().sendToServer(r);
			}
		}, this);
	}
	
	public void createDashBoardWindow(){
		dashBoardWindow = new DashBoardWindow(this);
	}
	
	public void createGameBoardWindow(Player opponent) {
		gameBoardWindow = new GameBoardWindow(this, opponent);
	}
	
	//////////////// GETTTERS //////////////
	
	public ChessClient getChessClient(){
		return chessClient;
	}
	

	public LoginWindow getLoginWindow() {
		return loginWindow;
	}

	public RegisterWindow getRegisterWindow() {
		return registerWindow;
	}

	public GameBoardWindow getGameBoardWindow(){
		return gameBoardWindow;
	}

	
	public DashBoardWindow getDashBoardWindow() {
		return dashBoardWindow;
	}

	public JFrame getClientWindow() {
		// TODO Auto-generated method stub
		return null;
	}

}
