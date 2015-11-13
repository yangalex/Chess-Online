package Client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Server.Player;
import Server.Settings;
import Server.Request.Authenticate;
import Server.Request.ChatMessage;
import Server.Request.Register;

public class ChessClient extends Thread{
	/// Server Properties
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	/// Player Properties
	private Player player;
	
	ChessClient(String host, int port){
		if (connectToServer(host,port)){
			try {
				oos = new ObjectOutputStream(socket.getOutputStream());
				ois = new ObjectInputStream(socket.getInputStream());
				
				sendToServer(new Register("SAL","american12", "Valentyna", "Restrepo"));
				//sendToServer(new Authenticate("valentyna15","american12"));
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

	private Boolean connectToServer(String host, int port) {
		try {
			socket = new Socket(host,port);
			message("Connected sucessfully to server.");
			return true;
		} catch (IOException e) {
			message("Could not connect to server.");
			if (Settings.Debug) e.printStackTrace();
			return false;
		}
	}
	
	public void run(){
		while (true){
			try {
				Object obj = ois.readObject();
				processRequest(obj);
				
			} catch (ClassNotFoundException | IOException e) {
				closeClient();
				if (Settings.Debug) e.printStackTrace();
				return;
			}
		}
	}
	
	public void message(String message){
		System.out.println(message);
	}
	
	private void closeClient() {
		try {
			ois.close();
			oos.close();
			socket.close();
			message("Client: Disconnected from Server");
			return;
		} catch (IOException e1) {
			if (Settings.Debug) e1.printStackTrace();
		}		
	}

	private void processRequest(Object obj) {
		if (obj instanceof ChatMessage){
			//Got a new chat message display it in the GUI
			String message = ((ChatMessage) obj).getMessage();
			message(message);
		}
		else if (obj instanceof Register){
			//// Did means that it could not register the user, please try again
			message("Did not register succesfully");
		}
		
		else if (obj instanceof Player){
			setPlayer((Player) obj);
			message("Got a player back" + ((Player) obj).getFirstName() + " " + ((Player) obj).getLastName());
		}
	}
	
	/////////// SETTERS AND GETTERS ////////////////////
	public Player getPlayer() {
		return player;
	}

	private void setPlayer(Player player) {
		this.player = player;
	}
	
	////////////////// MAIN /////////////////////////
	public static void main(String [] args){
		ChessClient cc = new ChessClient("45.55.5.167",61111);
		cc.start();
	}
}
