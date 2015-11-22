package Server;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import Server.Request.Authenticate;
import Server.Request.ChatMessage;
import Server.Request.GameRequest;
import Server.Request.OnlinePlayers;
import Server.Request.Register;

public class ChessClientSocket extends Thread {
	// CLIENT CONNECTION
	private Socket socket;
	private ChessServer chessServer;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	//PLAYER SETTINGS
	private Player player;
	
	ChessClientSocket(Socket s, ChessServer cs){
		socket = s;
		chessServer = cs;
		if (!createStreams()) {
			close();
			return;
		}
		start();

	}
	
	public Boolean createStreams(){
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
 
	public void run(){
		while(true){
				try {
					Object obj = ois.readObject();
					processRequest(obj);
				} catch (ClassNotFoundException e) {
					close();
					if (Settings.Debug) e.printStackTrace();
					break;
				} catch (IOException e) {
					close();
					if (Settings.Debug) e.getMessage();
					break;
				}
		}
	}
	
	public void message(String message){
		chessServer.message("CLIENT:" + message);
	}
	
 	private void processRequest(Object obj) {
		if (obj instanceof Authenticate){
			message(socket.getInetAddress().getHostAddress() + " is trying to log in");
			Player p = null;
			try {
				p = chessServer.getDatabaseManager().playerLogin((Authenticate) obj);
			} catch (IOException e) {
				((Authenticate) obj).message = "Player already online";
				sendToClient(obj);
				if (Settings.Debug) e.printStackTrace();
			}
			if (p != null){
				setPlayer(p);
				sendToClient(p);
			}
			else{
				((Authenticate) obj).message = "Could not login, please check your credentials.";
				sendToClient(obj);
			}
			
		}
		else if (obj instanceof Register){
			try {
				Player p = chessServer.getDatabaseManager().createPlayer((Register) obj);
				if (p != null){
					setPlayer(p);				
					sendToClient(p);
				}
			} catch (SQLException e) {
				if (e.getMessage().contains("Duplicate")){
					((Register) obj).message = "Username already exist";
					sendToClient(obj);
				}
				else sendToClient(obj);
			}

		}
		else if (obj instanceof ChatMessage){
			// got a message, send to everyone (including person who sent it)
			chessServer.sendToClients(obj);
		}
		else if (obj instanceof OnlinePlayers){
			Vector<Player> players = chessServer.getDatabaseManager().getOnlinePlayers();
			Iterator<Player> it = players.iterator();
			while(it.hasNext()){
				Player p = it.next();
				if (p.getUsername().equals(player.getUsername())){
					it.remove();
				}
			}
			sendToClient(players);
		}
		else if (obj instanceof GameRequest){
			for (ChessClientSocket ccs: chessServer.getClients()){
				if (ccs.getPlayer().getUsername().equals(((GameRequest) obj).getRequest().getUsername())){
					ccs.sendToClient(obj);
					System.out.println("SENT THE GAME REQUEST TO USER");
					return;
				}
			}
		}
	}
	
	public void close(){
		//Make offline on database
		if (player != null) chessServer.getDatabaseManager().playerOffline(player);
		//Remove from server
		chessServer.removeClient(this);
		//Close connections
		chessServer.message("Client Disconnected " + socket.getInetAddress().getHostAddress());
		try {
			if (oos != null){
				oos.close();
			}
			if (ois != null){
				ois.close();
			}
			socket.close();
		} catch (IOException e) {
			if (Settings.Debug) e.printStackTrace();
		}
	}
	
	public void sendToClient(Object obj){
		try {
			oos.writeObject(obj);
			oos.flush();
		} catch (IOException e) {
			if (Settings.Debug) e.printStackTrace();
		}
	}

	
	///////// GETTERS AND SETTERS /////////////

	public Player getPlayer() {
		return player;
	}
	

	private void setPlayer(Player player) {
		this.player = player;
	}
}
