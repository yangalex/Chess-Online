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
				try {
					Object obj = ois.readObject();
					if (obj instanceof Authenticate){
						chessServer.message("Got a Authenticate Object");
						authenticate((Authenticate) obj);
					}
				} catch (ClassNotFoundException e) {
					if (Settings.Debug) e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	private void authenticate(Authenticate obj) {
		String publicKey = "1234";
		// Talk to database
		if (obj.getUsername(publicKey) == "Camilo"){
			chessServer.message("Camilo!");
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
