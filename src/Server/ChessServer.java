package Server;



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;
import java.lang.Thread;

public class ChessServer extends Thread{
	private ServerSocket ss;
	private Vector<ChessClientSocket> clients;
	private DatabaseManager databaseManager;
	
	ChessServer(){
		startServer();
		connectToDatabase();
		message("Waiting for connections....");
	}
	
	private void startServer() {
		try{
			ss = new ServerSocket(Settings.port);
			clients = new Vector<ChessClientSocket>();
			message("Server has been started!");
			message("Listening: " + ss.getInetAddress() + ":"+ Settings.port);
			start();
		} 
		catch(IOException ioe){
			message("Problem starting server: " + ioe.getMessage());
			if (Settings.Debug) ioe.printStackTrace();
		}
	}
	
	private void connectToDatabase() {
		try {
			databaseManager = new DatabaseManager();
		} catch (SQLException e) {
			message("Problem connecting to Database.");
			if (Settings.Debug) e.printStackTrace();
		} catch (ClassNotFoundException e) {
			message("Database: Class not found");
			if (Settings.Debug) e.printStackTrace();
		} catch (InstantiationException e) {
			message("Database: InstantiationException");	
			if (Settings.Debug) e.printStackTrace();
		} catch (IllegalAccessException e) {
			message("Database: IllegalAccessException");	
			if (Settings.Debug) e.printStackTrace();
		}
	}

	@Override
	public void run(){
		while(true){
			try{
				Socket s = ss.accept();
				ChessClientSocket ccs = new ChessClientSocket(s,this);
				clients.add(ccs);
				message("Connection established: " + s.getInetAddress().getHostAddress());
			}
			catch(IOException ioe){
				message("Could not connect to client.");
				if (Settings.Debug) ioe.printStackTrace();
			}
		}
	}
	
	public void message(String message){
		System.out.println(message);
	}
	
	public void removeClient(ChessClientSocket chessClientSocket) {
		clients.remove(chessClientSocket);
		message("Client Disconnected.");
	}
	
	public static void main(String[] args){
		new ChessServer();
	}
}
