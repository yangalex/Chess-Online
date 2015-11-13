package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;
import java.io.IOException;
import java.lang.Thread;

public class ChessServer extends Thread{
	private ServerSocket ss;
	private Vector<ChessClientSocket> clients;
	private DatabaseManager databaseManager;
	
	public ChessServer(){
		if (startServer()) {
			message("Server started successfully.");
		}
		else {
			message("Could not start Server");
			return;
		}
		if (connectToDatabase()){
			message("Connection to database established.");
		}
		else{
			message("Could not connect to Database");
			return;
		}
		start();
	}
	
	private Boolean startServer(){
		// Connecting to Server
		try{
			ss = new ServerSocket(Settings.port);
			clients = new Vector<ChessClientSocket>();
			return true;
		} 
		catch(IOException ioe){
			message("Problem starting server: " + ioe.getMessage());
			if (Settings.Debug) ioe.printStackTrace();
			return false;
		}
	}
		
	private Boolean connectToDatabase(){
		//Connecting to Database
		try {
			databaseManager = new DatabaseManager(this);
			return true;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
			if (Settings.Debug) e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public void run(){
		message("Waiting for connections....");
		while(true){
			// Accepting new connections
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
	
	protected void removeClient(ChessClientSocket chessClientSocket) {
		clients.remove(chessClientSocket);
		message("Client Disconnected.");
	}
	
	public void close(){
		for (ChessClientSocket sct : clients){
			sct.close();			
		}
		
		if (databaseManager != null)
		databaseManager.close();
		
		if (ss != null)
			try {
				ss.close();
			} catch (IOException e) {
				if (Settings.Debug) e.printStackTrace();
			}
	}
	
	//////////// COMMUNICATIONS //////////////
	public void sendToClients(Object obj){
		for (ChessClientSocket ccs: clients){
			ccs.sendToClient(obj);
		}
	}
	
	public void sendToClients(Object obj, ChessClientSocket sender){
		for (ChessClientSocket ccs: clients){
			if (sender == ccs) continue;
			ccs.sendToClient(obj);
		}
	}
	
	/////// GETTERS AND SETTERS //////////////
	public Vector<ChessClientSocket> getClients(){
		return clients;
	}
	
	public DatabaseManager getDatabaseManager(){
		return databaseManager;
	}
	
	/////////////// MAIN ////////////////////

	public static void main(String[] args){
		new ChessServer();
	}
}
