package Client;
import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JFrame;

import Client.Game.Move;
import Client.Windows.ClientPanelWindow;
import Client.Windows.GameRequestedDialog;
import Server.Player;
import Server.Settings;
import Server.Request.Authenticate;
import Server.Request.ChatMessage;
import Server.Request.GameRequest;
import Server.Request.Register;

public class ChessClient extends Thread{
	/// Server Properties
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	/// Player Properties
	private Player player;
	private ClientPanelWindow cpw;
	
	private GameRequestedDialog dialog = null;
	
	public ChessClient(String host, int port, ClientPanelWindow cpw) throws IOException{
		this.cpw = cpw;
		if (connectToServer(host,port)){
			try {
				oos = new ObjectOutputStream(socket.getOutputStream());
				ois = new ObjectInputStream(socket.getInputStream());
				start();
			} catch (IOException e) {
				if(Settings.Debug) e.printStackTrace();
			}	
		}
		else return;
	}
	
	public void sendToServer(Object obj){
		try {
			oos.writeObject(obj);
			oos.flush();
		} catch (IOException e) {
			if (Settings.Debug) e.printStackTrace();
			
		}
	}
	
	private Boolean connectToServer(String host, int port) throws IOException {
		try {
			socket = new Socket(host,port);
			message("Connected sucessfully to server.");
			return true;
		} catch (IOException e) {
			message("Could not connect to server.");
			if (Settings.Debug) e.printStackTrace();
			throw e;
		}
	}
	
	public void clearDialog() {
		if (dialog != null) {
			dialog.dispose();
			dialog = null;
		}
	}
	
	// adds the gameboard window to the main panel
	public void startGame() {
		cpw.removeAll();
		cpw.add(cpw.getGameBoardWindow());
		cpw.revalidate();
		cpw.repaint();	
	}
	
	public void run(){
		while (true){
			try {
				Object obj = ois.readObject();
				processRequest(obj);
			} 
			catch (ClassNotFoundException | IOException e) {
				closeClient();
				if (Settings.Debug) e.printStackTrace();
				return;
			}
		}
	}
	
	public void message(String message){
		System.out.println(message);
	}
	
	public void addChatMessage(String message) {
		cpw.getGameBoardWindow().getChatBox().appendToChatArea(message);		
	}
	
	private void closeClient() {
		try {
			ois.close();
			oos.close();
			socket.close();
			return;
		} catch (IOException e1) {
			if (Settings.Debug) e1.printStackTrace();
		}		
	}

	private void processRequest(Object obj) {
		if (obj instanceof ChatMessage){
			ChatMessage cm = (ChatMessage)obj;
			String message = cm.getSender().getUsername() + ": " + cm.getMessage();
			addChatMessage(message);
		}
		else if (obj instanceof Register){
			cpw.getRegisterWindow().errorMessage(((Register) obj).message);
			cpw.getRegisterWindow().revalidate();
			cpw.getRegisterWindow().repaint(); 
		}
		else if (obj instanceof Authenticate){
			cpw.getLoginWindow().errorMessage(((Authenticate) obj).message);
			cpw.getLoginWindow().revalidate();
			cpw.getLoginWindow().repaint();
		}
		else if (obj instanceof Player){
			setPlayer((Player) obj);
			
			// SHOW DASH BOARD
			cpw.createDashBoardWindow();
			cpw.removeAll();
			cpw.add(cpw.getDashBoardWindow());
			cpw.revalidate();
			cpw.repaint();
		}
		else if (obj instanceof Vector<?>){
			cpw.getDashBoardWindow().setOnlinePlayers((Vector<Player>) obj);
		}
		else if (obj instanceof GameRequest){
			GameRequest request = (GameRequest)obj;

			if (dialog != null && (dialog.getRequestingPlayer().getUsername().equals(request.getAsking().getUsername()))) {
				// Request was cancelled by asking user
				if (request.getCancel() == true) {
					dialog.dispose();
					dialog = null;
				}
			} else {
				// Person who sent the invite receives response back 
				if (request.isResponse()) {
					if (request.getCancel() == true) {
						// game rejected; close dialog on player that sent invite
						cpw.getDashBoardWindow().clearDialog();
					} else {
						// start game and go to gameboard
						System.out.println("START GAME");
						cpw.getDashBoardWindow().clearDialog();
						
						cpw.createGameBoardWindow(request.getAsking(), Color.WHITE);
						startGame();
					}
				} else {
					// initial request (received a request from another user)
					System.out.println("Opened dialog");
					dialog = new GameRequestedDialog(request, this);
					dialog.setModal(false);
					dialog.setVisible(true);
				}
			}
		} else if (obj instanceof Move) {
			cpw.getGameBoardWindow().getGameBoard().recievedMove((Move)obj);
		}
	}
	
	/////////// SETTERS AND GETTERS ////////////////////
	public Player getPlayer() {
		return player;
	}

	private void setPlayer(Player player) {
		this.player = player;
	}

	public JFrame getClientWindow() {
		return cpw.getClientWindow();
	}
	
	public ClientPanelWindow getClientPanelWindow() {
		return cpw;
	}
	
}
