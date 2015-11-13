package Server;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ChessClientSocket extends Thread {
	// CLIENT CONNECTION
	private Socket socket;
	private ChessServer chessServer;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	//PLAYER SETTINGS
	private String username;
	
	ChessClientSocket(Socket s, ChessServer cs){
		socket = s;
		chessServer = cs;
		if (!createStreams()) {
			close();
			return;
		}

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
				if(ois == null) continue;
				try {
					Object obj = ois.readObject();
					processRequest(obj);
				} catch (ClassNotFoundException e) {
					if (Settings.Debug) e.printStackTrace();
					chessServer.message("Server: Client Disconnected");
					break;
				} catch (IOException e) {
					if (Settings.Debug) e.printStackTrace();
					chessServer.message("Server: Client Disconnected");
					break;
				}
		}
	}
	
	private void processRequest(Object obj) {
		if (obj instanceof Authenticate){
			//Authenticate request to login | Send back Authenticate OBJ for client to proceed
			
		}
		else if (obj instanceof Register){
			chessServer.getDatabaseManager().createUser((Register) obj);
			//Create new user | Send back Register OBJ for client to proceed
		}
	}
	
	public void close(){
		//Remove from server
		chessServer.removeClient(this);
		//Close connections
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
	
	public String getUsername() {
		return username;
	}
	

	public void setUsername(String username) {
		this.username = username;
	}
}
