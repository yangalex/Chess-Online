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
		
		// Connecting to Server
		try{
			ss = new ServerSocket(Settings.port);
			clients = new Vector<ChessClientSocket>();
			message("Server has been started!");
			message("Listening: " + ss.getInetAddress() + ":"+ Settings.port);
		} 
		catch(IOException ioe){
			message("Problem starting server: " + ioe.getMessage());
			if (Settings.Debug) ioe.printStackTrace();
		}
		
		//Connecting to Database
		try {
			databaseManager = new DatabaseManager(this);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
			if (Settings.Debug) e.printStackTrace();
		}
	}
	

	@Override
	public void run(){
		message("Waiting for connections....");
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
	
	public DatabaseManager getDatabaseManager(){
		return databaseManager;
	}
}
