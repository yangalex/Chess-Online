package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChestServer extends Thread {
	private Server server;
	private ServerSocket ss;
	ChestServer(int port, Server server){
		this.server = server;
		try{
			ss = new ServerSocket(port);
			server.message("Server has been started!");
			server.message("Waiting for connections....");
		}
		catch(IOException ioe){
			server.message("Problem starting server: " + ioe.getMessage());
		}
	}
	
	@Override
	public void run(){
		if (ss == null) return;
		while(!ss.isClosed()){
			try{
				Socket s = ss.accept();
				server.message("Connection established: " + s.getInetAddress());
				server.userConnected();				
			}
			catch(IOException ioe){
				server.message(ioe.getMessage());
			}
		}
	}
}
