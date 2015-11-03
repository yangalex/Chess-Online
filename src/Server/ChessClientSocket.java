package Server;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ChessClientSocket extends Thread {
	private Socket socket;
	private ChessServer chessServer;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	ChessClientSocket(Socket s, ChessServer cs){
		socket = s;
		chessServer = cs;
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
 
	public void run(){
		while(true){
				if(ois == null) continue;
				try {
					Object obj = ois.readObject();
					if (obj instanceof Authenticate){
						if (chessServer.getDatabaseManager() == null){
							sendToClient("Server: Could not authenticate request.");
							sendToClient("Server: Database connetion could not be established.");
							chessServer.message("Server: coult not authenticate request. Could not connect to database");
						}
						else {
						chessServer.getDatabaseManager().authenticate((Authenticate) obj);
						}
					}
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
	

	public void sendToClient(Object obj){
		try {
			oos.writeObject(obj);
			oos.flush();
		} catch (IOException e) {
			if (Settings.Debug) e.printStackTrace();
		}
	}
}
